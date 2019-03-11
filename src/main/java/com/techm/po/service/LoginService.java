package com.techm.po.service;

import java.util.Map;

import com.techm.po.model.bo.LoginBO;

public interface LoginService {

	Map<String, Object> associateLogin(LoginBO loginBo);
}
