CREATE DATABASE sistema_funeral;

USE sistema_funeral;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    endereco VARCHAR(100),
    telefone VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE pagamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT,
    valor DECIMAL(10, 2),
    dataVencimento DATE,
    dataPagamento DATE,
    statusPagamento VARCHAR(50),
    metodoPagamento VARCHAR(50),
    FOREIGN KEY (idCliente) REFERENCES clientes(id)
);

CREATE TABLE planos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    descricao TEXT,
    preco DECIMAL(10, 2)
);

CREATE TABLE sistema_funeral.cemitérios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    endereco VARCHAR(100),
    capacidade INT
);
