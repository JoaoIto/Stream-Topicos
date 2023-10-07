-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO public.login (senha) VALUES ('senha1');
INSERT INTO public.login (senha) VALUES ('senha2');

INSERT INTO public.stream (custostream, nome, nomeusuario) VALUES (10.0, 'Stream1', 'Usuario1');
INSERT INTO public.stream (custostream, nome, nomeusuario) VALUES (15.0, 'Stream2', 'Usuario2');

INSERT INTO public.cadastro (id_cadastro, nickname, nome, email) VALUES (1, 'Nickname1', 'name', 'email1@example.com');