package com.oonusave.coupon;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Store;
import com.oonusave.coupon.util.Constants;


/**
 * 
 * @author Ramesh
 *
 */

public class StoreAdaptor extends BaseAdapter{

	private Activity context;
	private List<Store> storeList;
	private boolean isMyWallet;
	private DrawableManager drawableManager = new DrawableManager();
	//DrawableMgr drawableManager = new DrawableMgr();
	private LayoutInflater inflater;
	//AsyncImageLoader asyncImageLoader  = new AsyncImageLoader();
	//AsyncLoader asyncImageLoader = new AsyncLoader();
	
	public ImageLoader imageLoader; 

	StoreAdaptor(Activity context,List<Store> storeList,boolean isMyWallet) {
		//Log.i(Constants.TAG,"# CouponAdapter # ------ constructor() ----- ");
		this.context = context;
		this.storeList = storeList;
		this.isMyWallet = isMyWallet;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//imageLoader=new ImageLoader(context);
	}

	public void add(Store store) {
		storeList.add(store);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return storeList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return storeList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	public void clear() {
		storeList.clear();
	}
	
	
	public static class ViewHolder{
        public TextView storeName;
        public ImageView image;
        public TextView kmsText;
        public String storeImage;
    }


	@Override
	public View getView(int position, View v, ViewGroup parent) {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG,"# CouponAdapter # ------ getView() ----- " + position);
		Store store = storeList.get(position);
		final ViewHolder holder;
		//if(v == null) {
			
			//return new CouponAdaptorView(this.context, coupon,drawableManager,isMyWallet );
			 holder=new ViewHolder();
			 
			v = inflater.inflate(R.layout.store_item, null);
			holder.storeImage = store.getImageUrl();
			holder.storeName=(TextView)v.findViewById(R.id.storeName);;
            holder.image=(ImageView)v.findViewById(R.id.storeImage);
            holder.kmsText = (TextView) v.findViewById(R.id.kmsText);
			v.setTag(holder);
		//}else 
			//holder=(ViewHolder)v.getTag();
		
		
		holder.image.setTag(holder.storeImage);
		
		
//		Bitmap cachedImage = asyncImageLoader.loadDrawable(holder.storeImage, new
//		ImageCallback() {
//		public void imageLoaded(Bitmap imageBitmap, String imageUrl) {
//		holder.image.setImageBitmap(imageBitmap);
//		notifyDataSetChanged();
//		}
//		});
		
//		if(cachedImage!=null)
//		holder.image.setImageBitmap(cachedImage);
		
		
		
//		Drawable cachedImage = asyncImageLoader.loadDrawable(holder.storeImage, new
//				AsyncImageLoader.ImageCallback() {
//				public void imageLoaded(Drawable imageBitmap, String imageUrl) {
//				holder.image.setBackgroundDrawable(imageBitmap);
//				notifyDataSetChanged();
//				}
//				});
//		
//		
//		if(cachedImage!=null)
//			holder.image.setBackgroundDrawable(cachedImage);
//		
		
		
		//ImageView storeImage = (ImageView) v.findViewById(R.id.storeImage);
		//TextView storeName = (TextView)v.findViewById(R.id.storeName);
		drawableManager.fetchDrawableOnThread(holder.storeImage, holder.image);
		
		//imageLoader.DisplayImage(store.getImageUrl(), context, storeImage);
				
		
		
		
		
		
		
		
		//Drawable image = ImageOperations(context,store.getImageUrl(),"image.jpg");
		//storeImage.setImageDrawable(image);

		//couponName.setText(coupon.getCouponName().length() > 18 ? coupon.getCouponName().substring(0,17) + "..." :coupon.getCouponName());
		//storeName.setText(store.getStoreName());
		
		if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			holder.storeName.setText(store.getStoreName().length() > 30 ? store.getStoreName().substring(0,29) + "..." : store.getStoreName());
		}else {
			holder.storeName.setText(store.getStoreName().length() > 15 ? store.getStoreName().substring(0,14) + "..." : store.getStoreName());
		}
		holder.kmsText.setText(store.getDistance());
		return v;
		//		}else {
		//			return v;
		//		}
		//return new StoreAdaptorView(this.context, store,drawableManager,isMyWallet );
	}
	

	private Drawable ImageOperations(Context ctx, String url, String saveFilename) {
		try {
			InputStream is = (InputStream) this.fetch(url);
			Drawable d = Drawable.createFromStream(is, "src");
			return d;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object fetch(String address) throws MalformedURLException,IOException {
		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}
}

class StoreAdaptorView extends LinearLayout {

	Context appContext;
	TextView addToWalletTxt;
	TextView showLocationTxt;
	TextView useCouponTxt; 
	Store store;
	boolean isMyWallet;
	DrawableManager drawableManager = null;

	public StoreAdaptorView(Context context,Store store,DrawableManager drawableManager,boolean isMyWallet) {
		super(context);
		this.appContext = context;
		this.store = store;
		this.drawableManager = drawableManager;
		this.isMyWallet = isMyWallet;

		//Log.i(Constants.TAG,"# CouponAdaptorView # ------ constructor() ----- ");
		// TODO Auto-generated constructor stub
		this.setOrientation(HORIZONTAL);
		this.setBackgroundResource(R.drawable.coupon_listing_bg);

		//android.view.ViewGroup.LayoutParams layoutParms = new android.view.ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		//setLayoutParams(layoutParms);

		LinearLayout.LayoutParams imageParams = 
			new LinearLayout.LayoutParams(50, 50);

		ImageView iconImage = new ImageView(context);
		iconImage.setLayoutParams(imageParams);
		iconImage.setBackgroundColor(Color.TRANSPARENT);
		//iconImage.setImageResource(R.drawable.couponicon);
		//		Drawable image = ImageOperations(context,store.getImageUrl(),"image.jpg");
		//		iconImage.setImageDrawable(image);
		//drawableManager.fetchDrawableOnThread(store.getImageUrl(), iconImage);
		iconImage.setPadding(3, 4, 3, 4);
		addView(iconImage,imageParams);




		LinearLayout.LayoutParams desParams = 
			new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//setBackgroundColor(Color.GREEN);

		desParams.setMargins(1, 1, 1, 1);


		TextView name = new TextView( context );
		name.setText( store.getStoreName());
		name.setTextSize(18f);
		name.setPadding(10, 5, 20, 0);
		name.setTextColor(Color.WHITE);
		addView(name,desParams);

		/*
		TextView categoryNames = new TextView( context );
		categoryNames.setText( store.getCategoryNames() );
		categoryNames.setTextSize(10f);
		shortDesc.setTextSize(15f);
		shortDesc.setPadding(5, 5, 20, 0);
		categoryNames.setTextColor(Color.WHITE);
		detailLayout.addView(categoryNames,desParams);

		 */

	}



	class ClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		}
	}

	AlertDialog alertDialog = null;
	void showAlert() {
		alertDialog = new AlertDialog.Builder(appContext).create();
		alertDialog.setTitle("Info");
		alertDialog.setMessage("Are you sure want to use this coupon?");
		alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//Log.i(Constants.TAG,"# CouponAdaptorView # ------ OK button clicked  ----- ");

			} }); 
	}




}
