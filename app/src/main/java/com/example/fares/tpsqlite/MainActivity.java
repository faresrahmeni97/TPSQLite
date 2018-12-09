package com.example.fares.tpsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtnom;
    private TextView txtnumber;
    private Button bt;
    private DatabaseHandler Db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtnom = findViewById(R.id.nom);
        txtnumber = findViewById(R.id.number);
        bt = findViewById(R.id.ajouter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Db = new DatabaseHandler(getApplicationContext());
                Db.open();
                Contact c = new Contact(txtnom.getText().toString(),txtnumber.getText().toString());
                Db.addContact(c);
                Intent i  = new Intent(MainActivity.this,ListActivity.class);
                startActivity(i);
            }
        });
    }
}
