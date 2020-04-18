package com.example.fady.uspets.profileModule;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.fady.uspets.ControllerDI.ControllerComponent;
import com.example.fady.uspets.FirebaseDatabase.FirebaseConstant;
import com.example.fady.uspets.Owner;
import com.example.fady.uspets.R;
import com.example.fady.uspets.USPetsMain.PetUiManager;
import com.example.fady.uspets.USPetsMain.UsPetsMainView;
import com.example.fady.uspets.databinding.ActivityProfileBinding;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileActivity extends UsPetsMainView implements Iprofile.IView {

    @Inject
    ProfilePresenter profilePresenter;

    ActivityProfileBinding binding;

    @SuppressLint("RestrictedApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        ControllerComponent controllerComponent = initDaggerController(this, null);
        controllerComponent.inject(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }
        profilePresenter.getUserData();
        imgUserOnClick();

    }


    public void imgUserOnClick() {
        binding.imgUser.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, FirebaseConstant.PET_PIC_RESULT_CODE);
        });


    }

    @Override
    public void showUserProfilePic(String imgUrl) {
        PetUiManager.getInstance().setPicassoImage(imgUrl, binding.imgUser);
    }

    @Override
    public void showUserInfo(Owner owner) {
        binding.tvUserEmail.setText(owner.getoEmail());
        binding.tvUserName.setText(owner.getoName());
        binding.tvUserPhone.setText(owner.getoPhone());
    }

    @Override
    public void onUpdatePhotoSuccess(String url) {
        this.dismisProgressView();
        PetUiManager.getInstance().setPicassoImage(url, binding.imgUser);
    }

    @Override
    public void onUpdatePhotoFailed(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == FirebaseConstant.PET_PIC_RESULT_CODE) {
            if (data != null) {
                this.showProgressView();
                profilePresenter.uploadUserImage(data.getData().toString());
            }
        }
    }
}
