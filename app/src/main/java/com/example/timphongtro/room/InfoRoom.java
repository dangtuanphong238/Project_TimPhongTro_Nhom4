package com.example.timphongtro.room;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testgooglelogin.R;

public class InfoRoom extends AppCompatActivity {
    TextView txtChiPhiInfoSearch,txtDiaChiInfoSearch,txtDienTichInfoSearch,txtGioiTinhInfoSearch,txtLoaiPhongInfoSearch,txtSucChuaInfoSearch;
    ImageView imgInfoSearch;
    private RoomModel searchModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_info_search );
        anhXa();
        Bundle extras = getIntent().getExtras();
        txtChiPhiInfoSearch.setText( extras.getDouble( "chiphi" ) +"");
        txtDiaChiInfoSearch.setText( extras.getString( "diachi" ) );
        txtDienTichInfoSearch.setText( extras.getDouble( "dientich" ) + "");
        txtGioiTinhInfoSearch.setText( extras.getString( "gioitinh" ) );
        txtLoaiPhongInfoSearch.setText( extras.getString( "loaiphong" ) );
        txtSucChuaInfoSearch.setText( extras.getInt( "succhua" )+"" );

        byte[] decoded = extras.getByteArray("decoded");
        Bitmap decodeByte = BitmapFactory.decodeByteArray( decoded,0,decoded.length );
        imgInfoSearch.setImageBitmap( decodeByte );


    }
    private void setUpSearchRoom(){
        txtChiPhiInfoSearch.setText( searchModel.getChiphi() +"");
        txtDiaChiInfoSearch.setText( searchModel.getDiachi() );
        txtDienTichInfoSearch.setText( searchModel.getDientich()+"" );
        txtGioiTinhInfoSearch.setText( searchModel.getGioitinh() );
        txtLoaiPhongInfoSearch.setText( searchModel.getLoaiphong() );
        txtSucChuaInfoSearch.setText( searchModel.getSucchua() );
    }
    private void anhXa(){
        txtChiPhiInfoSearch = findViewById( R.id.txtChiPhiInfoSearch );
        txtDiaChiInfoSearch = findViewById( R.id.txtDiaChiInfoSearch );
        txtDienTichInfoSearch = findViewById( R.id.txtDienTichInfoSearch );
        txtGioiTinhInfoSearch = findViewById( R.id.txtGioiTinhInfoSearch );
        txtLoaiPhongInfoSearch = findViewById( R.id.txtLoaiPhongInfoSearch );
        txtSucChuaInfoSearch = findViewById( R.id.txtSucChuaInfoSearch );
        imgInfoSearch = findViewById( R.id.imgInfoSearch );
    }
}
