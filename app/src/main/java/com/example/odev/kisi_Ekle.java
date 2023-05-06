package com.example.odev;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class kisi_Ekle extends AppCompatActivity {
    Button kaydet;
    EditText isim, soyisim, numara;

    Intent anaMenu;
    DataBaseHandler dataBaseHandler;
    Intent i;
    long id = 0;
    SQLiteDatabase dbase;
    String eklenecekNumara;
    public String formatPhoneNumber(String phoneNumber) {
        String formattedNumber = phoneNumber.replaceFirst("^0*(\\d{3})(\\d{3})(\\d+)", "($1) $2 $3");
        return formattedNumber;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kisi_ekle);

        Animation animasyon = AnimationUtils.loadAnimation(this, R.anim.animasyon);
        View content = findViewById(android.R.id.content);
        content.startAnimation(animasyon);

        kaydet = findViewById(R.id.kaydet);
        isim = findViewById(R.id.isimTxt);
        soyisim = findViewById(R.id.soyisimTxt);
        numara = findViewById(R.id.numaraTxt);

        dataBaseHandler = new DataBaseHandler(this);

        i = new Intent(this, anaEkran.class);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        if (id > 0) {
            String   ad = intent.getStringExtra("ad");
            String   soyad = intent.getStringExtra("soyad");
            String   numara2 = intent.getStringExtra("numara");

            isim.setText(ad);
            soyisim.setText(soyad);
            numara.setText(formatPhoneNumber(numara2));
            kaydet.setText("GÜNCELLE");
        }
        try {
            dbase = this.openOrCreateDatabase(DataBaseHandler.DATABASE_NAME, MODE_PRIVATE, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        findViewById(R.id.kapat).setOnClickListener(v -> {
            startActivity(i);
            finish();
        });
        kaydet.setOnClickListener(v -> {
            eklenecekNumara = numara.getText().toString().replaceAll("[^\\d]", "");
            eklenecekNumara = "0" + eklenecekNumara;

            if(id>0) {
                dataBaseHandler.kisiGuncelle(
                        id,
                        isim.getText().toString(),
                        soyisim.getText().toString(),
                        eklenecekNumara
                );
                startActivity(i);
                finish();
            }
            else{
                dataBaseHandler.kisiEkle(
                        isim.getText().toString(),
                        soyisim.getText().toString(),
                        eklenecekNumara,
                        this
                );
                startActivity(i);
                finish();
            }
        });

        numara.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = numara.getText().toString();
                int textLength = numara.getText().length();

                if (text.endsWith("-") || text.endsWith(" ") || text.endsWith(" "))
                    return;
                if (textLength == 1) {
                    if (!text.contains("(")) {
                        numara.setText(new StringBuilder(text).insert(text.length() - 1, "(").toString());
                        numara.setSelection(numara.getText().length());
                    }
                } else if (textLength == 5) {
                    if (!text.contains(")")) {
                        numara.setText(new StringBuilder(text).insert(text.length() - 1, ")").toString());
                        numara.setSelection(numara.getText().length());
                    }
                } else if (textLength == 6) {
                    numara.setText(new StringBuilder(text).insert(text.length() - 1, " ").toString());
                    numara.setSelection(numara.getText().length());
                } else if (textLength == 10) {
                    numara.setText(new StringBuilder(text).insert(text.length() - 1, " ").toString());
                    numara.setSelection(numara.getText().length());
                } else if (textLength == 13) {
                    if (text.contains(" ")) {
                        numara.setText(new StringBuilder(text).insert(text.length() - 1, " ").toString());
                        numara.setSelection(numara.getText().length());
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}