-- Professores
INSERT INTO PROFESSOR (id, nome, email)
VALUES
    (1, 'João Silva', 'joao.silva@example.com'),
    (2, 'Maria Souza', 'maria.souza@example.com');

-- Disciplinas
INSERT INTO DISCIPLINAS (id, nome, professor_id)
VALUES
    (1, 'Matemática', 1),
    (2, 'História', 2);
