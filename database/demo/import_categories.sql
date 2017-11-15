INSERT INTO `category` (`category_id`, `last_updated_by`) VALUES
(`gas_water`, 1),
(`call_gas`, 1),
(`call_water`, 1);

INSERT INTO `sub_category` (`category_id`, `pre_category`, `last_updated_by`) VALUES
(`gas_water`, `price_now`, 1),
(`call_gas`, `gas_water`, 1),
(`call_water`, `gas_water`, 1);
