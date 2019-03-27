package com.techm.po.model.bo;

public class MonthlyAmounts {
	
	private Amounts projections;
	private Amounts accruals;
	private Amounts billed;
	private int rusGapDays;
	private int rusGapHours;
	private String remarks;
	
	public Amounts getProjections() {
		return projections;
	}
	public void setProjections(Amounts projections) {
		this.projections = projections;
	}
	public Amounts getAccruals() {
		return accruals;
	}
	public void setAccruals(Amounts accruals) {
		this.accruals = accruals;
	}
	public Amounts getBilled() {
		return billed;
	}
	public void setBilled(Amounts billed) {
		this.billed = billed;
	}
	
	public int getRusGapDays() {
		return rusGapDays;
	}
	public void setRusGapDays(int rusGapDays) {
		this.rusGapDays = rusGapDays;
	}
	public int getRusGapHours() {
		return rusGapHours;
	}
	public void setRusGapHours(int rusGapHours) {
		this.rusGapHours = rusGapHours;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "MonthlyAmounts [projections=" + projections + ", accruals=" + accruals + ", billed=" + billed
				+ ", rusGapDays=" + rusGapDays + ", rusGapHours=" + rusGapHours + ", remarks=" + remarks + "]";
	}	
	
	
}
