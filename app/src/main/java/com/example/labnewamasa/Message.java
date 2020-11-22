package com.example.labnewamasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.labnewamasa.Database.DbHandler;

import java.util.ArrayList;
import java.util.List;

public class Message extends AppCompatActivity {
    TextView Subject;
    EditText Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Subject=findViewById(R.id.Subject);
        Message=findViewById(R.id.MessageView);
        Intent i1= getIntent();

        DbHandler DBMs=new DbHandler(getApplicationContext());
        List MessageDetails=new ArrayList();
        MessageDetails=DBMs.GetMEssageDeatils(i1.getStringExtra("ID").toString());
        Subject.setText(MessageDetails.get(0).toString());
        Message.setText(MessageDetails.get(1).toString());


    }
}