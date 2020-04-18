package com.example.fady.uspets.MainScreenModule.AdvertismentModule;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.AdvertisementViewHolder> {
    ArrayList<AdvertisementModel> advertisementModelArrayList;
    IAdvertisement.IAdvertismentClick iAdvertismentClick;

    public AdvertisementAdapter(ArrayList<AdvertisementModel> advertisementModelArrayList, IAdvertisement.IAdvertismentClick iAdvertismentClick) {
        this.advertisementModelArrayList = advertisementModelArrayList;
        this.iAdvertismentClick = iAdvertismentClick;

    }


    @Override
    public AdvertisementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdvertisementView advertisementView = new AdvertisementView(parent.getContext());
        return new AdvertisementViewHolder(advertisementView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertisementViewHolder holder, int position) {
        holder.advertisementView.setAdvertisementItem(advertisementModelArrayList.get(position));
        holder.advertisementView.setOnClickListener(v -> {
            iAdvertismentClick.onAdvertisementClickListener(position);
        });
    }

    @Override
    public int getItemCount() {
        return advertisementModelArrayList.size();
    }

    public class AdvertisementViewHolder extends RecyclerView.ViewHolder {
        AdvertisementView advertisementView;

        public AdvertisementViewHolder(@NonNull AdvertisementView itemView) {
            super(itemView);
            this.advertisementView = itemView;
        }
    }
}
