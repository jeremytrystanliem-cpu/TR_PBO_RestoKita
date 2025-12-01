package View;

// IMPORT LOGIKA & MODEL (PENTING)
import Controller.UserController;
import Model.User;
import javax.swing.JOptionPane;

// IMPORT TAMPILAN
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends javax.swing.JFrame {

    // Constructor
    public Login() {
        initComponents();
        
        // 1. Posisi Tengah Layar
        this.setLocationRelativeTo(null);
        
        // 2. MUAT GAMBAR LOGO
        try {
            ImageIcon iconAsli = new ImageIcon("images/RestoKita.png");
            
            if (iconAsli.getIconWidth() > -1) {
                Image gambarAsli = iconAsli.getImage();

                // Ukuran Logo 150x150
                int sisi = 150; 
                Image gambarScale = gambarAsli.getScaledInstance(sisi, sisi, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(gambarScale));
                
                lblLogo.setText(""); 
                lblLogo.setHorizontalAlignment(JLabel.CENTER);
            } else {
                System.out.println("File gambar tidak ditemukan di folder images!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblJudul = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - RestoKita");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(253, 250, 246));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 400, 150));

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(44, 62, 80));
        lblJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJudul.setText("Selamat Datang");
        jPanel1.add(lblJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 400, -1));

        jLabel2.setText("Password");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 300, -1));

        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 300, 40));

        jLabel3.setText("Username");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 300, -1));
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 300, 40));

        btnLogin.setBackground(new java.awt.Color(230, 126, 34));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Masuk");
        btnLogin.setBorder(null);
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 300, 45));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(100, 100, 100));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Belum punya akun?");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 400, -1));

        btnRegister.setForeground(new java.awt.Color(39, 174, 96));
        btnRegister.setText("Daftar");
        btnRegister.setBorderPainted(false);
        btnRegister.setContentAreaFilled(false);
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 500, 200, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        this.dispose(); // Tutup Login
        new Register().setVisible(true); // Buka Register
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
// AMBIL INPUT
        String u = txtUsername.getText();
        String p = new String(txtPassword.getPassword()); // PasswordField pakai getPassword
        
        // 1. Validasi Input Kosong
        if(u.isEmpty() || p.isEmpty()){
            JOptionPane.showMessageDialog(this, "Username dan Password harus diisi!");
            return;
        }
        
        // 2. Panggil Controller
        UserController uc = new UserController();
        User userLogin = uc.cekLogin(u, p);
        
        // 3. Cek Hasil
        if (userLogin != null) {
            JOptionPane.showMessageDialog(this, "Login Berhasil! Halo " + userLogin.getNama_lengkap());
            this.dispose(); // Tutup Login
            
            // LOGIKA PINDAH HALAMAN
            String role = userLogin.getRole();
            
            if (role.equalsIgnoreCase("Admin")) {
                 new Admin().setVisible(true);
            } else if (role.equalsIgnoreCase("Kasir")) {
                 new Kasir().setVisible(true);
            } else if (role.equalsIgnoreCase("Customer")) {
                 new Customer(userLogin).setVisible(true);
            } else {
                 new Manajer().setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Username atau Password Salah!", "Gagal", JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginActionPerformed

    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
