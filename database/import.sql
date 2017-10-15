INSERT INTO Users(`username`, `password`, `state`, `firstName`, `lastName`) VALUES
('+84968208244', '$2a$10$l9dsqwEN6nKFjpWZva3i0eUFtL0PZYkS45yEYdQ988Tf01qMahNZq', '1', 'Needy', 'Ldt');

INSERT INTO Roles(`role`, `title`, `createdBy`, `lastUpdatedBy`) VALUES 
('ADMIN', 'System admin', 1, 1),
('USER', 'Normal users', 1 , 1);

INSERT INTO UserRole(`role`, `userId`, `createdBy`, `lastUpdatedBy`) VALUES
('ADMIN', 1, 1, 1),
('USER', 1, 1, 1);
-- INSERT INTO Permissions (permission, title) VALUES
-- ('add_table', 'Create table'),
-- ('modify_table', 'Modify table'),
-- ('remove_table', 'Delete table'),

-- ('add_store', 'Create store'),
-- ('remove_store', 'Remove store'),
-- ('modify_store', 'Modify store'),

-- ('add_smgr', 'Add store manager'),
-- ('remove_smgr', 'Remove store manager'),

-- ('add_staff', 'Add staff'),
-- ('remove_staff', 'Remove staff'),

-- ('accept_order', 'Accept order'),
-- ('denied_order', 'Denied order'),

-- ('add_product', 'Create product'),
-- ('modify_product', 'Modify product'),
-- ('remove_product', 'Remove product');




-- ('', 'Accountant'),
-- ('cashier', 'Cashier'),

-- ('company_owener', 'Company owner (CEO)'),
-- ('store_manager', 'Manager storeshop'),
-- ('seller', 'Seller'),
-- ('storekeepers', 'Storekeepers');

-- INSERT INTO PermissionRole (permission, role) VALUES
-- ('add_store', 'company_owener'),
-- ('remove_store', 'company_owener'),
-- ('modify_store', 'company_owener'),
-- ('add_smgr', 'company_owener'),
-- ('remove_smgr', 'company_owener'),

-- ('add_staff', 'store_manager'),
-- ('remove_staff', 'store_manager'),

-- ('accept_order', 'seller'),
-- ('denied_order', 'seller'),

-- ('add_product', 'storekeepers'),
-- ('modify_product', 'storekeepers'),
-- ('remove_product', 'storekeepers');

-- Hoan thien du thong tin
-- INSERT INTO Users(fcmToken, state, firstName, lastName, phoneNumber, password, address, lat, lng) VALUES
-- ('123',  1, 'Nguyen', 'Do', '+84968208244', '12345678', '16 Le Quy Don, Hai Ba Trung, Ha Noi', '21.015465', '105.855834');