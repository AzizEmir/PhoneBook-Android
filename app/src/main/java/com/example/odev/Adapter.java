package com.example.odev;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Adapter extends BaseAdapter {
    private ArrayList<Kisi> mDataList;
    private Context context;

    public Adapter(ArrayList<Kisi> mDataList, Context context) {
        this.context = context;
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.kisi_goruntule_tasarimi, null);
        }

        TextView gosterilenKisiAdi = convertView.findViewById(R.id.ad);
        TextView gosterilenKisiSoyadi = convertView.findViewById(R.id.soyad);
        TextView gosterilenKisiNumara = convertView.findViewById(R.id.numara);
        ImageView edit = convertView.findViewById(R.id.duzenleKisi);
        ImageView sil = convertView.findViewById(R.id.silKisi);

        sil.setOnClickListener(v -> {
            DataBaseHandler dataBaseHandler = new DataBaseHandler(context);
            dataBaseHandler.kisiSil(mDataList.get(position).getId());
            Toast.makeText(context, mDataList.get(position).getId()+"", Toast.LENGTH_SHORT).show();
            ((Activity) context).recreate();

        });

        edit.setOnClickListener(v -> {
            Intent intent=new Intent(context, kisi_Ekle.class);
            intent.putExtra("id",mDataList.get(position).getId());
            intent.putExtra("ad",mDataList.get(position).getAd());
            intent.putExtra("soyad",mDataList.get(position).getSoyad());

            String text = mDataList.get(position).getNumara();

            intent.putExtra("numara",text);
            context.startActivity(intent);
        });
        gosterilenKisiAdi.setText(mDataList.get(position).getAd());
        gosterilenKisiSoyadi.setText(mDataList.get(position).getSoyad());
        gosterilenKisiNumara.setText(mDataList.get(position).getNumara());
        return convertView;
    }
}
