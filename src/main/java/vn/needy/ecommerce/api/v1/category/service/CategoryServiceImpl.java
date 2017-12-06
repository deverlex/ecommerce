package vn.needy.ecommerce.api.v1.category.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.domain.entity.Category;
import vn.needy.ecommerce.api.v1.category.json.CategoryJson;
import vn.needy.ecommerce.api.v1.category.response.ListCategoryResponse;
import vn.needy.ecommerce.repository.CategoryRepository;

@Service("categoriesService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoriesRepository;

	@Override
	public ListCategoryResponse getLinkCategories(String category) {
		List<CategoryJson> categoriesJson = new LinkedList<>();
		List<Category> categories = categoriesRepository.getLinkCategories(category);
		for(Category subCategories : categories) {
			categoriesJson.add(new CategoryJson(subCategories));
		}
		ListCategoryResponse response = new ListCategoryResponse();
		response.setCategories(categoriesJson);
		return response;
	}

	@Override
	public ListCategoryResponse getCompanyLinkCategories(long companyId, String category) {
		List<CategoryJson> categoriesJson = new LinkedList<>();
		List<Category> categories = categoriesRepository.getCompanyLinkCategories(companyId, category);
		for(Category subCategories : categories) {
			categoriesJson.add(new CategoryJson(subCategories));
		}
		ListCategoryResponse response = new ListCategoryResponse();
		response.setCategories(categoriesJson);
		return response;
	}

}
