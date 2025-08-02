-- Este SQL está correto e corresponde à sua intenção.
CREATE TABLE paciente(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(14) not null unique,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    telefone varchar(20) not null,
    ativo tinyint not null, -- MySQL usa tinyint(1) para booleanos, está correto.
    primary key(id)
);
