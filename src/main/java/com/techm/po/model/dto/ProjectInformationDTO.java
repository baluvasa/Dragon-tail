package com.techm.po.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_po_approvals")
public class ProjectInformationDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String projectName;
	private String associateId;
	private String monthYear;
	private String accrualsHours;
	private String accrualsAmount;
	private String buildHours;
	private String buildAmount;
	private String remarks;
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDate modifiedDate;
	private String status;
	private String associateName;
	
	// Getters & Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getAssociateId() {
		return associateId;
	}
	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	public String getAccrualsHours() {
		return accrualsHours;
	}
	public void setAccrualsHours(String accrualsHours) {
		this.accrualsHours = accrualsHours;
	}
	public String getAccrualsAmount() {
		return accrualsAmount;
	}
	public void setAccrualsAmount(String accrualsAmount) {
		this.accrualsAmount = accrualsAmount;
	}
	public String getBuildHours() {
		return buildHours;
	}
	public void setBuildHours(String buildHours) {
		this.buildHours = buildHours;
	}
	public String getBuildAmount() {
		return buildAmount;
	}
	public void setBuildAmount(String buildAmount) {
		this.buildAmount = buildAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getAssociateName() {
		return associateName;
	}
	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}
	
}
