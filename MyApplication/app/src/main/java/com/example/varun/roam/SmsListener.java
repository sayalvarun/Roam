package com.example.varun.roam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;

/**
 * Created by varun on 9/26/15.
 */
public class SmsListener extends BroadcastReceiver{
    private SharedPreferences preferences;


    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            String base = "";
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String messageBody = smsMessage.getMessageBody();
                base+=messageBody;

            }
            Log.d("DEBUG before decode:",base);
            byte[] data = Base64.decode(base, Base64.DEFAULT);
            Inflater decompressor = new Inflater();
            decompressor.setInput(data);
            byte[] res = new byte[2000];
            try {
                int resultLength = decompressor.inflate(res);
                decompressor.end();
                String outputString = new String(res, 0, resultLength, "UTF-8");
                Log.d("DATA*****",outputString);
                String[] elems = outputString.split("~");
                String totalDistance = elems[0].split(";")[0];
                String totalTime = elems[0].split(";")[1];

                Intent newIntent = new Intent(context,ListDirections.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                newIntent.putExtra("totalDistance",totalDistance);
                newIntent.putExtra("totalDuration",totalTime);
                ArrayList<String> list = new ArrayList<String>();
                for(int i=1; i<elems.length; i++){
                    list.add(elems[i]);
                }
                newIntent.putExtra("directions",list);
                context.startActivity(newIntent);
            }catch(Exception r){
                r.printStackTrace();
            }
            Log.d("DEBUG:","This is after decompression");
        }
    }

}
