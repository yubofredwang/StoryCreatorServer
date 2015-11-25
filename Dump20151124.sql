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
-- Table structure for table `GAMECONTENT`
--

DROP TABLE IF EXISTS `GAMECONTENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GAMECONTENT` (
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
-- Dumping data for table `GAMECONTENT`
--

LOCK TABLES `GAMECONTENT` WRITE;
/*!40000 ALTER TABLE `GAMECONTENT` DISABLE KEYS */;
INSERT INTO `GAMECONTENT` VALUES (-1455730796,'gamecontent2','steven',NULL,-1,-977829814,0,0,0,0),(-760054747,'gamecontent1','jack',NULL,-1,-977829814,0,0,0,0),(-537425163,'gamecontent1',NULL,'jack',-1,-811836775,0,0,0,0),(829648910,'gamecontent3','zack',NULL,-1,-265798435,0,0,0,0),(994061872,'gamecontent2',NULL,'steven',-1,-811836775,0,0,0,0),(1231818316,'gamecontent3',NULL,'zack',-1,1830312078,0,0,0,0),(1648290660,'gamecontentname','description',NULL,-1,-941573304,0,0,0,0);
/*!40000 ALTER TABLE `GAMECONTENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GAMEPROJECT`
--

DROP TABLE IF EXISTS `GAMEPROJECT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GAMEPROJECT` (
  `gpID` int(11) NOT NULL,
  `gpName` varchar(45) NOT NULL,
  `gpUserBelongTo` varchar(45) NOT NULL,
  PRIMARY KEY (`gpID`),
  UNIQUE KEY `gpID_UNIQUE` (`gpID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GAMEPROJECT`
--

LOCK TABLES `GAMEPROJECT` WRITE;
/*!40000 ALTER TABLE `GAMEPROJECT` DISABLE KEYS */;
INSERT INTO `GAMEPROJECT` VALUES (-1987378466,'new GP','thanakorn'),(-593114467,'gameproject1','thanakorn'),(68898180,'gameproject1','thanakorn'),(504425309,'jklj','owen'),(891896142,'gp_test1','hejie'),(1058464755,'gp_test2','hejie'),(1107839783,'new GP','thanakorn'),(1227728240,'newname3','thanakorn'),(1294609549,'new GP','thanakorn'),(1351509436,'new GP','thanakorn'),(1969551914,'new GP','thanakorn');
/*!40000 ALTER TABLE `GAMEPROJECT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GAMESETTING`
--

DROP TABLE IF EXISTS `GAMESETTING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GAMESETTING` (
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
-- Dumping data for table `GAMESETTING`
--

LOCK TABLES `GAMESETTING` WRITE;
/*!40000 ALTER TABLE `GAMESETTING` DISABLE KEYS */;
INSERT INTO `GAMESETTING` VALUES (504425309,'resources/templates/template2.png','resources/templates/template2_button.png','resources/templates/template2_button_pressed.png','resources/templates/MorrisRomanAlternate-Black.ttf',25,'WHITE',25,'YELLOW',-1,-1),(1058464755,'resources/templates/template1.png','resources/templates/template1_button.png','resources/templates/template1_button_pressed.png','resources/templates/Minecraft.ttf',25,'YELLOW',25,'YELLOW',-1,-1),(1227728240,'bgimagestring',NULL,NULL,'latestfont',25,'black',25,'black',-1,-1);
/*!40000 ALTER TABLE `GAMESETTING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PLAYER`
--

DROP TABLE IF EXISTS `PLAYER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PLAYER` (
  `pID` int(11) NOT NULL,
  `pName` varchar(45) NOT NULL,
  `pProjectBelongTo` varchar(45) NOT NULL,
  PRIMARY KEY (`pID`),
  UNIQUE KEY `pID_UNIQUE` (`pID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PLAYER`
--

LOCK TABLES `PLAYER` WRITE;
/*!40000 ALTER TABLE `PLAYER` DISABLE KEYS */;
INSERT INTO `PLAYER` VALUES (-1149007323,'player1','1227728240'),(733482877,'jlkjljlk','504425309');
/*!40000 ALTER TABLE `PLAYER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SCENE`
--

DROP TABLE IF EXISTS `SCENE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SCENE` (
  `sceneID` int(11) NOT NULL,
  `sceneName` varchar(45) NOT NULL,
  `currentSceneState` int(11) NOT NULL,
  `gpIDBelongTo` int(50) NOT NULL,
  PRIMARY KEY (`sceneID`),
  UNIQUE KEY `sceneID_UNIQUE` (`sceneID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SCENE`
--

LOCK TABLES `SCENE` WRITE;
/*!40000 ALTER TABLE `SCENE` DISABLE KEYS */;
INSERT INTO `SCENE` VALUES (-2083635173,'scene1',-1,-593114467),(-1449247075,'scene1',-1,891896142),(-977829814,'scene1',124919378,-593114467),(-941573304,'scenename',-1,1227728240),(-811836775,'scene1',886996422,68898180),(-265798435,'scene3',-1,-593114467),(-119408863,'scene_test1',-1,1058464755),(5220430,'scene2',-1,-593114467),(857664867,'jlkkjlk',-1,504425309),(1830312078,'scene3',-1,68898180),(1945086203,'scene2',-1,68898180);
/*!40000 ALTER TABLE `SCENE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SCENESTATE`
--

DROP TABLE IF EXISTS `SCENESTATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SCENESTATE` (
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
-- Dumping data for table `SCENESTATE`
--

LOCK TABLES `SCENESTATE` WRITE;
/*!40000 ALTER TABLE `SCENESTATE` DISABLE KEYS */;
INSERT INTO `SCENESTATE` VALUES (-1318618178,'ss5','h5',NULL,'','',0,0,0,0,'1830312078'),(-1124874050,',khlklkj','Default Description',NULL,'','',0,0,0,0,'857664867'),(-314760510,'ss2','h2',NULL,'','',0,0,0,0,'-977829814'),(-258065436,'state_test1','Default Description',NULL,'','',0,0,0,0,'-119408863'),(-189523913,'ss4','h4',NULL,'','',0,0,0,0,'1945086203'),(-162309083,'ss6','h6',NULL,'','',0,0,0,0,'-265798435'),(-132328853,'ss3','h3',NULL,'','',0,0,0,0,'1945086203'),(124919378,'ss1','h1',NULL,'','',0,0,0,0,'-977829814'),(211486753,'ss3','h3',NULL,'','',0,0,0,0,'5220430'),(368615845,'ss5','h5',NULL,'','',0,0,0,0,'-265798435'),(567705023,'scenestatename',NULL,NULL,'','',0,0,0,0,'-941573304'),(633591943,'ss2','h2',NULL,'','',0,0,0,0,'-811836775'),(886996422,'ss1','h1',NULL,'','',0,0,0,0,'-811836775'),(1021869664,'ss4','h4',NULL,'','',0,0,0,0,'5220430'),(1571653469,'ss6','h6',NULL,'','',0,0,0,0,'1830312078'),(1629957977,'state1','Default Description',NULL,'','',0,0,0,0,'-1449247075');
/*!40000 ALTER TABLE `SCENESTATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SCENESTATEPAIR`
--

DROP TABLE IF EXISTS `SCENESTATEPAIR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SCENESTATEPAIR` (
  `sspID` int(11) NOT NULL,
  `sceneID` int(11) NOT NULL,
  `stateID` int(11) NOT NULL,
  `SceneBelongTo` int(11) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  `w` int(11) NOT NULL,
  `h` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sspID`),
  UNIQUE KEY `sspID_UNIQUE` (`sspID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SCENESTATEPAIR`
--

LOCK TABLES `SCENESTATEPAIR` WRITE;
/*!40000 ALTER TABLE `SCENESTATEPAIR` DISABLE KEYS */;
INSERT INTO `SCENESTATEPAIR` VALUES (-1796428686,1945086203,-189523913,-811836775,0,0,0,0,'d2'),(-1473179873,345,123,-941573304,0,0,0,0,NULL),(-1203017109,5220430,1021869664,-977829814,0,0,0,0,'d2'),(-857361834,-977829814,-314760510,-265798435,0,0,0,0,'d6'),(-762739943,1830312078,1571653469,1945086203,0,0,0,0,'d4'),(-504145685,-265798435,368615845,5220430,0,0,0,0,'d3'),(184264015,5220430,211486753,-977829814,0,0,0,0,'d1'),(294622025,-265798435,-162309083,5220430,0,0,0,0,'d4'),(310184070,-977829814,124919378,-265798435,0,0,0,0,'d5'),(1301918951,1945086203,-132328853,-811836775,0,0,0,0,'d1'),(1337666397,-811836775,633591943,1830312078,0,0,0,0,'d6'),(1577186676,1830312078,-1318618178,1945086203,0,0,0,0,'d3'),(2056256434,-811836775,886996422,1830312078,0,0,0,0,'d5');
/*!40000 ALTER TABLE `SCENESTATEPAIR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES ('fred','wang'),('hejie','12345'),('owen','owen'),('thanakorn','newPassword'),('ton','pw'),('user2','mypassowrd'),('user3','mypassowrd');
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-24 17:28:41
