package com.example.odev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class numara_goruntule extends AppCompatActivity {
    Button sifir, bir, iki, uc, dort, bes, alti, yedi, sekiz, dokuz, yildiz, kare, aramaYap,sil;
    String aranacakNumara;
    EditText aranacak_numaraTxt;
    static final int REQUEST_CALL_PHONE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numara_goruntule);

        sifir = findViewById(R.id.sifir);
        bir = findViewById(R.id.bir);
        iki = findViewById(R.id.iki);
        uc = findViewById(R.id.uc);
        dort = findViewById(R.id.dort);
        bes = findViewById(R.id.bes);
        alti = findViewById(R.id.alti);
        yedi = findViewById(R.id.yedi);
        sekiz = findViewById(R.id.sekiz);
        dokuz = findViewById(R.id.dokuz);
        yildiz = findViewById(R.id.yildiz);
        kare = findViewById(R.id.kare);
        aramaYap = findViewById(R.id.aramaYap);
        sifir = findViewById(R.id.sifir);
        sil=findViewById(R.id.sil);

        aranacak_numaraTxt = findViewById(R.id.numaraa);
        aranacakNumara = "";

        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aranacakNumara.length() > 0) {
                    aranacakNumara = aranacakNumara.substring(0, aranacakNumara.length() - 1);
                }
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        bir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakNumara = aranacakNumara + "1";
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        iki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakNumara = aranacakNumara + "2";
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        uc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakNumara = aranacakNumara + "3";
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        dort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakNumara = aranacakNumara + "4";
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        bes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakNumara = aranacakNumara + "5";
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        alti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakNumara = aranacakNumara + "6";
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        yedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakNumara = aranacakNumara + "7";
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        sekiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakNumara = aranacakNumara + "8";
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        dokuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakNumara = aranacakNumara + "9";
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        yildiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakNumara = aranacakNumara + "*";
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        sifir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakNumara = aranacakNumara + "0";
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        kare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakNumara = aranacakNumara + "#";
                aranacak_numaraTxt.setText(aranacakNumara);
            }
        });
        aramaYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               callPhoneNumber(aranacakNumara);
            }
        });
    }
    public void callPhoneNumber(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // İzin verilmemiş ise, kullanıcıdan izin iste
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE_PERMISSION);
        } else {
            // İzin verilmiş ise, aramayı başlat
            startActivity(callIntent);
        }
    }
}