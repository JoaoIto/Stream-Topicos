-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database

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

insert into stream (nome, id_usuario, precoStream) values ('Stream 1', 1, 200);
insert into stream (nome, id_usuario, precoStream) values ('Stream 2', 2, 200);

insert into game (nome, categoria) values ('Fortnite', 'Battle Royale');
insert into game (nome, categoria) values ('Valorant', 'FPS');
insert into game (nome, categoria) values ('Free Fire', 'Muito ruim');
insert into game (nome, categoria) values ('Minecraft', 'Exploração e sobrevivência');

-- -- Duo 1: Stream 1, 2 horas, Fortnite + Free Fire
-- INSERT INTO duo(quantidadeHoras, id_stream) VALUES (2, 1);
--
-- -- Duo 2: Stream 2, 3 horas, Valorant + Minecraft
-- INSERT INTO duo(quantidadeHoras, id_stream) VALUES (3, 2);
--
-- -- Duo 3: Stream 1, 1 hora, Fortnite + Minecraft
-- INSERT INTO duo(quantidadeHoras, id_stream) VALUES (1, 1);
--
-- -- Solicitação 1: Usuário 1, Duo 1, Aguardando pagamento
-- INSERT INTO solicitacao(dataHora, id_usuario, id_duo, valorTotal, status) VALUES (NOW(), 1, 1, 400, 1);
--
-- -- Solicitação 2: Usuário 2, Duo 2, Pago
-- INSERT INTO solicitacao(dataHora, id_usuario, id_duo, valorTotal, status) VALUES (NOW(), 2, 2, 600, 1);
--
-- -- Solicitação 3: Usuário 3, Duo 3, Aguardando confirmação
-- INSERT INTO solicitacao(dataHora, id_usuario, id_duo, valorTotal, status) VALUES (NOW(), 3, 3, 200, 1);


