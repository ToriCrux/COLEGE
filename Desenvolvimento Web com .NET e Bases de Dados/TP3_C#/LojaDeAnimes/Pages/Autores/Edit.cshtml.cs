using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using LojaManga.Models;
using System.Threading.Tasks;

namespace LojaManga.Pages.Autores
{
    public class EditModel : PageModel
    {
        private readonly LojaDbContext _context;

        [BindProperty]
        public Autor Autor { get; set; }

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

            Autor = await _context.Autores.FindAsync(id);

            if (Autor == null)
            {
                return NotFound();
            }

            return Page();
        }

        public async Task<IActionResult> OnPostAsync()
        {
            if (!ModelState.IsValid)
            {
                return Page();
            }

            var autorExistente = await _context.Autores
                .FirstOrDefaultAsync(a => a.Nome == Autor.Nome && a.CodAu != Autor.CodAu);

            if (autorExistente != null)
            {
                ModelState.AddModelError(string.Empty, "Já existe um autor com esse nome!");
                return Page();
            }

            _context.Attach(Autor).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AutorExists(Autor.CodAu))
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

        private bool AutorExists(int id)
        {
            return _context.Autores.Any(e => e.CodAu == id);
        }
    }
}
