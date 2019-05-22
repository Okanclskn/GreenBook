package com.kitap.greenbook.Fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import com.kitap.greenbook.Adapters.RecyclerAdapter;
import com.kitap.greenbook.NavigationDrawerItem;
import com.kitap.greenbook.R;

public class NavigationFragment extends Fragment {
    private ActionBarDrawerToggle mdrawerToggle;
    private DrawerLayout mdrawerLayout;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_navigation,container,false);
        setUpRcyclerView(view);
        return view;
    }

    private void setUpRcyclerView(View view) {
        RecyclerView recyclerView =  view.findViewById(R.id.navigation_list);
        RecyclerAdapter adapter = new RecyclerAdapter(getActivity(), NavigationDrawerItem.getData());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    public void NavigationDrawer(DrawerLayout drawerLayout,Toolbar toolbar){
        mdrawerLayout=drawerLayout;

        mdrawerToggle=new ActionBarDrawerToggle(getActivity(),mdrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        mdrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                mdrawerToggle.onDrawerSlide(drawerView,slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        mdrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mdrawerToggle.syncState();
            }
        });

    }
}
