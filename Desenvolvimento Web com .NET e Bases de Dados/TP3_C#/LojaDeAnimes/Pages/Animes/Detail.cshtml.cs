using LojaManga.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Data.SqlClient;
using Microsoft.EntityFrameworkCore;
using System.Threading.Tasks;

namespace LojaManga.Pages.Livros
{
    public class DetailModel : PageModel
    {
        private readonly LojaDbContext _context;

        [BindProperty]
        public Manga Manga { get; set; }

        public DetailModel(LojaDbContext context)
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
          return RedirectToPage("./Index");
        }
    }
}