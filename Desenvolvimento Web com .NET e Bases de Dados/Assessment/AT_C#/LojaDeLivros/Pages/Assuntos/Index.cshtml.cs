using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;
using LojaDeMangas.Models;

namespace LojaDeMangas.Pages.Assuntos
{
    public class IndexModel : PageModel
    {
        private readonly LojaDbContext _context;

        public IndexModel(LojaDbContext context)
        {
            _context = context;
        }

        public IList<Assunto> Assuntos { get; set; }

        public async Task OnGetAsync()
        {
            Assuntos = await _context.Assuntos.ToListAsync();
        }
    }
}
