package com.example.oekonav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class SpalshScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);



        ParseUser currentUser = ParseUser.getCurrentUser();

        if(currentUser != null){
            if (currentUser.isAuthenticated()) {
                gotoMap();
            } else {

                Log.d("Hay8","DCM8");
                Intent intent = new Intent(this, MainActivity.class);
                Log.d("Hay9","DCM9");
                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                Log.d("Hay10","DCM10");

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Notification1")
                        .setContentText("")
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent);
                Log.d("Hay11","DCM11");



                NotificationManager notificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
                Log.d("Hay12","DCM12");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    builder.setChannelId("com.example.oekonav");
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(
                            "com.example.oekonav",
                            "ÖkoNav",
                            NotificationManager.IMPORTANCE_DEFAULT
                    );
                    if (notificationManager != null) {
                        notificationManager.createNotificationChannel(channel);
                    }
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                notificationManager.notify(2,builder.build());

                //startActivity(intent);
            }
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }

    }
    public void gotoMap() {
        Intent intent = new Intent(this, Navdrawmenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
