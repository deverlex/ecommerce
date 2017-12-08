package vn.needy.ecommerce.api.v1.category.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.model.json.CategoryJson;
import vn.needy.ecommerce.api.v1.category.response.ListCategoryResponse;
import vn.needy.ecommerce.repository.CategoryRepository;

@Service("categoriesService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoriesRepository;

	@Override
	public ListCategoryResponse getLinkCategories(String category) {
		List<CategoryJson> categoriesJson = new LinkedList<>();
		List<String> categories = categoriesRepository.getLinkCategories(category);
		for(String cat : categories) {
			categoriesJson.add(new CategoryJson(cat));
		}
		ListCategoryResponse response = new ListCategoryResponse();
		response.setCategories(categoriesJson);
		return response;
	}

	@Override
	public ListCategoryResponse getCompanyLinkCategories(long companyId, String category) {
		List<CategoryJson> categoriesJson = new LinkedList<>();
		List<String> categories = categoriesRepository.getCompanyLinkCategories(companyId, category);
		for(String cat : categories) {
			categoriesJson.add(new CategoryJson(cat));
		}
		ListCategoryResponse response = new ListCategoryResponse();
		response.setCategories(categoriesJson);
		return response;
	}

}
