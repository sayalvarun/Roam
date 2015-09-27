package com.example.varun.roam;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.pwittchen.weathericonview.WeatherIconView;


public class DisplayWeather extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_weather);


        String today = getIntent().getExtras().getString("todaysWeather");
        String tomorrow = getIntent().getExtras().getString("tomorrowsWeather");
        String todayIcon = getIntent().getExtras().getString("todaysWeatherIcon");
        String tomorrowIcon = getIntent().getExtras().getString("tomorrowsWeatherIcon");

        WeatherIconView todayIconView = (WeatherIconView) findViewById(R.id.today_weather_icon);
        WeatherIconView tomorrowIconView = (WeatherIconView) findViewById(R.id.tomorrow_weather_icon);

        setIcon(todayIconView, todayIcon);
        setIcon(tomorrowIconView, tomorrowIcon);

        TextView todayview = (TextView) findViewById(R.id.todays_weather);
        TextView tomorrowview = (TextView) findViewById(R.id.tomorrows_weather);
        todayview.setText(today);
        tomorrowview.setText(tomorrow);
    }


    private void setIcon(WeatherIconView iconView, String iconAPI){

        iconView.setIconResource(getString(R.string.wi_alien));

        if(iconAPI.contains("clear-day")){
            iconView.setIconResource(getString(R.string.wi_day_sunny));
        }
        if(iconAPI.contains("partly-cloudy-day")) {
            iconView.setIconResource(getString(R.string.wi_day_cloudy));
        }
        if(iconAPI.contains("cloudy")) {
            iconView.setIconResource(getString(R.string.wi_cloud));
        }
        if(iconAPI.contains("wind")) {
            iconView.setIconResource(getString(R.string.wi_windy));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_weather, menu);
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
