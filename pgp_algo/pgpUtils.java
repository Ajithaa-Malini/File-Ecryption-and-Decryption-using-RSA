package pgp_algo;
import java.nio.*;
import java.nio.channels.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.KeyPair;
import java.util.Base64;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

class JavaFileFilter extends FileFilter{
        @Override
	public boolean accept(File file)
	{
		if(file.getName().endsWith(".txt")) return true;
		if(file.getName().endsWith(".java")) return true;
		if(file.isDirectory()) return true;
		return false;
	}
        @Override
	public String getDescription()
	{
		return "Java and Text file for Encryption and Dencryption";
	}
}

class SaveJavaFileFilter extends FileFilter
 {
    @Override
    public boolean accept(File f)
   {
        if (f.isDirectory())
        	return true;

         String s = f.getName();

        return s.endsWith(".java");
   }

    @Override
   public String getDescription()
  {
       return "*.java";
  }

}

class SaveTextFileFilter extends FileFilter
 {
    @Override
    public boolean accept(File f)
   {
        if (f.isDirectory())
        	return true;
         String s = f.getName();

        return s.endsWith(".txt");
   }

    @Override
   public String getDescription()
  {
       return "*.txt";
  }

}
public class pgpUtils extends JFrame implements ActionListener{
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private File InputFile;
    public byte[] message;
    
    public JButton browse,enc,denc,cancel;
    private JLabel label;
    private JTextField filename;
    private JFileChooser jfc;
    private File file;
    Dimension d = null;
    FileInputStream fin;
    FileOutputStream fout;
    FileChannel fichan,fochan;
    long fsize;
    ByteBuffer mbuf,ombuf;
    long key=0;
    String ext="";
    JScrollPane displayScrollPane;
    ImageIcon image;
    int wdth,hight;
    
