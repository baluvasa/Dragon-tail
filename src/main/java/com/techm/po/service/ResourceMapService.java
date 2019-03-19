package com.techm.po.service;

import java.util.Map;

import com.techm.po.model.bo.ResourceMap;
import com.techm.po.model.bo.ResourceMapBO;

public interface ResourceMapService {

	Map<String, Object> mapResources(ResourceMapBO resourcemapBo);

}
