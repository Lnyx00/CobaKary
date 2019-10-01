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
public class dataPelamarFrame_Revisi_BACKUP extends javax.swing.JFrame {

    private Connection cn = new koneksi().connect();
    private DefaultTableModel tabmode;
//public cariBuku cb = null;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    String nikTabel;

    /**
     * Creates new form dataPelamarFrame
     */
    public dataPelamarFrame_Revisi_BACKUP() {
        initComponents();
        tanggal();
        datatable();
    }

    protected void datatable() {
        Object[] Baris = {"NIK", "Nama", "Tempat", "Tgl Lahir", "JK", "Lulusan", "Jabatan", "Agama", "No Telp", "Alamat","tgl Test"};
        tabmode = new DefaultTableModel(null, Baris);
        jTable1.setModel(tabmode);
        String sql = "select * from datapelamar";
        try {
            Statement stat = cn.createStatement();
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
                String[] data = {NIK, Nama, Tempat, tglLahir, JK, Lulusan, Jabatan, Agama, noTelp, Alamat,tglTest};
                tabmode.addRow(data);
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

    private void simpanDataPelamar() {
        String JenisKelamin = "";
        if (jRadioButton1.isSelected()) {
            JenisKelamin = jRadioButton1.getText();
        } else {
            JenisKelamin = jRadioButton2.getText();
        }
        String sql = "INSERT INTO datapelamar (NIK,Nama,Tempat,tglLahir,JK,Lulusan,Jabatan,Agama,NoTelp,Alamat,tglTest) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            String tanggal = ((JTextField) tJadwalTest.getDateEditor().getUiComponent()).getText();
            String[] splited = tanggal.split("\\s+");
            String hari = (splited[0]);
            String tanggalLengkap = (splited[1]);
            String tgl = tanggalLengkap.substring(0,2);
            String bulan = tanggalLengkap.substring(3,5);
            String tahun = tanggalLengkap.substring(6,10);
            
            String fix = (tahun+"-"+bulan+"-"+tgl);
            System.out.println(fix);

            pst = cn.prepareStatement(sql);
            pst.setString(1, tNIK.getText());
            pst.setString(2, tNama.getText());
            pst.setString(3, tTempat.getText());
            pst.setString(4, ((JTextField) tTGL.getDateEditor().getUiComponent()).getText());
            pst.setString(5, JenisKelamin);
            pst.setString(6, (String) comboLulusan.getSelectedItem());
            pst.setString(7, (String) comboJabatan.getSelectedItem());
            pst.setString(8, (String) comboAgama.getSelectedItem());
            pst.setString(9, tNoHP.getText());
            pst.setString(10, tAlamat.getText());
            pst.setString(11, fix);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Pelamar Berhasil Disimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal disimpan" + e);
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
//        ((JTextField) tJadwalTest.getDateEditor().getUiComponent()).setText("");
    }

    private void simpanLap() {
        String JenisKelamin = "";
        if (jRadioButton1.isSelected()) {
            JenisKelamin = jRadioButton1.getText();
        } else {
            JenisKelamin = jRadioButton2.getText();
        }
        String sql = "INSERT INTO lap_pen_karyawan (NIK,Nama,jenisKelamin,Lulusan,Jabatan,tgl_lamar,No_HP,tglTest) VALUES (?,?,?,?,?,?,?,?)";
        try {
            String tanggal = ((JTextField) tJadwalTest.getDateEditor().getUiComponent()).getText();
            String[] splited = tanggal.split("\\s+");
            String hari = (splited[0]);
            String tanggalLengkap = (splited[1]);
            String tgl = tanggalLengkap.substring(0,2);
            String bulan = tanggalLengkap.substring(3,5);
            String tahun = tanggalLengkap.substring(6,10);
            
            String fix = (tahun+"-"+bulan+"-"+tgl);
            System.out.println(fix);

            pst = cn.prepareStatement(sql);
            pst.setString(1, tNIK.getText());
            pst.setString(2, tNama.getText());
            pst.setString(3, JenisKelamin);
            pst.setString(4, (String) comboLulusan.getSelectedItem());
            pst.setString(5, (String) comboJabatan.getSelectedItem());
            pst.setString(6, tTanggalPinjam.getText());
            pst.setString(7, tNoHP.getText());
            pst.setString(8, fix);

            pst.execute();
            JOptionPane.showMessageDialog(null, "Laporan Telah di tambahkan!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal disimpan" + e);
        }
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
                    String[] data = {NIK, Nama, Tempat, tglLahir, JK, Lulusan, Jabatan, Agama, noTelp, Alamat,tglTest};
                    tabmode.addRow(data);
                    jumdata++;
                }
            } catch (Exception e) {
                jumdata = 0;
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void cetakRegis() {
        String JenisKelamin = "";
        if (jRadioButton1.isSelected()) {
            JenisKelamin = jRadioButton1.getText();
        } else {
            JenisKelamin = jRadioButton2.getText();
        }
        try {
            String tanggalB = ((JTextField) tTGL.getDateEditor().getUiComponent()).getText();
            String tahun3 = tanggalB.substring(0, 4);
            String bulan3 = tanggalB.substring(5, 7);
            String tgl3 = tanggalB.substring(8, 10);
            String fixTanggal = (tgl3 + "-" + bulan3 + "-" + tahun3);
            String tanggal2 = tTanggalPinjam.getText();
            String tahun2 = tanggal2.substring(0, 4);
            String bulan2 = tanggal2.substring(5, 7);
            String tgl2 = tanggal2.substring(8, 10);
            String fixTanggal2 = (tgl2 + "-" + bulan2 + "-" + tahun2);

            System.out.println(fixTanggal);
            System.out.println(fixTanggal2);

            java.util.Date d = new java.util.Date();
            SimpleDateFormat s = new SimpleDateFormat("EEEE dd-MMMM-yyyy");
            String tgl = s.format(d);

//            String path = "./src/print/cetakBukti.jasper";
            HashMap parameter = new HashMap();
            InputStream path = getClass().getResourceAsStream("/print/cetakRegis.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(path);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            parameter.put("NIK", tNIK.getText());
            parameter.put("Nama", tNama.getText());
            parameter.put("Tempat", tTempat.getText());
            parameter.put("JK", JenisKelamin);
            parameter.put("fixTanggal", tgl);
            parameter.put("tglLahir", fixTanggal);
            parameter.put("tglPendaftaran", fixTanggal2);
//            parameter.put("tglTest", ((JTextField) tJadwalTest.getDateEditor().getUiComponent()).getText());
            parameter.put("comboJabatan", (String) comboJabatan.getSelectedItem());
//            parameter.put("nama",tNama.getText());
//            parameter.put("kelas",jLabel32.getText());
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, cn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void delete() {
        String nik = nikTabel;
        String delete = "DELETE from datapelamar where NIK='" + nik + "'";
        try {
            pst = cn.prepareStatement(delete);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Proses Sedang Berjalan");
//            datatable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "UPDATE GAGAL" + e);
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
        tJadwalTest = new com.toedter.calendar.JDateChooser();
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
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/indah-cargo.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Data Pelamar ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("NIK");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nama");

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

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Tanggal Test");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText(":");

        tJadwalTest.setDateFormatString("EEEE dd-MM-yyyy");

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
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel15)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(tJadwalTest, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(tTempat, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tTGL, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(tNIK))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(tNama))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton2)))
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
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addGap(10, 10, 10))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel10)
                                .addComponent(tTempat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tTGL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(jLabel9)
                            .addComponent(jLabel19)
                            .addComponent(tNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboAgama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18))
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel22)
                        .addComponent(jLabel23))
                    .addComponent(tJadwalTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        tTanggalPinjam.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Data Pelamar"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NIK", "Nama", "Tempat", "Tgl Lahir", "JK", "Lulusan", "Jabatan", "Agama", "No Telp", "Alamat", "Tgl Test"
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
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

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Kembali");

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Ubah");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setText("Cetak");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
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
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton5, jButton7});

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
                    .addComponent(jButton3)
                    .addComponent(jButton7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(316, 316, 316)
                        .addComponent(tTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1148, 709));
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
            simpanDataPelamar();
            simpanLap();
            int tanya = JOptionPane.showConfirmDialog(null, "Apakah Ingin Anda Cetak Bukti Registrasi", "Konfrimasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
            if (tanya == 0) {
                cetakRegis();
            } else {
                reset();
            }
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
        if (Character.isLetter(vchar) || (vchar == KeyEvent.VK_SPACE) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_ENTER)) {
            evt = evt;
        } else {
            evt.consume();

            JOptionPane.showMessageDialog(null, "Hanya Character Yang Boleh Di Isi");
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
        nikTabel = tabmode.getValueAt(bar, 0).toString();
        String Nama = tabmode.getValueAt(bar, 1).toString();
        String Tempat = tabmode.getValueAt(bar, 2).toString();
        String tglLahir = tabmode.getValueAt(bar, 3).toString();
        String JK = tabmode.getValueAt(bar, 4).toString();
        String Lulusan = tabmode.getValueAt(bar, 5).toString();
        String Jabatan = tabmode.getValueAt(bar, 6).toString();
        String Agama = tabmode.getValueAt(bar, 7).toString();
        String noTelp = tabmode.getValueAt(bar, 8).toString();
        String Alamat = tabmode.getValueAt(bar, 9).toString();
//        String tglTest = tabmode.getValueAt(bar, 10).toString();
        tNIK.setText(nikTabel);
        tNama.setText(Nama);
        tTempat.setText(Tempat);
        if (JK.equals("Laki-Laki")) {
            jRadioButton1.setSelected(true);
        } else {
            jRadioButton2.setSelected(true);
        }
        ((JTextField) tTGL.getDateEditor().getUiComponent()).setText(tglLahir);
        tNoHP.setText(noTelp);
        tAlamat.setText(Alamat);
//        ((JTextField) tJadwalTest.getDateEditor().getUiComponent()).setText(tglTest);
        comboLulusan.setSelectedItem(Lulusan);
        comboJabatan.setSelectedItem(Jabatan);
        comboAgama.setSelectedItem(Agama);
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
//                String tglTest = rs.getString("tglTest");
                String[] data = {NIK, Nama, Tempat, tglLahir, JK, Lulusan, Jabatan, Agama, noTelp, Alamat};
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
        delete();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        delete();
        simpanDataPelamar();
        datatable();
        reset();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       cetakRegis();
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(dataPelamarFrame_Revisi_BACKUP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dataPelamarFrame_Revisi_BACKUP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dataPelamarFrame_Revisi_BACKUP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dataPelamarFrame_Revisi_BACKUP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dataPelamarFrame_Revisi_BACKUP().setVisible(true);
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
    private javax.swing.JButton jButton7;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea tAlamat;
    private com.toedter.calendar.JDateChooser tJadwalTest;
    private javax.swing.JTextField tNIK;
    private javax.swing.JTextField tNIK1;
    private javax.swing.JTextField tNama;
    private javax.swing.JTextField tNoHP;
    private com.toedter.calendar.JDateChooser tTGL;
    private javax.swing.JLabel tTanggalPinjam;
    private javax.swing.JTextField tTempat;
    // End of variables declaration//GEN-END:variables
}
