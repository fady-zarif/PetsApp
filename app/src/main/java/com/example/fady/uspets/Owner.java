package com.example.fady.uspets;

public class Owner {
    String oUid;
    String oName;
    String oEmail;
    String oPassword;
    String oPhone;
    String oPhoto;

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

    Long oRating;
    Long oRateNum;

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

    public void setoPhone(String oPhone) {
        this.oPhone = oPhone;
    }

    public String getoPhoto() {
        return oPhoto;
    }

    public void setoPhoto(String oPhoto) {
        this.oPhoto = oPhoto;
    }
}
