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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddBook extends AppCompatActivity {
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    DatabaseReference datareference;
    EditText bookName;
    EditText bookAuthor;
    EditText bookCategory;
    Button buttonAdd;
    ProgressDialog progressDialog;
    String userid;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        firebaseAuth = FirebaseAuth.getInstance();
        bookName = (EditText) findViewById(R.id.bookname);
        bookAuthor = (EditText) findViewById(R.id.bookauthor);
        bookCategory = (EditText) findViewById(R.id.bookcategory);
        buttonAdd = (Button) findViewById(R.id.button_add);

        progressDialog = new ProgressDialog(this);




        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookAdd();
            }
        });
    }

    private void BookAdd() {
        final String bookname = bookName.getText().toString().trim();
        final String bookauthor  = bookAuthor.getText().toString().trim();
        final String bookcategory = bookCategory.getText().toString().trim();
        if(TextUtils.isEmpty(bookname)|| TextUtils.isEmpty(bookauthor) || TextUtils.isEmpty(bookauthor)){
            Toast.makeText(this,"Lütfen tüm  alanları doldurun..",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Lütfen Bekleyiniz..");
        progressDialog.show();


        firebaseUser= firebaseAuth.getCurrentUser();
        String userid=firebaseUser.getUid();

        datareference = FirebaseDatabase.getInstance().getReference("Library").child("Books");

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("user id",userid);
        hashMap.put("book name",bookname);
        hashMap.put("book author",bookauthor);
        hashMap.put("book category",bookcategory);
        hashMap.put("imageURL","default");

        datareference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(AddBook.this, "Kitap Eklendi..", Toast.LENGTH_LONG).show();
                    Intent goIntent = new Intent(AddBook.this, Profile.class);
                    startActivity(goIntent);
                }
            }
        });

    }
}
