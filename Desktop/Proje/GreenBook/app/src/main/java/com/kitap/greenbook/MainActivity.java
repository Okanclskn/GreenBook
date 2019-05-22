package com.kitap.greenbook;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Printer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.kitap.greenbook.Adapters.LibraryAdapter;
import com.kitap.greenbook.Fragments.NavigationFragment;
import com.kitap.greenbook.data.model.Book;
import com.kitap.greenbook.data.model.Post;
import com.kitap.greenbook.data.model.remote.APIService;
import com.kitap.greenbook.data.model.remote.ApiUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();
        setUpDrawer();
    //    setUpBooks();


    }
/*
    private void setUpBooks() {
        RecyclerView recyclerView;
        ArrayList<Book> bookArrayList;
        LibraryAdapter adapter;


        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item =menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(MainActivity.this);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
                return true;
            case R.id.profile:
                startActivity(new Intent(MainActivity.this,Profile.class));
        }
        return false;
    }


    private void setUpToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Green Book");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


    }


    private void setUpDrawer(){
        NavigationFragment navigationFragment = (NavigationFragment) getFragmentManager().findFragmentById(R.id.fragment);
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        navigationFragment.NavigationDrawer(drawerLayout,toolbar);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("TextSubmit",query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("TextSubmit",newText);
        return false;
    }
}