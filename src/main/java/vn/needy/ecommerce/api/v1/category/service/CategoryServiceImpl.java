package vn.needy.ecommerce.api.v1.category.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.domain.entity.Category;
import vn.needy.ecommerce.model.json.CategoryJson;
import vn.needy.ecommerce.api.v1.category.response.ListCategoryResponse;
import vn.needy.ecommerce.repository.CategoryRepository;

@Service("categoriesService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoriesRepository;
	
	@Override
	public ListCategoryResponse getCategoriesPriceNow() {
		List<CategoryJson> categoriesJson = new LinkedList<>();
		List<Category> categories = categoriesRepository.getCategoriesPriceNow();
		for(Category category : categories) {
			categoriesJson.add(new CategoryJson(category));
		}
		ListCategoryResponse response = new ListCategoryResponse();
		response.setCategories(categoriesJson);
		return response;
	}

	@Override
	public ListCategoryResponse getSubCategoriesPriceNow(String category) {
		List<CategoryJson> categoriesJson = new LinkedList<>();
		List<Category> categories = categoriesRepository.getSubCategories(category);
		for(Category subCategories : categories) {
			categoriesJson.add(new CategoryJson(subCategories));
		}
		ListCategoryResponse response = new ListCategoryResponse();
		response.setCategories(categoriesJson);
		return response;
	}

	@Override
	public ListCategoryResponse getCompanyCategoriesPriceNow(long companyId) {
		List<CategoryJson> categoriesJson = new LinkedList<>();
		List<Category> categories = categoriesRepository.getCompanyCategoriesPriceNow(companyId);
		for(Category subCategories : categories) {
			categoriesJson.add(new CategoryJson(subCategories));
		}
		ListCategoryResponse response = new ListCategoryResponse();
		response.setCategories(categoriesJson);
		return response;
	}

	@Override
	public ListCategoryResponse getCompanySubCategoriesPriceNow(long companyId, String category) {
		List<CategoryJson> categoriesJson = new LinkedList<>();
		List<Category> categories = categoriesRepository.getCompanySubCategoriesPriceNow(companyId, category);
		for(Category subCategories : categories) {
			categoriesJson.add(new CategoryJson(subCategories));
		}
		ListCategoryResponse response = new ListCategoryResponse();
		response.setCategories(categoriesJson);
		return response;
	}

}
