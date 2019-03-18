package com.techm.po.model.bo;

import java.time.LocalDate;

public class ResoureMapInfoBo {
	private String rm;
	private String associateId;
	private String associateName;
	private LocalDate associateStartDate;
	private LocalDate associateEndDate;
	private String band;
	private int uof;
	private float fxrate;
	private int releasedate;	
	private Integer ratePerHour;
	private String location;
	private String linked;
	
	
	 
	public ResoureMapInfoBo(String rm, String associateId, String associateName, LocalDate associateStartDate,
			LocalDate associateEndDate, String band, Integer ratePerHour, String location,String linked) {
		super();
		this.rm = rm;
		this.associateId = associateId;
		this.associateName = associateName;
		this.associateStartDate = associateStartDate;
		this.associateEndDate = associateEndDate;
		this.band = band;
		this.ratePerHour = ratePerHour;
		this.location = location;
		this.linked=linked;
	}
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
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
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	public int getUof() {
		return uof;
	}
	public void setUof(int uof) {
		this.uof = uof;
	}
	public float getFxrate() {
		return fxrate;
	}
	public void setFxrate(float fxrate) {
		this.fxrate = fxrate;
	}
	public int getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(int releasedate) {
		this.releasedate = releasedate;
	}
	public Integer getRatePerHour() {
		return ratePerHour;
	}
	public void setRatePerHour(Integer ratePerHour) {
		this.ratePerHour = ratePerHour;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLinked() {
		return linked;
	}
	public void setLinked(String linked) {
		this.linked = linked;
	}
	@Override
	public String toString() {
		return "ResoureMapInfoBo [rm=" + rm + ", associateId=" + associateId + ", associateName=" + associateName
				+ ", associateStartDate=" + associateStartDate + ", associateEndDate=" + associateEndDate + ", band="
				+ band + ", uof=" + uof + ", fxrate=" + fxrate + ", releasedate=" + releasedate + ", ratePerHour="
				+ ratePerHour + ", location=" + location + ", linked=" + linked + "]";
	}

}
