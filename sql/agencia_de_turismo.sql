-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: agencia_de_turismo
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `hoteles`
--

DROP TABLE IF EXISTS `hoteles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoteles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo_hotel` varchar(255) DEFAULT NULL,
  `disponible_desde` date DEFAULT NULL,
  `disponible_hasta` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio_por_noche` double DEFAULT NULL,
  `reservado` bit(1) NOT NULL,
  `tipo_habitacion` varchar(255) DEFAULT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoteles`
--

LOCK TABLES `hoteles` WRITE;
/*!40000 ALTER TABLE `hoteles` DISABLE KEYS */;
INSERT INTO `hoteles` VALUES (1,'AR-0002','2024-02-10','2024-03-20','Atlantis Resort',630,_binary '\0','Doble','Miami'),(2,'AR-0003','2024-02-10','2024-03-23','Atlantis Resort 2',820,_binary '\0','Triple','Miami'),(3,'RC-0001','2024-02-10','2024-03-19','Ritz-Carlton',543,_binary '\0','Single','Buenos Aires'),(4,'RC-0002','2024-02-12','2024-04-17','Ritz-Carlton 2',720,_binary '\0','Doble','Medellín'),(5,'GH-0002','2024-04-17','2024-05-23','Grand Hyatt',579,_binary '','Doble','Madrid'),(6,'GH-0001','2024-01-02','2024-02-19','Grand Hyatt 2',415,_binary '\0','Single','Buenos Aires'),(7,'HL-0001','2024-01-23','2024-11-23','Hilton',390,_binary '\0','Single','Barcelona'),(8,'HL-0002','2024-01-23','2024-10-15','Hilton 2',584,_binary '\0','Doble','Barcelona'),(9,'MT-0003','2024-02-15','2024-03-27','Marriott',702,_binary '\0','Doble','Barcelona'),(10,'SH-0004','2024-03-01','2024-04-17','Sheraton',860,_binary '\0','Múltiple','Madrid'),(11,'SH-0002','2024-02-10','2024-03-20','Sheraton 2',640,_binary '\0','Doble','Iguazú'),(12,'IR-0004','2024-04-17','2024-06-12','InterContinental',937,_binary '\0','Múltiple','Cartagena');
/*!40000 ALTER TABLE `hoteles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `huespedes`
--

DROP TABLE IF EXISTS `huespedes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `huespedes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `reserva_hotel_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKscwn2raeu1jvv7x7xb5hftycw` (`reserva_hotel_id`),
  CONSTRAINT `FKscwn2raeu1jvv7x7xb5hftycw` FOREIGN KEY (`reserva_hotel_id`) REFERENCES `reserva_hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `huespedes`
--

LOCK TABLES `huespedes` WRITE;
/*!40000 ALTER TABLE `huespedes` DISABLE KEYS */;
INSERT INTO `huespedes` VALUES (1,'Blanco',41,'Manuel',1),(2,'Miranda',41,'Rosa',1);
/*!40000 ALTER TABLE `huespedes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasajeros`
--

DROP TABLE IF EXISTS `pasajeros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pasajeros` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `reserva_vuelo_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe1qbdlybo7f605ixprusmnicg` (`reserva_vuelo_id`),
  CONSTRAINT `FKe1qbdlybo7f605ixprusmnicg` FOREIGN KEY (`reserva_vuelo_id`) REFERENCES `reserva_vuelos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasajeros`
--

LOCK TABLES `pasajeros` WRITE;
/*!40000 ALTER TABLE `pasajeros` DISABLE KEYS */;
INSERT INTO `pasajeros` VALUES (1,'Blanco',41,'Manuel',1),(2,'Miranda',41,'Rosa',1);
/*!40000 ALTER TABLE `pasajeros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva_hotel`
--

DROP TABLE IF EXISTS `reserva_hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva_hotel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidad_noches` int DEFAULT NULL,
  `codigo_hotel` varchar(255) DEFAULT NULL,
  `fecha_entrada` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `numero_huespedes` int DEFAULT NULL,
  `tipo_habitacion` varchar(255) DEFAULT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  `hotel_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoam720s6r79311jp5a79oehr` (`hotel_id`),
  CONSTRAINT `FKoam720s6r79311jp5a79oehr` FOREIGN KEY (`hotel_id`) REFERENCES `hoteles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_hotel`
--

LOCK TABLES `reserva_hotel` WRITE;
/*!40000 ALTER TABLE `reserva_hotel` DISABLE KEYS */;
INSERT INTO `reserva_hotel` VALUES (1,4,'AR-0002','2024-02-11','2024-02-15',2,'Doble','Miami',5);
/*!40000 ALTER TABLE `reserva_hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva_vuelos`
--

DROP TABLE IF EXISTS `reserva_vuelos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva_vuelos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `destino` varchar(255) DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `numero_personas` int DEFAULT NULL,
  `numero_vuelo` varchar(255) DEFAULT NULL,
  `origen` varchar(255) DEFAULT NULL,
  `tipo_asiento` varchar(255) DEFAULT NULL,
  `vuelo_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlvl909b5jy5dx4ooonkp5s6hg` (`vuelo_id`),
  CONSTRAINT `FKlvl909b5jy5dx4ooonkp5s6hg` FOREIGN KEY (`vuelo_id`) REFERENCES `vuelos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_vuelos`
--

LOCK TABLES `reserva_vuelos` WRITE;
/*!40000 ALTER TABLE `reserva_vuelos` DISABLE KEYS */;
INSERT INTO `reserva_vuelos` VALUES (1,'Cartagena','2024-01-23',2,'BOCA-4213','Bogotá','Economy',7);
/*!40000 ALTER TABLE `reserva_vuelos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vuelos`
--

DROP TABLE IF EXISTS `vuelos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vuelos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `destino` varchar(255) DEFAULT NULL,
  `fecha_llegada` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `numero_vuelo` varchar(255) DEFAULT NULL,
  `origen` varchar(255) DEFAULT NULL,
  `precio_por_persona` double DEFAULT NULL,
  `tipo_asiento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vuelos`
--

LOCK TABLES `vuelos` WRITE;
/*!40000 ALTER TABLE `vuelos` DISABLE KEYS */;
INSERT INTO `vuelos` VALUES (1,'Miami','2024-02-15','2024-02-10','BAMI-1235','Barcelona',650,'Economy'),(2,'Madrid','2024-02-20','2024-02-10','MIMA1420','Miami',4320,'Business'),(3,'Madrid','2024-02-21','2024-02-10','MIMA-1420','Miami',2573,'Economy'),(4,'Buenos Aires','2024-02-17','2024-02-10','BABU-5536','Barcelona',732,'Economy'),(5,'Barcelona','2024-02-23','2024-02-12','BUBA-3369','Buenos Aires',1253,'Business'),(6,'Barcelona','2024-01-16','2024-01-02','IGBA-3369','Iguazú',540,'Economy'),(7,'Cartagena','2024-02-05','2024-01-23','BOCA-4213','Bogotá',800,'Economy'),(8,'Medellín','2024-01-31','2024-01-23','CAME-0321','Cartagena',780,'Economy'),(9,'Iguazú','2024-02-28','2024-02-15','BOIG-6567','Bogotá',570,'Business'),(10,'Buenos Aires','2024-03-14','2024-03-01','BOBA-6567','Bogotá',398,'Economy'),(11,'Madrid','2024-02-24','2024-02-10','BOMA-4442','Bogotá',1100,'Economy'),(12,'Miami','2024-05-02','2024-04-17','MEMI-9986','Medellín',1164,'Business');
/*!40000 ALTER TABLE `vuelos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-19  2:28:15
