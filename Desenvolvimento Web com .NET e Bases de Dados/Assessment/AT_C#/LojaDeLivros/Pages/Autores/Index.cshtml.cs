using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;
using LojaDeMangas.Models;

namespace LojaDeMangas.Pages.Autores
{
    public class IndexModel : PageModel
    {
        private readonly LojaDbContext _context;

        public IndexModel(LojaDbContext context)
        {
            _context = context;
        }

        public IList<Autor> Autores { get; set; }

        public async Task OnGetAsync()
        {
            Autores = await _context.Autores.ToListAsync();
        }
    }
}
