package com.example.timphongtro.room;

public class RoomModel {
    private Double chiphi;
    private String diachi;
    private Double dientich;
    private String gioitinh;
    private String loaiphong;
    private int succhua;
    private String picture;

    public RoomModel() {
    }

    public RoomModel(Double chiphi, String diachi, Double dientich, String gioitinh, String loaiphong, int succhua, String picture) {
        this.chiphi = chiphi;
        this.diachi = diachi;
        this.dientich = dientich;
        this.gioitinh = gioitinh;
        this.loaiphong = loaiphong;
        this.picture = picture;
        this.succhua = succhua;

    }


    public Double getChiphi() {
        return chiphi;
    }

    public void setChiphi(Double chiphi) {
        this.chiphi = chiphi;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Double getDientich() {
        return dientich;
    }

    public void setDientich(Double dientich) {
        this.dientich = dientich;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getLoaiphong() {
        return loaiphong;
    }

    public void setLoaiphong(String loaiphong) {
        this.loaiphong = loaiphong;
    }

    public int getSucchua() {
        return succhua;
    }

    public void setSucchua(int succhua) {
        this.succhua = succhua;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
