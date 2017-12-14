package vn.needy.ecommerce.api.v1.category.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.api.base.BaseCode;
import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.base.BaseStatus;
import vn.needy.ecommerce.model.wrapper.CategoryWrapper;
import vn.needy.ecommerce.api.v1.category.response.ListCategoryResp;
import vn.needy.ecommerce.repository.CategoryRepository;

@Service("categoriesService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoriesRepository;

	@Override
	public BaseResponse getLinkCategories(String category) {
		List<CategoryWrapper> categoriesJson = new LinkedList<>();
		List<String> categories = categoriesRepository.getLinkCategories(category);
		for(String cat : categories) {
			categoriesJson.add(new CategoryWrapper(cat));
		}
		return new BaseResponse<ListCategoryResp>(BaseStatus.OK, BaseCode.OK, "")
				.setData(new ListCategoryResp(categoriesJson));
	}

	@Override
	public BaseResponse getCompanyLinkCategories(long companyId, String category) {
		List<CategoryWrapper> categoriesJson = new LinkedList<>();
		List<String> categories = categoriesRepository.getCompanyLinkCategories(companyId, category);
		for(String cat : categories) {
			categoriesJson.add(new CategoryWrapper(cat));
		}
		return new BaseResponse<ListCategoryResp>(BaseStatus.OK, BaseCode.OK, "")
				.setData(new ListCategoryResp(categoriesJson));
	}

}
