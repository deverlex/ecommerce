package vn.needy.ecommerce.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.Category;
import vn.needy.ecommerce.repository.CategoriesRepository;

@Repository("categoriesRepository")
public class CategoriesRepositoriesImpl implements CategoriesRepository {

	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public List<Category> getProductCategories() {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM Categories c " + 
				"LEFT JOIN SubCategories sc ON c.category = sc.subCategory " + 
				"WHERE sc.refCategory IS NULL AND enable = true AND isService = false");
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category category = new Category();
			category.setCategory(rs.getString("category"));
			category.setTitle(rs.getString("title"));
			category.setCoverPicture(rs.getString("coverPicture"));
			categories.add(category);
		}
		return categories;
	}

	@Override
	public List<Category> getServiceCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}
