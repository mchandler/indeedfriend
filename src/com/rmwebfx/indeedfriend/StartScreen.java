package com.rmwebfx.indeedfriend;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rmwebfx.common.json.IServerRequestor;
import com.rmwebfx.common.json.ServerCaller;
import com.rmwebfx.indeedfriend.helpers.IndeedStringFormatter;
import com.rmwebfx.indeedfriend.helpers.JobListViewAdapter;
import com.rmwebfx.indeedfriend.helpers.JobsArrayBuilder;
import com.rmwebfx.indeedfriend.model.Job;
import com.rmwebfx.indeedfriend.model.SearchLocation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class StartScreen extends MenuAndAdActivity implements IServerRequestor, OnItemClickListener {
	private ArrayList<Job> jobsArray;
	private int totalJobs;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobsviewer);
        showAd(R.id.jobsViewerAd);
        
        // NOTE: processing continues via onResume()
    }
    
    @Override
	public void onResume() {
    	super.onResume();
    	initializeLocation();
    }
	
	public void initializeLocation() {
    	SearchLocation location = (SearchLocation) new SearchLocation(this).getLatestRecord();
    	
    	if (location == null || location.getCity() == null || location.getState() == null
    			|| location.getPostal() == null) {
    		// no location set, redirect to Set Location activity
    		Intent intent = new Intent().setClass(this, SetLocation.class);
    		startActivity(intent);
    	} else {
    		// a location is set, so let's show some jobs!
    		getJobs();
    	}
    }
    
    public synchronized void getJobs() {
        
        // TODO: On the layout, let's display a "processing" image here
        SearchLocation location = (SearchLocation) new SearchLocation(this).getLatestRecord();
        	
        URL feedURL = null;
		try {
			feedURL = new URL(IndeedStringFormatter.doFormat(location));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ServerCaller caller = new ServerCaller(this, feedURL);
		Thread jsonFinder = new Thread(caller);
		jsonFinder.start(); // spawn a thread that calls the feed.
		
		try {
			this.wait(); // wait for the new thread to wake us up
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		showAllJobs();
    }

	public synchronized void handleServerResponse(JSONObject json) throws JSONException {
		JSONArray resultsArray = json.getJSONArray("results");
		JobsArrayBuilder builder = new JobsArrayBuilder();
		jobsArray = (ArrayList<Job>) builder.parse(resultsArray);
		totalJobs = Integer.parseInt(json.getString("totalResults"));
		
		this.notify(); // wake up the UI thread
	}
	
	public void showAllJobs() {
		JobListViewAdapter adapter = new JobListViewAdapter(this, R.layout.listviewjobrow, jobsArray);
		ListView listview = (ListView) findViewById(R.id.jobsView);
		
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		Job job = jobsArray.get(position);
		
		Intent intent = new Intent().setClass(this, JobDetails.class);
		intent.putExtra("indeedUrl", job.getUrl());
		startActivity(intent);
	}
}