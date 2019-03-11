package com.techm.po.service;

import java.util.Map;
import com.techm.po.model.bo.FxRatesBO;

public interface FxRatesService {

	Map<String, Object> addFxRateDetails(FxRatesBO fxRatesBo);
	
	Map<String, Object> fetchFxRatesDetails(String countryCode, String date);
	
	Map<String, Object> modifyFxRates(FxRatesBO fxRatesBO);
	
	Map<String, Object> deleteFxRate(String countryCode);
}
