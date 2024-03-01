-- Criar a trigger para antes de inserir um usuário na tabela trasnforma o username em minusculo
CREATE OR REPLACE FUNCTION before_insert_usuario()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.USERNAME = LOWER(TRIM(NEW.USERNAME));
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Associar a trigger à tabela
CREATE TRIGGER before_insert_trigger
    BEFORE INSERT
    ON WELLS.USUARIO
    FOR EACH ROW
EXECUTE FUNCTION before_insert_usuario();
