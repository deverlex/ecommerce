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