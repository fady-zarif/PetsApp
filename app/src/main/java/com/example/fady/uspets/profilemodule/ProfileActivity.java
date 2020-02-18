package com.example.fady.uspets.profilemodule;

import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.fady.uspets.ControllerDI.ControllerComponent;
import com.example.fady.uspets.Owner;
import com.example.fady.uspets.R;
import com.example.fady.uspets.USPetsMain.PetUiManager;
import com.example.fady.uspets.USPetsMain.UsPetsMainView;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.Bidi;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileActivity extends UsPetsMainView implements Iprofile.IView {
    @BindView(R.id.imgUser)
    CircleImageView imgUser;
    @BindView(R.id.imgPickPhoto)
    ImageView imgPickPhoto;
    @BindView(R.id.tvUserName)
    MaterialEditText tvUserName;
    @BindView(R.id.tvUserEmail)
    MaterialEditText tvUserEmail;
    @BindView(R.id.tvUserPhone)
    MaterialEditText tvUserPhone;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ControllerComponent controllerComponent = initDaggerController(this, null);
        controllerComponent.inject(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.Toolbar)));

    }

    @OnClick(R.id.imgPickPhoto)
    public void imgPickPhotoOnClick() {

    }

    @OnClick(R.id.imgUser)
    public void imgUserOnClick() {
    }

    @Override
    public void showUserProfilePic(String imgUrl) {
        // TODO: 2019-11-14  put Picasso in the UiManager
        PetUiManager.getInstance().setPicassoImage(imgUrl, imgUser);
        AsyncTask asyncTask = new AsyncClass();
        asyncTask.execute("Aa");
    }

    @Override
    public void showUserInfo(Owner owner) {
        tvUserEmail.setText(owner.getoEmail());
        tvUserName.setText(owner.getoName());
        tvUserPhone.setText(owner.getoPhone());
    }

    public class AsyncClass extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

        @Override
        protected Void doInBackground(String... strings) {
            return null;
        }
    }

}
