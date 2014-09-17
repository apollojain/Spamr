package info.android.sendsmstest;

import info.android.sendsmstest.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class CallActivity extends Activity {

	final Context context = this;
	private ImageButton btn;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_call);

		btn = (ImageButton) findViewById(R.id.call);

		PhoneCallListener phoneCallListener = new PhoneCallListener();
		TelephonyManager telManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		telManager.listen(phoneCallListener, PhoneStateListener.LISTEN_CALL_STATE);

		// add button listener
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
		        

				Intent phoneCallIntent = new Intent(Intent.ACTION_CALL);
				phoneCallIntent.setData(Uri.parse("tel:" + getRandomNumber()));
				startActivity(phoneCallIntent);

			}

			private String getRandomNumber() {
				// TODO Auto-generated method stub
				int h = getRandomInt(2, 9);
				int i = getRandomInt(10, 70);
				int j = getRandomInt(100, 900);
				int k = getRandomInt(1000, 9000);
				String fin = String.valueOf(h) + String.valueOf(i) + String.valueOf(j) + String.valueOf(k);
				return fin;
			}
			
			// Returns a random integer between min and max
			// Using Math.round() will give you a non-uniform distribution!
			private int getRandomInt(int min, int max) {
			  return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
			}

		});

	}

	// monitor phone call states
	private class PhoneCallListener extends PhoneStateListener {

		String TAG = "LOGGING PHONE CALL";

		private boolean phoneCalling = false;

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {

			if (TelephonyManager.CALL_STATE_RINGING == state) {
				// phone ringing
				Log.i(TAG, "RINGING, number: " + incomingNumber);
			}

			if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
				// active
				Log.i(TAG, "OFFHOOK");

				phoneCalling = true;
			}

			// When the call ends launch the main activity again
			if (TelephonyManager.CALL_STATE_IDLE == state) {

				Log.i(TAG, "IDLE");

				if (phoneCalling) {

					Log.i(TAG, "restart app");

					// restart app
					Intent i = getBaseContext().getPackageManager()
							.getLaunchIntentForPackage(
									getBaseContext().getPackageName());

					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);

					phoneCalling = false;
				}

			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
			case R.id.action_home:
	        	Intent intenthome = new Intent(this, MainActivity.class);
	    		startActivity(intenthome);
	            return true;
	        case R.id.action_sms:
	        	Intent intent = new Intent(this, SMSActivity.class);
	    		startActivity(intent);
	            return true;
	        case R.id.action_call:
	        	Intent intent2 = new Intent(this, CallActivity.class);
	    		startActivity(intent2);
	            return true;
	        default:
	        	return true;
		}
	}

}