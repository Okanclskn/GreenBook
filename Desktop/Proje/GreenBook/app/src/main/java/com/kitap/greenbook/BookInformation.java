package com.kitap.greenbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.kitap.greenbook.data.model.Book;

import java.io.Serializable;

public class BookInformation extends AppCompatActivity implements Serializable {
    private ImageView ımageViewFilmResim;
    private TextView textViewFilmAd,textViewYil,textViewYonetmen;
    private Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        ımageViewFilmResim = findViewById(R.id.imageViewResim);
        textViewFilmAd = findViewById(R.id.textViewFilmAd);
        textViewYil = findViewById(R.id.textViewYil);
        textViewYonetmen = findViewById(R.id.textViewYonetmen);

        book = (Book) getIntent().getSerializableExtra("nesne");

        textViewFilmAd.setText(book.getBookName());
        textViewYil.setText(String.valueOf(book.getAuthor()));
        textViewYonetmen.setText(book.getBookCategory());


    }
}
