using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace LojaDeMangas.Migrations
{

    public partial class usuario : Migration
    {
  
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Usuarios",
                columns: table => new
                {
                    UserName = table.Column<string>(type: "varchar(40)", maxLength: 40, nullable: false),
                    Password = table.Column<string>(type: "varchar(10)", maxLength: 10, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Usuarios", x => x.UserName);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Usuarios");
        }
    }
}
