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
-- Table structure for table `GameContent`
--

DROP TABLE IF EXISTS `GameContent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GameContent` (
  `gcID` int(11) NOT NULL,
  `gcName` varchar(45) NOT NULL,
  `gcImagePath` longtext,
  `gcDescription` varchar(45) DEFAULT NULL,
  `gcPlayerBelongTo` int(11) DEFAULT NULL,
  `gcSceneBelongTo` int(11) DEFAULT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  `w` int(11) NOT NULL,
  `h` int(11) NOT NULL,
  PRIMARY KEY (`gcID`),
  UNIQUE KEY `gcID_UNIQUE` (`gcID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GameContent`
--

LOCK TABLES `GameContent` WRITE;
/*!40000 ALTER TABLE `GameContent` DISABLE KEYS */;
INSERT INTO `GameContent` VALUES (-1455730796,'gamecontent2','steven',NULL,-1,-977829814,0,0,0,0),(-760054747,'gamecontent1','jack',NULL,-1,-977829814,0,0,0,0),(-537425163,'gamecontent1',NULL,'jack',-1,-811836775,0,0,0,0),(829648910,'gamecontent3','zack',NULL,-1,-265798435,0,0,0,0),(994061872,'gamecontent2',NULL,'steven',-1,-811836775,0,0,0,0),(1231818316,'gamecontent3',NULL,'zack',-1,1830312078,0,0,0,0),(1648290660,'gamecontentname','description',NULL,-1,-941573304,0,0,0,0);
/*!40000 ALTER TABLE `GameContent` ENABLE KEYS */;
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
