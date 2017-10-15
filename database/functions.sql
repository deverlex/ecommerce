-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: needy
-- ------------------------------------------------------
-- Server version	5.7.18

DROP FUNCTION IF EXISTS `calculator_distance`;

-- Calculator as Google Map v3
DELIMITER $$

CREATE FUNCTION `calculator_distance` (plat float, plng float, tlat float, tlng float)
RETURNS FLOAT
BEGIN
	DECLARE distance FLOAT;
	SET distance = 3959 * acos (
      cos ( radians(plat) )
      * cos( radians( tlat ) )
      * cos( radians( tlng ) - radians(plng) )
      + sin ( radians(plat) )
      * sin( radians( tlat ) )
    );
RETURN distance;
END

$$
delimiter ;

DROP TRIGGER IF EXISTS `update_role_for_new_user`;

-- 
DELIMITER $$

CREATE TRIGGER `after_user_insert` AFTER INSERT ON `Users`
FOR EACH ROW
BEGIN
	DECLARE userId bigint(20);
	SET userId = NEW.id;
	INSERT INTO UserRole(`role`, `userId`, `createdBy`, `lastUpdatedBy`) 
	VALUES ('USER', userId, 1, 1);
END

$$
delimiter ;