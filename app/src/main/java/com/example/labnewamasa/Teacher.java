package com.example.labnewamasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labnewamasa.Database.DbHandler;

public class Teacher extends AppCompatActivity {
    TextView Wel;
    EditText Subject, Message;
    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Wel=findViewById(R.id.WelcomeString);
        String x;
        Intent PassedIntent = getIntent();
        String Myname =PassedIntent.getStringExtra("name").toString();
        x= "Welcome "+ PassedIntent.getStringExtra("name").toString();
        Wel.setText(x);
        Subject=findViewById(R.id.EditTextSubject);
        Message=findViewById(R.id.EditTextMessage);
        Save=findViewById(R.id.button4);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImalshiGobbya(Myname);
            }
        });
    }

    public void ImalshiGobbya(String username){
        Subject=findViewById(R.id.EditTextSubject);
        Message=findViewById(R.id.EditTextMessage);

        DbHandler DBMS= new DbHandler(getApplicationContext());
        long exc=DBMS.SaveMessage(username,Message.getText().toString(),Subject.getText().toString());
        if(exc >0){
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Imlashi Is Gobbaya", Toast.LENGTH_SHORT).show();
        }
    }
}