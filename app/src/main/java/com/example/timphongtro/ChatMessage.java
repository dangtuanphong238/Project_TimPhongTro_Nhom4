package com.example.timphongtro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testgooglelogin.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ChatMessage extends AppCompatActivity {
    FloatingActionButton btnSend;
    EditText edtMessage;
    ListView lvChat;
    private FirebaseListAdapter<Chat> adapter;

//    ArrayAdapter<String> arrayAdapter;
//    ArrayList<String> list_of_rooms = new ArrayList<>();
//    private String name;
//    private DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference().getRoot();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);

        btnSend = findViewById(R.id.btnSend);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),0
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(this,
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();

            // Load chat room contents
            displayChatMessages();
        }

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMessage = findViewById(R.id.edtMessage);
                FirebaseDatabase.getInstance()
                        .getReference()
                        .push()
                        .setValue(new Chat(edtMessage.getText().toString(),
                                FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getDisplayName())
                        );

                // Clear the input
                edtMessage.setText("");
            }
        });
//        btnSend = findViewById(R.id.btnSend);
//        edtMessage = findViewById(R.id.edtMessage);
//        lvChat = findViewById(R.id.lvChat);
//
//        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_of_rooms);
//        lvChat.setAdapter(arrayAdapter);
//
//        request_user_name();
//
//        btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Map<String,Object> map = new HashMap<String, Object>();
//                map.put(edtMessage.getText().toString(),"");
//                dataRef.updateChildren(map);
//            }
//        });
//
//        dataRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Set<String> set = new HashSet<>();
//                Iterator i = dataSnapshot.getChildren().iterator();
//                while (i.hasNext())
//                {
//                    set.add(((DataSnapshot)i.next()).getKey());
//                }
//                list_of_rooms.clear();
//                list_of_rooms.addAll(set);
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        lvChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                intent.putExtra("room_name",((TextView)view).getText().toString() );
//                intent.putExtra("user_name",name);
//                startActivity(intent);
//            }
//        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode != 0) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Successfully signed in. Welcome!",
                        Toast.LENGTH_LONG)
                        .show();
                displayChatMessages();
            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();

                // Close the app
                finish();
            }
        }
    }

    private void displayChatMessages() {
        lvChat = findViewById(R.id.lvChat);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Roomb  ");

//        Query query = FirebaseDatabase.getInstance().getReference().child("chats");
        FirebaseListOptions<Chat> options = new FirebaseListOptions.Builder<Chat>()
                .setQuery(databaseRef, Chat.class)
                .setLayout(R.layout.message)
                .build();
        //adapter = new FirebaseListAdapter<Chat>(this, Chat.class,R.layout.message, FirebaseDatabase.getInstance().getReference()) {
            adapter = new FirebaseListAdapter<Chat>(options) {
                @Override
                protected void populateView(@NonNull View v, @NonNull Chat model, int position) {

                    // Get references to the views of message.xml
                    TextView messageText = (TextView) v.findViewById(R.id.message_text);
                    Toast.makeText(ChatMessage.this, messageText.toString(), Toast.LENGTH_LONG).show();
                    TextView messageUser = (TextView) v.findViewById(R.id.message_user);
                    TextView messageTime = (TextView) v.findViewById(R.id.message_time);

                    // Set their text
                    messageText.setText(model.getMessageText());
                    messageUser.setText(model.getMessageUser());

                    // Format the date before showing it
                    messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                            model.getMessageTime()));
                }
            };

        lvChat.setAdapter(adapter);
    }


//    private void request_user_name() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Nhập vào tên:");
//        final EditText input_field = new EditText(this);
//        builder.setView(input_field);
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                name = input_field.getText().toString();
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//                request_user_name();
//            }
//        });
//        builder.show();
//    }
}
