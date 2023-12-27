package com.example.tgdt.Model;

public class model_giohang {
    String sdt;
    String diachi;
    String tenhang;
    String giahang;
    String sdt_nhanhang;
    String loaihang;
    String url;
    String mota;

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public model_giohang(String mota) {
        this.mota = mota;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public String getGiahang() {
        return giahang;
    }

    public void setGiahang(String giahang) {
        this.giahang = giahang;
    }

    public String getSdt_nhanhang() {
        return sdt_nhanhang;
    }

    public void setSdt_nhanhang(String sdt_nhanhang) {
        this.sdt_nhanhang = sdt_nhanhang;
    }

    public String getLoaihang() {
        return loaihang;
    }

    public void setLoaihang(String loaihang) {
        this.loaihang = loaihang;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public model_giohang(String sdt, String diachi, String tenhang, String giahang, String sdt_nhanhang, String loaihang, String url) {
        this.sdt = sdt;
        this.diachi = diachi;
        this.tenhang = tenhang;
        this.giahang = giahang;
        this.sdt_nhanhang = sdt_nhanhang;
        this.loaihang = loaihang;
        this.url = url;
    }

    public model_giohang() {

    }


}
