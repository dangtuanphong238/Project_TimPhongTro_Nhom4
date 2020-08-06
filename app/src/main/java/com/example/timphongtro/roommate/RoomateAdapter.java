package com.example.timphongtro.roommate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testgooglelogin.R;

import java.util.ArrayList;

public class RoomateAdapter extends RecyclerView.Adapter<RoomateAdapter.MyViewHolder> {

    Context context;
    ArrayList<RoomateModel> arrayList;
    TextView txtName, txtGender, txtAddress, txtAge, txtTinhTrang;
    ImageView imgRoomate;
    RecyclerView recyclerView;
    CardView cardView;

    //constructor
    public RoomateAdapter(Context c, ArrayList<RoomateModel> arr) {
        context = c;
        arrayList = arr;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtNameRoomate);
            txtAddress = itemView.findViewById(R.id.txtAddressRoomate);
            txtGender = itemView.findViewById(R.id.txtGenderRoomate);
            txtAge = itemView.findViewById(R.id.txtAgeRoomate);
            txtTinhTrang = itemView.findViewById(R.id.txtTinhTrangPhong);
            imgRoomate = itemView.findViewById(R.id.imgRoommate);
            recyclerView = itemView.findViewById(R.id.myRecycler);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.cardview_o_ghep, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoRoommate.class);
                //get position khi đã click ở dưới
                int pos = (int) v.getTag();

                intent.putExtra("name", arrayList.get(pos).getTen());
                intent.putExtra("gioitinh", arrayList.get(pos).getGioitinh());
                intent.putExtra("tuoi", arrayList.get(pos).getTuoi());
                intent.putExtra("diachi", arrayList.get(pos).getDiachi());
                intent.putExtra("tinhtrangphong", arrayList.get(pos).getTinhtrang());
//                intent.putExtra("image", arrayList.get(pos).getPicture());


//                RoomateModel roomate = new RoomateModel(arrayList.get(pos).getTinhtrang(),arrayList.get(pos).getGioitinh(),arrayList.get(pos).getTen()
//                ,arrayList.get(pos).getTuoi(),arrayList.get(pos).getDiachi(),arrayList.get(pos).getPicture());
//                intent.putExtra("roommate", roomate);
//                context.startActivity(intent);

                if (arrayList.get(pos).getPicture() != null) {
                    byte[] decodedString = Base64.decode(arrayList.get(pos).getPicture(), Base64.DEFAULT);
                    intent.putExtra("decoded",decodedString);
                }
                context.startActivity(intent);

            }
        });

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        txtTinhTrang.setText("Tình Trạng: " + arrayList.get(position).getTinhtrang());
        txtAge.setText("Tuổi: " + arrayList.get(position).getTuoi());
        txtGender.setText("Giới Tính: " + arrayList.get(position).getGioitinh());
        txtAddress.setText("Địa Chỉ: " + arrayList.get(position).getDiachi());
        txtName.setText("Tên: " + arrayList.get(position).getTen());
        if (arrayList.get(position).getPicture() != null) {
            byte[] decodedString = Base64.decode(arrayList.get(position).getPicture(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imgRoomate.setImageBitmap(decodedByte);
        }
        //set position khi đc click
        cardView.setTag(position);

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
