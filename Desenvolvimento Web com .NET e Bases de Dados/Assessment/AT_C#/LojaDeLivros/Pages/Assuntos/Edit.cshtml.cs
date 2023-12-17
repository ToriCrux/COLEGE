using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using LojaDeMangas.Models;
using System.Threading.Tasks;

namespace LojaDeMangas.Pages.Assuntos
{
    public class EditModel : PageModel
    {
        private readonly LojaDbContext _context;

        [BindProperty]
        public Assunto Assunto { get; set; }

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

            Assunto = await _context.Assuntos.FindAsync(id);

            if (Assunto == null)
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

            var assuntoExistente = await _context.Assuntos
                .FirstOrDefaultAsync(a => a.Descricao == Assunto.Descricao && a.CodAs != Assunto.CodAs);

            if (assuntoExistente != null)
            {
                ModelState.AddModelError(string.Empty, "Já existe um assunto com esse nome.");
                return Page();
            }

            _context.Attach(Assunto).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AutorExists(Assunto.CodAs))
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
            return _context.Assuntos.Any(e => e.CodAs == id);
        }
    }
}
