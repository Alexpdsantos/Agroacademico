-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pcc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pcc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pcc` DEFAULT CHARACTER SET latin1 ;
USE `pcc` ;

-- -----------------------------------------------------
-- Table `pcc`.`cadastrarproduto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pcc`.`cadastrarproduto` (
  `codigo` INT(11) NOT NULL,
  `descricao` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `pcc`.`cadastrartalhao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pcc`.`cadastrartalhao` (
  `talhao` VARCHAR(20) NOT NULL,
  `variedade` VARCHAR(20) NOT NULL,
  `area` VARCHAR(20) NULL DEFAULT NULL,
  `quantPes` INT(11) NULL DEFAULT NULL,
  `espacamento` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`talhao`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `pcc`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pcc`.`produto` (
  `idProduto` INT(11) NOT NULL AUTO_INCREMENT,
  `dataCriacao` DATE NOT NULL,
  `codigo` INT(11) NOT NULL,
  `descricao` VARCHAR(20) NOT NULL,
  `talhao` VARCHAR(20) NOT NULL,
  `lote` VARCHAR(20) NOT NULL,
  `quantidade` FLOAT(10,1) NOT NULL,
  PRIMARY KEY (`idProduto`),
    FOREIGN KEY (`codigo`)
    REFERENCES `pcc`.`cadastrarproduto` (`codigo`),
    FOREIGN KEY (`talhao`)
    REFERENCES `pcc`.`cadastrartalhao` (`talhao`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `pcc`.`beneficiar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pcc`.`beneficiar` (
  `idBeneficiar` INT(11) NOT NULL AUTO_INCREMENT,
  `idProduto` INT(11) NOT NULL,
  `dataBeneficiamento` DATE NOT NULL,
  `codigo` INT(11) NOT NULL,
  `descricao` VARCHAR(20) NOT NULL,
  `lote` VARCHAR(20) NOT NULL,
  `talhao` VARCHAR(20) NOT NULL,
  `entrada` FLOAT(10,1) NOT NULL,
  `saida` FLOAT(10,1) NOT NULL,
  `rendimento` FLOAT(10,1) NOT NULL,
  PRIMARY KEY (`idBeneficiar`),
    FOREIGN KEY (`idProduto`)
    REFERENCES `pcc`.`produto` (`idProduto`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `pcc`.`estoquebeneficiado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pcc`.`estoquebeneficiado` (
  `idBeneficiado` INT(11) NOT NULL AUTO_INCREMENT,
  `dataCriacao` DATE NOT NULL,
  `codigo` INT(11) NOT NULL,
  `descricao` VARCHAR(40) NOT NULL,
  `lote` VARCHAR(20) NOT NULL,
  `quantidade` FLOAT(10,2) NOT NULL,
  `talhao` VARCHAR(40) NOT NULL,
  `qualidade` VARCHAR(40) NOT NULL,
  `tipo` VARCHAR(40) NOT NULL,
  `umidade` FLOAT(10,1) NOT NULL,
  `rendimento` FLOAT(10,1) NOT NULL,
  `valor` FLOAT(10,2),
  `idBeneficiar` INT,
  `idProduto` INT,
  PRIMARY KEY (`idBeneficiado`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `pcc`.`relatoriobeneficiado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pcc`.`relatoriobeneficiado` (
  `idBeneficiado` INT(11) NOT NULL AUTO_INCREMENT,
  `dataCriacao` DATE NOT NULL,
  `codigo` INT(11) NOT NULL,
  `descricao` VARCHAR(40) NOT NULL,
  `lote` VARCHAR(20) NOT NULL,
  `quantidade` FLOAT(10,2) NOT NULL,
  `talhao` VARCHAR(40) NOT NULL,
  `qualidade` VARCHAR(40) NOT NULL,
  `tipo` VARCHAR(40) NOT NULL,
  `umidade` FLOAT(10,1) NOT NULL,
  `rendimento` FLOAT(10,1) NOT NULL,
  PRIMARY KEY (`idBeneficiado`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `pcc`.`relatoriobeneficiar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pcc`.`relatoriobeneficiar` (
  `idBeneficiar` INT(11) NOT NULL AUTO_INCREMENT,
  `idProduto` INT(11) NOT NULL,
  `dataBeneficiamento` DATE NOT NULL,
  `codigo` INT(11) NOT NULL,
  `descricao` VARCHAR(20) NOT NULL,
  `lote` VARCHAR(20) NOT NULL,
  `talhao` VARCHAR(20) NOT NULL,
  `entrada` FLOAT(10,1) NULL DEFAULT NULL,
  `saida` FLOAT(10,1) NULL DEFAULT NULL,
  `rendimento` FLOAT(10,1) NULL DEFAULT NULL,
  PRIMARY KEY (`idBeneficiar`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `pcc`.`relatorioproduto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pcc`.`relatorioproduto` (
  `codigo` INT(11) NOT NULL,
  `descricao` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `pcc`.`relatorioprodutos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pcc`.`relatorioprodutos` (
  `idProduto` INT(11) NOT NULL AUTO_INCREMENT,
  `dataCriacao` DATE NOT NULL,
  `codigo` INT(11) NOT NULL,
  `descricao` VARCHAR(20) NOT NULL,
  `talhao` VARCHAR(20) NOT NULL,
  `lote` VARCHAR(20) NOT NULL,
  `quantidade` FLOAT(10,1) NOT NULL,
  PRIMARY KEY (`idProduto`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `pcc`.`relatoriotalhao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pcc`.`relatoriotalhao` (
  `talhao` VARCHAR(20) NOT NULL,
  `variedade` VARCHAR(20) NOT NULL,
  `area` VARCHAR(20) NULL DEFAULT NULL,
  `quantPes` INT(11) NULL DEFAULT NULL,
  `espacamento` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`talhao`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `pcc`.`venderproduto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pcc`.`venderproduto` (
  `idVenda` INT(11) NOT NULL AUTO_INCREMENT,
  `idBeneficiado` INT(11) ,
  `codigo` INT(11) NOT NULL,
  `descricao` VARCHAR(100) NOT NULL,
  `talhao` VARCHAR(40) NOT NULL,
  `lote` VARCHAR(20) NOT NULL,
  `tipo` VARCHAR(40) NOT NULL,
  `qualidade` VARCHAR(100) NOT NULL,
  `quantidade` FLOAT(10,1) NOT NULL,
  `precokg` DOUBLE(10,2) NOT NULL,
  `desconto` INT NOT NULL,
  `valorTotal` DOUBLE(10,2) NOT NULL,
  `cliente` VARCHAR(100) NOT NULL,
  `dataVenda` DATE NOT NULL,
  PRIMARY KEY (`idVenda`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `pcc`.`relatoriovendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pcc`.`relatoriovendas` (
  `idVenda` INT(11) NOT NULL AUTO_INCREMENT,
  `idBeneficiado` INT(11),
  `codigo` INT(11) NOT NULL,
  `descricao` VARCHAR(100) NOT NULL,
  `talhao` VARCHAR(40) NOT NULL,
  `lote` VARCHAR(20) NOT NULL,
  `tipo` VARCHAR(40) NOT NULL,
  `qualidade` VARCHAR(100) NOT NULL,
  `quantidade` FLOAT(10,1) NOT NULL,
  `precokg` DOUBLE(10,2) NOT NULL,
  `desconto` INT NOT NULL,
  `valorTotal` DOUBLE NOT NULL,
  `cliente` VARCHAR(100) NOT NULL,
  `dataVenda` DATE NOT NULL,
  PRIMARY KEY (`idVenda`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;



DELIMITER $
CREATE TRIGGER Beneficiado_Insert AFTER INSERT
ON estoqueBeneficiado
FOR EACH ROW
BEGIN
    UPDATE Produto SET quantidade = quantidade - NEW.valor
WHERE idProduto = NEW.idProduto;
END$

CREATE TRIGGER Beneficiar_Upate AFTER INSERT
ON EstoqueBeneficiado
FOR EACH ROW
BEGIN
	UPDATE Beneficiar SET entrada = entrada - NEW.valor
	WHERE idBeneficiar = NEW.idBeneficiar;
    END$

CREATE TRIGGER Beneficiar_Delete AFTER DELETE
ON Beneficiar
FOR EACH ROW
BEGIN
    UPDATE Produto SET quantidade = quantidade + OLD.entrada
WHERE idProduto = OLD.idProduto;
END$

CREATE TRIGGER Vender_Insert AFTER INSERT
ON VenderProduto
FOR EACH ROW
BEGIN
    UPDATE EstoqueBeneficiado SET quantidade = quantidade - NEW.quantidade
WHERE idBeneficiado = NEW.idBeneficiado;
END$
DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

SET @@global.time_zone = '+00:00';
SET @@session.time_zone = '+00:00';