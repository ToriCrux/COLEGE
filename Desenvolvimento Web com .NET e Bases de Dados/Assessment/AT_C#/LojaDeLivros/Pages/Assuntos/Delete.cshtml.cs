using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using LojaDeMangas.Models;
using System.Threading.Tasks;
using Microsoft.Data.SqlClient;

namespace LojaDeMangas.Pages.Assuntos
{
    public class DeleteModel : PageModel
    {
        private readonly LojaDbContext _context;

        [BindProperty]
        public Assunto Assunto { get; set; }

        public DeleteModel(LojaDbContext context)
        {
            _context = context;
        }

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            Assunto = await _context.Assuntos
                .FirstOrDefaultAsync(m => m.CodAs == id);

            if (Assunto == null)
            {
                return NotFound();
            }

            return Page();
        }

        public async Task<IActionResult> OnPostAsync()
        {
            var assunto = await _context.Assuntos.FindAsync(Assunto.CodAs);

            if (assunto != null)
            {
                try
                {
                    _context.Assuntos.Remove(assunto);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateException ex)
                {
                    if (ex.InnerException is SqlException sqlException && (sqlException.Number == 547 || sqlException.Number == 2601))
                    {
                        ModelState.AddModelError(string.Empty, "Não é possível excluir o Assunto devido a restrições de chave estrangeira.");
                        return Page();
                    }

                    throw;
                }
            }

            return RedirectToPage("./Index");
        }
    }
}