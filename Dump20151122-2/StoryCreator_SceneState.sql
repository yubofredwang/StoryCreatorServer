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
-- Table structure for table `SceneState`
--

DROP TABLE IF EXISTS `SceneState`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SceneState` (
  `ssID` int(11) NOT NULL,
  `ssName` varchar(45) NOT NULL,
  `ssDescription` longtext,
  `ssImagePath` longtext,
  `sceneChoiceAL` longtext NOT NULL,
  `gcChoiceAL` longtext NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  `w` int(11) NOT NULL,
  `h` int(11) NOT NULL,
  `sceneBelongTo` varchar(45) NOT NULL,
  PRIMARY KEY (`ssID`),
  UNIQUE KEY `ssID_UNIQUE` (`ssID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SceneState`
--

LOCK TABLES `SceneState` WRITE;
/*!40000 ALTER TABLE `SceneState` DISABLE KEYS */;
INSERT INTO `SceneState` VALUES (-1318618178,'ss5','h5',NULL,'','',0,0,0,0,'1830312078'),(-314760510,'ss2','h2',NULL,'','',0,0,0,0,'-977829814'),(-258065436,'state_test1','Default Description',NULL,'','',0,0,0,0,'-119408863'),(-189523913,'ss4','h4',NULL,'','',0,0,0,0,'1945086203'),(-162309083,'ss6','h6',NULL,'','',0,0,0,0,'-265798435'),(-132328853,'ss3','h3',NULL,'','',0,0,0,0,'1945086203'),(124919378,'ss1','h1',NULL,'','',0,0,0,0,'-977829814'),(211486753,'ss3','h3',NULL,'','',0,0,0,0,'5220430'),(368615845,'ss5','h5',NULL,'','',0,0,0,0,'-265798435'),(567705023,'scenestatename',NULL,NULL,'','',0,0,0,0,'-941573304'),(633591943,'ss2','h2',NULL,'','',0,0,0,0,'-811836775'),(886996422,'ss1','h1',NULL,'','',0,0,0,0,'-811836775'),(1021869664,'ss4','h4',NULL,'','',0,0,0,0,'5220430'),(1571653469,'ss6','h6',NULL,'','',0,0,0,0,'1830312078'),(1629957977,'state1','Default Description',NULL,'','',0,0,0,0,'-1449247075');
/*!40000 ALTER TABLE `SceneState` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-22 21:54:33
