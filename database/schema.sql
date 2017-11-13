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
  `permission` varchar(64) PRIMARY KEY,
  `enable` tinyint(1) DEFAULT 1,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PermissionRole`
--
DROP TABLE IF EXISTS `PermissionRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PermissionRole` (
  `id` smallint(5) unsigned AUTO_INCREMENT PRIMARY KEY,
  `permission` varchar(64) NOT NULL,
  `role` varchar(64) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_permission_role_pr` UNIQUE (`permission`,`role`), 
  CONSTRAINT `Fk_permission_role_p` FOREIGN KEY (`permission`) REFERENCES `Permissions` (`permission`),
  CONSTRAINT `Fk_permission_role_r` FOREIGN KEY (`role`) REFERENCES `Roles` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Roles`
DROP TABLE IF EXISTS `Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Roles` (
  `role` varchar(64) NOT NULL PRIMARY KEY,
  `enable` tinyint(1) DEFAULT 1,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserRole`
DROP TABLE IF EXISTS `UserRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRole` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `role` varchar(64) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_user_role_ru` UNIQUE (`role`,`userId`), 
  CONSTRAINT `Fk_user_role_r` FOREIGN KEY (`role`) REFERENCES `Roles` (`role`),
  CONSTRAINT `Fk_user_role_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Users`
DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- phone number k dc unique vi viec dang ky su dung lai sdt se bi can tro
  `username` varchar(32) NOT NULL,
  `password` varchar(255) NOT NULL,
  -- Trang thai tai khoan: khoa, con hoat dong...
  `state` tinyint(2) NOT NULL,
  -- Khoa 90 ngay
  `unlockTime` datetime,
  -- firebase UID
  `firebaseUid` varchar(255), 
  -- Khong yeu cau null de register tu app Vendor
  `fcmToken` varchar(225),
  `fullName` varchar(255),
  `address` varchar(255),
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
DROP TABLE IF EXISTS `Wallets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Wallets` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `userId` bigint(20) NOT NULL,
  `budget` smallint(5) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_wallets_u` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `WalletPays`
DROP TABLE IF EXISTS `WalletPays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WalletPays` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `walletId` bigint(20) NOT NULL,
  `productId` bigint(20) NOT NULL,
  `budgetCharge` smallint(5) NOT NULL,
  `description` text(1000),
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
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
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
  -- Id of object - examples: orderId, productId...
  `refId` bigint(20),
  -- Will open activity on Client (Android) if it available
  `refEntity` varchar(64),
  -- action, examples: open an edit product...
  `refMethod` varchar(64),
  `title` varchar(255) NOT NULL,
  `htmlContent` text(2000) NOT NULL,
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
  -- CompanyNumber:     1710VKG1FI9O
  `companyNumber` varchar(12) UNIQUE NOT NULL,
  -- It will update for app manager
  `fcmToken` varchar(255),
  -- Trang thai: chua kich hoat, da kich hoat, tam ngung, da dong cua
  `state` tinyint(2) NOT NULL,
  -- Xep cap bac
  `level` tinyint(3) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `companyNumber_idx` ON `Companies` (`companyNumber`);
CREATE INDEX `state_idx` ON `Companies` (`state`);
CREATE INDEX `level_idx` ON `Companies` (`level`);
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
  `storeNumber` varchar(12) NOT NULL,
  -- Trang thai cua hang da kich hoat hay chua
  -- Dieu kien kich hoat: khi co hang ban
  -- inactive (0), active(1), locked (-1)
  `state` tinyint(2) NOT NULL,
  -- Trang thai san sang working, busy, close time
  `status` tinyint(2) NOT NULL,
  -- Khoa den khi don hang chuyen trang thai
  -- Qua 3 lan 1 ngay, khoa 7 ngay, de mo khoa, nop tien 1.000.000
  `unlockTime` datetime,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `lat` float(10,6) NOT NULL,
  `lng` float(10,6) NOT NULL,
  -- Thoi gian mo cua
  `openingTime` time NOT NULL,
  `closingTime` time NOT NULL,
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_stores_c` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `storeNumber_idx` ON `Stores` (`storeNumber`);
CREATE INDEX `status_idx` ON `Stores` (`status`);
CREATE INDEX `loc_idx` ON `Stores` (`lat`, `lng`);
CREATE INDEX `activeTime_idx` ON `Stores` (`openingTime`, `closingTime`);
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

  CONSTRAINT `Fk_company_staff_co` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`),
  CONSTRAINT `Fk_company_staff_st` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`),
  CONSTRAINT `Fk_company_staff_us` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;

CREATE INDEX `state_idx` ON `CompanyStaff` (`state`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CompanyGuarantees`
-- Chi co CEO moi dc thuc hien hanh dong yeu cau
DROP TABLE IF EXISTS `CompanyGuarantees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CompanyGuarantees` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- Chua xoa voi, doi doi tac chap thuan hoac xet duyet
  -- Trang thai: active, inactive, waiting
  `state` tinyint(2) NOT NULL,
  `companyId` bigint(20) NOT NULL,
  -- Ma hop dong tu sinh
  `agreementNumber` varchar(12) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `changedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_company_guarantee_c` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;

