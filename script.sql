CREATE TABLE USUARIO(
    nome varchar,
    sobrenome varchar,
    email varchar PRIMARY KEY,
    senha VARCHAR,
    apelido varchar,
    dt_nasc DATE,
    foto varchar,
    cidade varchar,
    estado varchar,
    permissao varchar
);

CREATE TABLE FILME(
    foto varchar,
    titulo varchar,
    sinopse varchar,
    ano varchar,
	data_inset DATE DEFAULT NOW(),
    id SERIAL PRIMARY KEY
);

CREATE TABLE GENERO(
    nome varchar PRIMARY KEY
);

CREATE TABLE GRUPO(
    id SERIAL PRIMARY KEY,
    nome VARCHAR, 
    descricao VARCHAR, 
    criador VARCHAR REFERENCES USUARIO(email)
);

CREATE TABLE AMIZADE(
    remetente varchar REFERENCES USUARIO(email),
    destinatario varchar REFERENCES USUARIO(email),
    status integer
);

CREATE TABLE AVALIACAO(
    id_filme INTEGER REFERENCES FILME(id),
    email_usr varchar REFERENCES USUARIO(email),
    rating VARCHAR,
    descricao VARCHAR
); 

CREATE TABLE TOPICO(
    id SERIAL PRIMARY KEY,
    grupo_id INTEGER REFERENCES GRUPO(id),
    email_usr VARCHAR REFERENCES USUARIO(email),
    filme_id INTEGER REFERENCES FILME(id),
    comentario VARCHAR,
    titulo VARCHAR
);

CREATE TABLE COMENTARIO(
    comentario VARCHAR,
    tpc_id INTEGER REFERENCES TOPICO(id),
    usr_email VARCHAR REFERENCES USUARIO(email)
);

CREATE TABLE ESTADO(
    sigla VARCHAR PRIMARY KEY
);

CREATE TABLE GRUPO_PARTICIPANTE(
    grupo_id INTEGER REFERENCES GRUPO(id),
    usr_email VARCHAR REFERENCES USUARIO(email)
);

CREATE TABLE RECOMENDACAO(
    filme INTEGER REFERENCES FILME(id) ,
    rem VARCHAR REFERENCES USUARIO(email),
    dest VARCHAR REFERENCES USUARIO(email)
);

CREATE TABLE GENERO_FILME(
    id_filme INTEGER REFERENCES FILME(id),
    genero VARCHAR REFERENCES GENERO(nome)
);

CREATE TABLE ATOR_FILME(
    id_filme INTEGER REFERENCES FILME(id),
    ator VARCHAR
);

CREATE TABLE DIRETOR_FILME(
    id_filme INTEGER REFERENCES FILME(id),
    diretor VARCHAR
);


create or replace function avl_media(INTEGER)
   returns INTEGER 
   language plpgsql
  as
$$
declare 
-- variable declaration
begin
    return 5;
end;
$$