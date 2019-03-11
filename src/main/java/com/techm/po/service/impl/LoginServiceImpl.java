package com.techm.po.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.techm.po.dao.LoginRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.model.bo.LoginBO;
import com.techm.po.model.dto.AccessDetailsDTO;
import com.techm.po.service.LoginService;


@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public Map<String, Object> associateLogin(LoginBO loginBo) {
		Map<String, Object> response;
		Optional<AccessDetailsDTO> accessDetails;
		String associateId;
		String password;
		LoginBO loggedUserDetails;
		
		response = new HashMap<>();
		associateId = loginBo.getAssociateId();
		password = loginBo.getPassword();
		
		if(!StringUtils.isEmpty(associateId) && !StringUtils.isEmpty(password)) {
			accessDetails = loginRepository.fetchAccessDetails(associateId.toUpperCase());
			if (accessDetails.isPresent()) {
				if(accessDetails.get().getPassword().equals(password)) {
					loggedUserDetails = mapDtoToBo(accessDetails);
					response.put("accessDetails", loggedUserDetails);
					response.put("status", HttpStatus.OK.value());
				}
				else {
					response.put("message", "Invalid associateId / password.");
					response.put("status", HttpStatus.OK.value());
				}
			}
			else {
				response.put("message", "user not exists for the given id.");
				response.put("status", HttpStatus.OK.value());
			}
		}
		else {
			throw new InvalidServiceException("Either Id and password cannot be null (or) empty.");
		}
		return response;
	}
	
	private LoginBO mapDtoToBo(Optional<AccessDetailsDTO> dto) {
		LoginBO bo = new LoginBO();
		bo.setAssociateId(dto.get().getGid());
		bo.setAssociateName(dto.get().getAssociateName());
		bo.setAccessType(dto.get().getAccessType());
		return bo;
	}

}
