package com.UTSmobproGanjil.johannes2002895.Model;

import com.google.gson.annotations.SerializedName;

public class Notes {

    @SerializedName("id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("ukuran")
    private String ukuran;

    public Notes(){}

    public Notes(String id, String nama, String kategori, String deskripsi,String ukuran) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.ukuran = ukuran;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

}
