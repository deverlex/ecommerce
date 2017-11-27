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
				"left join sub_category sc on c.name = sc.subcategory_name " +
				"where sc.refcategory_name = ? and c.enable = 1 and sc.is_next = 1", new Object[] {PRICE_NOW});
		
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category category = new Category();
			category.setCategory(rs.getString("name"));
			categories.add(category);
		}
		return categories;
	}

	@Override
	public List<Category> getSubCategories(String preCategory) {
		SqlRowSet rs = jdbc.queryForRowSet("select c.* from category c " + 
				"left join sub_category sc on c.name = sc.subcategory_name " +
				"where sc.refcategory_name = ? and sc.is_next = 1 and c.enable = 1",
					new Object[] {preCategory}); 
	    List<Category> categories = new LinkedList<>(); 
	    while(rs.next()) {
	      Category subCategory = new Category(); 
	      subCategory.setCategory(rs.getString("name"));
	      categories.add(subCategory); 
	    }
	    return categories;
	}

	@Override
	public List<Category> getCompanyCategoriesPriceNow(long companyId) {
		SqlRowSet rs = jdbc.queryForRowSet("select distinct sc1.refcategory_name as refcategory from company com " +
				"inner join product pr on pr.company_id = com.id " +
				"inner join category cts on cts.name = pr.category_name " +
				"inner join sub_category sc1 on sc1.subcategory_name = cts.name " +
				"left join sub_category sc2 on sc2.subcategory_name = sc1.refcategory_name " +
				"where com.id = ? and sc1.ref_level = 1 and sc2.refcategory_name = ?",
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
		SqlRowSet rs = jdbc.queryForRowSet("select * from category ct " +
				"inner join " +
				"(" + 
				"	select distinct sct.* from sub_category sct " +
				"	where sct.subcategory_id in " +
				"	(" + 
				"		select distinct sc.subcategory_name from sub_category sc " +
				"		where sc.refcategory_name = ? and sc.is_next = true " +
				"   )" + 
				"	and sct.refcategory_name in " +
				"	(" + 
				"		select distinct sc1.refcategory_name from company com " +
				"		inner join product pr on pr.company_id = com.id " +
				"		inner join category cts on cts.name = pr.category_id " +
				"		inner join sub_category sc1 on sc1.subcategory_name = cts.name " +
				"		left join sub_category sc2 on sc2.subcategory_name = sc1.refcategory_name " +
				"		where com.id = ? and sc1.ref_level = 1 and sc2.refcategory_name = ? " +
				"	)" + 
				") as sub " +
				"on ct.name = sub.subcategory_name " +
				"where ct.enable = true",
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
