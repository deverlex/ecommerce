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
  `enable` tinyint(1) DEFAULT 1,
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
  `id` smallint(5) unsigned AUTO_INCREMENT PRIMARY KEY,
  `permission` varchar(32) NOT NULL,
  `role` varchar(32) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_permission_role_pr` UNIQUE (`permission`,`role`), 
  CONSTRAINT `Fk_permission_role_p` FOREIGN KEY (`permission`) REFERENCES `Permissions` (`permission`),
  CONSTRAINT `Fk_permission_role_r` FOREIGN KEY (`role`) REFERENCES `Roles` (`role`), 
  CONSTRAINT `Fk_permission_role_cr` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
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
  `enable` tinyint(1) DEFAULT 1,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
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
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_user_role_ru` UNIQUE (`role`,`userId`), 
  CONSTRAINT `Fk_user_role_r` FOREIGN KEY (`role`) REFERENCES `Roles` (`role`),
  CONSTRAINT `Fk_user_role_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_user_role_cr` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
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
  `username` varchar(15) NOT NULL,
  `password` varchar(255) NOT NULL,
  -- Trang thai tai khoan: khoa, con hoat dong...
  `state` tinyint(2) NOT NULL,
  -- Khoa 90 ngay
  `unlockTime` datetime,
  -- firebase UID
  `firebaseUid` varchar(64), 
  -- Khong yeu cau null de register tu app Vendor
  `fcmToken` varchar(225),
  `fullName` varchar(128),
  `gender` varchar(20), 
  `address` varchar(128),
  `avatar` varchar(255),
  `coverPicture` varchar(255),
  `email` varchar(64),
  `birthday` date,
  `lat` float(10,6),
  `lng` float(10,6),
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastResetPassword` timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `state_idx` ON `Users` (`state`);
CREATE INDEX `username_idx` ON `Users` (`username`);
CREATE INDEX `loc_idx` ON `Users` (`lat`, `lng`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Wallet`
--
DROP TABLE IF EXISTS `Wallets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Wallets` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `userId` bigint(20) NOT NULL,
  `budget` smallint(5) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_wallets_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `WalletPays`
