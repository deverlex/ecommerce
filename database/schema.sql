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
DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `permission_id` varchar(64) PRIMARY KEY,
  `enable` tinyint(1) DEFAULT 1,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `permission_role`
--
DROP TABLE IF EXISTS `permission_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_role` (
  `perrol_id` smallint(5) unsigned AUTO_INCREMENT PRIMARY KEY,
  `permission_id` varchar(64) NOT NULL,
  `role_id` varchar(64) NOT NULL,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_permission_role_pr` UNIQUE (`permission_id`,`role_id`), 
  CONSTRAINT `Fk_permission_role_p` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`),
  CONSTRAINT `Fk_permission_role_r` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` varchar(64) NOT NULL PRIMARY KEY,
  `enable` tinyint(1) DEFAULT 1,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `userol_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `role_id` varchar(64) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_user_role_ru` UNIQUE (`role_id`,`user_id`), 
  CONSTRAINT `Fk_user_role_r` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `Fk_user_role_u` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- phone number k dc unique vi viec dang ky su dung lai sdt se bi can tro
  `username` varchar(32) NOT NULL,
  `password` varchar(255) NOT NULL,
  -- Trang thai tai khoan: khoa, con hoat dong...
  `state` tinyint(2) NOT NULL,
  -- Khoa 90 ngay
  `unlock_time` datetime,
  -- firebase UID
  `firebase_uid` varchar(255), 
  -- Khong yeu cau null de register tu app Vendor
  `fcm_token` varchar(225),
  `full_name` varchar(255),
  `address` varchar(255),
  `lat` float(10,6),
  `lng` float(10,6),
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_reset_password` timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `idx_state` ON `user` (`state`);
CREATE INDEX `idx_username` ON `user` (`username`);
CREATE INDEX `idx_loc` ON `user` (`lat`, `lng`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wallet`
DROP TABLE IF EXISTS `wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wallet` (
  `wallet_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `user_id` bigint(20) NOT NULL,
  `budget` smallint(5) NOT NULL,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_wallet_u` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `WalletPays`
DROP TABLE IF EXISTS `wallet_pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wallet_pay` (
  `walpay_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `wallet_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `budget_charge` smallint(5) NOT NULL,
  `description` text(1000),
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_wallet_pay_w` FOREIGN KEY (`wallet_id`) REFERENCES `wallet` (`wallet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `NotificationUser`
--
DROP TABLE IF EXISTS `notification_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_user` (
  `notuse_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- Id notification
  `notify_id` bigint(20) NOT NULL,
  -- Nguoi nhan notification
  `recever_id` bigint(20) NOT NULL,
  `is_read` tinyint(1) NOT NULL DEFAULT 0,
  `is_view` tinyint(1) NOT NULL DEFAULT 0,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_notify_user_r` FOREIGN KEY (`recever_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `Fk_notify_user_n` FOREIGN KEY (`notify_id`) REFERENCES `notification` (`notify_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification`
--
DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `notify_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- nguoi gui
  `sender_id` bigint(20) NOT NULL,
  -- Id of object - examples: orderId, productId...
  `ref_id` bigint(20),
  -- Will open activity on Client (Android) if it available
  `ref_entity` varchar(64),
  -- action, examples: open an edit product...
  `ref_method` varchar(64),
  `title` varchar(255) NOT NULL,
  `html_content` text(2000) NOT NULL,
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_notification_s` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company`
--
DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `company_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- It will update for app manager
  `fcm_token` varchar(255),
  -- Trang thai: chua kich hoat, da kich hoat, tam ngung, da dong cua
  `state` tinyint(2) NOT NULL,
  -- Xep cap bac
  `level` tinyint(3) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `idx_state` ON `company` (`state`);
CREATE INDEX `idx_level` ON `company` (`level`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store`
--
DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `store_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `company_id` bigint(20) NOT NULL,
  -- Trang thai cua hang da kich hoat hay chua
  -- Dieu kien kich hoat: khi co hang ban
  -- inactive (0), active(1), locked (-1)
  `state` tinyint(2) NOT NULL,
  -- Trang thai san sang working, busy, close time
  `status` tinyint(2) NOT NULL,
  -- Khoa den khi don hang chuyen trang thai
  -- Qua 3 lan 1 ngay, khoa 7 ngay, de mo khoa, nop tien 1.000.000
  `unlock_time` datetime,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `lat` float(10,6) NOT NULL,
  `lng` float(10,6) NOT NULL,
  -- Thoi gian mo cua
  `opening_time` time NOT NULL,
  `closing_time` time NOT NULL,
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL,
  CONSTRAINT `Fk_store_c` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `idx_status` ON `store` (`status`);
CREATE INDEX `idx_loc` ON `store` (`lat`, `lng`);
CREATE INDEX `idx_active_time` ON `store` (`opening_time`, `closing_time`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company_staff`
--
DROP TABLE IF EXISTS `company_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_staff` (
  `comsta_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- Moi sdt dang ky duy nhat 1 company
  `user_id` bigint(20) NOT NULL UNIQUE,
  `company_id` bigint(20) NOT NULL,
  -- Khi staff setup app and join system
  -- Firebase token will be update
  `store_id` bigint(20) NOT NULL,
  `fcm_token` varchar(255),
  -- Trang thai user - inactive, active, waiting
  -- Cho phep chu cua hang tu them sdt nhan vien cua minh vao he thong 
  -- Chi cho phep join thanh vien moi tu cach them tu he thong
  `state` tinyint(2) NOT NULL,
  -- ready, busy
  `status` tinyint(2) NOT NULL,
  -- Thoi gian gia nhap cua hang
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_company_staff_uc` UNIQUE (`user_id`,`company_id`),
  CONSTRAINT `Uniq_company_staff_us` UNIQUE (`user_id`,`store_id`),

  CONSTRAINT `Fk_company_staff_co` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`),
  CONSTRAINT `Fk_company_staff_st` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`),
  CONSTRAINT `Fk_company_staff_us` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;

CREATE INDEX `idx_state` ON `company_staff` (`state`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company_guarantee`
-- Chi co CEO moi dc thuc hien hanh dong yeu cau
DROP TABLE IF EXISTS `company_guarantee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_guarantee` (
  `comgua_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- Chua xoa voi, doi doi tac chap thuan hoac xet duyet
  -- Trang thai: active, inactive, waiting
  `state` tinyint(2) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `change_by` bigint(20) NOT NULL,
  CONSTRAINT `Fk_company_guarantee_c` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `budget`
--
DROP TABLE IF EXISTS `budget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `budget` (
  `budget_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `company_id` bigint(20) NOT NULL UNIQUE,
  -- Insert from code
  `budget` float(12,2) NOT NULL,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_budget_c` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pay`
--
DROP TABLE IF EXISTS `pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pay` (
  `pay_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `budget_id` bigint(20) NOT NULL,
  `behavior` tinyint(2) NOT NULL,
  `budget_charge` float(12, 2) NOT NULL,
  `description` text(1000) NOT NULL,
  -- tai khoan thanh toan
  `debit_account` varchar(64),
  -- tai khoan nhan
  `credit_account` varchar(64),
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint(20) NOT NULL,
  CONSTRAINT `Fk_pays_b` FOREIGN KEY (`budget_id`) REFERENCES `budget` (`budget_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE INDEX `idx_debit_account` ON `pay` (`debit_account`);
CREATE INDEX `idx_credit_account` ON `pay` (`credit_account`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category`
DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` varchar(64) PRIMARY KEY,
  `enable` tinyint(1) DEFAULT 1,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sub_category`
DROP TABLE IF EXISTS `sub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_category` (
  `subcat_id` int(10) AUTO_INCREMENT PRIMARY KEY, 
  `category_id` varchar(64) NOT NULL,
  `pre_category` varchar(64) NOT NULL,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL,
  CONSTRAINT `Fk_sub_category_cat` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `Fk_sub_category_pre` FOREIGN KEY (`pre_category`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attribute`
DROP TABLE IF EXISTS `attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attribute` (
  `attribute_id` varchar(64) PRIMARY KEY,
  `enable` tinyint(1) DEFAULT 1,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category_attribute`
DROP TABLE IF EXISTS `category_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_attribute` (
  `catatt_id` bigint(20) unsigned AUTO_INCREMENT PRIMARY KEY,
  `attribute_id` varchar(64) NOT NULL,
  `category_id` varchar(64) NOT NULL,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL,
  CONSTRAINT `Fk_category_attr_ct` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `Fk_category_attr_at` FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `category_id` varchar(64) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  -- Trang thai con su dung khong, con su dung, da duoc kiem duyt hay chua
  -- DELETED (-2), DENIED (-1), INACTIVE (0), ACTIVE (1)
  `state` tinyint(2),
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL,
  CONSTRAINT `Fk_products_cg` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `Fk_products_co` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;

CREATE INDEX `idx_product_number` ON `product` (`product_number`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_attribute`
DROP TABLE IF EXISTS `product_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_attribute` (
  `proatt_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `product_id` bigint(20) NOT NULL,
  `attribute_id` varchar(64) NOT NULL,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint NOT NULL,
  CONSTRAINT `Uniq_product_attr_pa` UNIQUE (`product_id`, `attribute_id`),
  CONSTRAINT `Fk_product_attr_at` FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`attribute_id`),
  CONSTRAINT `Fk_product_attr_pr` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Orders`
-- Cua hang hien thi theo order
DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `order_id` bigint(20) AUTO_INCREMENT PRIMARY KEY ,
  `user_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  -- Da dat, dang nhan don hang...
  `status` tinyint(2) NOT NULL,
  -- Da thanh toan hay chua, tuong lai tich hop them thanh toan dien tu
  `is_paid` tinyint(1) DEFAULT 0,
  -- Co lay hoa don (VAT) hay khong
  `is_get_tax` tinyint(1) DEFAULT 0,
  -- Phuong thuc thanh toan: tien mat, chuyen khoan, needy xu
  `payment_method` tinyint(2),
  -- Ghi chu cho ca don hang
  `note` text(1000),
  `receive_from` datetime,
  `receive_to` datetime,
  -- Phi van chuyen
  `fee_transport` float(10, 2) NOT NULL,

  `transport_from` datetime,
  `transport_to` datetime,
  -- Danh cho goi ho
  `receiver_id` bigint(20),
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Fk_orders_us` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `Fk_orders_st` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`),
  CONSTRAINT `Fk_orders_rc` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_product`
-- User hien thi theo tung product
DROP TABLE IF EXISTS `order_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_product` (
  `ordpro_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  -- So luong mua
  `quantity` smallint(5) unsigned NOT NULL,
  CONSTRAINT `Fk_order_product_or` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `Fk_order_product_ps` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_report`
--
DROP TABLE IF EXISTS `user_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_report` (
  `userep_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  -- Chua don hang => nguoi report
  `order_id` bigint(20) NOT NULL,
  `type_report` tinyint(2) NOT NULL,
  `is_accepted` tinyint(1) DEFAULT 0,
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `accepted_by` bigint(20) NOT NULL,
  CONSTRAINT `Fk_user_report_or` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `Fk_user_report_ac` FOREIGN KEY (`accepted_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store_bank_account`
DROP TABLE IF EXISTS `store_bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_bank_account` (
  `stbaac_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `store_id` bigint(20) NOT NULL,
  `credit_account` varchar(64) NOT NULL,
  -- Ten chu tai khoan
  `beneficiary_name` varchar(255) NOT NULL,
  -- Ten chi nhanh
  `beneficiary_bank_name` varchar(255) NOT NULL,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL,
  CONSTRAINT `Fk_store_bank_account_st` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE INDEX `idx_credit_account` ON `store_bank_account` (`credit_account`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Store_Review`
--
DROP TABLE IF EXISTS `store_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_review` (
  `storev_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `user_id` bigint(20) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  `rating` tinyint(2),
  `review` text(1000),
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_store_review_us` UNIQUE (`user_id`,`store_id`),
  CONSTRAINT `Fk_store_review_u` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `Fk_store_review_s` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Product_Review`
--
DROP TABLE IF EXISTS `product_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_review` (
  `provie_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `user_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `rating` tinyint(2),
  `review` text(1000),
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_product_review_up` UNIQUE (`user_id`,`product_id`),
  CONSTRAINT `Fk_product_review_u` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `Fk_product_review_p` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_review`
--
DROP TABLE IF EXISTS `user_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_review` (
  `userev_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `vendor_id` bigint(20) NOT NULL,
  `buyer_id` bigint(20) NOT NULL,
  `rating` tinyint(2),
  `review` text(1000),
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `Uniq_user_review_sb` UNIQUE (`vendor_id`,`buyer_id`),
  CONSTRAINT `Fk_user_review_v` FOREIGN KEY (`vendor_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `Fk_user_review_b` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fee_transport`
-- Phi van chuyen cho moi cong ty
-- Phi di chuyen neu co - tu van tai nha
DROP TABLE IF EXISTS `fee_transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fee_transport` (
  `feetra_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `company_id` bigint(20) NOT NULL,
  `distance_from` float(5, 1) NOT NULL,
  `distance_to` float(5, 1) NOT NULL,
  `fee` float(8, 2) NOT NULL,
  `last_updated_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` bigint(20) NOT NULL,
  CONSTRAINT `Fk_fee_transport_co` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii COLLATE=ascii_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hashtag`
DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hashtag` (
  `hashtag_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `hashtag` varchar(255) UNIQUE NOT NULL,
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_hashtag`
DROP TABLE IF EXISTS `product_hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_hashtag` (
  `prohas_id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
  `product_id` bigint(20) NOT NULL,
  `hashtag_id` bigint(20) NOT NULL,
  CONSTRAINT `Uniq_product_hashtag_ph` UNIQUE (`product_id`,`hashtag_id`),
  CONSTRAINT `Fk_product_hashtag_p` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `Fk_product_hashtag_h` FOREIGN KEY (`hashtag_id`) REFERENCES `hashtag` (`hashtag_id`)
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
