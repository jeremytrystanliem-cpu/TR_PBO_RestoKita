package Controller;

import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;
import Model.Transaksi;
import Model.DetailTransaksi;

public class TransaksiController {
    
    public Statement stm;
    public ResultSet res;
    public String sql;

    public TransaksiController() {
        Koneksi db = new Koneksi();
        db.config();
        this.stm = db.stm;
    }

    // Method Simpan Pesanan (Checkout)
    public boolean simpanTransaksi(Transaksi t, List<DetailTransaksi> keranjang) {
        try {
            // A. Simpan Header Transaksi
            // NOW() adalah fungsi MySQL untuk ambil waktu sekarang otomatis
            this.sql = "INSERT INTO transaksi (user_id, total_harga, status_pesanan, tanggal) VALUES " +
                       "(" + t.getUser_id() + ", " + t.getTotal_harga() + ", 'Pending', NOW())";
            
            this.stm.executeUpdate(this.sql);

            // B. Ambil ID Transaksi yang baru saja dibuat
            // Kita cari ID paling besar (terakhir masuk)
            int idTransaksiBaru = 0;
            this.res = this.stm.executeQuery("SELECT MAX(id) AS id_terakhir FROM transaksi");
            if (this.res.next()) {
                idTransaksiBaru = this.res.getInt("id_terakhir");
            }

            // C. Simpan Detail (Isi Keranjang)
            // Kita looping list belanjaan
            for (DetailTransaksi item : keranjang) {
                String sqlDetail = "INSERT INTO detail_transaksi (transaksi_id, menu_id, jumlah, subtotal) VALUES " +
                                   "(" + idTransaksiBaru + ", " + 
                                   item.getMenu_id() + ", " + 
                                   item.getJumlah() + ", " + 
                                   item.getSubtotal() + ")";
                
                // Eksekusi satu per satu
                // Note: Kita buat statement baru agar tidak bentrok dengan query sebelumnya
                Koneksi dbDetail = new Koneksi();
                dbDetail.config();
                dbDetail.stm.executeUpdate(sqlDetail);
            }
            
            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Transaksi: " + e.getMessage());
            e.printStackTrace(); // Cek error di console bawah NetBeans
            return false;
        }
    }
}