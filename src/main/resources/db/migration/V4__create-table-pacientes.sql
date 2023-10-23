CREATE table pacientes(
    id bigserial not null unique,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    telefone varchar(50) not null,
    cpf varchar(15) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf varchar(2) not null,
    cidade varchar(100) not null,

    primary key(id)

)