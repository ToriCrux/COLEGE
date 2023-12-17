using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace LojaManga.Models
{
    public class Assunto
    {
        public int CodAs { get; set; }

        [Required(ErrorMessage = "O campo descrição do Assunto é obrigatório.")]
        [StringLength(40, ErrorMessage = "O campo Descrição deve ter no máximo 40 caracteres.")]
        [Column(TypeName = "varchar(40)")]
        public string Descricao { get; set; }

        public ICollection<Manga>? Mangas { get; set; }
    }
}
