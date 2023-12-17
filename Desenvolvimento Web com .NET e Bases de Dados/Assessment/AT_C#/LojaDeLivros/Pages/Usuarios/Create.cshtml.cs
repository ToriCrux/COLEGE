using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using LojaDeMangas.Models;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace LojaDeMangas.Pages.Usuarios
{
    public class CreateModel : PageModel
    {
        private readonly LojaDbContext _context;

        [BindProperty]
        public Usuario Usuario { get; set; }

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

            var autorExistente = await _context.Usuarios
                .FirstOrDefaultAsync(a => a.UserName == Usuario.UserName);


            if (autorExistente != null)
            {
                ModelState.AddModelError(string.Empty, "Já existe um usuario com esse nome.");
                return Page();
            }

            _context.Usuarios.Add(Usuario);
            await _context.SaveChangesAsync();

            return RedirectToPage("Login");
        }
    }
}