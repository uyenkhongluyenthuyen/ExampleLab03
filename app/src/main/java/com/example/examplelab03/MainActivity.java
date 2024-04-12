package com.example.examplelab03;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtSearch;
    private ListView lvTS;

    private Button btnAdd;
    private List<ThiSinh> listTS;

    private ThiSinhAdapter adapter;
    private ThiSinhDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edtSearch);
        lvTS = findViewById(R.id.lvTS);
        btnAdd = findViewById(R.id.btnAdd);

        db = new ThiSinhDB(this, "ThiSinhdb", null, 1);
        //them du lieu dau vao
        //db.addThiSinh(new ThiSinh("TS1", "Nguyen Thu Uyen", 9.5f,8.5f,7.0f));

        listTS = db.getAllStudent();
        adapter = new ThiSinhAdapter(listTS, this);
        lvTS.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivityForResult(intent, 150);
            }
        });

        registerForContextMenu(lvTS);
        lvTS.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.actionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        String sbd = b.getString("SBD");
        String hoten = b.getString("HoTen");
        Float toan = b.getFloat("Toan");
        Float ly = b.getFloat("Ly");
        Float hoa = b.getFloat("Hoa");

        ThiSinh ts = new ThiSinh(sbd, hoten, toan, ly, hoa);

        if(requestCode == 150 && resultCode ==200){
            db.addThiSinh(ts);
            listTS.add(ts);
            adapter.notifyDataSetChanged();

        }

    }
}