package com.rmwebfx.indeedfriend.helpers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rmwebfx.indeedfriend.model.Job;

public class JobsArrayBuilder {
	
	private List<Job> jobs;
	
	public JobsArrayBuilder() {
		this.jobs = new ArrayList<Job>();
	}
	
	public List<Job> parse(JSONArray results) throws JSONException {
		for (int i = 0; i < results.length(); i++) {
			JSONObject result = results.getJSONObject(i);
			Job job = new Job();
			
			job.setJobTitle(result.getString("jobtitle"));
			job.setCompany(result.getString("company"));
			job.setSource(result.getString("source"));
			job.setCity(result.getString("city"));
			job.setState(result.getString("state"));
			job.setCountry(result.getString("country"));
			job.setSnippet(result.getString("snippet"));
			job.setUrl(result.getString("url"));
			job.setFormattedRelativeTime(result.getString("formattedRelativeTime"));
			
			jobs.add(job);
		}
		
		return jobs;
	}
}