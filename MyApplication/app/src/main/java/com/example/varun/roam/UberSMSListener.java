package com.example.varun.roam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Base64;
import android.util.Log;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by varun on 9/27/15.
 */
public class UberSMSListener extends BroadcastReceiver {
    private SharedPreferences preferences;


    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            String base = "";
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String messageBody = smsMessage.getMessageBody();
                base+=messageBody;

            }
            Log.d("DEBUG before decode:", base);
            byte[] data = Base64.decode(base, Base64.DEFAULT);
            Inflater decompressor = new Inflater();
            decompressor.setInput(data);
            byte[] res = new byte[2000];
            try {
                int resultLength = decompressor.inflate(res);
                decompressor.end();
                String outputString = new String(res, 0, resultLength, "UTF-8");

                String command = outputString.split("~")[0];
                Log.d("UBER",command);
                if(command.equals("uber")) {
                    Intent newIntent = new Intent(context, DisplayUber.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    String[] uberData = outputString.split("~");
                    ArrayList<String> products = new ArrayList<String>();
                    for(int i=1; i<products.size(); i++){
                        products.add(uberData[i]);
                    }

                    newIntent.putExtra("productStrings", outputString);
                    context.startActivity(newIntent);
                }
            }catch(Exception r){
                r.printStackTrace();
            }
            Log.d("DEBUG:","This is after decompression");
        }
    }

}
