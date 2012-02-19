package com.rmwebfx.indeedfriend;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class MenuAndAdActivity extends MenuActivity {
	
	private AdView adView;
	
	public void showAd(int viewId) {
		adView = (AdView) findViewById(viewId);
		
		AdRequest adRequest = new AdRequest();
		adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
		adView.loadAd(adRequest);
	}
	
	@Override
	public void onDestroy() {
		adView.destroy();
		super.onDestroy();
	}
}