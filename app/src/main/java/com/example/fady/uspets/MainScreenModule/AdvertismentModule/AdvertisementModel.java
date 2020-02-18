package com.example.fady.uspets.MainScreenModule.AdvertismentModule;

import com.example.fady.uspets.Owner;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class AdvertisementModel {
    private String ownerUid;
    private String name;
    private String adId;
    private String type;
    private String gender;
    private String description;
    private String age;
    private String price;
    private String petImage;
    @ServerTimestamp
    private Date date;

    private Owner owner;


    public AdvertisementModel() {
    }

    public AdvertisementModel(String ownerUid, String name, String type, String gender, String description, String age, String price, String petImage, Date date, String adId) {
        this.ownerUid = ownerUid;
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.description = description;
        this.age = age;
        this.price = price;
        this.petImage = petImage;
        this.date = date;
        this.adId = adId;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public AdvertisementModel(String name, String type, String gender, String description, String age, String price, String petImage, Date date) {
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.description = description;
        this.age = age;
        this.price = price;
        this.petImage = petImage;
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

    public String getPetImage() {
        return petImage;
    }

    public void setPetImage(String petImage) {
        this.petImage = petImage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
