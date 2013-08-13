package com.adi.justforyou.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class EventsDBHelper extends SQLiteOpenHelper {

	private static String DATABASE_NAME = "JUST_FOR_YOU_Database";
	private static int DATABASE_VERSION = 5;

	public static int COL_TITLE = 0;
	public static int COL_LINK = 1;
	public static int COL_WEBLINK = 2;
	public static int COL_STORYIMAGEURL = 3;
	public static int COL_DESC = 4;
	public static int COL_DATE = 5;
	public static int COL_GUID = 6;

	public static int WEATHER_COL_CITY = 0;
	public static int WEATHER_COL_ISINDIAN = 1;

	private static String EVENTS_TABLE_NAME = "FavouritiesTable";
	private static String EVENT_TITLE = "title";
	private static String EVENT_MOBILE_NO = "link";
	private static String EVENT_TYPE = "weblink";
	private static String EVENT_TIME = "storyimageurl";
	private static String EVENT_MSG = "desc";
	private static String EVENT_DATE = "date";
	private static String EVENT_TASK = "guid";

	private static EventsDBHelper mSelfInstance = null;

	private static final String CREATE_EVENTS_TABLE = "CREATE TABLE " + EVENTS_TABLE_NAME + " ( "
			+ EVENT_TITLE + " , " + EVENT_MOBILE_NO + " , " + EVENT_TYPE + " , " + EVENT_TIME + " , "
			+ EVENT_MSG + " , " + EVENT_DATE + " , " + EVENT_TASK + " );";


	public EventsDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public synchronized static EventsDBHelper getInstance(Context context) {
		if (mSelfInstance == null)
			mSelfInstance = new EventsDBHelper(context);
		Log.d("DB", " getInstance(Context context)");
		return mSelfInstance;
	}

	public synchronized static EventsDBHelper getInstance() {
		/*
		 * if(mSelfInstance != null) return mSelfInstance; return null;
		 */
		return mSelfInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("DB", " onCreate");
		db.execSQL(CREATE_EVENTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("DB", " onUpgrade");
		db.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE_NAME);
		onCreate(db);
	}

	public long addEvent(String title, String mobile, String type, String time, String msg, String date, String task) {
		String sql = "SELECT * FROM " + EVENTS_TABLE_NAME + " WHERE " + EVENT_TITLE + " = ?";
		Cursor queryresult = getWritableDatabase().rawQuery(sql, new String[] { title });
		if (queryresult.moveToFirst()) {
			queryresult.close();
		} else {
			queryresult.close();
			ContentValues newsItem = new ContentValues();
			newsItem.put(EVENT_TITLE, title);
			newsItem.put(EVENT_MOBILE_NO, mobile);
			newsItem.put(EVENT_TYPE, type);
			newsItem.put(EVENT_TIME, time);
			newsItem.put(EVENT_MSG, msg);
			newsItem.put(EVENT_DATE, date);
			newsItem.put(EVENT_TASK, task);
			return getWritableDatabase().insert(EVENTS_TABLE_NAME, null, newsItem);
		}
		return 0;
	}

	

	public int removeEvent(String title) {
		// String s[] = new String[]{title};
		// s[0] = title;
		return getWritableDatabase().delete(EVENTS_TABLE_NAME, EVENT_TITLE + " = ?", new String[] { title });
	}

	public Cursor getEvents() {

		Cursor newsItemsCursor;
		newsItemsCursor = getReadableDatabase().query(EVENTS_TABLE_NAME, null, null, null, null, null, null);

		if (newsItemsCursor == null) {
			return null;
		} else if (!newsItemsCursor.moveToFirst()) {
			newsItemsCursor.close();
			return null;
		}
		return newsItemsCursor;
	}


	public boolean isInEvents(String date) {
		String s[] = new String[1];
		s[0] = date;
		String sql = "SELECT * FROM " + EVENTS_TABLE_NAME + " WHERE " + EVENT_DATE + " = ?";
		Cursor newsItem = getWritableDatabase().rawQuery(sql, s);
		if (newsItem == null) {
			return false;
		} else if (!newsItem.moveToFirst()) {
			newsItem.close();
			return false;
		}
		newsItem.close();
		return true;
	}

}

