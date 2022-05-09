package com.firebase.myapplication;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Bussiness extends AppCompatActivity {

    private DatabaseReference mDatabase;
    List<Chitieu> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussiness);

        mDatabase = FirebaseDatabase.getInstance().getReference("User");

        list= new ArrayList<>();
        EditText txt = findViewById(R.id.txt1);
        EditText txt2 = findViewById(R.id.txt2);
        Button btn = findViewById(R.id.button2);


        laydc(list);

        updateui();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chitieu a= new Chitieu(txt.getText().toString(),Double.valueOf(txt2.getText().toString()));
                list.add(a);
                mDatabase.setValue(list);
                list = new ArrayList<>();
                laydc(list);
                updateui();
            }
        });





    }


    public void updateui(){
        ListView listView = (ListView) findViewById(R.id.idlist);
        Adapter adt = new Adapter(this,R.layout.activity_deltail,list);
        listView.setAdapter(adt);

    }

    public  void laydc(List<Chitieu> list){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                   Chitieu c = postSnapshot.getValue(Chitieu.class);
                   list.add(c);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
    }

}