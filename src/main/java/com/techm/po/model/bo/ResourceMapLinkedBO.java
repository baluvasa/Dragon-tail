package com.techm.po.model.bo;

import java.time.LocalDate;

public class ResourceMapLinkedBO {

	private int resourceMapId;
    private String associateId;			
    private String associateName;
    private String band;
    private String emailId;
	private Long contactNumber;
	private String pId;
	private String location;
    private int ratePerHour;
    private String linked;
    private String contractId;
    private LocalDate associateStartDate;
    private LocalDate associateEndDate;
    private String status;
	public int getResourceMapId() {
		return resourceMapId;
	}
	public void setResourceMapId(int resourceMapId) {
		this.resourceMapId = resourceMapId;
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
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getRatePerHour() {
		return ratePerHour;
	}
	public void setRatePerHour(int ratePerHour) {
		this.ratePerHour = ratePerHour;
	}
	public String getLinked() {
		return linked;
	}
	public void setLinked(String linked) {
		this.linked = linked;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public LocalDate getAssociateStartDate() {
		return associateStartDate;
	}
	public void setAssociateStartDate(LocalDate associateStartDate) {
		this.associateStartDate = associateStartDate;
	}
	public LocalDate getAssociateEndDate() {
		return associateEndDate;
	}
	public void setAssociateEndDate(LocalDate associateEndDate) {
		this.associateEndDate = associateEndDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ResourceMapLinkedBO [resourceMapId=" + resourceMapId + ", associateId=" + associateId
				+ ", associateName=" + associateName + ", band=" + band + ", emailId=" + emailId + ", contactNumber="
				+ contactNumber + ", pId=" + pId + ", location=" + location + ", ratePerHour=" + ratePerHour
				+ ", linked=" + linked + ", contractId=" + contractId + ", associateStartDate=" + associateStartDate
				+ ", associateEndDate=" + associateEndDate + ", status=" + status + "]";
	}
    
    
    
}
