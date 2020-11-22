package com.example.labnewamasa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labnewamasa.Database.DbHandler;

public class Register extends AppCompatActivity {
    EditText MyAnme,Pasword;
    Button Register;
    CheckBox Student,Teacher;
    String Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MyAnme=findViewById(R.id.EditTextUsername);
        Pasword=findViewById(R.id.EditTextpassword);
        Register=findViewById(R.id.RegisterButton1);
        Student=findViewById(R.id.TeacherCheckBox);
        Teacher=findViewById(R.id.StudentCheckbox);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavemyUSer();
            }
        });
    }

    public void SavemyUSer(){
        MyAnme=findViewById(R.id.EditTextUsername);
        Pasword=findViewById(R.id.EditTextpassword);
        Register=findViewById(R.id.RegisterButton1);
        Student=findViewById(R.id.StudentCheckbox);
        Teacher=findViewById(R.id.TeacherCheckBox);
        if(Student.isChecked()== true){
            Type="Student";
        }else{
            Type="Teacher";
        }


        DbHandler DBMS= new DbHandler(getApplicationContext());
        long exec=DBMS.AddMyUser( MyAnme.getText().toString(),  Pasword.getText().toString(), Type);
        if(exec > 0){
            Toast.makeText(Register.this, "User Added", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(Register.this, "Error", Toast.LENGTH_SHORT).show();
        }


    }


}