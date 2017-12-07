package vn.needy.ecommerce.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import vn.needy.ecommerce.repository.AttributeRepository;

import java.util.LinkedList;
import java.util.List;

@Repository("attributeRepository")
public class AttributeRepositoryImpl implements AttributeRepository {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<String> getListAttributeCategory(String category) {
        SqlRowSet rs = jdbc.queryForRowSet("select attr.name " +
                "from category_attribute cat " +
                "inner join attribute attr on attr.name = cat.attribute_name " +
                "where cat.category_name = ?", new Object[] {category});
        List<String> attribues = new LinkedList<>();
        while (rs.next()) {
            attribues.add(rs.getString("name"));
        }
        return attribues;
    }
}
