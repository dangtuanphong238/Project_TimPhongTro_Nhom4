package post_room;

public class model {

    private String sLoaiPhong;
    private int iSucChua;
    private String sGioiTinh;
    private double dDienTich;
    private double dChiPhi;

    public model() {
    }

    public model(String sLoaiPhong, int iSucChua, String sGioiTinh, double dDienTich, double dChiPhi) {
        this.sLoaiPhong = sLoaiPhong;
        this.iSucChua = iSucChua;
        this.sGioiTinh = sGioiTinh;
        this.dDienTich = dDienTich;
        this.dChiPhi = dChiPhi;
    }

    public String getLoaiPhong()
    {
        return sLoaiPhong;
    }
    public String getGioiTinh()
    {
        return sGioiTinh;
    }
    public int getSucChua()
    {
        return iSucChua;
    }
    public double getDienTich()
    {
        return dDienTich;
    }
    public double getChiPhi()
    {
        return dChiPhi;
    }


}
