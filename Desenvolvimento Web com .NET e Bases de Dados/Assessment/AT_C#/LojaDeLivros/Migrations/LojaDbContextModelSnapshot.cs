
using System;
using LojaDeMangas.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

#nullable disable

namespace LojaDeMangas.Migrations
{
    [DbContext(typeof(LojaDbContext))]
    partial class LojaDbContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "7.0.0")
                .HasAnnotation("Relational:MaxIdentifierLength", 128);

            SqlServerModelBuilderExtensions.UseIdentityColumns(modelBuilder);

            modelBuilder.Entity("LojaDeMangas.Models.Assunto", b =>
                {
                    b.Property<int>("CodAs")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    SqlServerPropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("CodAs"));

                    b.Property<string>("Descricao")
                        .IsRequired()
                        .HasMaxLength(40)
                        .HasColumnType("varchar(40)");

                    b.HasKey("CodAs");

                    b.ToTable("Assuntos");
                });

            modelBuilder.Entity("LojaDeMangas.Models.Autor", b =>
                {
                    b.Property<int>("CodAu")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    SqlServerPropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("CodAu"));

                    b.Property<string>("Nome")
                        .IsRequired()
                        .HasMaxLength(40)
                        .HasColumnType("varchar(40)");

                    b.HasKey("CodAu");

                    b.ToTable("Autores");
                });

            modelBuilder.Entity("LojaDeMangas.Models.Manga", b =>
                {
                    b.Property<int>("Codl")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    SqlServerPropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("Codl"));

                    b.Property<string>("AnoPublicacao")
                        .IsRequired()
                        .HasMaxLength(4)
                        .HasColumnType("varchar(4)");

                    b.Property<int?>("CodAs")
                        .IsRequired()
                        .HasColumnType("int");

                    b.Property<string>("Edicao")
                        .IsRequired()
                        .HasMaxLength(4)
                        .HasColumnType("nvarchar(4)");

                    b.Property<string>("Editora")
                        .IsRequired()
                        .HasMaxLength(40)
                        .HasColumnType("varchar(40)");

                    b.Property<string>("Titulo")
                        .IsRequired()
                        .HasMaxLength(40)
                        .HasColumnType("varchar(40)");

                    b.Property<decimal?>("Valor")
                        .IsRequired()
                        .HasColumnType("decimal(18,2)");

                    b.HasKey("Codl");

                    b.HasIndex("CodAs");

                    b.ToTable("Mangas");
                });

            modelBuilder.Entity("LojaDeMangas.Models.Manga_Autor", b =>
                {
                    b.Property<int>("Manga_Codl")
                        .HasColumnType("int");

                    b.Property<int>("Autor_CodAu")
                        .HasColumnType("int");

                    b.HasKey("Manga_Codl", "Autor_CodAu");

                    b.HasIndex("Autor_CodAu");

                    b.ToTable("Manga_Autor");
                });

            modelBuilder.Entity("LojaDeMangas.Models.Usuario", b =>
                {
                    b.Property<string>("UserName")
                        .HasMaxLength(40)
                        .HasColumnType("varchar(40)");

                    b.Property<string>("Password")
                        .IsRequired()
                        .HasMaxLength(10)
                        .HasColumnType("varchar(10)");

                    b.HasKey("UserName");

                    b.ToTable("Usuarios");
                });

            modelBuilder.Entity("LojaDeMangas.Models.Manga", b =>
                {
                    b.HasOne("LojaDeMangas.Models.Assunto", "Assunto")
                        .WithMany("Mangas")
                        .HasForeignKey("CodAs")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Assunto");
                });

            modelBuilder.Entity("LojaDeMangas.Models.Manga_Autor", b =>
                {
                    b.HasOne("LojaDeMangas.Models.Autor", "Autor")
                        .WithMany("Mangas")
                        .HasForeignKey("Autor_CodAu")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("LojaDeMangas.Models.Manga", "Manga")
                        .WithMany("Autores")
                        .HasForeignKey("Manga_Codl")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Autor");

                    b.Navigation("Manga");
                });

            modelBuilder.Entity("LojaDeMangas.Models.Assunto", b =>
                {
                    b.Navigation("Mangas");
                });

            modelBuilder.Entity("LojaDeMangas.Models.Autor", b =>
                {
                    b.Navigation("Mangas");
                });

            modelBuilder.Entity("LojaDeMangas.Models.Manga", b =>
                {
                    b.Navigation("Autores");
                });
#pragma warning restore 612, 618
        }
    }
}
