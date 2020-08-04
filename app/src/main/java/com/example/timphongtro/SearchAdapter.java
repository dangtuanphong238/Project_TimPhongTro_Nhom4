package com.example.timphongtro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testgooglelogin.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    Context context;
    ArrayList<SearchModel> arrayList;
    CardView cardviewSearch;
    RecyclerView recyclerView;
    public SearchAdapter(Context c, ArrayList<SearchModel> arr)
    {
        context = c;
        arrayList = arr;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtChiPhi,txtDiaChi,txtDienTich,txtLoaiPhong,txtGioiTinh,txtSucChua;
        ImageView imgSearch;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtChiPhi = itemView.findViewById( R.id.txtChiPhi);
            txtDiaChi = itemView.findViewById( R.id.txtDiaChi);
            txtDienTich = itemView.findViewById( R.id.txtDienTich);
            txtLoaiPhong = itemView.findViewById( R.id.txtLoaiPhong);
            txtGioiTinh = itemView.findViewById( R.id.txtGioiTinh);
            imgSearch = itemView.findViewById( R.id.imgSearch);
            txtSucChua = itemView.findViewById( R.id.txtSucChua);
            recyclerView = itemView.findViewById( R.id.myRecyclerSearch );
            cardviewSearch = itemView.findViewById( R.id.cardviewSearch );
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from( context).inflate( R.layout.cardviewsearch ,parent,false);
        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, InfoSearch.class );
                int pos = (int) v.getTag();

                intent.putExtra( "chiphi",arrayList.get(pos).getChiphi() );
                intent.putExtra( "diachi",arrayList.get( pos ).getDiachi() );
                intent.putExtra( "dientich",arrayList.get(pos).getDientich() );
                intent.putExtra( "gioitinh",arrayList.get( pos ).getGioitinh() );
                intent.putExtra( "loaiphong",arrayList.get(pos).getLoaiphong() );
                intent.putExtra( "succhua",arrayList.get( pos ).getSucchua() );
                if(arrayList.get( pos ).getPicture()!= null){
                    byte[] decodedString = Base64.decode( arrayList.get(pos).getPicture(),Base64.DEFAULT );
                    intent.putExtra( "decoded",decodedString );
                }
                context.startActivity(intent);
            }

        } );
        return new MyViewHolder(view);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtChiPhi.setText("Chi Phí: " + arrayList.get(position).getChiphi());
        holder.txtDiaChi.setText("Địa Chỉ: " + arrayList.get(position).getDiachi());
        holder.txtDienTich.setText("Diện Tích: " + arrayList.get(position).getDientich());
        holder.txtLoaiPhong.setText("Loại Phòng: " + arrayList.get(position).getLoaiphong());
        holder.txtGioiTinh.setText("Giới Tính: " + arrayList.get(position).getGioitinh());
        if(arrayList.get(position).getPicture() != null) {
            byte[] decodedString = Base64.decode(arrayList.get(position).getPicture(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.imgSearch.setImageBitmap(decodedByte);
        }
        holder.txtSucChua.setText("Sức Chứa: " + arrayList.get(position).getSucchua());
        cardviewSearch.setTag(position);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



}
