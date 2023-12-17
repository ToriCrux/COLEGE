using LojaDeMangas.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LojaDeMangas.Pages.Mangas
{
    public class EditModel : PageModel
    {
        private readonly LojaDbContext _context;

        [BindProperty]
        public Manga Manga { get; set; }

        public List<SelectListItem> Autores { get; set; }

        public List<SelectListItem> Assuntos { get; set; }


        [BindProperty]
        public List<int> AutoresSelecionados { get; set; }


        [BindProperty]
        public int AssuntoSelecionadoId { get; set; }

        public IList<Manga> Mangas { get; set; }

        public EditModel(LojaDbContext context)
        {
            _context = context;
        }


        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }


            Manga = await _context.Mangas
               .Include(l => l.Autores)
               .ThenInclude(al => al.Autor)
               .Include(l => l.Assunto)
               .FirstOrDefaultAsync(m => m.Codl == id);


            Assuntos = await _context.Assuntos
             .Select(a => new SelectListItem
             {
                 Value = a.CodAs.ToString(),
                 Text = a.Descricao
             })
             .ToListAsync();


            if (Manga == null)
            {
                return NotFound();
            }

            AutoresSelecionados = Manga.Autores.Select(al => al.Autor_CodAu).ToList();
            //AssuntosSelecionados = Manga.Assuntos.Select(al => al.Assunto_CodAs).ToList();
            AssuntoSelecionadoId = Manga.Assunto.CodAs;

            Autores = await _context.Autores
            .Select(a => new SelectListItem
            {
                Value = a.CodAu.ToString(),
                Text = a.Nome,
                Selected = AutoresSelecionados.Contains(a.CodAu)
            })
            .ToListAsync();

            // Assuntos = await _context.Assuntos
            //.Select(a => new SelectListItem
            //{
            //    Value = a.CodAs.ToString(),
            //    Text = a.Descricao,
            //    Selected = Assuntos != null && Manga.Assuntos.Any(al => al.Manga_Codl == Manga.Codl)
            //})
            //.ToListAsync();

            //Assuntos = await _context.Assuntos
            //.Select(a => new SelectListItem
            //{
            //    Value = a.CodAs.ToString(),
            //    Text = a.Descricao,
            //    Selected = AssuntosSelecionados.Contains(a.CodAs)
            //})
            //.ToListAsync();

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

            _context.Attach(Manga).State = EntityState.Modified;


            Mangas = await _context.Mangas.Include(l => l.Autores).ThenInclude(al => al.Autor)
                .Include(l => l.Assunto).ToListAsync();

            Manga.Autores.Clear();

            var autoresSelecionados = await _context.Autores
                .Where(a => AutoresSelecionados.Contains(a.CodAu))
                .ToListAsync();


            if (autoresSelecionados != null)
            {
                Manga.Autores = autoresSelecionados.Select(a => new Manga_Autor
                {
                    Autor = a
                }).ToList();
            }

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!MangaExists(Manga.Codl))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return RedirectToPage("./Index");
        }

        private bool MangaExists(int id)
        {
            return _context.Mangas.Any(e => e.Codl == id);
        }
    }
}