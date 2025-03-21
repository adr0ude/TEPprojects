CREATE DATABASE bdFarmacia;

USE bdFarmacia;

CREATE TABLE tbProduto (
	pro_id INT AUTO_INCREMENT PRIMARY KEY,
    pro_nome VARCHAR(100) NOT NULL,
    pro_preco DECIMAL(10,2) NOT NULL,
    pro_descricao VARCHAR(100) NOT NULL,
    pro_quantidade_estoque INT NOT NULL
);