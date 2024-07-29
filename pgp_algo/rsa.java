package pgp_algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyPair;
import java.util.Base64;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.swing.JOptionPane;

/**
 *
 * @author Ajithaa Malini
 */
public class rsa {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    
    // First I'm assigning value to the defined pgp keys  using a keypairGenerator  using  rsa 
    public rsa()
    {
        try
        {
            KeyPairGenerator  generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair pair=generator.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
        }
        catch(Exception ignored)
        {
        }
    }
    
    //nxt create encode() and decode() function  that will  be  used for encoding and decoding the  message withbase64(bin to txt) encoder  and decoder
    private String  encode(byte[]  data)
    {
        return Base64.getEncoder().encodeToString(data);
    }
    
    private  byte [] decode(String  data)
    {
        return Base64.getDecoder().decode(data);
    }
    
    /* nxt Encrypt() function in  the rsa class
        -It  converts the message to bytes as the specified encryption can be done using a byte array
        -Then specify the algorithm block mode, padding as "RSA/ECB?PKCS!Padding" for a "cipher" object
        -Then initialize cipher with "ENCRYPT_MODE" and the PGP  "publicKey"
    */
    public byte[] encrypt(byte[] message) throws Exception
    {
       
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[] encryptedBytes = cipher.doFinal(message);
        return (encryptedBytes);
    }
    
    /* Now DECRYPT
        -decrypt()  function to decode the strings that will use our PGP 'private key'to decrypt the messagein "DECRYPTED_MODE" and return the decrypted array
    */
     
    public String decrypt(String encryptedMessage) throws Exception
    {
       byte[] encryptedBytes = decode(encryptedMessage);
       Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
       cipher.init(Cipher.DECRYPT_MODE,privateKey);
       byte[] decryptedMessage=cipher.doFinal(encryptedBytes);
       return new  String(decryptedMessage,"UTF8");
    }
    
    //Main  Function:
    
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception
    {
        rsa RSA= new rsa();
        try{
         /*   String encryptedMessage = RSA.encrypt("Ajithaa Malini");
            String  decryptedMessage= RSA.decrypt(encryptedMessage);
            
            System.out.println("Encrypted:\n"+encryptedMessage);
            System.out.println("Decrypted:\n"+decryptedMessage);
        }
        catch  (  Exception ignored)
        {
            ignored.printStackTrace();
            System.out.println("ERROER\n"+ignored.getMessage());
        }*/
        File inputFile = new File("C:/Users/ponth/OneDrive/Documents/new1.txt");
        byte[] inputBytes = Files.readAllBytes(inputFile.toPath());

        // Encrypt file contents
            byte[] encryptedBytes = RSA.encrypt(inputBytes);

        // Write encrypted file contents to new file
        File outputFile = new File("C:/Users/ponth/OneDrive/Documents/dec.txt");
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(encryptedBytes);
        outputStream.close();

        // Print success message
        System.out.println("File encrypted successfully!");
    } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
    }
        try
        {
            FileReader fr=new FileReader("C:/Users/ponth/OneDrive/Documents/dec.txt");
            FileWriter fw=new FileWriter("C:/Users/ponth/OneDrive/Documents/new.txt");
            String str="";
            int i = 0;
            while((i-fr.read())!=-1)
            {
                str+=(char)i;
            }
            System.out.println(str);
            String de=RSA.decrypt(str);
            System.out.println(de);
            fw.write(str);
            fw.write("Ajithaa");
            fr.close();
            fw.close();
        }
        catch(IOException e)
        {
           // JOptionPane.showMessageDialog(rootPane, "Failes","Fail",JOptionPane.ERROR_MESSAGE);
        }
        }
    }




