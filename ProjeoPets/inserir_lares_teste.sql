
INSERT INTO lar_temporarios (nome_responsavel, telefone, endereco, capacidade_maxima, pets_atuais, disponivel, observacoes)
VALUES
('Maria Silva', '11999887766', 'Rua das Flores, 123 - São Paulo/SP', 3, 0, true, 'Casa com quintal grande'),
('João Santos', '21988776655', 'Av. Principal, 456 - Rio de Janeiro/RJ', 2, 0, true, 'Apartamento pet friendly'),
('Ana Costa', '31977665544', 'Rua Verde, 789 - Belo Horizonte/MG', 5, 0, true, 'Sítio com muito espaço')
ON CONFLICT DO NOTHING;

-- Verificar se os lares foram inseridos
SELECT * FROM lar_temporarios;

