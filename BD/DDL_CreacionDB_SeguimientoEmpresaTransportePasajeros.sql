-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SeguimientoEmpresasTransportePasajeros
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SeguimientoEmpresasTransportePasajeros` ;

-- -----------------------------------------------------
-- Schema SeguimientoEmpresasTransportePasajeros
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SeguimientoEmpresasTransportePasajeros` DEFAULT CHARACTER SET utf8 ;
USE `SeguimientoEmpresasTransportePasajeros` ;

-- -----------------------------------------------------
-- Table `Empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Empresa` ;

CREATE TABLE IF NOT EXISTS `Empresa` (
  `cuit` VARCHAR(21) NOT NULL,
  `razonSocial` VARCHAR(45) NULL,
  `domicilioComercial` VARCHAR(45) NULL,
  `emailEmpresa` VARCHAR(45) NULL,
  `activa` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`cuit`),
  UNIQUE INDEX `cuil_UNIQUE` (`cuit` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Unidad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Unidad` ;

CREATE TABLE IF NOT EXISTS `Unidad` (
  `dominio` VARCHAR(21) NOT NULL,
  `modelo` VARCHAR(45) NULL,
  `nroChasis` VARCHAR(45) NULL,
  `nroMotor` VARCHAR(45) NULL,
  `carroceria` VARCHAR(45) NULL,
  `activa` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`dominio`),
  UNIQUE INDEX `dominio_UNIQUE` (`dominio` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RevisionTecnica`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RevisionTecnica` ;

CREATE TABLE IF NOT EXISTS `RevisionTecnica` (
  `nroTecnica` INT NOT NULL AUTO_INCREMENT,
  `Unidad_dominio` VARCHAR(21) NULL,
  `nroInterno` INT NULL,
  `pagoTasa` TINYINT(1) NULL,
  `rtoAprobada` TINYINT(1) NULL,
  `fechaEmisionRTO` DATE NULL,
  PRIMARY KEY (`nroTecnica`),
  INDEX `fk_RevisionTecnica_Unidad1_idx` (`Unidad_dominio` ASC) VISIBLE,
  CONSTRAINT `fk_RevisionTecnica_Unidad1`
    FOREIGN KEY (`Unidad_dominio`)
    REFERENCES `Unidad` (`dominio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Trabajador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Trabajador` ;

CREATE TABLE IF NOT EXISTS `Trabajador` (
  `dni` BIGINT(10) UNSIGNED NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `domicilio` VARCHAR(45) NULL,
  `chofer` TINYINT NULL DEFAULT 0,
  `categoria` VARCHAR(45) NULL,
  `nacionalidad` VARCHAR(45) NULL,
  PRIMARY KEY (`dni`),
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC) VISIBLE)
ENGINE = InnoDB
PACK_KEYS = 1;


-- -----------------------------------------------------
-- Table `Licencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Licencia` ;

CREATE TABLE IF NOT EXISTS `Licencia` (
  `idLicencia` INT NOT NULL,
  `Empleado_dni` BIGINT(10) UNSIGNED NOT NULL,
  `fechaEmision` DATE NULL COMMENT 'Fecha emisión lic. nacional',
  `fechaVencimiento` DATE NULL COMMENT 'Fecha de venc. lic. nacional',
  `revocada` TINYINT NULL DEFAULT 0,
  `nroLicConducirNacional` VARCHAR(45) NULL,
  `tipoLicencia` VARCHAR(45) NULL,
  PRIMARY KEY (`idLicencia`),
  INDEX `fk_Licencia_Empleado1_idx` (`Empleado_dni` ASC) VISIBLE,
  CONSTRAINT `fk_Licencia_Empleado1`
    FOREIGN KEY (`Empleado_dni`)
    REFERENCES `Trabajador` (`dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Multa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Multa` ;

CREATE TABLE IF NOT EXISTS `Multa` (
  `idMulta` INT NOT NULL,
  `Empresa_cuil` VARCHAR(21) NOT NULL,
  `monto` FLOAT NULL,
  `descripcion` VARCHAR(120) NULL,
  `pagodo` TINYINT NULL DEFAULT NULL,
  `fechaMulta` DATE NULL,
  `fechaPago` DATE NULL,
  PRIMARY KEY (`idMulta`),
  INDEX `fk_Multa_Empresa1_idx` (`Empresa_cuil` ASC) VISIBLE,
  CONSTRAINT `fk_Multa_Empresa1`
    FOREIGN KEY (`Empresa_cuil`)
    REFERENCES `Empresa` (`cuit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Contrato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Contrato` ;

CREATE TABLE IF NOT EXISTS `Contrato` (
  `idContrato` INT NOT NULL AUTO_INCREMENT,
  `Empresa_cuil` VARCHAR(21) NOT NULL,
  `nroExpAltaEmpresa` VARCHAR(45) NULL,
  `nroExpBajaEmpresa` VARCHAR(45) NULL,
  `nroResolucion` VARCHAR(45) NULL,
  `fechaInicio` DATE NOT NULL,
  `fechaFinalizacion` DATE NULL,
  `dniGerente` VARCHAR(45) NULL,
  `gerenteNombre` VARCHAR(45) NULL,
  PRIMARY KEY (`idContrato`, `Empresa_cuil`),
  INDEX `fk_Contrato_Empresa1_idx` (`Empresa_cuil` ASC) VISIBLE,
  CONSTRAINT `fk_Contrato_Empresa1`
    FOREIGN KEY (`Empresa_cuil`)
    REFERENCES `Empresa` (`cuit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Flota`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Flota` ;

CREATE TABLE IF NOT EXISTS `Flota` (
  `Contrato_idContrato` INT NOT NULL,
  `Unidad_dominio` VARCHAR(21) NOT NULL,
  `IdFlota` INT NOT NULL,
  `nroExpAltaUnidad` VARCHAR(20) NOT NULL,
  `nroExpBajaUnidad` VARCHAR(20) NULL DEFAULT NULL,
  `fechaAltaUnidad` DATE NOT NULL,
  `fechaBajaUnidad` DATE NULL DEFAULT NULL,
  `nroResolucionAltaUnidada` VARCHAR(45) NULL,
  `nroResolucionBajaUnidad` VARCHAR(45) NULL,
  `corredor` VARCHAR(45) NULL,
  `nroInterno` INT NULL,
  PRIMARY KEY (`Contrato_idContrato`, `Unidad_dominio`),
  INDEX `fk_Flota_Unidad1_idx` (`Unidad_dominio` ASC) VISIBLE,
  INDEX `fk_Flota_Contrato1_idx` (`Contrato_idContrato` ASC) VISIBLE,
  CONSTRAINT `fk_Flota_Unidad1`
    FOREIGN KEY (`Unidad_dominio`)
    REFERENCES `Unidad` (`dominio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Flota_Contrato1`
    FOREIGN KEY (`Contrato_idContrato`)
    REFERENCES `Contrato` (`idContrato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Empleado` ;

CREATE TABLE IF NOT EXISTS `Empleado` (
  `Empresa_cuil` VARCHAR(21) NOT NULL,
  `Empleado_dni` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Empresa_cuil`),
  INDEX `fk_Trabajan_Empleado1_idx` (`Empleado_dni` ASC) VISIBLE,
  CONSTRAINT `fk_Trabajan_Empresa1`
    FOREIGN KEY (`Empresa_cuil`)
    REFERENCES `Empresa` (`cuit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Trabajan_Empleado1`
    FOREIGN KEY (`Empleado_dni`)
    REFERENCES `Trabajador` (`dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
