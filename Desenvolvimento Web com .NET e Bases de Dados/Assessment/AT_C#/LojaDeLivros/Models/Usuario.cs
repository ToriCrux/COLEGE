using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace LojaDeMangas.Models
{
    public class Usuario
    {
        [Required(ErrorMessage = "O campo Usuário é obrigatório.")]
        [StringLength(40, ErrorMessage = "O campo Usuário deve ter no máximo 40 caracteres.")]
        [Column(TypeName = "varchar(40)")]
        public string? UserName { get; set; }
        [Required(ErrorMessage = "O campo Senha é obrigatório.")]
        [StringLength(10, ErrorMessage = "O campo Senha deve ter no máximo 10 caracteres.")]
        [Column(TypeName = "varchar(10)")]
        public string? Password { get; set; }
    }
}
