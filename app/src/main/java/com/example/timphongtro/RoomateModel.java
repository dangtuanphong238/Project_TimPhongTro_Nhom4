package com.example.timphongtro;

public class RoomateModel {
    private String tinhTrang;
    private String gioiTinh;
    private String ten;
    private String tuoi;
    private String diaChi;
   // private String image;


    public RoomateModel() {
    }


    public RoomateModel(String tinhTrang, String gioiTinh, String ten, String tuoi, String diaChi) {
        this.tinhTrang = tinhTrang;
        this.gioiTinh = gioiTinh;
        this.ten = ten;
        this.tuoi = tuoi;
        this.diaChi = diaChi;
//        this.image = image;
    }

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }


    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
