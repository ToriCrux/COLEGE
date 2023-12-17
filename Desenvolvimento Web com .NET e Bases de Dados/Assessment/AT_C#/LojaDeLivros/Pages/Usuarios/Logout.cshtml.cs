using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using LojaDeMangas.Models;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace LojaDeMangas.Pages.Usuarios
{
    public class LogoutModel : PageModel
    {
        //private readonly LojaDbContext _context;


        //public LogoutModel(LojaDbContext context)
        //{
        //    _context = context;
        //}

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

            HttpContext.Response.Cookies.Append("UsuarioLogado", string.Empty);

            return RedirectToPage("/Index");
        }
    }
}