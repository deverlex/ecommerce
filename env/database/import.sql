DELETE FROM `user`;
DELETE FROM `role`;
DELETE FROM `user_role`;
DELETE FROM `permission`;
DELETE FROM `category`;
DELETE FROM `permission_role`;

INSERT INTO `user`(`username`, `password`, `state`, `lat`, `lng`) VALUES
('system', '$2a$10$l9dsqwEN6nKFjpWZva3i0eUFtL0PZYkS45yEYdQ988Tf01qMahNZq', '1', '21.028799', '105.850914');

INSERT INTO `permission` (`name`, `last_updated_by`) VALUES
('ALL', 1),
('VIEW_STORE', 1),
('EDIT_STORE', 1),
('DELETE_STORE', 1),
('CREATE_STORE', 1);

INSERT INTO `role`(`name`, `last_updated_by`) VALUES 
('SYSTEM', 1),
('ADMIN', 1),
('USER', 1),
('COMPANY_OWENER', 1),
('SELLER', 1),
('STORE_KEEPER', 1),
('STORE_MANAGER', 1);

INSERT INTO `permission_role`(`permission_name`, `role_name`, `last_updated_by`) VALUES
('ALL', 'SYSTEM', 1);

INSERT INTO `user_role`(`role_name`, `permission_name`, `user_id`, `last_updated_by`) VALUES
('SYSTEM', 'ALL', 1, 1);

INSERT INTO `category` (`name`, `last_updated_by`) VALUES
('price_now', 1),
('price_later', 1);