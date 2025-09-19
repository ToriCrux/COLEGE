MERGE INTO alunos (cpf, nome, email, telefone, rua, bairro, cidade, estado)
KEY (cpf)
VALUES
('11122233344', 'Ana Silva', 'ana@teste.com', '11999990001', 'Rua Alfa, 100', 'Centro', 'Blumenau', 'SC'),
('55566677788', 'Bruno Souza', 'bruno@teste.com', '11999990002', 'Rua Beta, 200', 'Velha', 'Blumenau', 'SC'),
('99900011122', 'Carla Lima', 'carla@teste.com', '11999990003', 'Rua Gama, 300', 'Itoupava', 'Blumenau', 'SC');
