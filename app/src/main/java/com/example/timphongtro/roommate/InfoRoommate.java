package com.example.timphongtro.roommate;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testgooglelogin.R;
import com.example.timphongtro.chat.ChatActivity;


public class InfoRoommate extends AppCompatActivity {
    TextView txtNameRM, txtGioiTinhRM, txtTuoiRM, txtDiaChiRM, txtTinhTrangPhong;
    ImageView imgRM;
    Button btnChat;
    private RoomateModel roomate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_roommate);
        anhXa();

        Bundle extras = getIntent().getExtras();
        txtNameRM.setText(extras.getString("name"));
        txtTuoiRM.setText(extras.getInt("tuoi") + "");
        txtGioiTinhRM.setText(extras.getString("gioitinh"));
        txtDiaChiRM.setText(extras.getString("diachi"));
        txtTinhTrangPhong.setText(extras.getString("tinhtrangphong"));

        byte[] decoded = extras.getByteArray("decoded");
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
        imgRM.setImageBitmap(decodedByte);

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoRoommate.this, ChatActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

    }

    private void setUpRoommate()
    {
        txtNameRM.setText(roomate.getTen());
        txtTuoiRM.setText(roomate.getTuoi());
        txtGioiTinhRM.setText(roomate.getGioitinh());
        txtDiaChiRM.setText(roomate.getDiachi());
        txtTinhTrangPhong.setText(roomate.getTinhtrang());
    }

    private void anhXa()
    {
        txtNameRM = findViewById(R.id.txtNameRoomate);
        txtTuoiRM = findViewById(R.id.txtAgeRoomate);
        txtGioiTinhRM = findViewById(R.id.txtGenderRoomate);
        txtDiaChiRM = findViewById(R.id.txtAddressRoomate);
        txtTinhTrangPhong = findViewById(R.id.txtTinhTrangPhong);
        imgRM = findViewById(R.id.imgInfoRM);
        btnChat = findViewById(R.id.btnChat);
    }

}
