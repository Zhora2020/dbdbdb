package com.zhora.hw;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    Button btnEndRegistration;
    EditText edtNewPassword, edtNewPasswordAgain, edtEmail, edtName, edtSurname;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    void registration(String email, String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                            String uid = auth.getCurrentUser().getUid();
                            database.getReference().child(uid).child("userInformation").setValue(new User(edtName.getText().toString(), edtSurname.getText().toString()));
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                        }
                    }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        edtEmail = findViewById(R.id.edt_email);
        btnEndRegistration = findViewById(R.id.btn_end_registration);
        edtNewPassword = findViewById(R.id.edt_password);
        edtName = findViewById(R.id.edt_name);
        edtSurname = findViewById(R.id.edt_surname);
        edtNewPasswordAgain = findViewById(R.id.edt_password_again);
        if (auth.getCurrentUser() != null){
            String uid = auth.getCurrentUser().getUid();
            /*database.getReference().child(uid).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    User currentUser = dataSnapshot.getValue(User.class);
                    edtName.setText(currentUser.getName());
                    edtSurname.setText(currentUser.getSurname());
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    User currentUser = dataSnapshot.getValue(User.class);
                    edtName.setText(currentUser.getName());
                    edtSurname.setText(currentUser.getSurname());
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });*/
        }
        btnEndRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNewPassword.getText().toString().equals(edtNewPasswordAgain.getText().toString())){
                    registration(edtEmail.getText().toString(), edtNewPassword.getText().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
