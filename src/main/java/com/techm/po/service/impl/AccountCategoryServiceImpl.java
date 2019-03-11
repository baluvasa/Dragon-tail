package com.techm.po.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.techm.po.dao.AccountCategoryRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.exception.ResourceNotFoundException;
import com.techm.po.model.bo.DefaultEnums;
import com.techm.po.model.dto.AccountCategoryDTO;
import com.techm.po.service.AccountCategoryService;

/**
 * 
 * @author Sharath Kumar
 *
 */
@Service
public class AccountCategoryServiceImpl implements AccountCategoryService {

	@Autowired
	private AccountCategoryRepository accountCategoryRepository;

	/**
	 * This method implementation is for Adding Account category details.
	 * accountCategory, accountName, createdBy are input parameters
	 */
	@Override
	public Map<String, Object> addAccountCategoryDetails(AccountCategoryDTO categoryDto) {
		Optional<AccountCategoryDTO> accountCategoryData;
		Map<String, Object> response;
		response = new HashMap<>();
		String id;
		accountCategoryData = accountCategoryRepository.fetchByNameAndCategory( categoryDto.getAccountCategory().toUpperCase( ),
				categoryDto.getAccountName().toUpperCase( ));
		if (accountCategoryData.isPresent()) {
			response.put("message", "Account Category details alredy exists for the given category and name.");
			response.put("status", HttpStatus.CONFLICT.value());			
		} else {
			if (!StringUtils.isEmpty(categoryDto.getAccountName())
					&& !StringUtils.isEmpty(categoryDto.getAccountCategory())) {
				try {
					id = UUID.randomUUID().toString();
					categoryDto.setAccountCategory(categoryDto.getAccountCategory().toUpperCase());
					categoryDto.setAccountName(categoryDto.getAccountName().toUpperCase());
					categoryDto.setAccountCategoryId(id.substring(0, 7));
					categoryDto.setStatus(DefaultEnums.ACTIVE.name());
					categoryDto.setCreatedDate(LocalDateTime.now());
					accountCategoryRepository.save(categoryDto);
					response.put("message", "Account Category details created successfully");
					response.put("status", HttpStatus.CREATED.value());

				} catch (Exception e) {
					throw new InvalidServiceException("Exception occured while adding Account Category details.");
				}
			} else {
				throw new InvalidServiceException("Either account category (or) name should not be null.");
			}
		}
		return response;
	}

	/**
	 * This method is for fetching Account Category details If user has not given
	 * any input, All the records will be fetched from the db If any input from the
	 * user based on the input parameter data will be fetched
	 */
	@Override
	public Map<String, Object> fetchAccountCategoryDetails(String accountCategory, String accountName) {
		List<AccountCategoryDTO> accCatDtoList;
		Map<String, Object> response;
		response = new HashMap<>();
		if(StringUtils.isEmpty(accountCategory) && StringUtils.isEmpty(accountName))
		{
			try {
				accCatDtoList=accountCategoryRepository.fetchAccountCategoryData();
				//accessDetailsList=accessDetailsRepository.findById(id);
				if(accCatDtoList.size()!=0 && !accCatDtoList.isEmpty()) {
					response.put("message","Account Category Details fetched Successfully");
					response.put("status", HttpStatus.OK.value());
					response.put("accountCategory", accCatDtoList);
				}else {
					response.put("message", "No data found.");
					response.put("status", HttpStatus.NO_CONTENT.value());
					response.put("accountCategory", accCatDtoList);
				}
			}
			catch(Exception e) {
				throw new InvalidServiceException("Exception occured while fetching Account Category details.");
			}
		}
		else
		{	
			accountCategory = accountCategory.isEmpty() ? "": ("%"+accountCategory+"%").toLowerCase();
			accountName = accountName.isEmpty() ? "": ("%"+accountName+"%").toLowerCase();
		try {				
			accCatDtoList=accountCategoryRepository.fetchAccountCategoryBySearch(accountCategory,accountName);				
			//accessDetailsObj=accessDetailsRepository.getOne(id);
			if(accCatDtoList.size()!=0 && !accCatDtoList.isEmpty()) {
				response.put("message","Account Category Details fetched Successfully");
				response.put("status", HttpStatus.OK.value());
				response.put("accountCategory", accCatDtoList);
			}else {
				response.put("message", "No data found.");
				response.put("status", HttpStatus.NO_CONTENT.value());
				response.put("accountCategory", accCatDtoList);
			}
		}
		catch(Exception e) {
			throw new InvalidServiceException("Exception occured while fetching Account Category details.");
		}
		}
		return response;			
		}
	/**
	 * This method is for deleting the account category, i.e., changing the status
	 * of the
	 */
	@Override
	public Map<String, Object> deleteAccountCategory(String accountCategoryId) {
		Optional<AccountCategoryDTO> accountCategoryData;
		Map<String, Object> response;
		response = new HashMap<>();

		accountCategoryData = accountCategoryRepository.findAccountCategory(accountCategoryId);
		if (accountCategoryData.isPresent()) {
			try {
				accountCategoryRepository.deletAccountcategory(accountCategoryId);
				response.put("message", "Account Category deleted successfully");
				response.put("status", HttpStatus.OK.value());
			} catch (Exception e) {
				throw new InvalidServiceException("Exception occured while deleting Account Category details.");
			}
		} else {
			response.put("message", "No data found.");
			response.put("status", HttpStatus.NO_CONTENT.value());
		}
		return response;
	}

