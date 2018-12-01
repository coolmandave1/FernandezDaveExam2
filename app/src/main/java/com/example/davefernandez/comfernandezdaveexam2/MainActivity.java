package com.example.davefernandez.comfernandezdaveexam2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root;
    EditText efname, elname, Ex1, Ex2;
    TextView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root=db.getReference("grade");

        efname = findViewById(R.id.fname);
        elname = findViewById(R.id.lname);
        Ex1 = findViewById(R.id.Exam1);
        Ex2= findViewById(R.id.Exam2);




    }
    public static class Student {

        //Long id;
        String fname, lname;
        Long exam1, exam2, average;

        public Student(String fname, String lname, Long average) {
            this.fname = fname;
            this.lname = lname;

            this.average = average;
        }

        public String getFname() {
            return fname;
        }

        public String getLname() {
            return lname;
        }

        public Long getAverage() {
            return average;
        }
    }


    public void comp(View v){

        efname=findViewById(R.id.fname);
        elname=findViewById(R.id.lname);
        Ex1=findViewById(R.id.Exam1);
        Ex2=findViewById(R.id.Exam2);


        String fname1 = efname.getText().toString().trim();
        String lname1 = elname.getText().toString().trim();
        Long exam1 = Long.parseLong(Ex1.getText().toString().trim());
        Long exam2 = Long.parseLong(Ex2.getText().toString().trim());
        Long average = (exam1 + exam2) / 2;

        Student grade = new Student(fname1,lname1,average);
        String key = root.push().getKey();
        root.child(key).setValue(grade);
        add = findViewById(R.id.average);
        add.setText(average.toString());
        Toast.makeText(this,"record added to db",Toast.LENGTH_LONG).show();





    }
}
