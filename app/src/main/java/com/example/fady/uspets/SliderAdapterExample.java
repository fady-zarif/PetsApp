package com.example.fady.uspets;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.example.fady.uspets.USPetsMain.SliderItemImage;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapterExample extends
        SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;
    private List<String> mSliderItems;

    public SliderAdapterExample(Context context, List<String> mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;
    }

    public void renewItems(List<String> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(String sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        SliderItemImage sliderItemImage = new SliderItemImage(context);
        return new SliderAdapterVH(sliderItemImage);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        viewHolder.sliderItemImage.setImageView(mSliderItems.get(position));

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        SliderItemImage sliderItemImage;

        public SliderAdapterVH(SliderItemImage itemView) {
            super(itemView);

            this.sliderItemImage = itemView;
        }
    }

}