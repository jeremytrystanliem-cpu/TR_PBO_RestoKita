package View;

import Controller.MenuController;
import Controller.TransaksiController;
import Model.DetailTransaksi;
import Model.Menu;
import Model.Transaksi;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Customer extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Customer.class.getName());

    // 1. DATA GLOBAL
    User currentUser; 
    List<DetailTransaksi> keranjang = new ArrayList<>();
    double totalBelanja = 0;

    // 2. CONSTRUCTOR (Dipanggil saat Run)
    public Customer() {
        initComponents();
        setupFrame(); // Panggil fungsi setup
    }

    // Constructor dengan Data User (Dipanggil dari Login)
    public Customer(User u) {
        initComponents();
        this.currentUser = u;
        setupFrame();
        
        // Tampilkan Nama
        if(u != null) lblSelamatDatang.setText("Halo, " + u.getNama_lengkap());
    }
    
    // Set up Visual & Data
    private void setupFrame() {
        // 1. Agar tidak terpotong (Layout Fix)
        this.getContentPane().setPreferredSize(new Dimension(1100, 700));
        this.pack();
        this.setLocationRelativeTo(null);
        
        // 2. Load Logo Header
        try {
            ImageIcon icon = new ImageIcon("images/RestoKita.png");
            if (icon.getIconWidth() > 0) {
                Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                lblIconLogo.setIcon(new ImageIcon(img));
                lblIconLogo.setText("");
            }
        } catch (Exception e) {}
        
        // 3. Siapkan Tabel & Menu
        siapkanTabelKeranjang();
        loadMenuBergambar();
    }

    private void siapkanTabelKeranjang() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Menu");
        model.addColumn("Qty");
        model.addColumn("Subtotal");
        tabelKeranjang.setModel(model); 
        
        // Atur lebar kolom
        tabelKeranjang.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabelKeranjang.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabelKeranjang.getColumnModel().getColumn(2).setPreferredWidth(40);
        tabelKeranjang.setRowHeight(25);
    }

    // Load Menu ke Tab Kategori
    private void loadMenuBergambar() {
        // 1. Bersihkan Panel Lama
        panelSemua.removeAll();
        panelMakanan.removeAll();
        panelMinuman.removeAll();
        panelSnack.removeAll();
        
        // 2. Atur Layout Grid (3 Kolom ke samping, baris otomatis)
        GridLayout layout = new GridLayout(0, 3, 15, 15);
        panelSemua.setLayout(layout);
        panelMakanan.setLayout(layout);
        panelMinuman.setLayout(layout);
        panelSnack.setLayout(layout);

        // 3. Ambil Data dari Database
        MenuController mc = new MenuController();
        List<Menu> daftarMenu = mc.getAllMenu(); 

        // 4. Masukkan Kartu ke Panel yang Sesuai
        for (Menu m : daftarMenu) {
            
            // Masukkan ke Tab "Semua"
            panelSemua.add(createCard(m));
            
            // Masukkan ke Tab Kategori (Harus buat kartu baru/copy karena komponen Swing tidak bisa di 2 tempat)
            String kat = m.getKategori();
            
            if (kat.equalsIgnoreCase("Makanan")) {
                panelMakanan.add(createCard(m));
            } 
            else if (kat.equalsIgnoreCase("Minuman")) {
                panelMinuman.add(createCard(m));
            } 
            else if (kat.equalsIgnoreCase("Snack")) {
                panelSnack.add(createCard(m));
            }
        }
        
        // 5. Refresh Agar Muncul
        updateUI();
    }
    
    private void updateUI() {
        panelSemua.revalidate(); panelSemua.repaint();
        panelMakanan.revalidate(); panelMakanan.repaint();
        panelMinuman.revalidate(); panelMinuman.repaint();
        panelSnack.revalidate(); panelSnack.repaint();
    }
    
    // Membuat Card Menu
    private JPanel createCard(Menu m) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        card.setPreferredSize(new Dimension(180, 240)); 

        // Gambar
        JLabel lblGbr = new JLabel("", JLabel.CENTER);
        try {
            ImageIcon icon = new ImageIcon("images/" + m.getGambar());
            if(icon.getIconWidth() > 0) {
                Image img = icon.getImage().getScaledInstance(140, 110, Image.SCALE_SMOOTH);
                lblGbr.setIcon(new ImageIcon(img));
            } else {
                lblGbr.setText("No Image");
            }
        } catch (Exception e) {
            lblGbr.setText("Err Img");
        }
        
        // Panel Bawah (Nama, Harga, Tombol)
        JPanel info = new JPanel(new GridLayout(3, 1, 0, 5));
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JLabel lblNama = new JLabel(m.getNama_menu(), JLabel.CENTER);
        lblNama.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        JLabel lblHarga = new JLabel("Rp " + (long)m.getHarga(), JLabel.CENTER);
        lblHarga.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblHarga.setForeground(new Color(230, 126, 34)); // Orange
        
        JButton btnAdd = new JButton("TAMBAH");
        btnAdd.setBackground(new Color(39, 174, 96)); // Hijau
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Aksi Klik Tombol Tambah
        btnAdd.addActionListener(e -> tambahKeKeranjang(m));

        info.add(lblNama);
        info.add(lblHarga);
        info.add(btnAdd);
        
        card.add(lblGbr, BorderLayout.CENTER);
        card.add(info, BorderLayout.SOUTH);
        
        return card;
    }

    // Logika Transaksi
    private void tambahKeKeranjang(Menu m) {
        String input = JOptionPane.showInputDialog(this, "Beli berapa porsi " + m.getNama_menu() + "?", "1");
        
        if (input != null && !input.isEmpty()) {
            try {
                int qty = Integer.parseInt(input);
                if (qty <= 0) return;
                
                if (qty > m.getStok()) {
                    JOptionPane.showMessageDialog(this, "Stok kurang! Sisa: " + m.getStok());
                    return;
                }
                
                double subtotal = qty * m.getHarga();
                
                // Tambah ke Data List
                DetailTransaksi dt = new DetailTransaksi();
                dt.setMenu_id(m.getId());
                dt.setJumlah(qty);
                dt.setSubtotal(subtotal);
                keranjang.add(dt);
                
                // Tambah ke Tabel Visual
                DefaultTableModel model = (DefaultTableModel) tabelKeranjang.getModel();
                model.addRow(new Object[]{m.getId(), m.getNama_menu(), qty, subtotal});
                
                // Update Total Belanja
                totalBelanja += subtotal;
                lblTotal.setText("Rp " + (long)totalBelanja);
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Masukkan angka valid!");
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblIconLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblSelamatDatang = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        scrollKeranjang = new javax.swing.JScrollPane();
        tabelKeranjang = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        btnCheckout = new javax.swing.JButton();
        btnHapusItem = new javax.swing.JButton();
        tabbedMenu = new javax.swing.JTabbedPane();
        scrollSemua = new javax.swing.JScrollPane();
        panelSemua = new javax.swing.JPanel();
        scrollMakanan = new javax.swing.JScrollPane();
        panelMakanan = new javax.swing.JPanel();
        scrollMinuman = new javax.swing.JScrollPane();
        panelMinuman = new javax.swing.JPanel();
        scrollSnack = new javax.swing.JScrollPane();
        panelSnack = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(230, 126, 34));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(lblIconLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RestoKita Customer");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 300, 30));

        lblSelamatDatang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSelamatDatang.setForeground(new java.awt.Color(255, 255, 255));
        lblSelamatDatang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSelamatDatang.setText("Halo, [Nama User]");
        jPanel1.add(lblSelamatDatang, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 25, 220, 25));

        btnLogout.setBackground(new java.awt.Color(231, 76, 60));
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("KELUAR");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 20, 90, 35));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 70));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Keranjang Saya");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, -1, -1));

        tabelKeranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Menu", "Qty", "Subtotal"
            }
        ));
        scrollKeranjang.setViewportView(tabelKeranjang);

        getContentPane().add(scrollKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 120, 320, 350));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(230, 126, 34));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("Rp. 0");
        getContentPane().add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 480, 320, -1));

        btnCheckout.setBackground(new java.awt.Color(39, 174, 96));
        btnCheckout.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCheckout.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckout.setLabel("PESAN SEKARANG");
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });
        getContentPane().add(btnCheckout, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 530, 320, 50));

        btnHapusItem.setBackground(new java.awt.Color(231, 76, 60));
        btnHapusItem.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusItem.setText("Hapus Item Terpilih");
        btnHapusItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusItemActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapusItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 590, 320, 30));

        panelSemua.setBackground(new java.awt.Color(255, 255, 255));
        scrollSemua.setViewportView(panelSemua);

        tabbedMenu.addTab("Semua Menu", scrollSemua);

        panelMakanan.setBackground(new java.awt.Color(255, 255, 255));
        scrollMakanan.setViewportView(panelMakanan);

        tabbedMenu.addTab("Makanan", scrollMakanan);

        panelMinuman.setBackground(new java.awt.Color(255, 255, 255));
        scrollMinuman.setViewportView(panelMinuman);

        tabbedMenu.addTab("Minuman", scrollMinuman);

        panelSnack.setBackground(new java.awt.Color(255, 255, 255));
        scrollSnack.setViewportView(panelSnack);

        tabbedMenu.addTab("Snack", scrollSnack);

        getContentPane().add(tabbedMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 700, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        if (keranjang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Keranjang masih kosong!");
            return;
        }

        Transaksi t = new Transaksi();
        t.setUser_id(currentUser.getId());
        t.setTotal_harga(totalBelanja);

        TransaksiController tc = new TransaksiController();
        if (tc.simpanTransaksi(t, keranjang)) {
            JOptionPane.showMessageDialog(this, "Pesanan Berhasil!");
            keranjang.clear();
            ((DefaultTableModel)tabelKeranjang.getModel()).setRowCount(0);
            totalBelanja = 0;
            lblTotal.setText("Rp 0");
        } else {
            JOptionPane.showMessageDialog(this, "Gagal Checkout!");
        }
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void btnHapusItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusItemActionPerformed
        int baris = tabelKeranjang.getSelectedRow();
        if (baris != -1) {
            double sub = Double.parseDouble(tabelKeranjang.getValueAt(baris, 3).toString());
            totalBelanja -= sub;
            lblTotal.setText("Rp " + (long)totalBelanja);
            
            keranjang.remove(baris);
            ((DefaultTableModel)tabelKeranjang.getModel()).removeRow(baris);
        } else {
             JOptionPane.showMessageDialog(this, "Pilih item yang mau dihapus!");
        }
    }//GEN-LAST:event_btnHapusItemActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Register().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnHapusItem;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblIconLogo;
    private javax.swing.JLabel lblSelamatDatang;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel panelMakanan;
    private javax.swing.JPanel panelMinuman;
    private javax.swing.JPanel panelSemua;
    private javax.swing.JPanel panelSnack;
    private javax.swing.JScrollPane scrollKeranjang;
    private javax.swing.JScrollPane scrollMakanan;
    private javax.swing.JScrollPane scrollMinuman;
    private javax.swing.JScrollPane scrollSemua;
    private javax.swing.JScrollPane scrollSnack;
    private javax.swing.JTabbedPane tabbedMenu;
    private javax.swing.JTable tabelKeranjang;
    // End of variables declaration//GEN-END:variables
}
