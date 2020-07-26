package com.example.timphongtro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testgooglelogin.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RoomateAdapter extends RecyclerView.Adapter<RoomateAdapter.MyViewHolder> {

    Context context;
    ArrayList<RoomateModel> arrayList;

    //constructor
    public RoomateAdapter(Context c, ArrayList<RoomateModel> arr)
    {
        context = c;
        arrayList = arr;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtName,txtGender,txtAddress,txtAge,txtTinhTrang;
        ImageView imgRoomate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtNameRoomate);
            txtAddress = itemView.findViewById(R.id.txtAddressRoomate);
            txtGender = itemView.findViewById(R.id.txtGenderRoomate);
            txtAge = itemView.findViewById(R.id.txtAgeRoomate);
            txtTinhTrang = itemView.findViewById(R.id.txtTinhTrangPhong);
            imgRoomate = itemView.findViewById(R.id.imgRoommate);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtTinhTrang.setText("Tình Trạng: " + arrayList.get(position).getTinhtrang());
        holder.txtAge.setText("Tuổi: " + arrayList.get(position).getTuoi());
        holder.txtGender.setText("Giới Tính: " + arrayList.get(position).getGioitinh());
        holder.txtAddress.setText("Địa Chỉ: " + arrayList.get(position).getDiachi());
        holder.txtName.setText("Tên: " + arrayList.get(position).getTen());
        if(arrayList.get(position).getPicture() != null) {
            byte[] decodedString = Base64.decode(arrayList.get(position).getPicture(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.imgRoomate.setImageBitmap(decodedByte);
        }
//        Picasso.get().load(arrayList.get(position).getImage()).into(holder.imgRoomate);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
