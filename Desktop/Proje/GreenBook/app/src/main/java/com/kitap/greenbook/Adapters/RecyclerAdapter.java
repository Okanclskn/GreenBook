package com.kitap.greenbook.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kitap.greenbook.NavigationDrawerItem;
import com.kitap.greenbook.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    Context context;
    LayoutInflater inflater;
    ArrayList<NavigationDrawerItem> mDataList;


    public RecyclerAdapter(Context c,ArrayList<NavigationDrawerItem> data) {
        this.context=c;
        this.inflater=LayoutInflater.from(context);
        mDataList=data;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.line,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NavigationDrawerItem clickitem = mDataList.get(position);
        holder.setData(clickitem,position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView header;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            header=itemView.findViewById(R.id.header);
            icon=itemView.findViewById(R.id.image_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,header.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });

        }

        public void setData(NavigationDrawerItem clickitem, int position) {
            this.header.setText(clickitem.getHeader());
            this.icon.setImageResource(clickitem.getImageID());
        }
    }






}
