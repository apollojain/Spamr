package info.android.sendsmstest;

import info.android.sendsmstest.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SMSActivity extends Activity {

	private EditText phoneNumber;
	private EditText smsBody;
	private EditText numTimes;
	private Button smsManagerBtn;
	private Button buttonReadContact;
	
	 final int RQS_PICKCONTACT = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);

		phoneNumber = (EditText) findViewById(R.id.phoneNumber);
		smsBody = (EditText) findViewById(R.id.smsBody);
		numTimes = (EditText) findViewById(R.id.numTimes);
		smsManagerBtn = (Button) findViewById(R.id.smsManager);
		smsManagerBtn.setOnClickListener(new OnClickListener() {
			 public void onClick(View view) {
				 sendSmsByManager();
			 }
		});
		/*buttonReadContact = (Button)findViewById(R.id.readcontact);
		  
		  buttonReadContact.setOnClickListener(new OnClickListener(){

		   @Override
		   public void onClick(View arg0) {
		    //Start activity to get contact
		    final Uri uriContact = ContactsContract.Contacts.CONTENT_URI;
		    Intent intentPickContact = new Intent(Intent.ACTION_PICK, uriContact);
		    startActivityForResult(intentPickContact, RQS_PICKCONTACT);
		   }});*/
		
	}
	
	/* @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  // TODO Auto-generated method stub
	  if(resultCode == RESULT_OK){
	   if(requestCode == RQS_PICKCONTACT){
	    Uri returnUri = data.getData();
	    Cursor cursor = getContentResolver().query(returnUri, null, null, null, null);
	    
	    if(cursor.moveToNext()){
	     int columnIndex_ID = cursor.getColumnIndex(ContactsContract.Contacts._ID);
	     String contactID = cursor.getString(columnIndex_ID);
	     
	     int columnIndex_HASPHONENUMBER = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
	     String stringHasPhoneNumber = cursor.getString(columnIndex_HASPHONENUMBER);
	     
	     if(stringHasPhoneNumber.equalsIgnoreCase("1")){
	      Cursor cursorNum = getContentResolver().query(
	        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
	        null, 
	        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactID, 
	        null, 
	        null);
	      
	      //Get the first phone number
	      if(cursorNum.moveToNext()){
	       int columnIndex_number = cursorNum.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
	       String stringNumber = cursorNum.getString(columnIndex_number);
	       phoneNumber.setText(stringNumber);
	      }
	      
	     }else{
	    	 phoneNumber.setText("NO Phone Number");
	     }
	     
	     
	    }else{
	     Toast.makeText(getApplicationContext(), "NO data!", Toast.LENGTH_LONG).show();
	    }
	   }
	  }
	 }*/

	
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
	public void sendSmsByManager() {
		try {

			int j = Integer.parseInt(numTimes.getText().toString());
			// Get the default instance of the SmsManager
			SmsManager smsManager = SmsManager.getDefault();
			/*while(j > 0)
			{*/
				smsManager.sendTextMessage(phoneNumber.getText().toString(), 
						null,  
						smsBody.getText().toString(), 
						null, 
						null);
				/*j--;
			}*/
			Toast.makeText(getApplicationContext(), "Your sms has successfully sent!",
					Toast.LENGTH_LONG).show();
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(),"Your sms has failed...",
					Toast.LENGTH_LONG).show();
			ex.printStackTrace();
		}
	}
	
}
