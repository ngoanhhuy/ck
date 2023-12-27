package com.example.tgdt.Model;

public class model_account_sigin {
    private String Username;
    private String Sdt;
    private String Passwork;
    private String loaitk;
    private String avata;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public String getPasswork() {
        return Passwork;
    }

    public void setPasswork(String passwork) {
        Passwork = passwork;
    }

    public String getLoaitk() {
        return loaitk;
    }

    public void setLoaitk(String loaitk) {
        this.loaitk = loaitk;
    }

    public String getAvata() {
        return avata;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }

    public model_account_sigin(String username, String sdt, String passwork, String loaitk, String avata) {
        Username = username;
        Sdt = sdt;
        Passwork = passwork;
        this.loaitk = loaitk;
        this.avata = avata;
    }

    public model_account_sigin() {

    }
}
