-- MySQL dump 10.13  Distrib 5.7.10, for Win64 (x86_64)
--
-- Host: localhost    Database: db_appflix
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `tbl_producao`
--

DROP TABLE IF EXISTS `tbl_producao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_producao` (
  `id_producao` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `sinopse` text NOT NULL,
  `imagem` varchar(200) NOT NULL,
  `nota` float DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_producao`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_producao`
--

LOCK TABLES `tbl_producao` WRITE;
/*!40000 ALTER TABLE `tbl_producao` DISABLE KEYS */;
INSERT INTO `tbl_producao` VALUES (8,'O Silêncio dos Inocentes','Cinco mulheres são brutalmente assassinadas em diferentes localidades dos Estados Unidos. Para chegar até o sanguinário assassino, a jovem agente do FBI, Clarice Starling, entrevista o ardiloso psiquiatra Hannibal Lecter, cuja mente psicopata está perigosamente voltada para o crime. Ao seguir as pistas apontadas pelo Dr. Lecter, Clarice envolve-se em uma teia mortífera surpreendente. O texto de Thomas Harris é arrepiante. Em 1991, a adaptação para o cinema de O silêncio dos inocentes com Anthony Hopkins e Jodie Foster nos papéis principais rendeu ao filme cinco estatuetas do Oscar.','5b0d979562600.jpg',5,'https://www.netflix.com/de-en/title/14546747'),(9,'O Iluminado','Jack Torrence consegue um emprego de zelador em um velho hotel, e acha que será a solução dos problemas de sua família: não vão mais passar por dificuldades, sua esposa não vai mais sofrer e seu filho, Danny, vai poder ter ar puro para se livrar de estranhas convulsões. Mas as coisas não são tão perfeitas como parecem: existem forças malignas rondando os antigos corredores. O hotel é uma chaga aberta de ressentimento e desejo de vingança, e, inevitavelmente, um embate entre o bem e o mal terá de ser travado.','5b0d98758e7ca.jpg',5,'https://www.netflix.com/title/959008'),(10,'O Poderoso Chefão Parte II','Após a máfia matar sua família, o jovem Vito foge da sua cidade na Sicília e vai para a América. Vito luta para manter sua família. Ele mata Black Hand Fanucci, que exigia dos comerciantes uma parte dos seus ganhos. Com a morte de Fanucci, o poderio de Vito cresce, mas sua família é o que mais importa para ele. Agora baseado no Lago Tahoe, Michael planeja fazer incursões em Las Vegas e Havana instalando negócios ligados ao lazer, mas descobre que aliados como Hyman Roth estão tentando matá-lo.','5b0d99564ecd5.jpg',5,'https://www.netflix.com/br/title/60011663'),(11,'O Poderoso Chefão Parte III','Don Michel Corleone está envelhecendo e, com a ajuda do sobrinho Vicente Mancini, busca a legitimação dos interesses da família, em Nova York e na Itália, antes de sua morte. Mas seu protegido não está só interessado em parte do império da família. Ele também deseja a filha de Michael, Mary.','5b0d99d118dd2.jpg',4,'https://www.netflix.com/br/title/60011153');
/*!40000 ALTER TABLE `tbl_producao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-29 16:32:33
