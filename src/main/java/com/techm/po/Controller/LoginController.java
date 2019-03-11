package com.techm.po.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techm.po.model.bo.LoginBO;
import com.techm.po.service.LoginService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/associate")
	public Map<String, Object> associateLogin(@RequestBody LoginBO loginBo){
		Map<String, Object> response;
		response = loginService.associateLogin(loginBo);
		return response;
	}
}
