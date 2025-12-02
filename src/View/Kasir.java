package View;

import Controller.TransaksiController;

import javax.swing.ImageIcon; 
import java.awt.Image;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Kasir extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Kasir.class.getName());

    public Kasir() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        try {
            ImageIcon iconAsli = new ImageIcon("images/RestoKita.png");
            if (iconAsli.getIconWidth() > -1) {
                Image img = iconAsli.getImage();
                // Resize ke 50x50 px agar pas di header
                Image imgScale = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                
                lblIconLogo.setIcon(new ImageIcon(imgScale));
            }
        } catch (Exception e) {
            System.out.println("Logo header tidak ketemu: " + e.getMessage());
        }
        
        loadDataTransaksi();
    }
    
    // METHOD HELPER: LOAD DATA TABEL KIRI
    private void loadDataTransaksi() {
        try {
            TransaksiController tc = new TransaksiController();
            DefaultTableModel modelBaru = tc.getDaftarTransaksiModel();
            tabelTransaksi.setModel(modelBaru);
        } catch (Exception e) {
            System.out.println("Gagal load data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        lblIconLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTransaksi = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        lblIdTransaksi = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelDetail = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        btnProses = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(230, 126, 34));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("RestoKita Kasir");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        btnLogout.setBackground(new java.awt.Color(231, 76, 60));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("KELUAR");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 90, 35));
        jPanel1.add(lblIconLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 70));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Daftar Transaksi Masuk");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        tabelTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabelTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelTransaksi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 500, 400));

        btnRefresh.setBackground(new java.awt.Color(52, 152, 219));
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Muat Ulang");
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        getContentPane().add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 150, 35));

        lblIdTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblIdTransaksi.setText("Detail Pesanan ID: ...");
        getContentPane().add(lblIdTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, -1, -1));

        tabelDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Menu", "Qty", "Harga"
            }
        ));
        jScrollPane2.setViewportView(tabelDetail);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, 400, 250));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(39, 174, 96));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("Total: Rp 0");
        lblTotal.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblTotal.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, 390, 400));

        btnProses.setBackground(new java.awt.Color(39, 174, 96));
        btnProses.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProses.setForeground(new java.awt.Color(255, 255, 255));
        btnProses.setText("SET STATUS: LUNAS / SELESAI");
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });
        getContentPane().add(btnProses, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 430, 400, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // LOGIC TOMBOL PROSES
    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
        int baris = tabelTransaksi.getSelectedRow();
        if (baris != -1) {
            int idTransaksi = Integer.parseInt(tabelTransaksi.getValueAt(baris, 0).toString());
            
            int confirm = JOptionPane.showConfirmDialog(this, "Konfirmasi pembayaran untuk ID: " + idTransaksi + "?", "Bayar", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                TransaksiController tc = new TransaksiController();
                if(tc.updateStatusTransaksi(idTransaksi, "Lunas")) {
                    JOptionPane.showMessageDialog(this, "Pembayaran Berhasil! Status: LUNAS");
                    
                    loadDataTransaksi();
                    
                    ((DefaultTableModel)tabelDetail.getModel()).setRowCount(0);
                    btnProses.setEnabled(false);
                    lblIdTransaksi.setText("Detail Pesanan ID: -");
                    lblTotal.setText("Total: Rp 0");
                }
            }
        }
    }//GEN-LAST:event_btnProsesActionPerformed

    // LOGIC TOMBOL REFRESH
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadDataTransaksi();
    }//GEN-LAST:event_btnRefreshActionPerformed

    // LOGIC TOMBOL KELUAR
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void tabelTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTransaksiMouseClicked
        int baris = tabelTransaksi.getSelectedRow();
        if (baris != -1) {
            // 1. Ambil data dari Tabel GUI (Kolom 0=ID, 2=Total, 3=Status)
            int idTransaksi = Integer.parseInt(tabelTransaksi.getValueAt(baris, 0).toString());
            String total = tabelTransaksi.getValueAt(baris, 2).toString();
            String status = tabelTransaksi.getValueAt(baris, 3).toString();
            
            // 2. Update Label UI
            lblIdTransaksi.setText("Detail Pesanan ID: " + idTransaksi);
            lblTotal.setText("Total: " + total);
            
            // 3. Load Detail (KANAN) - Pake Method Controller Baru
            TransaksiController tc = new TransaksiController();
            // Langsung set model tabel dari controller (tidak perlu loop manual lagi)
            tabelDetail.setModel(tc.getDetailTransaksiModel(idTransaksi));
            
            // 4. Logic Tombol Proses
            if (status.equalsIgnoreCase("Pending")) {
                btnProses.setEnabled(true);
                btnProses.setText("PROSES PEMBAYARAN (LUNAS)");
                btnProses.setBackground(new java.awt.Color(39, 174, 96));
            } else {
                btnProses.setEnabled(false);
                btnProses.setText("SUDAH LUNAS");
                btnProses.setBackground(java.awt.Color.GRAY);
            }
        }   
    }//GEN-LAST:event_tabelTransaksiMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new Kasir().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIconLogo;
    private javax.swing.JLabel lblIdTransaksi;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tabelDetail;
    private javax.swing.JTable tabelTransaksi;
    // End of variables declaration//GEN-END:variables
}
