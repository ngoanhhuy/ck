package com.example.tgdt.Model;

public class Employee {
    private String loaisp;

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }

    public Employee(String loaisp) {
        this.loaisp = loaisp;
    }
    @Override
    public String toString() {
        return this.getLoaisp();
    }
}
