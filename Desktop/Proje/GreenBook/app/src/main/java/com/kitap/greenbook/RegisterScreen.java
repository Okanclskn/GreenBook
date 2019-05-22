package com.kitap.greenbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kitap.greenbook.data.model.remote.APIService;
import com.kitap.greenbook.data.model.remote.ApiUtils;
import com.kitap.greenbook.data.model.remote.RegisterResponse;
import com.kitap.greenbook.data.model.remote.RetrofitClient;

import java.time.Instant;
import java.util.HashMap;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterScreen extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private static final Pattern PASSWORD_PATTERN= Pattern.compile("^"+
            "(?=.*[0-9])"+ "(?=.*[a-zA-Z])"+"(?=\\S+$)"+".{6,15}"+"$");
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextSurName;
    private EditText editTextPasswordConfirm;
    private Button buttonSignup;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);


        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.rE_Mail);
        editTextPassword = findViewById(R.id.rPassword);
        editTextPasswordConfirm = findViewById(R.id.rPasswordConfirm);
        editTextName = findViewById(R.id.Name);
        editTextSurName = findViewById(R.id.SurName);
        buttonSignup = findViewById(R.id.SignUp);
        buttonSignup.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);

    }
    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String passwordre = editTextPasswordConfirm.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String surname = editTextSurName.getText().toString().trim();


        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name) || TextUtils.isEmpty(surname)) {
            Toast.makeText(this, "Lütfen tüm boş alanları doldurun..", Toast.LENGTH_LONG).show();
            return;
        }

        else if (password.length() < 6) {
            Toast.makeText(this, "Şifreniz 6 karakterden küçük olamaz..", Toast.LENGTH_LONG).show();
            return;
        }
        else if (!password.equals(passwordre)) {
            Toast.makeText(this, "Şifre doğrulama uyuşmuyor", Toast.LENGTH_LONG).show();
            return;
        }
        else if(!PASSWORD_PATTERN.matcher(password).matches()){
            editTextEmail.setError("Şifreniz en az bir rakam veya harf içermeli ve özel karakter içeremez..");
            editTextEmail.requestFocus();
            return ;
        }


        progressDialog.setMessage("Lütfen Bekleyiniz..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            String userid=firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String,String> hashmap = new HashMap<>();
                            hashmap.put("id",userid);
                            hashmap.put("name",name);
                            hashmap.put("surName",surname);
                            hashmap.put("e-mail",email);
                            hashmap.put("password",password);
                            hashmap.put("imageURL","default");
                            hashmap.put("username",name +"  "+ surname);

                            reference.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(RegisterScreen.this, "Kayıt Başarılı..", Toast.LENGTH_LONG).show();
                                        Intent goIntent = new Intent(RegisterScreen.this, LoginActivity.class);
                                        startActivity(goIntent);
                                    }
                                }
                            });

                        }
                        else{
                            Toast.makeText(RegisterScreen.this,"Lütfen tüm bilgileri kontrol ediniz..",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    @Override
    public void onClick(View v) {
        registerUser();
    }
}
