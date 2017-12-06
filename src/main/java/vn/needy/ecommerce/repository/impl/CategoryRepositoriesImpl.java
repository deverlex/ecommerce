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
	public List<String> getLinkCategories(String preCategory) {
		SqlRowSet rs = jdbc.queryForRowSet("select category_name " +
						"from link_category " +
						"where reference_name = ? " +
						"and is_next = 1",
					new Object[] {preCategory});
		List<String> categories = new LinkedList<>();
		while(rs.next()) {
			categories.add(rs.getString("category_name"));
		}
		return categories;
	}

	@Override
	public List<String> getCompanyLinkCategories(long companyId, String category) {
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
		List<String> categories = new LinkedList<>();
		while(rs.next()) {
			categories.add(rs.getString("category_name"));
		}
		return categories;
	}

	@Override
	public List<String> getParentCategories(String preCategory) {
		SqlRowSet rs = jdbc.queryForRowSet("select distinct lc3.* from link_category lc1 " +
				"inner join link_category lc2 on lc2.category_name = lc1.reference_name " +
				"inner join category cat on cat.name = lc2.reference_name " +
				"inner join link_category lc3 on lc3.reference_name = cat.name " +
				"where lc1.category_name = ? and lc1.is_next = 1 " +
				"and lc2.is_next = 1 and lc3.is_next = 1", new Object[] {preCategory});
		List<String> categories = new LinkedList<>();
		while(rs.next()) {
			categories.add(rs.getString("category_name"));
		}
		return categories;
	}

}
