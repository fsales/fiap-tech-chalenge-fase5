INSERT INTO wells.usuario (username, senha, data_criacao)
VALUES
    ('cliente@wellsstore.br', '$2a$10$TD/.6nyjJl8UDIoLGVvARO0sQykrvXzotbTEx8Lfh2h6IlQ4yu9my', CURRENT_TIMESTAMP),
    ('admin@wellsstore.br', '$2a$10$TD/.6nyjJl8UDIoLGVvARO0sQykrvXzotbTEx8Lfh2h6IlQ4yu9my', CURRENT_TIMESTAMP);

INSERT INTO wells.role(nome, DATA_CRIACAO)
   values
    ('ADMIN',CURRENT_TIMESTAMP),
    ('CLIENTE',CURRENT_TIMESTAMP);


-- insert ADMIN
insert
	into
	wells.usuario_role (usuario_id,
	role_id)
select
	u.id,
	r.id
from
	wells.usuario u
join wells."role" r on
	r.nome = 'ADMIN'
where
	u.username = 'admin@wellsstore.br';

-- insert CLIENTE
insert
	into
	wells.usuario_role (usuario_id,
	role_id)
select
	u.id,
	r.id
from
	wells.usuario u
join wells."role" r on
	r.nome = 'CLIENTE'
where
	u.username = 'cliente@wellsstore.br';
