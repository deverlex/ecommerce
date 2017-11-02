package vn.needy.ecommerce.domain.entity;

import vn.needy.ecommerce.domain.BaseDomain;

public class ProductCompany extends BaseDomain {

	private static final long serialVersionUID = 129374247L;
//	`id` bigint(20) AUTO_INCREMENT PRIMARY KEY,
//	  `productId` bigint(20) NOT NULL,
//	  `companyId` bigint(20) NOT NULL,
//	  -- So luong con trong kho (Dich vu thi k can bang nay)
//	  `quantity` smallint(5) NOT NULL,
//	  -- Gia ban
//	  `price` float(12, 2) NOT NULL,
//	  `prePrice` float(12, 2) NOT NULL,
//	  -- Khuyen mai JSON - 5 k/m
//	  `promotion` text(600),
//	  -- Mo ta san pham
//	  `description` text(1000),
//	  `image` varchar(255),
//	  -- Save JSON format - 5 image
//	  `pictures` text(1281),
//	  `lastUpdatedTime` timestamp DEFAULT CURRENT_TIMESTAMP,
//	  `lastUpdatedBy` bigint(20) NOT NULL,
	private long id;
	private long productId;
	private long companyId;
	private int quantity;
	private float price;
	private float prePrice;
	
	private String promotion;
	private String description;
	private String image;
	private String pictures;
	
	

	public ProductCompany() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
