package com.rmwebfx.indeedfriend.model;

import java.sql.Timestamp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.rmwebfx.common.persistent.IDao;
import com.rmwebfx.common.persistent.IPersistent;
import com.rmwebfx.indeedfriend.db.Database;

public class SearchLocationDao implements IDao {
	private Database db;
	private Context context;
	
	public SearchLocationDao(Context context) {
		this.db = new Database(context);
		this.context = context;
	}
	
	public void save(IPersistent location) {
		SearchLocation locationObj = (SearchLocation) location;
		
		SQLiteDatabase database = db.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("city", locationObj.getCity());
		values.put("state", locationObj.getState());
		values.put("postal", locationObj.getPostal());
		values.put("updatedOn", new Timestamp(System.currentTimeMillis()).toString());
		
		if (locationObj.getId() > 0) {
			String whereClause = BaseColumns._ID + " = " + new Integer(locationObj.getId()).toString();
			database.update(Database.LOCATION_TABLE, values, whereClause, null);
			database.close();
		} else {
			long newId = database.insertOrThrow(Database.LOCATION_TABLE, null, values);
			database.close();
			
			int id = (int) newId;
			location.setId(id);
		}
	}

	public IPersistent load(IPersistent location, int id) {
		SearchLocation locationObj = (SearchLocation) location;
		
		String[] sqlArgs = {new Integer(id).toString()};
		StringBuilder builder = new StringBuilder();
		builder.append("select " + BaseColumns._ID + ", city, state, postal, updatedOn from ");
		builder.append(Database.LOCATION_TABLE + " ");
		builder.append("where " + BaseColumns._ID + " = ?");
		
		SQLiteDatabase database = db.getReadableDatabase();
		Cursor cursor = database.rawQuery(builder.toString(), sqlArgs);
		
		location = singleRowHandler(cursor, locationObj);
		cursor.close();
		database.close();
		
		return location;
	}

	public IPersistent load(IPersistent location, String column) {
		// TODO Auto-generated method stub
		return location;
	}
	
	public void delete(IPersistent location) {
		// TODO: Implement a delete method
	}
	
	public SearchLocation singleRowHandler(Cursor cursor, SearchLocation locationObj) {
		if (cursor.moveToFirst()) {
			int objId = (int) cursor.getLong(0);
			locationObj.setId(objId);
			locationObj.setCity(cursor.getString(1));
			locationObj.setState(cursor.getString(2));
			locationObj.setPostal(cursor.getString(3));
		} else {
			locationObj.setId(0);
		}
		
		return locationObj;
	}

	public IPersistent getLatestRecord(IPersistent location) {
		SearchLocation locationObj = (SearchLocation) location;
		
		String[] sqlArgs = {"1"};
		StringBuilder builder = new StringBuilder();
		builder.append("select " + BaseColumns._ID + ", city, state, postal, updatedOn from ");
		builder.append(Database.LOCATION_TABLE + " ORDER BY " + BaseColumns._ID + " DESC ");
		builder.append("LIMIT ?");
		
		SQLiteDatabase database = db.getReadableDatabase();
		Cursor cursor = database.rawQuery(builder.toString(), sqlArgs);
		
		location = singleRowHandler(cursor, locationObj);
		cursor.close();
		database.close();
		
		return location;
	}
}