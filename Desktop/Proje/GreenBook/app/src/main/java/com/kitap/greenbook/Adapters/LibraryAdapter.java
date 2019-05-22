package com.kitap.greenbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.kitap.greenbook.BookInformation;
import com.kitap.greenbook.R;
import com.kitap.greenbook.data.model.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public  class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.BookCard> implements Serializable {
    private Context mcontext;
    private List<Book> booklist;



    public LibraryAdapter(Context mcontext, List<Book> booklist) {
        this.mcontext = mcontext;
        this.booklist = booklist;

    }



    public class BookCard extends RecyclerView.ViewHolder{
        private CardView bookcard;
        private ImageView book_image;
        private TextView book_name;
        private TextView book_author;

        public BookCard(@NonNull View itemView) {
            super(itemView);
            book_image = itemView.findViewById(R.id.book_image);
            book_name = itemView.findViewById(R.id.book_name);
            book_author = itemView.findViewById(R.id.book_author);
            bookcard = itemView.findViewById(R.id.book_cardd);
        }
    }
    @NonNull
    @Override
    public BookCard onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_card,viewGroup,false);
        return new BookCard(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookCard bookCard, int position) {
        Book book = booklist.get(position);

        bookCard.book_name.setText(book.getBookName());
        bookCard.book_author.setText(book.getAuthor());
        bookCard.bookcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(mcontext, BookInformation.class);

                ıntent.putExtra("nesne", (Serializable) book);

                mcontext.startActivity(ıntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }
}
