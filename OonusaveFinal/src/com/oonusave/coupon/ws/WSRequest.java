package com.oonusave.coupon.ws;

import org.ksoap2.serialization.SoapObject;

import android.location.Location;
import android.util.Log;

import com.oonusave.coupon.model.Category;
import com.oonusave.coupon.model.ChangePassword;
import com.oonusave.coupon.model.LocationInfo;
import com.oonusave.coupon.model.Login;
import com.oonusave.coupon.model.UserDetails;
import com.oonusave.coupon.util.Constants;

public class WSRequest {

	public static SoapObject getValidateCredentialRequest(Login login) {
		Log.i(Constants.TAG, "# WSRequest # ==========  getValidateCredentialRequest() ========= ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_VALIDATE_CREDENTIAL);
		soapObject.addProperty("username", login.getUsername());
		soapObject.addProperty("password", login.getPassword());
		soapObject.addProperty("deviceIndentifier", login.getDeviceIndentifier());
		return soapObject;
	}
	
	public static SoapObject getChangePasswordRequest(ChangePassword changePassword,String userName) {
		Log.i(Constants.TAG, "# WSRequest # ==========  getChangePasswordRequest() ========= ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_CHANGE_PASSWORD);
		soapObject.addProperty("username", userName);
		soapObject.addProperty("oldpass", changePassword.getOldPassword());
		soapObject.addProperty("newpass", changePassword.getNewPassword());
		return soapObject;
	}
		
	
	public static SoapObject getRegistrationRequest(UserDetails userDetails) {
		Log.i(Constants.TAG, "# WSRequest # ==========  getRegistrationRequest() ========= ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_INSERT_USER);
		soapObject.addProperty("UserID", "");
		soapObject.addProperty("FirstName", userDetails.getFirstName());
		soapObject.addProperty("LastName", userDetails.getLastName());
		soapObject.addProperty("MobileNumber", userDetails.getMobileNumber());
		soapObject.addProperty("DateOfBirth", userDetails.getDateOfBirth());
		soapObject.addProperty("Age", "");
		soapObject.addProperty("Gender", userDetails.getGender());
		soapObject.addProperty("EmailID", userDetails.getEmail());
		soapObject.addProperty("UserName", userDetails.getUserName());
		soapObject.addProperty("Password", userDetails.getPassword());
		soapObject.addProperty("HowToKnow", userDetails.getHowToKnow());
		soapObject.addProperty("Interest", userDetails.getInterest());
		soapObject.addProperty("AlertEmail", userDetails.getAlertEmail());
		soapObject.addProperty("TotalSaving", userDetails.getTotalSaving());
		soapObject.addProperty("Status", userDetails.getStatus());
		soapObject.addProperty("deviceidentifier", userDetails.getDeviceIndentifier());
		soapObject.addProperty("promocode", userDetails.getPromocode());
		soapObject.addProperty("source", userDetails.getSource());


		/*<UserID>int</UserID>
		<FirstName>string</FirstName>
		<LastName>string</LastName>
		<MobileNumber>string</MobileNumber>
		<DateOfBirth>dateTime</DateOfBirth>
		<Age>int</Age>
		<Gender>string</Gender>
		<EmailID>string</EmailID>
		<UserName>string</UserName>
		<Password>string</Password>
		<HowToKnow>string</HowToKnow>
		<Interest>string</Interest>
		<AlertEmail>string</AlertEmail>
		<TotalSaving>double</TotalSaving>
		<Status>int</Status>
		<deviceidentifier>string</deviceidentifier>
		<promocode>string</promocode>
		<source>string</source>

*/
		return soapObject;
	}
	
	public static SoapObject getCategoryRequest() {
		Log.i(Constants.TAG, "# WSRequest # ==========  getCategoryRequest() ========= ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_ALL_CATEGORY);
		return soapObject;
	}
	
	
	public static SoapObject getSubCategoryRequest(Category category) {
		Log.i(Constants.TAG, "# WSRequest # ==========  getSubCategoryRequest() ========= ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_SUB_CATEGORY);
		soapObject.addProperty("categoryid",category.getCategoryId());
		return soapObject;
	}
	
	
	
	public static SoapObject getSearchCouponRequest(LocationInfo locationInfo) {
		Log.i(Constants.TAG, "# WSRequest # ==========  getSearchCouponRequest() ========= ");
		SoapObject soapObject = new SoapObject(WSConstants.NAMESPACE, WSConstants.METHOD_MOBILE_DISPLAY_COUPON_BY_CATEGORY);
		soapObject.addProperty("latitude", locationInfo.getLatitude());
		soapObject.addProperty("longitude", locationInfo.getLongitude());
		return soapObject;
	}
	
	
	
	
	
	
	
}

