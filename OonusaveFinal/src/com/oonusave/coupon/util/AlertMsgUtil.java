package com.oonusave.coupon.util;

/**
 * 
 * @author Ramesh
 *
 */

public class AlertMsgUtil {
	
	
	// Login Screen
	
	
	
	public static String getInvalidLoginMessage() {
		return Constants.INVALID_LOGIN_ENG;
	}
	
	public static String getEmptyLoginMessage() {
		return  Constants.EMPTY_LOGIN_ENG; 
	}
	
	public static String getEmptyEmailAddressMessage() {
		return Constants.EMPTY_EMAIL_ID_ENG; 
	}
		
	public static String getInvalidEmailAddressMessage() {
		return Constants.INVALID_EMAIL_ID_ENG;
	}
	
	
	public static String getForgotPasswordSuccessMessage() {
		return  Constants.LOGIN_FORGOT_PASSWORD_SUCCESS_ENG;
	
	}
	
	public static String getTandCSendSuccessMessage() {
		return Constants.SEND_TANDC_SUCCESS_ENG;
	}
	
	
	public static String getTandCSendFailureMessage() {
		return Constants.SEND_TANDC_FAILURE_ENG;
	}
	
	
	// Registration Screen
	
	public static String getAlertMsgEmptyFirstName() {
		return Constants.ALERT_MSG_EMPTY_FIRSTNAME_ENG;
	}
		
	public static String getAlertMsgEmptyLastName() {
		return Constants.ALERT_MSG_EMPTY_LASTNAME_ENG;
	}
	
	public static String getAlertMsgEmptyEmailAddress() {
		return Constants.ALERT_MSG_EMPTY_EMAIL_ADDRESS_ENG ;
	}
	
	public static String getAlertMsgEmptyPassword() {
		return Constants.ALERT_MSG_EMPTY_PASSWORD_ENG ;
	}
	
	public static String getAlertMsgPasswordLength() {
		return Constants.ALERT_MSG_PASSWORD_LENGTH_ENG;
	}
	
	public static String getAlertMsgEmptyConfirmPassword() {
		return Constants.ALERT_MSG_EMPTY_CONFIRM_PASSWORD_ENG;
	}
	
	public static String getAlertMsgPwdandConPwdDontMatch() {
		return Constants.ALERT_MSG_EMPTY_PWD_CON_PWD_DONOT_MATCH_ENG ;
	}
	
	public static String getAlertMsgTnCAccept() {
		return  Constants.ALERT_MSG_EMPTY_T_AND_C_ENG ;
	}
	
	public static String getInvalidEmailAddress() {
		return Constants.ALERT_MSG_INVALID_EMAIL_ENG ;
	}
	
	
	public static String getRegistrationSucessMessage() {
		return  Constants.ALERT_MSG_REGISTRATION_SUCCESS_ENG;
	}
	
	public static String getRegistrationFailureMessage() {
		return Constants.ALERT_MSG_REGISTRATION_FAILURE_ENG ;
	}
	
	
	// Feedback
	
	
	
	public static String getFeedbackSubmittedSuccessMessage() {
		return Constants.FEEDBACK_SCR1_SENT_SUCCESS_ENG;
	}
	
	
	public static String getFeedbackSubmittedFailureMessage() {
		return Constants.FEEDBACK_SCR1_SENT_FAILURE_ENG;
	}
	
	
	public static String getfeedbackEmptySubject() {
		return Constants.FEEDBACK_SCR2_EMPTY_SUBJECT_ENG ;
	}
	
	public static String getFeedbackEmptyComments() {
		return Constants.FEEDBACK_SCR2_EMPTY_MESSAGE_ENG ;
	}
	
	public static String getFeedbackEmptyMessage() {
		return Constants.FEEDBACK_SCR1_EMPTY_MESSAGE_ENG ;
	}
	
	
	// Settings
	
	
	public static String getSettingsSaveSuccessMsg() {
		return Constants.SETTINGS_SAVE_SUCCESS_ENG;
	}
	
	
	public static String getBothPriSecNotSameMsg() {
		return Constants.SETTINGS_PRI_SEC_NOT_SAME_ENG;
	}
	
	
	
	
	public static String getNoCouponsFoundAlertMsg() {
		return Constants.NO_COUPONS_FOUND_ENG;
	}
	
	
	
	// Coupon Details Screen
	
	
	public static String getEmailAlertMessage() {
		return Constants.EMAIL_SEND_SUCCESS_ENG;
	}
	
	
	public static String getCouponUsedMessage() {
		return Constants.COUPON_ALREADY_USED_MSG_ENG;
	}
	
	
	public static String getCouponAddedToWalletMessage() {
		return Constants.COUPON_ADD_TO_WALLET_SUCCESS_ENG;
	}
	
	
	public static String getCouponRemovedFromWalletMessage() {
		return Constants.COUPON_REMOVE_FROM_WALLET_SUCCESS_ENG;
	}
	
	public static String getCouponAlreadyAddedMessage() {
		return Constants.COUPON_ALLREADY_ADDED_TO_WALLET_ENG;
	}
	
	
	public static String getStoreTooFarMessage() {
		return Constants.COUPON_ALLREADY_ADDED_TO_WALLET_ENG ;
	}
	
	
	
	
	// Network 
	
	
	public static String getConnectFailureMessage() {
		return Constants.CONNECT_ERROR_ENG ;
	}
	
	
	public static String getLoadingMessageText() {
		return Constants.PLEASE_WAIT_MSG_ENG ;
	}
	
	
	
	// Store
	
	public static String getAddFavStoreSuccess() {
		return Constants.FAV_STORE_ADD_SUCCESS_ENG ;
	}
	
	
	public static String getAddFavStoreFailure() {
		return Constants.FAV_STORE_ADD_FAILURE_ENG ;
	}
	
	public static String getRemoveFavStoreSuccess() {
		return Constants.FAV_STORE_REMOVE_SUCCESS_ENG;
	}
	
	public static String getRemoveFavStoreFailure() {
		return Constants.FAV_STORE_REMOVE_FAILURE_ENG ;
	}
	
	
	// Fav Store
	
	
	
	// Edit Profile
	
	public static String getEditProfileSuccessMsg() {
		return  Constants.PROFILE_EDIT_SUCCESS_ENG  ;
	}
	
	public static String getEditProfileFailureMsg() {
		return  Constants.PROFILE_EDIT_FAILURE_ENG ;
	}
	public static String getCommonErrorMsg(){
		return  Constants.COMMON_ERROR_ENG ;
	}
		
	
	public static String getAlertCategorySelect() {
		return  "Please select atleast one category";
	}
	
	
}
