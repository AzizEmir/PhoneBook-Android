package com.example.odev;

public class Kisi {
    private String ad,soyad,numara;
    private int id;
    public Kisi(int id,String ad, String soyad, String numara) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.numara = numara;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }
}
