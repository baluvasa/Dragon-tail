package com.techm.po.model.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_holidays")
public class HolidaysDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer holidayId;
	private String accountCategoryId;
	private String projectName;
	private String year;
	private Integer january;
	private Integer february;
	private Integer march;
	private Integer april;
	private Integer may;
	private Integer june;
	private Integer july;
	private Integer august;
	private Integer september;
	private Integer october;
	private Integer november;
	private Integer december;
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDateTime modifiedDate;
	private String status;
	private String accountCategory;
	private String accountName;
	

	// Getters and Setters
	public Integer getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}
	public String getAccountCategoryId() {
		return accountCategoryId;
	}
	public void setAccountCategoryId(String accountCategoryId) {
		this.accountCategoryId = accountCategoryId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getJanuary() {
		return january;
	}
	public void setJanuary(Integer january) {
		this.january = january;
	}
	public Integer getFebruary() {
		return february;
	}
	public void setFebruary(Integer february) {
		this.february = february;
	}
	public Integer getMarch() {
		return march;
	}
	public void setMarch(Integer march) {
		this.march = march;
	}
	public Integer getApril() {
		return april;
	}
	public void setApril(Integer april) {
		this.april = april;
	}
	public Integer getMay() {
		return may;
	}
	public void setMay(Integer may) {
		this.may = may;
	}
	public Integer getJune() {
		return june;
	}
	public void setJune(Integer june) {
		this.june = june;
	}
	public Integer getJuly() {
		return july;
	}
	public void setJuly(Integer july) {
		this.july = july;
	}
	public Integer getAugust() {
		return august;
	}
	public void setAugust(Integer august) {
		this.august = august;
	}
	public Integer getSeptember() {
		return september;
	}
	public void setSeptember(Integer september) {
		this.september = september;
	}
	public Integer getOctober() {
		return october;
	}
	public void setOctober(Integer october) {
		this.october = october;
	}
	public Integer getNovember() {
		return november;
	}
	public void setNovember(Integer november) {
		this.november = november;
	}
	public Integer getDecember() {
		return december;
	}
	public void setDecember(Integer december) {
		this.december = december;
	}
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
	public String getAccountCategory() {
		return accountCategory;
	}
	public void setAccountCategory(String accountCategory) {
		this.accountCategory = accountCategory;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
