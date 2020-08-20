package com.example.moviecatalogueuiux.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.moviecatalogueuiux.R;
import com.example.moviecatalogueuiux.notification.AlarmReceiver;
import com.example.moviecatalogueuiux.storage.SharedPrefManager;

import java.util.Set;

public class SettingAlarmActivity extends AppCompatActivity {

    private Switch swDaily, swUpcoming;
    AlarmReceiver alarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_alarm);
        swDaily = findViewById(R.id.switch_daily);
        swUpcoming = findViewById(R.id.switch_upcoming);
        alarmReceiver = new AlarmReceiver();

        checkDailySwitch();
        checkUpcomingSwitch();

        swUpcoming.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    SharedPrefManager.setSwitchUpcoming(SettingAlarmActivity.this, true);
                    alarmReceiver.setRepeatingAlarm(SettingAlarmActivity.this, AlarmReceiver.TYPE_UPCOMING_NOTIFICATION, "08:00", "Upcoming Notification");
                    Toast toast2 = Toast.makeText(getApplicationContext(), String.valueOf(isChecked) ,Toast.LENGTH_LONG);
                    toast2.show();
                }else {
                    SharedPrefManager.setSwitchUpcoming(SettingAlarmActivity.this, false);
                    alarmReceiver.cancelAlarm(SettingAlarmActivity.this, AlarmReceiver.TYPE_UPCOMING_NOTIFICATION);
                    Toast toast2 = Toast.makeText(getApplicationContext(), String.valueOf(isChecked) ,Toast.LENGTH_LONG);
                    toast2.show();
                }
            }
        });

        swDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    SharedPrefManager.setSwitchDaily(SettingAlarmActivity.this, isChecked);
                    alarmReceiver.setRepeatingAlarm(SettingAlarmActivity.this, AlarmReceiver.TYPE_DAILY_NOTIFICATON, "07:00", "Daily Notification");
                }
                if (isChecked == false){
                    SharedPrefManager.setSwitchDaily(SettingAlarmActivity.this, isChecked);
                    alarmReceiver.cancelAlarm(SettingAlarmActivity.this, AlarmReceiver.TYPE_DAILY_NOTIFICATON);
                }
            }
        });
    }

    public void checkUpcomingSwitch(){
        if (SharedPrefManager.getSwitchUpcoming(SettingAlarmActivity.this) == true){
            swUpcoming.setChecked(true);
        }else {
            swUpcoming.setChecked(false);
        }

    }

    public void checkDailySwitch(){
        if (SharedPrefManager.getSwitchDaily(SettingAlarmActivity.this) == true){
            swDaily.setChecked(true);
        }else {
            swDaily.setChecked(false);
        }

    }
}
