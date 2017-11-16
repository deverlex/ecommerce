INSERT INTO `category` (`category_id`, `last_updated_by`) VALUES
("gas_water", 1),
("call_gas", 1),
("call_water", 1),

("repair_transport", 1),
("repair_automobile", 1),
("repair_car", 1),
("repair_motobike", 1),
("repair_ebike", 1),

("fix_houseware", 1),
("fix_fridge", 1),
("fix_air_conditioning", 1);

INSERT INTO `sub_category` (`subcategory_id`, `refcategory_id`, `ref_level`, `is_next`, `last_updated_by`) VALUES
("gas_water", "price_now", 0, true, 1), 

("call_gas", "gas_water", 1, true, 1),
("call_gas", "price_now", 0, false, 1),

("call_water", "gas_water", 1, true, 1),
("call_water", "price_now", 0, false, 1),

("repair_transport", "price_later", 0, true, 1),

("repair_automobile", "repair_transport", 1, true, 1),
("repair_automobile", "price_later", 0, false, 1),

("repair_car", "repair_automobile", 2, true, 1),
("repair_car", "repair_transport", 1, false, 1),
("repair_car", "price_later", 0, false, 1),

("repair_motobike", "repair_automobile", 2, true, 1),
("repair_motobike", "repair_transport", 1, false, 1),
("repair_motobike", "price_later", 0, false, 1),

("repair_ebike", "repair_automobile", 2, true, 1),
("repair_ebike", "repair_transport", 1, false, 1),
("repair_ebike", "price_later", 0, false, 1),

("fix_houseware", "price_later", 0, true, 1),

("fix_fridge", "fix_houseware", 1, true, 1),
("fix_fridge", "price_later", 0, false, 1),

("fix_air_conditioning", "fix_houseware", 1, true, 1),
("fix_air_conditioning", "price_later", 0, false, 1);