    public pgpUtils()
    {
        super("Encryption and Decryption Software");
        try
        {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair pair=generator.generateKeyPair();
            privateKey =pair.getPrivate();
            publicKey = pair.getPublic();
        }
        catch(NoSuchAlgorithmException e)
        {
        }
        
        
        
        //UI DESIGN
        
    	d=Toolkit.getDefaultToolkit().getScreenSize();
    	setBackground(Color.yellow);
		setForeground(Color.red);
    	wdth=d.width/2;
    	hight=d.height/2;
        setSize(wdth, hight);
        setLocation(d.width/4, d.height/4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        label = new JLabel("CHOOSE THE TEXT OR JAVA FILE FOR ENCRYPTION OR DECRYPTION");
        filename = new JTextField(50);
        filename.setEditable(false);
        browse=new JButton("Browse");
        browse.setForeground(Color.BLUE);
        enc=new JButton("Encrypt");
        enc.setForeground(Color.BLUE);
        enc.setEnabled(false);
        denc=new JButton("Dencrypt");
        denc.setForeground(Color.BLUE);
        denc.setEnabled(false);
        cancel=new JButton("Cancel");
        cancel.setForeground(Color.RED);
        jfc=new JFileChooser();
        jfc.setFileFilter(new JavaFileFilter());
        JPanel buttonPanel1 = new JPanel();
        JPanel buttonPanel2 = new JPanel();
        JPanel loadPanel = new JPanel();
        buttonPanel1.setPreferredSize(new Dimension(100,100));
        buttonPanel2.setPreferredSize(new Dimension(100,100));
        loadPanel.setPreferredSize(new Dimension(100,100));
        buttonPanel1.setOpaque(true);
		buttonPanel2.setOpaque(true);
		loadPanel.setOpaque(true);
		loadPanel.setBackground(Color.yellow);
		buttonPanel1.setBackground(Color.green);
		buttonPanel1.setForeground(Color.red);
		buttonPanel2.setBackground(Color.green);
		buttonPanel2.setForeground(Color.red);
		buttonPanel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		buttonPanel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		image=new ImageIcon("logo.GIF");
JLabel im= new JLabel(image);
loadPanel.add(im);

		buttonPanel1.add(label);
        buttonPanel1.add(filename);
        buttonPanel1.add(browse);
        buttonPanel2.add(enc);
        buttonPanel1.add(denc);
        buttonPanel2.add(cancel);
        label.setBounds(105, 10, 400, 25);
       	browse.addActionListener(this);
       	enc.addActionListener(this);
       	denc.addActionListener(this);
       	cancel.addActionListener(this);
       	add(buttonPanel1, BorderLayout.NORTH);
        add(loadPanel, BorderLayout.CENTER);
        add(buttonPanel2, BorderLayout.SOUTH);
        setVisible(true);
        
    }
    public pgpUtils(File in) throws FileNotFoundException
    {
        
    }
    
    private long enterKey() throws IOException
{
	long k=0;
	String key=JOptionPane.showInputDialog(null,"Enter the Key","SECURE KEYS",JOptionPane.QUESTION_MESSAGE);
	long intKey=(long)Integer.parseInt(key);
	long temp=intKey;
	checking:
	{
	do
	{
	k=k+(intKey%10);
	intKey=intKey/10;
	}while(intKey>=10);
	k=k+intKey;
	if(k>32)
	{
		intKey=temp/10;
		break checking;
	}
	}
	return k;
}
    //CONVERING THE FILE
    
    private void convert(long secureKey,String key) throws Exception
{
	long Key=secureKey;
try
{
JFileChooser jFileChooser = new JFileChooser();
jFileChooser.addChoosableFileFilter(new SaveJavaFileFilter());
jFileChooser.addChoosableFileFilter(new SaveTextFileFilter());
jFileChooser.setSelectedFile(new File("fileToSave.txt"));
int responce = jFileChooser.showSaveDialog(null);
byte[] byt=null;
if(responce==JFileChooser.APPROVE_OPTION)
{
    String extension=jFileChooser.getFileFilter().getDescription();
    if(extension.equals("*.java"))
      {
          ext=".java";
      }
    if(extension.equals("*.txt"))
      {
          ext=".txt";
      }
}
fin=new FileInputStream(file);
message=fin.readAllBytes();
if(key=="decrypt")
{
    
    String decrypted=decrypt(message);
    byt=decrypted.getBytes();
    
}

//MY CODE
if(key=="encrypt")
{
String encrypted_message=encrypt(message);
byt=encrypted_message.getBytes();

}
/*mbuf=ByteBuffer.allocate((int)fsize);
ombuf=ByteBuffer.allocate((int)fsize);
fichan.read(mbuf);
mbuf.rewind();
for(int i=0;i<fsize;i++)
{
long data=((long) mbuf.get());
ombuf.put((byte)(data+Key));
}
ombuf.rewind();
fochan.write(ombuf);
fichan.close();*/
fout=new FileOutputStream(jFileChooser.getSelectedFile()+ext);
fout.write(byt);
fin.close();
fout.close();
}
catch(IOException e)
{
System.out.println(e);
System.exit(1);
}
catch(BufferUnderflowException uf)
{
	System.out.println(uf);
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
  
  public String decrypt(byte[] message) throws Exception{
  byte[] encryptedBytes = decode(message);
  Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
  cipher.init(Cipher.DECRYPT_MODE,privateKey);
  byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
  return new String(decryptedMessage,"UTF8");
  }
  private byte[] decode(byte[] data){
  return Base64.getDecoder().decode(data);
  }
  
  
  public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == browse)	{
    		int result = jfc.showOpenDialog(null);
    		 	if(result==JFileChooser.APPROVE_OPTION)
    		 	{
    		 		label.setText("Selected file is : "+jfc.getSelectedFile().getName());
    		 		filename.setText(jfc.getSelectedFile().getPath());
    		 		file=jfc.getSelectedFile();
    		 		denc.setEnabled(true);
    		 		enc.setEnabled(true);
    		 	}
    		 	else
    		 	{
    		 		filename.setText("No file selected");
    		 		denc.setEnabled(false);
    		 		enc.setEnabled(false);
    		 	}
    	}
    	if(e.getSource()==enc)
    	{
    		try
    		{
    		key=enterKey();
    		if(key>=1)
    		{
    		JOptionPane.showMessageDialog(null,"Save the Encrypted file","ENCRYPTION COMPLETED",JOptionPane.INFORMATION_MESSAGE);
    	convert(-key,"encrypt");
    		}
    		else
	   			JOptionPane.showMessageDialog(null,"Please enter a nonzero key","WARNING",JOptionPane.WARNING_MESSAGE);
    		}catch(Exception ioee){
    		}
    	}
    	if(e.getSource()==denc)
    	{try
    	{
	   		key=enterKey();
	   		if(key>=1)
	   		{
	   		JOptionPane.showMessageDialog(null,"Save the Decrypted file","DECRYPTION COMPLETED",JOptionPane.INFORMATION_MESSAGE);
	   		convert(key,"decrypt");
	   		}
	   		else
	   			JOptionPane.showMessageDialog(null,"Please enter a nonzero key","WARNING",JOptionPane.WARNING_MESSAGE);
    	}catch(Exception ex){}
    	}
    if(e.getSource() == cancel)	{
    		System.exit(1);
    	}
    }
  
  
  
  
  
    public static void main(String args[]) throws FileNotFoundException
    {
       /* byte[] mess = null;
        File inputFile = new File("C:/Users/ponth/OneDrive/Desktop/honors cryptography/PGP.txt");
       pgpUtils pgp=new pgpUtils(inputFile);
       try{
        FileInputStream inputStream= new FileInputStream(inputFile);
        byte[] inputBytes= new byte[(int)inputFile.length()];
        mess=inputStream.readAllBytes();
        }
        catch(Exception e)
        {
            System.out.println("Error occured file reading");
        }
        try
        {
            String encryptedMessage = pgp.encrypt(mess);
            String decryptedMessage = pgp.decrypt(encryptedMessage);

  System.err.println("Encrypted:\n"+encryptedMessage);
  System.err.println("Decrypted:\n"+decryptedMessage);
        }
        catch(Exception e)
        {}*/
        SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new pgpUtils();
			}
		});
	}
        
}
            

