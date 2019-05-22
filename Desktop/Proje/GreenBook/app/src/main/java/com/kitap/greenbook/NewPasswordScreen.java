package com.kitap.greenbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class NewPasswordScreen extends AppCompatActivity {
    private EditText editTextEmail;
    private Button buttonSend;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password_screen);


        editTextEmail = (EditText) findViewById(R.id.E_Mail);
        buttonSend = (Button) findViewById(R.id.buttonSend);


        firebaseAuth = FirebaseAuth.getInstance();

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();


                if (email.isEmpty()) {
                    editTextEmail.setError("E-posta giriniz..");
                    editTextEmail.requestFocus();
                    return;
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(NewPasswordScreen.this,"E-postanıza şifre sıfırlama  linki gönderildi",Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(NewPasswordScreen.this,LoginActivity.class));
                           }
                           else{
                               String error=task.getException().getMessage();
                               Toast.makeText(NewPasswordScreen.this,error,Toast.LENGTH_SHORT).show();
                           }
                        }
                    });
                }
            }
        });
    }

}
