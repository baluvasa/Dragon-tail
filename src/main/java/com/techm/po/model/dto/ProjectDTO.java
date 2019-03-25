package com.techm.po.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_project")
public class ProjectDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="account_category")
	private String accountCategory;
	
	@Column(name="account_name")
	private String accountName;
	
	@Column(name="project_name")
	private String projectName;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="customer_spoc")
	private String customerSpoc;
	
	@Column(name="approval_method")
	private String approvalMethod;
	
	@Column(name="submission_mode")
	private String submissionMode;
	
	@Column(name="project_type")
	private String projectType;
	
	@Column(name="billing_currency")
	private String billingCurrency;
	
	@Column(name="po_amount")
	private String poAmount;
	
	@Column(name="project_start_date")
	private LocalDate projectStartDate;
	
	@Column(name="project_end_date")
	private LocalDate projectEndDate;
	
	@Column(name="delivery_spoc")
	private String deliverySpoc;
	
	@Column(name="effort_spoc")
	private String effortSpoc;
	
	@Column(name="pid")
	private String pid;
	
	@Column(name="quote")
	private String quote;
	
	@Column(name="contract")
	private String contract;
	
	@Column(name="po")
	private String po;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@Column(name="modified_by")
	private String modifiedBy;
	
	@Column(name="modified_date")
	private LocalDateTime modifiedDate;
	
	@Column(name="status")
	private String status;
	
	@Column(name="unit_of_mesurment")
	private int unitOfMeasurement;
	@Column(name="resource_count")
	private int resourceCount;
	public int getResourceCount() {
		return resourceCount;
	}
	public void setResourceCount(int resourceCount) {
		this.resourceCount = resourceCount;
	}
	public int getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	public void setUnitOfMeasurement(int unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerSpoc() {
		return customerSpoc;
	}
	public void setCustomerSpoc(String customerSpoc) {
		this.customerSpoc = customerSpoc;
	}
	public String getApprovalMethod() {
		return approvalMethod;
	}
	public void setApprovalMethod(String approvalMethod) {
		this.approvalMethod = approvalMethod;
	}
	public String getSubmissionMode() {
		return submissionMode;
	}
	public void setSubmissionMode(String submissionMode) {
		this.submissionMode = submissionMode;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getBillingCurrency() {
		return billingCurrency;
	}
	public void setBillingCurrency(String billingCurrency) {
		this.billingCurrency = billingCurrency;
	}
	public String getPoAmount() {
		return poAmount;
	}
	public void setPoAmount(String poAmount) {
		this.poAmount = poAmount;
	}
	public LocalDate getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(LocalDate projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public LocalDate getProjectEndDate() {
		return projectEndDate;
	}
	public void setProjectEndDate(LocalDate projectEndDate) {
		this.projectEndDate = projectEndDate;
	}
	public String getDeliverySpoc() {
		return deliverySpoc;
	}
	public void setDeliverySpoc(String deliverySpoc) {
		this.deliverySpoc = deliverySpoc;
	}
	public String getEffortSpoc() {
		return effortSpoc;
	}
	public void setEffortSpoc(String effortSpoc) {
		this.effortSpoc = effortSpoc;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
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
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDateTime modifiedDate) {
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
		return "ProjectDTO [id=" + id + ", accountCategory=" + accountCategory + ", accountName=" + accountName
				+ ", projectName=" + projectName + ", customerName=" + customerName + ", customerSpoc=" + customerSpoc
				+ ", approvalMethod=" + approvalMethod + ", submissionMode=" + submissionMode + ", projectType="
				+ projectType + ", billingCurrency=" + billingCurrency + ", poAmount=" + poAmount
				+ ", projectStartDate=" + projectStartDate + ", projectEndDate=" + projectEndDate + ", deliverySpoc="
				+ deliverySpoc + ", effortSpoc=" + effortSpoc + ", pid=" + pid + ", quote=" + quote + ", contract="
				+ contract + ", po=" + po + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", status=" + status
				+ ", unitOfMeasurement=" + unitOfMeasurement + ", resourceCount=" + resourceCount
				+ ", getResourceCount()=" + getResourceCount() + ", getUnitOfMeasurement()=" + getUnitOfMeasurement()
				+ ", getId()=" + getId() + ", getAccountCategory()=" + getAccountCategory() + ", getAccountName()="
				+ getAccountName() + ", getProjectName()=" + getProjectName() + ", getCustomerName()="
				+ getCustomerName() + ", getCustomerSpoc()=" + getCustomerSpoc() + ", getApprovalMethod()="
				+ getApprovalMethod() + ", getSubmissionMode()=" + getSubmissionMode() + ", getProjectType()="
				+ getProjectType() + ", getBillingCurrency()=" + getBillingCurrency() + ", getPoAmount()="
				+ getPoAmount() + ", getProjectStartDate()=" + getProjectStartDate() + ", getProjectEndDate()="
				+ getProjectEndDate() + ", getDeliverySpoc()=" + getDeliverySpoc() + ", getEffortSpoc()="
				+ getEffortSpoc() + ", getPid()=" + getPid() + ", getQuote()=" + getQuote() + ", getContract()="
				+ getContract() + ", getPo()=" + getPo() + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedDate()="
				+ getCreatedDate() + ", getModifiedBy()=" + getModifiedBy() + ", getModifiedDate()=" + getModifiedDate()
				+ ", getStatus()=" + getStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
