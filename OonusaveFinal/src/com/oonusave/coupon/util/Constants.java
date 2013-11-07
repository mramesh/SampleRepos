package com.oonusave.coupon.util;

/**
 * 
 * @author Ramesh
 *
 */

public class Constants {
		
	public static String TAG = "MOCANSA";
	public static String CONNECT_ERROR_ENG = "Connection failure! Please check your internet connection.";
	//public static String CONNECT_ERROR_DAN = "Der er fejl i! Tjek venligst din internetforbindelse.";
	
	public static String COMMON_ERROR_ENG = "Error Occured, Please try agian.";
	//public static String COMMON_ERROR_DAN = "Opstod fejl, prøv måske.";
	
	
	static public final String MIN_DISTANCE = "5";
	static public final String MIN_TIME = "120000";
	
	// Success registration
	public static String MSG_REG_SUCCESS_ENG = "Start saving, your registration has been successful";
	//public static String MSG_REG_SUCCESS_DAN = "Begynde at spare, er din tilmelding blevet en succes";
	
	
	// Success add copon to wallet
	
	public static String MSG_ADD_COUPON_TO_WALLET_ENG = "Coupon has been successfully added to your wallet";
	//public static String MSG_ADD_COUPON_TO_WALLET_DAN = "Kuponen er blevet tilføjet til din tegnebog";
	
	public static String MSG_USE_COUPON_ENG = "Are you sure you want to use this coupon? You may only be able to use it once.";
	//public static String MSG_USE_COUPON_DAN = "Er du sikker på du vil bruge denne kupon? Du kan kun være i stand til at bruge det en gang.";
	
	
	
	
	public static String getUseCouponMessage() {
		return MSG_USE_COUPON_ENG ; 
	}
	
	
	
	
	// LoginScreen
	
	
	public static String LOGIN_TITLE_TEXT_ENG = "Login";
	
	
	
	public static String LOGIN_ENTER_EMAIL_ENG = "Enter Your Email Address";
	public static String LOGIN_ENTER_PASSWORD_ENG = "Enter Your Password";
	public static String LOGIN_FORGOT_YOUR_PASSWORD_ENG = "Forgot Password ";
	public static String LOGIN_REMEMBER_PASSWORD_ENG = "Remember My Password";
	//public static String LOGIN_REMEMBER_PASSWORD_DAN = "Husk min adgangskode";
	
	public static String LOGIN_NEW_USER_REGIGER_ENG = "New user register here";
	public static String LOGIN_FORGOT_PASSWORD_ALERT_ENG = "Forgot your password";
	public static String LOGIN_FORGOT_PASSWORD_SUCCESS_ENG = "Password has been sent successfully to your email address";
	
	
	
	
	public static String LOGIN_USERNAME_TEXT_ENG ="Enter Your Email Address";
	
	//public static String LOGIN_USERNAME_TEXT_DAN = "Indtast din e-mail adresse";
	
	public static String LOGIN_PASSWORD_TEXT_ENG = "Enter Your Password";
	//public static String LOGIN_PASSWORD_TEXT_DAN = "Indtast din adgangskode";
	
	//public static String LOGIN_TITLE_TEXT_DAN = "Log ind";
	
	//public static String LOGIN_ENTER_EMAIL_DAN= "Indtast din e-mail adresse";
	//public static String LOGIN_ENTER_PASSWORD_DAN = "Indtast din kode";
	//public static String LOGIN_FORGOT_YOUR_PASSWORD_DAN= "Glemt din kode";
	//public static String LOGIN_NEW_USER_REGIGER_DAN = "Ny bruger-registrer her";
	//public static String LOGIN_FORGOT_PASSWORD_ALERT_DAN = "Har du glemt din adgangskode?";
		
	//public static String LOGIN_FORGOT_PASSWORD_SUCCESS_DAN = "Password er sendt med succes til din email adresse";
	
	
		
	// Short Registration Screen

