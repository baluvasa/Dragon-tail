package com.techm.po.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UploadExcelService {
	Map<String, Object> uploadExcelData(MultipartFile reapExcelDataFile);
}
