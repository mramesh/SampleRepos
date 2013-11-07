package com.oonusave.coupon.model;

import java.io.Serializable;


public class Coupon implements Serializable{
	
	
	private String distance = "";
	
	private int noDaysToExpiry = 0;
	private String phoneNo = "";
	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getWebAddress() {
		return webAddress;
	}


	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}
	private String webAddress = "";
	
	public int getNoDaysToExpiry() {
		return noDaysToExpiry;
	}


	public void setNoDaysToExpiry(int noDaysToExpiry) {
		this.noDaysToExpiry = noDaysToExpiry;
	}


	public String getDistance() {
		return distance;
	}


	public void setDistance(String distance) {
		this.distance = distance;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	private String shopName = "";
	private String address1 = "";
	private String address2 = "";
	private String address3 = "";
	private String shortDesc = "";
	private String desc = "";
	private String offerPrice = "";
	private float lati;
	private float longi;
	private String category = "";
	private String subCategory = "";
	private String expiryDate = "";
	private String imagePath = "";
	private String barcode = "";
	private long couponId = 0;
	private String couponName = "";
	private int featured = 0;
	
	
	
	public int getFeatured() {
		return featured;
	}


	public void setFeatured(int featured) {
		this.featured = featured;
	}
	private String location = "";
	private String country = "";
	private String storeName = "";
	
	
	
	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public String getAddress3() {
		return address3;
	}


	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	
	
	public String getCouponName() {
		return couponName;
	}


	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}


	public long getCouponId() {
		return couponId;
	}


	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}


	public long getStoreId() {
		return storeId;
	}


	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	private long storeId = 0;
	
	public String getBarcode() {
		return barcode;
	}


	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public Coupon(String shopName,String address,String shortDesc,String desc,String offerPrice,float lati,float longi,String category,String subCategory,String expiryDate) {
		this.shopName = shopName;
		this.address1 = address;
		this.shortDesc = shortDesc;
		this.desc = desc;
		this.offerPrice = offerPrice;
		this.lati = lati;
		this.longi = longi;
		this.category = category;
		this.subCategory = subCategory;
		this.expiryDate = expiryDate;
	}
	
	
	public Coupon() {
		
	}
	
	
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(String offerPrice) {
		this.offerPrice = offerPrice;
	}
	public float getLati() {
		return lati;
	}
	public void setLati(float lati) {
		this.lati = lati;
	}
	public float getLongi() {
		return longi;
	}
	public void setLongi(float longi) {
		this.longi = longi;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}


	
}
