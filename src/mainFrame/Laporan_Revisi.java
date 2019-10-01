/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFrame;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author ilham
 */
public class Laporan_Revisi extends javax.swing.JFrame {
private Connection cn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode2;
    private DefaultTableModel tabmode3;
    private DefaultTableModel tabmode4;

    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    /**
     * Creates new form Laporan_Revisi
     */
    public Laporan_Revisi() {
        initComponents();
        lap_data_pelamar();
        hasilTest();
        lap_data_pelatihan();
        lap_data_karyawan();
    }
    
    
    protected void lap_data_pelamar() {
        Object[] Baris = {"NIK", "Nama", "JK", "Lulusan","Jabatan","tgl_lamar","No_HP","tglTest"};
        tabmode = new DefaultTableModel(null, Baris);
        jTable1.setModel(tabmode);
        String sql = "select * from lap_pen_karyawan";
        try {
            java.sql.Statement stat = cn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String Nama = rs.getString("Nama");
                String jenisKelamin = rs.getString("jenisKelamin");
                String lulusan = rs.getString("Lulusan");
                String jabatan = rs.getString("Jabatan");
                String tglLamar = rs.getString("tgl_lamar");
                String noHP = rs.getString("No_HP");
                String tglTest = rs.getString("tglTest");
                
                
//                String tglPengembalian = rs.getString("tglPengembalian");
//                String test = rs.getString("tglTest");

//                String qty = rs.getString("qty");
                String[] data = {
                    NIK,
                    Nama,
                    jenisKelamin,
                    lulusan,
                    jabatan,
                    tglLamar,
                    noHP,
                    tglTest
                    };
                tabmode.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    protected void hasilTest() {
        Object[] Baris = {"NIK", "Nama", "JK","Jabatan","tglTest","Keterangan"};
        tabmode2 = new DefaultTableModel(null, Baris);
        jTable3.setModel(tabmode2);
        String sql = "select * from hasil_test";
        try {
            java.sql.Statement stat = cn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String Nama = rs.getString("Nama");
                String jenisKelamin = rs.getString("jenisKelamin");
//                String lulusan = rs.getString("Lulusan");
                String jabatan = rs.getString("Jabatan");
//                String tglLamar = rs.getString("tgl_lamar");
//                String noHP = rs.getString("No_HP");
                String tglTest = rs.getString("tglTest");
                String ket = rs.getString("Keterangan");
                
                
//                String tglPengembalian = rs.getString("tglPengembalian");
//                String test = rs.getString("tglTest");

//                String qty = rs.getString("qty");
                String[] data = {
                    NIK,
                    Nama,
                    jenisKelamin,
                    jabatan,
                    tglTest,
                    ket
                    };
                tabmode2.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    protected void lap_data_pelatihan() {
        Object[] Baris = {"NIK", "Nama", "JK","Jabatan","Tgl Mulai","Tgl Selesai","Keterangan"};
        tabmode3 = new DefaultTableModel(null, Baris);
        jTable4.setModel(tabmode3);
        String sql = "select * from pelatihan";
        try {
            java.sql.Statement stat = cn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String Nama = rs.getString("Nama");
                String jenisKelamin = rs.getString("JK");
//                String lulusan = rs.getString("Lulusan");
                String jabatan = rs.getString("Jabatan");
                String tglLamar = rs.getString("tglMulai");
                String noHP = rs.getString("tglSelesai");
                String tglTest = rs.getString("Keterangan");
                
                
//                String tglPengembalian = rs.getString("tglPengembalian");
//                String test = rs.getString("tglTest");

//                String qty = rs.getString("qty");
                String[] data = {
                    NIK,
                    Nama,
                    jenisKelamin,
                    jabatan,
                    tglLamar,
                    noHP,
                    tglTest
                    };
                tabmode3.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    protected void lap_data_karyawan() {
        Object[] Baris = {"NIK","NIP", "Nama", "JK", "Jabatan","Lulusan","Tempat","Tgl Lahir","Agama","Alamat","noTelp"};
        tabmode4 = new DefaultTableModel(null, Baris);
        jTable5.setModel(tabmode4);
        String sql = "select * from karyawan";
        try {
            java.sql.Statement stat = cn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String NIP = rs.getString("NIP");
                String Nama = rs.getString("Nama");
                String JK = rs.getString("JK");
                
                String jabatan = rs.getString("Jabatan");
                String lulusan = rs.getString("Lulusan");
                String tempat = rs.getString("Tempat");
                String tglLamar = rs.getString("tglLahir");
                String agama = rs.getString("Agama");
                String alamat = rs.getString("Alamat");
                String noTelp = rs.getString("noTelp");
                String[] data = {
                    NIK,
                    NIP,
                    Nama,
                    JK,
                    jabatan,
                    lulusan,
                    tempat,
                    tglLamar,
                    agama,
                    alamat,
                    noTelp
                };
                tabmode4.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void removeTable() {
        try {
            for (int t = tabmode.getRowCount(); t > 0; t--) {
                tabmode.removeRow(0);
            }
        } catch (Exception e) {
        }
    }
    public void removeTable2() {
        try {
            for (int t = tabmode2.getRowCount(); t > 0; t--) {
                tabmode2.removeRow(0);
            }
        } catch (Exception e) {
        }
    }
    public void removeTable3() {
        try {
            for (int t = tabmode3.getRowCount(); t > 0; t--) {
                tabmode3.removeRow(0);
            }
        } catch (Exception e) {
        }
    }
    public void removeTable4() {
        try {
            for (int t = tabmode4.getRowCount(); t > 0; t--) {
                tabmode4.removeRow(0);
            }
        } catch (Exception e) {
        }
    }
    
    private void print_hasil_test() {
        try {
            java.util.Date d = new java.util.Date();
            SimpleDateFormat s = new SimpleDateFormat("EEEE dd-MMMM-yyyy");
            String tgl = s.format(d);
            HashMap parameter = new HashMap();
            parameter.put("tabel", "tabel1");
//            String path = "./src/print/Pengembalian.jasper";
            InputStream path = getClass().getResourceAsStream("/print/laporan_data_hasil_tes.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(path);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JRDataSource dataSource = new JRTableModelDataSource(jTable3.getModel());
            parameter.put("tgl", tgl);
//            parameter.put("Induk_Siswa", tIndukSiswa.getText());
//            parameter.put("judul_buku", tJudulBuku.getText());
//            parameter.put("tanggal_pinjam", tTanggalPinjam.getText());
//            parameter.put("tanggal_Pengembalian", ((JTextField) tTanggalPengembalian.getDateEditor().getUiComponent()).getText());
//            parameter.put("qty", tBanyakBuku.getText());
//            parameter.put("nama",tNama.getText());
//            parameter.put("kelas",jLabel32.getText());
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void print_lap_karyawan() {
        try {
            java.util.Date d = new java.util.Date();
            SimpleDateFormat s = new SimpleDateFormat("EEEE dd-MMMM-yyyy");
            String tgl = s.format(d);
            HashMap parameter = new HashMap();
            parameter.put("tabel", "tabel1");
//            String path = "./src/print/Pengembalian.jasper";
            InputStream path = getClass().getResourceAsStream("/print/laporan_data_karyawan.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(path);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JRDataSource dataSource = new JRTableModelDataSource(jTable5.getModel());
            parameter.put("tgl", tgl);
//            parameter.put("Induk_Siswa", tIndukSiswa.getText());
//            parameter.put("judul_buku", tJudulBuku.getText());
//            parameter.put("tanggal_pinjam", tTanggalPinjam.getText());
//            parameter.put("tanggal_Pengembalian", ((JTextField) tTanggalPengembalian.getDateEditor().getUiComponent()).getText());
//            parameter.put("qty", tBanyakBuku.getText());
//            parameter.put("nama",tNama.getText());
//            parameter.put("kelas",jLabel32.getText());
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void print_lap_pelamar() {
        try {
            java.util.Date d = new java.util.Date();
            SimpleDateFormat s = new SimpleDateFormat("EEEE dd-MMMM-yyyy");
            String tgl = s.format(d);
            HashMap parameter = new HashMap();
            parameter.put("tabel", "tabel1");
//            String path = "./src/print/Pengembalian.jasper";
            InputStream path = getClass().getResourceAsStream("/print/laporan_data_pelamar.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(path);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JRDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
            parameter.put("tgl", tgl);
//            parameter.put("Induk_Siswa", tIndukSiswa.getText());
//            parameter.put("judul_buku", tJudulBuku.getText());
//            parameter.put("tanggal_pinjam", tTanggalPinjam.getText());
//            parameter.put("tanggal_Pengembalian", ((JTextField) tTanggalPengembalian.getDateEditor().getUiComponent()).getText());
//            parameter.put("qty", tBanyakBuku.getText());
//            parameter.put("nama",tNama.getText());
//            parameter.put("kelas",jLabel32.getText());
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     private void print_lap_pelatihan() {
        try {
            java.util.Date d = new java.util.Date();
            SimpleDateFormat s = new SimpleDateFormat("EEEE dd-MMMM-yyyy");
            String tgl = s.format(d);
            HashMap parameter = new HashMap();
            parameter.put("tabel", "tabel1");
//            String path = "./src/print/Pengembalian.jasper";
            InputStream path = getClass().getResourceAsStream("/print/laporan_data_pelatihan.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(path);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JRDataSource dataSource = new JRTableModelDataSource(jTable4.getModel());
            parameter.put("tgl", tgl);
//            parameter.put("Induk_Siswa", tIndukSiswa.getText());
//            parameter.put("judul_buku", tJudulBuku.getText());
//            parameter.put("tanggal_pinjam", tTanggalPinjam.getText());
//            parameter.put("tanggal_Pengembalian", ((JTextField) tTanggalPengembalian.getDateEditor().getUiComponent()).getText());
//            parameter.put("qty", tBanyakBuku.getText());
//            parameter.put("nama",tNama.getText());
//            parameter.put("kelas",jLabel32.getText());
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jDateChooser8 = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Laporan Data Pelamar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("Tanggal Awal");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, -1, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setText(":");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 30, -1, -1));

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 33, 207, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setText("Tanggal Akhir");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 72, -1, -1));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setText(":");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 72, -1, -1));

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 72, 207, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search_16x16.png"))); // NOI18N
        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 30, 90, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print_16x16.png"))); // NOI18N
        jButton2.setText("CETAK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 69, 100, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NIK", "Nama", "JK", "Lulusan", "Tgl Lamar", "No Hp", "Tgl Test"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(3).setHeaderValue("Lulusan");
            jTable1.getColumnModel().getColumn(4).setHeaderValue("Tgl Lamar");
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 100, 590, 224));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Previous_16x16.png"))); // NOI18N
        jButton3.setText("Kembali");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 100, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Laporan Hasil Tes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setText("Tanggal Awal");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, -1, -1));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setText(":");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 30, -1, -1));

        jDateChooser5.setDateFormatString("yyyy-MM-dd");
        jPanel3.add(jDateChooser5, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 33, 207, -1));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setText("Tanggal Akhir");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 72, -1, -1));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel25.setText(":");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 72, -1, -1));

        jDateChooser6.setDateFormatString("yyyy-MM-dd");
        jPanel3.add(jDateChooser6, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 72, 207, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search_16x16.png"))); // NOI18N
        jButton5.setText("Cari");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 30, 90, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print_16x16.png"))); // NOI18N
        jButton6.setText("CETAK");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 69, -1, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NIK", "Nama", "JK", "No Hp", "Tgl Test", "Keterangan"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 105, 580, 210));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Previous_16x16.png"))); // NOI18N
        jButton4.setText("Kembali");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Laporan Hasil Pelatihan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel26.setText("Tanggal Awal");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, -1, -1));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel27.setText(":");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 30, -1, -1));

        jDateChooser7.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(jDateChooser7, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 33, 207, -1));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel28.setText("Tanggal Akhir");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 72, -1, -1));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel29.setText(":");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 72, -1, -1));

        jDateChooser8.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(jDateChooser8, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 72, 207, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search_16x16.png"))); // NOI18N
        jButton7.setText("Cari");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 30, 100, -1));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print_16x16.png"))); // NOI18N
        jButton8.setText("CETAK");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 69, 100, -1));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NIK", "Nama", "JK", "No Hp", "Tgl Test", "Keterangan"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 100, 590, 150));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Previous_16x16.png"))); // NOI18N
        jButton9.setText("Kembali");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Laporan Data Karyawan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print_16x16.png"))); // NOI18N
        jButton10.setText("CETAK");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, -1, -1));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NIK", "Nama", "JK", "No Hp", "Tgl Test", "Keterangan"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, 590, 170));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Previous_16x16.png"))); // NOI18N
        jButton11.setText("Kembali");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tglAwal = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
        String tglAkhir = ((JTextField) jDateChooser2.getDateEditor().getUiComponent()).getText();
        String sql = "SELECT * FROM lap_pen_karyawan where tgl_lamar >='" + tglAwal + "'AND tgl_lamar <='" + tglAkhir + "'";
        try {
            removeTable();
            Statement stat = cn.createStatement();
//                String sql = "SELECT * FROM simpanbuku where judulBuku like '%" + item + "%'or NIS like'%" + item + "%'order by kd_Buku asc";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String Nama = rs.getString("Nama");
                String jenisKelamin = rs.getString("jenisKelamin");
                String lulusan = rs.getString("Lulusan");
                String jabatan = rs.getString("Jabatan");
//                String tglPengembalian = rs.getString("tglPengembalian");
                String tglLamar = rs.getString("tgl_lamar");
                String noHP = rs.getString("No_HP");
                String test = rs.getString("tglTest");
                String data[] = {
                    NIK, Nama, jenisKelamin, lulusan, jabatan, tglLamar,noHP,test
                };
                tabmode.addRow(data);
//                    jumdata++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        print_lap_pelamar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String tglAwal = ((JTextField) jDateChooser5.getDateEditor().getUiComponent()).getText();
        String tglAkhir = ((JTextField) jDateChooser6.getDateEditor().getUiComponent()).getText();
        String sql = "SELECT * FROM hasil_test where tglTest >='" + tglAwal + "'AND tglTest <='" + tglAkhir + "'";
        try {
            removeTable2();
            Statement stat = cn.createStatement();
            //                String sql = "SELECT * FROM simpanbuku where judulBuku like '%" + item + "%'or NIS like'%" + item + "%'order by kd_Buku asc";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String Nama = rs.getString("Nama");
                String jenisKelamin = rs.getString("jenisKelamin");
//                String lulusan = rs.getString("Lulusan");
                String jabatan = rs.getString("Jabatan");
//                String tglPengembalian = rs.getString("tglPengembalian");
                String tglLamar = rs.getString("tglTest");
                String noHP = rs.getString("Keterangan");
//                String test = rs.getString("tglTest");

//                String qty = rs.getString("qty");
                String[] data = {
                    NIK,
                    Nama,
                    jenisKelamin,
//                    lulusan,
                    jabatan,
                    tglLamar,
                    noHP,
                    //                    tglPengembalian,
                    };
                tabmode2.addRow(data);
                //                    jumdata++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        print_hasil_test();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        String tglAwal = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
        String tglAkhir = ((JTextField) jDateChooser2.getDateEditor().getUiComponent()).getText();
        String sql = "SELECT * FROM pelatihan where tglMulai >='" + tglAwal + "'AND tglSelesai <='" + tglAkhir + "'";
        try {
            removeTable();
            Statement stat = cn.createStatement();
            //                String sql = "SELECT * FROM simpanbuku where judulBuku like '%" + item + "%'or NIS like'%" + item + "%'order by kd_Buku asc";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String Nama = rs.getString("Nama");
                String jenisKelamin = rs.getString("jenisKelamin");
//                String lulusan = rs.getString("Lulusan");
                String jabatan = rs.getString("Jabatan");
//                String tglPengembalian = rs.getString("tglPengembalian");
                String tglLamar = rs.getString("tglTest");
                String noHP = rs.getString("Keterangan");
//                String test = rs.getString("tglTest");

//                String qty = rs.getString("qty");
                String[] data = {
                    NIK,
                    Nama,
                    jenisKelamin,
//                    lulusan,
                    jabatan,
                    tglLamar,
                    noHP,
                    //                    tglPengembalian,
                    };
                tabmode.addRow(data);
                //                    jumdata++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
String tglAwal = ((JTextField) jDateChooser7.getDateEditor().getUiComponent()).getText();
        String tglAkhir = ((JTextField) jDateChooser8.getDateEditor().getUiComponent()).getText();
        String sql = "SELECT * FROM pelatihan where tglMulai >='" + tglAwal + "'AND tglSelesai <='" + tglAkhir + "'";
        try {
            removeTable3();
            Statement stat = cn.createStatement();
            //                String sql = "SELECT * FROM simpanbuku where judulBuku like '%" + item + "%'or NIS like'%" + item + "%'order by kd_Buku asc";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String Nama = rs.getString("Nama");
                String jenisKelamin = rs.getString("JK");
//                String lulusan = rs.getString("Lulusan");
                String jabatan = rs.getString("Jabatan");
                String tglLamar = rs.getString("tglMulai");
                String noHP = rs.getString("tglSelesai");
                String tglTest = rs.getString("Keterangan");
                
                
//                String tglPengembalian = rs.getString("tglPengembalian");
//                String test = rs.getString("tglTest");

//                String qty = rs.getString("qty");
                String[] data = {
                    NIK,
                    Nama,
                    jenisKelamin,
                    jabatan,
                    tglLamar,
                    noHP,
                    tglTest
                    };
                tabmode3.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        print_lap_pelatihan();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        print_lap_karyawan();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new menuUtama().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new menuUtama().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        new menuUtama().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        new menuUtama().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Laporan_Revisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laporan_Revisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laporan_Revisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laporan_Revisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan_Revisi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private com.toedter.calendar.JDateChooser jDateChooser7;
    private com.toedter.calendar.JDateChooser jDateChooser8;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    // End of variables declaration//GEN-END:variables
}
