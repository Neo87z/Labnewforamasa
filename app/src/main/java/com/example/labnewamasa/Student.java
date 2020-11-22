package com.example.labnewamasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labnewamasa.Database.DbHandler;

import java.util.List;

public class Student extends AppCompatActivity {
    TextView WelcomeText;
    ListView MySubjectListToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        WelcomeText=findViewById(R.id.textView3);
        String x;
        Intent PassedIntent = getIntent();
        x= "Welcome "+ PassedIntent.getStringExtra("name").toString();
        WelcomeText.setText(x);

        List SubjectFiltered;
        MySubjectListToShow=findViewById(R.id.SubjectList);
        DbHandler DBMS= new DbHandler(getApplicationContext());
        SubjectFiltered=DBMS.GetAllSubject();
        if(SubjectFiltered.size()== 0){

        }else{
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,SubjectFiltered);
            MySubjectListToShow.setAdapter(arrayAdapter);
            MySubjectListToShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    position++;
                    Toast.makeText(Student.this, "Postion is " +position , Toast.LENGTH_SHORT).show();
                    String x=Integer.toString(position);
                    Intent ChangAcc= new Intent(getApplicationContext(),Message.class);
                    ChangAcc.putExtra("ID",x);
                    startActivity(ChangAcc);
                    
                }
            });
        }

    }
}