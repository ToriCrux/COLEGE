using LojaDeMangas.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Data.SqlClient;
using Microsoft.EntityFrameworkCore;
using System.Threading.Tasks;

namespace LojaDeMangas.Pages.Mangas
{
    public class DeleteModel : PageModel
    {
        private readonly LojaDbContext _context;

        [BindProperty]
        public Manga Manga { get; set; }

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

            Manga = await _context.Mangas.Include(l => l.Autores).ThenInclude(al => al.Autor)
            .Include(l => l.Assunto).FirstOrDefaultAsync(m => m.Codl == id);


            if (Manga == null)
            {
                return NotFound();
            }

            return Page();
        }

        public async Task<IActionResult> OnPostAsync()
        {
            var livro = await _context.Mangas
                .Include(l => l.Autores)
                .FirstOrDefaultAsync(m => m.Codl == Manga.Codl);

            if (livro != null)
            {
                try
                {   
                    _context.Mangas.Remove(livro);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateException ex)
                {
                    if (ex.InnerException is SqlException sqlException && (sqlException.Number == 547 || sqlException.Number == 2601))
                    {
                        ModelState.AddModelError(string.Empty, "Não é possível excluir o Manga devido a restrições de chave estrangeira.");
                        return Page();
                    }

                    throw;
                }
            }

            return RedirectToPage("./Index");
        }
    }
}