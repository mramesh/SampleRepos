package com.oonusave.coupon.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


import com.oonusave.coupon.model.Category;
import com.oonusave.coupon.model.LocationInfo;
import com.oonusave.coupon.model.UserDetails;

/**
 * 
 * @author Ramesh
 *
 */

public class DataUtil {
	
	
	public static boolean pushNoti = false;

	public static final String[] COUNTRIES = new String[] {
		"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
		"Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
		"Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
		"Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
		"Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
		"Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory",
		"British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi",
		"Cote d'Ivoire", "Cambodia", "Cameroon", "Canada", "Cape Verde",
		"Cayman Islands", "Central African Republic", "Chad", "Chile", "China",
		"Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
		"Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic",
		"Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
		"East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
		"Estonia", "Ethiopia", "Faeroe Islands", "Falkland Islands", "Fiji", "Finland",
		"Former Yugoslav Republic of Macedonia", "France", "French Guiana", "French Polynesia",
		"French Southern Territories", "Gabon", "Georgia", "Germany", "Ghana", "Gibraltar",
		"Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau",
		"Guyana", "Haiti", "Heard Island and McDonald Islands", "Honduras", "Hong Kong", "Hungary",
		"Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica",
		"Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos",
		"Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
		"Macau", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
		"Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova",
		"Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia",
		"Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand",
		"Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Northern Marianas",
		"Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru",
		"Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar",
		"Reunion", "Romania", "Russia", "Rwanda", "Sqo Tome and Principe", "Saint Helena",
		"Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon",
		"Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia", "Senegal",
		"Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands",
		"Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Korea",
		"Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden",
		"Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "The Bahamas",
		"The Gambia", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
		"Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Virgin Islands", "Uganda",
		"Ukraine", "United Arab Emirates", "United Kingdom",
		"United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan",
		"Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Wallis and Futuna", "Western Sahara",
		"Yemen", "Yugoslavia", "Zambia", "Zimbabwe"
	};

	public static final String[] GENDER = new String[] {
		"Male", "Female"
	};





	public static UserDetails userDetails = null;


	public static String userName = "";
	public static String password = "";
	public static long userId = 0;
	//public static String priLang = "English";
	//public static String priLang = "Danish";
	//public static String secLang = "";
	public static int radius = 50;

	public static boolean isEnglish = true;

	public static LocationInfo locationInfo = new LocationInfo();


	public static void setLocation(Location location) {
		locationInfo.setLatitude(location.getLatitude()+"");
		locationInfo.setLongitude(location.getLongitude()+"");
	}
	
	
	
	public static LocationFinder finder = new LocationFinder();
	public static LocationManager locationMgr = null;
	
	
	
	

	public static int getCountryPostion(String countryName) {
		for(int i = 0 ; i < COUNTRIES.length ; i++) {
			if(countryName.equalsIgnoreCase(COUNTRIES[i]))
				return i;
		}
		return 0;
	}

	public static int getGenderPosition(String genderName) {
		for(int i = 0 ; i < GENDER.length ; i++) {
			if(genderName.equalsIgnoreCase(GENDER[i]))
				return i;
		}
		return 0;
	}

	public static String[] prefLang = {"None","English"};

	public static int CURRENT_SCREEN = 0;


	public static boolean mapScreen = true;
	//public static String selectedCategories = "";
	
	public static CharSequence[] categories = new CharSequence[0];
	//public static CharSequence[] categorieIds = { "1", "2", "3", "4", "5", "6" };
	
	//public static Category[] selectedCategory = new Category[0];
	public static ArrayList<Category> selectedCategory = new ArrayList<Category>();
	
	public static List<Category> categoryList = new ArrayList<Category>();


	public static String[] termsAndCondEng = {
		"<b>ACCEPTANCE OF TERMS AND CONDITIONS </b><br />" + 
		"By using this site (\"Site\") you agree (\"you\" or \"end user\") the terms and conditions that we (\"Mobile Can Save Limited\") has been given. If you do not wish to accept these terms and conditions (\"Terms of Use\" or \"Agreement\"), you must not use Oonusave.<br /><br />" +
		"<b>I. TERMS OF USE</b><br/>\n" + 
		"<b>1. General.</b> <br/><br/>" +
		"The site is an interactive online service operated by Mobile Can Save ApS on the World Wide Web of the Internet (\"Web\") and smartphone devices consisting of information services, content and transaction capabilities delivered through Oonusave, subsidiaries of Oonusave or distributors ( \"dealer\") to issue vouchers for sale (\"coupons\") which can be used to obtain discounts on goods / services offered by dealers and other third parties."+ 
		"This agreement contains all the terms and conditions applicable to the end user through the use of this site. By using this site (other than to read this Agreement for the first time), End User agrees to comply with all terms and conditions hereafter. The right to use this website is personal to End User and is not transferable to other persons or entities. End user has the responsibility to protect the end user's password (s), if one exists. End User acknowledges that although the Internet is often a secure environment, sometimes there are interruptions in service or events that Oonusave's control, and Oonusave not responsible to lost data that is sent on the Internet. Although it is Oonusaves goal of making the site available 24 hours a day, seven days a week, the Site unavailable from time to time for any reason, including, without limitation, routine maintenance. You understand and acknowledge that due to factors both within and outside Oonusaves control, access to the Site is interrupted, suspended or terminated from time to time."+ 
		"Oonusave shall have the right at any time to modify or discontinue any aspect or feature of the site, including but not limited to, content, hours of availability and equipment needed for access or use. Moreover Oonusave interrupt the provision of a part of the information or category of information that can alter or eliminate any transmission method and may change transmission speeds or other signal characteristics. ",


		"<b>2. Modified Terms </b> <br/><br/>" +
		"Oonusave reserves the right at all times to discontinue or modify any of our Terms of Use and / or our Privacy Policy as we deem necessary or desirable. Such changes may in particular the addition of certain fees or taxes, or amendments thereto. If Oonusave performs substantial changes we will notify you by sending you an email to the email address registered with your account and / or by placing a notice of change on the website. Any significant changes to these Terms of Use will be effective upon dispatch of an email message to you, or when the message is posted on our website, provided that these changes will not apply to tickets purchased before the effective date of such changes. These changes will have immediate effect for new users of our site and any tickets purchased from these new users. We therefore suggest that you re-reads this important notice containing our - Terms of use and Privacy Policy from time to time to ensure that you maintain and keep you informed of such changes. Any use of the Site by End User after such notice shall be deemed to be an acceptance by End User after such changes.",

		"<b>3. Equipment</b> <br/><br/>" +
		"End User is responsible to obtain and maintain all phones, computers and other hardware and other equipment needed for access to and use of this Site and all charges related thereto. Oonusave is not responsible for any damage to End User equipment as a result of using this site." ,

		"<b>4. End user behavior</b> <br/><br/>" +
		"All interactions on this site and / or the Microsites must comply with these Terms of Use. Although we welcome and encourage user interaction on our website, we want and require that all end users restrict all activities in connection with the use of this Site and the Microsites to that which involves lawful purposes. End User shall not post or transmit through this website material that violates or infringes in any way the rights of others, or materials that are unlawful, threatening, abusive, libelous, invasive of privacy or publicity rights, vulgar, obscene, profane or otherwise objectionable, which encourages conduct that would constitute a criminal offense, give rise to civil liability or otherwise violate any law, or who are not Oonusaves express prior written approval, contains advertising or any appeal in respect of goods or services. Any conduct by an End User to Oonusaves exclusive restricts or inhibits any other End User from using or enjoying this Site and / or any of the Microsites is strictly prohibited. End User may not use this site or any of the Microsites to advertise or perform any commercial, religious, political or non-commercial solicitation, including but not limited to, the solicitation of users of this site and / or the Microsites to become users other on-or offline services directly or indirectly competitive or potentially competitive with Oonusave."+ 
		"The foregoing provisions of this Section 4 shall also apply to and benefit Oonusave, its subsidiaries, affiliates, distributors, and its third party content providers and licensors and each shall have the right to assert and enforce such provisions directly or on its own behalf.",

		"<b>5. Copyright and Trademarks </b> <br/><br/>" +
		"Everything located on or in this site, including the Microsites, is Mobile Can Saves exclusive property or used with express permission from copyright and / or owner of the mark. Any copying, distribution, transmission, broadcast, links and deep links or otherwise modify this website or any of the Microsites without express written permission of Mobile Can Save, is strictly prohibited. Any violation of this policy may result in a copyright, trademark or other intellectual property infringement can allow End User to civil and / or criminal penalties. <br/> <br />"+
		"This website and any Micro Site contains copyrighted material, trademarks and other proprietary information, including but not limited to, text, software, photographs, video, graphics, music and sound, and the entire contents of this website is protected by copyright as a collective work under Danish and U.S. copyright laws. Mobile Can Save is the owner of copyright in the selection, coordination, arrangement and enhancement of such content, as well as the original content. End User may not modify, publish, transmit, participate in the transfer or sale, create derivative works or otherwise exploit any content, in whole or in part. End User may download, print and / or save copyrighted material for End User's personal use. Unless otherwise expressly stated under copyright law, no copying, redistribution, retransmission, publication or commercial exploitation of downloaded material without the express permission of Mobile Can Save or copyright holder is prohibited. If copying or redistribution or publication of copyrighted material are permitted, no changes in or deletion of author attribution, trademark legend or copyright notice be published. End User acknowledges that he / she / it does not acquire any ownership rights by downloading copyrighted material. Trademarks that are located within or on the Site or a Microsite otherwise owned or operated jointly with Oonusave not considered to be in the public domain, but rather the exclusive property Oonusave unless such website is under license from the trademark owner, and in which case the license is solely for the benefit and use of Oonusave unless otherwise indicated.<br /><br />"+ 
		"End User shall not upload, transmit or otherwise make something available any material that is protected by copyright, trademark or other proprietary right without the express permission of the owner of the copyright, trademark or other proprietary rights. Mobile Can Save has no explicit burden or responsibility to provide End User Accounts with indications, markings or anything else that may aid End Users decision whether the material is protected by copyright or trademark protected. End User is solely responsible for any damage resulting from any infringement of copyrights, trademarks, intellectual property or other damages arising from such submission. By submitting material to any public area of this website or any Micro Site, End User warrants that the owner of such material has expressly Mobile Can Save the royalty-free, perpetual, irrevocable, nonexclusive right and license to use, reproduce, modify, adapt, publish, translate and distribute such material (in whole or in part) worldwide and / or to incorporate it in other works in any form, media or technology now known or hereafter developed for the full term of copyright that may exist in this material. End User also permits any other end-users to access, view, store or reproduce the material for End User's personal use. End User hereby grants Mobile Can Save the right to edit, copy, publish and distribute any material made available on this page or Micro Site by End User.<br /><br />"+ 
		"The foregoing provisions of Section 5 applies to both, and is in favor of Mobile Can Save, its subsidiaries, affiliates, inland sea and its third party content providers and licensors and each have the right to assert and enforce such provisions directly or on its own behalf.<br /><br />",



		"<b>6. Copyright Policy</b> <br/><br/>" + 
		"Mobile Can Save reserves the right to terminate its agreement with an End User who repeatedly infringe third party copyright upon prompt notification to Mobile Can Save by the copyright owner or holder of copyright legal agent. Without limiting the foregoing, if you believe that a copyright work has been copied and sent via the website or any Micro Site in a way that constitutes copyright infringement, please contact Mobile Can Save the following information: (a) an electronic or physical signature of the person authorized to act on behalf of the owner of the copyrighted work (b) an identification and location of the site or any Microsite of the copyrighted work that you claim has been infringed (c) a written statement by you that you in good faith believe that the disputed use is not authorized by the owner, its agent or the law; (d) your name and contact information such as phone number or e-mail address, and (e) a statement by you that the above information in your Notice is accurate and under penalty of perjury that you are the copyright owner or authorized to act on the copyright owner's behalf. Contact information on Mobile Can Saves copyright agent for notice of claims of copyright infringement is as follows:<br />"+ 
		"Mobile Can Save aps<br/> "+
		"Attn: Copyright Agent <br />"+
		"Gydevang 39-41 <br />"+
		"3450 All Red <br />"+
		"support@mobilecansave.com<br /> <br />",


		"<b>7. Disclaimer of Warranty</b><br /> <br />"+
		"END USER expressly agree that use of this website and microsites to final beneficiaries OWN RISK. Neither Mobile Can Save, its affiliates nor any of their respective employees, agents, wholesalers, third party content providers or licensors, or any of their officers, directors, employees or agents, warrants that use of the website or other microsite uninterrupted or error free; they give nor any guarantee of (I) results, which MAY BE OBTAINED FROM THE USE OF THIS SITE, OR ANY microsite, or (II) the accuracy, reliability or content of information and services or vouchers PROVIDED via this website OR Microsites.<br /><br />"+ 
		"THIS SITE AND Microsites made available on an \"AS IS\" AND \"AVAILABLE\" BASIS. Mobile Can Save hereby disclaims all liability, warranties and conditions, whether EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO, the provisions of Section-infringement, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.",


		"<b>8. Limitation of Liability</b><br /><br />"+
		"UNDER NO CIRCUMSTANCES CAN Mobile Can Save, BE LIABLE FOR ANY INDIRECT, incidental, special, consequential or disciplinary punishment arising out of, or anything related to this Agreement. UNDER NO CIRCUMSTANCES WILL Mobile Can Saves liability for Mobile Can Save, exceed the amount paid for Mobile Can Save AND TOTAL liability arising out of or relating to this agreement shall not exceed the amount paid END OF USER, THROUGH THE LAST six months prior to the filing of any claim.<br /><br />",

		"<b>9. Monitoring </b><br /><br />"+
		"Mobile Can Save entitled but not obliged, to monitor the content of the website and all Microsites at all times, including any chat rooms and forums can now be included as part of the site to determine compliance with this Agreement and any operational rules introduced by Mobile Can Save, and to comply with applicable laws or regulations or the authorized government request. Without any limitation of the above, the Mobile Can Save have the right but not obligation, to remove any material, Mobile Can Save, in its sole discretion, to remove any material that would conflict with its provisions or otherwise objectionable.<br /><br />",


		"<b>10. Privacy </b><br /><br />"+
		"End User acknowledges that all discussion for ratings, comments, bulletin board services, chat rooms and / or other message or communication facilities (collectively \"Communities\") are public and not private communications, therefore others can read the End User's communications without End User's knowledge . Mobile Can Save does not control or gives his consent to the content, messages or information found in any society, and therefore disclaims Mobile CanSave specifically any liability for Communities and any actions resulting from End Users participation in any Community, including any objectionable content. Generally, any communication which End User sends to Mobile Can Save (whether in chat rooms, discussion groups, bulletin boards or otherwise) is deemed to be non-confidential. If a particular Web pages permit the submission of communications which will be reviewed by the Mobile Can Save as confidential, this must be stated on these pages. By submitting comments, messages or other information on the website or any Micro Site, the End User authorization to Mobile Can Save may use such comments, messages or information for advertising, advertising, market research or other authorized purposes without territorial, time or other constraints . For more information, see Mobile Can Save's Privacy Policy.<br /><br /> " , 

		"<b>11. License permit </b><br /><br />"+
		"By posting communications on or through this website or any Micro Site, be deemed End User for providing the Mobile Can Save royalty-free, perpetual, irrevocable, nonexclusive license to use, reproduce, modify, publish, edit, translate and distribute , perform, and display the communication alone or as part of other works in any form, media or technology whether now known or hereafter developed without territorial or time limitations, and to sublicense such rights through multiple layers of subcontractors.<br /><br />", 

		"<b>12. Compensation / Release </b><br /><br />"+
		"End User agrees to defend, indemnify and hold Mobile Can Save indemnify including its affiliates and their respective directors, officers, employees and agents from and from all claims and expenses, including attorneys' fees, arising out of or in connection with products or services purchased by End User User in connection with the Site or any Microsites.<br /><br />"+ 
		"End User is solely responsible for interaction with the inland sea and other users of the Site or the Microsites. To the extent permitted by applicable law, End User hereby releases Oonusave all claims or liability for any product or service from a trader, any action or inaction on the inland sea, including the inland sea's lack of compliance with applicable laws and / or non-compliance with the terms of a voucher, and any behavior or speech, either online or offline to other users.<br /><br />",

		"<b>13. Termination </b><br /><br />"+
		"Mobile Can Save may terminate this agreement at any time. Without limiting the foregoing, Mobile Can Save the right to immediately terminate any passwords or accounts End User in the event of conduct by End User as Mobile Can Save, in its sole discretion, considers to be unacceptable, or in case of default of this Agreement by End User. The provisions of sections 2, 4, 5, 6, 7, 10, 11 and 12 will remain valid after termination of this Agreement.", 

		"<b>14. Trademarks </b><br /><br />"+
		"Oonusave is a trademark of Mobile Can Save aps. All rights in connection with this trademark are hereby expressly reserved. Unless otherwise stated, all other trademarks appearing on Oonusaves website of their respective owners.<br />", 

		"<b>15. Third-party content </b><br /><br /> "+
		"Oonusave, corresponding to an Internet Service Provider, is a distributor (and not a present) of content supplied by third parties and end users. Therefore, Mobile Can Save aps no more editorial control over content than a non-public library, bookstore or newsstand. Any opinion, advice, opinion, services, offers or other information or content expressed or made available by third parties, including information providers, or any other than the user of their respective author (s) or distributor (s) and not by Mobile Can Save."+ 
		"<br />In all cases, the available content on this site represents opinions and judgments of the respective information provider, end user or other user not under contract with Mobile Can Save. Mobile Can Save neither endorses or is responsible for the accuracy or reliability of any opinion, advice or statement made on Oonusave by other than authorized spokespersons Oonusave employees acting in official capacity. In no event will Mobile Can Save be liable for any loss or damage caused by an end-user reliance on information obtained through Oonusave. It is for End User to evaluate the accuracy, completeness or usefulness of any information, advice, etc., or other content available through Oonusave."+
		"<br />Oonusave contains links to third party Web sites maintained by other content providers. These links are solely a service to you and not approving of Mobile Can Save the contents on such third party websites and Oonusave hereby disclaims any express representations regarding the content or accuracy of materials on such third-party websites. If End User decides to go in on a related third party websites is at your own risk to End User. Unless you have received a written agreement from Mobile Can Save that explicitly allows you to do it, do not give a link to the site or any Micro Site from any other website. Mobile Can Save reserves the right to revoke its consent to any link at any time in its sole discretion.",

	};



