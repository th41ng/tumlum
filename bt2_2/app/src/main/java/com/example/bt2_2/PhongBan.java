package com.example.bt2_2;

import androidx.annotation.NonNull;

public class PhongBan {
    private String maPhongBan;
    private String tenPhongBan;
    private String soPhongBan;

    public PhongBan(String maPhongBan, String tenPhongBan, String soPhongBan) {
        this.maPhongBan = maPhongBan;
        this.tenPhongBan = tenPhongBan;
        this.soPhongBan = soPhongBan;
    }

    @NonNull
    @Override
    public String toString() {
        return this.tenPhongBan;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public String getSoPhongBan() {
        return soPhongBan;
    }
}
