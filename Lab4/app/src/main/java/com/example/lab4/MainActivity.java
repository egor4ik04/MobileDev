package com.example.lab4;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity
    extends AppCompatActivity
    implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener
{
    private SwitchCompat themeSwitch;
    private CheckBox checkBox;
    private RadioGroup radioGroupCats;
    private ImageButton imageButtonCat;
    private boolean isFullScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themeSwitch = findViewById(R.id.themeSwitch);
        checkBox = findViewById(R.id.checkBox);
        radioGroupCats = findViewById(R.id.radioGroupCats);
        imageButtonCat = findViewById(R.id.imageButtonCat);

        themeSwitch.setOnCheckedChangeListener(this);
        checkBox.setOnCheckedChangeListener(this);
        radioGroupCats.setOnCheckedChangeListener(this);
        imageButtonCat.setOnClickListener(this);

        radioGroupCats.check(0);
    }

    @Override
    public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked)
    {
        if (buttonView == themeSwitch)
        {
            if (isChecked)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (buttonView == checkBox)
        {
            radioGroupCats.setOrientation(isChecked ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);
        }
    }

    @Override
    public void onCheckedChanged(@NonNull RadioGroup group, int checkedId)
    {
        if (group == radioGroupCats)
        {
            if      (checkedId == R.id.radioCat1)
                imageButtonCat.setImageResource(R.drawable.cat1);
            else if (checkedId == R.id.radioCat2)
                imageButtonCat.setImageResource(R.drawable.cat2);
            else if (checkedId == R.id.radioCat3)
                imageButtonCat.setImageResource(R.drawable.cat3);
            else if (checkedId == R.id.radioCat4)
                imageButtonCat.setImageResource(R.drawable.cat4);
        }
    }

    @Override
    public void onClick(View v)
    {
        if (v == imageButtonCat) {
            if (!isFullScreen) {
                imageButtonCat.setScaleX(2f);
                imageButtonCat.setScaleY(2f);
                isFullScreen = true;
            } else {
                imageButtonCat.setScaleX(1f);
                imageButtonCat.setScaleY(1f);
                isFullScreen = false;
            }
        }
    }
}