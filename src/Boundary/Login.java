
package Boundary;

import Controller.JDBC;
import Entity.Administrator;
import Entity.Librarian;
import com.sun.glass.events.KeyEvent;
import java.awt.Point;
import javax.swing.JOptionPane;

/**
 *
 * @author biruk
 */
public class Login extends javax.swing.JFrame {

    JDBC conn = new JDBC();
    
    LibrarianHomePage librarian;
    AdministratorHomePage administrator;
    
    public Login() {
        initComponents();
       
    }
    
    public void showErrorMessage()
    {
        ErrorMessage.setText("Invalid Username/Password!");
        ForgetButton.setEnabled(true);
    }
    
    public LibrarianHomePage getLibrarianHomePage() {
        return librarian;
    }
    
    public AdministratorHomePage getAdministratorHomePage() {
        return administrator;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Username = new javax.swing.JTextField();
        Password = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ForgetButton = new javax.swing.JButton();
        ErrorMessage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1300, 700));
        setMinimumSize(new java.awt.Dimension(1300, 700));
        setPreferredSize(new java.awt.Dimension(1300, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameActionPerformed(evt);
            }
        });
        Username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsernameKeyPressed(evt);
            }
        });
        getContentPane().add(Username);
        Username.setBounds(290, 400, 320, 30);

        Password.setFocusTraversalPolicyProvider(true);
        Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordActionPerformed(evt);
            }
        });
        Password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PasswordKeyPressed(evt);
            }
        });
        getContentPane().add(Password);
        Password.setBounds(290, 450, 320, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/sign-check-icon.png"))); // NOI18N
        jButton1.setText("Log In");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(500, 500, 110, 40);

        jLabel2.setFont(new java.awt.Font("Century Schoolbook L", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Username");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(150, 400, 100, 30);

        jLabel3.setFont(new java.awt.Font("Century Schoolbook L", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("Password");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(150, 450, 110, 30);

        ForgetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Status-dialog-password-icon.png"))); // NOI18N
        ForgetButton.setText("Forget Password");
        ForgetButton.setEnabled(false);
        ForgetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ForgetButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ForgetButton);
        ForgetButton.setBounds(312, 500, 170, 40);

        ErrorMessage.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        ErrorMessage.setForeground(new java.awt.Color(255, 0, 0));
        ErrorMessage.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        getContentPane().add(ErrorMessage);
        ErrorMessage.setBounds(290, 350, 320, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/aaitBG3.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1300, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameActionPerformed

    private void PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try
        {
            String username = Username.getText();
            String password = Password.getText();
            
            Object user = conn.login(username, password);
                   
            if (user == null)
            {
                showErrorMessage();
            }
            
            else if (user.getClass().toString().equals("class Entity.Librarian"))
            {
                dispose();
                librarian = new LibrarianHomePage((Librarian)user);
                librarian.setVisible(true);
            }
            
            else if (user.getClass().toString().equals("class Entity.Administrator"))
            {
                dispose();
                administrator = new AdministratorHomePage((Administrator)user);
                administrator.setVisible(true);
            }
            
            else
            {
                showErrorMessage();
            }
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ForgetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ForgetButtonActionPerformed
        try
        {
            ForgetPassword reset = new ForgetPassword();
            reset.setVisible(true);
        }
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ForgetButtonActionPerformed

    private void UsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsernameKeyPressed
        
    }//GEN-LAST:event_UsernameKeyPressed

    private void PasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordKeyPressed
        try
        {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                String username = Username.getText();
                String password = Password.getText();

                Object user = conn.login(username, password);

                if (user == null)
                {
                    showErrorMessage();
                }

                else if (user.getClass().toString().equals("class Entity.Librarian"))
                {
                    dispose();
                    LibrarianHomePage librarian = new LibrarianHomePage((Librarian)user);
                    librarian.setVisible(true);
                }

                else if (user.getClass().toString().equals("class Entity.Administrator"))
                {
                    dispose();
                    AdministratorHomePage admin = new AdministratorHomePage((Administrator)user);
                    admin.setVisible(true);
                }

                else
                {
                    showErrorMessage();
                }
            }
        }
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_PasswordKeyPressed

    /**
     * @param args the command line arguments
     */
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ErrorMessage;
    private javax.swing.JButton ForgetButton;
    private javax.swing.JPasswordField Password;
    private javax.swing.JTextField Username;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}