package com.example.timphongtro.room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.testgooglelogin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class DangPhongFragment extends Fragment {

    private int PICK_IMAGE_REQUEST_CAP = 100;
    private int PICK_IMAGE_REQUEST_CHOOSE = 200;
    private String LOAIPHONG = "loaiphong";
    private String SUCCHUA = "succhua";
    private String GIOITINH = "gioitinh";
    private String DIENTICH = "dientich";
    private String CHIPHI = "chiphi";
    private String DIACHI = "diachi";
    private String USER_ID = "userid";
    FirebaseAuth mAuth;
    ImageView imgView;
    ImageButton btnChoose,btnCapture;
    Button btnDang;
    RadioButton rdKTX,rdPhongChoThue,rdPhongOGhep, rdNhaNguyenCan, rdTatCa, rdNam, rdNu;
    EditText edtSucChua, edtDienTich, edtChiPhi, edtDiaChi;


    public Uri mImageUri;
    Bitmap selectedBitmap;


    public DangPhongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_dang_phong, container, false);

        //Ánh xạ view
        btnChoose = fragmentLayout.findViewById(R.id.btnChoose);
        btnCapture =fragmentLayout.findViewById(R.id.btnCapture);
        btnDang = fragmentLayout.findViewById(R.id.btnDang);
        imgView = fragmentLayout.findViewById(R.id.imgView);
        rdKTX = fragmentLayout.findViewById(R.id.rdKTX);
        rdPhongChoThue = fragmentLayout.findViewById(R.id.rdPhongChoThue);
        rdPhongOGhep = fragmentLayout.findViewById(R.id.rdPhongOGhep);
        rdNhaNguyenCan = fragmentLayout.findViewById(R.id.rdNhaNguyenCan);
        edtChiPhi = fragmentLayout.findViewById(R.id.edtChiPhi);
        edtDienTich = fragmentLayout.findViewById(R.id.edtDienTich);
        edtSucChua = fragmentLayout.findViewById(R.id.edtSucChua);
        rdTatCa = fragmentLayout.findViewById(R.id.rdTatCa);
        rdNam = fragmentLayout.findViewById(R.id.rdNam);
        rdNu = fragmentLayout.findViewById(R.id.rdNu);
        edtDiaChi = fragmentLayout.findViewById(R.id.edtDiaChi);

        mAuth = FirebaseAuth.getInstance();

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturePicture();
            }
        });

        btnDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    xuLyThemMoi();
                    startActivity(Intent.makeMainActivity(getActivity().getComponentName()));

            }
        });

        return fragmentLayout;
    }


public void xuLyThemMoi() {

    //get data Loai phòng
    String loaiPhong= "";
    if(rdKTX.isChecked()) loaiPhong = rdKTX.getText().toString();
    else if(rdPhongChoThue.isChecked()) loaiPhong = rdPhongChoThue.getText().toString();
    else if(rdPhongOGhep.isChecked()) loaiPhong = rdPhongOGhep.getText().toString();
    else if(rdNhaNguyenCan.isChecked()) loaiPhong = rdNhaNguyenCan.getText().toString();

    //get data Giới tính
    String gioiTinh= "";
    if(rdTatCa.isChecked()) gioiTinh = rdTatCa.getText().toString();
    else if(rdNam.isChecked()) gioiTinh = rdNam.getText().toString();
    else if(rdNu.isChecked()) gioiTinh = rdNu.getText().toString();

    double dienTich = Double.parseDouble(edtDienTich.getText().toString());
    double chiPhi = Double.parseDouble(edtChiPhi.getText().toString());
    int sucChua = Integer.parseInt(edtSucChua.getText().toString());
    String diaChi = edtDiaChi.getText().toString();

    try {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("RoomInfo");
        //Kết nối tới node có tên là contacts (node này do ta định nghĩa trong CSDL Firebase)
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String Uid = firebaseUser.getUid();

        String key = databaseRef.push().getKey();
        databaseRef.child(key).child(USER_ID).setValue(Uid);
        databaseRef.child(key).child(LOAIPHONG).setValue(loaiPhong);
        databaseRef.child(key).child(SUCCHUA).setValue(sucChua);
        databaseRef.child(key).child(GIOITINH).setValue(gioiTinh);
        databaseRef.child(key).child(DIENTICH).setValue(dienTich);
        databaseRef.child(key).child(CHIPHI).setValue(chiPhi);
        databaseRef.child(key).child(DIACHI).setValue(diaChi);

//đưa bitmap về base64string:
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        selectedBitmap.compress(Bitmap.CompressFormat.PNG, PICK_IMAGE_REQUEST_CAP, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imgeEncoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        databaseRef.child(key).child("picture").setValue(imgeEncoded);
        getActivity().finish();

    } catch (Exception ex) {
        Toast.makeText(getActivity(), "Error:" + ex.toString(), Toast.LENGTH_LONG).show();
    }
}


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST_CHOOSE);

    }
    private void capturePicture() {
        Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cInt,PICK_IMAGE_REQUEST_CAP);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST_CAP && resultCode == RESULT_OK)
        {
            //xử lý lấy ảnh trực tiếp lúc chụp hình:
            selectedBitmap = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(selectedBitmap);

        }
        else if(requestCode == PICK_IMAGE_REQUEST_CHOOSE && resultCode == RESULT_OK)
        {
            try {
                mImageUri = data.getData();
                selectedBitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), mImageUri);
                imgView.setImageBitmap(selectedBitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
//                && data != null && data.getData() != null )
//        {
//            try {
//                mImageUri = data.getData();
//                selectedBitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), mImageUri);
//                imgView.setImageBitmap(selectedBitmap);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}