package com.adi.justforyou;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.adi.justforyou.utils.Alarm;
import com.adi.justforyou.utils.EventsDBHelper;

public class MainActivity extends Activity implements OnClickListener {

	private Button mWhatCanIDoBtn;
	private Button mAddEventsBtn;
	Alarm alarm = new Alarm();
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		alarm.SetAlarm(this);
		initViews();
	}

	private void initViews() {
		mWhatCanIDoBtn = (Button) findViewById(R.id.button1);
		mAddEventsBtn = (Button) findViewById(R.id.button2);
		mAddEventsBtn.setOnClickListener(this);
		mWhatCanIDoBtn.setOnClickListener(this);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			startWhatCanIdoActivity();
			break;
		case R.id.button2:
			startAddEventsActivity();
			break;

		default:
			break;
		}

	}

	private void startAddEventsActivity() {
		Intent intent = new Intent(this, EventsActivity.class);
		startActivity(intent);
	}

	private void startWhatCanIdoActivity() {
		Intent intent = new Intent(this, WhatCanIDoActivity.class);
		startActivity(intent);
	}

	

	private void load() {
		EventsDBHelper dbhelper = EventsDBHelper
				.getInstance(getApplicationContext());
		Cursor favourites = dbhelper.getEvents();
		if (favourites != null && favourites.moveToFirst()) {
			do {
				// NewsItem item = new NewsItem();
				String mTitle = favourites.getString(EventsDBHelper.COL_TITLE)
						.replace('\"', '"');
				String mLink = favourites.getString(EventsDBHelper.COL_LINK);
				String mWebLink = favourites
						.getString(EventsDBHelper.COL_WEBLINK);
				String mStoryImageUrl = favourites
						.getString(EventsDBHelper.COL_STORYIMAGEURL);
				String mDescription = favourites
						.getString(EventsDBHelper.COL_DESC);
				@SuppressWarnings("deprecation")
				long mDBDate = Date.parse(favourites
						.getString(EventsDBHelper.COL_DATE));
				String mGuid = favourites.getString(EventsDBHelper.COL_GUID);
				// mNewsItems.add(item);
			} while (favourites.moveToNext());
			favourites.close();
		} else {

		}

	}

}
