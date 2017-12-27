package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.mysql.Product;
import vn.needy.ecommerce.domain.mongo.ProductDetail;
import vn.needy.ecommerce.model.wrapper.ProductWrapper;
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
	public long addProduct(long userId, long storeId, long companyId, Product product, ProductDetail productDetail) {


		Map<String, Object> params = new HashMap<>();
		params.put("category_name", product.getCategory());
		params.put("company_id", product.getCompanyId());
		params.put("state", product.getState());
		params.put("name", product.getName());
		params.put("price", product.getPrice());
		params.put("last_updated_by", product.getLastUpdatedBy());
		Number productId = insert.executeAndReturnKey(params);
		
		// Insert product into mongodb
		productDetail.setProductId(productId.longValue());
		mongo.insert(productDetail);
		return productId.longValue();
	}

	@Override
	public List<Product> getAllProductOfCompany(long userId, long companyId) {
		// Check user is staff of company
		SqlRowSet rs = jdbc.queryForRowSet("select product.* from product " +
				"inner join company_staff on company_staff.company_id = ? " +
				"where product.company_id = ? and company_staff.user_id = ?",
				companyId,
				companyId,
				userId);
		List<Product> products = new LinkedList<>();
		while (rs.next()) {
			Product product = new Product();
			product.setId(rs.getLong("id"));
			product.setCategory(rs.getString("category_name"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getFloat("price"));
			products.add(product);
		}

		return products;
	}

}
