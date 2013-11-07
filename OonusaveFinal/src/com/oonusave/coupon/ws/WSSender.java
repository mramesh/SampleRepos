package com.oonusave.coupon.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.oonusave.coupon.model.AddToWalletResponse;
import com.oonusave.coupon.model.Category;
import com.oonusave.coupon.model.ChangePassword;
import com.oonusave.coupon.model.Competion;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.model.FAQ;
import com.oonusave.coupon.model.GeneralResponse;
import com.oonusave.coupon.model.LocationInfo;
import com.oonusave.coupon.model.Login;
import com.oonusave.coupon.model.RegistrationResponse;
import com.oonusave.coupon.model.Store;
import com.oonusave.coupon.model.SubCategory;
import com.oonusave.coupon.model.UpdateSettingsResponse;
import com.oonusave.coupon.model.UseCouponResponse;
import com.oonusave.coupon.model.UserDetails;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;

import android.content.Intent;
import android.util.Log;

/**
 * 
 * @author Ramesh
 *
 */

public class WSSender {

	int statusCode = 0;
	String statusMsg = "";
	String sentStatus = "";
	String serverResponse = "";

	/**
	 * 
	 * @param xmlData
	 */

	private void callWS(byte[] xmlData) {
		logMessage(" ------ callWS ----");
		HttpURLConnection conn = null;
		URL url = null;
		StringBuffer buffer = new StringBuffer();
		try {
			url = new URL(WSConstants.wsUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);

			//conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY_MARKER);
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			//conn.setRequestProperty("SOAPAction", "http://tempuri.org/OurRecommendations");
			conn.setRequestProperty("Content-Length", xmlData.length+"");

			conn.getOutputStream().write(xmlData);
			if (conn.getResponseCode() != -1) {
				statusCode = conn.getResponseCode();
				statusMsg = conn.getResponseMessage();
				logMessage(" Status Code ---- > " + statusCode + " ::: ---- Status Msg ------ > " + statusMsg);
				switch (statusCode) {
				case 200:
					conn.connect();
					InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
					BufferedReader buff = new BufferedReader(in);
					String line = "";
					while ((line = buff.readLine()) != null) {
						buffer.append(line);
					}
					serverResponse = buffer.toString();
					logMessage(" ServerResponse -----> " + serverResponse);
					break;
				default:
					sentStatus = "Errorcode -- > " + statusCode + " ::: Status Message -- > " + statusMsg;
				}
			} else {
				sentStatus = "Errorcode -- > " + statusCode + " ::: Status Message -- > " + statusMsg;
			}
		} catch (Exception ce) {
			ce.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param login
	 * @return
	 */


	public static UserDetails sendValidateCredentialRequest(Login login) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ===========> sendValidateCredentialRequest() ");
		//Log.i(Constants.TAG, "# WSSender # ===========> Username ---> " + login.getUsername() + "::: Password --- > " + login.getPassword() + " ::: D-Id - > " + login.getDeviceIndentifier());

		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_VALIDATE_CREDENTIAL);
		soapObject.addProperty("username", login.getUsername());
		soapObject.addProperty("password", login.getPassword());
		//soapObject.addProperty("deviceidentifier", login.getDeviceIndentifier());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_VALIDATE_CREDENTIAL);
		UserDetails userDetails = WSResponseParser.parseLoginResponse(resultObject);
		return userDetails;
	}

	/**
	 * 
	 * @param userDetails
	 * @return
	 */

