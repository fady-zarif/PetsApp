package com.example.fady.uspets.MainScreenModule.AdvertismentModule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.fady.uspets.Owner;
import com.example.fady.uspets.R;
import com.example.fady.uspets.USPetsMain.PetUiManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdvertisementView extends LinearLayout {

    // TODO: 4/17/20 replace with viewBinding
    @BindView(R.id.imgAdvPet)
    ImageView imgAdvPet;
    @BindView(R.id.tvPetType)
    TextView tvPetType;
    @BindView(R.id.tvPetAge)
    TextView tvPetAge;
    @BindView(R.id.tvPetGender)
    TextView tvPetGender;
    @BindView(R.id.tvPetPostDate)
    TextView tvPetPostDate;
    @BindView(R.id.tvPetPrice)
    TextView tvPetPrice;
    @BindView(R.id.imgOwner)
    ImageView imgOwner;
    @BindView(R.id.Rating_View)
    RatingBar ratingBar;

    public AdvertisementView(Context context) {
        super(context);
        initView(context);
    }

    public AdvertisementView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AdvertisementView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public AdvertisementView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }


    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.advertisment_view_layout, this);
        ButterKnife.bind(this, view);
    }

    public void setAdvertisementItem(AdvertisementModel advertisementModel) {
        if (advertisementModel.getPetImageArrayList() != null)
            setImgAdvPet(advertisementModel.getPetImageArrayList().get(0));

        setPetAge(advertisementModel.getAge());
        setPetType(advertisementModel.getType());
        setTvPetGender(advertisementModel.getGender());
        setTvPetPrice(advertisementModel.getPrice());
//        if (advertisementModel.getOwner() != null) {
//            PetsLogClass.showLogError("OWNEEISHERE", advertisementModel.getOwner().getoUid());
//            setAdOwnerInfo(advertisementModel.getOwner());
//        }
    }

    public void setAdOwnerInfo(Owner adOwnerInfo) {
        setAdOwnerPhoto(adOwnerInfo.getoPhoto());
        ratingBar.setRating(5);

//        setOwnerRate(adOwnerInfo.getoRateNum());
    }


    private void setOwnerRate(long rate) {
        ratingBar.setRating(5);
    }

    private void setAdOwnerPhoto(String url) {
        if (url != null) {
            if (!url.isEmpty()) {
                Picasso.get()
                        .load(url)
                        .centerCrop()
                        .into(imgOwner);
            }
        }
    }

    private void setImgAdvPet(String url) {
        if (!url.isEmpty()) {
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.loading_image)
                    .into(imgAdvPet);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setTvPetPrice(String price) {
        tvPetPrice.setText(price + PetUiManager.getInstance().getCurrencySymbol());
    }

    private void setTvPetGender(String gender) {
        tvPetGender.setText(gender);
    }

    private void setPetAge(String petAge) {
        tvPetAge.setText(petAge);
    }

    private void setPetType(String type) {
        tvPetType.setText(type);
    }

}

