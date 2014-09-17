package info.android.sendsmstest;

import info.android.sendsmstest.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageButton imageButton1;
	private ImageButton imageButton2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
			setContentView(R.layout.activity_main_horizontal);
        }else{
        	setContentView(R.layout.activity_main);
        }
		imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
		imageButton2 = (ImageButton) findViewById(R.id.imageButton1);
		
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
	public void smsView(View v){
		Intent intent = new Intent(this, SMSActivity.class);
		startActivity(intent);

		
	}

	public void callView(View v){
		Intent intent = new Intent(this, CallActivity.class);
		startActivity(intent);
	}
}
