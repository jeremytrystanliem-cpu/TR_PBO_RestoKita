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

    // VARIABEL GLOBAL
    User currentUser; 
    List<DetailTransaksi> keranjang = new ArrayList<>();
    double totalBelanja = 0;

    public Customer() {
        initComponents();
    }

    // CONSTRUCTOR 
    public Customer(User u) {
        initComponents();
        this.currentUser = u;
        this.setLocationRelativeTo(null);
        
        // Tampilkan Nama User
        if(u != null) {
            lblSelamatDatang.setText("Halo, " + u.getNama_lengkap());
        } else {
             lblSelamatDatang.setText("Halo, Tamu");
        }
        
        // Load Logo 
        try {
            ImageIcon iconAsli = new ImageIcon("images/RestoKita.png");
            if (iconAsli.getIconWidth() > -1) {
                Image img = iconAsli.getImage();
                Image imgScale = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                lblIconLogo.setIcon(new ImageIcon(imgScale));
                lblIconLogo.setText("");
            }
        } catch (Exception e) {
            System.out.println("Logo header tidak ketemu");
        }
        
        // Panggil Method Helper 
        siapkanTabelKeranjang();
        loadMenuBergambar();
    }
    
    // METHOD HELPER 

    private void siapkanTabelKeranjang() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Menu");
        model.addColumn("Qty");
        model.addColumn("Subtotal");
        tabelKeranjang.setModel(model); 
    }

    // Logic Menampilkan Gambar Grid
    private void loadMenuBergambar() {
        panelMenuContainer.removeAll(); // Reset Panel
        
        MenuController mc = new MenuController();
        List<Menu> daftarMenu = mc.getAllMenu(); // Ambil data
        
        // Set Grid Layout (Auto Baris, 3 Kolom)
        panelMenuContainer.setLayout(new GridLayout(0, 3, 10, 10)); 

        for (Menu m : daftarMenu) {
            // Buat Kartu
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            card.setPreferredSize(new Dimension(180, 230));

            // Gambar
            JLabel lblGbr = new JLabel("", JLabel.CENTER);
            try {
                ImageIcon icon = new ImageIcon("images/" + m.getGambar());
                Image img = icon.getImage().getScaledInstance(180, 150, Image.SCALE_SMOOTH);
                lblGbr.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                lblGbr.setText("No Image");
            }
            
            // Info Bawah
            JPanel infoPanel = new JPanel(new GridLayout(3, 1));
            infoPanel.setBackground(Color.WHITE);
            
            JLabel lblNama = new JLabel(m.getNama_menu(), JLabel.CENTER);
            lblNama.setFont(new Font("Segoe UI", Font.BOLD, 14));
            
            JLabel lblHarga = new JLabel("Rp " + (long)m.getHarga(), JLabel.CENTER);
            lblHarga.setForeground(new Color(230, 126, 34)); // Orange
            
            JButton btnAdd = new JButton("TAMBAH");
            btnAdd.setBackground(new Color(39, 174, 96)); // Hijau
            btnAdd.setForeground(Color.WHITE);
            btnAdd.setFocusPainted(false);
            
            // Aksi Tombol Tambah
            btnAdd.addActionListener(e -> tambahKeKeranjang(m));

            infoPanel.add(lblNama);
            infoPanel.add(lblHarga);
            infoPanel.add(btnAdd);
            
            card.add(lblGbr, BorderLayout.CENTER);
            card.add(infoPanel, BorderLayout.SOUTH);
            
            panelMenuContainer.add(card);
        }
        
        panelMenuContainer.revalidate();
        panelMenuContainer.repaint();
    }

    // Logic Tambah Item ke Keranjang
    private void tambahKeKeranjang(Menu m) {
        String input = JOptionPane.showInputDialog(this, "Beli berapa porsi " + m.getNama_menu() + "?", "1");
        
        if (input != null && !input.isEmpty()) {
            try {
                int qty = Integer.parseInt(input);
                if (qty > m.getStok()) {
                    JOptionPane.showMessageDialog(this, "Stok kurang! Sisa: " + m.getStok());
                    return;
                }
                
                double subtotal = qty * m.getHarga();
                
                // Masukkan ke List (Backend)
                DetailTransaksi dt = new DetailTransaksi();
                dt.setMenu_id(m.getId());
                dt.setJumlah(qty);
                dt.setSubtotal(subtotal);
                keranjang.add(dt);
                
                // Masukkan ke Tabel (Visual)
                DefaultTableModel model = (DefaultTableModel) tabelKeranjang.getModel();
                model.addRow(new Object[]{m.getId(), m.getNama_menu(), qty, subtotal});
                
                // Update Label Total
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
        scrollMenu = new javax.swing.JScrollPane();
        panelMenuContainer = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        scrollKeranjang = new javax.swing.JScrollPane();
        tabelKeranjang = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        btnCheckout = new javax.swing.JButton();
        btnHapusItem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1100, 700));
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

        panelMenuContainer.setBackground(new java.awt.Color(255, 255, 255));
        panelMenuContainer.setLayout(new java.awt.GridLayout(1, 0));
        scrollMenu.setViewportView(panelMenuContainer);

        getContentPane().add(scrollMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 700, 550));

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
    private javax.swing.JPanel panelMenuContainer;
    private javax.swing.JScrollPane scrollKeranjang;
    private javax.swing.JScrollPane scrollMenu;
    private javax.swing.JTable tabelKeranjang;
    // End of variables declaration//GEN-END:variables
}
