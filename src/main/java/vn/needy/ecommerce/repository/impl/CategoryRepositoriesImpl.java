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
		SqlRowSet rs = jdbc.queryForRowSet("select category_name " +
						"from link_category " +
						"where reference_name = ? " +
						"and is_next = 1",
					new Object[] {preCategory}); 
	    List<Category> categories = new LinkedList<>(); 
	    while(rs.next()) {
	      Category category = new Category();
	      category.setName(rs.getString("category_name"));
	      categories.add(category);
	    }
	    return categories;
	}

	@Override
	public List<Category> getCompanyLinkCategories(long companyId, String category) {
		SqlRowSet rs = jdbc.queryForRowSet("select rt.category_name from " +
				"(" +
				"	select distinct lc.reference_name as reference_name from company c" +
				"	inner join product p on p.company_id = c.id" +
				"	inner join link_category lc on lc.category_name = p.category_name" +
				"	where c.id = ?" +
				") as ct " +
				"inner join " +
				"(" +
				"	select distinct category_name" +
				"   from link_category" +
				"   where reference_name = ? and is_next = 1" +
				") as rt " +
				"on rt.category_name = ct.reference_name", new Object[] {companyId, category});
		List<Category> categories = new LinkedList<>();
		while(rs.next()) {
			Category cat = new Category();
			cat.setName(rs.getString("category_name"));
			categories.add(cat);
		}
		return categories;
	}

}
