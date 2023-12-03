using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace LojaManga.Migrations
{
    /// <inheritdoc />
    public partial class first : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Assuntos",
                columns: table => new
                {
                    CodAs = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Descricao = table.Column<string>(type: "varchar(40)", maxLength: 40, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Assuntos", x => x.CodAs);
                });

            migrationBuilder.CreateTable(
                name: "Autores",
                columns: table => new
                {
                    CodAu = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Nome = table.Column<string>(type: "varchar(40)", maxLength: 40, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Autores", x => x.CodAu);
                });

            migrationBuilder.CreateTable(
                name: "Mangas",
                columns: table => new
                {
                    Codl = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Titulo = table.Column<string>(type: "varchar(40)", maxLength: 40, nullable: false),
                    Editora = table.Column<string>(type: "varchar(40)", maxLength: 40, nullable: false),
                    Edicao = table.Column<string>(type: "nvarchar(4)", maxLength: 4, nullable: false),
                    AnoPublicacao = table.Column<string>(type: "varchar(4)", maxLength: 4, nullable: false),
                    Valor = table.Column<decimal>(type: "decimal(18,2)", nullable: false),
                    CodAs = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Animes", x => x.Codl);
                    table.ForeignKey(
                        name: "FK_Animes_Assuntos_CodAs",
                        column: x => x.CodAs,
                        principalTable: "Assuntos",
                        principalColumn: "CodAs",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Manga_Autor",
                columns: table => new
                {
                    AnimeCodl = table.Column<int>(name: "Manga_Codl", type: "int", nullable: false),
                    AutorCodAu = table.Column<int>(name: "Autor_CodAu", type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Manga_Autor", x => new { x.AnimeCodl, x.AutorCodAu });
                    table.ForeignKey(
                        name: "FK_Manga_Autor_Autores_Autor_CodAu",
                        column: x => x.AutorCodAu,
                        principalTable: "Autores",
                        principalColumn: "CodAu",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Manga_Autor_Animes_Manga_Codl",
                        column: x => x.AnimeCodl,
                        principalTable: "Mangas",
                        principalColumn: "Codl",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Manga_Autor_Autor_CodAu",
                table: "Manga_Autor",
                column: "Autor_CodAu");

            migrationBuilder.CreateIndex(
                name: "IX_Animes_CodAs",
                table: "Mangas",
                column: "CodAs");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Manga_Autor");

            migrationBuilder.DropTable(
                name: "Autores");

            migrationBuilder.DropTable(
                name: "Mangas");

            migrationBuilder.DropTable(
                name: "Assuntos");
        }
    }
}