	public static String[] termsAndCondDan = {

		"<b>ACCEPT AF VILK�R OG BETINGELSER</b><br />"+ 
		"Ved at bruge dette websted (\"Webstedet\"), accepterer du (\"du\" eller \"slutbruger\") de vilk�r og betingelser, som vi (\"Mobile Can Save ApS\") har givet. Hvis du ikke �nsker at acceptere disse vilk�r og betingelser (\"Vilk�r for anvendelse\" eller \"aftalen\"), skal du undlade at bruge Oonusave.<br /><br /> "+
		"<b>I. Vilk�r for brug </b><br />"+
		"<b>1. Generelt. </b><br /><br />"+
		"Sitet er en interaktiv online-tjeneste, der drives af Mobile Can Save ApS, p� World Wide Web af Internettet (\"Web\"), og smartphone-enheder best�ende af informationstjenester, indhold og transaktioner kapaciteter leveret gennem Oonusave, datterselskaber af Oonusave eller forhandlere (\"forhandleren\") at udstede kuponer til salg (\"kuponer\"), som kan bruges til at opn� rabatter p� varer / ydelser, der tilbydes af forhandlere og andre tredjeparter. "+
		"Denne aftale indeholder alle de vilk�r og betingelser, der g�lder for slutbrugeren ved brugen af dette site. Ved at bruge dette site (andet end at l�se denne aftale for f�rste gang), accepterer slutbruger, at overholde alle vilk�r og betingelser herefter. Retten til at anvende denne hjemmeside er personlig for slutbruger og kan ikke overdrages til andre personer eller enheder. Slutbruger har ansvaret for at beskytte slutbrugers password (s), hvis et s�dant findes. Slutbruger erkender, at selv om internettet er ofte et sikkert milj�, nogle gange er der afbrydelser i servicen eller begivenheder, som Oonusave ikke er herre over, og Oonusave ikke er ansvarlig overfor tabte data, som bliver sendt p� internettet. Selv om det er Oonusaves m�l at g�re sitet tilg�ngeligt 24 timer om dagen, syv dage om ugen, kan Site v�re utilg�ngelig fra tid til anden af en eller anden grund, herunder, uden begr�nsning, rutinem�ssig vedligeholdelse. Du forst�r og anerkender, at p� grund af omst�ndigheder b�de inden for og uden for Oonusaves kontrol, kan adgang til sitet afbrydes, suspenderes eller opsiges fra tid til anden. "+
		"Oonusave skal have ret til p� ethvert tidspunkt at �ndre eller afbryde ethvert  aspekt eller tr�k ved webstedet, herunder, men ikke begr�nset til, indhold, �bningstider tilg�ngelighed og n�dvendigt udstyr til adgang til eller brug heraf. Desuden kan Oonusave afbryde formidlingen af en del af de oplysninger eller kategori af oplysninger, kan �ndre eller fjerne enhver transmission metode, og kan �ndre transmissionshastighed eller andre signal egenskaber. <br /><br />",

		"<b> 2. Modificerede Vilk�r</b><br /><br />"+
		"Oonusave forbeholder sig ret til altid at afbryde eller �ndre nogen af vores Vilk�r for anvendelse og / eller vores Privacy politik, som vi sk�nner n�dvendig eller �nskelig. S�danne �ndringer kan blandt andet v�re tilf�jelsen af visse gebyrer eller afgifter, eller �ndringer af disse. Hvis Oonusave udf�rer v�sentlige �ndringer, vil vi underrette dig ved at sende dig en e-mail til den e-mail adresse, der er registreret med din konto og / eller ved at l�gge en meddelelse om �ndringen p� hjemmesiden. Alle v�sentlige �ndringer i disse Vilk�r for anvendelse vil v�re effektiv ved afsendelsen af en e-mail meddelelse til dig, eller n�r meddelelsen er lagt ud p� vores hjemmeside, forudsat at disse �ndringer ikke vil g�lde for kuponer k�bt f�r ikrafttr�delsesdatoen for s�danne �ndringer. Disse �ndringer vil have �jeblikkelig virkning for nye brugere af vores website og eventuelle kuponer k�bt af s�danne nye brugere. Vi foresl�r derfor, at du genl�ser denne vigtige meddelelse, der indeholder vores � Vilk�r for brugen og Privacy Policy fra tid til anden for, at du vedligeholder og holder dig informeret om s�danne �ndringer. Enhver brug af websitet fra End User efter en s�dan meddelelse, anses for at v�re en accept af Slutbruger efter s�danne �ndringer. <br/>",

		"<b>3. Udstyr.</b><br /><br /> "+
		"End User er ansvarlig for at oppeb�re og vedligeholde alle telefoner, computere og anden hardware og andet n�dvendigt udstyr for at f� adgang til og brug af dette site og alle afgifter i forbindelse hermed. Oonusave er ikke ansvarlig for eventuelle skader p� End User udstyr som f�lge af brugen af dette websted. <br />",

		"<b>4. Slut brugere adf�rd.</b><br /><br />"+ 
		"Alle interaktioner p� dette websted og / eller Microsites skal overholde disse brugsvilk�r. Selv om vi bifalder og opfordre brugerinteraktion p� vores hjemmeside, �nsker vi og kr�ver, at alle slutbrugere begr�nser samtlige aktiviteter i forbindelse med brugen af dette site og de Microsites til, hvad der indeb�rer lovlige form�l. End User m� ikke sende eller overf�re via denne hjemmeside materiale, som kr�nker eller kr�nker nogen m�de p� andres rettigheder, eller materiale, der er ulovlig, truende, misbrugende, injurierende, kr�nkende for privatlivets fred eller omtale rettigheder, vulg�rt, obsk�nt, blasfemisk eller p� anden m�de anst�deligt, som opfordrer til adf�rd, der ville udg�re en strafbar handling, give anledning til civilretligt ansvar eller p� anden m�de overtr�de nogen lov, eller som, uden Oonusaves udtrykkelige forudg�ende, skriftlig godkendelse, indeholder reklame eller ethvert opfordring med hensyn til varer eller tjenesteydelser. Enhver adf�rd af en Slutbruger at Oonusaves enekompetence begr�nser eller h�mmer andre End User fra at bruge eller nyde denne Hjemmeside og / eller et af de Microsites er strengt forbudt. End User m� ikke bruge denne hjemmeside eller nogen af de Microsites at reklamere eller udf�re nogen form for kommerciel, religi�s, politisk eller ikke-kommerciel salgsaktivitet, herunder, men ikke begr�nset til, ops�gning af brugere af dette site og / eller Microsites at blive brugere af andre on-eller offline tjenester direkte eller indirekte konkurrence eller potentielt konkurrencedygtige med Oonusave. "+
		"Ovenst�ende bestemmelser i denne afdeling 4 g�lder ogs� for og er til gavn for Oonusave, skal dets datterselskaber, s�sterselskaber, forhandlere og dets tredjeparts indholdsudbydere og licensgivere, og hver har ret til at h�vde og h�ndh�ve s�danne bestemmelser, der direkte eller p� egne vegne.",

		"<b>5. Copyright og varem�rker </b><br /><br />"+
		"Alt som ligger p� eller i dette site, herunder Microsites, er Mobile Can Saves eksklusive ejendom,  eller bruges med udtrykkelig tilladelse fra copyright og / eller ejeren af varem�rket. Enhver kopiering, distribution, overf�rsel, udsendelse, links og dybe links, ELLER anden m�de �ndre p� denne hjemmeside eller nogen af de Microsites uden udtrykkelig skriftlig tilladelse fra Mobile Can Save, er strengt forbudt. Enhver overtr�delse af denne politik kan resultere i en copyright, varem�rker eller anden intellektuel ejendomsret overtr�delse, kan lade End User til civil og / eller strafferetlige sanktioner.<br /><br />"+ 
		"Denne hjemmeside og enhver Microsite indeholder ophavsretligt beskyttet materiale, varem�rker og anden navnebeskyttet information, herunder men ikke begr�nset til, tekst, software, fotografier, video, grafik, musik og lyd, og hele indholdet af hjemmesiden er beskyttet af ophavsret som et kollektivt arbejde under den danske og amerikanske copyright-lovgivning. Mobile Can Save er indehaver af ophavsretten i forbindelse med udv�lgelsen, koordinering, arrangement og forbedring af s�dant indhold, s�vel som det oprindelige indhold. Slutbrugeren m� ikke �ndre, udgive, overf�re, deltage i overf�rslen eller salget, skabe afledte v�rker eller p� anden m�de udnytte noget af indholdet, helt eller delvis. End User kan downloade, udskrive og / eller gemme ophavsretligt beskyttet materiale til End User's personlig brug. Medmindre andet udtrykkeligt er angivet i henhold til ophavsretslovgivningen, ingen kopiering, omfordeling, viderespredning, offentligg�relse eller kommerciel udnyttelse af downloadet materiale uden udtrykkelig tilladelse fra Mobile Can Save eller indehaveren af ophavsretten er tilladt. Hvis kopiering eller omfordeling eller offentligg�relse af ophavsretligt beskyttet materiale er tilladt, skal ingen �ndringer i eller sletning af kildeangivelser, varem�rke legende eller meddelelse om ophavsret g�res. End User erkender, at han / hun / den ikke erhverver nogen ejendomsret ved at downloade ophavsretligt beskyttet materiale. Varem�rker, der er placeret inden for eller p� Webstedet eller en Microsite ellers ejes eller drives sammen med Oonusave anses ikke for at v�re i det offentlige dom�ne, men snarere den eksklusive ejendom Oonusave, medmindre en s�dan hjemmeside er under licens fra ejeren af varem�rket, og og i s� fald licens er udelukkende til fordel og brug af Oonusave medmindre andet er angivet.<br /><br />"+
		"End User m� ikke uploade, sende eller p� anden m�de g�re noget materiale tilg�ngeligt, som er beskyttet af copyright, varem�rker eller andre ejendomsrettigheder uden udtrykkelig tilladelse fra ejeren af ophavsretten, varem�rket eller andre ejendomsrettigheder. Mobile Can Save har ikke nogen udtrykkelig byrde eller ansvar for at levere Slutbrugerkonti med angivelser, m�rker eller noget andet, som kan st�tte End Users afg�relse, om det p�g�ldende materiale er beskyttet af copyright eller varem�rkebeskyttet. End User er eneansvarlig for eventuelle skader som f�lge af enhver overtr�delse af ophavsrettigheder, varem�rker, immaterielle rettigheder eller andre skader, som skyldes en s�dan freml�ggelse. Ved at indsende materiale til et offentligt omr�de af denne hjemmeside eller nogen Microsite, End User garanterer, at ejeren af et s�dant materiale udtrykkeligt har givet Mobile Can Save den royalty-fri, vedvarende, uigenkaldelig, ikke-eksklusiv ret og licens til at bruge, reproducere, modificere, tilpasse , offentligg�re, overs�tte og distribuere s�danne materialer (helt eller delvist) p� verdensplan og / eller indf�je det i andre v�rker i enhver form, medie eller teknologi nu kendt eller herefter udvikles for hele l�betiden af ophavsretten, kan findes i dette materiale . End User ogs� tillader alle andre slutbrugere adgang til, se, gemme eller gengive materialet for at End User's personlig brug. End User giver hermed Mobile Can Save ret til at redigere, kopiere, offentligg�re og distribuere ethvert materiale der stilles til r�dighed p� denne side eller Microsite af End User.<br /><br />"+
		"Ovenst�ende bestemmelser i afsnit 5 g�lder b�de for, og er til fordel for Mobile Can Save, skal dets datterselskaber, s�sterselskaber, Indhaver og dets tredjeparts indholdsudbydere og licensgivere og har hver is�r ret til at h�vde og h�ndh�ve s�danne bestemmelser, der direkte eller p� egne vegne.<br /><br /> ",

		"<b> 6. Copyright Policy </b> <br /><br />"+
		"Mobile Can Save forbeholder sig ret til at opsige sin aftale med en Slutbruger, der gentagne gange overtr�der tredjemands ophavsrettigheder ved omg�ende meddelelse til Mobile Can Save af ejeren af ophavsretten eller indehaveren af ophavsrettens juridiske agent. Uden at begr�nse ovenst�ende, hvis du mener, at et ophavsretligt beskyttet v�rk er blevet kopieret og sendt via hjemmesiden eller nogen Microsite p� en m�de der anf�gter copyright, skal du kontakte Mobile Can Save med f�lgende oplysninger: (a) en elektronisk eller fysisk underskift fra den person er bemyndiget til at handle p� vegne af ejeren af det ophavsretligt beskyttede (b) en identifikation og placering p� hjemmesiden eller nogen Microsite af den copyright som du mener er blevet overtr�dt (c) en skriftlig erkl�ring fra dig, at du i god tro, mener at den omstridte brug ikke er autoriseret af ejeren, dennes agent eller loven; (d) dit navn og kontaktoplysninger, s�som telefon nummer eller e-mail-adresse, og (e) en erkl�ring fra Dem om, at ovenst�ende oplysninger i din meddelelse er korrekte og under straf for mened, at du er indehaver af ophavsretten eller tilladelse til at handle p� ophavsrethaveren vegne. Kontaktoplysninger p� Mobile Can Saves copyright-agent for meddelelse af kr�nkelser af ophavsrettigheder, er som f�lger: <br />"+
		"Mobile Can Save aps<br />"+
		"Att: Copyright Agent<br /> "+
		"Gydevang 39-41<br />"+
		"3450 Alleroed<br /> "+
		"support@mobilecansave.com<br /><br />",

		"<b>7. Fral�ggelse af Garanti. </b> <br /><br /> "+
		"SLUTBRUGER accepterer udtrykkeligt,  at brugen af dette websted, og microsites ER P� slutbrugers EGEN RISIKO. Hverken Mobile Can Save, dets partnere ELLER nogen af deres respektive medarbejdere, agenter, grossister, tredjepartsindhold udbydere eller LICENSGIVERE, eller nogen af deres bestyrelsesmedlemmer, direkt�rer, ansatte eller agenter, GARANTERER at brugen af hjemmeside eller andre microsite uden afbrydelser eller fejlfri; de giver heller ikke  nogen form for garanti for (I) resultaterne, som KAN F�S FRA BRUG AF DENNE SITE, ELLER NOGEN microsite, eller (II) n�jagtigheden, p�lideligheden eller indholdet af information og service eller v�rdikuponer LEVERET via denne hjemmeside ELLER Microsites.<br /><br />"+
		"DENNE  SITE OG Microsites g�res tilg�ngelige p� en \"SOM DEN ER\" OG \"TIL R�DIGHEDS\" BASIS. Mobile Can Save fraskriver sig herved alt ansvar, garantier og vilk�r, hvad enten UDTRYKKELIGE ELLER STILTIENDE, HERUNDER, MEN IKKE BEGR�NSET TIL, bestemmelserne i afsnit-kr�nkelse, SALGBARHED OG EGNETHED TIL ET BESTEMT FORM�L.<br />",

		"<b> 8. Begr�nsning af ansvar. </b><br /><br />"+
		"UNDER INGEN OMST�NDIGHEDER KAN Mobile Can Save, V�RE ANSVARLIG FOR NOGEN INDIREKTE, tilf�ldig, speciel, f�lgeskader eller diciplin�r straf, der udspringer af, eller noget  i forbindelse med denne aftale. UNDER INGEN OMST�NDIGHEDER VIL Mobile Can Saves ansvar i forbindelse med Mobile Can Save, overstige det bel�b betalt for en s�dan Mobile Can Save, OG TOTAL  SAMLET erstatningsansvar, som f�lge af eller relateret til denne aftale, ikke overstige deT bel�b betalt AF END USER, GENNEM DE SENESTE seks m�neder forud for indgivelsen af ethvert krav. <br /><br />",

		"<b>9. Overv�gning. </b><br /><br />"+
		"Mobile Can Save har ret til, men ikke forpligtet til, at overv�ge indholdet af hjemmesiden og alle Microsites p� alle tidspunkter, herunder eventuelle chatrum og fora, der kan herefter indg� som en del af sitet, for at fastsl� overholdelse af denne aftale og eventuelle operationelle regler indf�rt ved Mobile Can Save, samt at overholde g�ldende lovgivning eller regulering eller autoriseret regeringsanmodning. Uden nogen begr�nsning af ovenst�ende, skal Mobile Can Save have retten, men ikke forpligtet til, at fjerne alt materiale, Mobile Can Save, efter eget sk�n, at fjerne alt materiale, som vil v�re i strid med aftalens bestemmelser eller p� anden m�de st�dende.",

		"<b> 10. Beskyttelse af personlige oplysninger.</b> <br /><br /> "+
		"End User erkender, at alle dr�ftelser for ratings, kommentarer, opslagstavle service, chat rooms og / eller andre besked eller kommunikationsfaciliteter (samlet \"F�llesskaberne\") er offentlige og ikke privat kommunikation, og derfor kan andre l�se End User's kommunikation, uden End User's viden. Mobile Can Save hverken godkender eller giver sit sammentykke til indhold, beskeder eller informationer der kan findes i ethvert samfund, og derfor frasiger Mobile CanSave sig specifikt ethvert ansvar vedr�rende F�llesskaberne og af eventuelle sager, der udspringer fra Slutbrugere deltagelse i ethvert f�llesskab, herunder eventuelle anst�deligt indhold. Generelt enhver meddelelse, som End User sender til Mobile Can Save (om det er i chatrooms, i diskussionsgrupper, opslagstavler eller noget andet), anses det for at v�re ikke-fortroligt. Hvis en bestemt websider tillader indsendelse af meddelelser, som vil blive behandlet af Mobile Can Save, som fortroligt, skal dette v�re angivet p� disse sider. Ved at sende kommentarer, beskeder eller andre oplysninger p� webstedet eller nogen Microsite, giver End User tilladelse til at Mobile Can Save kan anvende s�danne kommentarer, beskeder eller information til reklame, reklamer, markedsunders�gelser eller andre tilladte form�l uden geografiske, tids-eller andre begr�nsninger. For mere information, se Mobile Can Save's Privacy Policy.<br />",

		"<b> 11. Licens tilladelse. </b> <br /><br />"+
		"Ved udstationering kommunikation p� eller gennem denne hjemmeside eller nogen Microsite, anses End User for at have ydet til Mobile Can Save royalty-frit, vedvarende, uigenkaldelig, ikke-eksklusiv licens til at bruge, reproducere, modificere, publicere, redigere, overs�tte, distribuere, udf�re, og vise meddelelsen alene eller som del af andre v�rker i enhver form, medie eller teknologi, om nu kendt eller herefter udvikles uden territorial eller tidsbegr�nsninger, og at underlicensere disse rettigheder gennem flere lag af underentrepren�rer. <br />",

		"<b> 12. Erstatning / Release. </b> <br /><br />"+
		"End User indvilliger i at forsvare, godtg�re og holde Mobile Can Save skadesl�s ogs� dets datterselskaber og deres respektive direkt�rer, ledere, medarbejdere og agenter fra og mod alle krav og udgifter, herunder advokatsal�rer, der udspringer af eller i forbindelse med produkter eller tjenesteydelser indk�bt af End User Bruger i forbindelse med webstedet eller nogen Microsites. "+
		"End User er alene ansvarlig for interaktion med Indhaver og andre brugere af webstedet eller Microsites. I det omfang det er tilladt i henhold til g�ldende lovgivning, End User hermed udgivelser Oonusave fra alle krav eller ansvar i forbindelse med et produkt eller en tjenesteydelse af en Handlende, enhver handling eller passivitet fra Indhaver, herunder Indhaver's manglende overholdelse af g�ldende lovgivning og / eller manglende overholdelse af vilk�rene i en voucher, og enhver adf�rd eller tale, enten online eller offline, for andre brugere. <br />",

		"<b>13. Opsigelse. </b><br /><br />"+
		"Mobile Can Save kan opsige denne aftale p� ethvert tidspunkt. Uden at begr�nse ovenst�ende har Mobile Can Save ret til �jeblikkeligt at oph�ve ethvert password eller regnskab End User i tilf�lde af adf�rd fra End User som Mobile Can Save, efter eget sk�n, anser for at v�re uacceptabel, eller i tilf�lde af misligholdelse af denne aftale af End User. Bestemmelserne i afsnit 2, 4, 5, 6, 7, 10, 11 og 12 vil fortsat v�re g�ldende efter opsigelsen af denne aftale. <br />",

		"<b>14. Varem�rker. </b><br /><br /> " +
		"Oonusave er et varem�rke tilh�rende Mobile Can Save aps. Alle rettigheder i forbindelse med dette varemaerke er herved udtrykkeligt forbeholdt. Medmindre andet er angivet, er alle andre varem�rker, som er anf�rt p� Oonusaves hjemmeside, tilh�rende deres respektive ejere.<br/><br />", 

		"<b>15. Tredjeparts indhold. </b><br /><br />"+
		"Oonusave, svarende til en Internet Service Provider, er en forhandler (og ikke en forel�gger), i indhold, der leveres af tredjeparter og slutbrugere. Derfor har Mobile Can Save aps ikke mere redaktionel kontrol over indholdet, end et ikke offentligt bibliotek, boghandel eller kiosk. Enhver mening, r�dgivning, udtalelse, tjenesteydelse, tilbud eller anden information, eller indhold udtrykt eller stillet til r�dighed af tredjemand, herunder informations leverand�rer, eller enhver anden end user af dem  respektive forfatter (e) eller distribut�r (r) og ikke af Mobile Can Save.<br />"+
		"I alle tilf�lde, er det tilg�ngelige indhold via dette site, repr�senterer udtalelser og domme de respektive oplysninger udbyder, slutbruger, eller andre bruger ikke under kontrakt med Mobile Can Save. Mobile Can Save hverken st�tter eller er ansvarlig for n�jagtigheden eller p�lideligheden af nogen udtalelse, r�dgivning, eller udtalelser lavet p� Oonusave af andre end autoriserede Oonusave ansatte talsm�nd, som handler i officiel kapacitet. Under ingen omst�ndigheder vil Mobile Can Save v�re ansvarlig for tab eller skade som f�lge af en slutbruger afh�ngighed af oplysninger indhentet gennem Oonusave. Det p�hviler End User at vurdere n�jagtigheden, fuldst�ndigheden eller nyttev�rdien af enhver information, r�dgivning mv, eller andet indhold tilg�ngeligt via Oonusave.<br />"+
		"Oonusave indeholder links til tredjeparts websteder, som vedligeholdes af andre indholdsleverand�rer. Disse links er udelukkende en service til dig og ikke godkendelse af Mobile Can Save af indholdet p� s�danne tredjeparts websteder, og Oonusave fraskriver sig herved udtrykkeligt enhver henvendelse vedr�rende indholdet eller n�jagtigheden af materialer p� s�danne tredjeparts websteder. Hvis End User beslutter sig for at g� i p� et tilknyttet tredjeparts websteder, er det p� egen risiko for End User. Medmindre du har modtaget en skriftlig aftale fra Mobile Can Save, som udtrykkeligt giver dig mulighed for at g�re det, m� du ikke give et hyperlink til webstedet eller nogen Microsite fra enhver anden hjemmeside. Mobile Can Save forbeholder sig ret til at tilbagekalde sit samtykke til enhver forbindelse til enhver tid efter eget sk�n.<br />"
	};



