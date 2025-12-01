package Controller;

import Model.User;

import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class UserController {
    public Statement stm;
    public ResultSet res;
    public String sql;
    
    public DefaultTableModel dtm = new DefaultTableModel();
    
    public UserController(){
        Koneksi db = new Koneksi();
        db.config();
        this.stm = db.stm; 
    }
    
    // ===========================================
    // BAGIAN 1: UNTUK MENAMPILKAN DATA DI TABEL 
    // ===========================================

    // Method 1: Membuat judul kolom tabel visual
    public DefaultTableModel createTable(){
        // Sesuaikan dengan kolom tabel users di database restokita_db
        this.dtm.addColumn("ID");
        this.dtm.addColumn("Username");
        this.dtm.addColumn("Nama Lengkap");
        this.dtm.addColumn("Role");
        return this.dtm;
    }
    
    // Method 2: SELECT (Mengambil semua data user)
    public void tampilkanUser(){
        try {
            // Bersihkan baris lama agar tidak duplikat
            this.dtm.getDataVector().removeAllElements();
            this.dtm.fireTableDataChanged();
            
            // Query
            this.sql = "SELECT * FROM users";
            
            // Jalankan Query
            this.res = this.stm.executeQuery(sql);
            
            // Masukkan hasil query ke tabel virtual (dtm)
            while(res.next()){
                Object[] obj = new Object[4];
                obj[0] = res.getInt("id");            // Ambil ID
                obj[1] = res.getString("username");   // Ambil Username
                obj[2] = res.getString("nama_lengkap"); // Ambil Nama
                obj[3] = res.getString("role");       // Ambil Role
                
                this.dtm.addRow(obj); // Tambahkan baris ke tabel
            }
        } catch (Exception e) {
            System.out.println("Gagal query tampilkan user: " + e.getMessage());
        }
    }

    // ==================================
    // BAGIAN 2: LOGIKA LOGIN & REGISTER 
    // ==================================

    // Method 3: Cek Login (Mengembalikan object User jika ketemu)
    public User cekLogin(String username, String password) {
        User u = null;
        try {
            // Query Mencocokkan User dan Pass
            this.sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
            this.res = this.stm.executeQuery(sql);
            
            if (this.res.next()) {
                u = new User();
                // Mapping dari Database ke Model
                u.setId(res.getInt("id"));
                u.setUsername(res.getString("username"));
                u.setPassword(res.getString("password"));
                u.setNama_lengkap(res.getString("nama_lengkap"));
                u.setRole(res.getString("role"));
            }
        } catch (Exception e) {
            System.out.println("Login Gagal: " + e.getMessage());
        }
        return u;
    }

    // Method 4: Registrasi Customer Baru
    public boolean registerCustomer(String username, String password, String nama) {
        try {
            // Query Insert data baru dengan Role 'Customer'
            this.sql = "INSERT INTO users (username, password, nama_lengkap, role) " +
                       "VALUES ('" + username + "', '" + password + "', '" + nama + "', 'Customer')";
            
            // executeUpdate dipakai untuk INSERT/UPDATE/DELETE
            this.stm.executeUpdate(this.sql); 
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Register: " + e.getMessage());
            return false;
        }
    }
}