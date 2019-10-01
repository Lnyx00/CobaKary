/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFrame;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author ilham
 */
public class hasilTest_Revisi extends javax.swing.JFrame {

    private Connection cn = new koneksi().connect();
    private DefaultTableModel tabmode;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;

    String tabelJK,nikTabel;
    
    /**
     * Creates new form hasilTest
     */
    public hasilTest_Revisi() {
        initComponents();
        panelTestMobil.setVisible(false);
        panelTestSecurity.setVisible(false);
        panelTestStaffAdmin.setVisible(false);
        jButton3.setVisible(false);
        jButton2.setVisible(false);
        jButton8.setVisible(false);
        panelJadwalPelatihanTestSecurity.setVisible(false);
        panelJadwalPelatihanTestSecurity4.setVisible(false);
        panelJadwalPelatihanTestSecurity3.setVisible(false);
        datatable();
    }
    
    protected void datatable() {
        Object[] Baris = {"NIK", "Nama","Keterangan"};
        tabmode = new DefaultTableModel(null, Baris);
        jTable1.setModel(tabmode);
        String sql = "select * from hasil_test";
        try {
            Statement stat = cn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String Nama = rs.getString("Nama");
                String keterangan = rs.getString("Keterangan");
                tabelJK = rs.getString("jenisKelamin");
//                String Tempat = rs.getString("Tempat");
//                String tglLahir = rs.getString("tglLahir");
//                String JK = rs.getString("JK");
//                String Lulusan = rs.getString("Lulusan");
//                String Jabatan = rs.getString("Jabatan");
//                String Agama = rs.getString("Agama");
//                String noTelp = rs.getString("NoTelp");
//                String Alamat = rs.getString("Alamat");
//                String tglTest = rs.getString("tglTest");
                String[] data = {NIK, Nama,keterangan};
                tabmode.addRow(data);
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
    
    public void deletePelatihan() {
        String nik = tNama1.getText();
        String delete = "DELETE from pelatihan where NIK='" + nik + "'";
        try {
            pst = cn.prepareStatement(delete);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pelatihan Berhasil Di Hapus");
//            datatable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DELETE GAGAL" + e);
        }
System.out.println("INI TEST DELETE LAP " + nik);
    }

    private void cariNIK() {
        String sql = "select * from datapelamar where NIK=?";
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, tNIK.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                String nama = rs.getString("Nama");
                String JK = rs.getString("JK");
                String jabatan = rs.getString("Jabatan");
                String jadwalTest = rs.getString("tglTest");
                String noHandphone = rs.getString("NoTelp");
                tNama.setText(nama);
                tJK.setText(JK);
                tJabatan.setText(jabatan);
                tJadwalTest.setText(jadwalTest);
                tNoHP.setText(noHandphone);
                rs.close();
                pst.close();
            } else {
                JOptionPane.showMessageDialog(null, "Mohon Masukan No Induk Siswa Dengan benar!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    private void keterangan() {
        String jabString = tJabatan.getText();
        if (jabString.equals("Driver")) {
            panelTestMobil.setVisible(true);
            panelTestSecurity.setVisible(false);
//            panelNilaiSecurity.setVisible(false);
        } else if (jabString.equals("Security")) {
            panelTestMobil.setVisible(false);
            panelTestSecurity.setVisible(true);
            panelTestStaffAdmin.setVisible(false);
//            panelNilaiSecurity.setVisible(true);
        } else if (jabString.equals("Staff Admin")) {
            panelTestMobil.setVisible(false);
            panelTestSecurity.setVisible(false);
            panelTestStaffAdmin.setVisible(true);
        }
    }

    public void reset() {
        tNIK.setText("");
        tNama.setText("");
        tJK.setText("");
        tJabatan.setText("");
        tJadwalTest.setText("");
        tNoHP.setText("");
        tTinggi.setText("");
        tPushUp.setText("");
        tPullUp.setText("");
        tSitUp.setText("");
        tKeterangan2.setText("");
        tTeori.setText("");
        buttonGroup1.clearSelection();
        tKeterangan.setText("");
        tTeori1.setText("");
        tTeori2.setText("");
        tKeterangan1.setText("");
        panelTestMobil.setVisible(false);
        panelTestSecurity.setVisible(false);
        panelTestStaffAdmin.setVisible(false);
        jButton3.setVisible(false);
        panelJadwalPelatihanTestSecurity.setVisible(false);
        panelJadwalPelatihanTestSecurity4.setVisible(false);
        panelJadwalPelatihanTestSecurity3.setVisible(false);

    }

    private void simpanData() {
        String simpan = "INSERT INTO hasil_test (NIK,Nama,jenisKelamin,Jabatan,tglTest,Keterangan) VALUES (?,?,?,?,?,?)";
        String pilihan = tJabatan.getText();
        java.util.Date d = new java.util.Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String tgl = s.format(d);
        try {

            if (pilihan.equals("Driver")) {
                pst = cn.prepareStatement(simpan);
                pst.setString(1, tNIK.getText());
                pst.setString(2, tNama.getText());
                pst.setString(3, tJK.getText());
                pst.setString(4, tJabatan.getText());
                pst.setString(5, tgl);
                pst.setString(6, tKeterangan.getText());
                pst.execute();
                datatable();
                JOptionPane.showMessageDialog(null, "Sukses Di Simpan");
//                reset();
            } else if (pilihan.equals("Security")) {
                pst = cn.prepareStatement(simpan);
                pst.setString(1, tNIK.getText());
                pst.setString(2, tNama.getText());
                pst.setString(3, tJK.getText());
                pst.setString(4, tJabatan.getText());
                pst.setString(5, tgl);
                pst.setString(6, tKeterangan2.getText());
                pst.execute();
                datatable();
                JOptionPane.showMessageDialog(null, "Sukses Di Simpan");
//                reset();
            } else if (pilihan.equals("Staff Admin")) {
                pst = cn.prepareStatement(simpan);
                pst.setString(1, tNIK.getText());
                pst.setString(2, tNama.getText());
                pst.setString(3, tJK.getText());
                pst.setString(4, tJabatan.getText());
                pst.setString(5, tgl);
                pst.setString(6, tKeterangan1.getText());
                pst.execute();
                datatable();
                JOptionPane.showMessageDialog(null, "Sukses Di Simpan");
//                reset();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e);
        }
    }

    public void lulus() {
        String hasil = tKeterangan2.getText();
        if (hasil.equals("LULUS")) {
//            jButton3.setVisible(true);
            panelJadwalPelatihanTestSecurity.setVisible(true);
            jButton3.setVisible(false);
        } else if (hasil.equals("TIDAK LULUS")) {
            jButton3.setVisible(true);
            panelJadwalPelatihanTestSecurity.setVisible(false);
        }
    }

    public void simpanPelatihan() {
        String tanya = tJabatan.getText();
        if (tanya.equals("Driver")) {
            String simpan = "INSERT INTO pelatihan (NIK,Nama,JK,Jabatan,tglMulai,tglSelesai) VALUES (?,?,?,?,?,?)";
            try {
                pst = cn.prepareStatement(simpan);
                pst.setString(1, tNIK.getText());
                pst.setString(2, tNama.getText());
                pst.setString(3, tJK.getText());
                pst.setString(4, tJabatan.getText());
                pst.setString(5, ((JTextField) jDateChooser7.getDateEditor().getUiComponent()).getText());
                pst.setString(6, ((JTextField) jDateChooser8.getDateEditor().getUiComponent()).getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "SUKSES");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR" + e);
            }
        } else if (tanya.equals("Security")) {
            String simpan = "INSERT INTO pelatihan (NIK,Nama,JK,Jabatan,tglMulai,tglSelesai) VALUES (?,?,?,?,?,?)";
            try {
                pst = cn.prepareStatement(simpan);
                pst.setString(1, tNIK.getText());
                pst.setString(2, tNama.getText());
                pst.setString(3, tJK.getText());
                pst.setString(4, tJabatan.getText());
                pst.setString(5, ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText());
                pst.setString(6, ((JTextField) jDateChooser2.getDateEditor().getUiComponent()).getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "SUKSES");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR" + e);
            }
        } else if (tanya.equals("Staff Admin")) {
            String simpan = "INSERT INTO pelatihan (NIK,Nama,JK,Jabatan,tglMulai,tglSelesai) VALUES (?,?,?,?,?,?)";
            try {
                pst = cn.prepareStatement(simpan);
                pst.setString(1, tNIK.getText());
                pst.setString(2, tNama.getText());
                pst.setString(3, tJK.getText());
                pst.setString(4, tJabatan.getText());
                pst.setString(5, ((JTextField) jDateChooser9.getDateEditor().getUiComponent()).getText());
                pst.setString(6, ((JTextField) jDateChooser10.getDateEditor().getUiComponent()).getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "SUKSES");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR" + e);
            }
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
        tTanggalPinjam = new javax.swing.JLabel();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tNIK = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tNama = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tJK = new javax.swing.JTextField();
        tJabatan = new javax.swing.JTextField();
        tJadwalTest = new javax.swing.JTextField();
        tNoHP = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        panelTestSecurity = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tTinggi = new javax.swing.JTextField();
        tKeterangan2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        tPushUp = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        tPullUp = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        tSitUp = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        panelJadwalPelatihanTestSecurity = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();
        panelTestMobil = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tTeori = new javax.swing.JTextField();
        tKeterangan = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        lulusBox = new javax.swing.JCheckBox();
        tlBox = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        panelJadwalPelatihanTestSecurity3 = new javax.swing.JPanel();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jDateChooser8 = new com.toedter.calendar.JDateChooser();
        jButton17 = new javax.swing.JButton();
        panelTestStaffAdmin = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        tTeori1 = new javax.swing.JTextField();
        tKeterangan1 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        tTeori2 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        panelJadwalPelatihanTestSecurity4 = new javax.swing.JPanel();
        jDateChooser9 = new com.toedter.calendar.JDateChooser();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jDateChooser10 = new com.toedter.calendar.JDateChooser();
        jButton18 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        tNIK1 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        tNama1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        tJK1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();

        tTanggalPinjam.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/indah-cargo.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Pencarian Calon Karyawan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("NIK");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText(":");

        tNIK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tNIKKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNIKKeyTyped(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search_16x16.png"))); // NOI18N
        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(tNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel13)
                    .addComponent(tNIK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Informasi Calon Karyawan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Nama");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText(":");

        tNama.setEditable(false);
        tNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNamaKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Jenis Kelamin");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Jabatan");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText(":");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText(":");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Jadwal Tes");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText(":");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("No Handphone");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText(":");

        tJK.setEditable(false);
        tJK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tJKKeyTyped(evt);
            }
        });

        tJabatan.setEditable(false);
        tJabatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tJabatanKeyTyped(evt);
            }
        });

        tJadwalTest.setEditable(false);
        tJadwalTest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tJadwalTestKeyTyped(evt);
            }
        });

        tNoHP.setEditable(false);
        tNoHP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNoHPKeyTyped(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh_16x16.png"))); // NOI18N
        jButton11.setText("Reset");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Catatan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("1. Jika ada kesalahan keterangan mohon\n   di hapus data sebelumnya\n2. Jika ada kesalahan data pelamar dapat\n   di ubah pada halaman data pelamar\n3. jadwal pelatihan akan muncul secara\n   otomatis jika pelamar lulus test");
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton11)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(tNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(tJadwalTest, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(tJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(tJK, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel12)
                    .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(tJK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel16)
                    .addComponent(tJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel17)
                    .addComponent(tJadwalTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel18)
                    .addComponent(tNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Input Nilai Test", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel3.setLayout(new java.awt.CardLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Tinggi");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText(":");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText(":");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Keterangan");

        tTinggi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tTinggiActionPerformed(evt);
            }
        });
        tTinggi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tTinggiKeyTyped(evt);
            }
        });

        tKeterangan2.setEditable(false);
        tKeterangan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tKeterangan2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Save_16x16.png"))); // NOI18N
        jButton3.setText("Simpan");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Help_16x16.png"))); // NOI18N
        jButton4.setText("Info");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Check_16x16.png"))); // NOI18N
        jButton7.setText("Check");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Push Up");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText(":");

        tPushUp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tPushUpKeyTyped(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Pull Up");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText(":");

        tPullUp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tPullUpKeyTyped(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Sit Up");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText(":");

        tSitUp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tSitUpKeyTyped(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh_16x16.png"))); // NOI18N
        jButton12.setText("Reset");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        panelJadwalPelatihanTestSecurity.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Jadwal Pelatihan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Tgl Mulai");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText(":");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("Tgl Selesai");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText(":");

        jDateChooser2.setDateFormatString("yyyy-MM-dd");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Save_16x16.png"))); // NOI18N
        jButton6.setText("Simpan");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelJadwalPelatihanTestSecurityLayout = new javax.swing.GroupLayout(panelJadwalPelatihanTestSecurity);
        panelJadwalPelatihanTestSecurity.setLayout(panelJadwalPelatihanTestSecurityLayout);
        panelJadwalPelatihanTestSecurityLayout.setHorizontalGroup(
            panelJadwalPelatihanTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJadwalPelatihanTestSecurityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJadwalPelatihanTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJadwalPelatihanTestSecurityLayout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel39)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelJadwalPelatihanTestSecurityLayout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel37)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(jButton6)
                .addGap(20, 20, 20))
        );
        panelJadwalPelatihanTestSecurityLayout.setVerticalGroup(
            panelJadwalPelatihanTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJadwalPelatihanTestSecurityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJadwalPelatihanTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJadwalPelatihanTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(jLabel37))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(21, 21, 21)
                .addGroup(panelJadwalPelatihanTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJadwalPelatihanTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38)
                        .addComponent(jLabel39))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelTestSecurityLayout = new javax.swing.GroupLayout(panelTestSecurity);
        panelTestSecurity.setLayout(panelTestSecurityLayout);
        panelTestSecurityLayout.setHorizontalGroup(
            panelTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTestSecurityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelJadwalPelatihanTestSecurity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTestSecurityLayout.createSequentialGroup()
                        .addGroup(panelTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTestSecurityLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18)
                                .addComponent(tTinggi, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTestSecurityLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(53, 53, 53)
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(tPullUp, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTestSecurityLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(59, 59, 59)
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(tSitUp, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(panelTestSecurityLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(tPushUp, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTestSecurityLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addGroup(panelTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelTestSecurityLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(tKeterangan2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTestSecurityLayout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTestSecurityLayout.setVerticalGroup(
            panelTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTestSecurityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tTinggi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(panelTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tPushUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(panelTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel30)
                    .addComponent(tPullUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel31)
                    .addComponent(tSitUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tKeterangan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jButton7))
                .addGap(18, 18, 18)
                .addGroup(panelTestSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelJadwalPelatihanTestSecurity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(panelTestSecurity, "card2");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nilai Teori");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText(":");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Hasil Praktik");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText(":");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Keterangan");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText(":");

        tTeori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tTeoriKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tTeoriKeyTyped(evt);
            }
        });

        tKeterangan.setEditable(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Save_16x16.png"))); // NOI18N
        jButton2.setText("Simpan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(lulusBox);
        lulusBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lulusBox.setText("Lulus");
        lulusBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lulusBoxActionPerformed(evt);
            }
        });

        buttonGroup1.add(tlBox);
        tlBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tlBox.setText("Tidak Lulus");
        tlBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tlBoxActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Check_16x16.png"))); // NOI18N
        jButton5.setText("Info");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh_16x16.png"))); // NOI18N
        jButton13.setText("Reset");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        panelJadwalPelatihanTestSecurity3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Jadwal Pelatihan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jDateChooser7.setDateFormatString("yyyy-MM-dd");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Tgl Mulai");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText(":");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setText("Tgl Selesai");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setText(":");

        jDateChooser8.setDateFormatString("yyyy-MM-dd");

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Save_16x16.png"))); // NOI18N
        jButton17.setText("Simpan");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelJadwalPelatihanTestSecurity3Layout = new javax.swing.GroupLayout(panelJadwalPelatihanTestSecurity3);
        panelJadwalPelatihanTestSecurity3.setLayout(panelJadwalPelatihanTestSecurity3Layout);
        panelJadwalPelatihanTestSecurity3Layout.setHorizontalGroup(
            panelJadwalPelatihanTestSecurity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJadwalPelatihanTestSecurity3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJadwalPelatihanTestSecurity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelJadwalPelatihanTestSecurity3Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel51))
                    .addGroup(panelJadwalPelatihanTestSecurity3Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel49)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelJadwalPelatihanTestSecurity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser8, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(jDateChooser7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addComponent(jButton17)
                .addGap(20, 20, 20))
        );
        panelJadwalPelatihanTestSecurity3Layout.setVerticalGroup(
            panelJadwalPelatihanTestSecurity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJadwalPelatihanTestSecurity3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJadwalPelatihanTestSecurity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJadwalPelatihanTestSecurity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(jLabel49))
                    .addComponent(jDateChooser7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17))
                .addGap(21, 21, 21)
                .addGroup(panelJadwalPelatihanTestSecurity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJadwalPelatihanTestSecurity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(jLabel51))
                    .addComponent(jDateChooser8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelTestMobilLayout = new javax.swing.GroupLayout(panelTestMobil);
        panelTestMobil.setLayout(panelTestMobilLayout);
        panelTestMobilLayout.setHorizontalGroup(
            panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTestMobilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTestMobilLayout.createSequentialGroup()
                        .addGroup(panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelTestMobilLayout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13))
                            .addGroup(panelTestMobilLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(tKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelTestMobilLayout.createSequentialGroup()
                        .addGroup(panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTestMobilLayout.createSequentialGroup()
                                .addGroup(panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelTestMobilLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel19))
                                    .addGroup(panelTestMobilLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel20)))
                                .addGap(18, 18, 18)
                                .addGroup(panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTestMobilLayout.createSequentialGroup()
                                        .addComponent(lulusBox)
                                        .addGap(18, 18, 18)
                                        .addComponent(tlBox))
                                    .addGroup(panelTestMobilLayout.createSequentialGroup()
                                        .addComponent(tTeori, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton5)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(panelJadwalPelatihanTestSecurity3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelTestMobilLayout.setVerticalGroup(
            panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTestMobilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel19)
                    .addComponent(tTeori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lulusBox)
                        .addComponent(tlBox)))
                .addGap(18, 18, 18)
                .addGroup(panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel23)
                    .addComponent(tKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTestMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton13))
                .addGap(18, 18, 18)
                .addComponent(panelJadwalPelatihanTestSecurity3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        jPanel3.add(panelTestMobil, "card3");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Nilai Teori");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText(":");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Nilai Excel");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText(":");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Keterangan");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText(":");

        tTeori1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tTeori1ActionPerformed(evt);
            }
        });
        tTeori1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tTeori1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tTeori1KeyTyped(evt);
            }
        });

        tKeterangan1.setEditable(false);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Save_16x16.png"))); // NOI18N
        jButton8.setText("Simpan");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Help_16x16.png"))); // NOI18N
        jButton9.setText("Info");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Check_16x16.png"))); // NOI18N
        jButton10.setText("Check");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        tTeori2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tTeori2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tTeori2KeyTyped(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh_16x16.png"))); // NOI18N
        jButton14.setText("Reset");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        panelJadwalPelatihanTestSecurity4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Jadwal Pelatihan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jDateChooser9.setDateFormatString("yyyy-MM-dd");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("Tgl Mulai");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText(":");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("Tgl Selesai");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setText(":");

        jDateChooser10.setDateFormatString("yyyy-MM-dd");

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Save_16x16.png"))); // NOI18N
        jButton18.setText("Simpan");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelJadwalPelatihanTestSecurity4Layout = new javax.swing.GroupLayout(panelJadwalPelatihanTestSecurity4);
        panelJadwalPelatihanTestSecurity4.setLayout(panelJadwalPelatihanTestSecurity4Layout);
        panelJadwalPelatihanTestSecurity4Layout.setHorizontalGroup(
            panelJadwalPelatihanTestSecurity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJadwalPelatihanTestSecurity4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJadwalPelatihanTestSecurity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelJadwalPelatihanTestSecurity4Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel55))
                    .addGroup(panelJadwalPelatihanTestSecurity4Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel53)))
                .addGroup(panelJadwalPelatihanTestSecurity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJadwalPelatihanTestSecurity4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelJadwalPelatihanTestSecurity4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton18)
                .addContainerGap())
        );
        panelJadwalPelatihanTestSecurity4Layout.setVerticalGroup(
            panelJadwalPelatihanTestSecurity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJadwalPelatihanTestSecurity4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJadwalPelatihanTestSecurity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJadwalPelatihanTestSecurity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel52)
                        .addComponent(jLabel53))
                    .addComponent(jDateChooser9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18))
                .addGap(21, 21, 21)
                .addGroup(panelJadwalPelatihanTestSecurity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJadwalPelatihanTestSecurity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel54)
                        .addComponent(jLabel55))
                    .addComponent(jDateChooser10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelTestStaffAdminLayout = new javax.swing.GroupLayout(panelTestStaffAdmin);
        panelTestStaffAdmin.setLayout(panelTestStaffAdminLayout);
        panelTestStaffAdminLayout.setHorizontalGroup(
            panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTestStaffAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTestStaffAdminLayout.createSequentialGroup()
                        .addGroup(panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelTestStaffAdminLayout.createSequentialGroup()
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14))
                            .addGroup(panelTestStaffAdminLayout.createSequentialGroup()
                                .addGroup(panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelTestStaffAdminLayout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel35))
                                    .addGroup(panelTestStaffAdminLayout.createSequentialGroup()
                                        .addGroup(panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel32))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addGap(18, 18, 18)
                                .addGroup(panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTestStaffAdminLayout.createSequentialGroup()
                                        .addComponent(tTeori1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton9))
                                    .addComponent(tKeterangan1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelTestStaffAdminLayout.createSequentialGroup()
                                        .addComponent(tTeori2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton10)))))
                        .addGap(0, 101, Short.MAX_VALUE))
                    .addComponent(panelJadwalPelatihanTestSecurity4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelTestStaffAdminLayout.setVerticalGroup(
            panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTestStaffAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTestStaffAdminLayout.createSequentialGroup()
                        .addGroup(panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tTeori1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9))
                        .addGap(18, 18, 18)
                        .addGroup(panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(tTeori2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10)))
                    .addComponent(jLabel26))
                .addGap(17, 17, 17)
                .addGroup(panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(tKeterangan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTestStaffAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelJadwalPelatihanTestSecurity4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        jPanel3.add(panelTestStaffAdmin, "card4");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Panel Edit Delete", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

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
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("NIK");

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

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search_16x16.png"))); // NOI18N
        jButton15.setText("Cari");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        tNama1.setEditable(false);
        tNama1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNama1KeyTyped(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText(":");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText(":");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Nama");

        tJK1.setEditable(false);
        tJK1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tJK1KeyTyped(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("NIK");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText(":");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Jenis Kelamin");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Keterangan");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText(":");

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setText("Laki-Laki");

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setText("Perempuan");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Panel Aksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Delete_16x16.png"))); // NOI18N
        jButton16.setText("Delete");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Previous_16x16.png"))); // NOI18N
        jButton20.setText("Kembali");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh_16x16.png"))); // NOI18N
        jButton21.setText("Reset");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton16, jButton20, jButton21});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton16)
                    .addComponent(jButton20)
                    .addComponent(jButton21))
                .addContainerGap())
        );

        buttonGroup3.add(jRadioButton3);
        jRadioButton3.setText("LULUS");

        buttonGroup3.add(jRadioButton4);
        jRadioButton4.setText("TIDAK LULUS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(18, 18, 18)
                        .addComponent(tNIK1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton15))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(jLabel41)
                            .addComponent(jLabel45)
                            .addComponent(jLabel59))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel60)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton4))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(18, 18, 18)
                                .addComponent(tNama1))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(18, 18, 18)
                                .addComponent(tJK1))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton2))))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(tNIK1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(tNama1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addComponent(tJK1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton3)
                        .addComponent(jRadioButton4))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel59)
                        .addComponent(jLabel60)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(509, 509, 509)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel2, jPanel3});

        setSize(new java.awt.Dimension(1295, 698));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tNIKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNIKKeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tNIKKeyTyped

    private void tNamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNamaKeyTyped
        char vchar = evt.getKeyChar();
        if (Character.isLetter(vchar) || (vchar == KeyEvent.VK_SPACE) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_ENTER)) {
            evt = evt;
        } else {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Hanya Character Yang Boleh Di Isi");
        }
    }//GEN-LAST:event_tNamaKeyTyped

    private void tJKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tJKKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tJKKeyTyped

    private void tJabatanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tJabatanKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tJabatanKeyTyped

    private void tJadwalTestKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tJadwalTestKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tJadwalTestKeyTyped

    private void tNoHPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNoHPKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tNoHPKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cariNIK();
        keterangan();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (tTeori.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Field Teori Kosong");
        } else if (buttonGroup1.equals("")) {
            JOptionPane.showMessageDialog(null, "Hasil Praktik Tidak Terpilih");
        } else {
            simpanData();

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        simpanData();
        datatable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tPushUpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tPushUpKeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tPushUpKeyTyped

    private void tPullUpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tPullUpKeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tPullUpKeyTyped

    private void tSitUpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tSitUpKeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tSitUpKeyTyped

    private void tNIKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNIKKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cariNIK();
            keterangan();
        }
    }//GEN-LAST:event_tNIKKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JOptionPane.showMessageDialog(null, "Tinggi minimal:\n"
                + "Laki = 165\n"
                + "Perempuan = 155\n"
                + "Push Up minimal:\n"
                + "Laki = 15\n"
                + "Perempuan = 8\n"
                + "Pull up minimal:\n"
                + "Laki = 10\n"
                + "Chining Up = 5\n"
                + "Sit Up minimal:\n"
                + "Laki = 30\n"
                + "Perempuan = 12\n");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JOptionPane.showMessageDialog(null, "Nilai Minumum Teori Kelulusan adalah 21");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tTeoriKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTeoriKeyReleased
        int teori = Integer.parseInt(tTeori.getText());

        if (tTeori.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "KOSONG");
        } else if (teori < 21) {
            tlBox.setSelected(true);
            lulusBox.setSelected(false);
            tKeterangan.setText("TIDAK LULUS");
            jButton2.setVisible(true);

        } else if (teori >= 21) {
            buttonGroup1.clearSelection();
            tKeterangan.setText("");
            jButton2.setVisible(false);
        }