	public static String SHORT_REG_TITLE_TEXT_ENG = " Sign up";
	public static String SHORT_REG_SUB_TITLE_ENG = "Sign up now and start saving with Mocansa";
	public static String SHORT_REG_FIRST_NAME_ENG = "First name";
	public static String SHORT_REG_LAST_NAME_ENG = "Last name";
	public static String SHORT_REG_EMAIL_ADDRESS_ENG = "Email address";
	public static String SHORT_REG_PASSWORD_ENG = "Password minimum 6 char";
	public static String SHORT_REG_CONFIRM_PASSWORD_ENG = "Confirm password";
	public static String SHORT_REG_FT_AND_C_ENG = "Yes i have read and I accept Mocansa terms and conditions.";
	
//	public static String SHORT_REG_TITLE_TEXT_DAN = " Tilmeld";
//	public static String SHORT_REG_SUB_TITLE_DAN = "Tilmeld dig nu og begynd og spar med Mocansa";
//	public static String SHORT_REG_FIRST_NAME_DAN = "Fornavn";
//	public static String SHORT_REG_LAST_NAME_DAN = "Efternavn";
//	public static String SHORT_REG_EMAIL_ADDRESS_DAN = "E-mail adresse";
//	public static String SHORT_REG_PASSWORD_DAN = "Kodeord (min. 6 karakt.)";
//	public static String SHORT_REG_CONFIRM_PASSWORD_DAN = "Bekræft kodeord";
//	public static String SHORT_REG_FT_AND_C_DAN = "Ja - jeg bekræfter at have læst og accepteret Mocansa aftalen og betingelser";
	
	
	/*alert message*/
	
	
	
	public static String ALERT_MSG_EMPTY_FIRSTNAME_ENG = "First name cannot be empty!";
	public static String ALERT_MSG_EMPTY_LASTNAME_ENG = "Last name cannot be empty!";
	public static String ALERT_MSG_EMPTY_EMAIL_ADDRESS_ENG = "Email address cannot be empty!";
	public static String ALERT_MSG_EMPTY_PASSWORD_ENG = "Password cannot be empty!";
	public static String ALERT_MSG_EMPTY_CONFIRM_PASSWORD_ENG = "Confirm password cannot be empty!";
	public static String ALERT_MSG_INVALID_EMAIL_ENG = "Please enter valid email address!";
	public static String ALERT_MSG_EMPTY_T_AND_C_ENG = "Plesae accept the agreement";
	public static String ALERT_MSG_EMPTY_PWD_CON_PWD_DONOT_MATCH_ENG = "Please ensure password and confirm password fields match";
	public static String ALERT_MSG_PASSWORD_LENGTH_ENG = "Password field should contain minimum of 6 characters";
	
//	public static String ALERT_MSG_EMPTY_FIRSTNAME_DAN = "Brugernavn må ikke være tomt!";
//	public static String ALERT_MSG_EMPTY_LASTNAME_DAN = "Efternavn kan ikke være tom!";
//	public static String ALERT_MSG_EMPTY_EMAIL_ADDRESS_DAN = "E-mail adresse kan ikke være tom!!";
//	public static String ALERT_MSG_EMPTY_PASSWORD_DAN = "Password kan ikke være tom!";
//	public static String ALERT_MSG_INVALID_EMAIL_DAN = "Angiv gyldig e-mail-adresse!";
//	public static String ALERT_MSG_EMPTY_CONFIRM_PASSWORD_DAN = "Bekræft adgangskode kan ikke være tom!";
//	public static String ALERT_MSG_EMPTY_T_AND_C_DAN = "Plesae acceptere aftalen";
//	public static String ALERT_MSG_EMPTY_PWD_CON_PWD_DONOT_MATCH_DAN = "Sørg venligst for adgangskode og bekræft password felter match";
	//public static String ALERT_MSG_PASSWORD_LENGTH_DAN = "Password feltet skal indeholde mindst 6 tegn";
	
	public static String ALERT_MSG_REGISTRATION_SUCCESS_ENG = "Congratulations you are successfully completed the Mocansa registration!";
	//public static String ALERT_MSG_REGISTRATION_SUCCESS_DAN = "Tillykke du er med succes afsluttet de Mocansa registrering";
	
	public static String ALERT_MSG_REGISTRATION_FAILURE_ENG = "User name already exists";
	//public static String ALERT_MSG_REGISTRATION_FAILURE_DAN = "Brugernavn findes allerede";
		
