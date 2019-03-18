package com.techm.po.model.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_resource_map")
public class ResourceMapDTO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer resourceMapId;	
	private String associateId;
	private String pId;
	private String location;
	private int ratePerHour;
	private String linked;
	private String contractId;

	private LocalDate associateStartDate;
	private LocalDate associateEndDate;
	public Integer getResourceMapId() {
		return resourceMapId;
	}
	public void setResourceMapId(Integer resourceMapId) {
		this.resourceMapId = resourceMapId;
	}
	public String getAssociateId() {
		return associateId;
	}
	public void setAssociateId(String associateId) {
		this.associateId = associateId;
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
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	@Override
	public String toString() {
		return "ResourceMapDTO [resourceMapId=" + resourceMapId + ", associateId=" + associateId + ", pId=" + pId
				+ ", location=" + location + ", ratePerHour=" + ratePerHour + ", linked=" + linked + ", contractId="
				+ contractId + ", associateStartDate=" + associateStartDate + ", associateEndDate=" + associateEndDate
				+ "]";
	}
	
	
}
