-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: 182.74.154.218    Database: cse
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `staff_edu`
--

DROP TABLE IF EXISTS `staff_edu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff_edu` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `staffid` varchar(15) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `nod` varchar(45) DEFAULT NULL,
  `spl` varchar(200) DEFAULT NULL,
  `yop` varchar(10) DEFAULT NULL,
  `college` varchar(150) DEFAULT NULL,
  `university` varchar(150) DEFAULT NULL,
  `gpa` varchar(10) DEFAULT NULL,
  `pclass` varchar(45) DEFAULT NULL,
  `filename` varchar(45) DEFAULT NULL,
  `marksheet` longblob,
  PRIMARY KEY (`sno`),
  KEY `staff_id_idx` (`staffid`),
  CONSTRAINT `staff_id` FOREIGN KEY (`staffid`) REFERENCES `login`.`staff_login_details` (`staffid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-03 12:58:02
