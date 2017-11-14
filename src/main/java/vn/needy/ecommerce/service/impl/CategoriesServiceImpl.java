package vn.needy.ecommerce.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.domain.entity.Category;
import vn.needy.ecommerce.model.json.entity.CategoryJson;
import vn.needy.ecommerce.model.json.response.CategoriesResponse;
import vn.needy.ecommerce.repository.CategoryRepository;
import vn.needy.ecommerce.service.CategoriesService;

@Service("categoriesService")
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	CategoryRepository categoriesRepository;
	
	@Override
	public CategoriesResponse getProductCategories() {
		List<CategoryJson> categoriesJson = new LinkedList<>();
		List<Category> categories = categoriesRepository.getProductCategories();
		for(Category category : categories) {
			categoriesJson.add(new CategoryJson(category));
		}
		CategoriesResponse response = new CategoriesResponse();
		response.setCategories(categoriesJson);
		return response;
	}

	@Override
	public CategoriesResponse getProductSubCategory(String category) {
		List<CategoryJson> categoriesJson = new LinkedList<>();
		List<Category> categories = categoriesRepository.getProductSubCategory(category);
		for(Category subCategories : categories) {
			categoriesJson.add(new CategoryJson(subCategories));
		}
		CategoriesResponse response = new CategoriesResponse();
		response.setCategories(categoriesJson);
		return response;
	}

	@Override
	public CategoriesResponse getCompanyProductCategory(long companyId) {
		List<CategoryJson> categoriesJson = new LinkedList<>();
		List<Category> categories = categoriesRepository.getCompanyProductCategory(companyId);
		for(Category subCategories : categories) {
			categoriesJson.add(new CategoryJson(subCategories));
		}
		CategoriesResponse response = new CategoriesResponse();
		response.setCategories(categoriesJson);
		return response;
	}

	@Override
	public CategoriesResponse getCompanyProductSubCategory(long companyId, String category) {
		List<CategoryJson> categoriesJson = new LinkedList<>();
		List<Category> categories = categoriesRepository.getCompanyProductSubCategory(companyId, category);
		for(Category subCategories : categories) {
			categoriesJson.add(new CategoryJson(subCategories));
		}
		CategoriesResponse response = new CategoriesResponse();
		response.setCategories(categoriesJson);
		return response;
	}

}