	public static String TandCTextEng = "<b>ACCEPTANCE OF TERMS AND CONDITIONS<b> <br /><br />" + 
	"<small>By using this site (\"Site\") you agree (\"you\" or \"end user\") the terms and conditions that we (\"Mobile Can Save Limited\") has been given. If you do not wish to accept these terms and conditions (\"Terms of Use\" or \"Agreement\"), you must not use Oonusave.</small> <br /><br />"+

	"<b>I. TERMS OF USE<b><br/>\n" + 
	"<b>1 General.<b> <br/><br/>" +

	"The site is an interactive online service operated by Mobile Can Save ApS on the World Wide Web of the Internet (\"Web\") and smartphone devices consisting of information services, content and transaction capabilities delivered through Oonusave, subsidiaries of Oonusave or distributors ( \"dealer\") to issue vouchers for sale (\"coupons\") which can be used to obtain discounts on goods / services offered by dealers and other third parties."+ 
	"This agreement contains all the terms and conditions applicable to the end user through the use of this site. By using this site (other than to read this Agreement for the first time), End User agrees to comply with all terms and conditions hereafter. The right to use this website is personal to End User and is not transferable to other persons or entities. End user has the responsibility to protect the end user's password (s), if one exists. End User acknowledges that although the Internet is often a secure environment, sometimes there are interruptions in service or events that Oonusave's control, and Oonusave not responsible to lost data that is sent on the Internet. Although it is Oonusaves goal of making the site available 24 hours a day, seven days a week, the Site unavailable from time to time for any reason, including, without limitation, routine maintenance. You understand and acknowledge that due to factors both within and outside Oonusaves control, access to the Site is interrupted, suspended or terminated from time to time."+ 
	"Oonusave shall have the right at any time to modify or discontinue any aspect or feature of the site, including but not limited to, content, hours of availability and equipment needed for access or use. Moreover Oonusave interrupt the provision of a part of the information or category of information that can alter or eliminate any transmission method and may change transmission speeds or other signal characteristics. "+

