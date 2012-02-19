package com.rmwebfx.indeedfriend;

import android.os.Bundle;
import android.webkit.WebView;

public class JobDetails extends MenuActivity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobdetails);
        
        Bundle extras = getIntent().getExtras();
        String indeedUrl = extras.getString("indeedUrl");
        
        WebView jobWindow = (WebView) findViewById(R.id.indeedView);
        
        //showAd(R.id.jobDetailsAd);
        jobWindow.loadUrl(indeedUrl);
    }    
}