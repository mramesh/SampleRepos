package com.oonusave.coupon.model;

import java.io.Serializable;

public class Competion implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String comprtionName = "";
	private String price = "";
	private String description = "";
	private String comprtionNameDN = "";
	private String priceDN = "";
	private String descriptionDN = "";
	
	private String competionType = "";
	private int competionId = 0;
	

	public String getComprtionName() {
		return comprtionName;
	}
	public void setComprtionName(String comprtionName) {
		this.comprtionName = comprtionName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getComprtionNameDN() {
		return comprtionNameDN;
	}
	public void setComprtionNameDN(String comprtionNameDN) {
		this.comprtionNameDN = comprtionNameDN;
	}
	public String getPriceDN() {
		return priceDN;
	}
	public void setPriceDN(String priceDN) {
		this.priceDN = priceDN;
	}
	public String getDescriptionDN() {
		return descriptionDN;
	}
	public void setDescriptionDN(String descriptionDN) {
		this.descriptionDN = descriptionDN;
	}
	public String getCompetionType() {
		return competionType;
	}
	public void setCompetionType(String competionType) {
		this.competionType = competionType;
	}
	public int getCompetionId() {
		return competionId;
	}
	public void setCompetionId(int competionId) {
		this.competionId = competionId;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