--
DROP TABLE IF EXISTS `WalletPays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WalletPays` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `walletId` bigint(20) NOT NULL,
  `productId` bigint(20) NOT NULL,
  `budgetCharge` smallint(5) NOT NULL,
  `description` varchar(255),
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_wallet_pays_w` FOREIGN KEY (`walletId`) REFERENCES `Wallets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `NotificationUser`
--
DROP TABLE IF EXISTS `NotificationUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NotificationUser` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- Id notification
  `notificationId` bigint(20) NOT NULL,
  -- Nguoi nhan notification
  `receverId` bigint(20) NOT NULL,
  `isRead` tinyint(1) NOT NULL DEFAULT 0,
  `isView` tinyint(1) NOT NULL DEFAULT 0,
  CONSTRAINT `Fk_notify_user_r` FOREIGN KEY (`receverId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_notify_user_n` FOREIGN KEY (`notificationId`) REFERENCES `Notifications` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Notifications`
--
DROP TABLE IF EXISTS `Notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Notifications` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- nguoi gui
  `senderId` bigint(20) NOT NULL,
  -- Will open activity on Client (Android) if it available
  `refEntity` varchar(32),
  -- Id of object - examples: orderId, productId...
  `refId` bigint(20),
  -- action, examples: open an edit product...
  `refMethod` varchar(32),
  `title` varchar(255) NOT NULL,
  `htmlContent` text(1000) NOT NULL,
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_notifications_s` FOREIGN KEY (`senderId`) REFERENCES `Users` (`id`)
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
  `companyNumber` varchar(12) UNIQUE NOT NULL,
  -- It will update for app manager
  `fcmToken` varchar(255),
  -- Trang thai: chua kich hoat, da kich hoat, tam ngung, da dong cua
  `state` tinyint(2) NOT NULL,
  -- Xep cap bac
  `level` tinyint(3) NOT NULL,
  `name` varchar(128) NOT NULL,
  `officeAddress` varchar(255) NOT NULL,
  `foundedDate` date,
  `openingTime` time NOT NULL,
  `closingTime` time NOT NULL,
  -- {"url" : "", "host" : ""} -- Phan tan du lieu image
  `avatar` varchar(255),
  -- Mang cac url image [{ "url" : "", "host" : ""},...] max = 5 Image
  `pictures` text(1500),
  `description` text(1500),
  `siteUrl` varchar(255),
  -- Gia tri don hang tu ? - mien phi van chuyen
  `limitFreeTransport` float(8, 2),
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
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
  `agreementNumber` varchar(12) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `changedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_company_reputation_c` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`),
  CONSTRAINT `Fk_company_reputation_chg` FOREIGN KEY (`changedBy`) REFERENCES `Users` (`id`)
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
  -- tu dong sinh ra
  `budgetNumber` varchar(12) NOT NULL UNIQUE,
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
DROP TABLE IF EXISTS `Pays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pays` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `budgetId` bigint(20) NOT NULL,
  `behavior` tinyint(2) NOT NULL,
  -- So giao dich, tu sinh trong may
  `payNumber` varchar(12) NOT NULL,
  `budgetCharge` float(12, 2) NOT NULL,
  `description` text(500) NOT NULL,
  -- tai khoan thanh toan
  `debitAccount` varchar(32),
  -- tai khoan nhan
  `creditAccount` varchar(32),
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_pays_b` FOREIGN KEY (`budgetId`) REFERENCES `Budgets` (`id`),
  CONSTRAINT `Fk_pays_cr` FOREIGN KEY (`createdBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `payNumber_idx` ON `Pays` (`payNumber`);
CREATE INDEX `debitAccount_idx` ON `Pays` (`debitAccount`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CompanyStaff`
--
DROP TABLE IF EXISTS `CompanyStaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CompanyStaff` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- Moi sdt dang ky duy nhat 1 company
  `userId` bigint(20) NOT NULL UNIQUE,
  `companyId` bigint(20) NOT NULL,
  -- Khi staff setup app and join system
  -- Firebase token will be update
  `storeId` bigint(20) NOT NULL,
  `fcmToken` varchar(255),
  -- Trang thai user - inactive, active, waiting
  -- Cho phep chu cua hang tu them sdt nhan vien cua minh vao he thong 
  -- Chi cho phep join thanh vien moi tu cach them tu he thong
  `state` tinyint(2) NOT NULL,
  -- ready, busy
  `status` tinyint(2) NOT NULL,
  -- Thoi gian gia nhap cua hang
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_company_staff_uc` UNIQUE (`userId`,`companyId`),
  CONSTRAINT `Uniq_company_staff_us` UNIQUE (`userId`,`storeId`),
  CONSTRAINT `Fk_company_staff_s` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`),
  CONSTRAINT `Fk_company_staff_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
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
  `storeNumber` varchar(10) NOT NULL,
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
  `name` varchar(128) NOT NULL,
  `address` varchar(255) NOT NULL,
  -- Gioi thieu cua hang
  `description` text(1500),
  `avatar` varchar(255),
  -- Mang cac url image ["url1", "url2"] max = 5 Image
  `pictures` text(1500),
  `lat` float(10,6) NOT NULL,
  `lng` float(10,6) NOT NULL,
  -- Thoi gian mo cua
  `openingTime` time NOT NULL,
  `closingTime` time NOT NULL,
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_stores_c` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`),
  CONSTRAINT `Fk_stores_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `storeNumber_idx` ON `Stores` (`storeNumber`);
CREATE INDEX `status_idx` ON `Stores` (`status`);
CREATE INDEX `numberStaff_idx` ON `Stores` (`numberStaff`);
CREATE INDEX `loc_idx` ON `Stores` (`lat`, `lng`);
CREATE INDEX `activeTime_idx` ON `Stores` (`openingTime`, `closingTime`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Categories`
--
DROP TABLE IF EXISTS `Categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Categories` (
  `category` varchar(32) PRIMARY KEY,
  `coverPicture` varchar(255) NOT NULL,
  `isPriceLater` tinyint(1) DEFAULT 0,
  `enable` tinyint(1) DEFAULT 1,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_categories_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SubCategories`
--
DROP TABLE IF EXISTS `SubCategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubCategories` (
  `id` smallint(5) unsigned AUTO_INCREMENT PRIMARY KEY,
  `subCategory` varchar(32) NOT NULL,
  `refCategory` varchar(32) NOT NULL,
  `refLevel` tinyint(3) NOT NULL,
  `isNext` tinyint(1) DEFAULT 1,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_subcategories_sc` FOREIGN KEY (`subCategory`) REFERENCES `Categories` (`category`),
  CONSTRAINT `Fk_subcategories_rf` FOREIGN KEY (`refCategory`) REFERENCES `Categories` (`category`),
  CONSTRAINT `Fk_subcategories_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Attributes`
--
DROP TABLE IF EXISTS `Attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Attributes` (
  `attribute` varchar(32) PRIMARY KEY,
  `enable` tinyint(1) DEFAULT 1,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_attrs_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CategoryAttribute`
--
DROP TABLE IF EXISTS `CategoryAttribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CategoryAttribute` (
  `id` int(10) unsigned AUTO_INCREMENT PRIMARY KEY,
  `attribute` varchar(32) NOT NULL,
  `category` varchar(32) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_categoryattr_ct` FOREIGN KEY (`category`) REFERENCES `Categories` (`category`),
  CONSTRAINT `Fk_categoryattr_at` FOREIGN KEY (`attribute`) REFERENCES `Attributes` (`attribute`),
  CONSTRAINT `Fk_categoryattr_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
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
  `productNumber` varchar(12) NOT NULL,
  `category` varchar(32) NOT NULL,
  -- Trang thai con su dung khong, con su dung, da duoc kiem duyt hay chua
  -- DELETED (-2), DENIED (-1), INACTIVE (0), ACTIVE (1)
  `state` tinyint(2),
  `name` varchar(255) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_products_cg` FOREIGN KEY (`category`) REFERENCES `Categories` (`category`),
  CONSTRAINT `Fk_products_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `productNumber_idx` ON `Products` (`productNumber`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ServiceProduct`
-- Bang chi ra service bao gom cac product nao, vi du: sua bep gas
-- San pham: Khoa van, bep gas, day gas, binh gas...
DROP TABLE IF EXISTS `SubProduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubProduct` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `subProductId` bigint(20) NOT NULL,
  `productId` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_sub_product_sp` UNIQUE (`subProductId`, `productId`),
  CONSTRAINT `Fk_sub_product_s` FOREIGN KEY (`subProductId`) REFERENCES `Products` (`id`),
  CONSTRAINT `Fk_sub_product_p` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProductCompany`
--
DROP TABLE IF EXISTS `ProductCompany`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductCompany` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `productId` bigint(20) NOT NULL,
  `companyId` bigint(20) NOT NULL,
  -- So luong con trong kho (Dich vu thi k can bang nay)
  `quantity` smallint(5) NOT NULL,
  -- Gia ban
  `price` float(12, 2) NOT NULL,
  `prePrice` float(12, 2) NOT NULL,
  -- Khuyen mai JSON - 5 k/m
  `promotion` text(1500),
  -- Mo ta san pham
  `description` text(1500),
  `image` varchar(255),
  -- Save JSON format - 5 image
  `pictures` text(1500),
  -- Gia van chuyen
  `feeTransport` float(8, 2),
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_product_store_ps` UNIQUE (`productId`, `companyId`),
  CONSTRAINT `Fk_product_company_p` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`),
  CONSTRAINT `Fk_product_company_s` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`),
  CONSTRAINT `Fk_product_company_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `quantity_idx` ON `ProductCompany` (`quantity`);
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
  `attribute` varchar(32) NOT NULL,
  -- 12, Do, Composite...
  `value` varchar(128) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_product_attr_pa` UNIQUE (`productId`, `attribute`),
  CONSTRAINT `Fk_product_attr_a` FOREIGN KEY (`attribute`) REFERENCES `Attributes` (`attribute`),
  CONSTRAINT `Fk_product_attr_p` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `value_idx` ON `ProductAttribute` (`value`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Orders`
-- Cua hang hien thi theo order
DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY ,
  `userId` bigint(20) NOT NULL,
  `storeId` bigint(20) NOT NULL,
  -- Ma don hang
  `orderNumber` varchar(16) NOT NULL,
  -- Da dat, dang nhan don hang...
  `status` tinyint(2) NOT NULL,
  -- Da thanh toan hay chua, tuong lai tich hop them thanh toan dien tu
  `isPaid` tinyint(1) DEFAULT 0,
  -- Co lay hoa don (VAT) hay khong
  `isGetTax` tinyint(1) DEFAULT 0,
  -- Phuong thuc thanh toan: tien mat, chuyen khoan, needy xu
  `paymentMethod` tinyint(2),
  -- Ghi chu cho ca don hang
  `note` varchar(255),
  `receiveFrom` datetime,
  `receiveTo` datetime,
  -- Phi van chuyen
  `feeTransport` float(10, 2) NOT NULL,
  `transportFrom` datetime,
  `transportTo` datetime,
  -- Danh cho goi ho
  `receiverId` bigint(20),
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_orders_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_orders_s` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`),
  CONSTRAINT `Fk_orders_r` FOREIGN KEY (`receiverId`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `orderNumber_idx` ON `Orders` (`orderNumber`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `OrderProduct`
-- User hien thi theo tung product
DROP TABLE IF EXISTS `OrderProduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderProduct` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `orderId` bigint(20) NOT NULL,
  `productCompanyId` bigint(20) NOT NULL,
  -- So luong mua
  `quantity` smallint(5) unsigned NOT NULL,
  CONSTRAINT `Fk_order_product_o` FOREIGN KEY (`orderId`) REFERENCES `Orders` (`id`),
  CONSTRAINT `Fk_order_product_ps` FOREIGN KEY (`productCompanyId`) REFERENCES `ProductCompany` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserReports`
--
DROP TABLE IF EXISTS `UserReports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserReports` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- Chua don hang => nguoi report
  `orderId` bigint(20) NOT NULL,
  `typeReport` tinyint(2) NOT NULL,
  `isAccepted` tinyint(1) DEFAULT 0,
  `description` text(1500) NOT NULL,
  `pictures` text(1500) NOT NULL,
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `acceptedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_company_reputation_o` FOREIGN KEY (`orderId`) REFERENCES `Orders` (`id`),
  CONSTRAINT `Fk_company_reputation_ac` FOREIGN KEY (`acceptedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `StoreBankAccounts`
--
DROP TABLE IF EXISTS `StoreBankAccounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StoreBankAccounts` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `storeId` bigint(20) NOT NULL,
  `creditAccount` varchar(32) NOT NULL,
  -- Ten chu tai khoan
  `beneficiaryName` varchar(128) NOT NULL,
  -- Ten chi nhanh
  `beneficiaryBankName` varchar(255) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_store_bank_account_s` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`),
  CONSTRAINT `Fk_store_bank_account_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `creditAccount_idx` ON `StoreBankAccounts` (`storeId`);
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
  `rating` tinyint(2),
  `review` text(500),
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_store_review_us` UNIQUE (`userId`,`storeId`),
  CONSTRAINT `Fk_store_review_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_store_review_s` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`)
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
  `rating` tinyint(2),
  `review` text(500),
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_product_review_up` UNIQUE (`userId`,`productId`),
  CONSTRAINT `Fk_product_review_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_product_review_p` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`)
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
  `rating` tinyint(2),
  `review` text(500),
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_user_review_sb` UNIQUE (`sellerId`,`buyerId`),
  CONSTRAINT `Fk_user_review_s` FOREIGN KEY (`sellerId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_user_review_b` FOREIGN KEY (`buyerId`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FeeTransport`
-- Phi van chuyen cho moi cong ty
-- Phi di chuyen neu co - tu van tai nha
DROP TABLE IF EXISTS `FeeTransport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FeeTransport` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `companyId` bigint(20) NOT NULL,
  `distanceFrom` float(5, 1) NOT NULL,
  `distanceTo` float(5, 1) NOT NULL,
  `fee` float(8, 2) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_fee_transport_co` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`),
  CONSTRAINT `Fk_fee_transport_up` FOREIGN KEY (`lastUpdatedBy`) REFERENCES `Users` (`id`)
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
