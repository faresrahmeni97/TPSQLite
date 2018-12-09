package com.example.fares.tpsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {



    private DatabaseHandler DHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DHandler = new DatabaseHandler(this);
        DHandler.open();
        ListView lvl;
        ArrayList<String> lst = new ArrayList<>();
        List<Contact> listContact = new ArrayList<Contact>();
        listContact.addAll(DHandler.getAllContact());
        for (int i =0;i < listContact.size();i++){
            String stg = listContact.get(i).getId()+":"+listContact.get(i).getNom()+" : "+listContact.get(i).getNumber();
            lst.add(stg);
        }
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lst);
        lvl = (ListView) findViewById(R.id.listcontact);
        lvl.setAdapter(Adapter);
    }
}
