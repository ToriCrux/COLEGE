using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using LojaDeMangas.Models;
using System.Threading.Tasks;
using Microsoft.Data.SqlClient;

namespace LojaDeMangas.Pages.Autores
{
    public class DeleteModel : PageModel
    {
        private readonly LojaDbContext _context;

        [BindProperty]
        public Autor Autor { get; set; }

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

            Autor = await _context.Autores
                .FirstOrDefaultAsync(m => m.CodAu == id);

            if (Autor == null)
            {
                return NotFound();
            }

            return Page();
        }

        public async Task<IActionResult> OnPostAsync()
        {
            var autor = await _context.Autores.FindAsync(Autor.CodAu);

            if (autor != null)
            {
                try
                {
                    _context.Autores.Remove(autor);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateException ex)
                {
                    if (ex.InnerException is SqlException sqlException && (sqlException.Number == 547 || sqlException.Number == 2601))
                    {
                        ModelState.AddModelError(string.Empty, "Não é possível excluir o Autor devido a restrições de chave estrangeira.");
                        return Page();
                    }

                    throw;
                }
            }

            return RedirectToPage("./Index");
        }
    }
}