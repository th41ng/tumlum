package com.example.bt2_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QLNV.db";
    private static final int DATABASE_VERSION = 1;

    // Bảng nhân viên
    public static final String TABLE_NHANVIEN = "NhanVien";
    public static final String COLUMN_MA_NV = "maNV";
    public static final String COLUMN_MA_PB_NV = "maPhongBan";
    public static final String COLUMN_TEN_NV = "tenNV";
    public static final String COLUMN_TUOI = "tuoi";

    // Bảng phòng ban
    public static final String TABLE_PHONGBAN = "PhongBan";
    public static final String COLUMN_MA_PB = "maPhongBan";
    public static final String COLUMN_TEN_PB = "tenPhongBan";
    public static final String COLUMN_SO_PB = "soPhongBan";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true); // Kích hoạt kiểm tra khóa ngoại
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng PhongBan
        String CREATE_TABLE_PHONGBAN = "CREATE TABLE " + TABLE_PHONGBAN + " (" +
                COLUMN_MA_PB + " TEXT PRIMARY KEY, " +
                COLUMN_TEN_PB + " TEXT, " +
                COLUMN_SO_PB + " TEXT)";
        db.execSQL(CREATE_TABLE_PHONGBAN);

        // Tạo bảng NhanVien
        String CREATE_TABLE_NHANVIEN = "CREATE TABLE " + TABLE_NHANVIEN + " (" +
                COLUMN_MA_NV + " TEXT PRIMARY KEY, " +
                COLUMN_MA_PB_NV + " TEXT, " +
                COLUMN_TEN_NV + " TEXT, " +
                COLUMN_TUOI + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_MA_PB + ") REFERENCES " + TABLE_PHONGBAN + "(" + COLUMN_MA_PB + ") " +
                "ON DELETE CASCADE ON UPDATE CASCADE)";
        db.execSQL(CREATE_TABLE_NHANVIEN);

        // Chèn dữ liệu mẫu
        insertPhongBanSampleData(db);
    }

    private void insertPhongBanSampleData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + TABLE_PHONGBAN + " (" +
                COLUMN_MA_PB + ", " + COLUMN_TEN_PB + ", " + COLUMN_SO_PB + ") VALUES " +
                "('PB01', 'Phòng Kỹ Thuật', '101')," +
                "('PB02', 'Phòng Marketing', '102')," +
                "('PB03', 'Phòng Nhân Sự', '103')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NHANVIEN); // Xóa bảng NhanVien trước
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHONGBAN); // Xóa bảng PhongBan sau
        onCreate(db);
    }
}
