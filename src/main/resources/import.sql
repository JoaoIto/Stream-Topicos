-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO cadastro(nome, nickname, email) VALUES('Joao Victor Povoa', 'JoaoIto', 'joaovictorpfr@gmail.com');

INSERT INTO cadastro(nome, nickname, email)
VALUES('Maria Silva', 'MariaS', 'maria.silva@example.com');

INSERT INTO cadastro(nome, nickname, email)
VALUES('Pedro Oliveira', 'PedroO', 'pedro.oliveira@example.com');

INSERT INTO cadastro(nome, nickname, email)
VALUES('Ana Santos', 'AnaS', 'ana.santos@example.com');

INSERT INTO cadastro(nome, nickname, email)
VALUES('Rafaela Almeida', 'RafaelaA', 'rafaela.almeida@example.com');

INSERT INTO cadastro(nome, nickname, email)
VALUES('Lucas Ferreira', 'LucasF', 'lucas.ferreira@example.com');
