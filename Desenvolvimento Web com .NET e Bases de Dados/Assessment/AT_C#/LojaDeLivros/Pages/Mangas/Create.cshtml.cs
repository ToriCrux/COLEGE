using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using LojaDeMangas.Models;
using System.Threading.Tasks;
using System;
using Microsoft.Data.SqlClient;

namespace LojaDeMangas.Pages.Mangas
{
    public class CreateModel : PageModel
    {
        private readonly LojaDbContext _context;

        [BindProperty]
        public Manga Manga { get; set; }

        public List<SelectListItem> Autores { get; set; }

        public List<SelectListItem> Assuntos { get; set; }

        [BindProperty]
        public List<int> AutoresSelecionados { get; set; }

        public CreateModel(LojaDbContext context)
        {
            _context = context;
        }

        public async Task<IActionResult> OnGetAsync()
        {
            Autores = await _context.Autores
                .Select(a => new SelectListItem
                {
                    Value = a.CodAu.ToString(),
                    Text = a.Nome
                })
                .ToListAsync();

            Assuntos = await _context.Assuntos
               .Select(a => new SelectListItem
               {
                   Value = a.CodAs.ToString(),
                   Text = a.Descricao
               })
               .ToListAsync();

            return Page();
        }

        public async Task<IActionResult> OnPostAsync()
        {
            if (!ModelState.IsValid)
            {
                Autores = await _context.Autores
                    .Select(a => new SelectListItem
                    {
                        Value = a.CodAu.ToString(),
                        Text = a.Nome
                    })
                    .ToListAsync();

                Assuntos = await _context.Assuntos
                   .Select(a => new SelectListItem
                   {
                       Value = a.CodAs.ToString(),
                       Text = a.Descricao
                   })
                   .ToListAsync();

                return Page();
            }

            var autoresSelecionados = await _context.Autores
                .Where(a => AutoresSelecionados.Contains(a.CodAu))
                .ToListAsync();

            Manga.Autores = autoresSelecionados.Select(a => new Manga_Autor
            {
                Autor = a
            }).ToList();


            try
            {
                _context.Mangas.Add(Manga);
                await _context.SaveChangesAsync();

                return RedirectToPage("./Index");
            }
            catch (DbUpdateException ex)
            {
                if (ex.InnerException is SqlException sqlException && sqlException.Number == 2601)
                {
                    ModelState.AddModelError(string.Empty, "Já existe um livro com esse código.");
                    return Page();
                }

                ModelState.AddModelError(string.Empty, "Ocorreu um erro ao salvar o livro. Por favor, tente novamente.");
                return Page();
            }
        }
    }
}