package com.techm.po.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tbl_account_category")
public class AccountCategoryDTO implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String accountCategoryId;
	
	@NotNull
	private String accountCategory;
	
	@NotNull
	private String accountName;
	
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDate modifiedDate;
	private String status;
	
	// Getters and Setters
	public String getAccountCategoryId() {
		return accountCategoryId;
	}
	public void setAccountCategoryId(String accountCategoryId) {
		this.accountCategoryId = accountCategoryId;
	}
	public String getAccountCategory() {
		return accountCategory;
	}
	public void setAccountCategory(String accountCategory) {
		this.accountCategory = accountCategory;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	@Override
	public String toString() {
		return "AccountCategoryDTO [accountCategoryId=" + accountCategoryId + ", accountCategory=" + accountCategory
				+ ", accountName=" + accountName + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", status=" + status + "]";
	}

		
}
