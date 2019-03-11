package com.techm.po.model.bo;

public class LeavesBO {

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
	public String getLeaveDate() {
		return leaveDate;
	}
	@Override
	public String toString() {
		return "LeavesBO [associateId=" + associateId + ", associateName=" + associateName + ", leaveDate=" + leaveDate
				+ ", remarks=" + remarks + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", modifyDate="
				+ modifyDate + "]";
	}
	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
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
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	private Integer leaveId;
	public Integer getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}
	private String associateId;
	private String associateName;
	private String leaveDate;
	private String remarks;	
	private String createdBy;
	private String modifiedBy;
	private String modifyDate;
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
