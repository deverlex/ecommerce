-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: needy
-- ------------------------------------------------------
-- Server version	5.7.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Permissions`
--

DROP TABLE IF EXISTS `Permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Permissions` (
  `permission` varchar(32) PRIMARY KEY,
  `title` varchar(64) NOT NULL,
  `description` varchar(128),

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_permissions_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_permissions_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `PermissionRole`
--

DROP TABLE IF EXISTS `PermissionRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PermissionRole` (
  `id` smallint(5) AUTO_INCREMENT PRIMARY KEY,
  `permission` varchar(32) NOT NULL,
  `role` varchar(32) NOT NULL,

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_permission_role_pr` UNIQUE (`permission`,`role`), 
  CONSTRAINT `Fk_permission_role_p` FOREIGN KEY (`permission`) REFERENCES `Permissions` (`permission`),
  CONSTRAINT `Fk_permission_role_r` FOREIGN KEY (`role`) REFERENCES `Roles` (`role`), 
  CONSTRAINT `Fk_permission_role_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_permission_role_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `Roles`
--

DROP TABLE IF EXISTS `Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Roles` (
  `role` varchar(32) NOT NULL PRIMARY KEY,
  `title` varchar(64) NOT NULL,
  `description` varchar(128),

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_roles_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_roles_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserRole`
--

DROP TABLE IF EXISTS `UserRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRole` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `role` varchar(32) NOT NULL,
  `userId` bigint(20) NOT NULL,

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_user_role_r` FOREIGN KEY (`role`) REFERENCES `Roles` (`role`),
  CONSTRAINT `Fk_user_role_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_user_role_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_user_role_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- phone number k dc unique vi viec dang ky su dung lai sdt se bi can tro
  `username` varchar(16) NOT NULL,
  `password` varchar(255) NOT NULL,
  -- Trang thai tai khoan: khoa, con hoat dong...
  `state` tinyint(2) NOT NULL,

  -- Khoa 90 ngay
  `unlockTime` datetime,

  -- firebase UID
  `firebaseUid` varchar(32), 
  -- Khong yeu cau null de register tu app Vendor
  `fcmToken` varchar(225),

  `firstName` varchar(32) NOT NULL,
  `lastName` varchar(16) NOT NULL,
  
  `gender` varchar(20), 
  `address` varchar(128),
  `avatar` varchar(255),
  `coverPicture` varchar(255),

  `email` varchar(64),
  `birthday` date,
  `lat` float(10,6) NOT NULL,
  `lng` float(10,6) NOT NULL,

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastResetPassword` timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `state_idx` ON `Users` (`state`);
CREATE INDEX `username_idx` ON `Users` (`username`);
CREATE INDEX `loc_idx` ON `Users` (`lat`, `lng`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Notifications`
--

DROP TABLE IF EXISTS `Notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Notifications` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `recipientId` bigint(20) NOT NULL,
  `classReference` varchar(32) NOT NULL,
  `title` varchar(255) NOT NULL,
  `htmlContent` text(1000) NOT NULL, 
  `isRead` tinyint(1) NOT NULL DEFAULT 0,
  `isView` tinyint(0) NOT NULL DEFAULT 0,
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_notifications_ri` FOREIGN KEY (`recipientId`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Companies`
--

DROP TABLE IF EXISTS `Companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Companies` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- Ma code company tu dong gen
  -- CompanyCode:     1710VKG1FI9O
  `companyNumber` varchar(10) UNIQUE NOT NULL,
  -- It will update for app manager
  `fcmToken` varchar(255),
  -- Trang thai: chua kich hoat, da kich hoat, tam ngung, da dong cua
  `state` tinyint(2) NOT NULL,
  -- Xep cap bac
  `level` tinyint(3) NOT NULL,

  `name` varchar(128) NOT NULL,
  `numberEmployee` mediumint(7) NOT NULL,
  `officeAddress` varchar(128) NOT NULL,
  `foundedDate` date,

  `openingTime` time NOT NULL,
  `closingTime` time NOT NULL,
  -- {"url" : "", "host" : ""} -- Phan tan du lieu image
  `avatar` varchar(255),
  -- Mang cac url image [{ "url" : "", "host" : ""},...] max = 5 Image
  `pictures` text(1281),
  `description` text(1000),
  `website` varchar(120),

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,

  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_companies_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_companies_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `companyNumber_idx` ON `Companies` (`companyNumber`);
