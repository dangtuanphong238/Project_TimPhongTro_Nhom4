package com.example.timphongtro;

public class RoomateModel {


    private String tinhtrang;
    private String gioitinh;
    private String ten;
    private String tuoi;
    private String diachi;
   // private String image;


    public RoomateModel() {
    }

    public RoomateModel(String tinhtrang, String gioitinh, String ten, String tuoi, String diachi) {
        this.tinhtrang = tinhtrang;
        this.gioitinh = gioitinh;
        this.ten = ten;
        this.tuoi = tuoi;
        this.diachi = diachi;
    }

    //    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }



    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTuoi() {
        return tuoi;
    }

    public void setTuoi(String tuoi) {
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
