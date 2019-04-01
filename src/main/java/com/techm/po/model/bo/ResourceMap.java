package com.techm.po.model.bo;


public class ResourceMap {

	private Integer resourceMapId;
private String associateId;
private String associateStartDate;
private String associateEndDate;
private String location;
private String rateMethod;
public String getRateMethod() {
	return rateMethod;
}
public void setRateMethod(String rateMethod) {
	this.rateMethod = rateMethod;
}
private int ratePerHour;
private String linked;
private String pId;
private String contractId;
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
public String getpId() {
	return pId;
}
public void setpId(String pId) {
	this.pId = pId;
}
public String getAssociateId() {
	return associateId;
}
public Integer getResourceMapId() {
	return resourceMapId;
}
public void setResourceMapId(Integer resourceMapId) {
	this.resourceMapId = resourceMapId;
}
public void setAssociateId(String associateId) {
	this.associateId = associateId;
}
public String getAssociateEndDate() {
	return associateEndDate;
}
public void setAssociateEndDate(String associateEndDate) {
	this.associateEndDate = associateEndDate;
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
public String getAssociateStartDate() {
	return associateStartDate;
}
public void setAssociateStartDate(String associateStartDate) {
	this.associateStartDate = associateStartDate;
}
@Override
public String toString() {
	return "ResourceMap [resourceMapId=" + resourceMapId + ", associateId=" + associateId + ", associateStartDate="
			+ associateStartDate + ", associateEndDate=" + associateEndDate + ", location=" + location + ", rateMethod="
			+ rateMethod + ", ratePerHour=" + ratePerHour + ", linked=" + linked + ", pId=" + pId + ", contractId="
			+ contractId + "]";
}



}