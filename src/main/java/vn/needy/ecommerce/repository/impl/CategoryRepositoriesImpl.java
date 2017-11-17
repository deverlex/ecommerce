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
		SqlRowSet rs = jdbc.queryForRowSet("select c.* from category c  " + 
				"left join sub_category sc on c.category_id = sc.subcategory_id " + 
				"where sc.refcategory_id = ? and c.enable = 1 and sc.is_next = 1", new Object[] {PRICE_NOW}); 
		
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category category = new Category();
			category.setCategory(rs.getString("category_id"));
			categories.add(category);
		}
		return categories;
	}

	@Override
	public List<Category> getSubCategories(String preCategory) {
		SqlRowSet rs = jdbc.queryForRowSet("select c.* from category c " + 
				"left join sub_category sc on c.category_id = sc.subcategory_id " + 
				"where sc.refcategory_id = ? and sc.is_next = 1 and c.enable = 1",
					new Object[] {preCategory}); 
	    List<Category> categories = new LinkedList<>(); 
	    while(rs.next()) {
	      Category subCategory = new Category(); 
	      subCategory.setCategory(rs.getString("category_id")); 
	      categories.add(subCategory); 
	    }
	    return categories;
	}

	@Override
	public List<Category> getCompanyCategoriesPriceNow(long companyId) {
		SqlRowSet rs = jdbc.queryForRowSet("select distinct sc1.refcategory_id as refcategory from company com " + 
				"inner join product pr on pr.company_id = com.company_id " + 
				"inner join category cts on cts.category_id = pr.category_id " + 
				"inner join sub_category sc1 on sc1.subcategory_id = cts.category_id " + 
				"left join sub_category sc2 on sc2.subcategory_id = sc1.refcategory_id " + 
				"where com.company_id = ? and sc1.ref_level = 1 and sc2.refcategory_id = ?", 
					new Object[] {companyId, PRICE_NOW});
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category category = new Category();
			category.setCategory(rs.getString("refcategory"));
			categories.add(category);
		}
		return categories;
	}

	@Override
	public List<Category> getCompanySubCategoriesPriceNow(long companyId, String category) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM category ct " + 
				"INNER JOIN " + 
				"(" + 
				"	SELECT DISTINCT sct.* FROM sub_category sct " + 
				"	WHERE sct.subcategory_id IN " + 
				"	(" + 
				"		SELECT DISTINCT sc.subcategory_id FROM sub_category sc " + 
				"		WHERE sc.refcategory_id = ? AND sc.is_next = true " + 
				"   )" + 
				"	AND sct.refcategory_id IN " + 
				"	(" + 
				"		select distinct sc1.refcategory_id from company com " + 
				"		inner join product pr on pr.company_id = com.company_id " + 
				"		inner join category cts on cts.category_id = pr.category_id " + 
				"		inner join sub_category sc1 on sc1.subcategory_id = cts.category_id " + 
				"		left join sub_category sc2 on sc2.subcategory_id = sc1.refcategory_id " + 
				"		where com.company_id = ? and sc1.ref_level = 1 and sc2.refcategory_id = ? " + 
				"	)" + 
				") as Sub " + 
				"ON ct.category_id = Sub.subcategory_id " + 
				"WHERE ct.enable = true",
			new Object[] {category, companyId, PRICE_NOW});
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category subCategory = new Category();
			subCategory.setCategory(rs.getString("category_id"));
			categories.add(subCategory);
		}
		return categories;
	}
	
	
	@Override
	public List<Category> getCategoriesPriceLater() {
		
		return null;
	}

}
