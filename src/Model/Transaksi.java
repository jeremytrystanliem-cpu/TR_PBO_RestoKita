package Model;

import java.sql.Timestamp;

public class Transaksi {
    private int id;
    private int user_id;
    private int kasir_id;
    private Timestamp tanggal;
    private double total_harga;
    private String status_pesanan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getKasir_id() {
        return kasir_id;
    }

    public void setKasir_id(int kasir_id) {
        this.kasir_id = kasir_id;
    }

    public Timestamp getTanggal() {
        return tanggal;
    }

    public void setTanggal(Timestamp tanggal) {
        this.tanggal = tanggal;
    }

    public double getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(double total_harga) {
        this.total_harga = total_harga;
    }

    public String getStatus_pesanan() {
        return status_pesanan;
    }

    public void setStatus_pesanan(String status_pesanan) {
        this.status_pesanan = status_pesanan;
    }
    
    
}
