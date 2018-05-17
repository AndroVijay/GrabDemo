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
 * Created by vijay on 18-04-2018.
 */

public class CouponsRecyclerviewAdapter extends RecyclerView.Adapter<CouponsRecyclerviewAdapter.viewHolder> {

    Context context;
    ArrayList<Deals> deals_list;

    public CouponsRecyclerviewAdapter(Context context, ArrayList<Deals> deals_list) {
        this.context = context;
        this.deals_list = deals_list;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.coupons_item_layout,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        Deals deals=deals_list.get(position);

        holder.cTitle.setText(deals.getLogin());
        Glide.with(context).load(deals.getAvatar_url()).into(holder.cImage);


    }

    @Override
    public int getItemCount() {
        return deals_list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView cImage;
        TextView cTitle;
        public viewHolder(View itemView) {
            super(itemView);

            cImage=itemView.findViewById(R.id.coupon_image);
            cTitle=itemView.findViewById(R.id.coupon_title);
        }
    }
}