	"<br ><b>2. Modified Terms <b> <br/><br/>" +
	"Oonusave reserves the right at all times to discontinue or modify any of our Terms of Use and / or our Privacy Policy as we deem necessary or desirable. Such changes may in particular the addition of certain fees or taxes, or amendments thereto. If Oonusave performs substantial changes we will notify you by sending you an email to the email address registered with your account and / or by placing a notice of change on the website. Any significant changes to these Terms of Use will be effective upon dispatch of an email message to you, or when the message is posted on our website, provided that these changes will not apply to tickets purchased before the effective date of such changes. These changes will have immediate effect for new users of our site and any tickets purchased from these new users. We therefore suggest that you re-reads this important notice containing our - Terms of use and Privacy Policy from time to time to ensure that you maintain and keep you informed of such changes. Any use of the Site by End User after such notice shall be deemed to be an acceptance by End User after such changes."+ 

	"<br /><b>3 Equipment<b> <br/><br/>" +
	"End User is responsible to obtain and maintain all phones, computers and other hardware and other equipment needed for access to and use of this Site and all charges related thereto. Oonusave is not responsible for any damage to End User equipment as a result of using this site." +

	"<br /><b>4. End user behavior<b> <br/><br/>" +
	"All interactions on this site and / or the Microsites must comply with these Terms of Use. Although we welcome and encourage user interaction on our website, we want and require that all end users restrict all activities in connection with the use of this Site and the Microsites to that which involves lawful purposes. End User shall not post or transmit through this website material that violates or infringes in any way the rights of others, or materials that are unlawful, threatening, abusive, libelous, invasive of privacy or publicity rights, vulgar, obscene, profane or otherwise objectionable, which encourages conduct that would constitute a criminal offense, give rise to civil liability or otherwise violate any law, or who are not Oonusaves express prior written approval, contains advertising or any appeal in respect of goods or services. Any conduct by an End User to Oonusaves exclusive restricts or inhibits any other End User from using or enjoying this Site and / or any of the Microsites is strictly prohibited. End User may not use this site or any of the Microsites to advertise or perform any commercial, religious, political or non-commercial solicitation, including but not limited to, the solicitation of users of this site and / or the Microsites to become users other on-or offline services directly or indirectly competitive or potentially competitive with Oonusave."+ 
	"The foregoing provisions of this Section 4 shall also apply to and benefit Oonusave, its subsidiaries, affiliates, distributors, and its third party content providers and licensors and each shall have the right to assert and enforce such provisions directly or on its own behalf."+

	"<br /><b>5 Copyright and Trademarks <b> <br/><br/>" +
	"Everything located on or in this site, including the Microsites, is Mobile Can Saves exclusive property or used with express permission from copyright and / or owner of the mark. Any copying, distribution, transmission, broadcast, links and deep links or otherwise modify this website or any of the Microsites without express written permission of Mobile Can Save, is strictly prohibited. Any violation of this policy may result in a copyright, trademark or other intellectual property infringement can allow End User to civil and / or criminal penalties. <br/> <br />"+ 

	"This website and any Micro Site contains copyrighted material, trademarks and other proprietary information, including but not limited to, text, software, photographs, video, graphics, music and sound, and the entire contents of this website is protected by copyright as a collective work under Danish and U.S. copyright laws. Mobile Can Save is the owner of copyright in the selection, coordination, arrangement and enhancement of such content, as well as the original content. End User may not modify, publish, transmit, participate in the transfer or sale, create derivative works or otherwise exploit any content, in whole or in part. End User may download, print and / or save copyrighted material for End User's personal use. Unless otherwise expressly stated under copyright law, no copying, redistribution, retransmission, publication or commercial exploitation of downloaded material without the express permission of Mobile Can Save or copyright holder is prohibited. If copying or redistribution or publication of copyrighted material are permitted, no changes in or deletion of author attribution, trademark legend or copyright notice be published. End User acknowledges that he / she / it does not acquire any ownership rights by downloading copyrighted material. Trademarks that are located within or on the Site or a Microsite otherwise owned or operated jointly with Oonusave not considered to be in the public domain, but rather the exclusive property Oonusave unless such website is under license from the trademark owner, and in which case the license is solely for the benefit and use of Oonusave unless otherwise indicated.<br /><br />"+ 

	"End User shall not upload, transmit or otherwise make something available any material that is protected by copyright, trademark or other proprietary right without the express permission of the owner of the copyright, trademark or other proprietary rights. Mobile Can Save has no explicit burden or responsibility to provide End User Accounts with indications, markings or anything else that may aid End Users decision whether the material is protected by copyright or trademark protected. End User is solely responsible for any damage resulting from any infringement of copyrights, trademarks, intellectual property or other damages arising from such submission. By submitting material to any public area of this website or any Micro Site, End User warrants that the owner of such material has expressly Mobile Can Save the royalty-free, perpetual, irrevocable, nonexclusive right and license to use, reproduce, modify, adapt, publish, translate and distribute such material (in whole or in part) worldwide and / or to incorporate it in other works in any form, media or technology now known or hereafter developed for the full term of copyright that may exist in this material. End User also permits any other end-users to access, view, store or reproduce the material for End User's personal use. End User hereby grants Mobile Can Save the right to edit, copy, publish and distribute any material made available on this page or Micro Site by End User.<br /><br />"+ 

	"The foregoing provisions of Section 5 applies to both, and is in favor of Mobile Can Save, its subsidiaries, affiliates, inland sea and its third party content providers and licensors and each have the right to assert and enforce such provisions directly or on its own behalf.<br /><br />"+ 

	"<br /><b>6  Copyright Policy<b> <br/><br/>" + 
	"Mobile Can Save reserves the right to terminate its agreement with an End User who repeatedly infringe third party copyright upon prompt notification to Mobile Can Save by the copyright owner or holder of copyright legal agent. Without limiting the foregoing, if you believe that a copyright work has been copied and sent via the website or any Micro Site in a way that constitutes copyright infringement, please contact Mobile Can Save the following information: (a) an electronic or physical signature of the person authorized to act on behalf of the owner of the copyrighted work (b) an identification and location of the site or any Microsite of the copyrighted work that you claim has been infringed (c) a written statement by you that you in good faith believe that the disputed use is not authorized by the owner, its agent or the law; (d) your name and contact information such as phone number or e-mail address, and (e) a statement by you that the above information in your Notice is accurate and under penalty of perjury that you are the copyright owner or authorized to act on the copyright owner's behalf. Contact information on Mobile Can Saves copyright agent for notice of claims of copyright infringement is as follows:<br />"+ 
	"Mobile Can Save aps<br/> "+
	"Attn: Copyright Agent <br />"+
	"Gydevang 39-41 <br />"+
	"3450 All Red <br />"+
	"support@mobilecansave.com<br /> <br />"+

	"<br /><b>7 Disclaimer of Warranty</b><br /> <br />"+
	"END USER expressly agree that use of this website and microsites to final beneficiaries OWN RISK. Neither Mobile Can Save, its affiliates nor any of their respective employees, agents, wholesalers, third party content providers or licensors, or any of their officers, directors, employees or agents, warrants that use of the website or other microsite uninterrupted or error free; they give nor any guarantee of (I) results, which MAY BE OBTAINED FROM THE USE OF THIS SITE, OR ANY microsite, or (II) the accuracy, reliability or content of information and services or vouchers PROVIDED via this website OR Microsites.<br /><br />"+ 

	"THIS SITE AND Microsites made available on an \"AS IS\" AND \"AVAILABLE\" BASIS. Mobile Can Save hereby disclaims all liability, warranties and conditions, whether EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO, the provisions of Section-infringement, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE."+ 

	"<br /><b>8 Limitation of Liability</b><br /> <br />"+
	"UNDER NO CIRCUMSTANCES CAN Mobile Can Save, BE LIABLE FOR ANY INDIRECT, incidental, special, consequential or disciplinary punishment arising out of, or anything related to this Agreement. UNDER NO CIRCUMSTANCES WILL Mobile Can Saves liability for Mobile Can Save, exceed the amount paid for Mobile Can Save AND TOTAL liability arising out of or relating to this agreement shall not exceed the amount paid END OF USER, THROUGH THE LAST six months prior to the filing of any claim.<br /><br />"+ 

