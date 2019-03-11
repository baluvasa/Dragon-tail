package com.techm.po.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.techm.po.model.bo.LeavesBO;

@Service
public interface LeavesDetailsService {

		Map<String, Object> addLeavesDetails(LeavesBO LeavesBo);

		Map<String, Object> updateLeavesDetails(LeavesBO LeavesBo);

		Map<String, Object> deleteLeavesDetails(Integer id);

		Map<String, Object> fetchLeavesDetails(String id, String name, String monthYear);
		
}
