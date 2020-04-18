package com.example.fady.uspets;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.fady.uspets.USPetsMain.UserChannel;

import java.util.ArrayList;

public class Owner implements Parcelable {
    private String oUid;
    private String oName;
    private String oEmail;
    private String oPassword;
    private String oPhone;
    private String oPhoto;
    private ArrayList<UserChannel> userChannels;
    private Long oRating;
    private Long oRateNum;


    protected Owner(Parcel in) {
        oUid = in.readString();
        oName = in.readString();
        oEmail = in.readString();
        oPassword = in.readString();
        oPhone = in.readString();
        oPhoto = in.readString();
        if (in.readByte() == 0) {
            oRating = null;
        } else {
            oRating = in.readLong();
        }
        if (in.readByte() == 0) {
            oRateNum = null;
        } else {
            oRateNum = in.readLong();
        }
    }

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };

    public Long getoRating() {
        return oRating;
    }

    public void setoRating(Long oRating) {
        this.oRating = oRating;
    }

    public Long getoRateNum() {
        return oRateNum;
    }

    public void setoRateNum(Long oRateNum) {
        this.oRateNum = oRateNum;
    }


    public Owner() {
    }

    public Owner(String oName, String oEmail, String oPassword, String oPhone) {
        this.oName = oName;
        this.oEmail = oEmail;
        this.oPassword = oPassword;
        this.oPhone = oPhone;
    }


    public Owner(String oName, String oEmail, String oPassword, String oPhone, String oPhoto) {
        this.oName = oName;
        this.oEmail = oEmail;
        this.oPassword = oPassword;
        this.oPhone = oPhone;
        this.oPhoto = oPhoto;
    }

    public Owner(String oName, String oEmail, String oPassword, String oPhone, String oPhoto, Long oRating, Long oRateNum) {
        this.oName = oName;
        this.oEmail = oEmail;
        this.oPassword = oPassword;
        this.oPhone = oPhone;
        this.oPhoto = oPhoto;
        this.oRating = oRating;
        this.oRateNum = oRateNum;

    }

    public Owner(String oName, String oEmail, String oPassword, String oPhone, String oPhoto,
                 Long oRating, Long oRateNum, ArrayList<UserChannel> userChannels) {
        this(oName, oEmail, oPassword, oPhone, oPhoto, oRating, oRateNum);
        this.userChannels = userChannels;

    }

    public String getoUid() {
        return oUid;
    }

    public void setoUid(String oUid) {
        this.oUid = oUid;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public String getoEmail() {
        return oEmail;
    }

    public void setoEmail(String oEmail) {
        this.oEmail = oEmail;
    }

    public String getoPassword() {
        return oPassword;
    }

    public void setoPassword(String oPassword) {
        this.oPassword = oPassword;
    }

    public String getoPhone() {
        return oPhone;
    }

    public ArrayList<UserChannel> getUserChannels() {
        return userChannels;
    }

    public void setUserChannels(ArrayList<UserChannel> userChannels) {
        this.userChannels = userChannels;
    }

    public void setoPhone(String oPhone) {
        this.oPhone = oPhone;
    }

    public String getoPhoto() {
        return oPhoto;
    }

    public void setoPhoto(String oPhoto) {
        this.oPhoto = oPhoto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(oUid);
        dest.writeString(oName);
        dest.writeString(oEmail);
        dest.writeString(oPassword);
        dest.writeString(oPhone);
        dest.writeString(oPhoto);
        if (oRating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(oRating);
        }
        if (oRateNum == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(oRateNum);
        }
    }

    public void addUserChannel(UserChannel userChannel) {
        if (userChannels == null)
            userChannels = new ArrayList<>();
        userChannels.add(userChannel);
    }
}
