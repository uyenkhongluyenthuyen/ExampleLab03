package com.example.examplelab03;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtSearch;
    private ListView lvTS;

    private Button btnAdd;
    private List<ThiSinh> listTS;

    private ThiSinhAdapter adapter;
    private ThiSinhDB db;

    private ThiSinh seletedThiSinh;
    private int selectdId;

    private ConnectionReceiver receiver;

    //đăng kí sự kiến muốn lắng nghe
    private IntentFilter intentFilter;

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
                seletedThiSinh = listTS.get(position);
                selectdId = position;
                return false;
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                adapter.getFilter().filter(charSequence.toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        receiver = new ConnectionReceiver();
        intentFilter = new IntentFilter("com.example.myapplication.SOME_ACTION");
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver, intentFilter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.actionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.SortByTotalPoint){
            adapter.sortByTotalPoint();
            Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.contextmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id == R.id.mnuEdit){
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            Bundle b = new Bundle();
            b.putString("SBD", seletedThiSinh.getSbd());
            b.putString("HoTen", seletedThiSinh.getHoTen());
            b.putFloat("Toan", seletedThiSinh.getToan());
            b.putFloat("Ly", seletedThiSinh.getLy());
            b.putFloat("Hoa", seletedThiSinh.getHoa());
            intent.putExtras(b);
            startActivityForResult(intent, 100);
        }
        if(id== R.id.mnuDelete){
            db.deleteThiSinh(seletedThiSinh.getSbd());
            listTS.remove(seletedThiSinh);
            adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
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

        } else if (requestCode == 100 && resultCode==200) {
            db.updateThiSinh(sbd, ts);
            listTS.set(selectdId, ts);
            adapter.notifyDataSetChanged();
        }

    }
}