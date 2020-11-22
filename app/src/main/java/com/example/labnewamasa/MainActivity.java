package com.example.labnewamasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labnewamasa.Database.DbHandler;

public class MainActivity extends AppCompatActivity {
    Button RegisterButton,LoginButton;
    EditText Username,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RegisterButton=findViewById(R.id.RegisterButton);
        Username=findViewById(R.id.EditTextUserName);
        Password=findViewById(R.id.EditTextpassowrd);
        LoginButton=findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckUserLoginDetails();
            }
        });
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChangeActivity= new Intent(getApplicationContext(),Register.class);
                startActivity(ChangeActivity);

            }
        });
    }

    public void CheckUserLoginDetails(){
        Username=findViewById(R.id.EditTextUserName);
        Password=findViewById(R.id.EditTextpassowrd);
        DbHandler DMBS= new DbHandler(getApplicationContext());
        String USerTyep=DMBS.CheckUserLoginDetails(Username.getText().toString(),Password.getText().toString());
        if(USerTyep.equals("null")){
            Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }else if(USerTyep.equals("Teacher")){
            Toast.makeText(this, "Logged In as a teacher", Toast.LENGTH_SHORT).show();
            Intent i1 = new Intent(getApplicationContext(),Teacher.class);
            i1.putExtra("name",Username.getText().toString());
            startActivity(i1);

        }else{
            Toast.makeText(this, "Logged In as a Student", Toast.LENGTH_SHORT).show();
            Intent i1 = new Intent(getApplicationContext(),Student.class);
            i1.putExtra("name",Username.getText().toString());
            startActivity(i1);
        }

    }
}