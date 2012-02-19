package com.rmwebfx.indeedfriend.helpers;

import java.util.ArrayList;

import com.rmwebfx.indeedfriend.R;
import com.rmwebfx.indeedfriend.model.Job;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class JobListViewAdapter extends BaseAdapter {

	private ArrayList<Job> jobArray;
	private LayoutInflater inflater;
	private int resourceId;

	public JobListViewAdapter(Context context, int resourceId, ArrayList<Job> jobArray) {
		super();
		this.jobArray = jobArray;
		inflater = LayoutInflater.from(context);
		this.resourceId = resourceId;
	}

	public int getCount() {
		return jobArray.size();
	}

	public Object getItem(int position) {
		return jobArray.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		
		View row = inflater.inflate(resourceId, parent, false);
		
		holder = new Holder();
		holder.jobTitle = (TextView) row.findViewById(R.id.jobTitle);
		holder.company = (TextView) row.findViewById(R.id.company);
		holder.whereAt = (TextView) row.findViewById(R.id.whereAt);
		holder.source = (TextView) row.findViewById(R.id.source);
		holder.postedOn = (TextView) row.findViewById(R.id.postedOn);
		
		Job job = jobArray.get(position);
		
		holder.jobTitle.setText(job.getJobTitle());
		holder.company.setText(job.getCompany());
		holder.source.setText(job.getSource());
		holder.whereAt.setText(job.getCity() + ", " + job.getState());
		holder.postedOn.setText(job.getFormattedRelativeTime());
		
		return row;
	}
	
	static class Holder {
		TextView jobTitle;
		TextView company;
		TextView whereAt;
		TextView source;
		TextView postedOn;
	}
}