	// Terms and Conditions
	
	public static String T_AND_C_TITLE_TEXT_ENG = "Terms and conditions";
	//public static String T_AND_C_TITLE_TEXT_DAN = "Vilkår og betingelser";
	
	
	
	// COUPON LIST SCREEN
	
	public static String COUPON_LIST_TITLE_ENG = " Coupons list";
	
		
	//public static String COUPON_LIST_TITLE_DAN = " Kuponliste";	
	
	// CATEGORY SCREEN
	
	public static String CATEGORY_TITLE_ENG = " Categories";
	//public static String CATEGORY_TITLE_DAN = " Kategorier";
	
	
	
	// SUBCATEGORY SCREEN
	
	public static String SUB_CATEGORY_TITLE_ENG = " Sub categories";
	//public static String SUB_CATEGORY_TITLE_DAN = " underkategorier";
	
	// CATEGORY GOUPON LIST SCREEN
	
	
	
	// MYWALLET SCREEN
	
	
	public static String MY_WALLET_SUB_TITLE_ENG = " My wallet";
	//public static String MY_WALLET_SUB_TITLE_DAN = " Min lomme";
	
	// COUPON DETAILS SCREEN
	
	
	
	//public static String EMAIL_SEND_SUCCESS_DAN = "E-mail er blevet sendt med succes";
	public static String EMAIL_SEND_SUCCESS_ENG  = "Email has been sent successfully";
	
	
	
	//public static String COUPON_ALREADY_USED_MSG_DAN = "Du har allerede brugt denne kupon, eller kan du langt væk fra butikken";
	public static String COUPON_ALREADY_USED_MSG_ENG = "You already used this coupon or may be you far away from the store";
	
	
	
	public static String COUPON_ADD_TO_WALLET_SUCCESS_ENG = "Coupon has been successfully added to your wallet";
	//public static String COUPON_ADD_TO_WALLET_SUCCESS_DAN = "Kuponen er blevet tilføjet til din tegnebog";
	
	public static String COUPON_REMOVE_FROM_WALLET_SUCCESS_ENG = "Your selected coupon has been successfully removed from your wallet";
	//public static String COUPON_REMOVE_FROM_WALLET_SUCCESS_DAN = "valgte kupon er blevet fjernet fra din tegnebog";
		
	public static String COUPON_ALLREADY_ADDED_TO_WALLET_ENG = "This coupon is already in wallet";
	//public static String COUPON_ALLREADY_ADDED_TO_WALLET_DAN = "Denne kupon er allerede i tegnebogen";
	
	
	
	// BARCODE SCREEN
	
	// MORE SCREEN
	
	public static String MORE_TITLE_ENG = " More";
	public static String MORE_PROFILE_ENG = "Profile";
	public static String MORE_COMPETION_ENG = "Competion";
	public static String MORE_SETTINGS_ENG = "Settings";
	public static String MORE_USED_COUPON_ENG = "Used coupons";
	public static String MORE_EXPIRY_COUPON_ENG = "Expiry coupons";
	public static String MORE_STORE_FINDER_ENG = "Store finder";
	public static String MORE_FAV_STORE_ENG = "Favorite stores";
	//public static String MORE_MAP_ENG = "Map";
	public static String MORE_FEEDBACK_ENG = "Feedback";
	public static String MORE_INBOX_ENG = "Inbox";
	public static String LOG_OUT = "Log out";
	
	
	
//	public static String MORE_TITLE_DAN= " Mere";
//	public static String MORE_COMPETION_DAN = "Competion";
//	public static String MORE_PROFILE_DAN = "Profil";
//	public static String MORE_SETTINGS_DAN = "Indstilling";
//	public static String MORE_USED_COUPON_DAN = "Brugte kuponer";
//	public static String MORE_EXPIRY_COUPON_DAN = "Kupon udløber";
//	public static String MORE_STORE_FINDER_DAN = "Find butik";
//	public static String MORE_FAV_STORE_DAN = "Favorite stores";
//	public static String MORE_FEEDBACK_DAN = "Tilbagemelding";
//	public static String MORE_INBOX_DAN = "Inbox";
//	public static String LOG_OUT_DAN = "Log ud";
	
	
	
