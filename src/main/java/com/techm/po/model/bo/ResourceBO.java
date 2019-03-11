package com.techm.po.model.bo;

import java.time.LocalDateTime;

public class ResourceBO {
private String associateId;
private String associateName;
private String band;
private String emailId;
private String pId;
private Long contactNumber;
private String createdBy;
private LocalDateTime createdDate;
private String modifiedBy;
private LocalDateTime modifiedDate;
private String status;

public String getAssociateId() {
	return associateId;
}
public void setAssociateId(String associateId) {
	this.associateId = associateId;
}
public String getAssociateName() {
	return associateName;
}
public void setAssociateName(String associateName) {
	this.associateName = associateName;
}
public String getBand() {
	return band;
}
public void setBand(String band) {
	this.band = band;
}

public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getpId() {
	return pId;
}
public void setpId(String pId) {
	this.pId = pId;
}
public Long getContactNumber() {
	return contactNumber;
}
public void setContactNumber(Long contactNumber) {
	this.contactNumber = contactNumber;
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
	return "ResourceBO [ associateId=" + associateId + ", associateName=" + associateName
			+ ", band=" + band + ", emailId=" + emailId + ", pId=" + pId + ", contactNumber=" + contactNumber
			+ ", createdBy=" + createdBy + ", createdDate=" + createdDate +  ", modifiedBy="
			+ modifiedBy + ", modifiedDate=" + modifiedDate + ", status=" + status + "]";
}



}