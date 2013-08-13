package com.adi.justforyou;

import java.util.Date;

import com.adi.justforyou.utils.EventsDBHelper;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EventsActivity extends Activity implements OnClickListener {

	private static final String ADI_MOBILE_NUMBER = "9538114623";
	private static final int EVENING = 57600000;
	private static final int AFTER_NOON = 43200000;
	private Button mSubmitBtn;
	private EditText mTitleText;
	private EditText mMsgText;
	private EditText mMobileText;
	private EditText mDateText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events_layout);
		initViews();
	}

	private void initViews() {
		mTitleText = (EditText) findViewById(R.id.title_text);
		mMsgText = (EditText) findViewById(R.id.msg_text);
		mMobileText = (EditText) findViewById(R.id.mobile_text);
		mDateText = (EditText) findViewById(R.id.date_text);
		mSubmitBtn = (Button) findViewById(R.id.submit_btn);
		mSubmitBtn.setOnClickListener(this);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.submit_btn:
			extractData();
			break;
		default:
			break;
		}

	}

	private void extractData() {
		String title, date, mobile, msg;
		title = mTitleText.getText().toString();
		date = mDateText.getText().toString();
		mobile = mMobileText.getText().toString();
		msg = mMsgText.getText().toString();
		if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(date)
				&& !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(msg)) {
			addEvents(title, date, mobile, msg);
			finish();
		} else {
			Toast.makeText(getApplicationContext(), R.string.sorry_msg_no_blank,
					Toast.LENGTH_SHORT).show();
			
		}
	}

	/**
	 * 
	 */
	private void addEvents(String title, String date, String mobile, String msg) {
		long rowinserted;
		rowinserted = EventsDBHelper.getInstance(getApplicationContext())
				.addEvent(title, mobile, "", "", msg, date, "");
		if (rowinserted > 0) {
			Toast.makeText(getApplicationContext(), "Event Added",
					Toast.LENGTH_SHORT).show();
		}
	}
}
