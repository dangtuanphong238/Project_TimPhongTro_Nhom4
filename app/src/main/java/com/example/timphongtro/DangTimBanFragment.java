package com.example.timphongtro;


import android.app.ProgressDialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class DangTimBanFragment extends Fragment {
    private int PICK_IMAGE_REQUEST = 1;
    private String TINH_TRANG = "tinhtrang";
    private String GIOI_TINH = "gioitinh";
    private String TEN = "ten";
    private String TUOI = "tuoi";
    private String DIA_CHI = "diachi";
    private String PICTURE = "picture";

    RadioButton rdCoPhong, rdChuaCoPhong, rdTatCa, rdNam, rdNu;
    EditText edtTen,edtTuoi,edtDiaChi;
    ImageView imgView;
    ImageButton btnChoose;
    Button btnDangTim;

    public Uri mImageUri;
    Bitmap selectedBitmap;
    private ProgressDialog mLoadingBar;
    boolean isCompleted = false;

    public DangTimBanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_dang_tim_ban, container, false);

        rdCoPhong = fragmentLayout.findViewById(R.id.rdDaCoPhong);
        rdChuaCoPhong = fragmentLayout.findViewById(R.id.rdChuaPhong);
        edtTen = fragmentLayout.findViewById(R.id.edtTen);
        edtTuoi = fragmentLayout.findViewById(R.id.edtTuoi);
        edtDiaChi = fragmentLayout.findViewById(R.id.edtDiaChi);
        imgView = fragmentLayout.findViewById(R.id.imgView);
        btnChoose = fragmentLayout.findViewById(R.id.btnChoose);
        btnDangTim = fragmentLayout.findViewById(R.id.btnDangTim);
        rdTatCa = fragmentLayout.findViewById(R.id.rdTatCa);
        rdNam = fragmentLayout.findViewById(R.id.rdNam);
        rdNu = fragmentLayout.findViewById(R.id.rdNu);

        mLoadingBar = new ProgressDialog(getContext());
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        btnDangTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThemMoi();
                if(isCompleted == true)
                {
                    startActivity(Intent.makeMainActivity(getActivity().getComponentName()));
                }
            }
        });
        return fragmentLayout;
    }

    public void xuLyThemMoi() {
        String tinhTrang = "";
        if(rdCoPhong.isChecked()) tinhTrang = rdCoPhong.getText().toString();
        else if(rdChuaCoPhong.isChecked()) tinhTrang = rdChuaCoPhong.getText().toString();

        //get data Giới tính
        String gioiTinh= "";
        if(rdNam.isChecked()) gioiTinh = rdNam.getText().toString();
        else if(rdNu.isChecked()) gioiTinh = rdNu.getText().toString();

        String ten = edtTen.getText().toString();
        int tuoi = Integer.parseInt(edtTuoi.getText().toString());
        String diaChi = edtDiaChi.getText().toString();
        if(ten.isEmpty())
        {
            showError(edtTen, "Your name is not valid");
        }
        else if(tuoi <= 0)
        {
            showError(edtTuoi, "Age is not valid");
        }
        else if(diaChi.isEmpty())
        {
            showError(edtDiaChi, "Address is not valid");
        }
        else {
            mLoadingBar.setTitle("Đang đăng...");
            mLoadingBar.setMessage("Vui lòng chờ trong giây lát...");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();
            try {
                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("RoomatesInfo");
                //Kết nối tới node có tên là contacts (node này do ta định nghĩa trong CSDL Firebase)
                String key = databaseRef.push().getKey();
                databaseRef.child(key).child(TINH_TRANG).setValue(tinhTrang);
                databaseRef.child(key).child(GIOI_TINH).setValue(gioiTinh);
                databaseRef.child(key).child(TEN).setValue(ten);
                databaseRef.child(key).child(TUOI).setValue(tuoi);
                databaseRef.child(key).child(DIA_CHI).setValue(diaChi);

//đưa bitmap về base64string:
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                selectedBitmap.compress(Bitmap.CompressFormat.PNG, PICK_IMAGE_REQUEST, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String imgeEncoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                databaseRef.child(key).child(PICTURE).setValue(imgeEncoded);
                Toast.makeText(getActivity(), "Đăng thành công!", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                isCompleted = true;
            } catch (Exception ex) {
                Toast.makeText(getActivity(), "Error:" + ex.toString(), Toast.LENGTH_LONG).show();
            }
        }


    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
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

}
