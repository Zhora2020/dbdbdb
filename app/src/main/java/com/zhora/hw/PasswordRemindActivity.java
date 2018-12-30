package com.zhora.hw;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordRemindActivity extends AppCompatActivity {

    Button btnExitToMain, btnStartReminding;
    EditText edtRemidingLogin;


    void remiding(String email){
        while (email.length() > 0 && email.substring(email.length() - 1).equals(" ")){
            email = email.substring(0, email.length() - 1);
        }
        if (email.length() == 0) {
            edtRemidingLogin.setText("");
            Toast.makeText(getApplicationContext(),"Логин не введен", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful()){
                   startActivity(new Intent(getApplicationContext(), MainActivity.class));
               }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_remind);
        btnExitToMain = findViewById(R.id.btn_exit_to_main);
        btnStartReminding = findViewById(R.id.btn_start_reminding);
        /*btnStartReminding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                remiding(edtRemidingLogin.getText().toString());
            }
        });
        btnExitToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });*/


    }
}
