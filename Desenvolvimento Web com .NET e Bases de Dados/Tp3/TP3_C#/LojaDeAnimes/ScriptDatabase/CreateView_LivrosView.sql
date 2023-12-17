SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[MangasView]
AS
SELECT        dbo.Mangas.Codl, dbo.Mangas.Titulo, dbo.Mangas.Editora, dbo.Mangas.Edicao, dbo.Mangas.AnoPublicacao, dbo.Mangas.Valor, dbo.Assuntos.Descricao, dbo.Autores.Nome
FROM            dbo.Manga_Autor INNER JOIN
                         dbo.Autores ON dbo.Manga_Autor.Autor_CodAu = dbo.Autores.CodAu INNER JOIN
                         dbo.Mangas ON dbo.Manga_Autor.Manga_Codl = dbo.Mangas.Codl INNER JOIN
                         dbo.Assuntos INNER JOIN
                         dbo.Manga_Assunto ON dbo.Assuntos.CodAs = dbo.Manga_Assunto.Assunto_CodAs ON dbo.Mangas.Codl = dbo.Manga_Assunto.Manga_Codl
GO