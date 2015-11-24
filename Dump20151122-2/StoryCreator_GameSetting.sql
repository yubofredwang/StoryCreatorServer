-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: StoryCreator
-- ------------------------------------------------------
-- Server version	5.6.27

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
-- Table structure for table `GameSetting`
--

DROP TABLE IF EXISTS `GameSetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GameSetting` (
  `gpIDBelongTo` int(11) NOT NULL,
  `bgimageString` longtext,
  `releaseButtonimageString` longtext,
  `pressedButtonimageString` longtext,
  `fontString` longtext,
  `textFontSize` int(11) DEFAULT NULL,
  `textFontColor` varchar(45) DEFAULT NULL,
  `buttonFontSize` int(11) DEFAULT NULL,
  `buttonFontColor` varchar(45) DEFAULT NULL,
  `initialScene` int(11) DEFAULT NULL,
  `initialState` int(11) DEFAULT NULL,
  PRIMARY KEY (`gpIDBelongTo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GameSetting`
--

LOCK TABLES `GameSetting` WRITE;
/*!40000 ALTER TABLE `GameSetting` DISABLE KEYS */;
INSERT INTO `GameSetting` VALUES (1058464755,'resources/templates/template1.png','resources/templates/template1_button.png','resources/templates/template1_button_pressed.png','resources/templates/Minecraft.ttf',25,'YELLOW',25,'YELLOW',-1,-1),(1227728240,'bgimagestring',NULL,NULL,'latestfont',25,'black',25,'black',-1,-1);
/*!40000 ALTER TABLE `GameSetting` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-22 21:54:32
