package com.example.timphongtro;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testgooglelogin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OGhepFragment extends Fragment {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<RoomateModel> arrayList;
    RoomateAdapter adapter;
    public OGhepFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layoutFragment = null;
        layoutFragment = inflater.inflate(R.layout.fragment_oghep, container, false);

        recyclerView = layoutFragment.findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        arrayList = new ArrayList<RoomateModel>();

        reference = FirebaseDatabase.getInstance().getReference().child("RoomatesInfo");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    RoomateModel roomateModel = dataSnapshot1.getValue(RoomateModel.class);
                    arrayList.add(roomateModel);
                }
                adapter = new RoomateAdapter(getContext(),arrayList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Opsss...something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
        return layoutFragment;
    }

}
