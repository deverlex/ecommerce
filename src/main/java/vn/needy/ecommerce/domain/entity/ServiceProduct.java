package vn.needy.ecommerce.domain.entity;

import vn.needy.ecommerce.domain.BaseDomain;

public class ServiceProduct extends BaseDomain {

	private static final long serialVersionUID = 19393837L;
	
	private long id;
	private long serviceId;
	private long productId;

	public ServiceProduct() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
	
}
