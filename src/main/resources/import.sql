-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into estado (nome, sigla, version) values('Tocantins', 'TO', 0);
insert into estado (nome, sigla, version) values('Goiás', 'GO', 0);
insert into estado (nome, sigla, version) values('Rio de Janeiro', 'RJ', 0);
insert into estado (nome, sigla, version) values('São Paulo', 'SP', 0);

insert into cidade (nome, id_estado) values('Palmas', 1);
insert into cidade (nome, id_estado) values('Paraiso', 1);
insert into cidade (nome, id_estado) values('Porto Nacional', 1);
insert into cidade (nome, id_estado) values('Goiania', 2);
insert into cidade (nome, id_estado) values('Rio de Janeiro', 3);
insert into cidade (nome, id_estado) values('São Paulo', 4);

insert into usuario (nome, login, senha, perfil) values('Elon Musk', 'musk', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 1);
insert into usuario (nome, login, senha, perfil) values('Bill Gates', 'gates', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 2);

insert into telefone (codigoArea, numero) values('63', '9999-9999');
insert into telefone (codigoArea, numero) values('62', '8888-8888');
insert into telefone (codigoArea, numero) values('61', '7777-7777');
insert into telefone (codigoArea, numero) values('55', '6666-6666');

insert into usuario_telefone (id_usuario, id_telefone) values(1, 1);
insert into usuario_telefone (id_usuario, id_telefone) values(1, 2);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 3);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 4);

insert into faixa (nome, descricao, preco, estoque) values('Faixa Preta', 'Faixa preta de jiu', 100.0, 10);
insert into faixa (nome, descricao, preco, estoque) values('Faixa Marrom', 'Faixa marrom de jiu', 90.0, 15);
insert into faixa (nome, descricao, preco, estoque) values('Faixa Azul', 'Faixa Azul de jiu', 80.0, 5);