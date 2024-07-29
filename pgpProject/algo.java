
package pgpProject;

/**
 *
 * @author ponth
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ponth
 */
import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class algo{
private PrivateKey privateKey;
private PublicKey publicKey;

public algo() {
try {
  KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
  generator.initialize(1024);
  KeyPair pair = generator.generateKeyPair();
  privateKey = pair.getPrivate();
  publicKey = pair.getPublic();
    } catch (Exception ignored) {
   }
  }
public String encrypt(byte[] message) throws Exception{
  //byte[] messageToBytes = message.getBytes();
  Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
  cipher.init(Cipher.ENCRYPT_MODE,publicKey);
  byte[] encryptedBytes = cipher.doFinal(message);
  return encode(encryptedBytes);
  }
private String encode(byte[] data){
  return Base64.getEncoder().encodeToString(data);
  }

public String decrypt(String encryptedMessage) throws Exception{
  byte[] encryptedBytes = decode(encryptedMessage);
  Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
  cipher.init(Cipher.DECRYPT_MODE,privateKey);
  byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
  return new String(decryptedMessage,"UTF8");
  }
private byte[] decode(String data){
  return Base64.getDecoder().decode(data);
  }

public static void main(String[] args) throws FileNotFoundException, IOException {
    File input=new File("C:/Users/ponth/OneDrive/Documents/new1.txt");
    File output=new File("C:/Users/ponth/OneDrive/Documents/decrypted.txt");
    FileInputStream fin=new FileInputStream(input);
    byte[] fi=fin.readAllBytes();
 algo rsa = new algo();
 try{
  String encryptedMessage = rsa.encrypt(fi);
  String decryptedMessage = rsa.decrypt(encryptedMessage);

  System.err.println("Encrypted:\n"+encryptedMessage);
  System.err.println("Decrypted:\n"+decryptedMessage);
  System.out.println(encryptedMessage.getBytes());
 /* FileOutputStream fout=new FileOutputStream(output);
  fout.write(decryptedMessage.getBytes());
 
  FileWriter fw;
        try (FileReader fr = new FileReader(input)) {
            fw = new FileWriter(output);
            String str="";
            int i;
            while((i=fr.read())!=-1)
            {
                str+=(char)i;
            }         String de=rsa.decrypt(str);
            fw.write(str);
        }
  fw.close();
  fout.close();*/
  }catch (Exception ignored){}
 }
}
