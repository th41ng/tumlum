package com.example.bt2_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class MyDatabase {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public MyDatabase(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    // Thêm nhân viên
    public boolean insertNhanVien(NhanVien nhanVien) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_MA_NV, nhanVien.getMaNV());
        values.put(DBHelper.COLUMN_MA_PB, nhanVien.getMaPhongBan());
        values.put(DBHelper.COLUMN_TEN_NV, nhanVien.getTenNV());
        values.put(DBHelper.COLUMN_TUOI, nhanVien.getTuoi());

        long result = database.insert(DBHelper.TABLE_NHANVIEN, null, values);
        return result != -1; // Trả về true nếu thành công, false nếu thất bại
    }

    public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> nhanVienList = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_NHANVIEN, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int maNVIndex = cursor.getColumnIndex(DBHelper.COLUMN_MA_NV);
                int maPBIndex = cursor.getColumnIndex(DBHelper.COLUMN_MA_PB);
                int tenNVIndex = cursor.getColumnIndex(DBHelper.COLUMN_TEN_NV);
                int tuoiIndex = cursor.getColumnIndex(DBHelper.COLUMN_TUOI);

                if (maNVIndex != -1 && maPBIndex != -1 && tenNVIndex != -1 && tuoiIndex != -1) {
                    String maNV = cursor.getString(maNVIndex);
                    String maPB = cursor.getString(maPBIndex);
                    String tenNV = cursor.getString(tenNVIndex);
                    int tuoi = cursor.getInt(tuoiIndex);

                    NhanVien nhanVien = new NhanVien(maNV, maPB, tenNV, tuoi);
                    nhanVienList.add(nhanVien);
                } else {
                    Log.e("MyDatabase", "Lỗi: Không tìm thấy cột trong bảng NhanVien");
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return nhanVienList;
    }

    public ArrayList<NhanVien> getNhanVienByPhongBan(String maPhongBan) {
        ArrayList<NhanVien> nhanVienList = new ArrayList<>();
        // Sử dụng maPhongBan trong điều kiện WHERE
        Cursor cursor = database.query(DBHelper.TABLE_NHANVIEN, null, DBHelper.COLUMN_MA_PB + "=?", new String[]{maPhongBan}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int maNVIndex = cursor.getColumnIndex(DBHelper.COLUMN_MA_NV);
                int maPBIndex = cursor.getColumnIndex(DBHelper.COLUMN_MA_PB);
                int tenNVIndex = cursor.getColumnIndex(DBHelper.COLUMN_TEN_NV);
                int tuoiIndex = cursor.getColumnIndex(DBHelper.COLUMN_TUOI);

                if (maNVIndex != -1 && maPBIndex != -1 && tenNVIndex != -1 && tuoiIndex != -1) {
                    String maNV = cursor.getString(maNVIndex);
                    String maPB = cursor.getString(maPBIndex);
                    String tenNV = cursor.getString(tenNVIndex);
                    int tuoi = cursor.getInt(tuoiIndex);

                    NhanVien nhanVien = new NhanVien(maNV, maPB, tenNV, tuoi);
                    nhanVienList.add(nhanVien);
                } else {
                    Log.e("MyDatabase", "Lỗi: Không tìm thấy cột trong bảng NhanVien");
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return nhanVienList;
    }

    public ArrayList<PhongBan> getPhongBanList() {
        ArrayList<PhongBan> phongBanList = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_PHONGBAN, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int maPBIndex = cursor.getColumnIndex(DBHelper.COLUMN_MA_PB);
                int tenPBIndex = cursor.getColumnIndex(DBHelper.COLUMN_TEN_PB);
                int soPBIndex = cursor.getColumnIndex(DBHelper.COLUMN_SO_PB);

                if (maPBIndex != -1 && tenPBIndex != -1 && soPBIndex != -1) {
                    String maPB = cursor.getString(maPBIndex);
                    String tenPB = cursor.getString(tenPBIndex);
                    String soPB = cursor.getString(soPBIndex);

                    PhongBan phongBan = new PhongBan(maPB, tenPB, soPB);
                    phongBanList.add(phongBan);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return phongBanList;
    }

    // Xóa nhân viên
    public boolean deleteNhanVien(String maNV) {
        return database.delete(DBHelper.TABLE_NHANVIEN, DBHelper.COLUMN_MA_NV + "=?", new String[]{maNV}) > 0;
    }
}
