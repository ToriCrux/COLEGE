using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace LojaDeMangas.Models
{
    public class Manga_Autor
    {
        public int Manga_Codl { get; set; }
        public Manga Manga { get; set; }

        public int Autor_CodAu { get; set; }
        public Autor Autor { get; set; }


    }
}
