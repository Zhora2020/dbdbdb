package com.zhora.hw;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText edtPassword, edtLogin;
    TextView txtForgotPassword, txtAppName;
    Button btnSignIn, btnSignUp;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    void signIn(String email, String password){
        while (email.length() > 0 && email.substring(email.length() - 1).equals(" ")){
            email = email.substring(0, email.length() - 1);
        }
        if (email.length() == 0) {
            edtLogin.setText("");
            Toast.makeText(getApplicationContext(),"Логин не введен", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() == 0) {
            Toast.makeText(getApplicationContext(),"Пароль не введен", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Добро пожаловать", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Неверный пароль или логин", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtForgotPassword =findViewById(R.id.txt_forget_password);
        btnSignIn = findViewById(R.id.sing_btn);
        edtPassword = findViewById(R.id.pass);
        edtLogin = findViewById(R.id.login);
        btnSignUp = findViewById(R.id.btn_go_to_registration);
        txtAppName = findViewById(R.id.txt_app_name);
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PasswordRemindActivity.class));
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("EMAIL&PASSWORD", (edtLogin.getText().toString() + " " + edtPassword.getText().toString()));
                signIn(edtLogin.getText().toString(), edtPassword.getText().toString());
            }
        });
    }
}