//        if else(teori >= 21) {
//            tlBox.setSelected(false);
//            lulusBox.setSelected(false);
//            tKeterangan.setText("");
//        }
    }//GEN-LAST:event_tTeoriKeyReleased

    private void lulusBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lulusBoxActionPerformed
        String hasil = "";
        if (lulusBox.isSelected()) {
            tKeterangan.setText("LULUS");
            panelJadwalPelatihanTestSecurity3.setVisible(true);
            jButton2.setVisible(false);

        }
    }//GEN-LAST:event_lulusBoxActionPerformed

    private void tlBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tlBoxActionPerformed
        if (tlBox.isSelected()) {
            tKeterangan.setText("TIDAK LULUS");
            panelJadwalPelatihanTestSecurity3.setVisible(false);
        }
        if (tKeterangan.getText().equals("TIDAK LULUS")) {
            jButton2.setVisible(true);
        }
    }//GEN-LAST:event_tlBoxActionPerformed

    private void tTinggiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTinggiKeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tTinggiKeyTyped

    private void tTinggiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTinggiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTinggiActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String jk = tJK.getText();
        if (tJK.getText().equals("Laki-Laki")) {
            if (tTinggi.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Field Tinggi Kosong");
            } else if (tPushUp.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Field Push Up Kosong");
            } else if (tPullUp.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Field Pull Up Kosong");
            } else if (tSitUp.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Field Push Up Kosong");
            } else {
                int tinggi = Integer.parseInt(tTinggi.getText());
                int pushUp = Integer.parseInt(tPushUp.getText());
                int pullUp = Integer.parseInt(tPullUp.getText());
                int sitUp = Integer.parseInt(tSitUp.getText());

                if (tinggi < 165 || pushUp < 15 || pullUp < 10 || sitUp < 30) {
                    tKeterangan2.setText("TIDAK LULUS");
                } else if (tinggi >= 165 && pushUp >= 20 && pullUp >= 10 && sitUp >= 30) {
                    tKeterangan2.setText("LULUS");
                }
            }
        } else if (tJK.getText().equals("Perempuan")) {
            if (tTinggi.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Field Tinggi Kosong");
            } else if (tPushUp.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Field Push Up Kosong");
            } else if (tPullUp.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Field Pull Up Kosong");
            } else if (tSitUp.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Field Push Up Kosong");
            } else {
                int tinggi = Integer.parseInt(tTinggi.getText());
                int pushUp = Integer.parseInt(tPushUp.getText());
                int pullUp = Integer.parseInt(tPullUp.getText());
                int sitUp = Integer.parseInt(tSitUp.getText());
                if (tinggi < 155 || pushUp < 8 || pullUp < 5 || sitUp < 12) {
                    tKeterangan2.setText("TIDAK LULUS");
                } else if (tinggi >= 155 && pushUp >= 8 && pullUp >= 40 && sitUp >= 12) {
                    tKeterangan2.setText("LULUS");
                }
            }
        }
            lulus();


    }//GEN-LAST:event_jButton7ActionPerformed

    private void tTeori1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTeori1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tTeori1KeyReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        simpanData();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        JOptionPane.showMessageDialog(null, "Minimal Nilai Teori adalah 70 \nMinimal Nilai Praktik Excel 70");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int nilTeori = Integer.parseInt(tTeori1.getText());
        int nilaiExcel = Integer.parseInt(tTeori2.getText());

        if (tTeori1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Field Teori Kosong");
        } else if (tTeori2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Field Excel Kosong");
        } else {
            if (nilTeori < 70 || nilaiExcel < 70) {
                tKeterangan1.setText("TIDAK LULUS");
                panelJadwalPelatihanTestSecurity4.setVisible(false);
                jButton8.setVisible(true);
            } else {
                tKeterangan1.setText("LULUS");
                panelJadwalPelatihanTestSecurity4.setVisible(true);
                jButton8.setVisible(false);
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void tTeori2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTeori2KeyReleased
        int nilTeori = Integer.parseInt(tTeori1.getText());
        int nilaiExcel = Integer.parseInt(tTeori2.getText());
        if (nilTeori < 70 || nilaiExcel < 70) {
            tKeterangan1.setText("TIDAK LULUS");
            panelJadwalPelatihanTestSecurity4.setVisible(false);
            jButton8.setVisible(true);
        } else {
            tKeterangan1.setText("LULUS");
            panelJadwalPelatihanTestSecurity4.setVisible(true);
            jButton8.setVisible(false);
        }
    }//GEN-LAST:event_tTeori2KeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        tNama.setText("");
        tJK.setText("");
        tJabatan.setText("");
        tJadwalTest.setText("");
        tNoHP.setText("");
        reset();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        tTinggi.setText("");
        tPushUp.setText("");
        tPullUp.setText("");
        tSitUp.setText("");
        tKeterangan2.setText("");
        reset();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        tTeori.setText("");
        tlBox.setSelected(false);
        lulusBox.setSelected(false);
        tKeterangan.setText("");
        reset();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        tTeori1.setText("");
        tTeori2.setText("");
        tKeterangan1.setText("");
        reset();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void tTeori1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTeori1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTeori1ActionPerformed

    private void tTeori1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTeori1KeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tTeori1KeyTyped

    private void tTeori2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTeori2KeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tTeori2KeyTyped

    private void tTeoriKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTeoriKeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tTeoriKeyTyped

    private void tKeterangan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tKeterangan2ActionPerformed

    }//GEN-LAST:event_tKeterangan2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        simpanData();
        simpanPelatihan();
        reset();
        datatable();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        simpanData();
        simpanPelatihan();
        reset();
