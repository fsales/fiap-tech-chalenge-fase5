-- -- Conceder permissões para criar esquemas
-- GRANT CREATE ON DATABASE postgres TO postgres;


-- Tabela para armazenar informações de usuários no esquema 'WELLS'
CREATE TABLE WELLS.USUARIO
(
    id               SERIAL PRIMARY KEY,   -- Chave primária autoincrementada para identificação única do usuário
    USUARIO          VARCHAR(100),         -- Nome de usuário com no máximo 100 caracteres
    SENHA            VARCHAR(200),         -- Senha do usuário com no máximo 200 caracteres
    role             VARCHAR(25) NOT NULL, -- Papel do usuário (Admin ou Cliente) - não pode ser nulo
    DATA_CRIACAO     TIMESTAMP,            -- Data e hora de criação do registro
    DATA_MODIFICACAO TIMESTAMP,            -- Data e hora da última modificação do registro
    CRIADO_POR       VARCHAR(255),         -- Nome do usuário ou sistema que criou o registro
    MODIFICADO_POR   VARCHAR(255)          -- Nome do usuário ou sistema que modificou o registro
);

-- Adicionar índices após a criação da tabela
CREATE INDEX idx_usuario_nome ON WELLS.USUARIO (USUARIO);
CREATE INDEX idx_usuario_role ON WELLS.USUARIO (role);
CREATE INDEX idx_data_criacao ON WELLS.USUARIO (DATA_CRIACAO);
CREATE INDEX idx_data_modificacao ON WELLS.USUARIO (DATA_MODIFICACAO);

-- criar unique para a coluna USUARIO
ALTER TABLE WELLS.USUARIO ADD CONSTRAINT unique_usuario UNIQUE (USUARIO);
