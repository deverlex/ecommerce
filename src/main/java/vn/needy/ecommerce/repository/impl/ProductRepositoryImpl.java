package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.Product;
import vn.needy.ecommerce.domain.mongo.ProductDetail;
import vn.needy.ecommerce.repository.ProductRepository;

@Repository("productRepository")
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	JdbcTemplate jdbc;
	
	private SimpleJdbcInsert insert;
	
	@Autowired
	MongoTemplate mongo;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(Product.TABLE)
        		.usingGeneratedKeyColumns("id");
    }
	
	@Override
	public long addProduct(Product product, ProductDetail productDetail) {
		Map<String, Object> params = new HashMap<>();
		params.put("category_name", product.getCategory());
		params.put("company_id", product.getCompanyId());
		params.put("state", product.getState());
		params.put("last_updated_by", product.getLastUpdatedBy());
		Number productId = insert.executeAndReturnKey(params);
		
		// Insert product into mongodb
		productDetail.setProductId(productId.longValue());
		mongo.insert(productDetail);
		return productId.longValue();
	}
	
}
