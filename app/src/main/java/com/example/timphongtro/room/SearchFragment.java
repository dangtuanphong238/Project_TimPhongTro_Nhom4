package com.example.timphongtro.room;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class SearchFragment extends Fragment {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<RoomModel> arrayList;
    RoomAdapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layoutFragment = null;
        layoutFragment = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = layoutFragment.findViewById( R.id.myRecyclerSearch );
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
        arrayList = new ArrayList<RoomModel>();
        reference = FirebaseDatabase.getInstance().getReference().child("RoomInfo");
        reference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1:snapshot.getChildren()){
                    RoomModel searchModel=dataSnapshot1.getValue(RoomModel.class);
                    arrayList.add(searchModel);
                }
                adapter = new RoomAdapter( getContext(),arrayList );
                recyclerView.setAdapter( adapter );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText( getActivity(),"Something us wrong",Toast.LENGTH_SHORT ).show();
            }
        } );
        return layoutFragment;
    }

}