	"<br /><b>9 Monitoring </b><br /> <br />"+
	"Mobile Can Save entitled but not obliged, to monitor the content of the website and all Microsites at all times, including any chat rooms and forums can now be included as part of the site to determine compliance with this Agreement and any operational rules introduced by Mobile Can Save, and to comply with applicable laws or regulations or the authorized government request. Without any limitation of the above, the Mobile Can Save have the right but not obligation, to remove any material, Mobile Can Save, in its sole discretion, to remove any material that would conflict with its provisions or otherwise objectionable.<br /><br />"+ 

	"<br /><b>10 Privacy </b><br /> <br />"+
	"End User acknowledges that all discussion for ratings, comments, bulletin board services, chat rooms and / or other message or communication facilities (collectively \"Communities\") are public and not private communications, therefore others can read the End User's communications without End User's knowledge . Mobile Can Save does not control or gives his consent to the content, messages or information found in any society, and therefore disclaims Mobile CanSave specifically any liability for Communities and any actions resulting from End Users participation in any Community, including any objectionable content. Generally, any communication which End User sends to Mobile Can Save (whether in chat rooms, discussion groups, bulletin boards or otherwise) is deemed to be non-confidential. If a particular Web pages permit the submission of communications which will be reviewed by the Mobile Can Save as confidential, this must be stated on these pages. By submitting comments, messages or other information on the website or any Micro Site, the End User authorization to Mobile Can Save may use such comments, messages or information for advertising, advertising, market research or other authorized purposes without territorial, time or other constraints . For more information, see Mobile Can Save's Privacy Policy.<br /><br /> " + 

	"<br /><b>11 License permit </b><br /> <br />"+
	"By posting communications on or through this website or any Micro Site, be deemed End User for providing the Mobile Can Save royalty-free, perpetual, irrevocable, nonexclusive license to use, reproduce, modify, publish, edit, translate and distribute , perform, and display the communication alone or as part of other works in any form, media or technology whether now known or hereafter developed without territorial or time limitations, and to sublicense such rights through multiple layers of subcontractors.<br /><br />"+ 

	"<br /><b>12 Compensation / Release </b><br /> <br />"+
	"End User agrees to defend, indemnify and hold Mobile Can Save indemnify including its affiliates and their respective directors, officers, employees and agents from and from all claims and expenses, including attorneys' fees, arising out of or in connection with products or services purchased by End User User in connection with the Site or any Microsites.<br /><br />"+ 

	"End User is solely responsible for interaction with the inland sea and other users of the Site or the Microsites. To the extent permitted by applicable law, End User hereby releases Oonusave all claims or liability for any product or service from a trader, any action or inaction on the inland sea, including the inland sea's lack of compliance with applicable laws and / or non-compliance with the terms of a voucher, and any behavior or speech, either online or offline to other users.<br /><br />"+ 


	"<br /><b>13 Termination </b><br /> <br />"+
	"Mobile Can Save may terminate this agreement at any time. Without limiting the foregoing, Mobile Can Save the right to immediately terminate any passwords or accounts End User in the event of conduct by End User as Mobile Can Save, in its sole discretion, considers to be unacceptable, or in case of default of this Agreement by End User. The provisions of sections 2, 4, 5, 6, 7, 10, 11 and 12 will remain valid after termination of this Agreement.<br /><br />"+ 

	"<br /><b>14 Trademarks </b><br /> <br />"+
	"Oonusave is a trademark of Mobile Can Save aps. All rights in connection with this trademark are hereby expressly reserved. Unless otherwise stated, all other trademarks appearing on Oonusaves website of their respective owners.<br /><br />"+ 

	"<br /><b>15 Third-party content </b><br /> <br />"+
	"Oonusave, corresponding to an Internet Service Provider, is a distributor (and not a present) of content supplied by third parties and end users. Therefore, Mobile Can Save aps no more editorial control over content than a non-public library, bookstore or newsstand. Any opinion, advice, opinion, services, offers or other information or content expressed or made available by third parties, including information providers, or any other than the user of their respective author (s) or distributor (s) and not by Mobile Can Save.</b><br /><br />"+ 

	"<br />In all cases, the available content on this site represents opinions and judgments of the respective information provider, end user or other user not under contract with Mobile Can Save. Mobile Can Save neither endorses or is responsible for the accuracy or reliability of any opinion, advice or statement made on Oonusave by other than authorized spokespersons Oonusave employees acting in official capacity. In no event will Mobile Can Save be liable for any loss or damage caused by an end-user reliance on information obtained through Oonusave. It is for End User to evaluate the accuracy, completeness or usefulness of any information, advice, etc., or other content available through Oonusave.<br /><br />"+ 

	"<br />Oonusave contains links to third party Web sites maintained by other content providers. These links are solely a service to you and not approving of Mobile Can Save the contents on such third party websites and Oonusave hereby disclaims any express representations regarding the content or accuracy of materials on such third-party websites. If End User decides to go in on a related third party websites is at your own risk to End User. Unless you have received a written agreement from Mobile Can Save that explicitly allows you to do it, do not give a link to the site or any Micro Site from any other website. Mobile Can Save reserves the right to revoke its consent to any link at any time in its sole discretion.";



	public static String TandCTextDAN = "<b>ACCEPT AF VILK�R OG BETINGELSER<b><br /><br />"+ 
	"Ved at bruge dette websted (\"Webstedet\"), accepterer du (\"du\" eller \"slutbruger\") de vilk�r og betingelser, som vi (\"Mobile Can Save ApS\") har givet. Hvis du ikke �nsker at acceptere disse vilk�r og betingelser (\"Vilk�r for anvendelse\" eller \"aftalen\"), skal du undlade at bruge Oonusave.<br /><br /> "+

	"<b>I. VILK�R FOR ANVENDELSE </b><br>"+
	"<br /><b>1. Generelt. </b><br /><br />"+
	"Sitet er en interaktiv online-tjeneste, der drives af Mobile Can Save ApS, p� World Wide Web af Internettet (\"Web\"), og smartphone-enheder best�ende af informationstjenester, indhold og transaktioner kapaciteter leveret gennem Oonusave, datterselskaber af Oonusave eller forhandlere (\"forhandleren\") at udstede kuponer til salg (\"kuponer\"), som kan bruges til at opn� rabatter p� varer / ydelser, der tilbydes af forhandlere og andre tredjeparter. "+
	"Denne aftale indeholder alle de vilk�r og betingelser, der g�lder for slutbrugeren ved brugen af dette site. Ved at bruge dette site (andet end at l�se denne aftale for f�rste gang), accepterer slutbruger, at overholde alle vilk�r og betingelser herefter. Retten til at anvende denne hjemmeside er personlig for slutbruger og kan ikke overdrages til andre personer eller enheder. Slutbruger har ansvaret for at beskytte slutbrugers password (s), hvis et s�dant findes. Slutbruger erkender, at selv om internettet er ofte et sikkert milj�, nogle gange er der afbrydelser i servicen eller begivenheder, som Oonusave ikke er herre over, og Oonusave ikke er ansvarlig overfor tabte data, som bliver sendt p� internettet. Selv om det er Oonusaves m�l at g�re sitet tilg�ngeligt 24 timer om dagen, syv dage om ugen, kan Site v�re utilg�ngelig fra tid til anden af en eller anden grund, herunder, uden begr�nsning, rutinem�ssig vedligeholdelse. Du forst�r og anerkender, at p� grund af omst�ndigheder b�de inden for og uden for Oonusaves kontrol, kan adgang til sitet afbrydes, suspenderes eller opsiges fra tid til anden. "+
	"Oonusave skal have ret til p� ethvert tidspunkt at �ndre eller afbryde ethvert  aspekt eller tr�k ved webstedet, herunder, men ikke begr�nset til, indhold, �bningstider tilg�ngelighed og n�dvendigt udstyr til adgang til eller brug heraf. Desuden kan Oonusave afbryde formidlingen af en del af de oplysninger eller kategori af oplysninger, kan �ndre eller fjerne enhver transmission metode, og kan �ndre transmissionshastighed eller andre signal egenskaber. <br /><br />"+
	"<br /><b> 2. Modificerede Vilk�r</b></br>"+
	"Oonusave forbeholder sig ret til altid at afbryde eller �ndre nogen af vores Vilk�r for anvendelse og / eller vores Privacy politik, som vi sk�nner n�dvendig eller �nskelig. S�danne �ndringer kan blandt andet v�re tilf�jelsen af visse gebyrer eller afgifter, eller �ndringer af disse. Hvis Oonusave udf�rer v�sentlige �ndringer, vil vi underrette dig ved at sende dig en e-mail til den e-mail adresse, der er registreret med din konto og / eller ved at l�gge en meddelelse om �ndringen p� hjemmesiden. Alle v�sentlige �ndringer i disse Vilk�r for anvendelse vil v�re effektiv ved afsendelsen af en e-mail meddelelse til dig, eller n�r meddelelsen er lagt ud p� vores hjemmeside, forudsat at disse �ndringer ikke vil g�lde for kuponer k�bt f�r ikrafttr�delsesdatoen for s�danne �ndringer. Disse �ndringer vil have �jeblikkelig virkning for nye brugere af vores website og eventuelle kuponer k�bt af s�danne nye brugere. Vi foresl�r derfor, at du genl�ser denne vigtige meddelelse, der indeholder vores � Vilk�r for brugen og Privacy Policy fra tid til anden for, at du vedligeholder og holder dig informeret om s�danne �ndringer. Enhver brug af websitet fra End User efter en s�dan meddelelse, anses for at v�re en accept af Slutbruger efter s�danne �ndringer. <br/><br />"+
	"<br /> <b>3. Udstyr.</b><br /> "+
	"End User er ansvarlig for at oppeb�re og vedligeholde alle telefoner, computere og anden hardware og andet n�dvendigt udstyr for at f� adgang til og brug af dette site og alle afgifter i forbindelse hermed. Oonusave er ikke ansvarlig for eventuelle skader p� End User udstyr som f�lge af brugen af dette websted. <br /><br />"+
	"<br /><b>4. Slut brugere adf�rd.</b><br /><br />"+ 
	"Alle interaktioner p� dette websted og / eller Microsites skal overholde disse brugsvilk�r. Selv om vi bifalder og opfordre brugerinteraktion p� vores hjemmeside, �nsker vi og kr�ver, at alle slutbrugere begr�nser samtlige aktiviteter i forbindelse med brugen af dette site og de Microsites til, hvad der indeb�rer lovlige form�l. End User m� ikke sende eller overf�re via denne hjemmeside materiale, som kr�nker eller kr�nker nogen m�de p� andres rettigheder, eller materiale, der er ulovlig, truende, misbrugende, injurierende, kr�nkende for privatlivets fred eller omtale rettigheder, vulg�rt, obsk�nt, blasfemisk eller p� anden m�de anst�deligt, som opfordrer til adf�rd, der ville udg�re en strafbar handling, give anledning til civilretligt ansvar eller p� anden m�de overtr�de nogen lov, eller som, uden Oonusaves udtrykkelige forudg�ende, skriftlig godkendelse, indeholder reklame eller ethvert opfordring med hensyn til varer eller tjenesteydelser. Enhver adf�rd af en Slutbruger at Oonusaves enekompetence begr�nser eller h�mmer andre End User fra at bruge eller nyde denne Hjemmeside og / eller et af de Microsites er strengt forbudt. End User m� ikke bruge denne hjemmeside eller nogen af de Microsites at reklamere eller udf�re nogen form for kommerciel, religi�s, politisk eller ikke-kommerciel salgsaktivitet, herunder, men ikke begr�nset til, ops�gning af brugere af dette site og / eller Microsites at blive brugere af andre on-eller offline tjenester direkte eller indirekte konkurrence eller potentielt konkurrencedygtige med Oonusave. "+
	"Ovenst�ende bestemmelser i denne afdeling 4 g�lder ogs� for og er til gavn for Oonusave, skal dets datterselskaber, s�sterselskaber, forhandlere og dets tredjeparts indholdsudbydere og licensgivere, og hver har ret til at h�vde og h�ndh�ve s�danne bestemmelser, der direkte eller p� egne vegne."+
	"<br /><b>5. Copyright og varem�rker </b><br /><br />"+
	"Alt som ligger p� eller i dette site, herunder Microsites, er Mobile Can Saves eksklusive ejendom,  eller bruges med udtrykkelig tilladelse fra copyright og / eller ejeren af varem�rket. Enhver kopiering, distribution, overf�rsel, udsendelse, links og dybe links, ELLER anden m�de �ndre p� denne hjemmeside eller nogen af de Microsites uden udtrykkelig skriftlig tilladelse fra Mobile Can Save, er strengt forbudt. Enhver overtr�delse af denne politik kan resultere i en copyright, varem�rker eller anden intellektuel ejendomsret overtr�delse, kan lade End User til civil og / eller strafferetlige sanktioner."+ 
	"Denne hjemmeside og enhver Microsite indeholder ophavsretligt beskyttet materiale, varem�rker og anden navnebeskyttet information, herunder men ikke begr�nset til, tekst, software, fotografier, video, grafik, musik og lyd, og hele indholdet af hjemmesiden er beskyttet af ophavsret som et kollektivt arbejde under den danske og amerikanske copyright-lovgivning. Mobile Can Save er indehaver af ophavsretten i forbindelse med udv�lgelsen, koordinering, arrangement og forbedring af s�dant indhold, s�vel som det oprindelige indhold. Slutbrugeren m� ikke �ndre, udgive, overf�re, deltage i overf�rslen eller salget, skabe afledte v�rker eller p� anden m�de udnytte noget af indholdet, helt eller delvis. End User kan downloade, udskrive og / eller gemme ophavsretligt beskyttet materiale til End User's personlig brug. Medmindre andet udtrykkeligt er angivet i henhold til ophavsretslovgivningen, ingen kopiering, omfordeling, viderespredning, offentligg�relse eller kommerciel udnyttelse af downloadet materiale uden udtrykkelig tilladelse fra Mobile Can Save eller indehaveren af ophavsretten er tilladt. Hvis kopiering eller omfordeling eller offentligg�relse af ophavsretligt beskyttet materiale er tilladt, skal ingen �ndringer i eller sletning af kildeangivelser, varem�rke legende eller meddelelse om ophavsret g�res. End User erkender, at han / hun / den ikke erhverver nogen ejendomsret ved at downloade ophavsretligt beskyttet materiale. Varem�rker, der er placeret inden for eller p� Webstedet eller en Microsite ellers ejes eller drives sammen med Oonusave anses ikke for at v�re i det offentlige dom�ne, men snarere den eksklusive ejendom Oonusave, medmindre en s�dan hjemmeside er under licens fra ejeren af varem�rket, og og i s� fald licens er udelukkende til fordel og brug af Oonusave medmindre andet er angivet."+

