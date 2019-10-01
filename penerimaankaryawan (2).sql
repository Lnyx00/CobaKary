-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 20, 2019 at 10:38 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penerimaankaryawan`
--

-- --------------------------------------------------------

--
-- Table structure for table `datapelamar`
--

CREATE TABLE `datapelamar` (
  `NIK` int(255) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Tempat` varchar(20) NOT NULL,
  `tglLahir` date NOT NULL,
  `JK` varchar(10) NOT NULL,
  `Lulusan` varchar(10) NOT NULL,
  `Jabatan` varchar(25) NOT NULL,
  `Agama` varchar(10) NOT NULL,
  `NoTelp` varchar(16) NOT NULL,
  `Alamat` varchar(100) NOT NULL,
  `tglTest` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `datapelamar`
--

INSERT INTO `datapelamar` (`NIK`, `Nama`, `Tempat`, `tglLahir`, `JK`, `Lulusan`, `Jabatan`, `Agama`, `NoTelp`, `Alamat`, `tglTest`) VALUES
(1111, 'ilham', 'Jakarta', '2019-06-28', 'Laki-Laki', 'S1', 'Staff Admin', 'Islam', '054', 'uyjvfjv', '2019-06-28'),
(2020, 'Lip', 'Jakarta', '2019-06-28', 'Laki-Laki', 'S1', 'Security', 'Islam', '054', 'uyjvfjv', '2019-06-28'),
(12422, 'LILO', 'Jakarta', '2019-06-28', 'Laki-Laki', 'S1', 'Security', 'Islam', '054', 'uyjvfjv', '2019-07-03'),
(20155, 'LILO', 'Jakarta', '2019-06-28', 'Laki-Laki', 'S1', 'Driver', 'Islam', '054', 'uyjvfjv', '2019-06-29'),
(34524, 'Syifa', 'Jakarta', '2019-06-28', 'Perempuan', 'S1', 'Driver', 'Islam', '0241', 'adadsadw', '2019-06-29'),
(84654, 'Risma Yanti', 'Jakarta', '2019-06-28', 'Perempuan', 'S1', 'Staff Admin', 'Islam', '01245', 'Condet', '2019-06-29'),
(99999, 'GG', 'Jakarta', '2019-06-28', 'Laki-Laki', 'S1', 'Staff Admin', 'Islam', '054', 'uyjvfjv', '2019-06-28'),
(201999, 'Lip', 'Jakarta', '2019-06-28', 'Laki-Laki', 'S1', 'Security', 'Islam', '054', 'uyjvfjv', '2019-06-28'),
(824413, 'Dwi', 'Jakarta', '2019-06-28', 'Perempuan', 'S1', 'Staff Admin', 'Islam', '52315233', 'Condet', '2019-07-04');

-- --------------------------------------------------------

--
-- Table structure for table `hasil_test`
--

