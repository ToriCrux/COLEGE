using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace LojaDeMangas.Models
{
    public class Manga
    {
        public int Codl { get; set; }

        [Required(ErrorMessage = "O campo Título do Manga é obrigatório.")]
        [StringLength(40, ErrorMessage = "O campo Título deve ter no máximo 40 caracteres.")]
        [Column(TypeName = "varchar(40)")]
        public string Titulo { get; set; }

        [Required(ErrorMessage = "O campo Editora do Manga é obrigatório.")]
        [StringLength(40, ErrorMessage = "O campo Editora deve ter no máximo 40 caracteres.")]
        [Column(TypeName = "varchar(40)")]
        public string Editora { get; set; }

        [Required(ErrorMessage = "O campo Edição do Manga é obrigatório.")]
        [StringLength(4, ErrorMessage = "O campo Edição deve ter no máximo 4 caracteres.")]
        public string Edicao { get; set; }
        [Column(TypeName = "varchar(4)")]

        [Required(ErrorMessage = "O campo Ano de Publicação do Manga é obrigatório.")]
        [StringLength(4, ErrorMessage = "O campo Ano de Publicação deve ter no máximo 4 caracteres.")]
        public string AnoPublicacao { get; set; }

        [Required(ErrorMessage = "O campo Valor do Manga é obrigatório.")]
        public decimal? Valor { get; set; }

        [Required(ErrorMessage = "Selecione pelo menos um Autor.")]
        public List<Manga_Autor>? Autores { get; set; }

        [Required(ErrorMessage = "Selecione um Assunto.")]

        public int? CodAs { get; set; }
        public Assunto? Assunto { get; set; }

        public Manga()
        {
            Autores = new List<Manga_Autor>();
        }

    }
}
