-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: banco
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `atendente`
--

DROP TABLE IF EXISTS `atendente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atendente` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME_COMPLETO` varchar(130) NOT NULL,
  `MATRICULA` varchar(20) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `TELEFONE` varchar(100) NOT NULL,
  `GERENTE_RESPONSAVEL` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `atendente_ibfk_1_idx` (`GERENTE_RESPONSAVEL`),
  CONSTRAINT `fk_atendente` FOREIGN KEY (`GERENTE_RESPONSAVEL`) REFERENCES `gerente` (`ID`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atendente`
--

LOCK TABLES `atendente` WRITE;
/*!40000 ALTER TABLE `atendente` DISABLE KEYS */;
INSERT INTO `atendente` VALUES (7,'TAUANY MUNIZ SILVA','A2220162','TAUANY.3SILVA@GMAIL.COM','(61) 981347425',5),(8,'JOÃO MARCELO DE JESUS MACEDO','A2204228','JOAO@GMAIL.COM','(61) 994322941',3);
/*!40000 ALTER TABLE `atendente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME_COMPLETO` varchar(130) NOT NULL,
  `CPF` varchar(15) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `TELEFONE` varchar(100) NOT NULL,
  `DATA_NASCIMENTO` varchar(50) NOT NULL,
  `SALARIO` double NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (3,'MARIANE GIOVANA PEIXOTO','094.727.321-27','MARIANEGIOVANAPEIXOTO@HOTMAIL.COM','(61) 99503-0024','02-09-1996',2100),(4,'JOSÉ CARLOS NETO','123.456.789-10','CARLOSJOSE@GMAIL.COM','(61) 98414-4908','06-03-1997',2000),(5,'CHAVES DA BAU','145.632.564-89','ALEATORIO@GMAIL.COM','(61) 98654-3265','15-02-1980',4500),(8,'TESTE','021.564.895-32','DASDAS@GMAIL.COM','(61) 98754-6523','16-11-1998',2500),(10,'TESTANDO','054.532.654-78','DASFDS@GMAIL.COM','(61) 98756-4253','15-11-2010',2010),(12,'DASDADAS','545.454.545-45','GDGDF@','(61) 98785-4563','15-02-2010',2100);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conta`
--

DROP TABLE IF EXISTS `conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conta` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NUMERO_CONTA` int(11) NOT NULL,
  `DIGITO_CONTA` int(11) NOT NULL,
  `LIMITE_CHEQUE_ESPECIAL` double DEFAULT '0',
  `ID_TIPO_CONTA` int(11) NOT NULL,
  `ID_STATUS_CONTA` int(11) NOT NULL,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ibfk_3_idx` (`ID_STATUS_CONTA`),
  KEY `tipo_conta` (`ID_TIPO_CONTA`),
  KEY `cliente_ibk_4_idx` (`ID_CLIENTE`),
  CONSTRAINT `cliente` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `cliente` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `status_conta` FOREIGN KEY (`ID_STATUS_CONTA`) REFERENCES `status_conta` (`ID`) ON DELETE RESTRICT,
  CONSTRAINT `tipo_conta` FOREIGN KEY (`ID_TIPO_CONTA`) REFERENCES `tipo_conta` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conta`
--

LOCK TABLES `conta` WRITE;
/*!40000 ALTER TABLE `conta` DISABLE KEYS */;
INSERT INTO `conta` VALUES (26,653035,3,4200,2,1,5),(30,353082,0,0,1,1,10),(32,652871,9,2000,2,1,12);
/*!40000 ALTER TABLE `conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gerente`
--

DROP TABLE IF EXISTS `gerente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gerente` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME_COMPLETO` varchar(130) NOT NULL,
  `MATRICULA` varchar(100) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `TELEFONE` varchar(100) NOT NULL,
  `SETOR_RESPONSAVEL` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `gege_ibfk1_idx` (`SETOR_RESPONSAVEL`),
  CONSTRAINT `fk_setor` FOREIGN KEY (`SETOR_RESPONSAVEL`) REFERENCES `setor` (`ID`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gerente`
--

LOCK TABLES `gerente` WRITE;
/*!40000 ALTER TABLE `gerente` DISABLE KEYS */;
INSERT INTO `gerente` VALUES (3,'BRUNA TORRES ','G2264535','BRUNATORRES@GMAIL.COM','(61) 99129-8832',4),(5,'JOÃO MARCELO','G2238385','JOAOMARCELO@GMAIL.COM','(61) 99432-2941',4),(7,'JOÃO MARCELO DE JESUS MACEDO','G2230424','JOAOMARCELO588@GMAIL.COM','(61) 99432-2941',1),(10,'TESTE','G3221387','TESTS@GMAIL.COM','(61) 98785-8785',3);
/*!40000 ALTER TABLE `gerente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setor`
--

DROP TABLE IF EXISTS `setor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SETOR` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setor`
--

LOCK TABLES `setor` WRITE;
/*!40000 ALTER TABLE `setor` DISABLE KEYS */;
INSERT INTO `setor` VALUES (1,'ADMINISTRATIVO'),(2,'COMERCIAL'),(3,'FINANCEIRO'),(4,'OPERACIONAL'),(5,'RECURSOS HUMANOS');
/*!40000 ALTER TABLE `setor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_conta`
--

DROP TABLE IF EXISTS `status_conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_conta` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SITUACAO` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_conta`
--

LOCK TABLES `status_conta` WRITE;
/*!40000 ALTER TABLE `status_conta` DISABLE KEYS */;
INSERT INTO `status_conta` VALUES (1,'ATIVO'),(2,'INATIVO');
/*!40000 ALTER TABLE `status_conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_conta`
--

DROP TABLE IF EXISTS `tipo_conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_conta` (
  `TIPO_CONTA` varchar(50) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_conta`
--

LOCK TABLES `tipo_conta` WRITE;
/*!40000 ALTER TABLE `tipo_conta` DISABLE KEYS */;
INSERT INTO `tipo_conta` VALUES ('CONTA SIMPLES',1),('CONTA ESPECIAL',2);
/*!40000 ALTER TABLE `tipo_conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'banco'
--

--
-- Dumping routines for database 'banco'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-05 16:36:51
