INSERT INTO `attribute` (`attribute_id`, `last_updated_by`)
VALUES
("weight", 1),
("hight", 1),
("material", 1),
("neck_type", 1);

INSERT INTO `category_attribute` (`attribute_id`, `category_id`, `last_updated_by`)
VALUES
("weight", "call_gas", 1),
("weight", "call_water", 1),
("material", "gas_water", 1),
("neck_type", "call_gas", 1),
("hight", "call_gas", 1),
("hight", "call_water", 1);
