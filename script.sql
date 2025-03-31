-- Criando o banco --
CREATE DATABASE `Camily.intuitive.care`;
USE `Camily.intuitive.care`;

-- Criando as tabelas --
CREATE TABLE operadora_ativa (
    registro_ANS VARCHAR(20) NOT NULL,
    CNPJ VARCHAR(14) NOT NULL,
    razao_social VARCHAR(255) NOT NULL,
    nome_fantasia VARCHAR(255),
    modalidade VARCHAR(50),
    logradouro VARCHAR(255),
    numero VARCHAR(255), 
    complemento VARCHAR(255) NULL,
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    UF CHAR(2),
    CEP VARCHAR(8),
    DDD VARCHAR(3) NULL,
    telefone VARCHAR(20) NULL,
    fax VARCHAR(15) NULL,
    endereco_eletronico VARCHAR(255),
    representante VARCHAR(255),
    cargo_representante VARCHAR(255),
    regiao_de_comercializacao VARCHAR(255) NULL,  
    data_registro_ANS DATE,
    PRIMARY KEY (CNPJ)
);

CREATE TABLE demonstracao_contabil (
    DATA varchar(255) NOT NULL,
    reg_ANS VARCHAR(50) NOT NULL,
    cd_conta_contabil VARCHAR(50) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    vl_saldo_inicial DECIMAL(15,2) NOT NULL,
    vl_saldo_final DECIMAL(15,2) NOT NULL
);

-- Importar conteúdo dos arquivos --
LOAD DATA INFILE '/var/lib/mysql-files/Relatorio_cadop.csv'
INTO TABLE `Camily.intuitive.care`.operadora_ativa
FIELDS TERMINATED BY ';'  
ENCLOSED BY '"'  
LINES TERMINATED BY '\n'  
IGNORE 1 LINES  
(
    registro_ANS, 
    CNPJ, 
    razao_social,
    nome_fantasia, 
    modalidade, 
    logradouro,
    numero, 
    complemento, 
    bairro, 
    cidade, 
    UF, 
    CEP, 
    DDD, 
    telefone, 
    fax, 
    endereco_eletronico, 
    representante, 
    cargo_representante, 
    regiao_de_comercializacao, 
    data_registro_ANS
);

LOAD DATA INFILE '/var/lib/mysql-files/1T2023.csv'
INTO TABLE `Camily.intuitive.care`.demonstracao_contabil
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(DATA, 
reg_ANS, 
cd_conta_contabil, 
descricao, 
@vl_saldo_inicial, 
@vl_saldo_final)
SET
vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

LOAD DATA INFILE '/var/lib/mysql-files/2t2023.csv'
INTO TABLE `Camily.intuitive.care`.demonstracao_contabil
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(DATA, 
reg_ANS, 
cd_conta_contabil, 
descricao, 
@vl_saldo_inicial, 
@vl_saldo_final)
SET
vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

LOAD DATA INFILE '/var/lib/mysql-files/3T2023.csv'
INTO TABLE `Camily.intuitive.care`.demonstracao_contabil
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(DATA, 
reg_ANS, 
cd_conta_contabil, 
descricao, 
@vl_saldo_inicial, 
@vl_saldo_final)
SET
vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

LOAD DATA INFILE '/var/lib/mysql-files/4T2023.csv'
INTO TABLE `Camily.intuitive.care`.demonstracao_contabil
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(DATA, 
reg_ANS, 
cd_conta_contabil, 
descricao, 
@vl_saldo_inicial, 
@vl_saldo_final)
SET
vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

LOAD DATA INFILE '/var/lib/mysql-files/1T2024.csv'
INTO TABLE `Camily.intuitive.care`.demonstracao_contabil
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(DATA, 
reg_ANS, 
cd_conta_contabil, 
descricao, 
@vl_saldo_inicial, 
@vl_saldo_final)
SET
vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

LOAD DATA INFILE '/var/lib/mysql-files/2T2024.csv'
INTO TABLE `Camily.intuitive.care`.demonstracao_contabil
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(DATA, 
reg_ANS, 
cd_conta_contabil, 
descricao, 
@vl_saldo_inicial, 
@vl_saldo_final)
SET
vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

LOAD DATA INFILE '/var/lib/mysql-files/3T2024.csv'
INTO TABLE `Camily.intuitive.care`.demonstracao_contabil
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(DATA, 
reg_ANS, 
cd_conta_contabil, 
descricao, 
@vl_saldo_inicial, 
@vl_saldo_final)
SET
vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

LOAD DATA INFILE '/var/lib/mysql-files/4T2024.csv'
INTO TABLE `Camily.intuitive.care`.demonstracao_contabil
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(DATA, 
reg_ANS, 
cd_conta_contabil, 
descricao, 
@vl_saldo_inicial, 
@vl_saldo_final)
SET
vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

-- Consultas--
SELECT reg_ANS, SUM(vl_saldo_inicial + vl_saldo_final) AS total_despesas
FROM demonstracao_contabil
WHERE descricao = 'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR'
AND DATA BETWEEN '2024-01-01' AND '2024-03-31'  -- Ajuste as datas conforme necessário
GROUP BY reg_ANS
ORDER BY total_despesas DESC
LIMIT 10;

SELECT reg_ANS, SUM(vl_saldo_inicial + vl_saldo_final) AS total_despesas
FROM demonstracao_contabil
WHERE descricao = 'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR'
AND DATA BETWEEN '2023-01-01' AND '2023-12-31'  
GROUP BY reg_ANS
ORDER BY total_despesas DESC
LIMIT 10;