//        reset();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        simpanData();
        simpanPelatihan();
        reset();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        tNIK1.setText("");
        tNama1.setText("");
        tJK1.setText("");
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        String nik = tNama1.getText();
        String delete = "DELETE from hasil_test where NIK='" + nik + "'";
        try {
            pst = cn.prepareStatement(delete);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sukses di hapus");
            datatable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "UPDATE GAGAL" + e);
        }
        deletePelatihan();
        reset();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void tJK1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tJK1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tJK1KeyTyped

    private void tNama1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNama1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tNama1KeyTyped

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void tNIK1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNIK1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tNIK1KeyTyped

    private void tNIK1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNIK1KeyReleased
        int jumdata = 0;
        removeTable();
        String item = tNIK1.getText();
        try {
            Statement stat = cn.createStatement();
            String sql = "SELECT * FROM hasil_test where NIK like '%" + item + "%'or Nama like'%" + item + "%'order by Nama asc";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String NIK = rs.getString("NIK");
                String Nama = rs.getString("Nama");
                //                String Tempat = rs.getString("Tempat");
                //                String tglLahir = rs.getString("tglLahir");
                //                 tabelJK = rs.getString("jenisKelamin");
                //                String Lulusan = rs.getString("Lulusan");
                //                String Jabatan = rs.getString("Jabatan");
                //                String Agama = rs.getString("Agama");
                //                String noTelp = rs.getString("NoTelp");
                //                String Alamat = rs.getString("Alamat");
                //                String tglTest = rs.getString("tglTest");
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
        // TODO add your handling code here:
    }//GEN-LAST:event_tNIK1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int bar = jTable1.getSelectedRow();
        nikTabel = tabmode.getValueAt(bar, 0).toString();
        String Nama = tabmode.getValueAt(bar, 1).toString();
        String Keterangan = tabmode.getValueAt(bar, 2).toString();
        //        String tglLahir = tabmode.getValueAt(bar, 3).toString();
        //        String JK = tabmode.getValueAt(bar, 4).toString();
        //        String Lulusan = tabmode.getValueAt(bar, 5).toString();
        //        String Jabatan = tabmode.getValueAt(bar, 6).toString();
        //        String Agama = tabmode.getValueAt(bar, 7).toString();
        //        String noTelp = tabmode.getValueAt(bar, 8).toString();
        //        String Alamat = tabmode.getValueAt(bar, 9).toString();
        //        String tglTest = tabmode.getValueAt(bar, 10).toString();

        tNama1.setText(nikTabel);
        tJK1.setText(Nama);
        if(tabelJK.equals("Laki-Laki")) {
            jRadioButton1.setSelected(true);
        } else {
            jRadioButton2.setSelected(true);
        }

        if(Keterangan.equals("LULUS")) {
            jRadioButton3.setSelected(true);
        } else {
            jRadioButton4.setSelected(true);
        }

        //          tNoHP2.setText(Keterangan);

        //        tNIK.setText(nikTabel);
        //        tNama.setText(Nama);
        //        tTempat.setText(Tempat);
        //        if (JK.equals("Laki-Laki")) {
            //            jRadioButton1.setSelected(true);
            //        } else {
            //            jRadioButton2.setSelected(true);
            //        }
        //        ((JTextField) tTGL.getDateEditor().getUiComponent()).setText(tglLahir);
        //        tNoHP.setText(noTelp);
        //        tAlamat.setText(Alamat);
        ////        ((JTextField) tJadwalTest.getDateEditor().getUiComponent()).setText(tglTest);
        //        comboLulusan.setSelectedItem(Lulusan);
        //        comboJabatan.setSelectedItem(Jabatan);
        //        comboAgama.setSelectedItem(Agama);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        new menuUtama().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton20ActionPerformed

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
            java.util.logging.Logger.getLogger(hasilTest_Revisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(hasilTest_Revisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(hasilTest_Revisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(hasilTest_Revisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new hasilTest_Revisi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser10;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser7;
    private com.toedter.calendar.JDateChooser jDateChooser8;
    private com.toedter.calendar.JDateChooser jDateChooser9;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JCheckBox lulusBox;
    private javax.swing.JPanel panelJadwalPelatihanTestSecurity;
    private javax.swing.JPanel panelJadwalPelatihanTestSecurity3;
    private javax.swing.JPanel panelJadwalPelatihanTestSecurity4;
    private javax.swing.JPanel panelTestMobil;
    private javax.swing.JPanel panelTestSecurity;
    private javax.swing.JPanel panelTestStaffAdmin;
    private javax.swing.JTextField tJK;
    private javax.swing.JTextField tJK1;
    private javax.swing.JTextField tJabatan;
    private javax.swing.JTextField tJadwalTest;
    private javax.swing.JTextField tKeterangan;
    private javax.swing.JTextField tKeterangan1;
    private javax.swing.JTextField tKeterangan2;
    private javax.swing.JTextField tNIK;
    private javax.swing.JTextField tNIK1;
    private javax.swing.JTextField tNama;
    private javax.swing.JTextField tNama1;
    private javax.swing.JTextField tNoHP;
    private javax.swing.JTextField tPullUp;
    private javax.swing.JTextField tPushUp;
    private javax.swing.JTextField tSitUp;
    private javax.swing.JLabel tTanggalPinjam;
    private javax.swing.JTextField tTeori;
    private javax.swing.JTextField tTeori1;
    private javax.swing.JTextField tTeori2;
    private javax.swing.JTextField tTinggi;
    private javax.swing.JCheckBox tlBox;
    // End of variables declaration//GEN-END:variables
}
