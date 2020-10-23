-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: chatsac
-- ------------------------------------------------------
-- Server version	5.7.18-0ubuntu0.16.04.1

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
-- Table structure for table `Departament`
--

DROP TABLE IF EXISTS `Departament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Departament` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `ORG_ID` int(11) NOT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ORG_ID` (`ORG_ID`),
  CONSTRAINT `Departament_ibfk_1` FOREIGN KEY (`ORG_ID`) REFERENCES `Organization` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Departament`
--

LOCK TABLES `Departament` WRITE;
/*!40000 ALTER TABLE `Departament` DISABLE KEYS */;
INSERT INTO `Departament` VALUES (1,'GERAL',1,'2017-06-04 22:07:35',NULL);
/*!40000 ALTER TABLE `Departament` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER TR_DEPARTAMENT_DATE_CREATED 
	    BEFORE INSERT ON Departament 
	    FOR EACH ROW 
	BEGIN
	    SET NEW.date_created = NOW();
	END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER TR_DEPARTAMENT_DATE_UPDATE 
	    BEFORE UPDATE ON Departament 
	    FOR EACH ROW 
	BEGIN
	    SET NEW.date_updated = NOW();
	END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Message`
--

DROP TABLE IF EXISTS `Message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Message` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `Text` text NOT NULL,
  `date_send` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `date_visualization` datetime DEFAULT NULL,
  `date_answer` datetime DEFAULT NULL,
  `FACEBOOK_USER_ID` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `Message_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `User` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Message`
--

LOCK TABLES `Message` WRITE;
/*!40000 ALTER TABLE `Message` DISABLE KEYS */;
INSERT INTO `Message` VALUES (1,'Facebook User 1',NULL,'Olá tudo bem?','2017-06-04 22:08:53',NULL,NULL,'12345'),(2,'Shankar Nakai',1,'Olá como posso te ajudar?','2017-06-04 22:08:53',NULL,NULL,'12345'),(3,'Facebook User 1',NULL,'Gostaria de tirar algumas dúvidas sobre os seus produtos?','2017-06-04 22:08:53',NULL,NULL,'12345'),(4,'Shankar Nakai',1,'Sim, estou a dispoição','2017-06-04 22:08:53',NULL,NULL,'12345'),(5,'Shankar Nakai',1,'Em que podemos te ajudar?','2017-06-04 22:09:13',NULL,NULL,'12345'),(6,'Shankar Nakai',1,'Através dessa área podemos enviar mensagens para a nossa conversa, e eles ficarão salvas no sistema.','2017-06-04 22:09:58',NULL,NULL,'12345'),(7,'Shankar Nakai',1,'Done !!!!','2017-06-04 22:10:19',NULL,NULL,'12345');
/*!40000 ALTER TABLE `Message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Organization`
--

DROP TABLE IF EXISTS `Organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Organization` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `CNPJ` varchar(16) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Telefone` varchar(11) DEFAULT NULL,
  `FACEBOOK_TOKEN` varchar(1000) DEFAULT NULL,
  `FACEBOOK_ACCESS` varchar(1000) DEFAULT NULL,
  `FACEBOOK_PAGE` varchar(1000) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Organization`
--

LOCK TABLES `Organization` WRITE;
/*!40000 ALTER TABLE `Organization` DISABLE KEYS */;
INSERT INTO `Organization` VALUES (1,'Lorem LTDA','41257861867','shankar.nakai@gmail.com','1932413341','EAAK1yaa82XgBAAuuZAdCW5g8408HXsZCTZBSFGOA62neIKEyTDqQDUCi4PmUQJZBxmBmhdiWQMdFto4latjNkQpEvhudwTJypY2pK0WJLww19RighiBYUDNxDMZCtZA3Ldw4lZAokIeS48WiyZCVFWD4yvXCGp1MINIZD','AQBr4JCSWbW67Y1ITQRMum-eslQ6iOaktSLnmc-NWYaCYIfddwcKCaExIH95jk1_-p1JSwoNldJSjo_-fJDU9MCT8vDCC17L2vbRFCBBNkqzfNW53tdnVDVcX5vgFAF3G9qqyIFe2O3AdX1f0Jfo8HI7TkzxrTvMdc8ejP_-x4LMBTLdxdU1hCRW01dfbslz4_V3VFJhspd2tKZTywpxfi_6g7ppQi-N1ic-63nQM-m1rg8zmpKv4Zpdul-jjceuUbSzHoT-YWwHRX1mcGLeWcR7WWXARP2Dw1Skqwydm-zYi4-as7ANeXMtOc7EVAHIorc','1310346262420103','2017-06-04 22:07:35','2017-06-04 22:08:42');
/*!40000 ALTER TABLE `Organization` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER TR_ORGANIZATION_DATE_CREATED 
	    BEFORE INSERT ON Organization 
	    FOR EACH ROW 
	BEGIN
	    SET NEW.date_created = NOW();
	END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER TR_ORGANIZATION_DATE_UPDATE 
	    BEFORE UPDATE ON Organization 
	    FOR EACH ROW 
	BEGIN
	    SET NEW.date_updated = NOW();
	END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Status` int(11) unsigned DEFAULT NULL,
  `Username` varchar(25) DEFAULT NULL,
  `Password` varchar(65) DEFAULT NULL,
  `ORG_ID` int(11) DEFAULT NULL,
  `DEPARTAMENT_ID` int(11) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `date_updated` datetime DEFAULT NULL,
  `Salt` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ORG_ID` (`ORG_ID`),
  KEY `DEPARTAMENT_ID` (`DEPARTAMENT_ID`),
  CONSTRAINT `User_ibfk_1` FOREIGN KEY (`ORG_ID`) REFERENCES `Organization` (`ID`),
  CONSTRAINT `User_ibfk_2` FOREIGN KEY (`DEPARTAMENT_ID`) REFERENCES `Departament` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'Shankar Nakai',1,'shankar.nakai@gmail.com','$2a$10$r6qsJYwO9mUjXYpFF/xFyeCzFsI8yiKUZxKthsNUJRrXv.gZezPiG',1,1,'2017-06-04 22:07:35','2017-06-04 22:10:06','$2a$10$r6qsJYwO9mUjXYpFF/xFye');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER TR_USER_DATE_CREATED 
	    BEFORE INSERT ON User 
	    FOR EACH ROW 
	BEGIN
	    SET NEW.date_created = NOW();
	END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER TR_USER_DATE_UPDATE 
	    BEFORE UPDATE ON User 
	    FOR EACH ROW 
	BEGIN
	    SET NEW.date_updated = NOW();
	END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-04 22:21:08
