INSERT INTO `user`(`username`, `password`, `state`, `lat`, `lng`) VALUES
('system', '$2a$10$l9dsqwEN6nKFjpWZva3i0eUFtL0PZYkS45yEYdQ988Tf01qMahNZq', '1', '21.028799', '105.850914');

INSERT INTO `role`(`role_id`, `last_updated_by`) VALUES 
('SYSTEM', 1),
('ADMIN', 1),
('USER', 1),
('COMPANY_OWENER', 1),
('SELLER', 1),
('STORE_KEEPER', 1),
('STORE_MANAGER', 1);

INSERT INTO `user_role`(`role_id`, `user_id`, `last_updated_by`) VALUES
('SYSTEM', 1, 1);

INSERT INTO `category` (`category_id`, `last_updated_by`) VALUES
('price_now', 1),
('price_later', 1);