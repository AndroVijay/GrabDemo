package com.example.rationalheads.vijay.grabdemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.rationalheads.vijay.grabdemo.R;
import com.example.rationalheads.vijay.grabdemo.fragments.PopularBankOfferFragment;
import com.example.rationalheads.vijay.grabdemo.model.Banks;

import java.util.ArrayList;
import java.util.List;

public class BankOfferAdapter extends RecyclerView.Adapter<BankOfferAdapter.BankOfferView> implements Filterable {


    private Context context;
    private List<Banks> contactList;
    private List<Banks> banksListFiltered;

    public BankOfferAdapter(Context context, List<Banks> contactList) {

        this.context = context;
        this.contactList = contactList;
        this.banksListFiltered=contactList;

    }


    @NonNull
    @Override
    public BankOfferView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.bank_offer_single_item, parent, false);
        return new BankOfferView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankOfferView holder, int position) {

        Banks banks = banksListFiltered.get(position);

        holder.bankTitle.setText(banks.getBankName());
        holder.bankOffer.setText(banks.getOfferName());


    }

    @Override
    public int getItemCount() {
        return banksListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    banksListFiltered = contactList;
                } else {
                    List<Banks> filteredList = new ArrayList<>();
                    for (Banks row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getBankName().toLowerCase().contains(charString.toLowerCase()) || row.getOfferName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    banksListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = banksListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                banksListFiltered = (ArrayList<Banks>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }


    public class BankOfferView extends RecyclerView.ViewHolder {

        TextView bankTitle, bankOffer;

        public BankOfferView(View itemView) {
            super(itemView);

            bankTitle = itemView.findViewById(R.id.bank_title);
            bankOffer = itemView.findViewById(R.id.bank_offer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

}

