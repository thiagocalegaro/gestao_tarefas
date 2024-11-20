create table funcionarios (
    id serial primary key,
    uuid UUID DEFAULT gen_random_uuid(),
    nome varchar(255) not null,
    cargo varchar(255) not null,
    salario float not null,
    email varchar(255) not null unique
);
create table tarefas(
    id serial primary key,
    uuid UUID DEFAULT gen_random_uuid(),
    nome varchar(255) not null,
    descricao text not null,
    status varchar(255) not null,
    duracao float
);
create table realiza(
    id_funcionario integer not null,
    id_tarefa integer not null,
    foreign key (id_funcionario) references funcionarios(id),
    foreign key (id_tarefa) references tarefas(id)
);