CREATE TABLE Usuario(
	id SERIAL,
	nome VARCHAR(80) NOT NULL,
	email VARCHAR(80) NOT NULL,
	senha VARCHAR(30) NOT NULL,
	telefone VARCHAR(80) NOT NULL,
	data_nasc DATE NOT NULL,
	sexo VARCHAR(20) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE Carona(
	id SERIAL,
	usuario INT,
	origem VARCHAR(200) NOT NULL,
	destino VARCHAR(200) NOT NULL,
	origem_txt VARCHAR(200) NOT NULL,
	destino_txt VARCHAR(200) NOT NULL,
	data_viagem DATE NOT NULL,
	hora_saida TIME NOT NULL,
	duracao VARCHAR(50) NOT NULL,
	ajuda_custo DOUBLE PRECISION,
	distancia VARCHAR(50),
	FOREIGN KEY (usuario) REFERENCES Usuario (id) ON DELETE CASCADE,
	PRIMARY KEY (id)
);