CREATE INDEX `state_idx` ON `Companies` (`state`);
CREATE INDEX `level_idx` ON `Companies` (`level`);
CREATE INDEX `activeTime_idx` ON `Companies` (`openingTime`, `closingTime`);

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CompanyReputation`
-- Chi co CEO moi dc thuc hien hanh dong yeu cau

DROP TABLE IF EXISTS `CompanyReputation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CompanyReputation` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- Chua xoa voi, doi doi tac chap thuan hoac xet duyet
  -- Trang thai: active, inactive, waiting
  `state` tinyint(2) NOT NULL,
  `companyId` bigint(20) NOT NULL,
  -- Ma hop dong tu sinh
  `agreementNumber` varchar(4) NOT NULL,

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `acceptedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_company_reputation_c` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`),
  CONSTRAINT `Fk_company_reputation_a` FOREIGN KEY (`acceptedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `state_idx` ON `CompanyReputation` (`state`);
CREATE INDEX `agreementNumber_idx` ON `CompanyReputation` (`agreementNumber`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserReport`
--

DROP TABLE IF EXISTS `UserReport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserReport` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `storeId` bigint(20) NOT NULL,

  `isAccepted` tinyint(1) DEFAULT 0,
  `description` text(1000) NOT NULL,
  `pictures` text(1281) NOT NULL,

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  `acceptedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_company_reputation_s` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`),
  CONSTRAINT `Fk_company_reputation_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_company_reputation_ac` FOREIGN KEY (`acceptedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Budgets`
--

DROP TABLE IF EXISTS `Budgets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Budgets` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `companyId` bigint(20) NOT NULL UNIQUE,
  -- Insert from code
  `budget` float(12,2) NOT NULL,
  
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_budgets_c` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PayLogs`
--

DROP TABLE IF EXISTS `PayLogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PayLogs` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `budgetId` bigint(20) NOT NULL,
  `behavior` tinyint(2) NOT NULL,
  -- So giao dich, tu sinh trong may
  `payNumber` varchar(8) NOT NULL,
  `budgetCharge` float(12, 2) NOT NULL,
  `description` text(500) NOT NULL,
  -- tai khoan thanh toan
  `debitAccount` varchar(32),
  -- tai khoan nhan
  `creditAccount` varchar(32),

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_pay_logs_b` FOREIGN KEY (`budgetId`) REFERENCES `Budgets` (`id`),
  CONSTRAINT `Fk_pay_logs_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `payNumber_idx` ON `PayLogs` (`payNumber`);
CREATE INDEX `debitAccount_idx` ON `PayLogs` (`debitAccount`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CompanyStaff`
--

DROP TABLE IF EXISTS `CompanyStaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CompanyStaff` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `userId` bigint(20) NOT NULL,
  `companyId` bigint(20) NOT NULL,
  -- Khi staff setup app and join system
  -- Firebase token will be update
  `storeId` bigint(20) NOT NULL,
  `fcmToken` varchar(255),
  -- Trang thai user - inactive, active, waiting
  -- Cho phep chu cua hang tu them sdt nhan vien cua minh vao he thong 
  -- Chi cho phep join thanh vien moi tu cach them tu he thong
  `state` tinyint(2) NOT NULL,

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,

  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_company_staff_uc` UNIQUE (`userId`,`companyId`),
  CONSTRAINT `Uniq_company_staff_us` UNIQUE (`userId`,`storeId`),
  CONSTRAINT `Fk_company_staff_s` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`),
  CONSTRAINT `Fk_company_staff_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_company_staff_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_company_staff_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `state_idx` ON `CompanyStaff` (`state`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Stores`
--