	// SETTINGS SCREEN
	
	public static String SETTINGS_TITLE_ENG = " General Settings";
	public static String SETTINGS_PRE_LANG_ENG = " Preferred language";
	public static String SETTINGS_PRIMARY_TEXT_ENG = " Primary language";
	public static String SETTINGS_SEC_TEXT_ENG = " Secondary language";
	public static String SETTINGS_RADIUS_TEXT_ENG = " Radius settings in km";
	public static String SETTINGS_SAVE_SUCCESS_ENG = "Settings saved successfully!";
	public static String SETTINGS_PRI_SEC_NOT_SAME_ENG = "Both the primary and secondary language cannot not be same";
	
	
	
	
//	public static String SETTINGS_TITLE_DAN = " General Indstillinger";
//	public static String SETTINGS_PRE_LANG_DAN = " Foretrukne sprog";
//	public static String SETTINGS_PRIMARY_TEXT_DAN = " Primære sprog";
//	public static String SETTINGS_SEC_TEXT_DAN = " Sekundært sprog";
//	public static String SETTINGS_RADIUS_TEXT_DAN = " Radius indstillingerne i km";
//	
//	public static String SETTINGS_SAVE_SUCCESS_DAN = "Indstillinger er gemt";
//	
//	public static String SETTINGS_PRI_SEC_NOT_SAME_DAN = "Både primære og sekundære sprog kan ikke ikke være det samme";
		
	// USED COUPONS SCREEN
	
	public static String USED_COUPON_TITLE_TEXT_ENG = " Used coupons";
	public static String USED_COUPON_TOTAL_SAVING_TEXT_ENG = " Total Savings";
	
//	public static String USED_COUPON_TITLE_TEXT_DAN = " Liste over brugte kuponer";
//	public static String USED_COUPON_TOTAL_SAVING_TEXT_DAN = " Totalt sparet";
	
	
	
	
	// EXPIRY COUPONS SCREEN
	
	public static String EXPIRY_COUPON_TITLE_TEXT_ENG = " Expiry coupons";
//	public static String EXPIRY_COUPON_TITLE_TEXT_DAN = " Kuponer med snarlig udløb";
		
	
	public static String EXPIRY_COUPON_NO_DAYS_ENG  = "No. of days";
//	public static String EXPIRY_COUPON_NO_DAYS_DAN = "nr. af dage";
	
	// STORE FINDER  SCREEN
	
	public static String STORE_FINDER_TITLE_TEXT_ENG = " Store list";
//	public static String STORE_FINDER_TITLE_TEXT_DAN = " Liste over butikker";
	
	
	// MYFAV STORE SCREEN
	
	public static String FAV_STORE_TITLE_TEXT_ENG = " Favorite store list";
//	public static String FAV_STORE_TITLE_TEXT_DAN = " Liste over butikker";
		
	public static String FAV_STORE_ADD_SUCCESS_ENG = "Your favorite store has been successfully added";
//	public static String FAV_STORE_ADD_SUCCESS_DAN = "Din yndlings butik er blevet tilføjet";
	
	public static String FAV_STORE_ADD_FAILURE_ENG = "This store is already in your favorite list";
//	public static String FAV_STORE_ADD_FAILURE_DAN = "Denne butik er allerede i din favorit liste";
	
	public static String FAV_STORE_REMOVE_SUCCESS_ENG = "Your selected store has been successfully removed";
//	public static String FAV_STORE_REMOVE_SUCCESS_DAN = "Dit valgte butik er blevet fjernet";
	
	public static String FAV_STORE_REMOVE_FAILURE_ENG = "Error while removing favorite store. Please try again later";
//	public static String FAV_STORE_REMOVE_FAILURE_DAN = "Fejl under fjernelse favorit butik. Prøv igen senere";
	
	
	
	// MAP SCREEN
	public static String MAP_SCREEN_TITLE_TEXT_ENG = " Map view";
//	public static String MAP_SCREEN_TITLE_TEXT_DAN = " Kortvisning";
	
