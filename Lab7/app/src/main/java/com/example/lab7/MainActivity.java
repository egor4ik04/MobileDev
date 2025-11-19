package com.example.lab7;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity
        extends AppCompatActivity
{
    final String CHANNEL_ID = "ch1";
    final int PERMISSION_CODE = 100;
    final int NOTIFICATION_ID = 1;

    EditText notificationMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createNotificationChannel();
        startToast();

        notificationMessage = findViewById(R.id.notificationMessageEditText);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null)
            {
                CharSequence name = getString(R.string.channel_name);
                String description = getString(R.string.channel_description);
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
    private void startToast()
    {
        sendToast(getString(R.string.start_toast));
    }
    private void sendToast(CharSequence text)
    {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }
    public void createNotification(View view) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED)
        {
            showNotification();
        }
        else
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[] {Manifest.permission.POST_NOTIFICATIONS},
                        PERMISSION_CODE);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                showNotification();
        }
    }
    private void showNotification()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED)
            return;

        String textTitle = getString(R.string.notification_title);
        String textContent = notificationMessage.getText().length() > 0 ?
                getString(R.string.notification_description_long) + notificationMessage.getText() :
                getString(R.string.notification_description_short);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.terminal_24px)
                        .setContentTitle(textTitle)
                        .setContentText(textContent)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(textContent))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}