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
	public List<Category> getLinkCategories(String preCategory) {
		SqlRowSet rs = jdbc.queryForRowSet("select lc.category_name " +
						"	from category cat " +
						"	inner join link_category lc on lc.reference_name = cat.name " +
						"	inner join category sca on lc.category_name = sca.name " +
						"where cat.name = ? and cat.enable = 1 " +
						"and lc.is_next = 1 and sca.enable = 1",
					new Object[] {preCategory}); 
	    List<Category> categories = new LinkedList<>(); 
	    while(rs.next()) {
	      Category subCategory = new Category(); 
	      subCategory.setName(rs.getString("category_name"));
	      categories.add(subCategory); 
	    }
	    return categories;
	}

	@Override
	public List<Category> getCompanyLinkCategories(long companyId, String category) {
		SqlRowSet rs = jdbc.queryForRowSet("select cat.name from (" +
				"	select distinct lc.reference_name as reference_name from company c" +
				"	inner join product p on p.company_id = c.id" +
				"	inner join category cat on cat.name = p.category_name" +
				"	inner join link_category lc on lc.category_name = cat.name" +
				"	where c.id = ? and cat.enable = 1" +
				") as ct " +
				"inner join " +
				"(" +
				"	select distinct lc.category_name as category_name, lc.is_next as is_next " +
				"	from category cat" +
				"   inner join link_category lc on lc.reference_name = cat.name" +
				"   where cat.name = ? and cat.enable = 1" +
				") as rt " +
				"on ct.reference_name = rt.category_name " +
				"inner join category cat on rt.category_name = cat.name " +
				"where rt.is_next = 1 and cat.enable = 1", new Object[] {companyId, category});
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category cat = new Category();
			cat.setName(rs.getString("name"));
			categories.add(cat);
		}
		return categories;
	}

}
