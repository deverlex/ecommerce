package vn.needy.ecommerce.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.Category;
import vn.needy.ecommerce.repository.CategoryRepository;

@Repository("categoryRepository")
public class CategoryRepositoriesImpl implements CategoryRepository {

	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public List<Category> getProductCategories() {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM category c " + 
				"LEFT JOIN SubCategories sc ON c.category = sc.subCategory " + 
				"WHERE sc.refCategory IS NULL AND enable = true AND isPriceLater = false");
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category category = new Category();
			category.setCategory(rs.getString("category"));
			category.setCoverPicture(rs.getString("coverPicture"));
			categories.add(category);
		}
		return categories;
	}

	@Override
	public List<Category> getProductSubCategory(String category) {
		// TODO Auto-generated method stub
		SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM Categories c " + 
				"LEFT JOIN SubCategories sc ON c.category = sc.subCategory " + 
				"WHERE sc.refCategory = ? AND sc.isNext = true " + 
				"AND c.enable = true AND c.isPriceLater = false", new Object[] {category});
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category subCategory = new Category();
			subCategory.setCategory(rs.getString("category"));
			subCategory.setCoverPicture(rs.getString("coverPicture"));
			categories.add(subCategory);
		}
		return categories;
	}

	@Override
	public List<Category> getCompanyProductCategory(long companyId) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM Categories c WHERE c.category = " + 
				"(SELECT DISTINCT scts.refCategory FROM Companies com " + 
				"INNER JOIN ProductCompany pcom ON pcom.companyId = com.id " + 
				"INNER JOIN Products prd ON prd.id = pcom.productId " + 
				"INNER JOIN Categories cts ON cts.category = prd.category " + 
				"INNER JOIN SubCategories scts ON scts.subCategory = cts.category " + 
				"WHERE com.id = ? AND scts.refLevel = 1) " + 
				"AND c.enable = true AND c.isPriceLater = false", new Object[] {companyId});
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category category = new Category();
			category.setCategory(rs.getString("category"));
			category.setCoverPicture(rs.getString("coverPicture"));
			categories.add(category);
		}
		return categories;
	}

	@Override
	public List<Category> getCompanyProductSubCategory(long companyId, String category) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM Categories cts " + 
				"INNER JOIN " + 
				"(SELECT DISTINCT sct.* FROM SubCategories sct " + 
				"WHERE sct.subCategory IN " + 
				"(SELECT DISTINCT sc.subcategory FROM SubCategories sc " + 
				"WHERE sc.refCategory = ? AND sc.isNext = true) " + 
				"AND sct.refCategory IN " + 
				"(" + 
				"	SELECT c.category FROM Categories c WHERE c.category IN " + 
				"	(SELECT DISTINCT scts.refCategory FROM Companies com " + 
				"	INNER JOIN ProductCompany pcom ON pcom.companyId = com.id " + 
				"	INNER JOIN Products prd ON prd.id = pcom.productId " + 
				"	INNER JOIN Categories cts ON cts.category = prd.category " + 
				"	INNER JOIN SubCategories scts ON scts.subCategory = cts.category " + 
				"	WHERE com.id = ? AND scts.refLevel = 1) " + 
				"	AND c.enable = true AND c.isPriceLater = false " + 
				")) as Sub " + 
				"ON cts.category = Sub.subCategory " + 
				"WHERE cts.enable = true AND cts.isPriceLater = false", new Object[] {category, companyId});
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category subCategory = new Category();
			subCategory.setCategory(rs.getString("category"));
			subCategory.setCoverPicture(rs.getString("coverPicture"));
			categories.add(subCategory);
		}
		return categories;
	}
	
	
	@Override
	public List<Category> getServiceCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}
