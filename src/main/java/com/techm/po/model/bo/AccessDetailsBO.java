package com.techm.po.model.bo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessDetailsBO {

	private String gid;
	private String associateName;
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDateTime modifiedDate;
	private String accessType;
	private String status;

	// Getters & Setters
	
	  public String getGid() { return gid; } public void setGid(String gid) {
	  this.gid = gid; } public String getAssociateName() { return associateName; }
	  public void setAssociateName(String associateName) { this.associateName =
	  associateName; } public String getCreatedBy() { return createdBy; } public
	  void setCreatedBy(String createdBy) { this.createdBy = createdBy; } public
	  LocalDateTime getCreatedDate() { return createdDate; } public void
	  setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
	  public String getModifiedBy() { return modifiedBy; } public void
	  setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; } public
	  LocalDateTime getModifiedDate() { return modifiedDate; } public void
	  setModifiedDate(LocalDateTime modifiedDate) { this.modifiedDate =
	  modifiedDate; } public String getAccessType() { return accessType; } public
	  void setAccessType(String accessType) { this.accessType = accessType; }
	  public String getStatus() { return status; } public void setStatus(String
	  status) { this.status = status; }
	

}
