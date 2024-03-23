-- Adicionar entre 15 e 25 registros à tabela wells.pagamento
INSERT INTO wells.pagamento (valor, nome, numero, expiracao, codigo, status, tipo_cartao, pedido_id, data_criacao, criado_por)
VALUES
    (100.00, 'João Silva', '1234567890123456', '2024-04-30', '123', 'CRIADO', 'VISA_CREDITO', 1, CURRENT_TIMESTAMP, 'Admin'),
    (150.00, 'Maria Souza', '9876543210987654', '2024-05-31', '456', 'CONFIRMADO', 'MASTERCARD_CREDITO', 2, CURRENT_TIMESTAMP, 'Admin'),
    (75.50, 'Carlos Ferreira', '5432167890123456', '2024-06-30', '789', 'CANCELADO', 'AMEX_CREDITO', 3, CURRENT_TIMESTAMP, 'Admin'),
    (200.00, 'Ana Santos', '1234987650123456', '2024-07-31', '321', 'CRIADO', 'ELO_CREDITO', 4, CURRENT_TIMESTAMP, 'Admin'),
    (90.00, 'Fernanda Oliveira', '9876123450987654', '2024-08-31', '654', 'CONFIRMADO', 'ALELO_VALE_REFEICAO', 5, CURRENT_TIMESTAMP, 'Admin'),
    (120.00, 'Pedro Rocha', '1234789012345678', '2024-09-30', '987', 'CANCELADO', 'TICKET_RESTAURANTE', 6, CURRENT_TIMESTAMP, 'Admin'),
    (80.00, 'Mariana Lima', '9876123450987609', '2024-10-31', '654', 'CRIADO', 'MASTERCARD_MAESTRO_DEBITO', 7, CURRENT_TIMESTAMP, 'Admin'),
    (300.00, 'Lucas Vieira', '1234789012345612', '2024-11-30', '987', 'CONFIRMADO', 'VISA_DEBITO', 8, CURRENT_TIMESTAMP, 'Admin'),
    (110.00, 'Camila Costa', '9876123450987698', '2024-12-31', '654', 'CANCELADO', 'ELO_DEBITO', 9, CURRENT_TIMESTAMP, 'Admin'),
    (180.00, 'Rafaela Gomes', '1234789012345678', '2025-01-31', '987', 'CRIADO', 'VISA_CREDITO', 10, CURRENT_TIMESTAMP, 'Admin'),
    (250.00, 'Gustavo Almeida', '9876123450987698', '2025-02-28', '654', 'CONFIRMADO', 'MASTERCARD_CREDITO', 11, CURRENT_TIMESTAMP, 'Admin'),
    (95.00, 'Aline Pereira', '1234789012345678', '2025-03-31', '987', 'CANCELADO', 'AMEX_CREDITO', 12, CURRENT_TIMESTAMP, 'Admin'),
    (150.00, 'Juliana Rodrigues', '9876123450987698', '2025-04-30', '654', 'CRIADO', 'ELO_CREDITO', 13, CURRENT_TIMESTAMP, 'Admin'),
    (200.00, 'Bruno Martins', '1234789012345678', '2025-05-31', '987', 'CONFIRMADO', 'ALELO_VALE_REFEICAO', 14, CURRENT_TIMESTAMP, 'Admin'),
    (100.00, 'Fábio Oliveira', '9876123450987698', '2025-06-30', '654', 'CANCELADO', 'TICKET_RESTAURANTE', 15, CURRENT_TIMESTAMP, 'Admin'),
    (175.00, 'Patrícia Mendes', '1234789012345678', '2025-07-31', '987', 'CRIADO', 'MASTERCARD_MAESTRO_DEBITO', 16, CURRENT_TIMESTAMP, 'Admin'),
    (220.00, 'Tatiane Sousa', '9876123450987698', '2025-08-31', '654', 'CONFIRMADO', 'VISA_DEBITO', 17, CURRENT_TIMESTAMP, 'Admin'),
    (130.00, 'Marcelo Barbosa', '1234789012345678', '2025-09-30', '987', 'CANCELADO', 'ELO_DEBITO', 18, CURRENT_TIMESTAMP, 'Admin'),
    (190.00, 'Eduarda Silva', '1234789012345678', '2025-10-31', '987', 'CRIADO', 'VISA_CREDITO', 19, CURRENT_TIMESTAMP, 'Admin'),
    (270.00, 'Roberto Santos', '9876123450987698', '2025-11-30', '654', 'CONFIRMADO', 'MASTERCARD_CREDITO', 20, CURRENT_TIMESTAMP, 'Admin'),
    (105.00, 'Fabiana Oliveira', '1234789012345678', '2025-12-31', '987', 'CANCELADO', 'AMEX_CREDITO', 21, CURRENT_TIMESTAMP, 'Admin'),
    (160.00, 'Ricardo Lima', '9876123450987698', '2026-01-31', '654', 'CRIADO', 'ELO_CREDITO', 22, CURRENT_TIMESTAMP, 'Admin'),
    (210.00, 'Daniela Gomes', '1234789012345678', '2026-02-28', '987', 'CONFIRMADO', 'ALELO_VALE_REFEICAO', 23, CURRENT_TIMESTAMP, 'Admin'),
    (95.00, 'Fernando Oliveira', '9876123450987698', '2026-03-31', '654', 'CANCELADO', 'TICKET_RESTAURANTE', 24, CURRENT_TIMESTAMP, 'Admin'),
    (185.00, 'Cristina Mendes', '1234789012345678', '2026-04-30', '987', 'CRIADO', 'MASTERCARD_MAESTRO_DEBITO', 25, CURRENT_TIMESTAMP, 'Admin');