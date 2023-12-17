using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Data.SqlClient;
using Microsoft.Extensions.Configuration;
using System.Collections.Generic;
using System.Data;
using LojaDeMangas.Models;

namespace LojaDeMangas.Pages
{
    public class RelatorioModel : PageModel
    {
        private readonly IConfiguration _configuration;

        public RelatorioModel(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        public IActionResult OnGet()
        {
            return Page();

        }

        public IActionResult OnGetMangasData()
        {
            var livros = ObterDadosMangas();
            return new JsonResult(new { data = livros });
        }

        private List<MangaView> ObterDadosMangas()
        {
            var livros = new List<MangaView>();

            using (var connection = new SqlConnection(_configuration.GetConnectionString("DefaultConnection")))
            {
                connection.Open();

                using (var command = new SqlCommand("SELECT * FROM MangasView", connection))
                using (var reader = command.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        var livro = new MangaView
                        {
                            Codl = reader.GetOrdinal("Codl").ToString(),
                            Titulo = reader.GetString(reader.GetOrdinal("Titulo")),
                            Editora = reader.GetString(reader.GetOrdinal("Editora")),
                            Edicao = reader.GetString(reader.GetOrdinal("Edicao")),
                            AnoPublicacao = reader.GetOrdinal("AnoPublicacao").ToString(),
                            Valor = reader.GetOrdinal("Valor").ToString(),
                            Descricao = reader.GetString(reader.GetOrdinal("Descricao")),
                            Nome = reader.GetString(reader.GetOrdinal("Nome"))
                        };

                        livros.Add(livro);
                    }
                }
            }

            return livros;
        }
    }


}