	"End User m� ikke uploade, sende eller p� anden m�de g�re noget materiale tilg�ngeligt, som er beskyttet af copyright, varem�rker eller andre ejendomsrettigheder uden udtrykkelig tilladelse fra ejeren af ophavsretten, varem�rket eller andre ejendomsrettigheder. Mobile Can Save har ikke nogen udtrykkelig byrde eller ansvar for at levere Slutbrugerkonti med angivelser, m�rker eller noget andet, som kan st�tte End Users afg�relse, om det p�g�ldende materiale er beskyttet af copyright eller varem�rkebeskyttet. End User er eneansvarlig for eventuelle skader som f�lge af enhver overtr�delse af ophavsrettigheder, varem�rker, immaterielle rettigheder eller andre skader, som skyldes en s�dan freml�ggelse. Ved at indsende materiale til et offentligt omr�de af denne hjemmeside eller nogen Microsite, End User garanterer, at ejeren af et s�dant materiale udtrykkeligt har givet Mobile Can Save den royalty-fri, vedvarende, uigenkaldelig, ikke-eksklusiv ret og licens til at bruge, reproducere, modificere, tilpasse , offentligg�re, overs�tte og distribuere s�danne materialer (helt eller delvist) p� verdensplan og / eller indf�je det i andre v�rker i enhver form, medie eller teknologi nu kendt eller herefter udvikles for hele l�betiden af ophavsretten, kan findes i dette materiale . End User ogs� tillader alle andre slutbrugere adgang til, se, gemme eller gengive materialet for at End User's personlig brug. End User giver hermed Mobile Can Save ret til at redigere, kopiere, offentligg�re og distribuere ethvert materiale der stilles til r�dighed p� denne side eller Microsite af End User."+

	"Ovenst�ende bestemmelser i afsnit 5 g�lder b�de for, og er til fordel for Mobile Can Save, skal dets datterselskaber, s�sterselskaber, Indhaver og dets tredjeparts indholdsudbydere og licensgivere og har hver is�r ret til at h�vde og h�ndh�ve s�danne bestemmelser, der direkte eller p� egne vegne. "+
	"<br /> <b> 6. Copyright Policy </b> <br />"+
	"Mobile Can Save forbeholder sig ret til at opsige sin aftale med en Slutbruger, der gentagne gange overtr�der tredjemands ophavsrettigheder ved omg�ende meddelelse til Mobile Can Save af ejeren af ophavsretten eller indehaveren af ophavsrettens juridiske agent. Uden at begr�nse ovenst�ende, hvis du mener, at et ophavsretligt beskyttet v�rk er blevet kopieret og sendt via hjemmesiden eller nogen Microsite p� en m�de der anf�gter copyright, skal du kontakte Mobile Can Save med f�lgende oplysninger: (a) en elektronisk eller fysisk underskift fra den person er bemyndiget til at handle p� vegne af ejeren af det ophavsretligt beskyttede (b) en identifikation og placering p� hjemmesiden eller nogen Microsite af den copyright som du mener er blevet overtr�dt (c) en skriftlig erkl�ring fra dig, at du i god tro, mener at den omstridte brug ikke er autoriseret af ejeren, dennes agent eller loven; (d) dit navn og kontaktoplysninger, s�som telefon nummer eller e-mail-adresse, og (e) en erkl�ring fra Dem om, at ovenst�ende oplysninger i din meddelelse er korrekte og under straf for mened, at du er indehaver af ophavsretten eller tilladelse til at handle p� ophavsrethaveren vegne. Kontaktoplysninger p� Mobile Can Saves copyright-agent for meddelelse af kr�nkelser af ophavsrettigheder, er som f�lger: "+
	"Mobile Can Save aps"+
	"Att: Copyright Agent "+
	"Gydevang 39-41"+
	"3450 Alleroed "+
	"support@mobilecansave.com"+
	" <br /> <b>7. Fral�ggelse af Garanti. </b> <br /> "+
	"SLUTBRUGER accepterer udtrykkeligt,  at brugen af dette websted, og microsites ER P� slutbrugers EGEN RISIKO. Hverken Mobile Can Save, dets partnere ELLER nogen af deres respektive medarbejdere, agenter, grossister, tredjepartsindhold udbydere eller LICENSGIVERE, eller nogen af deres bestyrelsesmedlemmer, direkt�rer, ansatte eller agenter, GARANTERER at brugen af hjemmeside eller andre microsite uden afbrydelser eller fejlfri; de giver heller ikke  nogen form for garanti for (I) resultaterne, som KAN F�S FRA BRUG AF DENNE SITE, ELLER NOGEN microsite, eller (II) n�jagtigheden, p�lideligheden eller indholdet af information og service eller v�rdikuponer LEVERET via denne hjemmeside ELLER Microsites."+
	"DENNE  SITE OG Microsites g�res tilg�ngelige p� en \"SOM DEN ER\" OG \"TIL R�DIGHEDS\" BASIS. Mobile Can Save fraskriver sig herved alt ansvar, garantier og vilk�r, hvad enten UDTRYKKELIGE ELLER STILTIENDE, HERUNDER, MEN IKKE BEGR�NSET TIL, bestemmelserne i afsnit-kr�nkelse, SALGBARHED OG EGNETHED TIL ET BESTEMT FORM�L."+
	" <br /> <b> 8. Begr�nsning af ansvar. </b><br /><br />"+
	"UNDER INGEN OMST�NDIGHEDER KAN Mobile Can Save, V�RE ANSVARLIG FOR NOGEN INDIREKTE, tilf�ldig, speciel, f�lgeskader eller diciplin�r straf, der udspringer af, eller noget  i forbindelse med denne aftale. UNDER INGEN OMST�NDIGHEDER VIL Mobile Can Saves ansvar i forbindelse med Mobile Can Save, overstige det bel�b betalt for en s�dan Mobile Can Save, OG TOTAL  SAMLET erstatningsansvar, som f�lge af eller relateret til denne aftale, ikke overstige deT bel�b betalt AF END USER, GENNEM DE SENESTE seks m�neder forud for indgivelsen af ethvert krav. <br /><br />"+
	"<br /><b>9. Overv�gning. </b><br /><br />"+
	"Mobile Can Save har ret til, men ikke forpligtet til, at overv�ge indholdet af hjemmesiden og alle Microsites p� alle tidspunkter, herunder eventuelle chatrum og fora, der kan herefter indg� som en del af sitet, for at fastsl� overholdelse af denne aftale og eventuelle operationelle regler indf�rt ved Mobile Can Save, samt at overholde g�ldende lovgivning eller regulering eller autoriseret regeringsanmodning. Uden nogen begr�nsning af ovenst�ende, skal Mobile Can Save have retten, men ikke forpligtet til, at fjerne alt materiale, Mobile Can Save, efter eget sk�n, at fjerne alt materiale, som vil v�re i strid med aftalens bestemmelser eller p� anden m�de st�dende."+

	"<br /> <b> 10. Beskyttelse af personlige oplysninger.</b> <br /> "+
	"End User erkender, at alle dr�ftelser for ratings, kommentarer, opslagstavle service, chat rooms og / eller andre besked eller kommunikationsfaciliteter (samlet \"F�llesskaberne\") er offentlige og ikke privat kommunikation, og derfor kan andre l�se End User's kommunikation, uden End User's viden. Mobile Can Save hverken godkender eller giver sit sammentykke til indhold, beskeder eller informationer der kan findes i ethvert samfund, og derfor frasiger Mobile CanSave sig specifikt ethvert ansvar vedr�rende F�llesskaberne og af eventuelle sager, der udspringer fra Slutbrugere deltagelse i ethvert f�llesskab, herunder eventuelle anst�deligt indhold. Generelt enhver meddelelse, som End User sender til Mobile Can Save (om det er i chatrooms, i diskussionsgrupper, opslagstavler eller noget andet), anses det for at v�re ikke-fortroligt. Hvis en bestemt websider tillader indsendelse af meddelelser, som vil blive behandlet af Mobile Can Save, som fortroligt, skal dette v�re angivet p� disse sider. Ved at sende kommentarer, beskeder eller andre oplysninger p� webstedet eller nogen Microsite, giver End User tilladelse til at Mobile Can Save kan anvende s�danne kommentarer, beskeder eller information til reklame, reklamer, markedsunders�gelser eller andre tilladte form�l uden geografiske, tids-eller andre begr�nsninger. For mere information, se Mobile Can Save's Privacy Policy."+

	"<br /> <b> 11. Licens tilladelse. </b> <br />"+
	"Ved udstationering kommunikation p� eller gennem denne hjemmeside eller nogen Microsite, anses End User for at have ydet til Mobile Can Save royalty-frit, vedvarende, uigenkaldelig, ikke-eksklusiv licens til at bruge, reproducere, modificere, publicere, redigere, overs�tte, distribuere, udf�re, og vise meddelelsen alene eller som del af andre v�rker i enhver form, medie eller teknologi, om nu kendt eller herefter udvikles uden territorial eller tidsbegr�nsninger, og at underlicensere disse rettigheder gennem flere lag af underentrepren�rer. "+
	"<br /> <b> 12. Erstatning / Release. </b> <br />"+
	"End User indvilliger i at forsvare, godtg�re og holde Mobile Can Save skadesl�s ogs� dets datterselskaber og deres respektive direkt�rer, ledere, medarbejdere og agenter fra og mod alle krav og udgifter, herunder advokatsal�rer, der udspringer af eller i forbindelse med produkter eller tjenesteydelser indk�bt af End User Bruger i forbindelse med webstedet eller nogen Microsites. "+
	"End User er alene ansvarlig for interaktion med Indhaver og andre brugere af webstedet eller Microsites. I det omfang det er tilladt i henhold til g�ldende lovgivning, End User hermed udgivelser Oonusave fra alle krav eller ansvar i forbindelse med et produkt eller en tjenesteydelse af en Handlende, enhver handling eller passivitet fra Indhaver, herunder Indhaver's manglende overholdelse af g�ldende lovgivning og / eller manglende overholdelse af vilk�rene i en voucher, og enhver adf�rd eller tale, enten online eller offline, for andre brugere. "+
	"<br /> <b>13. Opsigelse. </b><br />"+
	"Mobile Can Save kan opsige denne aftale p� ethvert tidspunkt. Uden at begr�nse ovenst�ende har Mobile Can Save ret til �jeblikkeligt at oph�ve ethvert password eller regnskab End User i tilf�lde af adf�rd fra End User som Mobile Can Save, efter eget sk�n, anser for at v�re uacceptabel, eller i tilf�lde af misligholdelse af denne aftale af End User. Bestemmelserne i afsnit 2, 4, 5, 6, 7, 10, 11 og 12 vil fortsat v�re g�ldende efter opsigelsen af denne aftale. <br /><br />"+
	"<br /> <b>14. Varem�rker. >/b><br /> " +
	"Oonusave er et varem�rke tilh�rende Mobile Can Save aps. Alle rettigheder i forbindelse med dette varemaerke er herved udtrykkeligt forbeholdt. Medmindre andet er angivet, er alle andre varem�rker, som er anf�rt p� Oonusaves hjemmeside, tilh�rende deres respektive ejere.<br/><br />"+ 

