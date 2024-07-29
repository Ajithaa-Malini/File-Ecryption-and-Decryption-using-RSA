/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pgp_algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author ponth
 */
public class string {
    public static void main(String args[]) throws FileNotFoundException, IOException
    {
        try{
            File input=new File("C:/Users/ponth/OneDrive/Documents/new1.txt");

                FileInputStream fde=new FileInputStream(input);
                byte[] mess=fde.readAllBytes();
                String encryptedMessage=mess.toString();
                System.out.println(encryptedMessage);
                
                FileReader fr=new FileReader(input);
                
                BufferedReader br =new BufferedReader(fr);
                
                int i=0;
                StringBuilder s=new StringBuilder();
                
                char[] c =new char[9000];
                while(br.ready())
                {
                    c[i]=(char)br.read();
                    s.append(c[i]);
                    i++;
                }
                System.out.println(c);
                System.out.println("SSSS:"+s);
                System.out.println("\n"+s.toString());
             //   System.out.println(c);
                StringBuilder sb=new StringBuilder();
                for(int k=0;k<c.length;k++)
                {
                    sb.append(c[k]);
                }
                System.out.println(sb.toString());
                
                String str=String.copyValueOf(c);
                System.out.println(str);
    }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
