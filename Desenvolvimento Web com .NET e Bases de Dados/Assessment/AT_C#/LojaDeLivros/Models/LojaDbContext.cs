using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Reflection.Emit;
using System.Text.RegularExpressions;


namespace LojaDeMangas.Models
{
    public class LojaDbContext : DbContext
    {
        public LojaDbContext(DbContextOptions<LojaDbContext> options) : base(options)
        {
        }

        //public DbSet<ApplicationUser> Users { get; set; }


        public DbSet<Manga> Mangas { get; set; }

        public DbSet<Autor> Autores { get; set; }

        public DbSet<Assunto> Assuntos { get; set; }

        public DbSet<Usuario> Usuarios { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {

            //Modelo Manga
            modelBuilder.Entity<Manga>()
            .HasKey(l => l.Codl);

            modelBuilder.Entity<Manga>()
                .Property(l => l.Titulo)
                .IsRequired();

            modelBuilder.Entity<Manga>()
                .Property(l => l.Valor)
                .HasColumnType("decimal(18,2)");

            // Configuração do relacionamento um para muitos entre Manga e Assunto
            modelBuilder.Entity<Manga>()
           .HasOne(l => l.Assunto)
           .WithMany(a => a.Mangas)
           .HasForeignKey(l => l.CodAs);

            //Modelo Autor
            modelBuilder.Entity<Autor>()
           .HasKey(l => l.CodAu);

            modelBuilder.Entity<Autor>()
                .Property(l => l.Nome)
                .IsRequired();

            //Modelo Assunto
            modelBuilder.Entity<Assunto>()
            .HasKey(l => l.CodAs);

            modelBuilder.Entity<Assunto>()
                .Property(l => l.Descricao)
                .IsRequired();

            //Modelo Usuario
            modelBuilder.Entity<Usuario>()
           .HasKey(l => l.UserName);

            modelBuilder.Entity<Usuario>()
                .Property(l => l.Password)
                .IsRequired();

            //Manga_Autor
            modelBuilder.Entity<Manga_Autor>()
           .HasKey(la => new { la.Manga_Codl, la.Autor_CodAu });


            // Configuração do relacionamento muitos para muitos entre Manga e Autor
            modelBuilder.Entity<Manga_Autor>()
           .HasKey(la => new { la.Manga_Codl, la.Autor_CodAu });


            modelBuilder.Entity<Manga_Autor>()
                .HasOne(la => la.Manga)
                .WithMany(l => l.Autores)
                .HasForeignKey(la => la.Manga_Codl);

            modelBuilder.Entity<Manga_Autor>()
                .HasOne(la => la.Autor)
                .WithMany(a => a.Mangas)
                .HasForeignKey(la => la.Autor_CodAu);

        }


    }
}
