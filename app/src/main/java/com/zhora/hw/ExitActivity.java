package com.zhora.hw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        setContentView(R.layout.activity_exit);
        Button btn2Exit = findViewById(R.id.second_exit);
        btn2Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}
