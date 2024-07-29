
package pgpProject;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pgp_algo.error;

public class ui extends javax.swing.JFrame  implements  ActionListener {

    private PrivateKey privateKey;
private PublicKey publicKey;

 private JFileChooser fileChooser;
 private JButton encryptButton;
 private JButton decryptButton;
    public ui() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        try {
  KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
  generator.initialize(2048);
  KeyPair pair = generator.generateKeyPair();
  privateKey = pair.getPrivate();
  publicKey = pair.getPublic();
    } catch (Exception ignored) {
   }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        integrity = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("DialogInput", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DOCUMENT CONFIDENTIALITY");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 1160, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 180));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("DialogInput", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CLICK HERE TO DECRYPT FILE");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 625, 153));

        jLabel5.setFont(new java.awt.Font("DialogInput", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CLICK HERE TO ENCRYPT FILE");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 153));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 190, 1570, 190));

        integrity.setBackground(new java.awt.Color(153, 153, 153));
        integrity.setFont(new java.awt.Font("DialogInput", 1, 48)); // NOI18N
        integrity.setForeground(new java.awt.Color(255, 255, 255));
        integrity.setText(">>CHECK INTEGRITY<<");
        integrity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        integrity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                integrityActionPerformed(evt);
            }
        });
        jPanel1.add(integrity, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 530, 580, 180));

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("DialogInput", 1, 48)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("DECRYPT");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 380, 310, 80));

        jButton4.setBackground(new java.awt.Color(153, 153, 153));
        jButton4.setFont(new java.awt.Font("DialogInput", 1, 48)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("ENCRYPT");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 310, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1583, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 806, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(rootPane,"Select a file to decrypt title","DECRYPTION",JOptionPane.INFORMATION_MESSAGE);
        error  er=new error();
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void integrityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_integrityActionPerformed
        JOptionPane.showMessageDialog(rootPane,"Choose two files to check integrity","CHECK INTEGRITY",JOptionPane.PLAIN_MESSAGE);
        this.setVisible(true);
        String path1 = null,path2 = null;
        fileChooser=new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
                // Call your RSA encryption function with the selected file
                JOptionPane.showMessageDialog(rootPane, "CHOOSE A FILE","CHOOSE FILE",JOptionPane.DEFAULT_OPTION);
                path1 = fileChooser.getSelectedFile().getPath();
        }
        fileChooser=new JFileChooser();
        int res = fileChooser.showOpenDialog(this);
        if(res == JFileChooser.APPROVE_OPTION)
        {
            JOptionPane.showMessageDialog(rootPane, "CHOOSE A FILE","CHOOSE FILE",JOptionPane.DEFAULT_OPTION);
                path2=fileChooser.getSelectedFile().getPath();
        
        }
        md5 ch=new md5(path1,path2);
        System.out.println(path1+" "+path2);
        int inte=md5.Integrity();
        if(inte==1)
        {
             this.setVisible(true);
            JOptionPane.showMessageDialog(rootPane,"INTEGRITY ACHIEVED","CHECK INTEGRITY",JOptionPane.INFORMATION_MESSAGE);
           
        }   
        else
        {
             this.setVisible(true);
            JOptionPane.showMessageDialog(rootPane,"INTEGRITY NOT ACHIEVED(file content might have changed PLEASE CHECK YOUR FILES)", "INTEGRITY  FAILED",JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_integrityActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        error er=new error();
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the encrypt button was clicked
    /*    if(e.getSource()==integrity)
        {
            JFileChooser file = null;
            int result=file.showOpenDialog(this);
            if(result== JFileChooser.APPROVE_OPTION)
            {
                String filePath=file.getSelectedFile().
            }
        }*/
        if (e.getSource() == encryptButton) {
            // Get the selected file from the file chooser
            fileChooser=new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                // Call your RSA encryption function with the selected file
                String filePath = fileChooser.getSelectedFile().getPath();
                // Call your RSA encryption function with the file path
                // For example:
                // String encryptedText = rsa.encrypt(readFile(filePath));
                try{
                FileInputStream fin=new FileInputStream(filePath);
                byte[] mess=fin.readAllBytes();
                String encryptedMessage=encrypt(mess);
                FileOutputStream fout=new FileOutputStream(filePath);
                fout.write(encryptedMessage.getBytes());
                System.out.println(encryptedMessage);
                }
                catch(Exception actionEncryption)
                {
                    System.out.println("I am from action encryption"+actionEncryption);
                }
            }
        }
        System.err.println(e.getSource());
        if(e.getSource()==decryptButton)
        {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                // Call your RSA encryption function with the selected file
                String filePath = fileChooser.getSelectedFile().getPath();
                // Call your RSA encryption function with the file path
                // For example:
                // String encryptedText = rsa.encrypt(readFile(filePath));
                try{
                    
                FileInputStream fde=new FileInputStream(filePath);
                byte[] mess=fde.readAllBytes();
                String encryptedMessage=mess.toString();
                System.out.println(encryptedMessage);
                
                FileReader fr=new FileReader(filePath);
                
                BufferedReader br =new BufferedReader(fr);
                
                int i=0;
                char[] c = new char[9000];
                StringBuilder sb=new StringBuilder();
                while(br.ready())
                {
                    c[i]=(char)br.read();
                    sb.append(c[i]);
                    i++;
                }
                System.out.println(sb);
              

                
                
                String decryptedMessage=decrypt((sb).toString());
               // File output=new File(filePath);
               System.out.println(decryptedMessage);
                FileOutputStream fout=new FileOutputStream(filePath);
                fout.write(decryptedMessage.getBytes());
                fout.close();
                }
                catch(Exception actiondecryption)
                {
                    System.out.println("I am from action decryption"+actiondecryption);
                }
        }
    }
 }

public String encrypt(byte[] message) throws Exception{
  //byte[] messageToBytes = message.getBytes();
  Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
  cipher.init(Cipher.ENCRYPT_MODE,publicKey);
  byte[] encryptedBytes = cipher.doFinal(message);
  return encode(encryptedBytes);
  }
private String encode(byte[] data){//TRYENCODESTRING
  return Base64.getUrlEncoder().encodeToString(data);
  //String encodedEmail = new String(Base64.getEncoder().encode(
              //  user.getEmail().getBytes(StandardCharsets.UTF_8)));
   //JsonObject obj = Json.createReader(new ByteArrayInputStream(Base64.getDecoder().decode(accessToken.split("\\.")[1].
                        //replace('-', '+').replace('_', '/')))).readObject();

  }

public String decrypt(String encryptedMessage) throws Exception{
  byte[] encryptedBytes = decode(encryptedMessage);
  Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
  cipher.init(Cipher.DECRYPT_MODE,privateKey);
  byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
  return new String(decryptedMessage,"UTF8");
  }
private byte[] decode(String data){
  return Base64.getUrlDecoder().decode(data);
  }



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
            java.util.logging.Logger.getLogger(ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ui().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton integrity;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
