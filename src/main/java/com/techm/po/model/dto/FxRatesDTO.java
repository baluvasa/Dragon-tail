package com.techm.po.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_fx_rates")
public class FxRatesDTO {

	@Id
	private String fxId;
	
	private String currencyCode;
	private String fxRate;
	private LocalDate fxDate;
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDate modifiedDate;
	private String status;
	
	// Getters & Setters
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
	public LocalDate getFxDate() {
		return fxDate;
	}
	public void setFxDate(LocalDate fxDate) {
		this.fxDate = fxDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
