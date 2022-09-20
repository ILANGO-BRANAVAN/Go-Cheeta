-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: cabservice
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `BranchId` int NOT NULL AUTO_INCREMENT,
  `BranchName` varchar(50) NOT NULL,
  PRIMARY KEY (`BranchId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'Jaffna'),(2,'Colombo');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver` (
  `DriverId` int NOT NULL AUTO_INCREMENT,
  `UserDetailId` int NOT NULL,
  `VehicleDetailId` int NOT NULL,
  `BranchId` int NOT NULL,
  PRIMARY KEY (`DriverId`),
  KEY `driver_userdetail_UserDetailId_fk` (`UserDetailId`),
  KEY `driver_branch_BranchId_fk` (`BranchId`),
  KEY `driver_vehicledetail_VehicleDetailid_fk` (`VehicleDetailId`),
  CONSTRAINT `driver_branch_BranchId_fk` FOREIGN KEY (`BranchId`) REFERENCES `branch` (`BranchId`),
  CONSTRAINT `driver_userdetail_UserDetailId_fk` FOREIGN KEY (`UserDetailId`) REFERENCES `userdetail` (`UserDetailId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `driver_vehicledetail_VehicleDetailid_fk` FOREIGN KEY (`VehicleDetailId`) REFERENCES `vehicledetail` (`VehicleDetailId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,3,3,1),(2,5,2,2);
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `FeedBackId` int NOT NULL AUTO_INCREMENT,
  `CustomerRating` int NOT NULL DEFAULT '0',
  `TripId` int NOT NULL,
  `UserDetailId` int NOT NULL,
  PRIMARY KEY (`FeedBackId`),
  KEY `FeedBack_trip_TripId_fk` (`TripId`),
  KEY `FeedBack_userdetail_UserDetailId_fk` (`UserDetailId`),
  CONSTRAINT `FeedBack_trip_TripId_fk` FOREIGN KEY (`TripId`) REFERENCES `trip` (`TripId`),
  CONSTRAINT `FeedBack_userdetail_UserDetailId_fk` FOREIGN KEY (`UserDetailId`) REFERENCES `userdetail` (`UserDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `LocationId` int NOT NULL AUTO_INCREMENT,
  `LocationName` varchar(50) NOT NULL,
  `StreetAddress` varchar(50) DEFAULT NULL,
  `City` varchar(50) NOT NULL,
  `BranchId` int NOT NULL,
  PRIMARY KEY (`LocationId`),
  KEY `Location_branch_BranchId_fk` (`BranchId`),
  CONSTRAINT `Location_branch_BranchId_fk` FOREIGN KEY (`BranchId`) REFERENCES `branch` (`BranchId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'Nallur','Nallur','Jaffna',1),(2,'Town','Town','Jaffna',1),(3,'Christian College','Christian College','Kopai',1),(4,'Junction','Junction','Kopai',1),(5,'Police Station','Police Station','Wellawatta',2),(6,'Market','Market','Wellawatta',2),(7,'Junction','Junction','Dehiwala',2),(8,'Station Rd','Station Rd','Dehiwala',2);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip` (
  `TripId` int NOT NULL AUTO_INCREMENT,
  `SourceLocationId` int NOT NULL,
  `DestinationLocationId` int DEFAULT NULL,
  `TravelDateTime` varchar(50) NOT NULL,
  `TripFare` decimal(12,2) NOT NULL,
  `TripStatus` varchar(20) DEFAULT NULL,
  `DriverId` int NOT NULL DEFAULT '0',
  `BranchId` int NOT NULL,
  `UserDetailid` int NOT NULL,
  PRIMARY KEY (`TripId`),
  KEY `trip_branch_BranchId_fk` (`BranchId`),
  KEY `trip_userdetail_UserDetailId_fk` (`UserDetailid`),
  CONSTRAINT `trip_branch_BranchId_fk` FOREIGN KEY (`BranchId`) REFERENCES `branch` (`BranchId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trip_userdetail_UserDetailId_fk` FOREIGN KEY (`UserDetailid`) REFERENCES `userdetail` (`UserDetailId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdetail`
--

DROP TABLE IF EXISTS `userdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userdetail` (
  `UserDetailId` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `EmailAddress` varchar(50) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `UserPassword` varchar(100) NOT NULL,
  `UserType` int NOT NULL,
  PRIMARY KEY (`UserDetailId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdetail`
--

LOCK TABLES `userdetail` WRITE;
/*!40000 ALTER TABLE `userdetail` DISABLE KEYS */;
INSERT INTO `userdetail` VALUES (1,'KK','Prem','admin@cab.com','Nallur Jaffna','0777728630','11223344',1),(2,'Siva','Kumar','customer@cab.com','Kacheri Jaffna','0777745678','11223344',2),(3,'Kulanthai','Ravi','driver@cab.com','Town Jaffna','0773445668','11223344',3),(4,'Test1','Test1','Test1@cab.com','Test1','Test1','Test1',3),(5,'Test2','Test2','Test2@cab.com','Test2','Test2','Test2',3);
/*!40000 ALTER TABLE `userdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiclecategory`
--

DROP TABLE IF EXISTS `vehiclecategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiclecategory` (
  `VehicleCategoryId` int NOT NULL AUTO_INCREMENT,
  `VehicleCategoryName` varchar(50) NOT NULL,
  PRIMARY KEY (`VehicleCategoryId`),
  UNIQUE KEY `VehicleCategory_VehicleCategoryName_uindex` (`VehicleCategoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiclecategory`
--

LOCK TABLES `vehiclecategory` WRITE;
/*!40000 ALTER TABLE `vehiclecategory` DISABLE KEYS */;
INSERT INTO `vehiclecategory` VALUES (3,'Luxury'),(1,'Mini'),(2,'Primary'),(5,'SampleCat');
/*!40000 ALTER TABLE `vehiclecategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicledetail`
--

DROP TABLE IF EXISTS `vehicledetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicledetail` (
  `VehicleDetailId` int NOT NULL AUTO_INCREMENT,
  `BrandName` varchar(50) NOT NULL,
  `ModelName` varchar(50) NOT NULL,
  `VehicleCategoryId` int NOT NULL,
  PRIMARY KEY (`VehicleDetailId`),
  KEY `vehicledetail_vehiclecategory_VehicleCategoryId_fk` (`VehicleCategoryId`),
  CONSTRAINT `vehicledetail_vehiclecategory_VehicleCategoryId_fk` FOREIGN KEY (`VehicleCategoryId`) REFERENCES `vehiclecategory` (`VehicleCategoryId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicledetail`
--

LOCK TABLES `vehicledetail` WRITE;
/*!40000 ALTER TABLE `vehicledetail` DISABLE KEYS */;
INSERT INTO `vehicledetail` VALUES (1,'Toyota','TR01',2),(2,'Toyota','LR',3),(3,'AutoG','AA01',1);
/*!40000 ALTER TABLE `vehicledetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cabservice'
--
/*!50003 DROP PROCEDURE IF EXISTS `insert_update_driver` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_update_driver`(IN pFirstName varchar(50), IN pLastName varchar(50),
                                                            IN pEmailAddress varchar(50), IN pAddress varchar(50),
                                                            IN pMobileNumber varchar(50), IN pUserPassword varchar(50),
                                                            IN pVehicleDetailId int, IN pBranchId int,
                                                            OUT rRes tinyint(1), OUT rMsg varchar(50))
BEGIN
    DECLARE lUserId INTEGER DEFAULT 0;
    SET rRes := true;
    SET rMsg := 'Success';

    insert into userdetail(FirstName, LastName, EmailAddress, Address, MobileNumber, UserPassword, UserType)
        value (pFirstName, pLastName, pEmailAddress, pAddress, pMobileNumber, pUserPassword, 3);

    SELECT LAST_INSERT_ID() into lUserId;
    #SELECT concat('lUserId----> ', lUserId);
    IF lUserId > 0 THEN
        insert into driver(UserDetailId, VehicleDetailId, BranchId) value (lUserId, pVehicleDetailId, pBranchId);
    ELSE
        SET rRes := false;
        SET rMsg := 'Failed to register the driver';
    END IF;
END ;;
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

-- Dump completed on 2022-08-16  9:35:55
