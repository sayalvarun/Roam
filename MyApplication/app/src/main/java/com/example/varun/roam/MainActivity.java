package com.example.varun.roam;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.Menu;
import android.view.MenuItem;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    EditText source = null;
    EditText dest = null;
    Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        registerReceiver(new SmsListener(),filter);
        source = (EditText) findViewById(R.id.sourceTextField);
        dest = (EditText) findViewById(R.id.destinationTextField);
        button = (Button) findViewById(R.id.getDirectionsButton);
        final SmsManager smsManager = SmsManager.getDefault();

        button.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String content = "";
                    content+= "from: "+source.getText().toString()+"\n";
                    content+= "to: "+dest.getText().toString();
                    smsManager.sendTextMessage("17328100017", null, content, null, null);
                }
            }
        );
    }

    //SmsManager smsManager = SmsManager.getDefault();
    //smsManager.sendTextMessage("16318962070", null, "Varun is awesome", null, null);



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
