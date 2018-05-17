package com.example.rationalheads.vijay.grabdemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.model.Festival;

import java.util.ArrayList;
import java.util.Objects;

public class FestivalOfferAdapter extends RecyclerView.Adapter<FestivalOfferAdapter.FestivalView> {

    private OnItemClickListener mListener;
    private ArrayList<Festival> arrayList;
    private Context context;



    public interface  OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }
    public FestivalOfferAdapter(ArrayList<Festival> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FestivalView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_item_festival,parent,false);

        return new FestivalView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FestivalView holder, int position) {

        Festival festival=arrayList.get(position);

        holder.fnumber.setText(festival.getFestnumber());
        holder.festTitle.setText(festival.getFesttle());




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class FestivalView extends RecyclerView.ViewHolder{


        TextView  festTitle,fnumber;


        public FestivalView(View itemView) {
            super(itemView);

            fnumber=itemView.findViewById(R.id.fest_number);
            festTitle=itemView.findViewById(R.id.fest_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListener!=null)
                    {
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION)
                        {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
