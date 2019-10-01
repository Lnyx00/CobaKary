/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFrame;

import java.awt.TextField;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
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
import sun.security.util.Password;

/**
 *
 * @author ilham
 */
public class dataKaryawanFrame_Revisi extends javax.swing.JFrame {

    private Connection cn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode2;

//public cariBuku cb = null;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    String nikTabel,nikTabel2, tempat, tglLahir, alamat, agama, noHP, Jabatan, Lulusan, jenisKelamin, NIP;

    /**
     * Creates new form dataPelamarFrame
     */
    public dataKaryawanFrame_Revisi() {
        initComponents();
        tanggal();
        datatable();
        datatableKaryawan();
    }

    public void getDatePelamar() {
        String data = "SELECT * FROM datapelamar where NIK=?";
        try {
            pst = cn.prepareStatement(data);
            pst.setString(1, nikTabel2);
            rs = pst.executeQuery();
            if (rs.next()) {
                tempat = rs.getString("Tempat");
                tglLahir = rs.getString("tglLahir");
                alamat = rs.getString("Alamat");
                agama = rs.getString("Agama");
                noHP = rs.getString("NoTelp");
                Jabatan = rs.getString("Jabatan");
                Lulusan = rs.getString("Lulusan");
                jenisKelamin = rs.getString("JK");
                rs.close();
                pst.close();
            } else {
                JOptionPane.showMessageDialog(null, "Data Pelamar Tidak Ada Mohon Masukan Secara Manual!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e);
        }
    }

    public void getDateKaryawan() {
        String data = "SELECT * FROM karyawan where NIK=?";
        try {
            pst = cn.prepareStatement(data);
            pst.setString(1, nikTabel);
            rs = pst.executeQuery();
            if (rs.next()) {
                tempat = rs.getString("Tempat");
                tglLahir = rs.getString("tglLahir");
                alamat = rs.getString("Alamat");
                agama = rs.getString("Agama");
                noHP = rs.getString("NoTelp");
                Jabatan = rs.getString("Jabatan");
                Lulusan = rs.getString("Lulusan");
                jenisKelamin = rs.getString("JK");
                NIP = rs.getString("NIP");
                rs.close();
                pst.close();
            } else {
                JOptionPane.showMessageDialog(null, "Mohon Masukan No Induk Siswa Dengan benar!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e);
        }
    }

    protected void datatable() {
        Object[] Baris = {"NIK", "Nama", "Keterangan"};
        tabmode = new DefaultTableModel(null, Baris);
        jTable1.setModel(tabmode);
        String sql = "select * from pelatihan where keterangan='LULUS'";
        try {
            Statement stat = cn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String Nama = rs.getString("Nama");
//                String Tempat = rs.getString("Tempat");
//                String tglLahir = rs.getString("tglLahir");
//                String JK = rs.getString("JK");
//                String Lulusan = rs.getString("Lulusan");
//                String Jabatan = rs.getString("Jabatan");
//                String Agama = rs.getString("Agama");
//                String noTelp = rs.getString("NoTelp");
//                String Alamat = rs.getString("Alamat");
                String Keterangan = rs.getString("Keterangan");
                String[] data = {NIK, Nama, Keterangan};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    protected void datatableKaryawan() {
        Object[] Baris = {"NIK", "NIP", "Nama"};
        tabmode2 = new DefaultTableModel(null, Baris);
        jTable2.setModel(tabmode2);
        String sql = "select * from karyawan";
        try {
            Statement stat = cn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String nip = rs.getString("NIP");
//                String Tempat = rs.getString("Tempat");
//                String tglLahir = rs.getString("tglLahir");
//                String JK = rs.getString("JK");
//                String Lulusan = rs.getString("Lulusan");
//                String Jabatan = rs.getString("Jabatan");
//                String Agama = rs.getString("Agama");
//                String noTelp = rs.getString("NoTelp");
//                String Alamat = rs.getString("Alamat");
                String Nama = rs.getString("Nama");
                String[] data = {NIK, nip, Nama};
                tabmode2.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tanggal() {
        java.util.Date d = new java.util.Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        tTanggalPinjam.setText(s.format(d));
    }

    private void JK() {
        String JenisKelamin = "";
        if (jRadioButton1.isSelected()) {
            JenisKelamin = jRadioButton1.getText();
        } else {
            JenisKelamin = jRadioButton2.getText();
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

    private void reset() {
        tNIK.setText("");
        tNama.setText("");
        tTempat.setText("");
        ((JTextField) tTGL.getDateEditor().getUiComponent()).setText("");
        buttonGroup1.clearSelection();
        comboLulusan.setSelectedIndex(0);
        comboJabatan.setSelectedIndex(0);
        comboAgama.setSelectedIndex(0);
        tNoHP.setText("");
        tAlamat.setText("");
        tNama1.setText("");
//        ((JTextField) tJadwalTest.getDateEditor().getUiComponent()).setText("");
    }

    public void cari() {
        if (tNIK.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kotak Pencarian Kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            int jumdata = 0;
            removeTable();
            String item = tNIK1.getText();
            try {
                Statement stat = cn.createStatement();
                String sql = "SELECT * FROM datapelamar where NIK like '%" + item + "%'or Nama like'%" + item + "%'order by Nama asc";
                rs = stat.executeQuery(sql);
                while (rs.next()) {
                    String NIK = rs.getString("NIK");
                    String Nama = rs.getString("Nama");
                    String Tempat = rs.getString("Tempat");
                    String tglLahir = rs.getString("tglLahir");
                    String JK = rs.getString("JK");
                    String Lulusan = rs.getString("Lulusan");
                    String Jabatan = rs.getString("Jabatan");
                    String Agama = rs.getString("Agama");
                    String noTelp = rs.getString("NoTelp");
                    String Alamat = rs.getString("Alamat");
                    String tglTest = rs.getString("tglTest");
                    String[] data = {NIK, Nama, Tempat, tglLahir, JK, Lulusan, Jabatan, Agama, noTelp, Alamat, tglTest};
                    tabmode.addRow(data);
                    jumdata++;
                }
            } catch (Exception e) {
                jumdata = 0;
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    public void ubahData() {
//        String tanggal = ((JTextField) tJadwalTest.getDateEditor().getUiComponent()).getText();
//        String[] splited = tanggal.split("\\s+");
//        String hari = (splited[0]);
//        String tanggalLengkap = (splited[1]);
//        String tgl = tanggalLengkap.substring(0, 2);
//        String bulan = tanggalLengkap.substring(3, 5);
//        String tahun = tanggalLengkap.substring(6, 10);
//
//        String fix = (tahun + "-" + bulan + "-" + tgl);
//        System.out.println(fix);
        String kelamin = "";
        String NIK = tNIK.getText();
        String nip = tNama.getText();
        String nama = tNama1.getText();
        String Tempat = tTempat.getText();
        String tanggalLahir = ((JTextField) tTGL.getDateEditor().getUiComponent()).getText();
        String Alamat = tAlamat.getText();
        String LULUSAN = (String) comboLulusan.getSelectedItem();
        String JABATAN = (String) comboJabatan.getSelectedItem();
        String AGAMA = (String) comboAgama.getSelectedItem();
        String HP = tNoHP.getText();
        if (jRadioButton1.isSelected()) {
            kelamin = "Laki-Laki";
        } else if (jRadioButton2.isSelected()) {
            kelamin = "Perempuan";
        }
        try {
            String ubah = "UPDATE karyawan set NIK='" + NIK + "',"
                    + "NIP='" + nip + "',"
                    + "Nama='" + nama + "',"
                    + "JK='" + kelamin + "',"
                    + "Jabatan ='" + JABATAN + "',"
                    + "Lulusan='" + LULUSAN + "',"
                    + "Tempat='" + Tempat +"',"
                    + "tglLahir='" + tanggalLahir +"',"
                    + "Agama='" + AGAMA + "',"
                    + "Alamat='" + Alamat +"',"
                    + "noTelp='" + HP + "' WHERE NIK='" + nikTabel +"'";
            pst = cn.prepareStatement(ubah);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Laporan Berubah");
            datatableKaryawan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e);
        }
        System.out.println(nikTabel);
    }

    public void delete() {
        String nik = nikTabel;
        String delete = "DELETE from karyawan where NIK='" + nik + "'";
        try {
            pst = cn.prepareStatement(delete);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
//            datatable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "UPDATE GAGAL" + e);
        }
    }

    private void simpanDataKaryawan() {
        String JenisKelamin = "";
        if (jRadioButton1.isSelected()) {
            JenisKelamin = jRadioButton1.getText();
        } else {
            JenisKelamin = jRadioButton2.getText();
        }
        String sql = "INSERT INTO karyawan (NIK,NIP,Nama,JK,Jabatan,Lulusan,Tempat,tglLahir,Agama,Alamat,NoTelp) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
//            String tanggal = ((JTextField) tJadwalTest.getDateEditor().getUiComponent()).getText();
//            String[] splited = tanggal.split("\\s+");
//            String hari = (splited[0]);
//            String tanggalLengkap = (splited[1]);
//            String tgl = tanggalLengkap.substring(0, 2);
//            String bulan = tanggalLengkap.substring(3, 5);
//            String tahun = tanggalLengkap.substring(6, 10);
//
//            String fix = (tahun + "-" + bulan + "-" + tgl);
//            System.out.println(fix);

            pst = cn.prepareStatement(sql);
            pst.setString(1, tNIK.getText());
            pst.setString(2, tNama.getText());
            pst.setString(3, tNama1.getText());
            pst.setString(4, JenisKelamin);
            pst.setString(5, (String) comboJabatan.getSelectedItem());
            pst.setString(6, (String) comboLulusan.getSelectedItem());
            pst.setString(7, tTempat.getText());
            pst.setString(8, ((JTextField) tTGL.getDateEditor().getUiComponent()).getText());
            pst.setString(9, (String) comboAgama.getSelectedItem());
            pst.setString(10, tAlamat.getText());
            pst.setString(11, tNoHP.getText());
//            pst.setString(11, fix);
            pst.execute();
            datatable();
            JOptionPane.showMessageDialog(null, "Data Pelamar Berhasil Disimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal disimpan" + e);
        }
    }

    public void deleteLaporan() {
        String nik = nikTabel;
        String delete = "DELETE from lap_pen_karyawan where NIK='" + nik + "'";
        try {
            pst = cn.prepareStatement(delete);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Laporan Berhasil Di Hapus");
//            datatable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "UPDATE GAGAL" + e);
        }
        System.out.println("INI TEST DELETE LAP " + nik);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tNIK = new javax.swing.JTextField();
        tNama = new javax.swing.JTextField();
        tTempat = new javax.swing.JTextField();
        tTGL = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tAlamat = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        comboLulusan = new javax.swing.JComboBox<>();
        comboJabatan = new javax.swing.JComboBox<>();
        comboAgama = new javax.swing.JComboBox<>();
        tNoHP = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tNama1 = new javax.swing.JTextField();
        tTanggalPinjam = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        tNIK1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        tNIK2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/indah-cargo.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Data Pelamar ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("NIK");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("NIP");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Tempat, Tanggal Lahir");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Alamat");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Lulusan");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Jabatan");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Agama");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("No-Telp");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText(":");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText(":");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText(":");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText(":");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText(":");

        tNIK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNIKKeyTyped(evt);
            }
        });

        tNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNamaKeyTyped(evt);
            }
        });

        tTempat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tTempatKeyTyped(evt);
            }
        });

        tTGL.setDateFormatString("yyyy-MM-dd");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Jenis Kelamin");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText(":");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRadioButton1.setText("Laki-Laki");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRadioButton2.setText("Perempuan");

        tAlamat.setColumns(20);
        tAlamat.setRows(5);
        jScrollPane1.setViewportView(tAlamat);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText(":");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText(":");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText(":");

        comboLulusan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--PILIH--", "SMA", "SMK", "D3", "S1" }));

        comboJabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--PILIH--", "Driver", "Security", "Staff Admin", "Kasir", " " }));

        comboAgama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--PILIH--", "Islam", "Kristen", "Budha", "Kong Hu Cu", "Hindu" }));

        tNoHP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNoHPKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Nama");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText(":");

        tNama1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNama1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel11)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel22))
                                .addGap(80, 80, 80)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addGap(18, 18, 18)
                                        .addComponent(tNama1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addGap(18, 18, 18)
                                            .addComponent(tNIK, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addGap(18, 18, 18)
                                            .addComponent(tNama))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel16)
                                            .addGap(18, 18, 18)
                                            .addComponent(jRadioButton1)
                                            .addGap(18, 18, 18)
                                            .addComponent(jRadioButton2))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel19))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel14))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel17)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tNoHP)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboAgama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboLulusan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(tTempat, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTGL, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel13)
                            .addComponent(tNIK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12)
                            .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel14)
                            .addComponent(comboLulusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel17)
                            .addComponent(comboJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(tNama1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(comboAgama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel9)
                    .addComponent(jLabel19)
                    .addComponent(tNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel10)
                        .addComponent(tTempat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tTGL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        tTanggalPinjam.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Data Pelatihan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "NIK", "Nama", "Keterangan"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Nama Atau NIK");

        tNIK1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tNIK1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tNIK1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNIK1KeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText(":");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search_16x16.png"))); // NOI18N
        jButton6.setText("Cari");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(tNIK1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(tNIK1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Panel Aksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Save_16x16.png"))); // NOI18N
        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh_16x16.png"))); // NOI18N
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Previous_16x16.png"))); // NOI18N
        jButton3.setText("Kembali");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Delete_16x16.png"))); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit_16x16.png"))); // NOI18N
        jButton5.setText("Ubah");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton5});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Data Karyawan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "NIK", "NIP", "Nama"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable2MouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Nama Atau NIK");

        tNIK2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tNIK2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tNIK2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNIK2KeyTyped(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText(":");

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search_16x16.png"))); // NOI18N
        jButton8.setText("Cari");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(tNIK2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(tNIK2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1243, 709));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (tNIK.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field NIK kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (tNama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field Nama kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (tTempat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field Tempat kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (((JTextField) tTGL.getDateEditor().getUiComponent()).getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field Tanggal Lahir kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (tAlamat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field Alamat kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (tNoHP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field No Handphone kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (comboAgama.getSelectedItem().equals("--PILIH--")) {
            JOptionPane.showMessageDialog(null, "Mohon Pilih Agama Pelamar", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (comboJabatan.getSelectedItem().equals("--PILIH--")) {
            JOptionPane.showMessageDialog(null, "Mohon Pilih Jabata yang diinginkan Pelamar", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (comboLulusan.getSelectedItem().equals("--PILIH--")) {
            JOptionPane.showMessageDialog(null, "Mohon Pilih Lulusan Pelamar", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            simpanDataKaryawan();
            datatableKaryawan();
//            simpanDataPelamar();
//            simpanLap();
//            int tanya = JOptionPane.showConfirmDialog(null, "Apakah Anda  Ingin Cetak Bukti Registrasi?", "Konfrimasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
//            if (tanya == 0) {
////                cetakRegis();
//            } else {
//                reset();
//            }
            datatable();
            reset();

//            cetakRegis();
//            String tanggal = ((JTextField) tJadwalTest.getDateEditor().getUiComponent()).getText();
//            String[] splited = tanggal.split("\\s+");
//            String hari = splited[0];
//            String tanggalLengkap = splited[1];
//            int countArray = splited.length;           
//             System.out.println(hari + " Ini adalah split 0");
//            System.out.println(tanggalLengkap + " Ini adalah split 1");
//            String tgl = tanggalLengkap.substring(0,2);
//            String bulan = tanggalLengkap.substring(3,5);
//            String tahun = tanggalLengkap.substring(6,10);
//            String fix = (tahun+"-"+bulan+"-"+tgl);
//            System.out.println(fix);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tNamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNamaKeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tNamaKeyTyped

    private void tTempatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTempatKeyTyped
        char vchar = evt.getKeyChar();
        if (Character.isLetter(vchar) || (vchar == KeyEvent.VK_SPACE) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_ENTER)) {
            evt = evt;
        } else {
            evt.consume();

            JOptionPane.showMessageDialog(null, "Hanya Character Yang Boleh Di Isi");

        }
    }//GEN-LAST:event_tTempatKeyTyped

    private void tNIKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNIKKeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tNIKKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int bar = jTable1.getSelectedRow();
        nikTabel2 = tabmode.getValueAt(bar, 0).toString();
        System.out.println(nikTabel2);
        getDatePelamar();
        
        String Nama = tabmode.getValueAt(bar, 1).toString();
////        String Keterangan = tabmode.getValueAt(bar, 2).toString();
////        String Tempat = tabmode.getValueAt(bar, 2).toString();
////        String tglLahir = tabmode.getValueAt(bar, 3).toString();
////        String JK = tabmode.getValueAt(bar, 4).toString();
////        String Lulusan = tabmode.getValueAt(bar, 5).toString();
////        String Jabatan = tabmode.getValueAt(bar, 6).toString();
////        String Agama = tabmode.getValueAt(bar, 7).toString();
////        String noTelp = tabmode.getValueAt(bar, 8).toString();
////        String Alamat = tabmode.getValueAt(bar, 9).toString();
////        String tglTest = tabmode.getValueAt(bar, 10).toString();
        tNIK.setText(nikTabel2);
        tTempat.setText(tempat);
        ((JTextField) tTGL.getDateEditor().getUiComponent()).setText(tglLahir);
        tAlamat.setText(alamat);
        comboAgama.setSelectedItem(agama);
        comboJabatan.setSelectedItem(Jabatan);
        comboLulusan.setSelectedItem(Lulusan);
        tNama1.setText(Nama);

        if (jenisKelamin.equals("Laki-Laki")) {
            jRadioButton1.setSelected(true);
        } else {
            jRadioButton2.setSelected(true);
        }
        tNoHP.setText(noHP);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void tNIK1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNIK1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tNIK1KeyTyped

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tNIK1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNIK1KeyReleased
        int jumdata = 0;
        removeTable();
        String item = tNIK1.getText();
        try {
            Statement stat = cn.createStatement();
            String sql = "SELECT * FROM pelatihan where (NIK like '%" + item + "%'or Nama like'%" + item + "%') AND Keterangan='LULUS' order by Nama asc";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String Nama = rs.getString("Nama");
//                String Tempat = rs.getString("Tempat");
//                String tglLahir = rs.getString("tglLahir");
//                String JK = rs.getString("JK");
//                String Lulusan = rs.getString("Lulusan");
//                String Jabatan = rs.getString("Jabatan");
//                String Agama = rs.getString("Agama");
//                String noTelp = rs.getString("NoTelp");
//                String Alamat = rs.getString("Alamat");
                String Keterangan = rs.getString("Keterangan");
                String[] data = {NIK, Nama, Keterangan};
                tabmode.addRow(data);
                jumdata++;
            }
        } catch (Exception e) {
            jumdata = 0;
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tNIK1KeyReleased

    private void tNIK1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNIK1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cari();
        }
    }//GEN-LAST:event_tNIK1KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String nik = tNIK.getText();
        String nip = tNama.getText();
        String Nama = tNama1.getText();
        int tanya = JOptionPane.showConfirmDialog(null, "Hapus NIK: " + nik + "\nNIP: " + nip + "\nNama: " +Nama +"", "Konfrimasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (tanya == 0) {
            delete();
//            deleteLaporan();
            datatableKaryawan();
            reset();
        } else {
            reset();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (tNIK.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field NIK kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (tNama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field Nama kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (tTempat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field Tempat kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (((JTextField) tTGL.getDateEditor().getUiComponent()).getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field Tanggal Lahir kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } //        else if(((JTextField) tJadwalTest.getDateEditor().getUiComponent()).getText().isEmpty()) {
        //            JOptionPane.showMessageDialog(null, "Field Tanggal Test kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        //        }
        else if (tAlamat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field Alamat kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (tNoHP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field No Handphone kosong!", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (comboAgama.getSelectedItem().equals("--PILIH--")) {
            JOptionPane.showMessageDialog(null, "Mohon Pilih Agama Pelamar", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (comboJabatan.getSelectedItem().equals("--PILIH--")) {
            JOptionPane.showMessageDialog(null, "Mohon Pilih Jabata yang diinginkan Pelamar", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (comboLulusan.getSelectedItem().equals("--PILIH--")) {
            JOptionPane.showMessageDialog(null, "Mohon Pilih Lulusan Pelamar", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            ubahData();
            datatableKaryawan();
//            ubahDataLaporan();
            reset();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int bar = jTable2.getSelectedRow();
        nikTabel = tabmode2.getValueAt(bar, 0).toString();
        getDateKaryawan();
        String Nama = tabmode2.getValueAt(bar, 2).toString();
        String nip = tabmode2.getValueAt(bar, 1).toString();
//        String Keterangan = tabmode.getValueAt(bar, 2).toString();
//        String Tempat = tabmode.getValueAt(bar, 2).toString();
//        String tglLahir = tabmode.getValueAt(bar, 3).toString();
//        String JK = tabmode.getValueAt(bar, 4).toString();
//        String Lulusan = tabmode.getValueAt(bar, 5).toString();
//        String Jabatan = tabmode.getValueAt(bar, 6).toString();
//        String Agama = tabmode.getValueAt(bar, 7).toString();
//        String noTelp = tabmode.getValueAt(bar, 8).toString();
//        String Alamat = tabmode.getValueAt(bar, 9).toString();
//        String tglTest = tabmode.getValueAt(bar, 10).toString();
        tNIK.setText(nikTabel);
        tNama.setText(nip);
        tTempat.setText(tempat);
        ((JTextField) tTGL.getDateEditor().getUiComponent()).setText(tglLahir);
        tAlamat.setText(alamat);
        comboAgama.setSelectedItem(agama);
        comboJabatan.setSelectedItem(Jabatan);
        comboLulusan.setSelectedItem(Lulusan);
        tNama1.setText(Nama);

        if (jenisKelamin.equals("Laki-Laki")) {
            jRadioButton1.setSelected(true);
        } else {
            jRadioButton2.setSelected(true);
        }
        tNoHP.setText(noHP);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseEntered

    private void tNIK2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNIK2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNIK2KeyPressed

    private void tNIK2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNIK2KeyReleased
        int jumdata = 0;
        removeTable2();
        String item = tNIK2.getText();
        try {
            Statement stat = cn.createStatement();
            String sql = "SELECT * FROM Karyawan where NIK like '%" + item + "%'or Nama like'%" + item + "%' or NIP like '%" + item + "%' order by Nama asc";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String nip = rs.getString("NIP");
//                String Tempat = rs.getString("Tempat");
//                String tglLahir = rs.getString("tglLahir");
//                String JK = rs.getString("JK");
//                String Lulusan = rs.getString("Lulusan");
//                String Jabatan = rs.getString("Jabatan");
//                String Agama = rs.getString("Agama");
//                String noTelp = rs.getString("NoTelp");
//                String Alamat = rs.getString("Alamat");
                String Keterangan = rs.getString("Nama");
                String[] data = {NIK, nip, Keterangan};
                tabmode2.addRow(data);
                jumdata++;
            }
        } catch (Exception e) {
            jumdata = 0;
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tNIK2KeyReleased

    private void tNIK2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNIK2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tNIK2KeyTyped

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tNama1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNama1KeyTyped
        char vchar = evt.getKeyChar();
        if (Character.isLetter(vchar) || (vchar == KeyEvent.VK_SPACE) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_ENTER)) {
            evt = evt;
        } else {
            evt.consume();

            JOptionPane.showMessageDialog(null, "Hanya Character Yang Boleh Di Isi");
        }
    }//GEN-LAST:event_tNama1KeyTyped

    private void tNoHPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNoHPKeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tNoHPKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new menuUtama().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(dataKaryawanFrame_Revisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dataKaryawanFrame_Revisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dataKaryawanFrame_Revisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dataKaryawanFrame_Revisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dataKaryawanFrame_Revisi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboAgama;
    private javax.swing.JComboBox<String> comboJabatan;
    private javax.swing.JComboBox<String> comboLulusan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea tAlamat;
    private javax.swing.JTextField tNIK;
    private javax.swing.JTextField tNIK1;
    private javax.swing.JTextField tNIK2;
    private javax.swing.JTextField tNama;
    private javax.swing.JTextField tNama1;
    private javax.swing.JTextField tNoHP;
    private com.toedter.calendar.JDateChooser tTGL;
    private javax.swing.JLabel tTanggalPinjam;
    private javax.swing.JTextField tTempat;
    // End of variables declaration//GEN-END:variables
}
