using LojaManga.Models;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace LojaManga.Pages.Livros
{
    public class IndexModel : PageModel
    {
        private readonly LojaDbContext _context;

        public IndexModel(LojaDbContext context)
        {
            _context = context;
        }

        public IList<Manga> Mangas { get; set; }

        public async Task OnGetAsync()
        {

            Mangas = await _context.Mangas.Include(l => l.Autores).ThenInclude(al => al.Autor)
                    .Include(l => l.Assunto).ToListAsync();
                    //.ThenInclude(sl => sl.Assunto).ToListAsync();

        }
    }
}