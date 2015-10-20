CREATE DATABASE  IF NOT EXISTS `congresohemoterapia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `congresohemoterapia`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: congresohemoterapia
-- ------------------------------------------------------
-- Server version	5.5.36

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
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
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
  CONSTRAINT `fk_Person_1` FOREIGN KEY (`idCertificate`) REFERENCES `certificate` (`idCertificate`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (7,'Marcos Esteban','Urbanjea Sanchez',2,1,NULL,NULL,1),(8,'Daniel','Formia',1,2,NULL,NULL,1),(9,'Ricardo','Jaime',1,2,NULL,NULL,1),(10,'Ricardo','Jaime',1,2,NULL,NULL,1),(11,'Paulo','Vinci',1,2,NULL,NULL,1),(12,'Paulo','Vinci',1,2,NULL,NULL,1),(13,'Paulo','Vinci',1,2,NULL,NULL,1);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `certificate` (
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
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
INSERT INTO `certificate` VALUES (1,'P',1900,'Professional',1200,1450),(2,'T',1450,'Technician',800,1450),(3,'G',0,'Guest',0,0);
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-20 13:28:18
