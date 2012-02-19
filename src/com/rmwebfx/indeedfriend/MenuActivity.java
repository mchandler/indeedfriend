package com.rmwebfx.indeedfriend;

import com.rmwebfx.common.dialog.DialogHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuActivity extends Activity {
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.app_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.menu_location:
	    	Intent location = new Intent().setClass(this, SetLocation.class);
    		startActivity(location);
	        return true;
	    case R.id.menu_jobs:
	    	Intent jobs = new Intent().setClass(this, StartScreen.class);
    		startActivity(jobs); 
	        return true;
	    case R.id.menu_about:
	        AlertDialog alert = DialogHelper.simpleDialog(this, "My about text here", "OK");
	        alert.show();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
}