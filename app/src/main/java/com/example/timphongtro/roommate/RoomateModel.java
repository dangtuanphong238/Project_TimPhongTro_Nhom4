package com.example.timphongtro.roommate;

import java.io.Serializable;

public class RoomateModel implements Serializable {


    private String tinhtrang;
    private String gioitinh;
    private String ten;
    private int tuoi;
    private String diachi;
    private String picture;
   // private String image;


    public RoomateModel() {
    }

    public RoomateModel(String tinhtrang, String gioitinh, String ten, int tuoi, String diachi, String picture) {
        this.tinhtrang = tinhtrang;
        this.gioitinh = gioitinh;
        this.ten = ten;
        this.tuoi = tuoi;
        this.diachi = diachi;
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
