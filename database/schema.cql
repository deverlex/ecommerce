--
-- Table structure for table `ViewProductLogs`
-- Cac san pham da xem

--
-- Table structure for table `Logs`
--
DROP TABLE IF EXISTS `Logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Logs` (
  `class` varchar(32) NOT NULL,
  `method` varchar(32) NOT NULL,
  `description` varchar(255) NOT NULL,
  `createdTime` timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX `class_idx` ON `Logs` (`class`);
CREATE INDEX `method_idx` ON `Logs` (`method`);
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


--
-- Table structure for table `WalletLogs`
--