	// FAQ SCREEN
	
	
	//public static String FAQ_TITLE_TEXT_ENG = " Frequently asked questions";
	
	
	//public static String FAQ_TITLE_TEXT_DAN = " Ofte stillede spørgsmål";
	
public static String FAQ_TITLE_TEXT_ENG = " Inbox";
	
	
//	public static String FAQ_TITLE_TEXT_DAN = " Inbox";
	
	
	// FAQ DETAILS SCREEN
	
	public static String FAQ_DETAILS_TITLE_TEXT_ENG = " Frequently asked questions";
		
//	public static String FAQ_DETAILS_TITLE_TEXT_DAN = " Ofte stillede spørgsmål";
	
	
	// FEEDBACK MAIN SCREEN
	
	public static String FEEDBACK_TITLE_TEXT_ENG = " Feedback";
	public static String FEEDBACK_RATE_THIS_APP_TITLE_ENG = " Rate this App";
	public static String FEEDBACK_RATE_THIS_APP_DESC_ENG = "Click here to appreciate us by rating this application.";
	public static String FEEDBACK_REPORT_A_BUG_TITLE_ENG = " Report a Bug";
	public static String FEEBACK_REPORT_A_BUG_DESC_ENG = "Let us know about the bug you came across in this App.";
	
//	public static String FEEDBACK_TITLE_TEXT_DAN = " Tilbagemelding";
//	public static String FEEDBACK_RATE_THIS_APP_TITLE_DAN = " Vurder denne app";
//	public static String FEEDBACK_RATE_THIS_APP_DESC_DAN = "Klik herunder for at glæde os, ved at vurdere denne applikation.";
//	public static String FEEDBACK_REPORT_A_BUG_TITLE_DAN = " Rapporter fejl";
//	public static String FEEBACK_REPORT_A_BUG_DESC_DAN = "Fandt du en fejl, beder vi dig venligt rapportere den ved at klikke herunder.";
	
	
	// FEEDBACK SCREEN 1
	
	public static String FEEDBACK_SCR1_TITLE_TEXT_ENG = " Rate this App";
	public static String FEEDBACK_SCR1_RATING_TEXT_ENG = " Your Ratings ";
	public static String FEEDBACK_SCR1_MESSAGE_TEXT_ENG = " Comments";
	public static String FEEDBACK_SCR1_EMPTY_MESSAGE_ENG = "Please enter your valuable comments";
	public static String FEEDBACK_SCR1_SENT_SUCCESS_ENG = "Feedback has been submitted successfully!"; 
	public static String FEEDBACK_SCR1_SENT_FAILURE_ENG = "Error while sending feedback. Please try again later";
	
	
//	public static String FEEDBACK_SCR1_TITLE_TEXT_DAN = " Vurder denne app";
//	public static String FEEDBACK_SCR1_RATING_TEXT_DAN = " Your Bedømmelse ";
//	public static String FEEDBACK_SCR1_MESSAGE_TEXT_DAN = " Kommentar";
//	public static String FEEDBACK_SCR1_EMPTY_MESSAGE_DAN = "Indtast dine værdifulde kommentarer";
//	public static String FEEDBACK_SCR1_SENT_SUCCESS_DAN = "Feedback er blevet modtaget!";
//	public static String FEEDBACK_SCR1_SENT_FAILURE_DAN = "Fejl ved afsendelse af feedback. Prøv igen senere";
	
	
	
	// FEEDBACK SCREEN 2
	
	
	public static String FEEDBACK_SCR2_TITLE_TEXT_ENG = " Report a Bug";
	public static String FEEDBACK_SCR2_SUBJECT_TEXT_ENG = "Subject";
	public static String FEEDBACK_SCR2_MESSAGE_TEXT_ENG = "Message";
	public static String FEEDBACK_SCR2_EMPTY_SUBJECT_ENG = "Please enter the subject";
	public static String FEEDBACK_SCR2_EMPTY_MESSAGE_ENG = "Please enter your message";
	
	
//	public static String FEEDBACK_SCR2_TITLE_TEXT_DAN = " Rapporter fejl";
//	public static String FEEDBACK_SCR2_SUBJECT_TEXT_DAN = "Emne";
//	public static String FEEDBACK_SCR2_MESSAGE_TEXT_DAN = "Besked";
//	public static String FEEDBACK_SCR2_EMPTY_SUBJECT_DAN = "Indtast emne";
//	public static String FEEDBACK_SCR2_EMPTY_MESSAGE_DAN = "Indtast din besked";
	
	
	
