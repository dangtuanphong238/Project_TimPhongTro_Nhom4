package post_room;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testgooglelogin.R;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostRoomActivity extends AppCompatActivity {
    RadioButton rdKTX,rdPhongChoThue,rdPhongOGhep, rdNhaNguyenCan, rdTatCa, rdNam, rdNu;
    EditText edtSucChua, edtDienTich, edtChiPhi;
    Button btnDang;
    DatabaseReference ref;
    private FirebaseRecyclerOptions<model> options;
    private FirebaseRecyclerAdapter<model, MyViewHolder>adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_room);

        anhXa();

//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ref = FirebaseDatabase.getInstance().getReference().child("Roomss");

        btnDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                String key = ref.push().getKey();
                ref.child(key).child("loaiphong").setValue(loaiPhong);
                ref.child(key).child("succhua").setValue(sucChua);
                ref.child(key).child("gioitinh").setValue(gioiTinh);
                ref.child(key).child("dientich").setValue(dienTich);
                ref.child(key).child("chiphi").setValue(chiPhi);
            }
        });

//        options = new FirebaseRecyclerOptions.Builder<model>().setQuery(ref, model.class).build();
//        adapter = new FirebaseRecyclerAdapter<model, MyViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull model model) {
//  //              holder.txtName.setText(model.getName());
//  //             holder.txtAge.setText(model.getAge());
//                holder.edtDienTich.setText((int) model.getDienTich());
//                holder.edtSucChua.setText(model.getSucChua());
//                holder.edtChiPhi.setText((int) model.getChiPhi());
//
//            }
//
//            @NonNull
//            @Override
//            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout,parent,false);
//                return new MyViewHolder(v);
//            }
//        };
//
//        adapter.startListening();
//        recyclerView.setAdapter(adapter);
    }



    private void anhXa()
    {

        rdKTX = findViewById(R.id.rdKTX);
        rdPhongChoThue = findViewById(R.id.rdPhongChoThue);
        rdPhongOGhep = findViewById(R.id.rdPhongOGhep);
        rdNhaNguyenCan = findViewById(R.id.rdNhaNguyenCan);
        edtChiPhi = findViewById(R.id.edtChiPhi);
        edtDienTich = findViewById(R.id.edtDienTich);
        edtSucChua = findViewById(R.id.edtSucChua);
        rdTatCa = findViewById(R.id.rdTatCa);
        rdNam = findViewById(R.id.rdNam);
        rdNu = findViewById(R.id.rdNu);
        btnDang = findViewById(R.id.btnDang);
    }


}
