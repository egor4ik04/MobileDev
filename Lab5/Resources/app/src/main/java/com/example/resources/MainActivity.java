package com.example.resources;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.FontRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity
        extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final LinearLayout mylayout = (LinearLayout)findViewById(R.id.root);
        TextView text = findViewById(R.id.my_text);
        TextView textLand = findViewById(R.id.my_text_land);
        int itId = item.getItemId();

        if (itId == R.id.red)
        {
            mylayout.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            return true;
        }
        else if (itId == R.id.green)
        {
            mylayout.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
            return true;
        }
        else if (itId == R.id.blue)
        {
            mylayout.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
            return true;
        }
        else if (itId == R.id.font_underdog)
        {
            setFont(text, R.font.underdog);
            setFont(textLand, R.font.underdog);
            return true;
        }
        else if (itId == R.id.font_poiret_one)
        {
            setFont(text, R.font.poiret_one);
            setFont(textLand, R.font.poiret_one);
            return true;
        }
        else if (itId == R.id.font_kablammo)
        {
            setFont(text, R.font.kablammo);
            setFont(textLand, R.font.kablammo);
            return true;
        }
        else if (itId == R.id.exit)
        {
            finish();
            return true;
        }
        else return super.onOptionsItemSelected(item);
    }

    private void setFont(TextView text, @FontRes int fontId)
    {
        if (text != null)
            text.setTypeface(ResourcesCompat.getFont(this, fontId));
    }
}