package com.example.moviecatalogueuiux.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.moviecatalogueuiux.R;
import com.example.moviecatalogueuiux.activity.DetailActivity;
import com.example.moviecatalogueuiux.activity.MainActivity;
import com.example.moviecatalogueuiux.adapter.MovieAdapter;
import com.example.moviecatalogueuiux.api.APIClient;
import com.example.moviecatalogueuiux.api.APIInterface;
import com.example.moviecatalogueuiux.model.MovieModel;
import com.example.moviecatalogueuiux.response.MovieResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmReceiver extends BroadcastReceiver {
    public static String API_KEY = "312582920fd7603b69f03439e144b0dc";
    public static  String LANGUAGE = "en-us";
    public static final String TYPE_UPCOMING_NOTIFICATION = "Upcoming";
    public static final String TYPE_DAILY_NOTIFICATON = "Daily";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_TYPE = "type";
    private final int ID_UPCOMING = 100;
    private final int ID_DAILY = 101;
    private String DATE_FORMAT = "yyyy-MM-dd";
    private String TIME_FORMAT = "HH:mm";
    private Calendar newCalendar;


    @Override
    public void onReceive(Context context, Intent intent) {
        String type = intent.getStringExtra(EXTRA_TYPE);
        if (type.equals(TYPE_UPCOMING_NOTIFICATION)){
            newCalendar = Calendar.getInstance();
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH) .format(newCalendar.getTime());
            String date_gte = currentDate;
            String date_lte = currentDate;
            getUpcomingMovie(context, date_gte, date_lte);
        } else {
            String title = "mFlix";
            String pesan = "Daily Notification - Yuk Cek Aplikasinya";
            showAlarmNotification(context, title, pesan, ID_DAILY);
            showToast(context, title, pesan);
        }
    }

    private void getUpcomingMovie(final Context context, String date_gte, String date_lte){

        APIInterface apiService =
                APIClient.getClient()
                        .create(APIInterface.class);
        Call<MovieResponse> getMovieNotification = apiService.getTodayMovie(API_KEY, LANGUAGE, date_gte, date_lte);
        getMovieNotification.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponses = response.body();
                List<MovieResponse.Data> getData = movieResponses.getDataList();
                if (getData != null){
                    for (int i = 0 ; i<getData.size() ; i++){
                        int id_movie = getData.get(i).getId();
                        String name = getData.get(i).getOriginal_title();
                        if (!name.contains("'")){
                            MovieModel movieModel = new MovieModel();
                            movieModel.setId(getData.get(i).getId());
                            movieModel.setPhoto(getData.get(i).getPoster_path());
                            movieModel.setName(name);
                            movieModel.setDescription(getData.get(i).getOverview());
                            movieModel.setReleasedate(getData.get(i).getRelease_date());
                            movieModel.setRate(getData.get(i).getVote_average());
                            String movieTitl = getData.get(i).getOriginal_title();
                            String notification = "Film Rilis Hari Ini " + movieTitl;
                            showAlarmNotificationRelease(context, movieTitl, notification, id_movie, movieModel);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showToast(Context context, String title, String message) {
        Toast.makeText(context, title + " : " + message, Toast.LENGTH_LONG).show();
    }

    public void setRepeatingAlarm(Context context, String type, String time, String message) {
        if (isDateInvalid(time, TIME_FORMAT)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_TYPE, type);

        String timeArray[] = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);
//        if (calendar.before(Calendar.getInstance())) calendar.add(Calendar.DATE, 1);

        PendingIntent pendingIntent;
        if (type.equals(TYPE_DAILY_NOTIFICATON)){
            int requestCode = ID_DAILY;
            pendingIntent = PendingIntent.getBroadcast(context, ID_DAILY, intent, 0);

            Toast.makeText(context, "Daily notification set up", Toast.LENGTH_SHORT).show();
        }else {
            int requestCode = ID_UPCOMING;
            pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Toast.makeText(context, "Upcoming notification set up", Toast.LENGTH_SHORT).show();
        }
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }



    }

    public void cancelAlarm(Context context, String type) {
        Log.i("cancelAlarm", "cancelAlarm");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        if (type.equals(TYPE_DAILY_NOTIFICATON)){
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_DAILY, intent, 0);
            pendingIntent.cancel();
            if (alarmManager != null) {
                alarmManager.cancel(pendingIntent);
            }
            Toast.makeText(context, "Daily notification dibatalkan", Toast.LENGTH_SHORT).show();
        }

        if (type.equals(TYPE_UPCOMING_NOTIFICATION)){
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_UPCOMING, intent, 0);
            pendingIntent.cancel();
            if (alarmManager != null) {
                alarmManager.cancel(pendingIntent);
            }
            Toast.makeText(context, "Upcoming notification dibatalkan", Toast.LENGTH_SHORT).show();
        }

    }

    private void showAlarmNotification(Context context, String title, String message, int notifId) {
        String CHANNEL_ID = "Channel_1";
        String CHANNEL_NAME = "AlarmManager channel";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setChannelId(CHANNEL_ID)
                .setSound(alarmSound);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

            builder.setChannelId(CHANNEL_ID);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }

        }
            notificationManager.notify(notifId, builder.build());
    }

    private void showAlarmNotificationRelease(Context context, String title, String message, int notifId, MovieModel movieModel) {
        String CHANNEL_ID = "Channel_1";
        String CHANNEL_NAME = "AlarmManager channel";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("object", movieModel);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setChannelId(CHANNEL_ID)
                .setSound(alarmSound);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

            builder.setChannelId(CHANNEL_ID);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }

        }
        notificationManager.notify(notifId, builder.build());
    }


    public boolean isDateInvalid(String date, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
            df.setLenient(false);
            df.parse(date);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }
}
