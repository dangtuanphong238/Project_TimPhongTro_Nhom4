package com.example.timphongtro;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.testgooglelogin.R;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaiKhoanFragment extends Fragment {
    Button btnLogout;
    FirebaseAuth mAuth;

    public TaiKhoanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        // Inflate the layout for this fragment
        btnLogout = fragmentLayout.findViewById(R.id.btnlogout);
        mAuth = FirebaseAuth.getInstance();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity().getApplication(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return fragmentLayout;
    }


}
