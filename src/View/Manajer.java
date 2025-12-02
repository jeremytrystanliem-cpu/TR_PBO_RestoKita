package View;

import Controller.TransaksiController;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;

public class Manajer extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Manajer.class.getName());

    public Manajer() {
        initComponents();
        this.setLocationRelativeTo(null); 
        
        try {
            ImageIcon iconAsli = new ImageIcon("images/RestoKita.png");
            if (iconAsli.getIconWidth() > -1) {
                Image img = iconAsli.getImage();
                Image imgScale = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                lblIcon.setIcon(new ImageIcon(imgScale));
                lblIcon.setText("");
            }
        } catch (Exception e) {}
        
        loadDataLaporan();
    }
    
    // METHOD UNTUK LOAD DATA & HITUNG OMSET 
    private void loadDataLaporan() {
        double totalOmset = 0;
        int jumlahSukses = 0;
        
        try {
            TransaksiController tc = new TransaksiController();
            
            // 1. Ambil Model Tabel langsung dari Controller
            DefaultTableModel model = tc.getDaftarTransaksiModel();
            
            // 2. Pasang ke Tabel View
            tabelLaporan.setModel(model);
            
            // 3. Atur Lebar Kolom
            if (tabelLaporan.getColumnModel().getColumnCount() > 0) {
                tabelLaporan.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
                tabelLaporan.getColumnModel().getColumn(1).setPreferredWidth(150); // Nama
                tabelLaporan.getColumnModel().getColumn(2).setPreferredWidth(120); // Total
                tabelLaporan.getColumnModel().getColumn(3).setPreferredWidth(100); // Status
                tabelLaporan.getColumnModel().getColumn(4).setPreferredWidth(150); // Tanggal
            }

            // 4. HITUNG OMSET DARI ISI TABEL
            for (int i = 0; i < model.getRowCount(); i++) {
                String status = model.getValueAt(i, 3).toString(); 
                String totalStr = model.getValueAt(i, 2).toString(); 
                
                // Cek Status Lunas/Selesai
                if (status.equalsIgnoreCase("Lunas") || status.equalsIgnoreCase("Selesai")) {
                    jumlahSukses++;
                    
                    String angkaOnly = totalStr.replace("Rp ", "").replace(".", ""); 
                    try {
                        totalOmset += Double.parseDouble(angkaOnly);
                    } catch (NumberFormatException e) {
                        System.out.println("Gagal parse harga: " + totalStr);
                    }
                }
            }
            
            // 5. Update Label Kartu
            lblJmlTransaksi1.setText(String.valueOf(jumlahSukses));
            lblPendapatan.setText("Rp " + (long)totalOmset);
            
        } catch (Exception e) {
            System.out.println("Gagal load laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        panelCard2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblPendapatan = new javax.swing.JLabel();
        panelCard1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblJmlTransaksi1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelLaporan = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelHeader.setBackground(new java.awt.Color(230, 126, 34));
        panelHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelHeader.add(lblIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RestoKita Manajer");
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

        panelCard2.setBackground(new java.awt.Color(39, 174, 96));
        panelCard2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total Pendapatan (Omset)");
        panelCard2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, -1, -1));

        lblPendapatan.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblPendapatan.setForeground(new java.awt.Color(255, 255, 255));
        lblPendapatan.setText("Rp 0");
        panelCard2.add(lblPendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, -1, -1));

        getContentPane().add(panelCard2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 350, 100));

        panelCard1.setBackground(new java.awt.Color(52, 152, 219));
        panelCard1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total Transaksi Sukses");
        panelCard1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, -1, -1));

        lblJmlTransaksi1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblJmlTransaksi1.setForeground(new java.awt.Color(255, 255, 255));
        lblJmlTransaksi1.setText("0");
        panelCard1.add(lblJmlTransaksi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, -1, -1));

        getContentPane().add(panelCard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 300, 100));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Riwayat Semua Transaksi");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        tabelLaporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Customer", "Total Bayar", "Status", "Tanggal"
            }
        ));
        jScrollPane1.setViewportView(tabelLaporan);
        if (tabelLaporan.getColumnModel().getColumnCount() > 0) {
            tabelLaporan.getColumnModel().getColumn(0).setResizable(false);
            tabelLaporan.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelLaporan.getColumnModel().getColumn(1).setResizable(false);
            tabelLaporan.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabelLaporan.getColumnModel().getColumn(2).setResizable(false);
            tabelLaporan.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabelLaporan.getColumnModel().getColumn(3).setResizable(false);
            tabelLaporan.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabelLaporan.getColumnModel().getColumn(4).setResizable(false);
            tabelLaporan.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 940, 300));

        btnRefresh.setBackground(new java.awt.Color(52, 152, 219));
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh Laporan");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        getContentPane().add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 150, 35));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadDataLaporan();
    }//GEN-LAST:event_btnRefreshActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Manajer().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblJmlTransaksi1;
    private javax.swing.JLabel lblPendapatan;
    private javax.swing.JPanel panelCard1;
    private javax.swing.JPanel panelCard2;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JTable tabelLaporan;
    // End of variables declaration//GEN-END:variables
}