	"<br /> <b>15. Tredjeparts indhold. </b><br/><br />"+
	"Oonusave, svarende til en Internet Service Provider, er en forhandler (og ikke en forel�gger), i indhold, der leveres af tredjeparter og slutbrugere. Derfor har Mobile Can Save aps ikke mere redaktionel kontrol over indholdet, end et ikke offentligt bibliotek, boghandel eller kiosk. Enhver mening, r�dgivning, udtalelse, tjenesteydelse, tilbud eller anden information, eller indhold udtrykt eller stillet til r�dighed af tredjemand, herunder informations leverand�rer, eller enhver anden end user af dem  respektive forfatter (e) eller distribut�r (r) og ikke af Mobile Can Save."+
	"I alle tilf�lde, er det tilg�ngelige indhold via dette site, repr�senterer udtalelser og domme de respektive oplysninger udbyder, slutbruger, eller andre bruger ikke under kontrakt med Mobile Can Save. Mobile Can Save hverken st�tter eller er ansvarlig for n�jagtigheden eller p�lideligheden af nogen udtalelse, r�dgivning, eller udtalelser lavet p� Oonusave af andre end autoriserede Oonusave ansatte talsm�nd, som handler i officiel kapacitet. Under ingen omst�ndigheder vil Mobile Can Save v�re ansvarlig for tab eller skade som f�lge af en slutbruger afh�ngighed af oplysninger indhentet gennem Oonusave. Det p�hviler End User at vurdere n�jagtigheden, fuldst�ndigheden eller nyttev�rdien af enhver information, r�dgivning mv, eller andet indhold tilg�ngeligt via Oonusave."+
	"Oonusave indeholder links til tredjeparts websteder, som vedligeholdes af andre indholdsleverand�rer. Disse links er udelukkende en service til dig og ikke godkendelse af Mobile Can Save af indholdet p� s�danne tredjeparts websteder, og Oonusave fraskriver sig herved udtrykkeligt enhver henvendelse vedr�rende indholdet eller n�jagtigheden af materialer p� s�danne tredjeparts websteder. Hvis End User beslutter sig for at g� i p� et tilknyttet tredjeparts websteder, er det p� egen risiko for End User. Medmindre du har modtaget en skriftlig aftale fra Mobile Can Save, som udtrykkeligt giver dig mulighed for at g�re det, m� du ikke give et hyperlink til webstedet eller nogen Microsite fra enhver anden hjemmeside. Mobile Can Save forbeholder sig ret til at tilbagekalde sit samtykke til enhver forbindelse til enhver tid efter eget sk�n.";


