package com.example.lab8;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ContactsActivity
        extends AppCompatActivity
{
    private static final int REQ_CONTACTS = 200;
    private ListView listContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        listContacts = findViewById(R.id.listContacts);

        Button btnToMain = findViewById(R.id.btnToMain);

        btnToMain.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class)));

        loadContactsWithPermission();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        loadContactsWithPermission();
    }

    private void loadContactsWithPermission()
    {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    REQ_CONTACTS
            );
        }
        else
        {
            loadContacts();
        }
    }

    private void loadContacts()
    {
        ArrayList<String> items = new ArrayList<>();

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                },
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        );

        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                String name = cursor.getString(0);
                String number = cursor.getString(1);
                items.add(name + " — " + number);
            }
            cursor.close();
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listContacts.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQ_CONTACTS) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED)
                loadContacts();
        }
    }
}
