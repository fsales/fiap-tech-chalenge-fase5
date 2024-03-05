-- Conceder permissões para criar esquemas
-- GRANT CREATE ON DATABASE postgres TO postgres;

-- Tabela para armazenar informações de usuários no esquema 'WELLS'
CREATE TABLE WELLS.USUARIO (
    id               SERIAL PRIMARY KEY,   -- Chave primária autoincrementada para identificação única do usuário
    USERNAME         VARCHAR(100) NOT NULL, -- Nome de usuário com no máximo 100 caracteres
    SENHA            VARCHAR(200) NOT NULL, -- Senha do usuário com no máximo 200 caracteres
    DATA_CRIACAO     TIMESTAMP NOT NULL,    -- Data e hora de criação do registro
    DATA_MODIFICACAO TIMESTAMP,              -- Data e hora da última modificação do registro
    CRIADO_POR       VARCHAR(255),           -- Nome do usuário ou sistema que criou o registro
    MODIFICADO_POR   VARCHAR(255),           -- Nome do usuário ou sistema que modificou o registro

    -- Adicionar índices
    CONSTRAINT idx_usuario_nome UNIQUE (USERNAME)
);

-- Tabela de Roles
CREATE TABLE WELLS.ROLE (
    id   SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    DATA_CRIACAO TIMESTAMP NOT NULL,

    -- Adicionar índices
    CONSTRAINT uk_role_nome UNIQUE (nome)
);

-- Tabela de Associação Usuario_Role
CREATE TABLE WELLS.USUARIO_ROLE (
    usuario_id SERIAL NOT NULL REFERENCES WELLS.USUARIO(id),
    role_id    SERIAL NOT NULL REFERENCES WELLS.ROLE(id),

    -- Adicionar índices
    PRIMARY KEY (usuario_id, role_id),
    CONSTRAINT idx_usuario_role_usuario_id UNIQUE (usuario_id, role_id),

    -- Adicionar restrição para evitar registros inválidos
    CONSTRAINT fk_usuario_role_usuario_id FOREIGN KEY (usuario_id) REFERENCES WELLS.USUARIO(id),
    CONSTRAINT fk_usuario_role_role_id FOREIGN KEY (role_id) REFERENCES WELLS.ROLE(id)
);
