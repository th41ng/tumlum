package com.example.bt2_2;

import androidx.annotation.NonNull;

public class NhanVien {
    private String maNV;
    private String maPhongBan;
    private String tenNV;
    private int tuoi;

    public NhanVien(String maNV, String maPhongBan, String tenNV, int tuoi) {
        this.maNV = maNV;
        this.maPhongBan = maPhongBan;
        this.tenNV = tenNV;
        this.tuoi = tuoi;
    }

    @NonNull
    @Override
    public String toString() {
        return this.tenNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    public String getTenNV() {
        return tenNV;
    }

    public int getTuoi() {
        return tuoi;
    }
}
