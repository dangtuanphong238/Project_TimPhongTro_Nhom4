package com.example.timphongtro;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testgooglelogin.R;


public class InfoRoommate extends AppCompatActivity {
    TextView txtNameRM, txtGioiTinhRM, txtTuoiRM, txtDiaChiRM, txtTinhTrangPhong;
    ImageView imgRM;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info_roommate);
        anhXa();

        Bundle extras = getIntent().getExtras();
        txtNameRM.setText(extras.getString("name"));
        txtTuoiRM.setText(extras.getString("tuoi"));
        txtGioiTinhRM.setText(extras.getString("gioitinh"));
        txtDiaChiRM.setText(extras.getString("diachi"));
        txtTinhTrangPhong.setText(extras.getString("tinhtrangphong"));



    }

    private void anhXa()
    {
        txtNameRM = findViewById(R.id.txtNameRoomate);
        txtTuoiRM = findViewById(R.id.txtAgeRoomate);
        txtGioiTinhRM = findViewById(R.id.txtGenderRoomate);
        txtDiaChiRM = findViewById(R.id.txtAddressRoomate);
        txtTinhTrangPhong = findViewById(R.id.txtTinhTrangPhong);
        imgRM = findViewById(R.id.imgInfoRM);
    }

}
