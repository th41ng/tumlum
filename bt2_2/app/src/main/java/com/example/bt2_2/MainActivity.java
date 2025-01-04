package com.example.bt2_2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerPhongBan;
    private ListView lvDanhSach;
    private EditText edtMaNV, edtTenNV, edtTuoi;
    private Button btnThem, btnXoa;

    private MyDatabase myDatabase;
    private ArrayList<PhongBan> phongBanList;
    private ArrayList<NhanVien> nhanVienList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerPhongBan = findViewById(R.id.spinnerPhongBan);
        lvDanhSach = findViewById(R.id.lvDanhSach);
        edtMaNV = findViewById(R.id.edtMaNV);
        edtTenNV = findViewById(R.id.edtTenNV);
        edtTuoi = findViewById(R.id.edtTuoi);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);

        myDatabase = new MyDatabase(this);

        // Lấy danh sách phòng ban
        phongBanList = myDatabase.getPhongBanList();

        // Thiết lập Spinner
        ArrayAdapter<PhongBan> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, phongBanList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPhongBan.setAdapter(spinnerAdapter);

        // Lấy danh sách nhân viên theo phòng ban đầu tiên
        if (!phongBanList.isEmpty()) {
            loadNhanVienByPhongBan(phongBanList.get(0).getMaPhongBan());
        }

        spinnerPhongBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String maPhongBan = phongBanList.get(position).getMaPhongBan();
                loadNhanVienByPhongBan(maPhongBan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnThem.setOnClickListener(v -> {
            String maNV = edtMaNV.getText().toString();
            String tenNV = edtTenNV.getText().toString();
            int tuoi = Integer.parseInt(edtTuoi.getText().toString());
            String maPB = ((PhongBan) spinnerPhongBan.getSelectedItem()).getMaPhongBan();

            NhanVien newNhanVien = new NhanVien(maNV, maPB, tenNV, tuoi);

            if (myDatabase.insertNhanVien(newNhanVien)) {
                Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                loadNhanVienByPhongBan(maPB);
            } else {
                Toast.makeText(this, "Lỗi! Mã nhân viên đã tồn tại.", Toast.LENGTH_SHORT).show();
            }
        });

        btnXoa.setOnClickListener(v -> {
            String maNV = edtMaNV.getText().toString();

            if (myDatabase.deleteNhanVien(maNV)) {
                Toast.makeText(this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                String maPB = ((PhongBan) spinnerPhongBan.getSelectedItem()).getMaPhongBan();
                loadNhanVienByPhongBan(maPB);
            } else {
                Toast.makeText(this, "Lỗi! Không tìm thấy nhân viên.", Toast.LENGTH_SHORT).show();
            }
        });

        lvDanhSach.setOnItemClickListener((parent, view, position, id) -> {
            NhanVien nhanVien = nhanVienList.get(position);
            edtMaNV.setText(nhanVien.getMaNV());
            edtTenNV.setText(nhanVien.getTenNV());
            edtTuoi.setText(String.valueOf(nhanVien.getTuoi()));
        });
    }

    private void loadNhanVienByPhongBan(String maPhongBan) {
        nhanVienList = myDatabase.getNhanVienByPhongBan(maPhongBan);
        myAdapter = new MyAdapter(this, nhanVienList, phongBanList);
        lvDanhSach.setAdapter(myAdapter);
    }
}
