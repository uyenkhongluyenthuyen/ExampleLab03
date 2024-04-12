package com.example.examplelab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    private EditText edtSBD;
    private EditText edtHoTen;
    private EditText edtToan;
    private EditText edtLy;
    private EditText edtHoa;

    private Button btnEdit;

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        edtSBD = findViewById(R.id.edtSBD);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtToan = findViewById(R.id.edtToan);
        edtLy = findViewById(R.id.edtLy);
        edtHoa = findViewById(R.id.edtHoa);
        btnEdit = findViewById(R.id.btnEdit);
        btnBack = findViewById(R.id.btnBack);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString("SBD", edtSBD.getText().toString());
                b.putString("HoTen", edtHoTen.getText().toString());
                b.putFloat("Toan", Float.valueOf(edtToan.getText().toString()));
                b.putFloat("Ly", Float.valueOf(edtLy.getText().toString()));
                b.putFloat("Hoa", Float.valueOf(edtHoa.getText().toString()));
                intent.putExtras(b);
                setResult(200, intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}