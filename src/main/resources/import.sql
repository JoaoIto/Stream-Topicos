-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO public.login (senha, perfil) VALUES ('8NYXRw9jrvRsObmz99SXDxMWeMXiBZx0vLYuc2yRU/gQAtSYbZJRKx2fNdXkaeJ7dIv3mGQLpH2qUgTK0QfDSg==', 1);
INSERT INTO public.login (senha, perfil) VALUES ('7lz1xGG9KoOuSAENChquqUOTGeSlX4N7r0lp0ZjMulXXUl58jp76n1QOZOhUnEUdbXZeo95sgE9Hz7penJz1FQ==', 2);
INSERT INTO public.login (senha, perfil) VALUES ('Hn7YKvnjPMNo0LcGojCX7nYnvaddtCzDm9RovbX5yYLhyeJQCNo9CY787DYxwqJLc+0ZGIudd1qxOMWhELtwsg==', 3);

INSERT INTO public.stream (custostream, nome, nomeusuario) VALUES (10.0, 'Stream1', 'Usuario1');
INSERT INTO public.stream (custostream, nome, nomeusuario) VALUES (15.0, 'Stream2', 'Usuario2');

INSERT INTO public.duo (horasGame, preco, annotation, status) VALUES (2000.0, 200.0, 'Bora duo carai', true);

INSERT INTO public.cadastro (id_cadastro, nickname, nome, email) VALUES (1, 'Nickname1', 'name', 'email1@example.com');