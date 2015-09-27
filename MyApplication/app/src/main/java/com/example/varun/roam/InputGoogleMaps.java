package com.example.varun.roam;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class InputGoogleMaps extends Activity {

    EditText source = null;
    EditText dest = null;
    Button button = null;
    final SmsListener s = new SmsListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_google_maps);
        Intent intent = getIntent();
        IntentFilter filter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        registerReceiver(s, filter);

       // Typeface myTypeface = Typeface.createFromAsset(getAssets(), "xiomara.ttf");
       // TextView myTextView = (TextView)findViewById(R.id.directionsText);
       // myTextView.setTypeface(myTypeface);

        source = (EditText) findViewById(R.id.sourceTextField);
        dest = (EditText) findViewById(R.id.destinationTextField);
        button = (Button) findViewById(R.id.getDirectionsButton);
        final SmsManager smsManager = SmsManager.getDefault();

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String content = "directions~";
                        content+= source.getText().toString().replace(" ","")+";";
                        content+= dest.getText().toString();
                        //Log.d("DEBUG:",content);
                        smsManager.sendTextMessage("17328100017", null, content, null, null);
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_google_maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(s);
    }
}
