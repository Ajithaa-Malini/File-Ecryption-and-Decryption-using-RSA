/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pgp_algo;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyUI extends JFrame implements ActionListener {
    private JFileChooser fileChooser;
    private JButton encryptButton;
    private JButton decryptButton;

    public MyUI() {
        // Create a file chooser and add it to a panel
        fileChooser = new JFileChooser();
        JPanel panel = new JPanel();
        panel.add(fileChooser);

        // Create a button and add an action listener to it
        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(this);
        decryptButton=new JButton("DEcrypt");
        panel.add(decryptButton);
        panel.add(encryptButton);
        Dimension d=d=Toolkit.getDefaultToolkit().getScreenSize();
        // Add the panel to the frame
        this.add(panel);

        // Set the frame properties
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the encrypt button was clicked
        if (e.getSource() == encryptButton) {
            // Get the selected file from the file chooser
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                // Call your RSA encryption function with the selected file
                String filePath = fileChooser.getSelectedFile().getPath();
                // Call your RSA encryption function with the file path
                // For example:
                // String encryptedText = rsa.encrypt(readFile(filePath));
    /*            FileInputStream fin=new FileInputStream(filePath);
                byte[] mess=fin.readAllBytes();
                String encryptedText= error.encrypt(mess);*/
            }
        }
    }

    // A helper function to read the content of a file
    private String readFile(String filePath) throws FileNotFoundException {
        // Implement this function to read the content of a file and return it as a string
        FileInputStream fin=new  FileInputStream(filePath);
        
        
        return "";
    }

    public static void main(String[] args) {
        new MyUI();
    }
}
