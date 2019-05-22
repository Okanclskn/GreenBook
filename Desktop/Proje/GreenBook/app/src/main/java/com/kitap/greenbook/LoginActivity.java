package com.kitap.greenbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kitap.greenbook.data.model.remote.APIService;
import com.kitap.greenbook.data.model.remote.ApiUtils;
import com.kitap.greenbook.data.model.remote.LoginResponse;
import com.kitap.greenbook.data.model.remote.RegisterResponse;

import org.json.JSONObject;

import java.util.Map;
import java.util.regex.Pattern;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textForgot;
    private Button buttonRegister;
    private APIService mAPIService;
    private TextView mResponseTv;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser != null){
            Intent goIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(goIntent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.E_Mail);
        editTextPassword = findViewById(R.id.Password);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);
        textForgot = findViewById(R.id.textForgot);
        textForgot.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("E-posta giriniz..");
            editTextEmail.requestFocus();
            return;
        }

        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Geçerli bir E-posta giriniz..");
            editTextEmail.requestFocus();
            return;
        }

        else if (password.isEmpty()) {
            editTextPassword.setError("Şifrenizi giriniz..");
            editTextPassword.requestFocus();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent goIntent = new Intent(LoginActivity.this, MainActivity.class);
                            goIntent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK  );
                            startActivity(goIntent);
                            finish();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"E-posta veya şifre hatalı..",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

       // sendPost(email, password);

    }
/*
    public void sendPost(String email, String password) {
    /*    Map<String, Object> jsonParams = new ArrayMap<>();
        jsonParams.put("username", email);
        jsonParams.put("password", password);

        LoginResponse loginResponse = new LoginResponse(email,password);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());

        LoginResponse loginResponse = new LoginResponse(email,password);
        Call<Void> call = ApiUtils.getAPIService().loginUSer(loginResponse);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.code()==200) {

                    Toast.makeText(getApplicationContext(),"Giriş başarılı...",Toast.LENGTH_SHORT).show();
                    Intent goIntent = new Intent(LoginActivity.this, MainActivity.class);
                    goIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(goIntent);
                    finish();
                }

                else{
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_SHORT).show();
                }

            }



            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(),call.toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }

*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                userLogin();
                break;
            case R.id.buttonRegister:
                Intent goIntent = new Intent(LoginActivity.this,RegisterScreen.class);
                startActivity(goIntent);
                break;
            case R.id.textForgot:
                Intent gointent = new Intent(LoginActivity.this,NewPasswordScreen.class);
                startActivity(gointent);
                break;
        }
    }
}