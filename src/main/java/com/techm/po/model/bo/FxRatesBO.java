package com.techm.po.model.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class FxRatesBO {
	
	private String fxId;
	
	private String currencyCode;
	
	private String fxRate;
	
	private String fxDate;
	
	private String createdBy;

	
	public String getFxId() {
		return fxId;
	}

	public void setFxId(String fxId) {
		this.fxId = fxId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getFxRate() {
		return fxRate;
	}

	public void setFxRate(String fxRate) {
		this.fxRate = fxRate;
	}
	
	public String getFxDate() {
		return fxDate;
	}

	public void setFxDate(String fxDate) {
		this.fxDate = fxDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
