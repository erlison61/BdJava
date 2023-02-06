-- drop table sistema.Historico;
-- drop table interfaces.Conta;
-- drop table sistema.Banco;

CREATE DATABASE projetobanco;

CREATE TABLE `projetobanco`.`banco` (
  `idBanco` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`idBanco`));


CREATE TABLE `projetobanco`.`conta` (
  `idConta` INT NOT NULL  AUTO_INCREMENT,
  numeroDaConta VARCHAR(45) NULL,
  numeroDaAgencia VARCHAR(45) NULL,
  `ativa` VARCHAR(45) NULL,
  `saldo` DECIMAL(10) NULL,
  `limite` DECIMAL(10) NULL,
  `cliente` VARCHAR(45) NULL,
  `banco` INT NOT NULL,
  PRIMARY KEY (`idConta`));

ALTER TABLE `projetobanco`.`conta`
ADD CONSTRAINT `banco`
  FOREIGN KEY (`banco`)
  REFERENCES `projetobanco`.`banco` (`idBanco`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


CREATE TABLE `projetobanco`.`historico` (
  `idHistorico` INT NOT NULL  AUTO_INCREMENT,
  `operacao` VARCHAR(45) NULL,
  `data` VARCHAR(45) NULL,
  `valor` DECIMAL(10) NULL,
  numeroDaConta INT NOT NULL,
  PRIMARY KEY (`idHistorico`));

ALTER TABLE `projetobanco`.`historico`
ADD CONSTRAINT `conta`
  FOREIGN KEY (numeroDaConta)
  REFERENCES `projetobanco`.`conta` (`idConta`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;