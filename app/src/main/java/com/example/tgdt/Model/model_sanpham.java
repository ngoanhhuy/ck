package com.example.tgdt.Model;

public class model_sanpham {
    String Link_anh_sp;
    String Ten_sp;
    String Gia_sp;
    String mota;
    String loaisp;
    String mahang;

    public model_sanpham() {

    }

    public model_sanpham(String link_anh_sp, String ten_sp, String gia_sp, String mota, String loaisp, String mahang) {
        Link_anh_sp = link_anh_sp;
        Ten_sp = ten_sp;
        Gia_sp = gia_sp;
        this.mota = mota;
        this.loaisp = loaisp;
        this.mahang = mahang;
    }

    public String getLink_anh_sp() {
        return Link_anh_sp;
    }

    public void setLink_anh_sp(String link_anh_sp) {
        Link_anh_sp = link_anh_sp;
    }

    public String getTen_sp() {
        return Ten_sp;
    }

    public void setTen_sp(String ten_sp) {
        Ten_sp = ten_sp;
    }

    public String getGia_sp() {
        return Gia_sp;
    }

    public void setGia_sp(String gia_sp) {
        Gia_sp = gia_sp;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }

    public String getMahang() {
        return mahang;
    }

    public void setMahang(String mahang) {
        this.mahang = mahang;
    }
}
