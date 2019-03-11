package com.techm.po.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techm.po.model.dto.AccountCategoryDTO;
import com.techm.po.service.AccountCategoryService;

/**
 * 
 * @author Sharath Kumar
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/account_category")
public class AccountCategoryController {

	@Autowired
	private AccountCategoryService accountCategoryService;

	
	/**
	 * This API is for adding Account Category
	 * @param categoryDto of type {@link AccountCategoryDTO}
	 * @return success message as output along with status code.
	 */
	@PostMapping("/create")
	public Map<String, Object> createAccountCategory(@RequestBody AccountCategoryDTO categoryDto) {
		Map<String, Object> response;
		response = accountCategoryService.addAccountCategoryDetails(categoryDto);
		return response;
	}
	
	
	/**
	 * This API is for fetching Account Category
	 * @param accountCategory of type {@link String}
	 * @param accountName of type {@link String}
	 * @return list of Account categories
	 */
	@GetMapping("/fetch")
	
	public Map<String, Object> getAccountCategoryData(@RequestParam(name = "accountCategory", required = false) String accountCategory,
			@RequestParam(name = "accountName", required = false) String accountName) {
		Map<String, Object> response;
		response = accountCategoryService.fetchAccountCategoryDetails(accountCategory, accountName);
		return response;
	}
	
	
	/**
	 * This API is for deleting Account Category
	 * @param id of type {@link String}, Unique Id of the Account Category
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> deleteAccountCategory(@RequestParam(name = "accountCategoryId")String id) {
		Map<String, Object> response;
		response = accountCategoryService.deleteAccountCategory(id);
		return response;	
	}
	
	
	/**
	 * This API is for Modifying Account Category (Only Account Name can be updated)
	 * @param categoryDto of type {@link AccountCategoryDTO}
	 * @return success message as response along with the status code
	 */
	@PutMapping("/update")
	public Map<String, Object> modifyAccountCategory(@RequestBody AccountCategoryDTO categoryDto) {
		Map<String, Object> response;
		response = accountCategoryService.modifyAccountCategory(categoryDto);
		return response;
	}
	
	
	/**
	 * This API is for fetching Account Categories
	 * @return List of Account Categories
	 */
	@GetMapping("/categories")
	public Map<String, Object> fetchAccountCategories(){
		Map<String, Object> response;
		response = accountCategoryService.fetchAccountCategories();
		return response;
	}
	
	
	/**
	 * This API is for Fetching Account Names based on the Account Category
	 * @param accountCategory of type {@link} String 
	 * @return list of account names
	 */
	@GetMapping("/category/names")
	public Map<String, Object> fetchAccountCategoryNames(@RequestParam(name="accountCategory", required=true) String accountCategory){
		Map<String, Object> response;
		response = accountCategoryService.fetchAccountNames(accountCategory);
		return response;
	}
	
	
	/**
	 * This API is for fetching specific record of the Account category based on the ID
	 * @param id of type {@link String}, unique Id of the Account category
	 * @return
	 */
	@GetMapping("/fetch/specific")
	public Map<String, Object> fetchCategoryDetails(@RequestParam("id") String id) {
		Map<String, Object> response;
		response = accountCategoryService.getAccountCategoryDatasingle(id);
		return response;
	}

}
