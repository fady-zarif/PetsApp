package com.example.fady.uspets.USPetsMain;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.fady.uspets.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SliderItemImage extends LinearLayout {
    @BindView(R.id.imgPetPic)
    ImageView imageView;

    public SliderItemImage(Context context) {
        super(context);
        initItemView(context);
    }

    public SliderItemImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initItemView(context);
    }

    public SliderItemImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initItemView(context);
    }

    public SliderItemImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initItemView(context);
    }

    private void initItemView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.pet_image_item_slider, this);
        ButterKnife.bind(this, view);
    }
    public void setImageView(String url)
    {
        Picasso.get().load(url).placeholder(R.drawable.loading_image).into(imageView);
    }
}
