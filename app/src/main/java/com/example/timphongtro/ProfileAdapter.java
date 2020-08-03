package com.example.timphongtro;


import android.app.Activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testgooglelogin.R;



public class ProfileAdapter extends ArrayAdapter<Profile> {
    Activity context;
    int resource;
    public ProfileAdapter(Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View custom=context.getLayoutInflater().inflate(resource,null);
        TextView txtId=custom.findViewById(R.id.txtId);
        TextView txtName=custom.findViewById(R.id.txtName);
        TextView txtPhone=custom.findViewById(R.id.txtPhone);
        TextView txtEmail=custom.findViewById(R.id.txtEmail);
        Profile contact=getItem(position);
        txtId.setText(contact.getContactId());
        txtName.setText(contact.getName());
        txtPhone.setText(contact.getPhone());
        txtEmail.setText(contact.getEmail());
        return custom;
    }
}
