-- Criação da tabela "pagamento" no esquema "wells"
CREATE TABLE wells.pagamento
(
    id                    BIGSERIAL PRIMARY KEY,   -- Identificador único do pagamento
    valor                 NUMERIC(19, 2) NOT NULL, -- Valor do pagamento
    nome                  VARCHAR(100),            -- Nome do titular do pagamento
    numero                VARCHAR(19),             -- Número do pagamento
    expiracao             DATE           NOT NULL, -- Data de expiração do pagamento
    codigo                VARCHAR(3),              -- Código do pagamento
    status                VARCHAR(255)   NOT NULL, -- Status do pagamento
    forma_de_pagamento_id BIGINT         NOT NULL, -- ID da forma de pagamento associada
    carrinho_id           BIGINT         NOT NULL, -- ID do pedido associado ao pagamento (único)
    data_criacao          TIMESTAMP      NOT NULL, -- Data e hora de criação do registro
    data_modificacao      TIMESTAMP,               -- Data e hora da última modificação do registro
    criado_por            VARCHAR(255),            -- Nome do usuário ou sistema que criou o registro
    modificado_por        VARCHAR(255)             -- Nome do usuário ou sistema que modificou o registro
);

-- Índices para otimização de consultas
CREATE INDEX idx_forma_de_pagamento_id ON wells.pagamento (forma_de_pagamento_id);

-- Adiciona comentário à tabela pagamento
COMMENT ON TABLE wells.pagamento IS 'Tabela para armazenar informações de pagamentos';

-- Adiciona comentários às colunas da tabela pagamento
COMMENT ON COLUMN wells.pagamento.id IS 'Identificador único do pagamento';
COMMENT ON COLUMN wells.pagamento.valor IS 'Valor do pagamento';
COMMENT ON COLUMN wells.pagamento.nome IS 'Nome do titular do pagamento';
COMMENT ON COLUMN wells.pagamento.numero IS 'Número do pagamento';
COMMENT ON COLUMN wells.pagamento.expiracao IS 'Data de expiração do pagamento';
COMMENT ON COLUMN wells.pagamento.codigo IS 'Código do pagamento';
COMMENT ON COLUMN wells.pagamento.status IS 'Status do pagamento';
COMMENT ON COLUMN wells.pagamento.forma_de_pagamento_id IS 'ID da forma de pagamento associada';
COMMENT ON COLUMN wells.pagamento.carrinho_id IS 'ID único do pedido associado ao pagamento';
COMMENT ON COLUMN wells.pagamento.data_criacao IS 'Data e hora de criação do registro';
COMMENT ON COLUMN wells.pagamento.data_modificacao IS 'Data e hora da última modificação do registro';
COMMENT ON COLUMN wells.pagamento.criado_por IS 'Nome do usuário ou sistema que criou o registro';
COMMENT ON COLUMN wells.pagamento.modificado_por IS 'Nome do usuário ou sistema que modificou o registro';