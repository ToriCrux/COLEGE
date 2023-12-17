using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using LojaDeMangas.Models;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace LojaDeMangas.Pages.Assuntos
{
    public class CreateModel : PageModel
    {
        private readonly LojaDbContext _context;

        [BindProperty]
        public Assunto Assunto { get; set; }

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

            var assuntoExistente = await _context.Assuntos
               .FirstOrDefaultAsync(a => a.Descricao == Assunto.Descricao);

            if (assuntoExistente != null)
            {
                ModelState.AddModelError(string.Empty, "Já existe um assunto com essa descrição.");
                return Page();
            }

            _context.Assuntos.Add(Assunto);
            await _context.SaveChangesAsync();

            return RedirectToPage("./Index");
        }
    }
}