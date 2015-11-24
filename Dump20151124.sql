-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: storycreator
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
-- Table structure for table `gamecontent`
--

DROP TABLE IF EXISTS `gamecontent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gamecontent` (
  `gcID` int(11) NOT NULL,
  `gcName` varchar(45) NOT NULL,
  `gcImagePath` varchar(45) DEFAULT NULL,
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
-- Dumping data for table `gamecontent`
--

LOCK TABLES `gamecontent` WRITE;
/*!40000 ALTER TABLE `gamecontent` DISABLE KEYS */;
INSERT INTO `gamecontent` VALUES (-1455730796,'gamecontent2','steven',NULL,-1,-977829814,0,0,0,0),(-760054747,'gamecontent1','jack',NULL,-1,-977829814,0,0,0,0),(-537425163,'gamecontent1',NULL,'jack',-1,-811836775,0,0,0,0),(829648910,'gamecontent3','zack',NULL,-1,-265798435,0,0,0,0),(994061872,'gamecontent2',NULL,'steven',-1,-811836775,0,0,0,0),(1231818316,'gamecontent3',NULL,'zack',-1,1830312078,0,0,0,0),(1648290660,'gamecontentname','description',NULL,-1,-941573304,0,0,0,0);
/*!40000 ALTER TABLE `gamecontent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gameproject`
--

DROP TABLE IF EXISTS `gameproject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gameproject` (
  `gpID` int(11) NOT NULL,
  `gpName` varchar(45) NOT NULL,
  `gpUserBelongTo` varchar(45) NOT NULL,
  PRIMARY KEY (`gpID`),
  UNIQUE KEY `gpID_UNIQUE` (`gpID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gameproject`
--

LOCK TABLES `gameproject` WRITE;
/*!40000 ALTER TABLE `gameproject` DISABLE KEYS */;
INSERT INTO `gameproject` VALUES (-1987378466,'new GP','thanakorn'),(-1964837638,'dadiaoyihao','gaodadiao'),(-593114467,'gameproject1','thanakorn'),(-393346849,'kjida','liudadiao'),(68898180,'gameproject1','thanakorn'),(868527878,'nb ','shizheman'),(1107839783,'new GP','thanakorn'),(1227728240,'newname3','thanakorn'),(1294609549,'new GP','thanakorn'),(1351509436,'new GP','thanakorn'),(1969551914,'new GP','thanakorn');
/*!40000 ALTER TABLE `gameproject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gamesetting`
--

DROP TABLE IF EXISTS `gamesetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gamesetting` (
  `gpIDBelongTo` int(11) NOT NULL,
  `bgimageString` longtext,
  `releaseButtonimageString` longtext,
  `pressedButtonimageString` longtext,
  `fontString` longtext,
  `textFontSize` int(11) DEFAULT NULL,
  `textFontColor` longtext,
  `buttonFontSize` int(11) DEFAULT NULL,
  `buttonFontColor` varchar(45) DEFAULT NULL,
  `initialScene` int(11) DEFAULT NULL,
  `initialState` int(11) DEFAULT NULL,
  PRIMARY KEY (`gpIDBelongTo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gamesetting`
--

LOCK TABLES `gamesetting` WRITE;
/*!40000 ALTER TABLE `gamesetting` DISABLE KEYS */;
INSERT INTO `gamesetting` VALUES (-393346849,'resources/templates/template1.png','resources/templates/template1_button.png','resources/templates/template1_button_pressed.png','resources/templates/Minecraft.ttf',25,'YELLOW',25,'YELLOW',-1,-1),(868527878,'resources/templates/template1.png','resources/templates/template1_button.png','resources/templates/template1_button_pressed.png','resources/templates/Minecraft.ttf',25,'YELLOW',25,'YELLOW',-1,-1),(1227728240,'bgimagestring',NULL,NULL,'latestfont',25,'black',25,'black',-1,-1);
/*!40000 ALTER TABLE `gamesetting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `pID` int(11) NOT NULL,
  `pName` varchar(45) NOT NULL,
  `pProjectBelongTo` varchar(45) NOT NULL,
  PRIMARY KEY (`pID`),
  UNIQUE KEY `pID_UNIQUE` (`pID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (-1149007323,'player1','1227728240');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scene`
--

DROP TABLE IF EXISTS `scene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scene` (
  `sceneID` int(11) NOT NULL,
  `sceneName` varchar(45) NOT NULL,
  `currentSceneState` int(11) NOT NULL,
  `gpIDBelongTo` int(50) NOT NULL,
  PRIMARY KEY (`sceneID`),
  UNIQUE KEY `sceneID_UNIQUE` (`sceneID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scene`
--

LOCK TABLES `scene` WRITE;
/*!40000 ALTER TABLE `scene` DISABLE KEYS */;
INSERT INTO `scene` VALUES (-2083635173,'scene1',-1,-593114467),(-977829814,'scene1',124919378,-593114467),(-941573304,'scenename',-1,1227728240),(-811836775,'scene1',886996422,68898180),(-265798435,'scene3',-1,-593114467),(5220430,'scene2',-1,-593114467),(557031451,'jibabab',-1,-393346849),(595670844,'jbutton',-2029821512,-1964837638),(1830312078,'scene3',-1,68898180),(1945086203,'scene2',-1,68898180),(2036246282,'jbjkb',744431319,868527878);
/*!40000 ALTER TABLE `scene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scenestate`
--

DROP TABLE IF EXISTS `scenestate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scenestate` (
  `ssID` int(11) NOT NULL,
  `ssName` varchar(45) NOT NULL,
  `ssDescription` varchar(45) DEFAULT NULL,
  `ssImagePath` varchar(45) DEFAULT NULL,
  `sceneChoiceAL` varchar(50) NOT NULL,
  `gcChoiceAL` varchar(45) NOT NULL,
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
-- Dumping data for table `scenestate`
--

LOCK TABLES `scenestate` WRITE;
/*!40000 ALTER TABLE `scenestate` DISABLE KEYS */;
INSERT INTO `scenestate` VALUES (-2110093913,'dadiaoduanle','Default Description',NULL,'1470426079,','1470426079,',0,0,0,0,'595670844'),(-2029821512,'sal;kd;aslkd;laskd','Default Description',NULL,'','',0,0,0,0,'595670844'),(-1318618178,'ss5','h5',NULL,'','',0,0,0,0,'1830312078'),(-314760510,'ss2','h2',NULL,'','',0,0,0,0,'-977829814'),(-189523913,'ss4','h4',NULL,'','',0,0,0,0,'1945086203'),(-162309083,'ss6','h6',NULL,'','',0,0,0,0,'-265798435'),(-132328853,'ss3','h3',NULL,'','',0,0,0,0,'1945086203'),(124919378,'ss1','h1',NULL,'','',0,0,0,0,'-977829814'),(211486753,'ss3','h3',NULL,'','',0,0,0,0,'5220430'),(368615845,'ss5','h5',NULL,'','',0,0,0,0,'-265798435'),(567705023,'scenestatename',NULL,NULL,'','',0,0,0,0,'-941573304'),(633591943,'ss2','h2',NULL,'','',0,0,0,0,'-811836775'),(744431319,'bn','Default Description',NULL,'','',0,0,0,0,'2036246282'),(886996422,'ss1','h1',NULL,'','',0,0,0,0,'-811836775'),(1021869664,'ss4','h4',NULL,'','',0,0,0,0,'5220430'),(1571653469,'ss6','h6',NULL,'','',0,0,0,0,'1830312078');
/*!40000 ALTER TABLE `scenestate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scenestatepair`
--

DROP TABLE IF EXISTS `scenestatepair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scenestatepair` (
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
-- Dumping data for table `scenestatepair`
--

LOCK TABLES `scenestatepair` WRITE;
/*!40000 ALTER TABLE `scenestatepair` DISABLE KEYS */;
INSERT INTO `scenestatepair` VALUES (-1796428686,1945086203,-189523913,-811836775,0,0,0,0,'d2'),(-1473179873,345,123,-941573304,0,0,0,0,NULL),(-1203017109,5220430,1021869664,-977829814,0,0,0,0,'d2'),(-857361834,-977829814,-314760510,-265798435,0,0,0,0,'d6'),(-762739943,1830312078,1571653469,1945086203,0,0,0,0,'d4'),(-504145685,-265798435,368615845,5220430,0,0,0,0,'d3'),(184264015,5220430,211486753,-977829814,0,0,0,0,'d1'),(294622025,-265798435,-162309083,5220430,0,0,0,0,'d4'),(310184070,-977829814,124919378,-265798435,0,0,0,0,'d5'),(1301918951,1945086203,-132328853,-811836775,0,0,0,0,'d1'),(1337666397,-811836775,633591943,1830312078,0,0,0,0,'d6'),(1470426079,595670844,-2110093913,595670844,259,73,345,73,'gaodadiaodedadiaoduanle'),(1577186676,1830312078,-1318618178,1945086203,0,0,0,0,'d3'),(2056256434,-811836775,886996422,1830312078,0,0,0,0,'d5');
/*!40000 ALTER TABLE `scenestatepair` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('fred','wang'),('gaodadiao','bigdick'),('jingbao','123'),('jingbaobao','youyiyi'),('liudadiao','bigdick'),('shizheman','wohenchou'),('thanakorn','newPassword'),('ton','pw'),('user','kk'),('user2','mypassowrd'),('yubowang','1234'),('yubowang333','333');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-24  9:23:45
