-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;
--
--insert into estado (nome, sigla, version) values('Tocantins', 'TO', 0);
--insert into estado (nome, sigla, version) values('Goiás', 'GO', 0);
--insert into estado (nome, sigla, version) values('Rio de Janeiro', 'RJ', 0);
--insert into estado (nome, sigla, version) values('São Paulo', 'SP', 0);

--insert into cidade (nome, id_estado) values('Palmas', 1);
--insert into cidade (nome, id_estado) values('Paraiso', 1);
--insert into cidade (nome, id_estado) values('Porto Nacional', 1);
--insert into cidade (nome, id_estado) values('Goiania', 2);
--insert into cidade (nome, id_estado) values('Rio de Janeiro', 3);
--insert into cidade (nome, id_estado) values('São Paulo', 4);

insert into usuario (nome, login, senha, perfil) values('Elon Musk', 'musk', '8NYXRw9jrvRsObmz99SXDxMWeMXiBZx0vLYuc2yRU/gQAtSYbZJRKx2fNdXkaeJ7dIv3mGQLpH2qUgTK0QfDSg==', 1);
insert into usuario (nome, login, senha, perfil) values('Bill Gates', 'gates', '7lz1xGG9KoOuSAENChquqUOTGeSlX4N7r0lp0ZjMulXXUl58jp76n1QOZOhUnEUdbXZeo95sgE9Hz7penJz1FQ==', 2);
insert into usuario (nome, login, senha, perfil) values('João Victor', 'JV stro', 'Hn7YKvnjPMNo0LcGojCX7nYnvaddtCzDm9RovbX5yYLhyeJQCNo9CY787DYxwqJLc+0ZGIudd1qxOMWhELtwsg==', 3);

insert into telefone (codigoArea, numero) values('63', '9999-9999');
insert into telefone (codigoArea, numero) values('62', '8888-8888');
insert into telefone (codigoArea, numero) values('61', '7777-7777');
insert into telefone (codigoArea, numero) values('55', '6666-6666');
insert into telefone (codigoArea, numero) values('55', '4444-4444');

insert into usuario_telefone (id_usuario, id_telefone) values(1, 1);
insert into usuario_telefone (id_usuario, id_telefone) values(1, 2);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 3);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 4);
insert into usuario_telefone (id_usuario, id_telefone) values(3, 5);

insert into stream (nome, id_usuario) values ('Stream 1', 1);
insert into stream (nome, id_usuario) values ('Stream 2', 2);
