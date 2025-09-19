-- Professores
INSERT INTO professor (nome, email, senha)
VALUES ('João Silva', 'joao.silva@example.com', '12345');

-- Alunos
INSERT INTO alunos (nome, cpf, email, telefone, rua, bairro, cidade, estado)
VALUES
('Maria Souza', '12345678901', 'maria@exemplo.com', '(47) 99999-0000', 'Rua A, 100', 'Centro', 'Blumenau', 'SC'),
('Carlos Lima', '98765432100', 'carlos@exemplo.com', '(47) 98888-1111', 'Rua B, 200', 'Velha', 'Blumenau', 'SC');

-- Disciplinas
INSERT INTO disciplinas (nome, codigo)
VALUES
('Matemática', 'MAT101'),
('História', 'HIS202');
