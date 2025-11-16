package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements MyDialogFragment.MyDialogFragmentListener {

    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDialog = findViewById(R.id.btnDialog);
        txtResult = findViewById(R.id.txtResult);

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                MyDialogFragment dialog = new MyDialogFragment();
                dialog.show(manager, "myDialog");
            }
        });
    }

    @Override
    public void onReturnValue(String value) {
        txtResult.setText(value);
    }
}
