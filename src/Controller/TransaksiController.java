package Controller;

import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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

    // METHOD UNTUK CUSTOMER
    public boolean simpanTransaksi(Transaksi t, List<DetailTransaksi> keranjang) {
        try {
            // 1. Simpan Header Transaksi
            // status_pesanan default 'Pending'
            this.sql = "INSERT INTO transaksi (user_id, total_harga, status_pesanan, tanggal) VALUES " +
                       "(" + t.getUser_id() + ", " + t.getTotal_harga() + ", 'Pending', NOW())";
            
            this.stm.executeUpdate(this.sql);

            // 2. Ambil ID Transaksi barusan
            int idTransaksiBaru = 0;
            this.res = this.stm.executeQuery("SELECT MAX(id) AS id_terakhir FROM transaksi");
            if (this.res.next()) {
                idTransaksiBaru = this.res.getInt("id_terakhir");
            }

            // 3. Simpan Detail Item
            for (DetailTransaksi item : keranjang) {
                Koneksi db2 = new Koneksi(); db2.config(); // Koneksi baru biar aman
                String sqlDetail = "INSERT INTO detail_transaksi (transaksi_id, menu_id, jumlah, subtotal) VALUES " +
                                   "(" + idTransaksiBaru + ", " + 
                                   item.getMenu_id() + ", " + 
                                   item.getJumlah() + ", " + 
                                   item.getSubtotal() + ")";
                db2.stm.executeUpdate(sqlDetail);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    


    // METHOD UNTUK KASIR
    public DefaultTableModel getDaftarTransaksiModel() {
        DefaultTableModel model = new DefaultTableModel();
        // Setup Kolom
        model.addColumn("ID");
        model.addColumn("Nama Pemesan");
        model.addColumn("Total");
        model.addColumn("Status");
        model.addColumn("Tanggal");
        
        try {
            // Join ke tabel users untuk ambil nama
            String sql = "SELECT t.id, u.nama_lengkap, t.total_harga, t.status_pesanan, t.tanggal " +
                         "FROM transaksi t " +
                         "JOIN users u ON t.user_id = u.id " +
                         "ORDER BY t.id DESC"; // Paling baru di atas
            
            Koneksi db = new Koneksi(); db.config();
            ResultSet rs = db.stm.executeQuery(sql);
            
            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nama_lengkap"), // Ini yang tidak ada di Model kamu
                    "Rp " + (long)rs.getDouble("total_harga"),
                    rs.getString("status_pesanan"),
                    rs.getTimestamp("tanggal")
                });
            }
        } catch (Exception e) {
            System.out.println("Error Load Table: " + e.getMessage());
        }
        return model;
    }
    
    // Ambil Detail Item untuk Tabel Kanan Kasir
    public DefaultTableModel getDetailTransaksiModel(int idTransaksi) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nama Menu");
        model.addColumn("Qty");
        model.addColumn("Subtotal");
        
        try {
            String sql = "SELECT m.nama_menu, d.jumlah, d.subtotal " +
                         "FROM detail_transaksi d " +
                         "JOIN menus m ON d.menu_id = m.id " +
                         "WHERE d.transaksi_id = " + idTransaksi;
            
            Koneksi db = new Koneksi(); db.config();
            ResultSet rs = db.stm.executeQuery(sql);
            
            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nama_menu"),
                    rs.getInt("jumlah"),
                    "Rp " + (long)rs.getDouble("subtotal")
                });
            }
        } catch (Exception e) {
            System.out.println("Error Detail: " + e.getMessage());
        }
        return model;
    }
    
    // Update Status jadi Lunas
    public boolean updateStatusTransaksi(int idTransaksi, String statusBaru) {
        try {
            String sql = "UPDATE transaksi SET status_pesanan = '" + statusBaru + "' WHERE id = " + idTransaksi;
            Koneksi db = new Koneksi(); db.config();
            db.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Update: " + e.getMessage());
            return false;
        }
    }
}