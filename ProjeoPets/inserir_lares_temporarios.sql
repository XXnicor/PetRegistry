

INSERT INTO lar_temporarios (nome_responsavel, contato, endereco_completo, capacidade_maxima, sem_vagas)
VALUES
('Maria Silva', '11999887766', 'Rua das Flores, 123 - São Paulo/SP', 3, false),
('João Santos', '21988776655', 'Av. Principal, 456 - Rio de Janeiro/RJ', 2, false),
('Ana Costa', '31977665544', 'Rua Verde, 789 - Belo Horizonte/MG', 5, false),
('Carlos Oliveira', '41966554433', 'Praça Central, 321 - Curitiba/PR', 4, false)
ON CONFLICT DO NOTHING;

SELECT * FROM lar_temporarios;

