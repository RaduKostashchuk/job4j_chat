insert into role (name) values ('ROLE_ADMIN'), ('ROLE_USER');

insert into person (name, password, role_id) values (
    'admin',
    '$2a$10$zF05DqKZgDYAkod00hRZcuhl5h0uYNAz4/QyRxUmJp66yFlQALct.',
    (select id from role where name = 'ROLE_ADMIN'));


