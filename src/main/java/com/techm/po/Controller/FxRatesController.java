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

import com.techm.po.model.bo.FxRatesBO;
import com.techm.po.service.FxRatesService;

@RestController
@CrossOrigin
@RequestMapping("/fx_rates")
public class FxRatesController {

	@Autowired
	private FxRatesService fxRatesService;

	
	/**
	 * This API is for creating FX rates
	 * @param fxRatesBo 
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> createFxRates(@RequestBody FxRatesBO fxRatesBo) {
		Map<String, Object> response;
		response = fxRatesService.addFxRateDetails(fxRatesBo);
		return response;
	}

	/**
	 * This API is for fetching List of FxRates data
	 * 
	 * @return
	 */
	@GetMapping("/fetch")
	public Map<String, Object> getFxRatesData(@RequestParam(name = "countryCode", required = false) String countryCode,
								@RequestParam(name = "date", required = false) String date) {
		Map<String, Object> response;
		response = fxRatesService.fetchFxRatesDetails(countryCode, date);
		return response;
	}
	
	/**
	 * This API is for updating FxRates details
	 * @param fxRatesDto of type {@link FxRatesDTO}, contains currencyCode and fxRate
	 * @return
	 */
	@PutMapping("/update")
	public Map<String, Object> modifyFxRates(@RequestBody FxRatesBO fxRatesBO) {
		Map<String, Object> response;
		response = fxRatesService.modifyFxRates(fxRatesBO);
		return response;
	}
	
	/**
	 * This API is for Deleting FxRates details
	 * @param country code of type {@link String}, it is country code
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> deleteFxRate(@RequestParam(name = "fxId")String fxId) {
		Map<String, Object> response;
		response = fxRatesService.deleteFxRate(fxId);
		return response;	
	}
}