	public static RegistrationResponse sendRegistreationRequest(UserDetails userDetails)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ===========> sendRegistreationRequest() ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_CREATE_REGISTRATION);

		//Log.i(Constants.TAG, "# WSSender # ===========> FirstName === > " + userDetails.getFirstName() );
		//Log.i(Constants.TAG, "# WSSender # ===========> LastName ==== > " + userDetails.getLastName());
		//Log.i(Constants.TAG, "# WSSender # ===========> EmailID ===== > " + userDetails.getUserName());
		//Log.i(Constants.TAG, "# WSSender # ===========> Password ==== > " + userDetails.getPassword());

		soapObject.addProperty("firstname",userDetails.getFirstName());
		soapObject.addProperty("lastname",userDetails.getLastName());
		soapObject.addProperty("username",userDetails.getUserName());
		soapObject.addProperty("password", userDetails.getPassword());


		soapObject.addProperty("alertemail", "yes");
		//soapObject.addProperty("address1",userDetails.getAddress1());
		//soapObject.addProperty("address2",userDetails.getAddress2());
		//soapObject.addProperty("pincode",userDetails.getZipcode());
		//soapObject.addProperty("city",userDetails.getCity());
		//soapObject.addProperty("state",userDetails.getState());
		//soapObject.addProperty("country",userDetails.getCountry());
		//soapObject.addProperty("mobilenumber",userDetails.getMobileNumber());
		//soapObject.addProperty("emailid",userDetails.getEmail());
		//soapObject.addProperty("gender",userDetails.getGender());
		soapObject.addProperty("deviceidentifier","213123");
		soapObject.addProperty("promocode","promocode");
		soapObject.addProperty("source","source");
		soapObject.addProperty("active","1");

		soapObject.addProperty("interestedcat",userDetails.getCategories());
		soapObject.addProperty("postcode", userDetails.getZipcode());
		soapObject.addProperty("age", userDetails.getAge());
		soapObject.addProperty("gender", userDetails.getGender());
		soapObject.addProperty("refferedby", userDetails.getReferredBy());
		soapObject.addProperty("imageurl","testurl");



		/*

		 */

		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_CREATE_REGISTRATION);
		RegistrationResponse registrationResponse = WSResponseParser.parseRegistrationResponse(resultObject);
		return registrationResponse;
	}


	public static GeneralResponse sendEditProfileRequest(UserDetails userDetails) throws ConnectException,IOException{

		GeneralResponse generalResponse = new GeneralResponse();
		try{
			//Log.i(Constants.TAG, "# WSSender # ===========> sendEditProfileRequest() ");
			SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_EDIT_PROFILE);
			////Log.i(Constants.TAG, "# WSSender # ===========> Country  : " + userDetails.getCountry());
			//Log.i(Constants.TAG, "# WSSender # ===========> Gender :" + userDetails.getGender());
			//Log.i(Constants.TAG, "# WSSender # ===========> Mobile Number :" + userDetails.getMobileNumber());
			//Log.i(Constants.TAG, "# WSSender # ===========> Email :" + userDetails.getEmail());
			//Log.i(Constants.TAG, "# WSSender # ===========> Alert Email :" + userDetails.getAlertEmail());
			//Log.i(Constants.TAG, "# WSSender # ===========> DOB :" + userDetails.getDateOfBirth());


			soapObject.addProperty("userid", DataUtil.userDetails.getUserId());
			soapObject.addProperty("firstname", userDetails.getFirstName());
			soapObject.addProperty("lastname", userDetails.getLastName());
			//soapObject.addProperty("address1", userDetails.getAddress1());
			//soapObject.addProperty("address2", userDetails.getAddress2());
			//soapObject.addProperty("address3","sad");
			soapObject.addProperty("postcode",userDetails.getZipcode());
			//soapObject.addProperty("city",userDetails.getCity());

			//soapObject.addProperty("country",userDetails.getCountry());
			//soapObject.addProperty("mobileno",userDetails.getMobileNumber());

			//soapObject.addProperty("dob",userDetails.getDateOfBirth());
			soapObject.addProperty("age",userDetails.getAge());
			soapObject.addProperty("gender",userDetails.getGender());
			soapObject.addProperty("password", userDetails.getPassword());
			soapObject.addProperty("refferedby", userDetails.getReferredBy());

			//soapObject.addProperty("email",userDetails.getEmail());
			soapObject.addProperty("alertemail",userDetails.getAlertEmail());

			soapObject.addProperty("deviceidentifier","213123");
			soapObject.addProperty("promocode","promocode");
			soapObject.addProperty("source","source");
			SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_EDIT_PROFILE);
			generalResponse = WSResponseParser.parseGeneralResponse(resultObject);


			//			soapObject.addProperty("userid", DataUtil.userDetails.getUserId());
			//			soapObject.addProperty("firstname", userDetails.getFirstName());
			//			soapObject.addProperty("lastname", userDetails.getLastName());
			//			soapObject.addProperty("address1", userDetails.getAddress1());
			//			soapObject.addProperty("address2", userDetails.getAddress2());
			//			//soapObject.addProperty("address3","sad");
			//			soapObject.addProperty("postcode",userDetails.getZipcode());
			//			soapObject.addProperty("city",userDetails.getCity());
			//			
			//			soapObject.addProperty("country",userDetails.getCountry());
			//			soapObject.addProperty("mobileno",userDetails.getMobileNumber());
			//			
			//			soapObject.addProperty("dob",userDetails.getDateOfBirth());
			//			soapObject.addProperty("age",12);
			//			soapObject.addProperty("gender",userDetails.getGender());
			//			soapObject.addProperty("email",userDetails.getEmail());
			//			soapObject.addProperty("alertemail",userDetails.getAlertEmail());
			//
			//			soapObject.addProperty("deviceidentifier","213123");
			//			soapObject.addProperty("promocode","promocode");
			//			soapObject.addProperty("source","source");
			//			SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_EDIT_PROFILE);
			//			generalResponse = WSResponseParser.parseGeneralResponse(resultObject);

		}catch(Exception e){
			e.printStackTrace();
		}
		return generalResponse;
	}


	public static int sendGetSubCategoryCount(int id) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========> sendGetSubCategoryCount()");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_GET_SUBCATEGORY_COUNT);
		soapObject.addProperty("categoryid", id);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_GET_SUBCATEGORY_COUNT);
		return WSResponseParser.parseSubCategoryCountResponse(resultObject);
	}



	/**
	 * 
	 * @param locationInfo
	 * @return
	 */


	public static List<Coupon> sendSearchCouponBasedOnLocationRequest(LocationInfo locationInfo)  throws ConnectException,IOException{
		List<Coupon> couponList = new ArrayList<Coupon>();
		try {
			//Log.i(Constants.TAG, "# WSSender # ==========> sendSearchCouponBasedOnLocationRequest()");
			SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_ALL_COUPON);
			//Log.i(Constants.TAG, "# WSSender # -- Latitude -- >" + DataUtil.locationInfo.getLatitude() + ":: Longitude --> " + DataUtil.locationInfo.getLongitude());
			//Log.i(Constants.TAG, "# WSSender # -- UserID ---- > " + DataUtil.userDetails.getUserId());
			soapObject.addProperty("latitude", DataUtil.locationInfo.getLatitude());
			soapObject.addProperty("longitude", DataUtil.locationInfo.getLongitude());
			soapObject.addProperty("userid", DataUtil.userDetails.getUserId());
			SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_MOBILE_DISPLAY_ALL_COUPON);
			//Log.i(Constants.TAG, "# WSSender # =====resultObject =====> " + resultObject);
			couponList = WSResponseParser.parseSearchCouponResponse(resultObject);
		}catch(Exception e){
			e.printStackTrace();
		}
		return couponList;
	}


	public static List<Coupon> searchCouponBasedOnKeyword(String keyword)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========> searchCouponBasedOnKeyword()");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_SEARCH_COUPON);
		soapObject.addProperty("latitude", DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude", DataUtil.locationInfo.getLongitude());
		soapObject.addProperty("keyword", keyword);
		soapObject.addProperty("userid", DataUtil.userDetails.getUserId());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_MOBILE_SEARCH_COUPON);
		//Log.i(Constants.TAG, "# WSSender # =====resultObject =====> " + resultObject);
		List<Coupon> couponList = WSResponseParser.parseSearchCouponResponse(resultObject);
		return couponList;
	}

	/**
	 * 
	 * @param category
	 * @return
	 */

	public static List sendSearchCouponBasedOnCategoryRequest(Category category)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========> sendSearchCouponBasedOnCategoryRequest() ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_ALL_CATEGORY);

		return null;
	}

	/**
	 * 
	 * @param subCategory
	 * @return
	 */


	public static List<Coupon> sendSearchCouponBasedOnSubCategory(SubCategory subCategory)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========> sendSearchCouponBasedOnSubCategory() ---- > " + subCategory.getSubCategoryId());
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_COUPON_BY_SUB_CATEGORY);
		soapObject.addProperty("latitude", DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude", DataUtil.locationInfo.getLongitude());
		soapObject.addProperty("subcategoryid", subCategory.getSubCategoryId());
		soapObject.addProperty("userid", DataUtil.userDetails.getUserId());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_MOBILE_DISPLAY_COUPON_BY_SUB_CATEGORY);
		//Log.i(Constants.TAG, "# WSSender # =====resultObject =====> " + resultObject);
		List<Coupon> couponList = WSResponseParser.parseSearchCouponResponse(resultObject);
		return couponList;
	}



	public static List<Coupon> sendSearchCouponBasedOnCategory(SubCategory subCategory)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========> sendSearchCouponBasedOnSubCategory() ---- > " + subCategory.getSubCategoryId());
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_COUPON_BY_CATEGORY);
		soapObject.addProperty("latitude", DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude", DataUtil.locationInfo.getLongitude());
		soapObject.addProperty("categoryid", subCategory.getSubCategoryId());
		soapObject.addProperty("userid", DataUtil.userDetails.getUserId());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_MOBILE_DISPLAY_COUPON_BY_CATEGORY);
		//Log.i(Constants.TAG, "# WSSender # =====resultObject =====> " + resultObject);
		List<Coupon> couponList = WSResponseParser.parseSearchCouponResponse(resultObject);
		return couponList;
	}

	/**
	 * 
	 * @return
	 */

	public static List<Category> sendSearchAllCategoryRequest(long userId)  throws ConnectException,IOException{ 
		Log.i(Constants.TAG, "# WSSender # ==========> sendSearchAllCategoryRequest() ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_ALL_CATEGORY);
		soapObject.addProperty("userid", userId);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_MOBILE_DISPLAY_ALL_CATEGORY);
		return WSResponseParser.parseCategorySearchResponse(resultObject);
	}

	/**
	 * 
	 * @param category
	 * @return
	 */


	public static List<SubCategory> sendGetAllSubCategoryForCategory(Category category)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========> sendGetAllSubCategoryForCategory() ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_SUB_CATEGORY);
		soapObject.addProperty("categoryid",category.getCategoryId());
		soapObject.addProperty("userid", DataUtil.userDetails.getUserId());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_MOBILE_DISPLAY_SUB_CATEGORY);
		return WSResponseParser.parseSubCategorySearchResponse(resultObject);

	}

	/**
	 * 
	 * @param userId
	 * @param userName
	 * @param password
	 * @return
	 */


	public static List<Coupon> sendGetAllCouponInWallet(long userId,String userName,String password)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendGetAllCouponInWallet() ");
		//Log.i(Constants.TAG, "# WSSender # ==========>  UserId ----------> " + userId);
		////Log.i(Constants.TAG, "# WSSender # ==========>  sendGetAllCouponInWallet() ");
		////Log.i(Constants.TAG, "# WSSender # ==========>  sendGetAllCouponInWallet() ");
		List<Coupon> couponList = new ArrayList<Coupon>();

		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_ALL_COUPON_IN_WALLET);
		soapObject.addProperty("userid",userId);
		soapObject.addProperty("username",userName);
		soapObject.addProperty("pass",password);
		soapObject.addProperty("latitude", DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude", DataUtil.locationInfo.getLongitude());

		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_DISPLAY_ALL_COUPON_IN_WALLET);
		couponList = WSResponseParser.parseSearchCouponResponse(resultObject);
		return couponList;
	}

	/**
	 * 
	 * @param userId
	 * @param couponId
	 * @return
	 */



	public static AddToWalletResponse sendAddToWalletRequest(int userId,int couponId)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendAddToWalletRequest() ");
		//Log.i(Constants.TAG, "# WSSender # ==========>  CouponID ----> " + couponId);
		//Log.i(Constants.TAG, "# WSSender # ==========>  userId ------> " + userId);
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_INSERT_COUPON_INTO_WALLET);
		soapObject.addProperty("userid",userId);
		soapObject.addProperty("couponid",couponId);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_INSERT_COUPON_INTO_WALLET);
		AddToWalletResponse addToWalletResponse = WSResponseParser.parseAddToWalletResponse(resultObject);
		return addToWalletResponse;
	}
	/**
	 * 
	 * @param userId
	 * @param couponId
	 * @return
	 */

	public static AddToWalletResponse sendRemoveFromWalletRequest(long userId,long couponId)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendRemoveFromWalletRequest() ");
		//Log.i(Constants.TAG, "# WSSender # ==========>  CouponID ----> " + couponId);
		//Log.i(Constants.TAG, "# WSSender # ==========>  userId ------> " + userId);
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_REMOVE_COUPON_FROM_WALLET);
		soapObject.addProperty("userid",userId);
		soapObject.addProperty("couponid",couponId);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_REMOVE_COUPON_FROM_WALLET);
		AddToWalletResponse addToWalletResponse = WSResponseParser.parseAddToWalletResponse(resultObject);
		return addToWalletResponse;
	}
	/**
	 * 
	 * @param userId
	 * @param couponId
	 * @return
	 */

	public static boolean sendValidateCouponRequest(long userId, long couponId)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendValidateCouponRequest() ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_VALIDATE_COUPON);
		soapObject.addProperty("userid",userId);
		soapObject.addProperty("couponid",couponId);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_VALIDATE_COUPON);
		return WSResponseParser.parseValidateCouponResponse(resultObject);

	}

	/**
	 * 
	 * @param userId
	 * @param couponId
	 * @param storeId
	 * @return
	 */

	public static UseCouponResponse sendUseCouponRequest(long userId,long couponId, long storeId)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendUseCouponRequest() ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_INSERT_USED_COUPON);
		soapObject.addProperty("userid",userId);
		soapObject.addProperty("couponid",couponId);
		soapObject.addProperty("storeid",storeId);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_INSERT_USED_COUPON);
		return WSResponseParser.parseUseCouponResponse(resultObject);
	}

	/**
	 * 
	 * @param changePassword
	 * @param userName
	 * @return
	 */

	public static String sendChangePasswordRequest(ChangePassword changePassword,String userName)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendChangePasswordRequest() ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_CHANGE_PASSWORD);
		soapObject.addProperty("username", userName);
		soapObject.addProperty("oldpass", changePassword.getOldPassword());
		soapObject.addProperty("newpass", changePassword.getNewPassword());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_CHANGE_PASSWORD);
		return "success";
	}

	/**
	 * 
	 * 
	 * @param keyword
	 * @return
	 */


	public static List<Store> sendSerachStoresRequest(String keyword)  throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendSerachStoresRequest() ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_SEARCH_STORES);
		soapObject.addProperty("keyword", keyword);	
		soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		soapObject.addProperty("latitude", DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude", DataUtil.locationInfo.getLongitude());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_SEARCH_STORES);
		return WSResponseParser.parseSelectStoreReponse(resultObject);
	}

	/**
	 * 
	 * @return
	 */


	public static List<Store> sendSelectStoresRequest() throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendSelectStoresRequest() ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_SELECT_STORES_FOR_USERS);
		//soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		soapObject.addProperty("latitude", DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude", DataUtil.locationInfo.getLongitude());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_SELECT_STORES_FOR_USERS);
		return WSResponseParser.parseSelectStoreReponse(resultObject);
	}

	/**
	 * 
	 * @param storeId
	 * @return
	 */


	public static List<Coupon> sendSelectCouponsInStore(int storeId) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendSelectCouponsInStore() ");
		List<Coupon> couponList = new ArrayList<Coupon>();
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_ALL_COUPON_IN_STORE);
		soapObject.addProperty("storeid",storeId);
		soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		soapObject.addProperty("latitude", DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude", DataUtil.locationInfo.getLongitude());

		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_DISPLAY_ALL_COUPON_IN_STORE);
		couponList = WSResponseParser.parseSearchCouponResponse(resultObject);
		return couponList;
	}
	/**
	 * 
	 * @param subject
	 * @param message
	 * @return
	 */


	public static boolean sendFeebackRequest(String subject,String message) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendFeebackRequest() ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_FEEDBACK);
		soapObject.addProperty("subject", subject);
		soapObject.addProperty("message", message);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_FEEDBACK);
		return WSResponseParser.parseSendFeedbackResponse(resultObject);
	}

	/**
	 * 
	 * @return
	 */


	public static List<FAQ> sendFAQRequest() throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendFAQRequest() ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_FAQ);
		soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_FAQ);
		return WSResponseParser.parseSendFAQResponse(resultObject);
	}
	/**
	 * 
	 * @param faqID
	 * @return
	 */

	public static FAQ sendFAQDetailsRequest(int faqID) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendFAQDetailsRequest --- > " + faqID);
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_FAQ_DETAIL);
		soapObject.addProperty("FAQID",faqID);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_FAQ_DETAIL);
		return WSResponseParser.parseSendFAQDetailsResponse(resultObject);
	}

	/**
	 * 
	 * @param emailId
	 * @return
	 */


	public static GeneralResponse sendForgotPasswordRequest(String emailId) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendForgotPasswordRequest --- > " + emailId);
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_FORGOT_PASSWORD);
		soapObject.addProperty("username",emailId);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_FORGOT_PASSWORD);
		return WSResponseParser.parseForgotPasswordResponse(resultObject);
	}

	/**
	 * 
	 * @param userDetails
	 * @return
	 */


	//	public static RegistrationResponse sendEditProfile(UserDetails userDetails) throws ConnectException,IOException{
	//		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_EDIT_PROFILE);
	//
	//		soapObject.addProperty("userid",1); // int
	//		soapObject.addProperty("firstname", "");  // string</firstname>
	//		soapObject.addProperty("lastname", ""); //string</lastname>
	//		soapObject.addProperty("address1",""); //string</address1>
	//		soapObject.addProperty("address2",""); // string</address2>
	//		soapObject.addProperty("address3",""); //string</address3>
	//		soapObject.addProperty("pincode",""); // string</pincode>
	//		soapObject.addProperty("location",""); // string</location>
	//		soapObject.addProperty("country",""); // string</country>
	//		soapObject.addProperty("mobilenumber",""); //>string</mobilenumber>
	//		soapObject.addProperty("dob",""); // >dateTime</dob>
	//		soapObject.addProperty("age",""); //>int</age>
	//		soapObject.addProperty("gender",""); // >string</gender>
	//		soapObject.addProperty("email",""); //>string</email>
	//		soapObject.addProperty("alertemail",""); // >string</alertemail>
	//		soapObject.addProperty("promocode",""); // >string</promocode>
	//		soapObject.addProperty("deviceidentifier",""); //>string</deviceidentifier>
	//		soapObject.addProperty("source",""); //>string</source>
	//
	//
	//		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_DISPLAY_EDIT_PROFILE);
	//		return WSResponseParser.parseRegistrationResponse(resultObject);
	//	}
	/**
	 * 
	 * @param userId
	 * @param lang1
	 * @param lang2
	 * @param radius
	 * @return
	 */


	public static UpdateSettingsResponse sendUpdateSettingsRequest(int userId,String lang1,String lang2,int radius,String pushNoti) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendUpdateSettingsRequest --- > ");
		//Log.i(Constants.TAG, "# WSSender # ==========>  UserId  --- > " + userId);
		//Log.i(Constants.TAG, "# WSSender # ==========>  Lang1  	--- > " + lang1);
		//Log.i(Constants.TAG, "# WSSender # ==========>  Lang2  	--- > " + lang2);
		//Log.i(Constants.TAG, "# WSSender # ==========>  Radius 	--- > " + radius);


		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_UPDATE_USER_SETTING);
		soapObject.addProperty("userid",userId); 
		soapObject.addProperty("lang1", lang1);  
		soapObject.addProperty("lang2", lang2);
		soapObject.addProperty("radius", radius);
		soapObject.addProperty("ref_Random", "123");
		StringBuffer sb = new StringBuffer();
		for(Category c : DataUtil.selectedCategory) {
			sb.append(c.getCategoryName() + "*" + c.getCategoryId() +"/");
		}
		if(sb.toString().length() > 3) {
			soapObject.addProperty("category1",sb.toString().substring(0, sb.toString().length() - 1));
			Log.i(Constants.TAG, "Selected Category --- > " + sb.toString().substring(0, sb.toString().length() - 1));
		}else {
			soapObject.addProperty("category1", "");
		}
		
		soapObject.addProperty("pushmsgActiveStatus", pushNoti);

		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_UPDATE_USER_SETTING);
		return WSResponseParser.parseUpdateSettingsResponse(resultObject);
	}

	/**
	 * 
	 * @param locationInfo
	 * @param dateRange
	 * @return
	 */

	public static List<Coupon> sendGetExpiryCoupons(long userId,int dateRange) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendGetExpiryCoupons --- > ");
		List<Coupon> couponList = new ArrayList<Coupon>();
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_EXPIRY_COUPON);
		soapObject.addProperty("latitude",DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude",DataUtil.locationInfo.getLongitude());
		soapObject.addProperty("daterange",dateRange);
		soapObject.addProperty("userid",userId);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_DISPLAY_EXPIRY_COUPON);
		couponList = WSResponseParser.parseSearchCouponResponse(resultObject);
		return couponList;
	}


	public static List<Coupon> send10GetExpiryCoupons(long userId) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  send10GetExpiryCoupons --- > ");
		List<Coupon> couponList = new ArrayList<Coupon>();
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_10EXPIRY_COUPON);
		soapObject.addProperty("latitude",DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude",DataUtil.locationInfo.getLongitude());
		soapObject.addProperty("userid",userId);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_DISPLAY_10EXPIRY_COUPON);
		couponList = WSResponseParser.parseSearchCouponResponse(resultObject);
		return couponList;
	}




	/**
	 * 
	 * @param userId
	 * @return
	 */

	public static List<Coupon> sendGetAllUsedCoupons(long userId) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendGetAllUsedCoupons --- > " + userId);
		List<Coupon> couponList = new ArrayList<Coupon>();
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_USED_COUPONS);
		soapObject.addProperty("userid",userId);
		soapObject.addProperty("latitude",DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude",DataUtil.locationInfo.getLongitude());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_DISPLAY_USED_COUPONS);
		couponList = WSResponseParser.parseSearchCouponResponse(resultObject);
		return couponList;
	}

	/**
	 * 
	 * @param userId
	 * @param storeId
	 * @return
	 * @throws ConnectException
	 * @throws IOException
	 */

	public static GeneralResponse sendAddFavoriteStore(int userId,int storeId) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendAddFavoriteStore--- > ");
		//Log.i(Constants.TAG, "# === sendAddFavoriteStore ===> UserId--> " + userId + "::: StoreID --- > " + storeId);
		GeneralResponse generalResponse = new GeneralResponse();
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_INSERT_FAVORITE_STORE);
		soapObject.addProperty("userid",userId);
		soapObject.addProperty("storeid",storeId);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_INSERT_FAVORITE_STORE);
		generalResponse = WSResponseParser.parseAddAndRemoveStoreResponse(resultObject);		
		return generalResponse;
	}

	/**
	 * 
	 * @param userId
	 * @param storeId
	 * @return
	 * @throws ConnectException
	 * @throws IOException
	 */

	public static GeneralResponse sendRemoveFavoriteStore(int userId,int storeId) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # ==========>  sendAddFavoriteStore--- > ");
		//Log.i(Constants.TAG, "# === sendAddFavoriteStore ===> UserId--> " + userId + "::: StoreID --- > " + storeId);
		GeneralResponse generalResponse = new GeneralResponse();
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_REMOVE_FAVORITE_STORE);
		soapObject.addProperty("userid",userId);
		soapObject.addProperty("storeid",storeId);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_REMOVE_FAVORITE_STORE);
		generalResponse = WSResponseParser.parseAddAndRemoveStoreResponse(resultObject);		
		return generalResponse;

	}

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ConnectException
	 * @throws IOException
	 */

	public static List<Store> sendSelectFavouriteStores(int userId) throws ConnectException,IOException{
		//Log.i(Constants.TAG, "# WSSender # =========== sendSelectFavouriteStores() => ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_SELECT_FAVOURITE_STORES);
		soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		soapObject.addProperty("latitude", DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude", DataUtil.locationInfo.getLongitude());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_SELECT_FAVOURITE_STORES);
		return WSResponseParser.parseSelectStoreReponse(resultObject);
	}


	public static List<Store> sendSearchFavouriteStores(String keyword,int userId) throws ConnectException,IOException{

		//Log.i(Constants.TAG, " ********* sendSearchFavouriteStores ******** ");
		//Log.i(Constants.TAG, " Keyword ---- >   " + keyword);
		//Log.i(Constants.TAG, " DataUtil.userDetails.getUserId() ---- > " + DataUtil.userDetails.getUserId());

		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_SEARCH_FAVOURITE_STORES);
		soapObject.addProperty("keyword",keyword);
		soapObject.addProperty("latitude", DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude", DataUtil.locationInfo.getLongitude());
		soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_SEARCH_FAVOURITE_STORES);

		return WSResponseParser.parseSelectStoreReponse(resultObject);
	}


	public static GeneralResponse sendTotalSaving(int totalSaving)  throws ConnectException,IOException{
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_TOTAL_SAVING);
		soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		soapObject.addProperty("totalsaving", totalSaving);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_TOTAL_SAVING);
		return WSResponseParser.parseGeneralResponse(resultObject);

	}


	public static GeneralResponse sendTellAFriend(String userName, long couponId,String emailIds) throws ConnectException,IOException{
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_TELL_A_FRIEND);
		soapObject.addProperty("username",DataUtil.userDetails.getUserName());
		soapObject.addProperty("couponid", couponId);
		soapObject.addProperty("tomailid", emailIds);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_TELL_A_FRIEND);
		return WSResponseParser.parseGeneralResponse(resultObject);
	}



	public static GeneralResponse sendTandC(String emailId) throws ConnectException,IOException{
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_SEND_T_AND_C);
		//Log.i(Constants.TAG, "sendTandC ---- > Email Id --- > " + emailId);
		soapObject.addProperty("tomailid",emailId);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_SEND_T_AND_C);
		return WSResponseParser.parseGeneralResponse(resultObject);
	}



	public static GeneralResponse insertClickReport(int couponId) throws ConnectException,IOException{
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_INSERT_CLICK_REPORT);
		//Log.i(Constants.TAG, "sendTandC ---- > Email Id --- > " + emailId);
		//soapObject.addProperty("tomailid",emailId);
		soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		soapObject.addProperty("latitude", DataUtil.locationInfo.getLatitude());
		soapObject.addProperty("longitude", DataUtil.locationInfo.getLongitude());
		soapObject.addProperty("couponid", couponId);
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_INSERT_CLICK_REPORT);
		return WSResponseParser.parseGeneralResponse(resultObject);
	}



	public static List<FAQ> sendMInboxmessageIP() throws IOException,ConnectException{
		Log.i(Constants.TAG, "# WSSender # =========== sendMInboxmessageIP() => ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_INBOX_MESSAGE);
		soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_INBOX_MESSAGE);
		return WSResponseParser.parseInboxMessage(resultObject);
	}



	public static List<Competion> sendCompetionRequest() throws IOException,ConnectException{
		Log.i(Constants.TAG, "# WSSender # =========== sendMInboxmessageIP() => ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_COMPETION);
		soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_MOBILE_COMPETION);
		return WSResponseParser.parseCompetionMessage(resultObject);
	}

	public static List<UserDetails> sendSelectTopTenRecomentors() throws IOException,ConnectException{
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_SELECTTOPTEN_RECOMMENTORS_USER);
		soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_MOBILE_SELECTTOPTEN_RECOMMENTORS_USER);
		return WSResponseParser.parseUserDetails(resultObject);
	}

	public static List<UserDetails> sendSelectTopTenCouponUsers() throws IOException,ConnectException{
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_SELECTTOPTENCOUPON_USER);
		soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_MOBILE_SELECTTOPTENCOUPON_USER);
		return WSResponseParser.parseUserDetails(resultObject);
	}

	public static List<UserDetails> sendSelectCouponFirstUser() throws IOException,ConnectException{
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_SELECT_FIRST_USER);
		soapObject.addProperty("userid",DataUtil.userDetails.getUserId());
		SoapObject resultObject = send(soapObject,WSConstants.SOAP_ACTION_MOBILE_SELECT_FIRST_USER);
		return WSResponseParser.parseUserDetails(resultObject);
	}
	

	/**
	 * 
	 * @param request
	 * @param soapAction
	 * @return
	 */


	private static SoapObject send(SoapObject request,String soapAction) throws IOException,ConnectException{
		//Log.i(Constants.TAG, "# WSSender # =========== send() => ");
		try {
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(WSConstants.wsUrl);
			androidHttpTransport.call(soapAction, envelope);
			//Log.i(Constants.TAG, "# WSSender # ========  response => " + envelope.getResponse());
			return (SoapObject) envelope.getResponse();
		}catch(XmlPullParserException xpl){

		}finally{

		}
		return null;
	}



	private void logMessage(String msg) {
		//Log.i(Constants.TAG, "# WSSender # ---------- " + msg);
	}
}
