/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ilham
 */
public class koneksi {
    private Connection koneksi;
    public Connection connect(){
     try{
        Class.forName("com.mysql.jdbc.Driver");
//        System.out.println("i");
    }catch(ClassNotFoundException ex){
        System.out.println("Gagal Koneksi "+ex);
    }
    String url = "jdbc:mysql://localhost:3306/ilhamariy_penerimaankaryawan";
    try{
        koneksi = DriverManager.getConnection(url,"ilhamari_root","J}w*dH^?[CFp");
        System.out.println("koneksi tersambung");
    }catch(SQLException ex){
        System.out.println("Gagal Koneksi Database "+ex);
    }
    return koneksi;
    }
}
