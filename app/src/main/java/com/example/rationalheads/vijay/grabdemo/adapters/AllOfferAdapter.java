package com.example.rationalheads.vijay.grabdemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class AllOfferAdapter extends RecyclerView.Adapter<AllOfferAdapter.AllOfferView> {

    Context context;
    ArrayList<Deals> deals_list;

    public AllOfferAdapter(Context context, ArrayList<Deals> deals_list) {
        this.context = context;
        this.deals_list = deals_list;
    }

    @NonNull
    @Override
    public AllOfferView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.all_offer_single_item,parent,false);

        return new AllOfferView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllOfferView holder, int position) {

        Deals deals=deals_list.get(position);

        holder.OfferTitle.setText(deals.getLogin());
        Glide.with(context).load(deals.getAvatar_url()).into(holder.OfferImage);


    }

    @Override
    public int getItemCount() {
        return deals_list.size();
    }

    public class AllOfferView extends RecyclerView.ViewHolder{

        ImageView OfferImage;
        TextView OfferTitle;


        public AllOfferView(View itemView) {
            super(itemView);

            OfferImage=itemView.findViewById(R.id.all_offer_image);
            OfferTitle=itemView.findViewById(R.id.all_offer_title);
        }
    }
}
