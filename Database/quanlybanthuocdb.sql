-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 13, 2018 at 10:27 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlybanthuocdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaChiTietHD` int(11) NOT NULL,
  `MaHD` int(11) DEFAULT NULL,
  `TenThuoc` varchar(120) DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `TrieuChung` varchar(150) DEFAULT NULL,
  `ChuanDoan` varchar(200) DEFAULT NULL,
  `TrangThai` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` int(11) NOT NULL,
  `MaThuoc` int(11) DEFAULT NULL,
  `MaKH` int(11) DEFAULT NULL,
  `NgayBan` datetime DEFAULT NULL,
  `TrangThai` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` int(12) NOT NULL,
  `MaHD` int(11) DEFAULT NULL,
  `HoTen` varchar(32) DEFAULT NULL,
  `NgaySinh` datetime DEFAULT NULL,
  `GioiTinh` bit(3) DEFAULT NULL,
  `DiaChi` varchar(130) DEFAULT NULL,
  `SoDienThoai` char(11) DEFAULT NULL,
  `TrieuChung` varchar(150) DEFAULT NULL,
  `ChuanDoan` varchar(200) DEFAULT NULL,
  `TrangThai` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `khothuoc`
--

CREATE TABLE `khothuoc` (
  `MaThuoc` int(11) NOT NULL,
  `TenThuoc` varchar(50) DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `DonGia` float DEFAULT NULL,
  `DonVi` int(11) DEFAULT NULL,
  `NSX` datetime DEFAULT NULL,
  `HSD` datetime DEFAULT NULL,
  `TrangThai` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ngaynhap`
--

CREATE TABLE `ngaynhap` (
  `MaNhap` int(11) NOT NULL,
  `MaThuoc` int(11) DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `DonGia` int(11) DEFAULT NULL,
  `TrangThai` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` int(11) NOT NULL,
  `MaHD` int(11) DEFAULT NULL,
  `HoTen` varchar(32) DEFAULT NULL,
  `GioiTinh` bit(3) DEFAULT NULL,
  `DiaChi` varchar(130) DEFAULT NULL,
  `SoDienThoai` char(11) DEFAULT NULL,
  `Username` varchar(32) DEFAULT NULL,
  `Password` varchar(32) DEFAULT NULL,
  `TrangThai` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MaChiTietHD`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `MaThuoc` (`MaThuoc`),
  ADD KEY `MaKH` (`MaKH`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`),
  ADD KEY `MaHD` (`MaHD`);

--
-- Indexes for table `khothuoc`
--
ALTER TABLE `khothuoc`
  ADD PRIMARY KEY (`MaThuoc`);

--
-- Indexes for table `ngaynhap`
--
ALTER TABLE `ngaynhap`
  ADD PRIMARY KEY (`MaNhap`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`),
  ADD KEY `MaHD` (`MaHD`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  MODIFY `MaChiTietHD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKH` int(12) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khothuoc`
--
ALTER TABLE `khothuoc`
  MODIFY `MaThuoc` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ngaynhap`
--
ALTER TABLE `ngaynhap`
  MODIFY `MaNhap` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNV` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
