create table if not exists role (
  id serial primary key,
  name varchar(50)
);

create table if not exists person (
  id serial primary key,
  name varchar(50) not null,
  password varchar(100) not null,
  enabled boolean not null default true,
  role_id int not null references role(id)
);
