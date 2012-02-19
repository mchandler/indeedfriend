package com.rmwebfx.indeedfriend;

import com.rmwebfx.indeedfriend.model.SearchLocation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class SetLocation extends MenuActivity implements OnClickListener {
    
	private SearchLocation location;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setlocation);
        
        location = (SearchLocation) new SearchLocation(this).getLatestRecord();
    	
        View saveButton = findViewById(R.id.locationButton); 
        saveButton.setOnClickListener(this);
        
        initializeForm();
    }

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.locationButton:
			persistLocation();
			break;
		}
	}
	
	protected void initializeForm() {
		// set values from the database, or set to empty strings if nothing is there
		String city = (location.getCity() == null) ? "" : location.getCity().trim();
		String state = (location.getState() == null) ? "" : location.getState().trim();
		String postal = (location.getPostal() == null) ? "" : location.getPostal().trim();
		
		// set values on the form
		EditText cityField = (EditText) findViewById(R.id.cityField);
		cityField.setText(city);
		
		EditText stateField = (EditText) findViewById(R.id.stateField);
		stateField.setText(state);
		
		EditText postalField = (EditText) findViewById(R.id.postalField);
		postalField.setText(postal);
	}
	
	protected void persistLocation() {
		EditText cityField = (EditText) findViewById(R.id.cityField);
		EditText stateField = (EditText) findViewById(R.id.stateField);
		EditText postalField = (EditText) findViewById(R.id.postalField);
		
		if (validate()) {
			location.setCity(cityField.getText().toString());
			location.setState(stateField.getText().toString());
			location.setPostal(postalField.getText().toString());
			location.save();
		}
		
	}
	
	protected boolean validate() {
		return true; // TODO: to implement later!
	}
    
}