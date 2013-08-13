package com.adi.justforyou;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WhatCanIDoActivity extends Activity implements OnClickListener {

	private static final String ADI_MOBILE_NUMBER = "9538114623";
	private static final int EVENING = 57600000;
	private static final int AFTER_NOON = 43200000;
	private TextView mGreetingTextView;
	private Button mSubmitBtn;
	private EditText mContentText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.what_can_i_do_layout);
		initViews();
	}

	private void initViews() {
		mGreetingTextView = (TextView) findViewById(R.id.greeting_text);
		mContentText = (EditText) findViewById(R.id.content_text);
		mSubmitBtn = (Button) findViewById(R.id.submit_btn);
		mSubmitBtn.setOnClickListener(this);
		setGreetingText();
	}

	private void setGreetingText() {
		Date currentDate = new Date(System.currentTimeMillis());
		long d = new Date().getTime();
		int offset = TimeZone.getDefault().getOffset(d);
		d = ((d + offset)/ 86400000l) * 86400000l - offset;
		if (currentDate.before(new Date(d+AFTER_NOON))) {
			mGreetingTextView.setText(R.string.morning_msg);
		} else if (currentDate.after(new Date(d+AFTER_NOON))
				&& currentDate.before(new Date(d+EVENING))) {
			mGreetingTextView.setText(R.string.noon_msg);
		} else if (currentDate.after(new Date(d+EVENING))) {
			mGreetingTextView.setText(R.string.evening_msg);
		}
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.submit_btn:
			if (TextUtils.isEmpty(mContentText.getText().toString())) {
				Toast.makeText(getApplicationContext(), "Please Enter ua wish",
						Toast.LENGTH_SHORT).show();
			} else {
				sendMsg();
				finish();
			}
			break;

		default:
			break;
		}

	}

	private void sendMsg() {
		SmsManager sm = SmsManager.getDefault();
		String number = ADI_MOBILE_NUMBER;
		String msg = mContentText.getText().toString();
		sm.sendTextMessage(number, null, msg, null, null);
	}

}
