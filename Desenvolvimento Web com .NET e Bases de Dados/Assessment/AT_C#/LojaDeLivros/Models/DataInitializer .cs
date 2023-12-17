namespace LojaDeMangas.Models
{
    public class DataInitializer : IHostedService
    {
        private readonly LojaDbContext _dbContext;

        public DataInitializer(LojaDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public Task StartAsync(CancellationToken cancellationToken)
        {
            // Certifique-se de que o banco de dados foi criado
            _dbContext.Database.EnsureCreated();

            // Verifique se já existem dados em cada tabela
            if (!_dbContext.Mangas.Any())
            {
                // Adicione alguns livros iniciais
                _dbContext.Mangas.AddRange(
                    new Manga { Titulo = "Manga 1", Valor = 29.99m, CodAs = 1 },
                    new Manga { Titulo = "Manga 2", Valor = 39.99m, CodAs = 2 }
                // Adicione mais livros conforme necessário
                );
            }

            if (!_dbContext.Autores.Any())
            {
                // Adicione alguns autores iniciais
                _dbContext.Autores.AddRange(
                    new Autor { Nome = "Autor 1" },
                    new Autor { Nome = "Autor 2" }
                // Adicione mais autores conforme necessário
                );
            }

            if (!_dbContext.Assuntos.Any())
            {
                // Adicione alguns assuntos iniciais
                _dbContext.Assuntos.AddRange(
                    new Assunto { Descricao = "Assunto 1" },
                    new Assunto { Descricao = "Assunto 2" }
                // Adicione mais assuntos conforme necessário
                );
            }

            if (!_dbContext.Usuarios.Any())
            {
                // Adicione alguns usuários iniciais
                _dbContext.Usuarios.AddRange(
                    new Usuario { UserName = "usuario1", Password = "senha1" },
                    new Usuario { UserName = "usuario2", Password = "senha2" }
                // Adicione mais usuários conforme necessário
                );
            }

            // Salve as alterações no banco de dados
            _dbContext.SaveChanges();

            return Task.CompletedTask;
        }

        public Task StopAsync(CancellationToken cancellationToken)
        {
            return Task.CompletedTask;
        }
    }
}
