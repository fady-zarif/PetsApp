package com.example.fady.uspets.MainScreenModule.AdvertismentModule;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.fady.uspets.Owner;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.ArrayList;
import java.util.Date;

public class AdvertisementModel implements Parcelable {
    private String ownerUid;
    private String name;
    private String adId;
    private String type;
    private String gender;
    private String description;
    private String age;
    private String price;
    private ArrayList<String> petImageArrayList;
    @ServerTimestamp
    private Date date;

    private Owner owner;


    public AdvertisementModel() {
    }

    public AdvertisementModel(String ownerUid, String name, String type, String gender, String description, String age, String price, ArrayList<String> petImageArrayList, Date date, String adId) {
        this.ownerUid = ownerUid;
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.description = description;
        this.age = age;
        this.price = price;
        this.petImageArrayList = petImageArrayList;
        this.date = date;
        this.adId = adId;
    }


    protected AdvertisementModel(Parcel in) {
        ownerUid = in.readString();
        name = in.readString();
        adId = in.readString();
        type = in.readString();
        gender = in.readString();
        description = in.readString();
        age = in.readString();
        price = in.readString();
        petImageArrayList = in.createStringArrayList();
        owner = in.readParcelable(Owner.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ownerUid);
        dest.writeString(name);
        dest.writeString(adId);
        dest.writeString(type);
        dest.writeString(gender);
        dest.writeString(description);
        dest.writeString(age);
        dest.writeString(price);
        dest.writeStringList(petImageArrayList);
        dest.writeParcelable(owner, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AdvertisementModel> CREATOR = new Creator<AdvertisementModel>() {
        @Override
        public AdvertisementModel createFromParcel(Parcel in) {
            return new AdvertisementModel(in);
        }

        @Override
        public AdvertisementModel[] newArray(int size) {
            return new AdvertisementModel[size];
        }
    };

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public AdvertisementModel(String name, String type, String gender, String description, String age, String price, ArrayList<String> petImageArrayList, Date date) {
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.description = description;
        this.age = age;
        this.price = price;
        this.petImageArrayList = petImageArrayList;
        this.date = date;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(String ownerUid) {
        this.ownerUid = ownerUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<String> getPetImageArrayList() {
        return petImageArrayList;
    }

    public void setPetImageArrayList(ArrayList<String> petImageArrayList) {
        this.petImageArrayList = petImageArrayList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