CREATE TABLE `hasil_test` (
  `NIK` int(11) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `jenisKelamin` varchar(20) NOT NULL,
  `Jabatan` varchar(20) NOT NULL,
  `tglTest` date NOT NULL,
  `Keterangan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hasil_test`
--

INSERT INTO `hasil_test` (`NIK`, `Nama`, `jenisKelamin`, `Jabatan`, `tglTest`, `Keterangan`) VALUES
(20155, 'LILO', 'Laki-Laki', 'Driver', '2019-06-28', 'LULUS'),
(34524, 'Syifa', 'Perempuan', 'Driver', '2019-06-29', 'LULUS'),
(84654, 'Risma Yanti', 'Perempuan', 'Staff Admin', '2019-06-29', 'LULUS'),
(824413, 'Dwi', 'Perempuan', 'Staff Admin', '2019-07-04', 'LULUS');

-- --------------------------------------------------------

--
-- Table structure for table `hasil_test_pelatihan`
--

CREATE TABLE `hasil_test_pelatihan` (
  `NIK` int(11) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Keterangan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `NIK` int(11) NOT NULL,
  `NIP` int(11) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `JK` varchar(100) NOT NULL,
  `Jabatan` varchar(100) NOT NULL,
  `Lulusan` varchar(100) NOT NULL,
  `Tempat` varchar(100) NOT NULL,
  `tglLahir` date NOT NULL,
  `Agama` varchar(100) NOT NULL,
  `Alamat` varchar(100) NOT NULL,
  `noTelp` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`NIK`, `NIP`, `Nama`, `JK`, `Jabatan`, `Lulusan`, `Tempat`, `tglLahir`, `Agama`, `Alamat`, `noTelp`) VALUES
(1111, 82471, 'Ilham Ariyanda', 'Laki-Laki', 'Staff Admin', 'S1', 'Jakarta', '2019-06-28', 'Islam', 'uyjvfjv', '054'),
(2023, 541, 'Ilham Ariyanda', 'Laki-Laki', 'Security', 'S1', 'Jakarta', '2019-06-28', 'Islam', 'uyjvfjv', '054'),
(20155, 52102, 'LILO', 'Laki-Laki', 'Driver', 'S1', 'Jakarta', '2019-06-28', 'Islam', 'uyjvfjv', '054'),
(99999, 8888, 'GG', 'Laki-Laki', 'Staff Admin', 'S1', 'Jakarta', '2019-06-28', 'Islam', 'uyjvfjv', '054'),
(824413, 99999999, 'Dwi', 'Perempuan', 'Staff Admin', 'S1', 'Jakarta', '2019-06-28', 'Islam', 'Condet', '52315233');

-- --------------------------------------------------------

--
-- Table structure for table `lap_pen_karyawan`
--

CREATE TABLE `lap_pen_karyawan` (
  `NIK` int(255) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `jenisKelamin` varchar(10) NOT NULL,
  `Lulusan` varchar(10) NOT NULL,
  `Jabatan` varchar(20) NOT NULL,
  `tgl_lamar` date NOT NULL,
  `No_HP` int(16) NOT NULL,
  `tglTest` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lap_pen_karyawan`
--

INSERT INTO `lap_pen_karyawan` (`NIK`, `Nama`, `jenisKelamin`, `Lulusan`, `Jabatan`, `tgl_lamar`, `No_HP`, `tglTest`) VALUES
(1111, 'ilham', 'Laki-Laki', 'S1', 'Staff Admin', '2019-06-28', 54, '2019-06-28'),
(2018, 'Lip', 'Laki-Laki', 'S1', 'Security', '2019-06-28', 54, '2019-06-28'),
(2019, 'Lip', 'Laki-Laki', 'S1', 'Security', '2019-06-28', 54, '2019-06-28'),
(12422, 'LILO', 'Laki-Laki', 'S1', 'Security', '2019-07-03', 54, '2019-07-03'),
(20155, 'LILO', 'Laki-Laki', 'S1', 'Driver', '2019-06-28', 54, '2019-06-29'),
(20188, 'Lip', 'Laki-Laki', 'S1', 'Security', '2019-06-27', 54, '2019-06-27'),
(34524, 'Syifa', 'Perempuan', 'S1', 'Driver', '2019-06-29', 241, '2019-06-29'),
(84654, 'Risma Yanti', 'Perempuan', 'S1', 'Staff Admin', '2019-06-29', 1245, '2019-06-29'),
(99999, 'GG', 'Laki-Laki', 'S1', 'Staff Admin', '2019-06-28', 54, '2019-06-28'),
(824413, 'Dwi', 'Perempuan', 'S1', 'Staff Admin', '2019-07-04', 52315233, '2019-07-04');

-- --------------------------------------------------------

--
-- Table structure for table `pelatihan`
--

CREATE TABLE `pelatihan` (
  `NIK` int(11) NOT NULL,
  `Nama` varchar(100) DEFAULT NULL,
  `JK` varchar(50) DEFAULT NULL,
  `Jabatan` varchar(50) DEFAULT NULL,
  `tglMulai` date DEFAULT NULL,
  `tglSelesai` date DEFAULT NULL,
  `Keterangan` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelatihan`
--

INSERT INTO `pelatihan` (`NIK`, `Nama`, `JK`, `Jabatan`, `tglMulai`, `tglSelesai`, `Keterangan`) VALUES
(2020, 'Lip', 'Laki-Laki', 'Security', '2019-06-29', '2019-06-29', 'LULUS'),
(20155, 'LILO', 'Laki-Laki', 'Driver', '2019-06-28', '2019-06-29', 'LULUS'),
(34524, 'Syifa', 'Perempuan', 'Driver', '2019-07-01', '2019-10-01', 'LULUS'),
(84654, 'Risma Yanti', 'Perempuan', 'Staff Admin', '2019-06-30', '2019-06-30', 'LULUS'),
(201999, 'Lip', 'Laki-Laki', 'Security', '2019-06-28', '2019-06-29', 'LULUS'),
(824413, 'Dwi', 'Perempuan', 'Staff Admin', '2019-08-01', '2019-11-01', 'LULUS');

-- --------------------------------------------------------

--
-- Table structure for table `userid`
--

CREATE TABLE `userid` (
  `Username` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userid`
--

INSERT INTO `userid` (`Username`, `Password`) VALUES
('admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `datapelamar`
--
ALTER TABLE `datapelamar`
  ADD PRIMARY KEY (`NIK`);

--
-- Indexes for table `hasil_test`
--
ALTER TABLE `hasil_test`
  ADD PRIMARY KEY (`NIK`);

--
-- Indexes for table `hasil_test_pelatihan`
--
ALTER TABLE `hasil_test_pelatihan`
  ADD PRIMARY KEY (`NIK`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`NIK`,`NIP`);

--
-- Indexes for table `lap_pen_karyawan`
--
ALTER TABLE `lap_pen_karyawan`
  ADD PRIMARY KEY (`NIK`);

--
-- Indexes for table `pelatihan`
--
ALTER TABLE `pelatihan`
  ADD PRIMARY KEY (`NIK`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
