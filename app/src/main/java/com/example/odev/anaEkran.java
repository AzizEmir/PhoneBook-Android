package com.example.odev;

import static com.example.odev.numara_goruntule.REQUEST_CALL_PHONE_PERMISSION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class anaEkran extends AppCompatActivity {
    EditText aramaCubugu;
    Button kisiEkle, favoriler, son, kisiler;
    ListView liste;
    DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
    Adapter adapter1;
    ArrayList<Kisi> kayitliKisiler = new ArrayList<>();
    FloatingActionButton numara_gir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_ekran_kisiler);

        aramaCubugu = findViewById(R.id.aramaCubugu);
        kisiEkle = findViewById(R.id.kisiEkle);
        favoriler = findViewById(R.id.favoriler);
        son = findViewById(R.id.son);
        kisiler = findViewById(R.id.kisiler);
        liste = findViewById(R.id.liste);
        numara_gir = findViewById(R.id.floatingActionButton2);
        kayitliKisiler = dataBaseHandler.kisiListele();//Veritabanından okunan verilerin geçici listeye atayan kod bloğu

        adapter1 = new Adapter(kayitliKisiler, anaEkran.this);
        liste.setAdapter(adapter1);

        //Kullanılan cihaz'ın ekran boyutu alındı
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int ekranGenislik = size.x; // int ekranYukseklik = size.y;

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               callPhoneNumber(kayitliKisiler.get(position).getNumara());
                System.out.println(kayitliKisiler.get(position).getNumara());
            }
        });


        //Arama çubuğu'na ekran genişliğinin yarısı kadar genişlik atandı.
        aramaCubugu.getLayoutParams().width = (int) (ekranGenislik / 1.10);
        //Kişi ekle butonu'na ekran genişliğinin yarısı kadar genişlik atandı.
        kisiEkle.getLayoutParams().width = (int) (ekranGenislik / 1.50);

        kisiEkle.setOnClickListener(v -> {
            Intent kisiEkle = new Intent(this, kisi_Ekle.class);
            startActivity(kisiEkle);
            finish();
        });

        aramaCubugu.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                findViewById(R.id.menu).setVisibility(View.INVISIBLE);
                numara_gir.setVisibility(View.INVISIBLE);
            }
        });

        numara_gir.setOnClickListener(v -> {
            Intent numaraGir = new Intent(this, numara_goruntule.class);
            startActivity(numaraGir);
        });

    }

    @Override
    public void onBackPressed() {
        if (aramaCubugu.hasFocus()) {
            aramaCubugu.clearFocus();
            findViewById(R.id.menu).setVisibility(View.VISIBLE);
            numara_gir.setVisibility(View.VISIBLE);
        } else
            finish();
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