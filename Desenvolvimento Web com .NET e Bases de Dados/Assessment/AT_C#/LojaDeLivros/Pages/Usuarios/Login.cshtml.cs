using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using LojaDeMangas.Models;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace LojaDeMangas.Pages.Usuarios
{
    public class LoginModel : PageModel
    {
        private readonly LojaDbContext _context;

        [BindProperty]
        public Usuario Usuario { get; set; }

        [TempData]
        public string ErrorMessage { get; set; }

        public LoginModel(LojaDbContext context)
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

            var usuarioExistente = await _context.Usuarios
                .FirstOrDefaultAsync(a => a.UserName == Usuario.UserName && a.Password == Usuario.Password);


            if (usuarioExistente != null)
            {
                HttpContext.Response.Cookies.Append("UsuarioLogado", usuarioExistente.UserName);
                return RedirectToPage("/Index");

            }

            ErrorMessage = "Usuário não encontrado ou senha incorreta.";

            //ViewData["Error"] = "Credenciais inválidas.";
            //return RedirectToPage("/Login");
            return Page();
        }
    }
}