package post_room;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testgooglelogin.R;

class MyViewHolder extends RecyclerView.ViewHolder {
    RadioButton rdKTX,rdPhongChoThue,rdPhongOGhep, rdNhaNguyenCan, rdTatCa, rdNam, rdNu;
    EditText edtSucChua, edtDienTich, edtChiPhi;
    Button btnDang;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        rdKTX = itemView.findViewById(R.id.rdKTX);
        rdPhongChoThue = itemView.findViewById(R.id.rdPhongChoThue);
        rdPhongOGhep = itemView.findViewById(R.id.rdPhongOGhep);
        rdNhaNguyenCan = itemView.findViewById(R.id.rdNhaNguyenCan);
        edtChiPhi = itemView.findViewById(R.id.edtChiPhi);
        edtDienTich = itemView.findViewById(R.id.edtDienTich);
        edtSucChua = itemView.findViewById(R.id.edtSucChua);
        rdTatCa = itemView.findViewById(R.id.rdTatCa);
        rdNam = itemView.findViewById(R.id.rdNam);
        rdNu = itemView.findViewById(R.id.rdNu);
        btnDang = itemView.findViewById(R.id.btnDang);

    }
}
