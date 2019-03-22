package com.techm.po.model.bo;

public class LeavesBO {
	
	private int leaveId;
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	private String associateId;
	private String associateName;
	private String leaveDate;
	private String remarks;	
	private String createdBy;
	private String modifiedBy;
	private String modifyDate;
	private long count;
	
	public LeavesBO() {}
	public LeavesBO(String associateId, long count) {
		this.associateId = associateId;
		this.count = count;
	}
	
	public LeavesBO(int leaveId,String associateId,String associateName,String leaveDate,String remarks) {
		this.leaveId = leaveId;
		this.associateId = associateId;
		this.associateName = associateName;
		this.leaveDate = leaveDate;
		this.remarks = remarks;
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
	public String getLeaveDate() {
		return leaveDate;
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

		
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "LeavesBO [leaveId=" + leaveId + ", associateId=" + associateId + ", associateName=" + associateName
				+ ", leaveDate=" + leaveDate + ", remarks=" + remarks + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", modifyDate=" + modifyDate + ", count=" + count + "]";
	}

	
}
