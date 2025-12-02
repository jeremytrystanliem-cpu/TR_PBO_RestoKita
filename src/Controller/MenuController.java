package Controller;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import Model.Menu;

public class MenuController {
    
    public Statement stm;
    public ResultSet res;
    public String sql;
    public DefaultTableModel dtm = new DefaultTableModel();

    // Constructor
    public MenuController() {
        Koneksi db = new Koneksi();
        db.config();
        this.stm = db.stm;
    }

    // 1. Setup Tabel Visual 
    public DefaultTableModel createTable() {
        // Reset dulu biar bersih
        dtm = new DefaultTableModel();
        
        this.dtm.addColumn("ID");
        this.dtm.addColumn("Nama Menu");
        this.dtm.addColumn("Harga");
        this.dtm.addColumn("Stok");
        this.dtm.addColumn("Kategori");
        this.dtm.addColumn("Gambar");
        return this.dtm;
    }

    // 2. Tampilkan Data Menu ke Tabel 
    public void tampilkanMenu() {
        try {
            this.dtm.getDataVector().removeAllElements();
            this.dtm.fireTableDataChanged();

            this.sql = "SELECT * FROM menus";
            this.res = this.stm.executeQuery(this.sql);

            while (this.res.next()) {
                Object[] obj = new Object[6]; 
                obj[0] = res.getInt("id");
                obj[1] = res.getString("nama_menu");
                obj[2] = res.getDouble("harga");
                obj[3] = res.getInt("stok");
                obj[4] = res.getString("kategori");
                obj[5] = res.getString("gambar");
                
                this.dtm.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("Gagal Tampil Menu: " + e.getMessage());
        }
    }

    // 3. Tambah Menu )
    public boolean insertMenu(String nama, double harga, int stok, String kategori, String gambar) {
        try {
            // Jika gambar kosong, isi default
            if(gambar.isEmpty()) gambar = "default.png";

            this.sql = "INSERT INTO menus (nama_menu, harga, stok, kategori, gambar) VALUES " +
                       "('" + nama + "', " + harga + ", " + stok + ", '" + kategori + "', '" + gambar + "')";
            
            this.stm.executeUpdate(this.sql);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Insert: " + e.getMessage());
            return false;
        }
    }

    // 4. Update Menu 
    public boolean updateMenu(int id, String nama, double harga, int stok, String kategori, String gambar) {
        try {
            this.sql = "UPDATE menus SET " +
                       "nama_menu = '" + nama + "', " +
                       "harga = " + harga + ", " +
                       "stok = " + stok + ", " +
                       "kategori = '" + kategori + "', " +
                       "gambar = '" + gambar + "' " + 
                       "WHERE id = " + id;
            
            this.stm.executeUpdate(this.sql);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Update: " + e.getMessage());
            return false;
        }
    }

    // 5. Hapus Menu 
    public boolean deleteMenu(int id) {
        try {
            this.sql = "DELETE FROM menus WHERE id = " + id;
            this.stm.executeUpdate(this.sql);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Delete: " + e.getMessage());
            return false;
        }
    }
    
    // 6. Mengambil semua data menu sebagai List (Untuk Customer View Bergambar)
    public java.util.List<Model.Menu> getAllMenu() {
        java.util.List<Model.Menu> listMenu = new java.util.ArrayList<>();
        Koneksi db = new Koneksi(); // Buka koneksi baru biar aman
        db.config();
        
        try {
            String sql = "SELECT * FROM menus";
            java.sql.ResultSet rs = db.stm.executeQuery(sql);
            
            while(rs.next()){
                Model.Menu m = new Model.Menu();
                m.setId(rs.getInt("id"));
                m.setNama_menu(rs.getString("nama_menu"));
                m.setHarga(rs.getDouble("harga"));
                m.setStok(rs.getInt("stok"));
                m.setKategori(rs.getString("kategori"));
                m.setGambar(rs.getString("gambar"));
                
                listMenu.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMenu;
    }
}