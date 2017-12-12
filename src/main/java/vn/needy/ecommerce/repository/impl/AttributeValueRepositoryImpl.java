package vn.needy.ecommerce.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import vn.needy.ecommerce.domain.mongo.AttributeValue;
import vn.needy.ecommerce.repository.AttributeValueRepository;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Repository("attributeValueRepository")
public class AttributeValueRepositoryImpl implements AttributeValueRepository {

    @Autowired
    private MongoTemplate mongo;

    @Override
    public List<AttributeValue> getListAttributeValue(String category, List<String> attributes) {
        Query query = new Query(Criteria.where("category").is(category).and("attribute").in(attributes));

        return mongo.find(query, AttributeValue.class);
    }
}
