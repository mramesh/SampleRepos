package com.oonusave.coupon.model;

import java.io.Serializable;

public class SubCategory implements Serializable{
	private int subCategoryId = 0;
	private String subCategoryName = "";
	
	public int getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	
}
