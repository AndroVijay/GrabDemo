package com.example.rationalheads.vijay.grabdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.model.Deals;

import java.util.ArrayList;

/**
 * Created by vijay on 13-04-2018.
 */

public class ApiDemoCustomAdapter extends RecyclerView.Adapter<ApiDemoCustomAdapter.MyViewHolder> {

    Context context;
    ArrayList<Deals> deals_list;

    public ApiDemoCustomAdapter(Context context, ArrayList<Deals> deals_list) {

        this.context = context;
        this.deals_list = deals_list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.deal_fragment_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Deals deals=deals_list.get(position);
        holder.item_name.setText(deals.getLogin());
        Glide.with(context).load(deals.getAvatar_url()).into(holder.item_image);

    }

    @Override
    public int getItemCount() {
        return deals_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_name;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.deal_image);
            item_name = itemView.findViewById(R.id.deal_text);
        }
    }
}
