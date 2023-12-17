using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace LojaDeMangas.Models
{
    public class DbInitializer : IHostedService
    {
        private readonly LojaDbContext _dbContext;
        public DbInitializer(LojaDbContext dbContext)
        {
            _dbContext = dbContext;
        }



        public Task StartAsync(CancellationToken cancellationToken)
        {


            _dbContext.Database.EnsureCreated();

            // Verifique se já existem dados em cada tabela
            if (!_dbContext.Mangas.Any())
            {
                // Adicione alguns livros iniciais
                _dbContext.Mangas.AddRange(
                    new Manga { Titulo = "Manga 1", Valor = 29.99m, CodAs = 1 },
                    new Manga { Titulo = "Manga 2", Valor = 39.99m, CodAs = 2 }
                );
            }

            if (!_dbContext.Autores.Any())
            {
                _dbContext.Autores.AddRange(
                    new Autor { Nome = "Autor 1" },
                    new Autor { Nome = "Autor 2" }
                );
            }

            if (!_dbContext.Assuntos.Any())
            {
                _dbContext.Assuntos.AddRange(
                    new Assunto { Descricao = "Assunto 1" },
                    new Assunto { Descricao = "Assunto 2" }
                );
            }

            if (!_dbContext.Usuarios.Any())
            {
                _dbContext.Usuarios.AddRange(
                    new Usuario { UserName = "usuario1", Password = "senha1" },
                    new Usuario { UserName = "usuario2", Password = "senha2" }
                );
            }

            _dbContext.SaveChanges();

            return Task.CompletedTask;
        }

        public Task StopAsync(CancellationToken cancellationToken)
        {
            return Task.CompletedTask;
        }
    }
}
