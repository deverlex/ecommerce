package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.base.BaseDomain;

public class Product extends BaseDomain {

	private static final long serialVersionUID = 137262525L;
	
	public static final String TABLE = "product";

	private long id;
	private String category;
	private long companyId;
	private int state;

	private String name;
	private boolean hasGift;
	private float price;
	private float oldPrice;
	private long refProduct;

	private Date lastUpdatedTime;
	private long lastUpdatedBy;

	public Product() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasGift() {
        return hasGift;
    }

    public void setHasGift(boolean hasGift) {
        this.hasGift = hasGift;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(float oldPrice) {
        this.oldPrice = oldPrice;
    }

    public long getRefProduct() {
        return refProduct;
    }

    public void setRefProduct(long refProduct) {
        this.refProduct = refProduct;
    }

    public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
}
