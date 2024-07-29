
package pgpProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class md5

// Java program to calculate MD5 hash value
{
    public static String message1;
    public static String message2;
    public md5(String msg,String  msg2)
    {
        message1=readFile(msg);
        message2=readFile(msg2);
        //readFile(message1,message2);
    }
	public static String getMd5(String input)
	{
		try {

			// Static getInstance method is called with hashing MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// digest() method is called to calculate message digest
			// of an input digest() return array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
                        System.out.println(input+" "+hashtext);
			return hashtext;
                        }

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
        public static int Integrity()
        {
           /* String hash=getMd5(msg);
            try
                {
            Class.forName("oracle.jdbc.driver.OracleDriver");  

            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","120698")) {
                Statement stmt=con.createStatement();
                PreparedStatement prep=con.prepareStatement("insert  into message_digest(s_no,md) values(?,?)");
                prep.setString(1,p_id);
                prep.setString(2,date);

                prep.addBatch();
                con.setAutoCommit(false);
                prep.executeBatch();
                con.setAutoCommit(true);*/
            String hash1=getMd5(message1);
            String hash2=getMd5(message2);
            System.out.println(hash1+" "+ hash2);
            if(hash1.equals(hash2))
            {
                return 1;
            }
            else
            {
                return 0;
            }
            
        }
        public static String readFile(String file)
        {
            StringBuilder sb=new StringBuilder();
            try
            {
         File fr=new File(file);
                FileReader fread=new FileReader(fr);
                
                BufferedReader br =new BufferedReader(fread);
                
                int i=0;
                char[] c = new char[9000];
                
                while(br.ready())
                {
                    c[i]=(char)br.read();
                    sb.append(c[i]);
                    i++;
                }   
            }
            catch(Exception  e)
            {
                System.out.println(e);
            }
        return sb.toString();
        }
	// Driver code
	public static void main(String args[]) throws NoSuchAlgorithmException, FileNotFoundException, IOException
	{
             String source=new String("C:/Users/ponth/OneDrive/Documents/dec - copy.txt");
                String dest=new String("C:/Users/ponth/OneDrive/Documents/dec.txt");
                //"C:\Users\ponth\OneDrive\Documents\dec - Copy.txt"
                
                String read1=readFile(source);
                String read2=readFile(dest);
                
                System.out.println(read1+read2);
		//md5 md=new md5(read1,read2);
                String s1=getMd5(read1);
                String s2=getMd5(read2);
                //int check=md5.Integrity();
                if(s1.equals(s2))
                {
                    System.out.println("Success");
                }
                else
                {
                    System.out.println("FAilure");
                }
          /*      String st="h";
                String to="h";
                //md5 md1=new md5(st,to);               
                String ans1=getMd5(st);
                String ans2=getMd5(to);
                
                if(ans1.equals(ans2))
                {
                    System.out.println("Success");
                }
                else
                    System.out.println("FAilure");
            */    
	}
}