CREATE INDEX `agreementNumber_idx` ON `CompanyGuarantees` (`agreementNumber`);
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
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_budgets_c` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Pays`
--
DROP TABLE IF EXISTS `Pays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pays` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `budgetId` bigint(20) NOT NULL,
  `behavior` tinyint(2) NOT NULL,
  -- So giao dich, tu sinh trong may
  `payNumber` varchar(16) NOT NULL,
  `budgetCharge` float(12, 2) NOT NULL,
  `description` text(1000) NOT NULL,
  -- tai khoan thanh toan
  `debitAccount` varchar(64),
  -- tai khoan nhan
  `creditAccount` varchar(64),
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `createdBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_pays_b` FOREIGN KEY (`budgetId`) REFERENCES `Budgets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE INDEX `payNumber_idx` ON `Pays` (`payNumber`);
CREATE INDEX `debitAccount_idx` ON `Pays` (`debitAccount`);
CREATE INDEX `creditAccount_idx` ON `Pays` (`creditAccount`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Categories`
--
DROP TABLE IF EXISTS `Categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Categories` (
  `category` varchar(64) PRIMARY KEY,
  `enable` tinyint(1) DEFAULT 1,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Attributes`
--
DROP TABLE IF EXISTS `Attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Attributes` (
  `attribute` varchar(64) PRIMARY KEY,
  `enable` tinyint(1) DEFAULT 1,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CategoryAttribute`
--
DROP TABLE IF EXISTS `CategoryAttribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CategoryAttribute` (
  `id` bigint(20) unsigned AUTO_INCREMENT PRIMARY KEY,
  `attribute` varchar(64) NOT NULL,
  `category` varchar(64) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_categoryattr_ct` FOREIGN KEY (`category`) REFERENCES `Categories` (`category`),
  CONSTRAINT `Fk_categoryattr_at` FOREIGN KEY (`attribute`) REFERENCES `Attributes` (`attribute`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Products`
--
DROP TABLE IF EXISTS `Products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Products` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `category` varchar(64) NOT NULL,
  `companyId` bigint(20) NOT NULL,
  `productNumber` varchar(12) NOT NULL,
  -- Trang thai con su dung khong, con su dung, da duoc kiem duyt hay chua
  -- DELETED (-2), DENIED (-1), INACTIVE (0), ACTIVE (1)
  `state` tinyint(2),
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_products_cg` FOREIGN KEY (`category`) REFERENCES `Categories` (`category`),
  CONSTRAINT `Fk_products_co` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;

CREATE INDEX `productNumber_idx` ON `Products` (`productNumber`);
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
  `attribute` varchar(64) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint NOT NULL,
  CONSTRAINT `Uniq_product_attr_pa` UNIQUE (`productId`, `attribute`),
  CONSTRAINT `Fk_product_attr_at` FOREIGN KEY (`attribute`) REFERENCES `Attributes` (`attribute`),
  CONSTRAINT `Fk_product_attr_pr` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
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
  `note` text(1000),
  `receiveFrom` datetime,
  `receiveTo` datetime,
  -- Phi van chuyen
  `feeTransport` float(10, 2) NOT NULL,

  `transportFrom` datetime,
  `transportTo` datetime,
  -- Danh cho goi ho
  `receiverId` bigint(20),
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_orders_us` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `Fk_orders_st` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`),
  CONSTRAINT `Fk_orders_rc` FOREIGN KEY (`receiverId`) REFERENCES `Users` (`id`)
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
  -- Sua thanh productId
  `productId` bigint(20) NOT NULL,
  -- So luong mua
  `quantity` smallint(5) unsigned NOT NULL,
  CONSTRAINT `Fk_order_product_or` FOREIGN KEY (`orderId`) REFERENCES `Orders` (`id`),
  CONSTRAINT `Fk_order_product_ps` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
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
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `acceptedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_company_reputation_or` FOREIGN KEY (`orderId`) REFERENCES `Orders` (`id`),
  CONSTRAINT `Fk_company_reputation_ac` FOREIGN KEY (`acceptedBy`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
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
  `creditAccount` varchar(64) NOT NULL,
  -- Ten chu tai khoan
  `beneficiaryName` varchar(255) NOT NULL,
  -- Ten chi nhanh
  `beneficiaryBankName` varchar(255) NOT NULL,
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedBy` bigint(20) NOT NULL,
  CONSTRAINT `Fk_store_bank_account_st` FOREIGN KEY (`storeId`) REFERENCES `Stores` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE INDEX `creditAccount_idx` ON `StoreBankAccounts` (`creditAccount`);
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
  `review` text(1000),
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
  `review` text(1000),
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
  `vendorId` bigint(20) NOT NULL,
  `buyerId` bigint(20) NOT NULL,
  `rating` tinyint(2),
  `review` text(1000),
  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_user_review_sb` UNIQUE (`vendorId`,`buyerId`),
  CONSTRAINT `Fk_user_review_v` FOREIGN KEY (`vendorId`) REFERENCES `Users` (`id`),
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
  CONSTRAINT `Fk_fee_transport_co` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `HashTags`
DROP TABLE IF EXISTS `HashTags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HashTags` (
  `id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `hashtag` varchar(32) UNIQUE NOT NULL,
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProductHashTag`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
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
