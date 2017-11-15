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

	private static final String PRICE_NOW = "price_now";
	//private static final String PRICE_LATER ="price_later";
	
	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public List<Category> getCategoriesPriceNow() {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT * "
				+ "FROM sub_category sc "
				+ "INNER JOIN category c ON c.category_id = sc.category_id "
				+ "WHERE sc.pre_category = ? AND c.enable = ?  ", new Object[] {PRICE_NOW});
		
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category category = new Category();
			category.setCategory(rs.getString("category"));
			categories.add(category);
		}
		return categories;
	}

	@Override
	public List<Category> getSubCategoriesPriceNow(String preCategory) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT * "
				+ "FROM sub_category sc "
				+ "INNER JOIN category c ON c.category_id = sc.category_id "
				+ "WHERE sc.pre_category = ? AND c.enable = ?  ", new Object[] {preCategory});
		
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category category = new Category();
			category.setCategory(rs.getString("category"));
			categories.add(category);
		}
		return categories;
	}

	@Override
	public List<Category> getCompanyCategoriesPriceNow(long companyId) {
//		SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM Categories c WHERE c.category = " + 
//				"(SELECT DISTINCT scts.refCategory FROM Companies com " + 
//				"INNER JOIN ProductCompany pcom ON pcom.companyId = com.id " + 
//				"INNER JOIN Products prd ON prd.id = pcom.productId " + 
//				"INNER JOIN Categories cts ON cts.category = prd.category " + 
//				"INNER JOIN SubCategories scts ON scts.subCategory = cts.category " + 
//				"WHERE com.id = ? AND scts.refLevel = 1) " + 
//				"AND c.enable = true AND c.isPriceLater = false", new Object[] {companyId});
//		List<Category> categories = new LinkedList<>();
//		while(rs.next()) {
//			Category category = new Category();
//			category.setCategory(rs.getString("category"));
//			categories.add(category);
//		}
		return null;
	}

	@Override
	public List<Category> getCompanySubCategoriesPriceNow(long companyId, String category) {
		SqlRowSet rs = jdbc.queryForRowSet("");
//		SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM Categories cts " + 
//				"INNER JOIN " + 
//				"(SELECT DISTINCT sct.* FROM SubCategories sct " + 
//				"WHERE sct.subCategory IN " + 
//				"(SELECT DISTINCT sc.subcategory FROM SubCategories sc " + 
//				"WHERE sc.refCategory = ? AND sc.isNext = true) " + 
//				"AND sct.refCategory IN " + 
//				"(" + 
//				"	SELECT c.category FROM Categories c WHERE c.category IN " + 
//				"	(SELECT DISTINCT scts.refCategory FROM Companies com " + 
//				"	INNER JOIN ProductCompany pcom ON pcom.companyId = com.id " + 
//				"	INNER JOIN Products prd ON prd.id = pcom.productId " + 
//				"	INNER JOIN Categories cts ON cts.category = prd.category " + 
//				"	INNER JOIN SubCategories scts ON scts.subCategory = cts.category " + 
//				"	WHERE com.id = ? AND scts.refLevel = 1) " + 
//				"	AND c.enable = true AND c.isPriceLater = false " + 
//				")) as Sub " + 
//				"ON cts.category = Sub.subCategory " + 
//				"WHERE cts.enable = true AND cts.isPriceLater = false", new Object[] {category, companyId});
//		List<Category> categories = new LinkedList<>();
//		while(rs.next()) {
//			Category subCategory = new Category();
//			subCategory.setCategory(rs.getString("category"));
//			categories.add(subCategory);
//		}
		return null;
	}
	
	
	@Override
	public List<Category> getCategoriesPriceLater() {
		// TODO Auto-generated method stub
		return null;
	}

}
