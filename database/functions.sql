-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: needy
-- ------------------------------------------------------
-- Server version	5.7.18

DROP FUNCTION IF EXISTS `fun_calculator_distance`;

-- Calculator as Google Map v3
DELIMITER $$

CREATE FUNCTION `fn_calculator_distance` (plat float, plng float, tlat float, tlng float)
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

DROP TRIGGER IF EXISTS `tr_add_role_for_new_user`;

-- 
DELIMITER $$

CREATE TRIGGER `tr_add_role_for_new_user` AFTER INSERT ON `user`
FOR EACH ROW
BEGIN
	DECLARE user_id bigint(20);
	SET user_id = NEW.user_id;
	INSERT INTO user_role(`role_id`, `user_id`, `last_updated_by`) 
	VALUES ('USER', user_id, 1);
END

$$
delimiter ;