DROP TABLE IF EXISTS `Stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stores` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `companyId` bigint(20) NOT NULL,
  `storeNumber` varchar(5) NOT NULL,
  -- Trang thai cua hang da kich hoat hay chua
  -- Dieu kien kich hoat: khi co hang ban
  -- inactive (0), active(1), locked (-1)
  `state` tinyint(2) NOT NULL,
  -- Trang thai san sang working, busy, close time
  `status` tinyint(2) NOT NULL,
  -- Khoa den khi don hang chuyen trang thai
  -- Qua 3 lan 1 ngay, khoa 7 ngay, de mo khoa, nop tien 1.000.000
  `unlockTime` datetime,
  `numberStaff` smallint(5) NOT NULL,
  
  `name` varchar(64) NOT NULL,
  `address` varchar(128) NOT NULL,
  -- khuyen mai chung JSON - 10 k/m
  `promotion` text(1200),
  -- Gioi thieu cua hang
  `description` text(1000),
  `avatar` varchar(255),
  -- Mang cac url image ["url1", "url2"] max = 5 Image
  `pictures` text(1281),

  `lat` float(10,6) NOT NULL,
  `lng` float(10,6) NOT NULL,

  -- Thoi gian mo cua
  `openingTime` time NOT NULL,
  `closingTime` time NOT NULL,

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_stores_c` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`),
  CONSTRAINT `Fk_stores_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_stores_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `storeNumber_idx` ON `Stores` (`storeNumber`);
CREATE INDEX `status_idx` ON `Stores` (`status`);
CREATE INDEX `numberStaff_idx` ON `Stores` (`numberStaff`);
CREATE INDEX `loc_idx` ON `Stores` (`lat`, `lng`);
CREATE INDEX `activeTime_idx` ON `Stores` (`openingTime`, `closingTime`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `StoreBankAccount`
--

DROP TABLE IF EXISTS `StoreBankAccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StoreBankAccount` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `storeId` bigint(20) NOT NULL,
  `creditAccount` varchar(32) NOT NULL,
  -- Ten chu tai khoan
  `beneficiaryName` varchar(64) NOT NULL,
  -- Ten chi nhanh
  `beneficiaryBankName` varchar(128) NOT NULL,
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_store_bank_account_s` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`),
  CONSTRAINT `Fk_store_bank_account_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_store_bank_account_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `creditAccount_idx` ON `StoreBankAccount` (`storeId`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Categories`
--

DROP TABLE IF EXISTS `Categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Categories` (
  `category` varchar(16) PRIMARY KEY,
  `title` varchar(32) NOT NULL,
  `coverPicture` varchar(255) NOT NULL,
  `description` varchar(128) NOT NULL,
  `isService` tinyint(1) DEFAULT 0,

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_categories_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_categories_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Categories`
--

DROP TABLE IF EXISTS `SubCategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubCategories` (
  `subcategory` varchar(16) PRIMARY KEY,
  `category` varchar(16) NOT NULL,
  `title` varchar(32) NOT NULL,
  `coverPicture` varchar(255) NOT NULL,
  `description` varchar(128) NOT NULL,
  -- Phi thu cho moi don hang tren he thong
  `intermediaryFees` float(6, 2) NOT NULL,

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_subcategories_c` FOREIGN KEY (`category`) REFERENCES `Categories` (`category`),
  CONSTRAINT `Fk_subcategories_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_subcategories_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GroupAttribute`
-- Example: Platform contain: OS, Chipset, CPU, GPU attrs

DROP TABLE IF EXISTS `GroupAttribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GroupAttribute` (
  `groupAttribute` varchar(16) PRIMARY KEY,
  `title` varchar(32) NOT NULL,
  `description` varchar(128) NOT NULL,

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_group_attribute_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_group_attribute_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `Attributes`
--

DROP TABLE IF EXISTS `Attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Attributes` (
  `attribute` varchar(16) PRIMARY KEY,
  `subcategory` varchar(16) NOT NULL ,
  `groupAttribute`varchar(16) NOT NULL,
  `title` varchar(32) NOT NULL,
  `description` varchar(64) NOT NULL ,

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_attrs_sc` FOREIGN KEY (`subcategory`) REFERENCES `SubCategories` (`subcategory`),
  CONSTRAINT `Fk_attrs_ga` FOREIGN KEY (`groupAttribute`) REFERENCES `GroupAttribute` (`groupAttribute`),
  CONSTRAINT `Fk_attrs_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_attrs_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Products`
--

DROP TABLE IF EXISTS `Products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Products` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `productNumber` varchar(5) NOT NULL,
  `storeId` bigint(20) NOT NULL,
  `subcategory` varchar(16) NOT NULL,
  -- Trang thai con su dung khong, con su dung, da duoc kiem duyt hay chua
  `state` tinyint(1) DEFAULT 0,
  -- So luong con trong kho/thoi luong cho dich vu
  `quantity` smallint(5),

  `name` varchar(120) NOT NULL,
  -- Gia ban
  `price` float(12, 2) NOT NULL,
  -- Phi van chuyen cho moi san pham / 1km
  -- Phi di chuyen neu co - tu van tai nha
  `feeTransport` float(10, 2) NOT NULL,
  

  `image` varchar(255),
  -- Save JSON format - 5 image
  `pictures` text(1281),

  -- Khuyen mai JSON - 5 k/m
  `promotion` text(600),
  -- Mo ta san pham
  `description` text(1000),
  -- 
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  `lastUpdatedBy` bigint(20) NOT NULL,

  CONSTRAINT `Fk_products_s` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`),
  CONSTRAINT `Fk_products_sc` FOREIGN KEY (`subcategory`) REFERENCES `SubCategories` (`subcategory`),
  CONSTRAINT `Fk_products_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_products_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `productNumber_idx` ON `Products` (`productNumber`);
CREATE INDEX `quantity_idx` ON `Products` (`quantity`);
CREATE INDEX `price_idx` ON `Products` (`price`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProductHashTag`
--

DROP TABLE IF EXISTS `ProductHashTag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductHashTag` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `productId` bigint(20) NOT NULL,
  `hashtagId` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_product_hashtag_ph` UNIQUE (`productId`,`hashtagId`),
  CONSTRAINT `Fk_product_hashtag_p` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`),
  CONSTRAINT `Fk_product_hashtag_h` FOREIGN KEY (`hashtagId`) REFERENCES `HashTags` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `HashTags`
--
DROP TABLE IF EXISTS `HashTags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HashTags` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `hashtag` varchar(32) UNIQUE NOT NULL,
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ServiceProduct`
--
-- Bang chi ra service bao gom cac product nao, vi du: sua bep gas
-- San pham: Khoa van, bep gas, day gas, binh gas...
DROP TABLE IF EXISTS `ServiceProduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceProduct` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `serviceId` bigint(20) NOT NULL,
  `productId` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_service_product_sp` UNIQUE (`serviceId`, `productId`),
  CONSTRAINT `Fk_service_product_s` FOREIGN KEY (`serviceId`) REFERENCES `Products` (`id`),
  CONSTRAINT `Fk_service_product_p` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProductPriceLogs`
--

DROP TABLE IF EXISTS `ProductPriceLogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductPriceLogs` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `productId` bigint(20) NOT NULL,
  `oldPrice` float(12, 2) NOT NULL,

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_product_price_p` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`),
  CONSTRAINT `Fk_product_price_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `oldPrice_idx` ON `ProductPriceLogs` (`oldPrice`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProductAttribute`
--

DROP TABLE IF EXISTS `ProductAttribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductAttribute` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `productId` bigint(20) NOT NULL,
  `attribute` varchar(16) NOT NULL,
  -- 12, Do, Composite...
  `value` varchar(64) NOT NULL,
  CONSTRAINT `Fk_product_attr_a` FOREIGN KEY (`attribute`) REFERENCES `Attributes` (`attribute`),
  CONSTRAINT `Fk_product_attr_p` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `value_idx` ON `ProductAttribute` (`value`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Orders`
--
-- Cua hang hien thi theo order
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY ,
  `userId` bigint(20) NOT NULL,
  `storeId` bigint(20) NOT NULL,
  -- Ma don hang
  `orderNumber` varchar(6) NOT NULL,
  -- Da dat, dang nhan don hang...
  `status` tinyint(2) NOT NULL,
  -- Don hang co khan cap hay khong? = 1 neu su dung can ngay
  `isImminent` tinyint(1) DEFAULT 0,
  -- Co lay hoa don (VAT) hay khong
  `isGetReceipt` tinyint(1) DEFAULT 0,
  `paymentMethod` tinyint(2),
  -- Ghi chu cho ca don hang
  `note` text(180),

  `receiveFrom` datetime,
  `receiveTo` datetime,

  -- Phi van chuyen
  `feeTransport` float(10, 2) NOT NULL,

  `transportFrom` datetime,
  `transportTo` datetime,

  -- Danh cho goi ho
  `receiverId` bigint(20),

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_orders_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_orders_s` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`),
  CONSTRAINT `Fk_orders_r` FOREIGN KEY (`receiverId`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `orderNumber_idx` ON `Orders` (`orderNumber`);

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `OrderProduct`
--
-- User hien thi theo tung product
--

DROP TABLE IF EXISTS `OrderProduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderProduct` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `orderId` bigint(20) NOT NULL,
  `productId` bigint(20) NOT NULL,

  -- So luong mua
  `quantity` smallint(5) NOT NULL,
  -- Ghi chu cho don hang
  `note` text(180),
  
  CONSTRAINT `Fk_order_product_o` FOREIGN KEY (`orderId`) REFERENCES `Orders` (`id`),
  CONSTRAINT `Fk_order_product_p` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `OrderLogs`
--

DROP TABLE IF EXISTS `OrderLogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderLogs` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `orderId` bigint(20) NOT NULL,
  `companyStaffId` bigint(20) NOT NULL,
  `oldStatus` tinyint(2) NOT NULL,
  -- Ly do thay doi, huy don mua, tu choi don dat hang
  `description` text(500),

  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_order_logs_o` FOREIGN KEY (`orderId`) REFERENCES `Orders` (`id`),
  CONSTRAINT `Fk_order_logs_cs` FOREIGN KEY (`companyStaffId`) REFERENCES `CompanyStaff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `oldStatus_idx` ON `OrderLogs` (`oldStatus`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Store_Review`
--

DROP TABLE IF EXISTS `StoreReview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StoreReview` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `userId` bigint(20) NOT NULL,
  `storeId` bigint(20) NOT NULL,
  `rating` smallint(1),
  `review` text(500),
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_store_review_us` UNIQUE (`userId`,`storeId`),
  CONSTRAINT `Fk_store_review_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_store_review_s` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Reply_Store_Review`
--

DROP TABLE IF EXISTS `ReplyStoreReview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ReplyStoreReview` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `userId` bigint(20) NOT NULL,
  `storeReviewId` bigint(20) NOT NULL,
  `isLiked` tinyint(1),
  `reply` text(500),
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_review_store_review_usr` UNIQUE (`userId`,`storeReviewId`),
  CONSTRAINT `Fk_reply_store_review_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_reply_store_review_s` FOREIGN KEY (`storeReviewId`) REFERENCES `StoreReview` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `Product_Review`
--

DROP TABLE IF EXISTS `ProductReview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductReview` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `userId` bigint(20) NOT NULL,
  `productId` bigint(20) NOT NULL,
  `rating` smallint(1),
  `review` text(500),
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_product_review_up` UNIQUE (`userId`,`productId`),
  CONSTRAINT `Fk_product_review_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_product_review_p` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ReplyProductReview`
--

DROP TABLE IF EXISTS `ReplyProductReview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ReplyProductReview` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `userId` bigint(20) NOT NULL,
  `productReviewId` bigint(20) NOT NULL,
  `isLiked` tinyint(1),
  `reply` text(500),
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_reply_product_review_upr` UNIQUE (`userId`,`productReviewId`),
  CONSTRAINT `Fk_reply_product_review_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_reply_product_review_s` FOREIGN KEY (`productReviewId`) REFERENCES `ProductReview` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserReview`
--

DROP TABLE IF EXISTS `UserReview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserReview` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `sellerId` bigint(20) NOT NULL,
  `buyerId` bigint(20) NOT NULL,
  `rating` smallint(1),
  `review` text(500),
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_user_review_sb` UNIQUE (`sellerId`,`buyerId`),
  CONSTRAINT `Fk_user_review_s` FOREIGN KEY (`sellerId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_user_review_b` FOREIGN KEY (`buyerId`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ReplyUserReview`
--

DROP TABLE IF EXISTS `ReplyUserReview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ReplyUserReview` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `userId` bigint(20) NOT NULL,
  `userReviewId` bigint(20) NOT NULL,
  `isLiked` tinyint(1),
  `reply` text(500),
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_reply_user_review_uur` UNIQUE (`userId`,`userReviewId`),
  CONSTRAINT `Fk_reply_user_review_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_reply_user_review_ur` FOREIGN KEY (`userReviewId`) REFERENCES `UserReview` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-03 10:09:22
