INSERT INTO Permissions (permission, title) VALUES
('add_table', 'Create table'),
('modify_table', 'Modify table'),
('remove_table', 'Delete table'),

('add_store', 'Create store'),
('remove_store', 'Remove store'),
('modify_store', 'Modify store'),

('add_smgr', 'Add store manager'),
('remove_smgr', 'Remove store manager'),

('add_staff', 'Add staff'),
('remove_staff', 'Remove staff'),

('accept_order', 'Accept order'),
('denied_order', 'Denied order'),

('add_product', 'Create product'),
('modify_product', 'Modify product'),
('remove_product', 'Remove product');


INSERT INTO Roles(role, title) VALUES 
('admin', 'System Admin'),
('db_admin', 'Database Admin'),
('accountant', 'Accountant'),
('cashier', 'Cashier'),

('company_owener', 'Company owner (CEO)'),
('store_manager', 'Manager storeshop'),
('seller', 'Seller'),
('storekeepers', 'Storekeepers');

INSERT INTO PermissionRole (permission, role) VALUES
('add_store', 'company_owener'),
('remove_store', 'company_owener'),
('modify_store', 'company_owener'),
('add_smgr', 'company_owener'),
('remove_smgr', 'company_owener'),

('add_staff', 'store_manager'),
('remove_staff', 'store_manager'),

('accept_order', 'seller'),
('denied_order', 'seller'),

('add_product', 'storekeepers'),
('modify_product', 'storekeepers'),
('remove_product', 'storekeepers');

-- Insert Users
-- INSERT INTO Users(fcmToken, firstName, lastName, phoneNumber, password, lat, lng) VALUES 
-- ('123', 'Nguyen', 'Do2', '+84968208202', '12345678', '21.020312', '105.848324'),
-- ('123', 'Nguyen', 'Do3', '+84968208203', '12345678', '21.029285', '105.858666'),
-- ('123', 'Nguyen', 'Do4', '+84968208204', '12345678', '21.018549', '105.826437'),
-- ('123', 'Nguyen', 'Do5', '+84968208205', '12345678', '21.029894', '105.814625'),
-- ('123', 'Nguyen', 'Do6', '+84968208207', '12345678', '21.007522', '105.816696'),
-- ('123', 'Nguyen', 'Do7', '+84968208208', '12345678', '21.040937', '105.827234'),
-- ('123', 'Nguyen', 'Do8', '+84968208209', '12345678', '21.026638', '105.785136'),
-- ('123', 'Nguyen', 'Do9', '+84968208210', '12345678', '20.994812', '105.795683'),
-- ('123', 'Nguyen', 'Do0', '+84968208211', '12345678', '20.969677', '105.819119'),
-- -- Begin 11
-- ('123', 'Nguyen', 'Thanh1', '+84968208212', '12345678', '20.962746', '105.864108'),
-- ('123', 'Nguyen', 'Thanh2', '+84968208213', '12345678', '20.971871', '105.891723'),
-- ('123', 'Nguyen', 'Thanh3', '+84968208214', '12345678', '21.026357', '105.771643'),
-- ('123', 'Nguyen', 'Thanh4', '+84968208215', '12345678', '21.090558', '105.780430'),
-- ('123', 'Nguyen', 'Thanh5', '+84968208216', '12345678', '21.091852', '105.782830'),
-- -- Begin 16
-- ('123', 'Nguyen', 'Thanh6', '+84968208217', '12345678', '21.098558', '105.782935'),
-- ('123', 'Nguyen', 'Thanh7', '+84968208218', '12345678', '21.090956', '105.780533'),
-- ('123', 'Nguyen', 'Thanh8', '+84968208219', '12345678', '21.092558', '105.780231'),
-- ('123', 'Nguyen', 'Thanh9', '+84968208220', '12345678', '21.096579', '105.780850'),
-- ('123', 'Nguyen', 'Thanh0', '+84968208221', '12345678', '21.077709', '105.822836');

-- Hoan thien du thong tin
-- INSERT INTO Users(fcmToken, state, firstName, lastName, phoneNumber, password, address, lat, lng) VALUES
-- ('123',  1, 'Nguyen', 'Do', '+84968208244', '12345678', '16 Le Quy Don, Hai Ba Trung, Ha Noi', '21.015465', '105.855834');