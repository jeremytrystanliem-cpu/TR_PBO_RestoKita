package View;

import Controller.MenuController;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Admin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Admin.class.getName());

    public Admin() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        // 1. LOGO KECIL
        try {
            ImageIcon iconAsli = new ImageIcon("images/RestoKita.png");
            if (iconAsli.getIconWidth() > -1) {
                Image img = iconAsli.getImage();
                Image imgScale = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                lblLogoKecil.setIcon(new ImageIcon(imgScale));
                lblLogoKecil.setText("");
            }
        } catch (Exception e) {}

        // 2. LOAD DATA
        loadData();
    }
    
    // --- METHOD HELPER ---
    
    private void loadData() {
        MenuController mc = new MenuController();
        DefaultTableModel model = mc.createTable();
        mc.dtm = model; 
        mc.tampilkanMenu();
        tabelMenu.setModel(mc.dtm);
        
        // Setelah load data, hitung ID otomatis untuk inputan baru
        generateAutoID();
    }
    
    private void generateAutoID() {
        int maxID = 0;
        // Loop semua baris di tabel untuk cari ID tertinggi
        for (int i = 0; i < tabelMenu.getRowCount(); i++) {
            try {
                int id = Integer.parseInt(tabelMenu.getValueAt(i, 0).toString());
                if (id > maxID) {
                    maxID = id;
                }
            } catch (Exception e) {}
        }
        // Set label ID menjadi Max + 1
        lblIDValue.setText(String.valueOf(maxID + 1));
    }
    
    private void clearForm() {
        generateAutoID(); 
        txtNama.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        txtGambar.setText("");
        cbKategori.setSelectedIndex(0);
        
        btnSimpan.setEnabled(true);
        tabelMenu.clearSelection();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        lblLogoKecil = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbKategori = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtGambar = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelMenu = new javax.swing.JTable();
        lblIDValue = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(236, 240, 241));
        setName("Admin"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelHeader.setBackground(new java.awt.Color(230, 126, 34));
        panelHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelHeader.add(lblLogoKecil, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RestoKita Administrator");
        panelHeader.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        btnLogout.setBackground(new java.awt.Color(231, 76, 60));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("KELUAR");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        panelHeader.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 90, 35));

        getContentPane().add(panelHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 70));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Input / Edit Data Menu");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel3.setText("ID Menu (Auto)");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel4.setText("Nama Makanan");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        txtNama.setBackground(new java.awt.Color(236, 240, 241));
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });
        getContentPane().add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 250, 35));

        jLabel5.setText("Harga (Rp)");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        txtHarga.setBackground(new java.awt.Color(236, 240, 241));
        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });
        getContentPane().add(txtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 250, 35));

        jLabel6.setText("Stok");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        txtStok.setBackground(new java.awt.Color(236, 240, 241));
        txtStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStokActionPerformed(evt);
            }
        });
        getContentPane().add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 250, 35));

        jLabel7.setText("Kategori");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Makanan", "Minuman", "Snack" }));
        getContentPane().add(cbKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 250, 35));

        jLabel8.setText("Nama File Gambar");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        txtGambar.setBackground(new java.awt.Color(236, 240, 241));
        txtGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGambarActionPerformed(evt);
            }
        });
        getContentPane().add(txtGambar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 250, 35));

        btnSimpan.setBackground(new java.awt.Color(39, 174, 96));
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 495, 80, 40));

        btnUbah.setBackground(new java.awt.Color(230, 126, 34));
        btnUbah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setText("UBAH");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        getContentPane().add(btnUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 495, 80, 40));

        btnHapus.setBackground(new java.awt.Color(231, 76, 60));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 495, 80, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Daftar Menu Tersedia");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        tabelMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Menu", "Harga", "Stok", "Gambar"
            }
        ));
        tabelMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMenuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelMenu);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 550, 410));

        lblIDValue.setText("-");
        getContentPane().add(lblIDValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose(); 
        new Login().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void txtStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStokActionPerformed

    private void txtGambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGambarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGambarActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try {
            String nama = txtNama.getText();
            double harga = Double.parseDouble(txtHarga.getText());
            int stok = Integer.parseInt(txtStok.getText());
            String kat = cbKategori.getSelectedItem().toString();
            // String gbr = txtGambar.getText(); // Nanti ditambahkan di Controller jika sudah update
            
            MenuController mc = new MenuController();
            // Pastikan method insertMenu di Controller sudah sesuai parameter
            boolean sukses = mc.insertMenu(nama, harga, stok, kat, txtGambar.getText()); 
            
            if(sukses) {
                JOptionPane.showMessageDialog(this, "Berhasil Simpan!");
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal Simpan!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga dan Stok harus angka!");
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        try {
            if(lblIDValue.getText().isEmpty()){ 
                JOptionPane.showMessageDialog(this, "Pilih data dulu!");
                return;
            }
            
            int id = Integer.parseInt(lblIDValue.getText());
            String nama = txtNama.getText();
            double harga = Double.parseDouble(txtHarga.getText());
            int stok = Integer.parseInt(txtStok.getText());
            String kat = cbKategori.getSelectedItem().toString();
            String gbr = txtGambar.getText();
            
            MenuController mc = new MenuController();
            boolean sukses = mc.updateMenu(id, nama, harga, stok, kat, gbr);
            
            if(sukses) {
                JOptionPane.showMessageDialog(this, "Berhasil Update!");
                loadData();
                clearForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        if(btnSimpan.isEnabled()){
             JOptionPane.showMessageDialog(this, "Pilih data di tabel dulu!");
             return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(lblIDValue.getText());
            MenuController mc = new MenuController();
            if(mc.deleteMenu(id)){
                JOptionPane.showMessageDialog(this, "Terhapus!");
                loadData();
                clearForm();
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void tabelMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMenuMouseClicked
        int baris = tabelMenu.getSelectedRow();
        if (baris != -1) {
            lblIDValue.setText(tabelMenu.getValueAt(baris, 0).toString());
            
            txtNama.setText(tabelMenu.getValueAt(baris, 1).toString());
            txtHarga.setText(tabelMenu.getValueAt(baris, 2).toString());
            txtStok.setText(tabelMenu.getValueAt(baris, 3).toString());
            cbKategori.setSelectedItem(tabelMenu.getValueAt(baris, 4).toString());
            
            Object img = tabelMenu.getValueAt(baris, 5);
            txtGambar.setText(img != null ? img.toString() : "");
            
            btnSimpan.setEnabled(false); 
        }
    }//GEN-LAST:event_tabelMenuMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Admin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbKategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIDValue;
    private javax.swing.JLabel lblLogoKecil;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JTable tabelMenu;
    private javax.swing.JTextField txtGambar;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