	/**
	 * This method is for modifying the Account Category (Only
	 */
	@Override
	public Map<String, Object> modifyAccountCategory(AccountCategoryDTO categoryDto) {
		Optional<AccountCategoryDTO> accountCategoryData;
		Map<String, Object> response;
		response = new HashMap<String, Object>();

		accountCategoryData = accountCategoryRepository.findAccountCategory(categoryDto.getAccountCategoryId());
		if (accountCategoryData.isPresent()) {
			try {
				accountCategoryRepository.updateAccountCategory(categoryDto.getAccountName().toUpperCase(),
						categoryDto.getAccountCategoryId(),categoryDto.getModifiedBy(),LocalDateTime.now());
				response.put("message", "Account category details updated successfully.");
				
				response.put("status", HttpStatus.OK.value());
			} catch (Exception e) {
				throw new InvalidServiceException("Exception occured while updating Account Category details.");
			}
		} else {
			response.put("message", "No Data found for the given Id.");
			response.put("status", HttpStatus.NO_CONTENT.value());
		}
		return response;
	}

	@Override
	public Map<String, Object> getAccountCategoryDatasingle(String id) {
		Optional<AccountCategoryDTO> accountCategoryData;
		Map<String, Object> response;
		response = new HashMap<String, Object>();

		if (!StringUtils.isEmpty(id)) {
			try {
				accountCategoryData = accountCategoryRepository.findById(id);
				if (accountCategoryData.isPresent()) {
					response.put("accountCategory", accountCategoryData);
					response.put("message", "Account category data fetched successfully.");
					response.put("status", HttpStatus.OK.value());
				} else {
					response.put("accountCategory", accountCategoryData);
					response.put("message", "No data found for the given Id.");
					response.put("status", HttpStatus.NO_CONTENT.value());
				}
			} catch (Exception e) {
				throw new ResourceNotFoundException("Error occureed while fetching Account Category data.");
			}
		} else {
			throw new InvalidServiceException("Id cannot be null or empty");
		}
		return response;
	}

	@Override
	public Map<String, Object> fetchAccountCategories() {
		List<String> categories;
		Map<String, Object> response;
		response = new HashMap<>();
		try {
			categories = accountCategoryRepository.fetchAccountCategories();
			if (categories.size() != 0 && !categories.isEmpty()) {
				response.put("accountCategories", categories);
				response.put("message", "Account categories fetched successfully.");
				response.put("status", HttpStatus.OK.value());
			} else {
				response.put("accountCategories", categories);
				response.put("message", "No data found.");
				response.put("status", HttpStatus.NO_CONTENT.value());
			}
		} catch (Exception e) {
			throw new InvalidServiceException("Error occured while fetching Account Categories");
		}
		return response;
	}

	@Override
	public Map<String, Object> fetchAccountNames(String accountCategory) {
		List<String> accountNames;
		Map<String, Object> response;
		response = new HashMap<>();
		try {
			accountNames = accountCategoryRepository.fetchAccountCategoryNames(accountCategory);
			if (accountNames.size() != 0 && !accountNames.isEmpty()) {
				response.put("accountCategory", accountCategory);
				response.put("accountNames", accountNames);
				response.put("message", "Account Category Names fetched successfully.");
				response.put("status", HttpStatus.OK.value());
			} else {
				response.put("accountCategoryNames", accountNames);
				response.put("message", "No data found.");
				response.put("status", HttpStatus.NO_CONTENT.value());
			}
		} catch (Exception e) {
			throw new InvalidServiceException("Error occured while fetching Account Category Names");
		}
		return response;
	}


}
