package com.oonusave.coupon;


import com.oonusave.coupon.R;

import android.app.ListActivity;
import android.os.Bundle;

public class Checkbox extends ListActivity {
    /** Called when the activity is first created. */
	
	private CheckBoxifiedTextListAdapter cbla;
	// Create CheckBox List Adapter, cbla
	private String[] items = {"Box 1", "Box 2", "Box 3", "Box 4"};
	// Array of string we want to display in our list

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        
        cbla = new CheckBoxifiedTextListAdapter(this);
        for(int k=0; k<items.length; k++)
        {
        	cbla.addItem(new CheckBoxifiedText(items[k], false));
        }  
        // Display it
        setListAdapter(cbla);
    }
    
    /* Remember the other methods of the CheckBoxifiedTextListAdapter class!!!!
     * cbla.selectAll() :: Check all items in list
     * cbla.deselectAll() :: Uncheck all items
     */
}