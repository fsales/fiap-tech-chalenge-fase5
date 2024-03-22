-- Criação da tabela "pagamento" no esquema "wells"
CREATE TABLE wells.pagamento
(
    id                    BIGSERIAL PRIMARY KEY,   -- Identificador único do pagamento
    valor                 NUMERIC(19, 2) NOT NULL, -- Valor do pagamento
    nome                  VARCHAR(255) NOT NULL,   -- Nome completo do titular do pagamento
    numero                VARCHAR(19)  NOT NULL,   -- Número do pagamento
    expiracao             DATE         NOT NULL,   -- Data de expiração do pagamento
    codigo                VARCHAR(3)   NOT NULL,   -- Código do pagamento
    status                VARCHAR(10)  NOT NULL CHECK (status IN ('CRIADO', 'CONFIRMADO', 'CANCELADO')), -- Status do pagamento (Deve ser um dos seguintes: CRIADO, CONFIRMADO, CANCELADO)
    tipo_cartao           VARCHAR(26)  NOT NULL CHECK (tipo_cartao IN ('VISA_CREDITO', 'MASTERCARD_CREDITO', 'AMEX_CREDITO', 'ELO_CREDITO', 'ALELO_VALE_REFEICAO', 'TICKET_RESTAURANTE', 'MASTERCARD_MAESTRO_DEBITO', 'VISA_DEBITO', 'ELO_DEBITO')), -- Nome do tipo de cartão associado ao pagamento
    pedido_id           BIGINT       NOT NULL,   -- ID do pedido associado ao pagamento
    data_criacao          TIMESTAMP    NOT NULL,   -- Data e hora de criação do registro
    data_modificacao      TIMESTAMP,                -- Data e hora da última modificação do registro
    criado_por            VARCHAR(255) NOT NULL,   -- Nome do usuário ou sistema que criou o registro
    modificado_por        VARCHAR(255)             -- Nome do usuário ou sistema que modificou o registro
);

-- Índices para otimização de consultas
CREATE INDEX idx_nome ON wells.pagamento (nome);
CREATE INDEX idx_numero ON wells.pagamento (numero);
CREATE INDEX idx_status ON wells.pagamento (status);
CREATE INDEX idx_tipo_cartao ON wells.pagamento (tipo_cartao);
CREATE INDEX idx_pedido_id ON wells.pagamento (pedido_id);

-- Adiciona comentário à tabela pagamento
COMMENT ON TABLE wells.pagamento IS 'Tabela para armazenar informações de pagamentos';

-- Adiciona comentários às colunas da tabela pagamento
COMMENT ON COLUMN wells.pagamento.id IS 'Identificador único do pagamento';
COMMENT ON COLUMN wells.pagamento.valor IS 'Valor do pagamento';
COMMENT ON COLUMN wells.pagamento.nome IS 'Nome completo do titular do pagamento';
COMMENT ON COLUMN wells.pagamento.numero IS 'Número do pagamento';
COMMENT ON COLUMN wells.pagamento.expiracao IS 'Data de expiração do pagamento';
COMMENT ON COLUMN wells.pagamento.codigo IS 'Código do pagamento';
COMMENT ON COLUMN wells.pagamento.status IS 'Status do pagamento (Deve ser um dos seguintes: CRIADO, CONFIRMADO, CANCELADO)';
COMMENT ON COLUMN wells.pagamento.tipo_cartao IS 'Nome do tipo de cartão associado ao pagamento (Deve ser um dos seguintes: VISA_CREDITO, MASTERCARD_CREDITO, AMEX_CREDITO, ELO_CREDITO, ALELO_VALE_REFEICAO, TICKET_RESTAURANTE, MASTERCARD_MAESTRO_DEBITO, VISA_DEBITO, ELO_DEBITO)';
COMMENT ON COLUMN wells.pagamento.pedido_id IS 'ID único do pedido associado ao pagamento';
COMMENT ON COLUMN wells.pagamento.data_criacao IS 'Data e hora de criação do registro';
COMMENT ON COLUMN wells.pagamento.data_modificacao IS 'Data e hora da última modificação do registro';
COMMENT ON COLUMN wells.pagamento.criado_por IS 'Nome do usuário ou sistema que criou o registro';
COMMENT ON COLUMN wells.pagamento.modificado_por IS 'Nome do usuário ou sistema que modificou o registro';
