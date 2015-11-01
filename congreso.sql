CREATE DATABASE  IF NOT EXISTS `congresohemoterapia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `congresohemoterapia`;
-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: congresohemoterapia
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Certificate`
--

DROP TABLE IF EXISTS `Certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Certificate` (
  `idCertificate` int(11) NOT NULL AUTO_INCREMENT COMMENT '		',
  `certificate_type` char(1) NOT NULL,
  `taxWithLodgings` double DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `taxWithoutLodgings` double DEFAULT NULL,
  `taxCompanions` double DEFAULT NULL,
  PRIMARY KEY (`idCertificate`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Certificate`
--

LOCK TABLES `Certificate` WRITE;
/*!40000 ALTER TABLE `Certificate` DISABLE KEYS */;
INSERT INTO `Certificate` VALUES (1,'P',1900,'Professional',1200,1450),(2,'T',1450,'Technician',800,1450),(3,'G',0,'Guest',0,0);
/*!40000 ALTER TABLE `Certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person` (
  `idPerson` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `companions` smallint(6) DEFAULT '0',
  `idCertificate` int(11) NOT NULL COMMENT '	',
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `lodgings` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPerson`),
  KEY `fk_Person_1_idx` (`idCertificate`),
  CONSTRAINT `fk_Person_1` FOREIGN KEY (`idCertificate`) REFERENCES `Certificate` (`idCertificate`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person` VALUES (7,'Marcos Esteban','Urbanjea Sanchez',2,1,NULL,NULL,1),(8,'Daniel','Formia',1,2,NULL,NULL,1),(9,'Ricardo','Jaime',1,2,NULL,NULL,1),(10,'Ricardo','Jaime',1,2,NULL,NULL,1),(11,'Paulo','Vinci',1,2,NULL,NULL,1),(13,'Paulo','Vinci',1,2,NULL,NULL,1),(14,'Rodrigo','Rolon Luna',1,1,NULL,NULL,1),(15,'Laura','Sanchez',0,2,NULL,NULL,2),(16,'Laura','Sanchez',0,2,NULL,NULL,2),(17,'Laura','Sanchez',0,2,NULL,NULL,2),(18,'Laura','Sanchez',0,2,NULL,NULL,2),(19,'Luciano','Castro',2,1,NULL,NULL,1),(20,'Ramiro','Rolon Luna',0,1,NULL,NULL,2),(21,'Ramiro','Rolon Luna',0,1,NULL,NULL,2),(22,'German','Urbaneja',5,1,NULL,NULL,1),(23,'German','Urbaneja',5,1,NULL,NULL,1),(24,'German','Urbaneja',5,1,NULL,NULL,1),(25,'German','Urbaneja',2,1,NULL,NULL,1),(26,'German','Urbaneja',2,1,NULL,NULL,1),(27,'German','Urbaneja',2,1,NULL,NULL,1),(57,'Daniela','Priani',2,2,'calle 38 Nro 543 dpto 2b','dnanis@gmail.com',1),(58,'Susana','Chinamberro',0,2,'10 n525','susi@hotmail.com',2),(59,'pepe','Mujica',0,3,'sfdasdf','pepin@uruguay.uy',2),(60,'Ricardo','Urbaneja',1,2,'farrignton 234','rou@tl.com.ar',1);
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-30 20:34:39
