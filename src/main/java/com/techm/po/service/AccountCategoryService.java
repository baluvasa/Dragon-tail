package com.techm.po.service;

import java.util.Map;

import com.techm.po.model.dto.AccountCategoryDTO;

public interface AccountCategoryService {
	
	Map<String, Object> addAccountCategoryDetails(AccountCategoryDTO categoryDto);

	Map<String, Object> fetchAccountCategoryDetails(String accountCategory, String accountName);
	
	Map<String, Object> deleteAccountCategory(String accountCategoryId);
	
	Map<String, Object> modifyAccountCategory(AccountCategoryDTO categoryDto);
	
//	Map<String, Object> getAccountCategoryData(String id);
	
	Map<String, Object> getAccountCategoryDatasingle(String id);
	
	Map<String, Object> fetchAccountCategories();
	
	Map<String, Object> fetchAccountNames(String accountCategory);
	
}
