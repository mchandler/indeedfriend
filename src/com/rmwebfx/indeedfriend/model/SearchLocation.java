package com.rmwebfx.indeedfriend.model;

import android.content.Context;

import com.rmwebfx.common.persistent.ActiveRecord;
import com.rmwebfx.common.persistent.IPersistent;

public class SearchLocation extends ActiveRecord implements IPersistent {
	private String city;
	private String state;
	private String postal;
	
	public SearchLocation() {
		
	}
	
	public SearchLocation(Context context) {
		super();
		dao = new SearchLocationDao(context); // wire in the data access object
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}
}