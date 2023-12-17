using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using LojaDeMangas.Models;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace LojaDeMangas.Pages.Autores
{
    public class CreateModel : PageModel
    {
        private readonly LojaDbContext _context;

        [BindProperty]
        public Autor Autor { get; set; }

        public CreateModel(LojaDbContext context)
        {
            _context = context;
        }

        public IActionResult OnGet()
        {
            return Page();
        }

        public async Task<IActionResult> OnPostAsync()
        {
            if (!ModelState.IsValid)
            {
                return Page();
            }

            var autorExistente = await _context.Autores
                .FirstOrDefaultAsync(a => a.Nome == Autor.Nome);


            if (autorExistente != null)
            {
                ModelState.AddModelError(string.Empty, "Já existe um autor com esse nome.");
                return Page();
            }

            _context.Autores.Add(Autor);
            await _context.SaveChangesAsync();

            return RedirectToPage("./Index");
        }
    }
}