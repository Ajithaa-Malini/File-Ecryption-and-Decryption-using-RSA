
package pgp_algo;

//import org.apache.pdfbox.Loader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 *
 * @author ponth
 */
import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pgpProject.ui;

public class error extends JFrame implements ActionListener{
private PrivateKey privateKey;
private PublicKey publicKey;

 private JFileChooser fileChooser;
 private JButton encryptButton;
 private JButton decryptButton;
 private JButton Back;

public error() {
    // Create a file chooser and add it to a panel
       
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        // Create a button and add an action listener to it
        Back=new JButton("BACK");
        Back.addActionListener((ActionListener)this);
        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener((ActionListener) this);
        decryptButton=new JButton("Decrypt");
        decryptButton.addActionListener((ActionListener) this);

        panel.add(decryptButton);
        panel.add(encryptButton);
         fileChooser = new JFileChooser();
        panel.add(fileChooser);
        Dimension d=d=Toolkit.getDefaultToolkit().getScreenSize();
        // Add the panel to the frame
        this.add(panel);

        // Set the frame properties
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
try {
  KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
  generator.initialize(2048);
  KeyPair pair = generator.generateKeyPair();
  privateKey = pair.getPrivate();
  publicKey = pair.getPublic();
    } catch (Exception ignored) {
   }
  }



@Override
    public void actionPerformed(ActionEvent e) {
        // Check if the encrypt button was clicked
        if(e.getSource()==Back)
        {
            ui j=new ui();
            this.setVisible(false);
        }
        if (e.getSource() == encryptButton) {
            // Get the selected file from the file chooser
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
                JOptionPane.showMessageDialog(rootPane,"ENCRYPTION  SUCCESFULLY COMPLETED CHECK  YOUR  DOCUMENT", "SUCESSFULLY ENCRYPTED", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(encryptedMessage);
                }
                catch(Exception actionEncryption)
                {
                    System.out.println("I am from action encryption"+actionEncryption);
                    JOptionPane.showMessageDialog(rootPane,"ENCRYPTION  FAILED  PLEASE TRY  AGAIN", "ENCRYPTION UNSUCESSFUL", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(rootPane,"DECRYPTION  SUCCESFULLY COMPLETED CHECK  YOUR  DOCUMENT", "SUCESSFULLY DECRYPTED", JOptionPane.INFORMATION_MESSAGE);

                }
                catch(Exception actiondecryption)
                {
                   JOptionPane.showMessageDialog(rootPane,"DECRYPTION  FAILED  PLEASE TRY  AGAIN", "DECRYPTION  UNSUCESSFUL", JOptionPane.ERROR_MESSAGE);

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

/*private ByteBuffer decode(char[] data){
    CharBuffer charBuffer=CharBuffer.wrap(data);
    ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
    
  //return Base64.getDecoder.decode(data);
  return Base64.getDecoder().decode(byteBuffer);
  }*/

public static void main(String[] args) throws FileNotFoundException, IOException {
    File input=new File("C:/Users/ponth/OneDrive/Documents/new1.txt");
    File output=new File("C:/Users/ponth/OneDrive/Documents/dec.txt");
    FileInputStream fin=new FileInputStream(input);
    byte[] fi=fin.readAllBytes();
 error rsa = new error();
 try{
  String encryptedMessage = rsa.encrypt(fi);
  String decryptedMessage = rsa.decrypt(encryptedMessage);

  System.err.println("Encrypted:\n"+encryptedMessage);
  System.err.println("Decrypted:\n"+decryptedMessage);
  FileOutputStream fout=new FileOutputStream(output);
  fout.write(decryptedMessage.getBytes());

  fout.close();
  }catch (Exception ignored){}
 }
}