	//ACCEPT AF VILK�R OG BETINGELSER 
	//Ved at bruge dette websted ("Webstedet"), accepterer du ("du" eller "slutbruger") de vilk�r og betingelser, som vi ("Mobile Can Save ApS") har givet. Hvis du ikke �nsker at acceptere disse vilk�r og betingelser ("Vilk�r for anvendelse" eller "aftalen"), skal du undlade at bruge Oonusave. 
	//
	//I. VILK�R FOR ANVENDELSE 
	//1. Generelt. 
	//
	//Sitet er en interaktiv online-tjeneste, der drives af Mobile Can Save ApS, p� World Wide Web af Internettet ("Web"), og smartphone-enheder best�ende af informationstjenester, indhold og transaktioner kapaciteter leveret gennem Oonusave, datterselskaber af Oonusave eller forhandlere ("forhandleren�) at udstede kuponer til salg ("kuponer"), som kan bruges til at opn� rabatter p� varer / ydelser, der tilbydes af forhandlere og andre tredjeparter. 
	//Denne aftale indeholder alle de vilk�r og betingelser, der g�lder for slutbrugeren ved brugen af dette site. Ved at bruge dette site (andet end at l�se denne aftale for f�rste gang), accepterer slutbruger, at overholde alle vilk�r og betingelser herefter. Retten til at anvende denne hjemmeside er personlig for slutbruger og kan ikke overdrages til andre personer eller enheder. Slutbruger har ansvaret for at beskytte slutbrugers password (s), hvis et s�dant findes. Slutbruger erkender, at selv om internettet er ofte et sikkert milj�, nogle gange er der afbrydelser i servicen eller begivenheder, som Oonusave ikke er herre over, og Oonusave ikke er ansvarlig overfor tabte data, som bliver sendt p� internettet. Selv om det er Oonusaves m�l at g�re sitet tilg�ngeligt 24 timer om dagen, syv dage om ugen, kan Site v�re utilg�ngelig fra tid til anden af en eller anden grund, herunder, uden begr�nsning, rutinem�ssig vedligeholdelse. Du forst�r og anerkender, at p� grund af omst�ndigheder b�de inden for og uden for Oonusaves kontrol, kan adgang til sitet afbrydes, suspenderes eller opsiges fra tid til anden. 
	//Oonusave skal have ret til p� ethvert tidspunkt at �ndre eller afbryde ethvert  aspekt eller tr�k ved webstedet, herunder, men ikke begr�nset til, indhold, �bningstider tilg�ngelighed og n�dvendigt udstyr til adgang til eller brug heraf. Desuden kan Oonusave afbryde formidlingen af en del af de oplysninger eller kategori af oplysninger, kan �ndre eller fjerne enhver transmission metode, og kan �ndre transmissionshastighed eller andre signal egenskaber. 
	//
	//2. Modificerede Vilk�r
	//Oonusave forbeholder sig ret til altid at afbryde eller �ndre nogen af vores Vilk�r for anvendelse og / eller vores Privacy politik, som vi sk�nner n�dvendig eller �nskelig. S�danne �ndringer kan blandt andet v�re tilf�jelsen af visse gebyrer eller afgifter, eller �ndringer af disse. Hvis Oonusave udf�rer v�sentlige �ndringer, vil vi underrette dig ved at sende dig en e-mail til den e-mail adresse, der er registreret med din konto og / eller ved at l�gge en meddelelse om �ndringen p� hjemmesiden. Alle v�sentlige �ndringer i disse Vilk�r for anvendelse vil v�re effektiv ved afsendelsen af en e-mail meddelelse til dig, eller n�r meddelelsen er lagt ud p� vores hjemmeside, forudsat at disse �ndringer ikke vil g�lde for kuponer k�bt f�r ikrafttr�delsesdatoen for s�danne �ndringer. Disse �ndringer vil have �jeblikkelig virkning for nye brugere af vores website og eventuelle kuponer k�bt af s�danne nye brugere. Vi foresl�r derfor, at du genl�ser denne vigtige meddelelse, der indeholder vores � Vilk�r for brugen og Privacy Policy fra tid til anden for, at du vedligeholder og holder dig informeret om s�danne �ndringer. Enhver brug af websitet fra End User efter en s�dan meddelelse, anses for at v�re en accept af Slutbruger efter s�danne �ndringer. 
	//
	//3. Udstyr. 
	//End User er ansvarlig for at oppeb�re og vedligeholde alle telefoner, computere og anden hardware og andet n�dvendigt udstyr for at f� adgang til og brug af dette site og alle afgifter i forbindelse hermed. Oonusave er ikke ansvarlig for eventuelle skader p� End User udstyr som f�lge af brugen af dette websted. 
	//
	//4. Slut brugere adf�rd. 
	//Alle interaktioner p� dette websted og / eller Microsites skal overholde disse brugsvilk�r. Selv om vi bifalder og opfordre brugerinteraktion p� vores hjemmeside, �nsker vi og kr�ver, at alle slutbrugere begr�nser samtlige aktiviteter i forbindelse med brugen af dette site og de Microsites til, hvad der indeb�rer lovlige form�l. End User m� ikke sende eller overf�re via denne hjemmeside materiale, som kr�nker eller kr�nker nogen m�de p� andres rettigheder, eller materiale, der er ulovlig, truende, misbrugende, injurierende, kr�nkende for privatlivets fred eller omtale rettigheder, vulg�rt, obsk�nt, blasfemisk eller p� anden m�de anst�deligt, som opfordrer til adf�rd, der ville udg�re en strafbar handling, give anledning til civilretligt ansvar eller p� anden m�de overtr�de nogen lov, eller som, uden Oonusaves udtrykkelige forudg�ende, skriftlig godkendelse, indeholder reklame eller ethvert opfordring med hensyn til varer eller tjenesteydelser. Enhver adf�rd af en Slutbruger at Oonusaves enekompetence begr�nser eller h�mmer andre End User fra at bruge eller nyde denne Hjemmeside og / eller et af de Microsites er strengt forbudt. End User m� ikke bruge denne hjemmeside eller nogen af de Microsites at reklamere eller udf�re nogen form for kommerciel, religi�s, politisk eller ikke-kommerciel salgsaktivitet, herunder, men ikke begr�nset til, ops�gning af brugere af dette site og / eller Microsites at blive brugere af andre on-eller offline tjenester direkte eller indirekte konkurrence eller potentielt konkurrencedygtige med Oonusave. 
	//Ovenst�ende bestemmelser i denne afdeling 4 g�lder ogs� for og er til gavn for Oonusave, skal dets datterselskaber, s�sterselskaber, forhandlere og dets tredjeparts indholdsudbydere og licensgivere, og hver har ret til at h�vde og h�ndh�ve s�danne bestemmelser, der direkte eller p� egne vegne.
	//5. Copyright og varem�rker
	//Alt som ligger p� eller i dette site, herunder Microsites, er Mobile Can Saves eksklusive ejendom,  eller bruges med udtrykkelig tilladelse fra copyright og / eller ejeren af varem�rket. Enhver kopiering, distribution, overf�rsel, udsendelse, links og dybe links, ELLER anden m�de �ndre p� denne hjemmeside eller nogen af de Microsites uden udtrykkelig skriftlig tilladelse fra Mobile Can Save, er strengt forbudt. Enhver overtr�delse af denne politik kan resultere i en copyright, varem�rker eller anden intellektuel ejendomsret overtr�delse, kan lade End User til civil og / eller strafferetlige sanktioner. 
	//
	//Denne hjemmeside og enhver Microsite indeholder ophavsretligt beskyttet materiale, varem�rker og anden navnebeskyttet information, herunder men ikke begr�nset til, tekst, software, fotografier, video, grafik, musik og lyd, og hele indholdet af hjemmesiden er beskyttet af ophavsret som et kollektivt arbejde under den danske og amerikanske copyright-lovgivning. Mobile Can Save er indehaver af ophavsretten i forbindelse med udv�lgelsen, koordinering, arrangement og forbedring af s�dant indhold, s�vel som det oprindelige indhold. Slutbrugeren m� ikke �ndre, udgive, overf�re, deltage i overf�rslen eller salget, skabe afledte v�rker eller p� anden m�de udnytte noget af indholdet, helt eller delvis. End User kan downloade, udskrive og / eller gemme ophavsretligt beskyttet materiale til End User's personlig brug. Medmindre andet udtrykkeligt er angivet i henhold til ophavsretslovgivningen, ingen kopiering, omfordeling, viderespredning, offentligg�relse eller kommerciel udnyttelse af downloadet materiale uden udtrykkelig tilladelse fra Mobile Can Save eller indehaveren af ophavsretten er tilladt. Hvis kopiering eller omfordeling eller offentligg�relse af ophavsretligt beskyttet materiale er tilladt, skal ingen �ndringer i eller sletning af kildeangivelser, varem�rke legende eller meddelelse om ophavsret g�res. End User erkender, at han / hun / den ikke erhverver nogen ejendomsret ved at downloade ophavsretligt beskyttet materiale. Varem�rker, der er placeret inden for eller p� Webstedet eller en Microsite ellers ejes eller drives sammen med Oonusave anses ikke for at v�re i det offentlige dom�ne, men snarere den eksklusive ejendom Oonusave, medmindre en s�dan hjemmeside er under licens fra ejeren af varem�rket, og og i s� fald licens er udelukkende til fordel og brug af Oonusave medmindre andet er angivet.
	// 
	//End User m� ikke uploade, sende eller p� anden m�de g�re noget materiale tilg�ngeligt, som er beskyttet af copyright, varem�rker eller andre ejendomsrettigheder uden udtrykkelig tilladelse fra ejeren af ophavsretten, varem�rket eller andre ejendomsrettigheder. Mobile Can Save har ikke nogen udtrykkelig byrde eller ansvar for at levere Slutbrugerkonti med angivelser, m�rker eller noget andet, som kan st�tte End Users afg�relse, om det p�g�ldende materiale er beskyttet af copyright eller varem�rkebeskyttet. End User er eneansvarlig for eventuelle skader som f�lge af enhver overtr�delse af ophavsrettigheder, varem�rker, immaterielle rettigheder eller andre skader, som skyldes en s�dan freml�ggelse. Ved at indsende materiale til et offentligt omr�de af denne hjemmeside eller nogen Microsite, End User garanterer, at ejeren af et s�dant materiale udtrykkeligt har givet Mobile Can Save den royalty-fri, vedvarende, uigenkaldelig, ikke-eksklusiv ret og licens til at bruge, reproducere, modificere, tilpasse , offentligg�re, overs�tte og distribuere s�danne materialer (helt eller delvist) p� verdensplan og / eller indf�je det i andre v�rker i enhver form, medie eller teknologi nu kendt eller herefter udvikles for hele l�betiden af ophavsretten, kan findes i dette materiale . End User ogs� tillader alle andre slutbrugere adgang til, se, gemme eller gengive materialet for at End User's personlig brug. End User giver hermed Mobile Can Save ret til at redigere, kopiere, offentligg�re og distribuere ethvert materiale der stilles til r�dighed p� denne side eller Microsite af End User.
	// 
	//Ovenst�ende bestemmelser i afsnit 5 g�lder b�de for, og er til fordel for Mobile Can Save, skal dets datterselskaber, s�sterselskaber, Indhaver og dets tredjeparts indholdsudbydere og licensgivere og har hver is�r ret til at h�vde og h�ndh�ve s�danne bestemmelser, der direkte eller p� egne vegne. 
	//
	//6. Copyright Policy
	//Mobile Can Save forbeholder sig ret til at opsige sin aftale med en Slutbruger, der gentagne gange overtr�der tredjemands ophavsrettigheder ved omg�ende meddelelse til Mobile Can Save af ejeren af ophavsretten eller indehaveren af ophavsrettens juridiske agent. Uden at begr�nse ovenst�ende, hvis du mener, at et ophavsretligt beskyttet v�rk er blevet kopieret og sendt via hjemmesiden eller nogen Microsite p� en m�de der anf�gter copyright, skal du kontakte Mobile Can Save med f�lgende oplysninger: (a) en elektronisk eller fysisk underskift fra den person er bemyndiget til at handle p� vegne af ejeren af det ophavsretligt beskyttede (b) en identifikation og placering p� hjemmesiden eller nogen Microsite af den copyright som du mener er blevet overtr�dt (c) en skriftlig erkl�ring fra dig, at du i god tro, mener at den omstridte brug ikke er autoriseret af ejeren, dennes agent eller loven; (d) dit navn og kontaktoplysninger, s�som telefon nummer eller e-mail-adresse, og (e) en erkl�ring fra Dem om, at ovenst�ende oplysninger i din meddelelse er korrekte og under straf for mened, at du er indehaver af ophavsretten eller tilladelse til at handle p� ophavsrethaveren vegne. Kontaktoplysninger p� Mobile Can Saves copyright-agent for meddelelse af kr�nkelser af ophavsrettigheder, er som f�lger: 
	//Mobile Can Save aps
	//Att: Copyright Agent 
	//Gydevang 39-41
	//3450 Alleroed 
	//support@mobilecansave.com
	//7. Fral�ggelse af Garanti. 
	//SLUTBRUGER accepterer udtrykkeligt,  at brugen af dette websted, og microsites ER P� slutbrugers EGEN RISIKO. Hverken Mobile Can Save, dets partnere ELLER nogen af deres respektive medarbejdere, agenter, grossister, tredjepartsindhold udbydere eller LICENSGIVERE, eller nogen af deres bestyrelsesmedlemmer, direkt�rer, ansatte eller agenter, GARANTERER at brugen af hjemmeside eller andre microsite uden afbrydelser eller fejlfri; de giver heller ikke  nogen form for garanti for (I) resultaterne, som KAN F�S FRA BRUG AF DENNE SITE, ELLER NOGEN microsite, eller (II) n�jagtigheden, p�lideligheden eller indholdet af information og service eller v�rdikuponer LEVERET via denne hjemmeside ELLER Microsites.
	// 
	//DENNE  SITE OG Microsites g�res tilg�ngelige p� en "SOM DEN ER" OG "TIL R�DIGHEDS" BASIS. Mobile Can Save fraskriver sig herved alt ansvar, garantier og vilk�r, hvad enten UDTRYKKELIGE ELLER STILTIENDE, HERUNDER, MEN IKKE BEGR�NSET TIL, bestemmelserne i afsnit-kr�nkelse, SALGBARHED OG EGNETHED TIL ET BESTEMT FORM�L.
	//
	//8. Begr�nsning af ansvar. 
	//UNDER INGEN OMST�NDIGHEDER KAN Mobile Can Save, V�RE ANSVARLIG FOR NOGEN INDIREKTE, tilf�ldig, speciel, f�lgeskader eller diciplin�r straf, der udspringer af, eller noget  i forbindelse med denne aftale. UNDER INGEN OMST�NDIGHEDER VIL Mobile Can Saves ansvar i forbindelse med Mobile Can Save, overstige det bel�b betalt for en s�dan Mobile Can Save, OG TOTAL  SAMLET erstatningsansvar, som f�lge af eller relateret til denne aftale, ikke overstige deT bel�b betalt AF END USER, GENNEM DE SENESTE seks m�neder forud for indgivelsen af ethvert krav. 
	//
	//9. Overv�gning. 
	//Mobile Can Save har ret til, men ikke forpligtet til, at overv�ge indholdet af hjemmesiden og alle Microsites p� alle tidspunkter, herunder eventuelle chatrum og fora, der kan herefter indg� som en del af sitet, for at fastsl� overholdelse af denne aftale og eventuelle operationelle regler indf�rt ved Mobile Can Save, samt at overholde g�ldende lovgivning eller regulering eller autoriseret regeringsanmodning. Uden nogen begr�nsning af ovenst�ende, skal Mobile Can Save have retten, men ikke forpligtet til, at fjerne alt materiale, Mobile Can Save, efter eget sk�n, at fjerne alt materiale, som vil v�re i strid med aftalens bestemmelser eller p� anden m�de st�dende.
	// 
	//10. Beskyttelse af personlige oplysninger. 
	//End User erkender, at alle dr�ftelser for ratings, kommentarer, opslagstavle service, chat rooms og / eller andre besked eller kommunikationsfaciliteter (samlet "F�llesskaberne") er offentlige og ikke privat kommunikation, og derfor kan andre l�se End User's kommunikation, uden End User's viden. Mobile Can Save hverken godkender eller giver sit sammentykke til indhold, beskeder eller informationer der kan findes i ethvert samfund, og derfor frasiger Mobile CanSave sig specifikt ethvert ansvar vedr�rende F�llesskaberne og af eventuelle sager, der udspringer fra Slutbrugere deltagelse i ethvert f�llesskab, herunder eventuelle anst�deligt indhold. Generelt enhver meddelelse, som End User sender til Mobile Can Save (om det er i chatrooms, i diskussionsgrupper, opslagstavler eller noget andet), anses det for at v�re ikke-fortroligt. Hvis en bestemt websider tillader indsendelse af meddelelser, som vil blive behandlet af Mobile Can Save, som fortroligt, skal dette v�re angivet p� disse sider. Ved at sende kommentarer, beskeder eller andre oplysninger p� webstedet eller nogen Microsite, giver End User tilladelse til at Mobile Can Save kan anvende s�danne kommentarer, beskeder eller information til reklame, reklamer, markedsunders�gelser eller andre tilladte form�l uden geografiske, tids-eller andre begr�nsninger. For mere information, se Mobile Can Save's Privacy Policy
	//. 
	//11. Licens tilladelse. 
	//Ved udstationering kommunikation p� eller gennem denne hjemmeside eller nogen Microsite, anses End User for at have ydet til Mobile Can Save royalty-frit, vedvarende, uigenkaldelig, ikke-eksklusiv licens til at bruge, reproducere, modificere, publicere, redigere, overs�tte, distribuere, udf�re, og vise meddelelsen alene eller som del af andre v�rker i enhver form, medie eller teknologi, om nu kendt eller herefter udvikles uden territorial eller tidsbegr�nsninger, og at underlicensere disse rettigheder gennem flere lag af underentrepren�rer. 
	//12. Erstatning / Release. 
	//End User indvilliger i at forsvare, godtg�re og holde Mobile Can Save skadesl�s ogs� dets datterselskaber og deres respektive direkt�rer, ledere, medarbejdere og agenter fra og mod alle krav og udgifter, herunder advokatsal�rer, der udspringer af eller i forbindelse med produkter eller tjenesteydelser indk�bt af End User Bruger i forbindelse med webstedet eller nogen Microsites. 
	//
	//End User er alene ansvarlig for interaktion med Indhaver og andre brugere af webstedet eller Microsites. I det omfang det er tilladt i henhold til g�ldende lovgivning, End User hermed udgivelser Oonusave fra alle krav eller ansvar i forbindelse med et produkt eller en tjenesteydelse af en Handlende, enhver handling eller passivitet fra Indhaver, herunder Indhaver's manglende overholdelse af g�ldende lovgivning og / eller manglende overholdelse af vilk�rene i en voucher, og enhver adf�rd eller tale, enten online eller offline, for andre brugere. 
	//
	//
	//13. Opsigelse. 
	//Mobile Can Save kan opsige denne aftale p� ethvert tidspunkt. Uden at begr�nse ovenst�ende har Mobile Can Save ret til �jeblikkeligt at oph�ve ethvert password eller regnskab End User i tilf�lde af adf�rd fra End User som Mobile Can Save, efter eget sk�n, anser for at v�re uacceptabel, eller i tilf�lde af misligholdelse af denne aftale af End User. Bestemmelserne i afsnit 2, 4, 5, 6, 7, 10, 11 og 12 vil fortsat v�re g�ldende efter opsigelsen af denne aftale. 
	//
	//14. Varem�rker. 
	//Oonusave er et varem�rke tilh�rende Mobile Can Save aps. Alle rettigheder i forbindelse med dette varemaerke er herved udtrykkeligt forbeholdt. Medmindre andet er angivet, er alle andre varem�rker, som er anf�rt p� Oonusaves hjemmeside, tilh�rende deres respektive ejere. 
	//
	//15. Tredjeparts indhold. 
	//Oonusave, svarende til en Internet Service Provider, er en forhandler (og ikke en forel�gger), i indhold, der leveres af tredjeparter og slutbrugere. Derfor har Mobile Can Save aps ikke mere redaktionel kontrol over indholdet, end et ikke offentligt bibliotek, boghandel eller kiosk. Enhver mening, r�dgivning, udtalelse, tjenesteydelse, tilbud eller anden information, eller indhold udtrykt eller stillet til r�dighed af tredjemand, herunder informations leverand�rer, eller enhver anden end user af dem  respektive forfatter (e) eller distribut�r (r) og ikke af Mobile Can Save.
	// 
	//I alle tilf�lde, er det tilg�ngelige indhold via dette site, repr�senterer udtalelser og domme de respektive oplysninger udbyder, slutbruger, eller andre bruger ikke under kontrakt med Mobile Can Save. Mobile Can Save hverken st�tter eller er ansvarlig for n�jagtigheden eller p�lideligheden af nogen udtalelse, r�dgivning, eller udtalelser lavet p� Oonusave af andre end autoriserede Oonusave ansatte talsm�nd, som handler i officiel kapacitet. Under ingen omst�ndigheder vil Mobile Can Save v�re ansvarlig for tab eller skade som f�lge af en slutbruger afh�ngighed af oplysninger indhentet gennem Oonusave. Det p�hviler End User at vurdere n�jagtigheden, fuldst�ndigheden eller nyttev�rdien af enhver information, r�dgivning mv, eller andet indhold tilg�ngeligt via Oonusave.
	//
	//Oonusave indeholder links til tredjeparts websteder, som vedligeholdes af andre indholdsleverand�rer. Disse links er udelukkende en service til dig og ikke godkendelse af Mobile Can Save af indholdet p� s�danne tredjeparts websteder, og Oonusave fraskriver sig herved udtrykkeligt enhver henvendelse vedr�rende indholdet eller n�jagtigheden af materialer p� s�danne tredjeparts websteder. Hvis End User beslutter sig for at g� i p� et tilknyttet tredjeparts websteder, er det p� egen risiko for End User. Medmindre du har modtaget en skriftlig aftale fra Mobile Can Save, som udtrykkeligt giver dig mulighed for at g�re det, m� du ikke give et hyperlink til webstedet eller nogen Microsite fra enhver anden hjemmeside. Mobile Can Save forbeholder sig ret til at tilbagekalde sit samtykke til enhver forbindelse til enhver tid efter eget sk�n.




}


class LocationFinder implements LocationListener {

	
	@Override
	public void onLocationChanged(Location location) {
		try {
			if (location == null) {
				return;
			}
			double curr_lat = formatFraction(location.getLatitude());
			double curr_longi = formatFraction(location.getLongitude());

			//			//Log.i(Constants.TAG, " ************ LOCATION INFO ************************ ");
			//			//Log.i(Constants.TAG, " Lati --- >  " + curr_lat + "::: Longi ---- > " + curr_longi);
			//			//Log.i(Constants.TAG, " ************ LOCATION INFO ************************ ");

			//Toast.makeText(, "Location Changed --  Lati -> " + curr_lat + ": Longi - > " + curr_longi, Toast.LENGTH_SHORT).show();
			DataUtil.locationInfo.setLatitude(curr_lat+"");
			DataUtil.locationInfo.setLongitude(curr_longi+"");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}


	public double formatFraction(double d) {
		try {
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setGroupingUsed(false); // don't group by threes
			nf.setMinimumFractionDigits(5);
			nf.setMaximumFractionDigits(5);
			String sd = nf.format(d);
			return Double.valueOf(sd.trim()).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
}


