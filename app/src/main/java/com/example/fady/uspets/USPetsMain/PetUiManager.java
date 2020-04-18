package com.example.fady.uspets.USPetsMain;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.example.fady.uspets.Owner;
import com.example.fady.uspets.R;
import com.google.firebase.firestore.auth.User;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class PetUiManager {
    private static PetUiManager petUiManager;
    private Owner currentUser;
    private HashMap<String, String> userChannelsHashMap;


    public PetUiManager() {
        userChannelsHashMap = new HashMap<>();
    }

    public static PetUiManager getInstance() {
        if (petUiManager == null) {
            petUiManager = new PetUiManager();
        }
        return petUiManager;
    }

    public String getCurrencySymbol() {
        return Currency.getInstance(Locale.US).getSymbol();
    }

    public void setPicassoImage(String url, CircleImageView imageView) {
        Picasso.get().load(url).placeholder(R.drawable.loading_image).into(imageView);
    }

    public byte[] getByteArray(Context context, String uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(uri));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    public void setCurrentUser(Owner currentUser) {
        this.currentUser = currentUser;
    }

    public void addUserChannel(UserChannel userChannel) {
        if (!userChannelsHashMap.containsKey(userChannel.getUid())) {
            userChannelsHashMap.put(userChannel.getUid(), userChannel.getChannelId());
            getCurrentUser().addUserChannel(userChannel);
        }
//    if (currentUser==null)
//        Log.e("FADYSHAHAT"," Current User Null");
//    else
//        Log.e("FADYSHAHAT"," Current User "+currentUser.getoName());
    }

    public HashMap<String, String> getUserChannelsHashMap() {
        return userChannelsHashMap;
    }

    public Owner getCurrentUser() {
        return currentUser;
    }

}
