INSERT INTO matriculas (aluno_id, disciplina_id, nota)
SELECT 1, 1, 8.5
WHERE NOT EXISTS (
    SELECT 1 FROM matriculas WHERE aluno_id = 1 AND disciplina_id = 1
);

INSERT INTO matriculas (aluno_id, disciplina_id, nota)
SELECT 1, 2, 6.0
WHERE NOT EXISTS (
    SELECT 1 FROM matriculas WHERE aluno_id = 1 AND disciplina_id = 2
);

INSERT INTO matriculas (aluno_id, disciplina_id, nota)
SELECT 2, 1, 7.0
WHERE NOT EXISTS (
    SELECT 1 FROM matriculas WHERE aluno_id = 2 AND disciplina_id = 1
);

INSERT INTO matriculas (aluno_id, disciplina_id, nota)
SELECT 2, 3, 5.5
WHERE NOT EXISTS (
    SELECT 1 FROM matriculas WHERE aluno_id = 2 AND disciplina_id = 3
);

INSERT INTO matriculas (aluno_id, disciplina_id, nota)
SELECT 3, 2, 9.0
WHERE NOT EXISTS (
    SELECT 1 FROM matriculas WHERE aluno_id = 3 AND disciplina_id = 2
);

INSERT INTO matriculas (aluno_id, disciplina_id, nota)
SELECT 3, 3, 7.5
WHERE NOT EXISTS (
    SELECT 1 FROM matriculas WHERE aluno_id = 3 AND disciplina_id = 3
);
