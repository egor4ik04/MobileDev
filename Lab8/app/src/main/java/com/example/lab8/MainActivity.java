package com.example.lab8;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity
        extends AppCompatActivity
{
    private static final int REQ_CALL = 100;

    private EditText editNumber;
    private TextView txtLifecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber = findViewById(R.id.editNumber);
        txtLifecycle = findViewById(R.id.txtLifecycle);

        Button btnCall = findViewById(R.id.btnCall);
        Button btnToContacts = findViewById(R.id.btnToContacts);

        btnCall.setOnClickListener(v -> tryCall());
        btnToContacts.setOnClickListener(v ->
                startActivity(new Intent(this, ContactsActivity.class)));

        log("onCreate()");
    }

    private void tryCall()
    {
        String number = editNumber.getText().toString().trim();
        if (number.isEmpty()) return;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQ_CALL
            );
        }
        else
        {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number)));
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQ_CALL)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                tryCall();
        }
    }

    private void log(String s)
    {
        txtLifecycle.append(s + "\n");
    }

    @Override protected void onStart() { super.onStart(); log("onStart()"); }
    @Override protected void onResume() { super.onResume(); log("onResume()"); }
    @Override protected void onPause() { super.onPause(); log("onPause()"); }
    @Override protected void onStop() { super.onStop(); log("onStop()"); }
    @Override protected void onDestroy() { super.onDestroy(); log("onDestroy()"); }
}