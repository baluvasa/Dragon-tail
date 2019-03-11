package com.techm.po.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="tbl_resources")
public class ResourceDTO implements Serializable {
/**
* 
*/
private static final long serialVersionUID = 1L;

@Id
private String associateId;
private String associateName;
private String band;
private String emailId;
private Long contactNumber;
private String pId;
private String createdBy;
private LocalDateTime createdDate;
private String modifiedBy;
private LocalDateTime modifiedDate;
private String status;

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


public Long getContactNumber() {
return contactNumber;
}


public void setContactNumber(Long contactNumber) {
this.contactNumber = contactNumber;
}


public static long getSerialversionuid() {
return serialVersionUID;
}




public String getpId() {
return pId;
}


public void setpId(String pId) {
this.pId = pId;
}


@Override
public String toString() {
	return "ResourceDTO [associateId=" + associateId + ", associateName=" + associateName + ", band=" + band
			+ ", emailId=" + emailId + ", contactNumber=" + contactNumber + ", pId=" + pId + ", createdBy=" + createdBy
			+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
			+ ", status=" + status + "]";
}



}