	// EDIT PROFILE SCREEN
	
	public static String PROFILE_EDIT_TITLE_TEXT_ENG = " profile";
	public static String PROFILE_FIRST_NAME_TEXT_ENG = " First name";
	public static String PROFILE_LAST_NAME_TEXT_ENG = " Last name";
	public static String PROFILE_YOUR_ALT_EMAIL_TEXT_ENG = "Enter your alternate email address";
	public static String PROFILE_GENDER_TEXT_ENG = " Gender";
	public static String PROFILE_ADDRESS1_TEXT_ENG = " Address1";
	public static String PROFILE_ADDRESS2_TEXT_ENG = " Address2";
	public static String PROFILE_CITY_TEXT_ENG = " City";
	public static String PROFILE_COUNTRY_TEXT_ENG = " Country";
	public static String PROFILE_POST_CODE_TEXT_ENG = " Zip Code";
	public static String PROFILE_MOBILE_NUMBER_TEXT_ENG = " Contact number";
	public static String PROFILE_DOB_TEXT_ENG = " Date of birth";
	public static String PROFILE_EDIT_SUCCESS_ENG = "Your profile has been updated successfully";
	public static String PROFILE_EDIT_FAILURE_ENG = "Error while updating your profile. Please try again";	
	
//	public static String PROFILE_EDIT_TITLE_TEXT_DAN = " profil";
//	public static String PROFILE_FIRST_NAME_TEXT_DAN = " Fornavn";
//	public static String PROFILE_LAST_NAME_TEXT_DAN = " Efternavn";
//	public static String PROFILE_YOUR_ALT_EMAIL_TEXT_DAN = "Indtast din alternative e-mail adresse";
//	public static String PROFILE_GENDER_TEXT_DAN = " Køn";
//	public static String PROFILE_ADDRESS1_TEXT_DAN = " Address1";
//	public static String PROFILE_ADDRESS2_TEXT_DAN = " Address2";
//	public static String PROFILE_CITY_TEXT_DAN = " By";
//	public static String PROFILE_COUNTRY_TEXT_DAN = " Land";
//	public static String PROFILE_POST_CODE_TEXT_DAN = " Postnr.";
//	public static String PROFILE_MOBILE_NUMBER_TEXT_DAN = " Kontaktnummer";
//	public static String PROFILE_DOB_TEXT_DAN = " Fødselsdag";
////	
//	public static String PROFILE_EDIT_SUCCESS_DAN = "Din profil er blevet opdateret med succes";
//	public static String PROFILE_EDIT_FAILURE_DAN = "Fejl mens opdatere din profil. Prøv venligst igen";
		
	// GENERAL ERROR MESSAGES (English)
	
