/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFrame;

import javax.swing.JOptionPane;

/**
 *
 * @author Ilham
 */
public class Loading {
    public static void main(String[] args) {
        loadingFrame LF = new loadingFrame();
        LF.setVisible(true);
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(30);
                LF.Progress.setValue(i);
//                LF.persen.setText(Integer.toString(i)+"%");
                LoginFrame logF = new LoginFrame();
                if(i == 100) {
                    LF.dispose();
                    logF.show();
                    JOptionPane.showMessageDialog(null, "Koneksi Suksses");
//                    JOptionPane.showMessageDialog(null, "Username: admin \nPassword: admin");
                }
            }
        } catch (Exception e) {
        }
    }
}
