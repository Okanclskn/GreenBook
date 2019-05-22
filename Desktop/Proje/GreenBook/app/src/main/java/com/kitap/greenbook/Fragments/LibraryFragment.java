package com.kitap.greenbook.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kitap.greenbook.Adapters.LibraryAdapter;
import com.kitap.greenbook.AddBook;
import com.kitap.greenbook.MainActivity;
import com.kitap.greenbook.R;
import com.kitap.greenbook.data.model.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class LibraryFragment extends Fragment implements Serializable {
    private RecyclerView booksRv;
    private LibraryAdapter libraryAdapter;
    private ArrayList<Book> BooksList;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    FirebaseUser fuser;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_library,container,false);

        TextView addbook = (TextView) view.findViewById(R.id.addbook);
        addbook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddBook.class);
                startActivity(intent);

            }
        });


        database = FirebaseDatabase.getInstance();
        booksRv = view.findViewById(R.id.booksRv);
        booksRv.setHasFixedSize(true);
        booksRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        BooksList = new ArrayList<>();
        databaseReference = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Library").child("Books");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Book books = snapshot.getValue(Book.class);
                    BooksList.add(books);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        libraryAdapter = new LibraryAdapter(getContext(),BooksList);
        booksRv.setAdapter(libraryAdapter);

        return view;
    }
}