	public static String INVALID_EMAIL_ID_ENG = "Please enter valid email address";
	public static String EMPTY_EMAIL_ID_ENG = "Please enter email address";
	public static String EMPTY_LOGIN_ENG = "Enter username(email) and/or Password";
	public static String INVALID_LOGIN_ENG = "Username(email) and/or password invalid!";	
	
	
	// GENERAL ERROR MESSAGES (Danish)
	
//	public static String INVALID_EMAIL_ID_DAN = "Angiv gyldig e-mail-adresse";
//	public static String EMPTY_EMAIL_ID_DAN = "Indtast e-mail-adresse!";
//	public static String EMPTY_LOGIN_DAN = "Indtast brugernavn (e-mail) og / eller kodeord";
//	public static String INVALID_LOGIN_DAN = "Brugernavn (e-mail) og / eller kodeord invalid!";
	
		
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// Feedback
	
	
	/*public static String FEEDBACK_SUBMIT_SUCCESS_MSG_ENG = "Feedback submitted successfully!";
	public static String FEEDBACK_SUBMIT_SUCCESS_MSG_DAN = "Feedback submitted successfully!";
	
	
	public static String FEEDBACK_SUBMIT_FAILURE_MSG_ENG = "Error while sending feedback. Please try again!";
	public static String FEEDBACK_SUBMIT_FAILURE_MSG_DAN = "Error while sending feedback. Please try again!";
	*/
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static String TELL_A_FRIEND_ENG = "Tell A Friend";
	//public static String TELL_A_FRIEND_DAN = "Anbefal til en ven";
	
	
	public static String TELL_A_FRIEND_EMAIL_ADDRESS_TEXT_ENG = "Whom do you inform?";
	//public static String TELL_A_FRIEND_EMAIL_ADDRESS_TEXT_DAN = "Hvem mener du oplyse?";
	
	
	public static String TELL_A_FRIEND_EMAIL_ADDRESS_DESC_TEXT_ENG = "For more friends put comma between the email addresses";
	//public static String TELL_A_FRIEND_EMAIL_ADDRESS_DESC_TEXT_DAN = "For flere venner sætte komma mellem e-mail-adresser";
		
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public static String CANCEL_ENG = "Cancel";
	public static String SEND_ENG = "Send";
	
	
//	public static String CANCEL_DAN = "Annuller";
//	public static String SEND_DAN = "Send";
		
	
	/////////////////////
	
	
	public static String NO_COUPONS_FOUND_ENG = "No coupons found!";
//	public static String NO_COUPONS_FOUND_DAN = "Ingen kuponer fundet!";
	
	
	
	// 
	
	public static String PLEASE_WAIT_MSG_ENG = "Please wait while loading...";
//	public static String PLEASE_WAIT_MSG_DAN = "Vent venligst mens loading ...";
	

	public static String LOCATION_UNAVAILABLE_ENG = "Oonusave is location service based application. It will not work properly without knowing the location information. So please allow this location service by relaunch the application";
	//public static String LOCATION_UNAVAILABLE_DAN = "Mocansa er placeringen service baseret applikation. Det vil ikke fungere korrekt uden at kende de geografiske oplysninger. Så lad dette sted service ved relancere ansøgningen"; 
		
	
	public static String NO_NETWOTK_ENG = "Oonusave requires an active network connection.  Please activate a data or wifi connection and try again.";
	//public static String NO_NETWOTK_DAN =  "Oonusave kræver en aktiv netværksforbindelse. Venligst aktivere en data-eller wifi-forbindelse, og prøv igen.";
	
	
	
	//
	
	
	public static String SEND_TANDC_SUCCESS_ENG = "Terms and conditions has been  successfully sent to the mail";
//	public static String SEND_TANDC_SUCCESS_DAN = "Vilkår og betingelser er blevet sendt til mail";
	public static String SEND_TANDC_FAILURE_ENG = "Error occured while sending terms and conditions. Please try again";
//	public static String SEND_TANDC_FAILURE_DAN = "Der opstod en fejl under afsendelse af vilkår og betingelser. Prøv venligst igen";
	
	public static String T_AND_C_TITLE_ENG = "Enter your email address";
//	public static String T_AND_C_TITLE_DAN = "Indtast din e-mailadresse";
	
	public static String T_AND_C_SENDMAIL_TITLE_ENG = "Send mail";
//	public static String T_AND_C_SENDMAIL_TITLE_DAN = "Send mail";
		
	public static String SEARCH_TITLE_ENG = "Search";
//	public static String SEARCH_TITLE_DAN = "Søg";
	
	public static String BARCODE_EXPIRE_DATE_ENG = "Expire Date";
//	public static String BARCODE_EXPIRE_DATE_DAN = "Udløbsdato";
	
	
	
	// competion
	
	public static String COMPETION_TITLE_ENG = " Competition";
//	public static String COMPETION_TITLE_DAN = " Konkurrence";
	
	
	
	public static String  COMPETION_TOP_USER_ENG = " Top Users";
//	public static String  COMPETION_TOP_USER_DAN = " Top Brugere";
	
	
	public static String COMPETION_TOP_RECOMMEND_ENG = " Top Recommends";
//	public static String COMPETION_TOP_RECOMMEND_DAN = " Top anbefaler";
	
	public static String  COMPETION_TOP_SAVER_ENG = " Top Savers";
//	public static String  COMPETION_TOP_SAVER_DAN = " Top Savers";
	
	
	
	
	
}
