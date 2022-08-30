CREATE TABLE IF NOT EXISTS USUARIO(
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

CREATE TABLE IF NOT EXISTS FILME(
    foto varchar,
    titulo varchar,
    sinopse varchar,
    ano varchar,
	data_inset DATE DEFAULT NOW(),
    id IDENTITY PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS GENERO(
    nome varchar PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS GRUPO(
    id IDENTITY PRIMARY KEY,
    nome VARCHAR, 
    descricao VARCHAR, 
    criador VARCHAR REFERENCES USUARIO(email)
);

CREATE TABLE IF NOT EXISTS AMIZADE(
    remetente varchar REFERENCES USUARIO(email),
    destinatario varchar REFERENCES USUARIO(email),
    status integer
);

CREATE TABLE IF NOT EXISTS AVALIACAO(
    id_filme INTEGER REFERENCES FILME(id),
    email_usr varchar REFERENCES USUARIO(email),
    rating VARCHAR,
    descricao VARCHAR
); 

CREATE TABLE IF NOT EXISTS TOPICO(
    id IDENTITY PRIMARY KEY,
    grupo_id INTEGER REFERENCES GRUPO(id),
    email_usr VARCHAR REFERENCES USUARIO(email),
    filme_id INTEGER REFERENCES FILME(id),
    comentario VARCHAR,
    titulo VARCHAR
);

CREATE TABLE IF NOT EXISTS COMENTARIO(
    comentario VARCHAR,
    tpc_id INTEGER REFERENCES TOPICO(id),
    usr_email VARCHAR REFERENCES USUARIO(email)
);

CREATE TABLE IF NOT EXISTS ESTADO(
    sigla VARCHAR PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS GRUPO_PARTICIPANTE(
    grupo_id INTEGER REFERENCES GRUPO(id),
    usr_email VARCHAR REFERENCES USUARIO(email)
);

CREATE TABLE IF NOT EXISTS RECOMENDACAO(
    filme INTEGER REFERENCES FILME(id) ,
    rem VARCHAR REFERENCES USUARIO(email),
    dest VARCHAR REFERENCES USUARIO(email)
);

CREATE TABLE IF NOT EXISTS GENERO_FILME(
    id_filme INTEGER REFERENCES FILME(id),
    genero VARCHAR REFERENCES GENERO(nome)
);

CREATE TABLE IF NOT EXISTS ATOR_FILME(
    id_filme INTEGER REFERENCES FILME(id),
    ator VARCHAR
);

CREATE TABLE IF NOT EXISTS DIRETOR_FILME(
    id_filme INTEGER REFERENCES FILME(id),
    diretor VARCHAR
);
