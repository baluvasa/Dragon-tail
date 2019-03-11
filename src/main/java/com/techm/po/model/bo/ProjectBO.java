package com.techm.po.model.bo;

import java.time.LocalDateTime;
import java.util.List;

import com.techm.po.model.dto.ResourceMapDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectBO {

	private List<ResourceMap> resources;
	private String accountCategory;
	private String accountName;
	private String projectName;
	private String customerName;
	private String customerSpoc;
	private String approvalMethod;
	private String submissionMode;
	private String projectType;
	private String billingCurrency;
	private String poAmount;
	private String projectStartDate;
	private String projectEndDate;
	private String deliverySpoc;
	private String effortSpoc;
	private String pid;
	private String quote;
	private String contract;
	private String po;
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDateTime modifiedDate;
	private String status;
	
	
	
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
	public String getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public String getProjectEndDate() {
		return projectEndDate;
	}
	public void setProjectEndDate(String projectEndDate) {
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

	
	public List<ResourceMap> getResources() {
		return resources;
	}
	public void setResources(List<ResourceMap> resources) {
		this.resources = resources;
	}
	@Override
	public String toString() {
		return "ProjectBO [resources=" + resources + ", accountCategory=" + accountCategory + ", accountName="
				+ accountName + ", projectName=" + projectName + ", customerName=" + customerName + ", customerSpoc="
				+ customerSpoc + ", approvalMethod=" + approvalMethod + ", submissionMode=" + submissionMode
				+ ", projectType=" + projectType + ", billingCurrency=" + billingCurrency + ", poAmount=" + poAmount
				+ ", projectStartDate=" + projectStartDate + ", projectEndDate=" + projectEndDate + ", deliverySpoc="
				+ deliverySpoc + ", effortSpoc=" + effortSpoc + ", pid=" + pid + ", quote=" + quote + ", contract="
				+ contract + ", po=" + po + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", status=" + status + "]";
	}
	
}
