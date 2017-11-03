INSERT INTO Categories (`category`, `title`, `coverPicture`, `description`, `lastUpdatedBy`)
VALUES
("GASWATER", "Ga và nước", "url", "none", 1),
("HOTEL", "Nhà nghỉ - khách sạn", "url", "none", 1),
("GAS", "Gọi Gas", "url", "none", 1),
("WATER", "Gọi nước", "url", "none", 1),
("BOOKHOTEL", "Đặt phòng nhà nghỉ", "url", "none", 1);

INSERT INTO SubCategories (`subCategory`, `refCategory`, `lastUpdatedBy`)
VALUES
("GAS", "GASWATER", 1),
("WATER", "GASWATER", 1),
("BOOKHOTEL", "HOTEL", 1);
