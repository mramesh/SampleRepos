package com.oonusave.coupon.ws;



import org.ksoap2.serialization.SoapObject;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import com.oonusave.coupon.model.AddToWalletResponse;
import com.oonusave.coupon.model.Category;
import com.oonusave.coupon.model.Competion;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.model.FAQ;
import com.oonusave.coupon.model.GeneralResponse;
import com.oonusave.coupon.model.RegistrationResponse;
import com.oonusave.coupon.model.Store;
import com.oonusave.coupon.model.SubCategory;
import com.oonusave.coupon.model.UpdateSettingsResponse;
import com.oonusave.coupon.model.UseCouponResponse;
import com.oonusave.coupon.model.UserDetails;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;


/**
 * 
 * @author Ramesh
 *
 */

public class WSResponseParser {
	public static UserDetails parseLoginResponse(SoapObject soapRequest) {

		Log.i(Constants.TAG, "# WSResponseParser # ---- parseLoginResponse() --- " + soapRequest);
		UserDetails userDetails = new UserDetails();



		if(soapRequest != null) {
			if(soapRequest.getPropertyCount() != 0) {
				Log.i(Constants.TAG, "# WSResponseParser # ---- parseLoginResponse 1 --- " + soapRequest);
				try {

					SoapObject userObject = (SoapObject) soapRequest.getProperty(0);
					//				//Log.i(Constants.TAG, "# WSResponseParser # ---- UserID ---- > " + userObject.getProperty("UserID").toString());
					//				userDetails.setUserId(Long.parseLong(userObject.getProperty("UserID").toString()));
					//				userDetails.setFirstName(userObject.getProperty("FirstName").toString());
					//				userDetails.setLastName(userObject.getProperty("LastName").toString());
					//				//userDetails.setMobileNumber(userObject.getProperty("MobileNumber").toString());
					//				//userDetails.setEmail(userObject.getProperty("EmailID").toString());
					//				userDetails.setUserName(userObject.getProperty("UserName").toString());
					//				userDetails.setPassword(userObject.getProperty("Password").toString());
					//				userDetails.setDeviceIndentifier(userObject.getProperty("deviceidentifier").toString());



					userDetails.setUserId(userObject.getProperty("UserID") != null ? Long.parseLong(userObject.getProperty("UserID").toString()) : 0); 
					userDetails.setFirstName((userObject.getProperty("FirstName") != null ? userObject.getProperty("FirstName").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("FirstName").toString());
					userDetails.setLastName((userObject.getProperty("LastName") != null ? userObject.getProperty("LastName").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("LastName").toString());
					//userDetails.setMobileNumber(userObject.getProperty("MobileNumber") != null ? userObject.getProperty("MobileNumber").toString() : "");
					userDetails.setDateOfBirth(userObject.getProperty("DateOfBirth") != null ? userObject.getProperty("DateOfBirth").toString() : "");
					userDetails.setAge(userObject.getProperty("Age") != null ? Integer.parseInt(userObject.getProperty("Age").toString()) : 0);
					userDetails.setGender(userObject.getProperty("Gender") != null ? userObject.getProperty("Gender").toString() : "");
					//userDetails.setEmail((userObject.getProperty("EmailID") != null ? userObject.getProperty("EmailID").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("EmailID").toString());
					userDetails.setUserName(userObject.getProperty("UserName") != null ? userObject.getProperty("UserName").toString() : "");
					//userDetails.setPassword(userObject.getProperty("Password") != null ? userObject.getProperty("Password").toString() : "");
					userDetails.setHowToKnow(userObject.getProperty("HowToKnow") != null ? userObject.getProperty("HowToKnow").toString() : "");
					//userDetails.setInterest(userObject.getProperty("Interest") != null ? userObject.getProperty("UserID").toString() : "");
					userDetails.setAlertEmail(userObject.getProperty("AlertEmail") != null ? userObject.getProperty("AlertEmail").toString() : "");
					userDetails.setTotalSaving(userObject.getProperty("TotalSaving") != null ? userObject.getProperty("TotalSaving").toString() : "0");
					userDetails.setStatus(userObject.getProperty("Status") != null ? userObject.getProperty("Status").toString() : "");
					userDetails.setDeviceIndentifier(userObject.getProperty("deviceidentifier") != null ? userObject.getProperty("deviceidentifier").toString() : "");
					userDetails.setPromocode(userObject.getProperty("promocode") != null ? userObject.getProperty("promocode").toString() : "");
					userDetails.setSource(userObject.getProperty("source") != null ? userObject.getProperty("source").toString() : "0");
					//userDetails.setPreffredlang(userObject.getProperty("preffredlang") != null ? userObject.getProperty("preffredlang").toString() : "");
					userDetails.setLang1(userObject.getProperty("lang1") != null ? userObject.getProperty("lang1").toString() : "");
					userDetails.setLang2(userObject.getProperty("lang2") != null ? userObject.getProperty("lang2").toString() : "");
					userDetails.setRadius(userObject.getProperty("radius") != null ? Integer.parseInt(userObject.getProperty("radius").toString()) : 0);
					userDetails.setAddress1((userObject.getProperty("Address1") != null ? userObject.getProperty("Address1").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("Address1").toString());
					userDetails.setAddress2((userObject.getProperty("Address2") != null ? userObject.getProperty("Address2").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("Address2").toString());
					userDetails.setZipcode((userObject.getProperty("Pincode") != null ? userObject.getProperty("Pincode").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("Pincode").toString());

					userDetails.setCountry((userObject.getProperty("Country") != null ? userObject.getProperty("Country").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("Country").toString());
					userDetails.setMobileNumber((userObject.getProperty("MobileNumber") != null ? userObject.getProperty("MobileNumber").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("MobileNumber").toString());
					userDetails.setCategories((userObject.getProperty("categorySelect") != null ? userObject.getProperty("categorySelect").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("categorySelect").toString());

					//DataUtil.priLang = userObject.getProperty("lang1") != null ? userObject.getProperty("lang1").toString() : "";
					//DataUtil.secLang = userObject.getProperty("lang2") != null ? userObject.getProperty("lang2").toString() : "";
					DataUtil.radius = userObject.getProperty("radius") != null ? Integer.parseInt(userObject.getProperty("radius").toString()) : 0;

					//userDetails.setCity((userObject.getProperty("State") != null ? userObject.getProperty("State").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("State").toString());



					if(userDetails.getCategories() != null && userDetails.getCategories().length() > 0) {
						if(userDetails.getCategories().indexOf("/") != -1) {
							String category[] = userDetails.getCategories().split("/");
							ArrayList<Category> categoryList = new ArrayList<Category>();
							Category c = null;

							for(String str : category) {
								c = new Category();
								Log.i(Constants.TAG, "str --- > " + str );
								if(str.indexOf("*") != -1) {
									
									int pos = str.indexOf("*");
									
								
									
									//String s[] = str.split("*");
									Log.i(Constants.TAG, " pos ---------- > " + pos);
									String categoryName = str.substring(0, pos);
									Log.i(Constants.TAG, " Category Name ------ > " + categoryName);
									
									int categoryId = Integer.parseInt(str.substring((pos + 1) , str.length() ));
									Log.i(Constants.TAG, "Category Id --- > " + categoryId + " :: Category Name --- >" + categoryName );
									
									c.setCategoryId(categoryId);
									c.setCategoryName(categoryName);
									categoryList.add(c);
									
								}else {
									Log.i(Constants.TAG, " * not found");
								}
							}
							DataUtil.selectedCategory = categoryList;
						}else {
							Log.i(Constants.TAG, " / not found");
						}
					}

					/*
					//Log.i(Constants.TAG, "UserID ---- > " + userDetails.getUserId());
					//Log.i(Constants.TAG, "FirstName ---- > " + userDetails.getFirstName());
					//Log.i(Constants.TAG, "LastName ---- > " + userDetails.getLastName());
					//Log.i(Constants.TAG, "MobileNumber ---- > " + userDetails.getMobileNumber());
					//Log.i(Constants.TAG, "Address1 ---- > " + userDetails.getAddress1());
					//Log.i(Constants.TAG, "Address2 ---- > " + userDetails.getAddress2());
					//Log.i(Constants.TAG, "City  ---- > " + userDetails.getCity());
					//Log.i(Constants.TAG, "Country ---- > " + userDetails.getCountry());

					//Log.i(Constants.TAG, "DOB ---- > " + userDetails.getDateOfBirth());
					//Log.i(Constants.TAG, "Age  ---- > " + userDetails.getAge());
					//Log.i(Constants.TAG, "Gender ---- > " + userDetails.getGender());
					//Log.i(Constants.TAG, "EmailID ---- > " + userDetails.getEmail());
					//Log.i(Constants.TAG, "UserName ---- > " + userDetails.getUserName());
					//Log.i(Constants.TAG, "Password  ---- > " + userDetails.getPassword());
					//Log.i(Constants.TAG, "HowToKnow ---- > " + userDetails.getHowToKnow());
					//Log.i(Constants.TAG, "Interest ---- > " + userDetails.getInterest());
					//Log.i(Constants.TAG, "AlertEmail ---- > " + userDetails.getAlertEmail());
					//Log.i(Constants.TAG, "TotalSaving ---- > " + userDetails.getTotalSaving());
					//Log.i(Constants.TAG, "Status ---- > " + userDetails.getStatus());
					//Log.i(Constants.TAG, "Device Inde ---- > " + userDetails.getDeviceIndentifier());
					//Log.i(Constants.TAG, "Promo Code ---- > " + userDetails.getPromocode());
					//Log.i(Constants.TAG, "Sorce ---- > " + userDetails.getSource());
					//Log.i(Constants.TAG, "PreLan ---- > " + userDetails.getPreffredlang());
					//Log.i(Constants.TAG, "Lang1 ---- > " + userDetails.getLang1());
					//Log.i(Constants.TAG, "Lang2 ---- > " + userDetails.getLang2());
					//Log.i(Constants.TAG, "Radius ---- > " + userDetails.getRadius());
					 */



					Log.i(Constants.TAG, "Selected Categories  ---- > " + userDetails.getCategories());

				}catch(Exception e) {
					e.printStackTrace();
				}
				DataUtil.userDetails = userDetails;
				//				try {
				//					List<Category> categoryList = WSSender.sendSearchAllCategoryRequest();
				//					DataUtil.categoryList = categoryList; 
				////					CharSequence categories[] = new CharSequence[categoryList.size()];
				////					
				////					int count = 0;
				////					for(Category category : categoryList) {
				////						categories[count] = category.getCategoryName();
				////						count++;
				////					}
				////					
				////					DataUtil.categories = categories;
				//				
				//				
				//				}catch(Exception e) {
				//					e.printStackTrace();
				//					Log.e(Constants.TAG, " Error while getting category List --- > " + e.getLocalizedMessage());
				//				}

			}else {
				return null;
			}
		}else {
			return null;
		}




		return userDetails;
	}


	public static List<Coupon> parseSearchCouponResponse(SoapObject soapRequest) {
		List<Coupon> couponList = new ArrayList<Coupon>();
		Log.i(Constants.TAG, "# WSResponseParser # ---- parseSearchCouponResponse() --- " + soapRequest);
		if(soapRequest == null) {
			return couponList;
		}
		try {
			//Log.i(Constants.TAG, "# WSResponseParser # ---- count  --- " + soapRequest.getPropertyCount());

			for(int j = 0; j < soapRequest.getPropertyCount() ; j++) {
				SoapObject couponObject = (SoapObject)soapRequest.getProperty(j);
				Coupon coupon = new Coupon();

				long CouponID = Integer.parseInt(couponObject.getProperty("CouponID").toString());
				String MerchantID = couponObject.getProperty("MerchantID").toString();
				long StoreID = Integer.parseInt(couponObject.getProperty("StoreID").toString());
				String storeName = (couponObject.getProperty("StoreName") != null ? couponObject.getProperty("StoreName").toString() : "").equalsIgnoreCase("anyType{}") ? "" : couponObject.getProperty("StoreName").toString();
				String address1 = (couponObject.getProperty("Address1") != null ? couponObject.getProperty("Address1").toString() : "").equalsIgnoreCase("anyType{}") ? "" : couponObject.getProperty("Address1").toString();
				String address2 = (couponObject.getProperty("Address2") != null ? couponObject.getProperty("Address2").toString() : "").equalsIgnoreCase("anyType{}") ? "" : couponObject.getProperty("Address2").toString();
				String address3 = "";//couponObject.getProperty("Address3").toString();
				String location = (couponObject.getProperty("City") != null ? couponObject.getProperty("City").toString() : "").equalsIgnoreCase("anyType{}") ? "" : couponObject.getProperty("City").toString();
				String country = (couponObject.getProperty("Country") != null ? couponObject.getProperty("Country").toString() : "").equalsIgnoreCase("anyType{}") ? "" : couponObject.getProperty("Country").toString();
				String CouponName = couponObject.getProperty("CouponName").toString();
				//String CouponNameDE = couponObject.getProperty("CouponNameDE").toString();
				String ShortDescription = couponObject.getProperty("ShortDescription").toString();
				//String ShortDescriptionDE = couponObject.getProperty("ShortDescriptionDE").toString();
				String LongDescription = couponObject.getProperty("LongDescription").toString();
				//String LongDescriptionDE = couponObject.getProperty("LongDescriptionDE").toString();
				String ActualValue = couponObject.getProperty("ActualValue").toString();
				String ValueOffered = couponObject.getProperty("ValueOffered").toString();
				String ImagePath = couponObject.getProperty("ImagePath").toString();
				String distance = (couponObject.getProperty("distance") != null ? couponObject.getProperty("distance").toString() : "").equalsIgnoreCase("anyType{}") ? "" : couponObject.getProperty("distance").toString();

				String Barcode = couponObject.getProperty("Barcode") != null ? couponObject.getProperty("Barcode").toString() : "";
				String ExpiryDate = couponObject.getProperty("ExpiryDate").toString();
				float lati = couponObject.getProperty("Latitude") != null? Float.parseFloat(couponObject.getProperty("Latitude").toString()) : 0;
				float longi = couponObject.getProperty("Longitude") != null? Float.parseFloat(couponObject.getProperty("Longitude").toString()) : 0;
				int featured = couponObject.getProperty("featured") != null? Integer.parseInt(couponObject.getProperty("featured").toString()) : 0;
				//String phoneNo  = couponObject.getProperty("ContactNumber") != null? couponObject.getProperty("ContactNumber").toString() : "";
				String webAddress = 	couponObject.getProperty("WebAddress") != null? couponObject.getProperty("WebAddress").toString() : "";

				int noOfDays = couponObject.getProperty("noofdays") != null? Integer.parseInt(couponObject.getProperty("noofdays").toString()) : 0;

				//String Clicks = couponObject.getProperty("Clicks").toString();
				//String Active = couponObject.getProperty("Active").toString();
				String Category = couponObject.getProperty("Category").toString();
				String SubCategory = couponObject.getProperty("SubCategory").toString();
				//String StartFromDate = couponObject.getProperty("StartFromDate").toString();

				/*String StartToDate = couponObject.getProperty("StartToDate").toString();
				String ExpiryFromDate = couponObject.getProperty("ExpiryFromDate").toString();
				String ExpiryToDate = couponObject.getProperty("ExpiryToDate").toString();
				String radiocouponname= couponObject.getProperty("radiocouponname").toString();
				String radiostartdate= couponObject.getProperty("radiostartdate").toString();
				String radioexpirydate= couponObject.getProperty("radioexpirydate").toString();
				String radioshopname= couponObject.getProperty("radioshopname").toString();
				 */

				/*
				//Log.i(Constants.TAG, "# ****************************************************** #");
				//Log.i(Constants.TAG, "# WSResponseParser # ---- CouponName --------- > " + CouponName);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- ShortDescription --- > " + ShortDescription);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- ValueOffered ------- > " + ValueOffered);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- ImagePath ---------- > " + ImagePath);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Category ----------- > " + Category);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- ExpiryToDate ------- > " + SubCategory);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Barcode ------------ > " + Barcode);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Address1 ----------- > " + address1);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Address2 ----------- > " + address2);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Address3 ----------- > " + address3);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- StoreName ---------- > " + storeName);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Location ----------- > " + location);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Country ------------ > " + country);
				 */
				coupon.setDistance(distance);
				coupon.setCouponId(CouponID);
				coupon.setStoreId(StoreID);
				coupon.setCategory(Category);
				coupon.setSubCategory(SubCategory);
				coupon.setExpiryDate(ExpiryDate);
				coupon.setImagePath(ImagePath);
				coupon.setBarcode(Barcode);
				coupon.setAddress1(address1);
				coupon.setAddress2(address2);
				coupon.setAddress3(address3);
				coupon.setLocation(location);
				coupon.setCountry(country);
				coupon.setStoreName(storeName);
				coupon.setLati(lati);
				coupon.setLongi(longi);
				coupon.setFeatured(featured);
				coupon.setNoDaysToExpiry(noOfDays);
				//coupon.setPhoneNo(phoneNo);
				coupon.setWebAddress(webAddress);

				//if(DataUtil.isEnglish) {
				coupon.setCouponName(CouponName);
				coupon.setShortDesc(ShortDescription);
				coupon.setDesc(LongDescription);
				//			}else {
				//				coupon.setCouponName(CouponNameDE);
				//				coupon.setShortDesc(ShortDescriptionDE);
				//				coupon.setDesc(LongDescriptionDE);
				//			}
				couponList.add(coupon);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		//Log.i(Constants.TAG, "# WSResponseParser # ----CouponListSize  " + couponList.size());

		return couponList;
	}


	public static List<Category> parseCategorySearchResponse(SoapObject soapRequest) {
		Log.i(Constants.TAG, "# WSResponseParser # ---- parseCategorySearchResponse() --- " + soapRequest);
		Log.i(Constants.TAG, "# WSResponseParser # ---- parseCategorySearchResponse() --- " + soapRequest.getPropertyCount());
		List<Category> categoryList = new ArrayList<Category>();
		//Log.i(Constants.TAG, "# WSResponseParser # ---- " + soapRequest);
		try {
			for(int j = 0; j < soapRequest.getPropertyCount() ; j++) {
				//Log.i(Constants.TAG, "# WSResponseParser # ---- " + soapRequest.getProperty(j));
				SoapObject categories = (SoapObject)soapRequest.getProperty(j);
				String categoryId = categories.getProperty("CategoryID").toString(); 
				String categoryName = categories.getProperty("CategoryName").toString();
				String imagePath = categories.getProperty("Imagepath").toString();
				String count = categories.getProperty("Count").toString();
				Log.i(Constants.TAG, "# WSResponseParser # ---- categoryId --- > " + categoryId + ":: categoryName ---- > " + categoryName+ ":: ImagePath ----> " + imagePath+":: Count --->" + count);
				Category category = new Category();
				category.setCategoryId(Integer.parseInt(categoryId));
				category.setCategoryName(categoryName);
				category.setImagePath(imagePath);
				category.setCount(Integer.parseInt(count));
				categoryList.add(category);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}


	public static List<SubCategory> parseSubCategorySearchResponse(SoapObject soapRequest) {
		//Log.i(Constants.TAG, "# WSResponseParser # ---- parseSubCategorySearchResponse() --- " + soapRequest);
		try {
			if(soapRequest != null) {
				List<SubCategory> subCategoryList = new ArrayList<SubCategory>();
				for(int j = 0; j < soapRequest.getPropertyCount() ; j++) {
					//Log.i(Constants.TAG, "# WSResponseParser # ---- " + soapRequest.getProperty(j));
					SoapObject categories = (SoapObject)soapRequest.getProperty(j);
					String categoryId = categories.getProperty("SubCategoryID").toString(); 
					String categoryName = categories.getProperty("SubCategoryName").toString();	
					//Log.i(Constants.TAG, "# WSResponseParser # ---- subCategoryId --- > " + categoryId + ":: subCategoryName ---- > " + categoryName);
					SubCategory subCategory = new SubCategory();
					subCategory.setSubCategoryId(Integer.parseInt(categoryId));
					subCategory.setSubCategoryName(categoryName);
					subCategoryList.add(subCategory);
				}
				return subCategoryList;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static RegistrationResponse parseRegistrationResponse(SoapObject resultObject) {
		Log.i(Constants.TAG, "# WSResponseParser # ---- parseRegistrationResponse() --- " + resultObject);

		RegistrationResponse registrationResponse = new RegistrationResponse();
		try {
			if(resultObject != null) {
				String isSuccess = resultObject.getProperty("Status").toString();
				StringBuffer sb = new StringBuffer();
				SoapObject messagesObject = (SoapObject)resultObject.getProperty("Messages");
				for(int i = 0 ; i < messagesObject.getPropertyCount() ; i++) {
					sb.append(messagesObject.getProperty(i).toString());
				}
				Log.i(Constants.TAG, "# WSResponseParser # ---- IsSuccess ---- > " + isSuccess);
				Log.i(Constants.TAG, "# WSResponseParser # ---- Messages ----- > " + sb.toString()) ;
				if(isSuccess.equalsIgnoreCase("true")) {
					registrationResponse.setSuccess(true);
				}else {
					registrationResponse.setSuccess(false);
				}
				registrationResponse.setMessage(sb.toString());
			}else {
				registrationResponse.setSuccess(false);
				registrationResponse.setMessage("Error occurred while registration. Please try again later!");
				return registrationResponse;
			}
		}catch(Exception e) {

		}
		return registrationResponse;
	}


	public static AddToWalletResponse parseAddToWalletResponse(SoapObject resultObject) {
		//Log.i(Constants.TAG, "# WSResponseParser # ---- parseAddToWalletResponse() --- " + resultObject);
		AddToWalletResponse addToWalletResponse = new AddToWalletResponse();
		try {
			if(resultObject != null) {
				String isSuccess = resultObject.getProperty("Status").toString();
				StringBuffer sb = new StringBuffer();
				SoapObject messagesObject = (SoapObject)resultObject.getProperty("Messages");
				for(int i = 0 ; i < messagesObject.getPropertyCount() ; i++) {
					sb.append(messagesObject.getProperty(i).toString());
				}
				//Log.i(Constants.TAG, "# WSResponseParser # ---- IsSuccess ---- > " + isSuccess);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Messages ----- > " + sb.toString()) ;
				if(isSuccess.equalsIgnoreCase("true")) {
					addToWalletResponse.setSuccess(true);
				}else {
					addToWalletResponse.setSuccess(false);
				}
				addToWalletResponse.setMessage(sb.toString());
			}else {
				addToWalletResponse.setSuccess(false);
				addToWalletResponse.setMessage("Error occurred while adding coupon to wallet. Please try again later!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return addToWalletResponse;		
	}


	public static boolean parseValidateCouponResponse(SoapObject resultObject) {
		//Log.i(Constants.TAG, "# WSResponseParser # ---- parseValidateCouponResponse() --- " + resultObject);
		try {
			if(resultObject != null) {
				SoapObject obj = (SoapObject)resultObject.getProperty("Active");
				//Log.i(Constants.TAG, "# WSResponseParser # ---- obj --- " + obj);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- obj --- > " + obj.getProperty("active").toString());
				if(obj.getProperty("active").toString().equalsIgnoreCase("0")) {
					return true;
				}else {
					return false;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;


	}

	public static UseCouponResponse parseUseCouponResponse(SoapObject resultObject) {
		//Log.i(Constants.TAG, "# WSResponseParser # ---- sendUseCouponRequest() --- " + resultObject);
		UseCouponResponse useCouponResponse = null;
		try {
			if(resultObject != null) {
				useCouponResponse = new UseCouponResponse();
				String isSuccess = resultObject.getProperty("Status").toString();
				StringBuffer sb = new StringBuffer();
				SoapObject messagesObject = (SoapObject)resultObject.getProperty("Messages");
				for(int i = 0 ; i < messagesObject.getPropertyCount() ; i++) {
					sb.append(messagesObject.getProperty(i).toString());
				}
				//Log.i(Constants.TAG, "# WSResponseParser # ---- IsSuccess ---- > " + isSuccess);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Messages ----- > " + sb.toString()) ;
				if(isSuccess.equalsIgnoreCase("true")) { 
					useCouponResponse.setSuccess(true);
				}else {
					useCouponResponse.setSuccess(false);
				}
				useCouponResponse.setMessage(sb.toString());
				return useCouponResponse;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;	
	}




	public static boolean parseSendFeedbackResponse(SoapObject resultObject) {
		//Log.i(Constants.TAG, "# WSResponseParser # ---- parseSendFeedbackResponse() --- " );
		try {
			if(resultObject != null) {
				String isSuccess = resultObject.getProperty("Status").toString();
				if(isSuccess.equalsIgnoreCase("true")) {
					return true;
				}else {
					return false;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	public static List<FAQ> parseSendFAQResponse(SoapObject resultObject){
		List<FAQ> faqList = new ArrayList<FAQ>();
		//Log.i(Constants.TAG, "# WSResponseParser # ---- parseSendFAQResponse() --- " );
		try {
			if(resultObject != null) {
				for(int j = 0; j < resultObject.getPropertyCount() ; j++) {
					SoapObject faqObject = (SoapObject)resultObject.getProperty(j);
					String faqId = faqObject.getProperty("FAQID") != null ? faqObject.getProperty("FAQID").toString(): ""; 
					String question = faqObject.getProperty("Question") != null ? faqObject.getProperty("Question").toString(): "";	
					String answer = faqObject.getProperty("Answer") != null? faqObject.getProperty("Answer").toString() : "";
					//Log.i(Constants.TAG, "# WSResponseParser # ---- FAQ ID  --- > " + faqId + ":: QUESTION ---- > " + question);
					FAQ faq = new FAQ();
					faq.setFaqId(Integer.parseInt(faqId));
					faq.setQuestion(question);
					faq.setAnswer(answer);
					faqList.add(faq);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return faqList;
	}

	public static FAQ parseSendFAQDetailsResponse(SoapObject resultObject){
		//Log.i(Constants.TAG, "# WSResponseParser # ---- sendFAQDetailsRequest() --- "  + resultObject);
		FAQ faq = new FAQ();
		try {
			if(resultObject != null) {
				for(int j = 0; j < resultObject.getPropertyCount() ; j++) {
					SoapObject faqObject = (SoapObject)resultObject.getProperty(j);
					String faqId = faqObject.getProperty("FAQID").toString(); 
					String question = faqObject.getProperty("Question").toString();	
					String answer = faqObject.getProperty("Answer").toString();
					//Log.i(Constants.TAG, "# WSResponseParser # ---- FAQ ID  --- > " + faqId + ":: QUESTION ---- > " + question + "---Answer --- > " + answer);
					faq.setFaqId(Integer.parseInt(faqId));
					faq.setQuestion(question);
					faq.setAnswer(answer);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return faq;
	}


	public static List<Store> parseSelectStoreReponse(SoapObject resultObject) {


		List<Store> storeList = new ArrayList<Store>();
		Log.i(Constants.TAG, "# WSResponseParser # ---- parseSelectStoreReponse() --- " + resultObject);
		try {
			if(resultObject != null) {

				//Log.i(Constants.TAG, "# WSResponseParser # ---- Store Count ---- > " + resultObject.getPropertyCount());
				for(int j = 0; j < resultObject.getPropertyCount() ; j++) {
					SoapObject storeObject = (SoapObject)resultObject.getProperty(j);
					String storeId = storeObject.getProperty("StoreID") != null ? storeObject.getProperty("StoreID").toString() : "";; 
					String storeName = (storeObject.getProperty("StoreName") != null ? storeObject.getProperty("StoreName").toString() : "").equalsIgnoreCase("anyType{}") ? "" : storeObject.getProperty("StoreName").toString();
					String logoPath = storeObject.getProperty("logopath") != null ? storeObject.getProperty("logopath").toString() : "";
					String latitude = storeObject.getProperty("Latitude") != null ? storeObject.getProperty("Latitude").toString() : "";
					String longitude = storeObject.getProperty("Longitude") != null ? storeObject.getProperty("Longitude").toString() : "";
					String distance = storeObject.getProperty("distance") != null ? storeObject.getProperty("distance").toString() : "0";	
					String address1 = (storeObject.getProperty("Address1") != null ? storeObject.getProperty("Address1").toString() : "").equalsIgnoreCase("anyType{}") ? "" : storeObject.getProperty("Address1").toString();
					String address2 = (storeObject.getProperty("Address2") != null ? storeObject.getProperty("Address2").toString() : "").equalsIgnoreCase("anyType{}") ? "" : storeObject.getProperty("Address2").toString();
					String address3 = "";//storeObject.getProperty("Address3") != null ? storeObject.getProperty("Address3").toString() : "";
					String country = storeObject.getProperty("Country") != null ? storeObject.getProperty("Country").toString() :"";
					String webAddress = storeObject.getProperty("WebAddress") != null ? storeObject.getProperty("WebAddress").toString() :"";
					String accountType = storeObject.getProperty("AccountType") != null ? storeObject.getProperty("AccountType").toString() :"";
					
					
					//Log.i(Constants.TAG, "Web Address --- > " + webAddress);
					//String pincode = storeObject.getProperty("Country") != null ? storeObject.getProperty("Country").toString(): "";

					Store store = new Store();				
					//Log.i(Constants.TAG, "# WSResponseParser # ---- StoreID  --- > " + storeId + ":: StoreName ---- > " + storeName);
					store.setStoreId(Integer.parseInt(storeId));
					store.setStoreName(storeName);
					store.setImageUrl(logoPath);
					store.setLatitude(latitude);
					store.setLongitude(longitude);
					store.setDistance(distance);
					store.setAddress1(address1);
					store.setAddress2(address2);
					store.setAddress3(address3);
					store.setCountry(country);
					store.setWebAddress(webAddress);
					store.setAccountType(accountType);
					//store.setPincode(pincode);
					storeList.add(store);

				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return storeList;
	}



	public static GeneralResponse parseForgotPasswordResponse(SoapObject resultObject) {
		//Log.i(Constants.TAG, "# WSResponseParser # ---- parseForgotPasswordResponse() --- " + resultObject);
		GeneralResponse generalResponse = new GeneralResponse();
		try {
			if(resultObject != null) {
				String isSuccess = resultObject.getProperty("Status").toString();
				StringBuffer sb = new StringBuffer();
				SoapObject messagesObject = (SoapObject)resultObject.getProperty("Messages");
				for(int i = 0 ; i < messagesObject.getPropertyCount() ; i++) {
					sb.append(messagesObject.getProperty(i).toString());
				}
				//Log.i(Constants.TAG, "# WSResponseParser # ---- IsSuccess ---- > " + isSuccess);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Messages ----- > " + sb.toString()) ;
				if(isSuccess.equalsIgnoreCase("true")) {
					generalResponse.setSuccess(true);
				}else {
					generalResponse.setSuccess(false);
				}
				generalResponse.setMessage(sb.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return generalResponse;
	}


	public static UpdateSettingsResponse parseUpdateSettingsResponse(SoapObject resultObject) {
		//Log.i(Constants.TAG, "# WSResponseParser # ---- parseUpdateSettingsResponse() --- " );
		UpdateSettingsResponse updateSettingsResponse = new UpdateSettingsResponse();
		try {
			if(resultObject != null) {
				String isSuccess = resultObject.getProperty("Status").toString();
				StringBuffer sb = new StringBuffer();
				SoapObject messagesObject = (SoapObject)resultObject.getProperty("Messages");
				for(int i = 0 ; i < messagesObject.getPropertyCount() ; i++) {
					sb.append(messagesObject.getProperty(i).toString());
				}
				//Log.i(Constants.TAG, "# WSResponseParser # ---- IsSuccess ---- > " + isSuccess);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Messages ----- > " + sb.toString()) ;
				if(isSuccess.equalsIgnoreCase("true")) {
					updateSettingsResponse.setSuccess(true);
				}else {
					updateSettingsResponse.setSuccess(false);
				}
				updateSettingsResponse.setMessage(sb.toString());
			}else {
				updateSettingsResponse.setSuccess(false);
				updateSettingsResponse.setMessage("Error occurred while registration. Please try again later!");
				return updateSettingsResponse;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return updateSettingsResponse;
	}


	public static GeneralResponse parseAddAndRemoveStoreResponse(SoapObject resultObject) {
		//Log.i(Constants.TAG, "======= parseAddAndRemoveStoreResponse() ==== " + resultObject); 
		GeneralResponse generalResponse =  new GeneralResponse();
		try {
			if(resultObject != null){
				String isSuccess = resultObject.getProperty("Status").toString();
				StringBuffer sb = new StringBuffer();
				SoapObject messagesObject = (SoapObject)resultObject.getProperty("Messages");
				for(int i = 0 ; i < messagesObject.getPropertyCount() ; i++) {
					sb.append(messagesObject.getProperty(i).toString());
				}
				//Log.i(Constants.TAG, "# WSResponseParser # ---- IsSuccess ---- > " + isSuccess);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Messages ----- > " + sb.toString()) ;
				if(isSuccess.equalsIgnoreCase("true")) {
					generalResponse.setSuccess(true);
				}else {
					generalResponse.setSuccess(false);
				}
				generalResponse.setMessage(sb.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return generalResponse;

	}


	public static GeneralResponse parseGeneralResponse(SoapObject resultObject) {
		//Log.i(Constants.TAG, "======= parseGeneralResponse() ==== " + resultObject); 
		GeneralResponse generalResponse =  new GeneralResponse();
		try {
			if(resultObject != null){
				String isSuccess = resultObject.getProperty("Status").toString();
				StringBuffer sb = new StringBuffer();
				SoapObject messagesObject = (SoapObject)resultObject.getProperty("Messages");
				for(int i = 0 ; i < messagesObject.getPropertyCount() ; i++) {
					sb.append(messagesObject.getProperty(i).toString());
				}
				//Log.i(Constants.TAG, "# WSResponseParser # ---- IsSuccess ---- > " + isSuccess);
				//Log.i(Constants.TAG, "# WSResponseParser # ---- Messages ----- > " + sb.toString()) ;
				if(isSuccess.equalsIgnoreCase("true")) {
					generalResponse.setSuccess(true);
				}else {
					generalResponse.setSuccess(false);
				}
				generalResponse.setMessage(sb.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return generalResponse;
	}



	public static int parseSubCategoryCountResponse(SoapObject resultObject) {
		//Log.i(Constants.TAG, "======= parseSubCategoryCountResponse() ==== " + resultObject);
		int count = 0;
		try{
			String countStr = ((SoapObject)resultObject.getProperty(0)).getProperty("Count").toString();
			//Log.i(Constants.TAG," Count Str ------- > " + countStr);
			count = Integer.parseInt(countStr);
			//Log.i(Constants.TAG," Count ---- >"  + count );
		}catch(NumberFormatException nfe) {
			nfe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally {

		}
		return count;

	}



	public static List<FAQ> parseInboxMessage(SoapObject resultObject) {
		Log.i(Constants.TAG, "======= parseInboxMessage() ==== " + resultObject);
		List<FAQ> faqList = new ArrayList<FAQ>();
		//Log.i(Constants.TAG, "# WSResponseParser # ---- parseSendFAQResponse() --- " );
		try {
			if(resultObject != null) {
				for(int j = 0; j < resultObject.getPropertyCount() ; j++) {
					SoapObject faqObject = (SoapObject)resultObject.getProperty(j);
					String faqId = faqObject.getProperty("FAQID") != null ? faqObject.getProperty("FAQID").toString(): ""; 
					String question = faqObject.getProperty("Question") != null ? faqObject.getProperty("Question").toString(): "";	
					String answer = faqObject.getProperty("Answer") != null? faqObject.getProperty("Answer").toString() : "";
					int  active = faqObject.getProperty("active") != null? Integer.parseInt(faqObject.getProperty("active").toString()) : 0;;
					String adminId = faqObject.getProperty("Adminid") != null? faqObject.getProperty("Adminid").toString() : ""; 
					//Log.i(Constants.TAG, "# WSResponseParser # ---- FAQ ID  --- > " + faqId + ":: QUESTION ---- > " + question);
					FAQ faq = new FAQ();
					faq.setFaqId(Integer.parseInt(faqId));
					faq.setQuestion(question);
					faq.setAnswer(answer);
					faq.setActive(active);
					faq.setAdminId(adminId);
					faqList.add(faq);

				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return faqList;

	}


	public static List<Competion> parseCompetionMessage(SoapObject resultObject) {
		Log.i(Constants.TAG, "======= parseCompetionMessage() ==== " + resultObject);
		List<Competion> competionList = new ArrayList<Competion>();
		try {
			if(resultObject != null) {
				Competion competion = null;
				//SoapObject resObject = (SoapObject) resultObject.getProperty(0);
				Log.i(Constants.TAG, " --- Count ------------ > " + resultObject.getPropertyCount() );
				for(int j = 0; j < resultObject.getPropertyCount() ; j++) {
					SoapObject faqObject = (SoapObject)resultObject.getProperty(j);
					String competionName = faqObject.getProperty("CompetionName") != null ? faqObject.getProperty("CompetionName").toString(): ""; 
					String price = faqObject.getProperty("CompetionPrize") != null ? faqObject.getProperty("CompetionPrize").toString(): "";	
					String description = faqObject.getProperty("CompetionDesc") != null? faqObject.getProperty("CompetionDesc").toString() : "";
					String type = faqObject.getProperty("CompetionType") != null? faqObject.getProperty("CompetionType").toString() : "";
					
//					String competionNameDN = faqObject.getProperty("CompetionNameDN") != null ? faqObject.getProperty("CompetionNameDN").toString(): ""; 
//					String priceDN = faqObject.getProperty("CompetionPrizeDN") != null ? faqObject.getProperty("CompetionPrizeDN").toString(): "";	
//					String descriptionDN = faqObject.getProperty("CompetionDescDN") != null? faqObject.getProperty("CompetionDescDN").toString() : "";
					
					Log.i(Constants.TAG, " Comp Name -- > " + competionName + ":: Price --- > " +  price + "::: Desc --- > " + description);
					//Log.i(Constants.TAG, " Comp Name -- > " + competionName + ":: Price --- > " +  price + "::: Desc --- > " + description + " Comp Name DN-- > " + competionNameDN + ":: PriceDN --- > " +  priceDN + "::: Desc --- > " + descriptionDN + "::: Type --- > " + type);
					
					competion = new Competion();
					competion.setComprtionName(competionName);
					competion.setDescription(description);
					competion.setPrice(price);
					
//					competion.setComprtionNameDN(competionNameDN);
//					competion.setDescriptionDN(descriptionDN);
//					competion.setPriceDN(priceDN);
					competion.setCompetionType(type);
					competionList.add(competion);
							
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return competionList;
	}

	public static List<UserDetails> parseUserDetails(SoapObject soapRequest) {
		Log.i(Constants.TAG, "======= parseUserDetails() ==== " + soapRequest);
		List<UserDetails> userList = new ArrayList<UserDetails>();
		if(soapRequest != null) {
			if(soapRequest.getPropertyCount() != 0) {
				for(int i = 0 ; i < soapRequest.getPropertyCount() ; i++) {
					UserDetails userDetails = new UserDetails();
					try {
						SoapObject userObject = (SoapObject) soapRequest.getProperty(i);
						userDetails.setUserId(userObject.getProperty("UserID") != null ? Long.parseLong(userObject.getProperty("UserID").toString()) : 0); 
						userDetails.setFirstName((userObject.getProperty("FirstName") != null ? userObject.getProperty("FirstName").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("FirstName").toString());
						userDetails.setHowToKnow((userObject.getProperty("HowToKnow") != null ? userObject.getProperty("HowToKnow").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("HowToKnow").toString());
						userDetails.setAge(userObject.getProperty("Age") != null ? Integer.parseInt(userObject.getProperty("Age").toString()) : 0);
						//userDetails.setAge((userObject.getProperty("Image") != null ? userObject.getProperty("Image").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("Image").toString());
						//userDetails.setLastName((userObject.getProperty("LastName") != null ? userObject.getProperty("LastName").toString() : "").equalsIgnoreCase("anyType{}") ? "" : userObject.getProperty("LastName").toString());
						
						
						
						
						userList.add(userDetails);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return userList;
	}


}
