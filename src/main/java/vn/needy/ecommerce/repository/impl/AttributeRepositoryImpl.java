package vn.needy.ecommerce.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import vn.needy.ecommerce.domain.mysql.Attribute;
import vn.needy.ecommerce.repository.AttributeRepository;

import java.util.LinkedList;
import java.util.List;

@Repository("attributeRepository")
public class AttributeRepositoryImpl implements AttributeRepository {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Attribute> getListAttributeCategory(String category) {
        SqlRowSet rs = jdbc.queryForRowSet("select attr.name, attr.data_type " +
                "from category_attribute cat " +
                "inner join attribute attr on attr.name = cat.attribute_name " +
                "where cat.category_name = ?", new Object[] {category});
        List<Attribute> attributes = new LinkedList<>();
        while (rs.next()) {
            Attribute attribute = new Attribute(
                    rs.getString("name"),
                    rs.getInt("data_type"));
            attributes.add(attribute);
        }

        return attributes;
    }
}
