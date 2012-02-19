package com.rmwebfx.indeedfriend.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class Database extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "railsjobs";
	public static final int DATABASE_VERSION = 1;
	public static final String LOCATION_TABLE = "location";
	
	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createLocationTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	public void createLocationTable(SQLiteDatabase db) {
		StringBuilder builder = new StringBuilder();
		builder.append("CREATE TABLE " + LOCATION_TABLE + " ");
		builder.append("(" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
		builder.append("city VARCHAR(50), ");
		builder.append("state VARCHAR(50), ");
		builder.append("postal VARCHAR(50), ");
		builder.append("updatedOn TIMESTAMP);");
		db.execSQL(builder.toString());
	}
}