package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener, TextWatcher
{
    private float mTextSize = 20;
    private TextView mEdit;
    private TextView tSize;
    private ToggleButton buttonB;
    private ToggleButton buttonI;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdit = findViewById(R.id.resultTextTextView);
        tSize = findViewById(R.id.textSizeValueTextView);

        buttonB = findViewById(R.id.boldButtonView);
        buttonI = findViewById(R.id.italicButtonView);
        RadioButton buttonSans = findViewById(R.id.sansSerifRadioButton);
        RadioButton buttonSerif = findViewById(R.id.serifRadioButton);
        RadioButton buttonMonospace = findViewById(R.id.monospaceRadioButton);
        Button buttonPlus = findViewById(R.id.greaterTextSizeButtonView);
        Button buttonMinus = findViewById(R.id.lessTextSizeButtonView);
        EditText editText = findViewById(R.id.editTextView);

        buttonB.setOnClickListener(this);
        buttonI.setOnClickListener(this);
        buttonSans.setOnClickListener(this);
        buttonSerif.setOnClickListener(this);
        buttonMonospace.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        editText.addTextChangedListener(this);

        tSize.setText(Integer.toString((int) mTextSize));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v)
    {
        int but = v.getId();
        if (but == R.id.boldButtonView || but == R.id.italicButtonView)
        {
            int typefaceStyle = buttonB.isChecked() ?
                    (buttonI.isChecked() ? Typeface.BOLD_ITALIC : Typeface.BOLD) :
                    (buttonI.isChecked() ? Typeface.ITALIC : Typeface.NORMAL);

            mEdit.setTypeface(Typeface.create(mEdit.getTypeface(), typefaceStyle));

            ToggleButton button = but == R.id.boldButtonView ? buttonB : buttonI;
            int color = ContextCompat.getColor(this, button.isChecked() ? R.color.defaultActive : R.color.defaultInactive);
            button.setBackgroundTintList(ColorStateList.valueOf(color));
        }
        else if (but == R.id.sansSerifRadioButton)
            mEdit.setTypeface(Typeface.SANS_SERIF, mEdit.getTypeface().getStyle());
        else if (but == R.id.serifRadioButton)
            mEdit.setTypeface(Typeface.SERIF, mEdit.getTypeface().getStyle());
        else if (but == R.id.monospaceRadioButton)
            mEdit.setTypeface(Typeface.MONOSPACE, mEdit.getTypeface().getStyle());
        else if (but == R.id.greaterTextSizeButtonView) {
            if (mTextSize <= 72)
                mTextSize += 2;
            mEdit.setTextSize(mTextSize);
            tSize.setText(Integer.toString((int) mTextSize));
        }
        else if (but == R.id.lessTextSizeButtonView) {
            if (mTextSize >= 20)
                mTextSize -= 2;
            mEdit.setTextSize(mTextSize);
            tSize.setText(Integer.toString((int) mTextSize));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) { }
    @Override
    public void afterTextChanged(Editable s)
    {
        String currentText = s.toString();
        mEdit.setText(currentText);
    }
}