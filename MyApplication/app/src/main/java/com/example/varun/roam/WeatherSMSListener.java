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
public class WeatherSMSListener extends BroadcastReceiver {
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
                Log.d("WEATHER",command);
                if(command.equals("weather")) {
                    Intent newIntent = new Intent(context, DisplayWeather.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    String[] weatherData = outputString.split("~")[1].split(";");
                    newIntent.putExtra("todaysWeather", weatherData[0]);
                    newIntent.putExtra("tomorrowsWeather", weatherData[1]);
                    String[] weatherIcons = outputString.split("~")[2].split(";");
                    newIntent.putExtra("todaysWeatherIcon", weatherIcons[0]);
                    newIntent.putExtra("tomorrowsWeatherIcon", weatherIcons[1]);
                    context.startActivity(newIntent);
                }
            }catch(Exception r){
                r.printStackTrace();
            }
            Log.d("DEBUG:","This is after decompression");
        }
    }
}
