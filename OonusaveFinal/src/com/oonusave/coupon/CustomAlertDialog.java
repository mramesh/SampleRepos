package com.oonusave.coupon;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.oonusave.coupon.util.TitleTextUtils;

public class CustomAlertDialog {

	public static void showCustomDialog(Context context) {
		Dialog dialog = null;
		
		AlertDialog.Builder alertBuilder = new
		AlertDialog.Builder(context);
		alertBuilder.setTitle("You are in the demo account. kindly register your self to use this feature.")
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		})
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		dialog = alertBuilder.create();
		dialog.show();
		
		/*AlertDialog.Builder alertBuilder = new
		AlertDialog.Builder(context);
		alertBuilder.setTitle("You are in the demo account. kindly register your self to use this feature.")
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		})
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}

		});
		alertBuilder.create();
		alertBuilder.show();
		*/

	}

	void openRegistrationPage(){
		System.out.println(" ---- openRegistrationPage() ---- ");
	}

}
