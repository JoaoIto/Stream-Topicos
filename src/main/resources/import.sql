-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into estado (nome, sigla) values('Tocantins', 'TO');
insert into estado (nome, sigla) values('Goiás', 'GO');
insert into estado (nome, sigla) values('Rio de Janeiro', 'RJ');
insert into estado (nome, sigla) values('São Paulo', 'SP');

insert into cidade (nome, id_estado) values('Palmas', 1);
insert into cidade (nome, id_estado) values('Paraiso', 1);
insert into cidade (nome, id_estado) values('Porto Nacional', 1);
insert into cidade (nome, id_estado) values('Goiania', 2);
insert into cidade (nome, id_estado) values('Rio de Janeiro', 3);
insert into cidade (nome, id_estado) values('São Paulo', 4);