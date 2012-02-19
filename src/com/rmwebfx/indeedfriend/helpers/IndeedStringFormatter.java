package com.rmwebfx.indeedfriend.helpers;

import java.net.URLEncoder;

import android.util.Log;

import com.rmwebfx.indeedfriend.config.Constants;
import com.rmwebfx.indeedfriend.model.SearchLocation;

public class IndeedStringFormatter {
	
	public static String doFormat(SearchLocation location) {
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.indeedURL + "?v=2&publisher=" + Constants.publisherID);
		sb.append("&q=" + Constants.searchQuery);
		
		sb.append("&l=" + doLocationString(location));
		
		sb.append(doLimitString());
		sb.append("&format=json&sort=date");
		
		Log.d("Remote URL",sb.toString());
		
		return sb.toString();
	}
    
    public static String doLocationString(SearchLocation location) {
		StringBuilder sb = new StringBuilder();
		
		if (location.getCity() != null && location.getCity().length() > 0) {
			sb.append(location.getCity().toLowerCase().trim());
		}
		
		if (location.getState() != null && location.getState().length() > 0) {
			if (sb.toString().length() > 0) {
				sb.append(", ");
			}
			sb.append(location.getState().toLowerCase().trim());
		}
		
		if (location.getPostal() != null && location.getPostal().length() > 0) {
			if (sb.toString().length() > 0) {
				sb.append(" ");
			}
			sb.append(location.getPostal().trim());
		}
		
		return URLEncoder.encode(sb.toString());
	}
	
	public static String doLimitString() {
		return "&limit=" + Constants.jobCountLimit;
	}
}