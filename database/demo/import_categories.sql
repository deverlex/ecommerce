INSERT INTO Categories (`category`, `coverPicture`, `lastUpdatedBy`)
VALUES
("GASWATER","url", 1),
("HOTEL", "url", 1),
("GAS", "url", 1),
("WATER", "url", 1),
("BOOKHOTEL", "url", 1),
("CALLGAS", "url", 1),
("GASTOVE", "url", 1);

INSERT INTO SubCategories (`subCategory`, `refCategory`, `refLevel`, `isNext`, `lastUpdatedBy`)
VALUES
("GAS", "GASWATER", 1, 1, 1),
("WATER", "GASWATER", 1, 1, 1),
("BOOKHOTEL", "HOTEL", 1, 1, 1),
("CALLGAS", "GASWATER", 1, 0, 1),
("CALLGAS", "GAS", 2, 1, 1),
("GASTOVE", "GASWATER", 1, 0, 1),
("GASTOVE", "GAS", 2, 1, 1);
