package com.example.bt2_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<NhanVien> nhanVienList;
    private List<PhongBan> phongBanList;

    public MyAdapter(Context context, List<NhanVien> nhanVienList, List<PhongBan> phongBanList) {
        this.context = context;
        this.nhanVienList = nhanVienList;
        this.phongBanList = phongBanList;
    }

    @Override
    public int getCount() {
        return nhanVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanVienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_nhanvien, parent, false);
        }

        TextView txtInfo = convertView.findViewById(R.id.txtInfo);

        NhanVien nhanVien = nhanVienList.get(position);

        // Lấy tên phòng ban dựa trên mã phòng ban
        String tenPhongBan = "";
        for (PhongBan phongBan : phongBanList) {
            if (phongBan.getMaPhongBan().equals(nhanVien.getMaPhongBan())) {
                tenPhongBan = phongBan.getTenPhongBan();
                break;
            }
        }

        String displayText = tenPhongBan + ": " + nhanVien.getMaNV() + ": " + nhanVien.getTenNV() + ": " + nhanVien.getTuoi();
        txtInfo.setText(displayText);

        return convertView;
    }
}
