package com.techm.po.model.bo;

import java.time.LocalDate;

public class ResourceFxBO {
	
	private String rm;
	private String associateId;
	private String associateName;
	private LocalDate associateStartDate;
	private LocalDate associateEndDate;
	private String band;
	private int uof;
	private float fxrate;
	private int releasedate;	
	
	public ResourceFxBO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ResourceFxBO(String rm, String associateId, String associateName, LocalDate associateStartDate,
			LocalDate associateEndDate, String band, int uof, float fxrate, int releasedate) {

		this.rm = rm;
		this.associateId = associateId;
		this.associateName = associateName;
		this.associateStartDate = associateStartDate;
		this.associateEndDate = associateEndDate;
		this.band = band;
		this.uof = uof;
		this.fxrate = fxrate;
		this.releasedate = releasedate;
		
	}
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
	}
	public int getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(int releasedate) {
		this.releasedate = releasedate;
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
	@Override
	public String toString() {
		return "ResourceFxBO [rm=" + rm + ", associateId=" + associateId + ", associateName=" + associateName
				+ ", associateStartDate=" + associateStartDate + ", associateEndDate=" + associateEndDate + ", band="
				+ band + ", uof=" + uof + ", fxrate=" + fxrate + ", releasedate=" + releasedate + "]";
	}
			
}
