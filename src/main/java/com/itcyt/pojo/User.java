package com.itcyt.pojo;

/**
 * 用户bean
 */
public class User {
    private String _id;
    private String _openId;
    private String admin;
    private String faceImg;
    private String nickName;
    private String number;
    private String password;
    private String signature;


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", _openId='" + _openId + '\'' +
                ", admin='" + admin + '\'' +
                ", faceImg='" + faceImg + '\'' +
                ", nickName='" + nickName + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User(String _id, String _openId, String admin, String faceImg, String nickName, String number, String password, String signature) {
        this._id = _id;
        this._openId = _openId;
        this.admin = admin;
        this.faceImg = faceImg;
        this.nickName = nickName;
        this.number = number;
        this.password = password;
        this.signature = signature;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_openId() {
        return _openId;
    }

    public void set_openId(String _openId) {
        this._openId = _openId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
