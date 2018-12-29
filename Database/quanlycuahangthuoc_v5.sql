-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 29, 2018 at 11:18 AM
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
-- Database: `quanlycuahangthuoc`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHD` int(11) NOT NULL,
  `MaThuoc` int(11) NOT NULL,
  `SoLuong` smallint(6) DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` int(11) NOT NULL,
  `NgayLapHD` date DEFAULT NULL,
  `MaKH` int(11) DEFAULT NULL,
  `MaNV` int(11) DEFAULT NULL,
  `TongTien` int(11) DEFAULT NULL,
  `TrangThai` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` int(11) NOT NULL,
  `HoTenKH` varchar(100) COLLATE utf8_bin NOT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` tinyint(1) DEFAULT NULL,
  `DiaChi` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `TrieuChung` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ChuanDoan` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `TrangThai` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `khothuoc`
--

CREATE TABLE `khothuoc` (
  `MaThuoc` int(11) NOT NULL,
  `TenThuoc` varchar(100) COLLATE utf8_bin NOT NULL,
  `DonVi` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `DonGia` int(11) DEFAULT NULL,
  `NSX` date DEFAULT NULL,
  `HSD` date DEFAULT NULL,
  `NguoiNhap` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `NgayNhap` date DEFAULT NULL,
  `MaNhomThuoc` int(11) DEFAULT NULL,
  `TrangThai` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` int(11) NOT NULL,
  `HoTenNV` varchar(100) COLLATE utf8_bin NOT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `DiaChi` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `Usernane` varchar(32) COLLATE utf8_bin NOT NULL,
  `Password` varchar(32) COLLATE utf8_bin NOT NULL,
  `TrangThai` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `HoTenNV`, `NgaySinh`, `GioiTinh`, `DiaChi`, `Usernane`, `Password`, `TrangThai`) VALUES
(4, 'admin', '2018-12-31', 'Nam', 'Ha Noi', 'admin', 'admin', 1),
(5, 'admin2', '1999-03-31', NULL, NULL, 'admin2', 'admin2', 0),
(6, 'nguyen van a', '2019-01-01', NULL, NULL, 'nguyenvana', '123456', 0),
(8, 'nguyen van b', '1999-03-31', NULL, NULL, 'nguyenvanb', '123456', 0),
(12, 'nguyen van c', '1111-11-11', NULL, NULL, 'nguyenvanc', '123456', 0),
(13, 'dasd', '2018-11-28', 'nam', 'sdasd', 'pck', 'pck', 0);

-- --------------------------------------------------------

--
-- Table structure for table `nhomthuoc`
--

CREATE TABLE `nhomthuoc` (
  `MaNhomThuoc` int(11) NOT NULL,
  `TenNhomThuoc` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `GhiChu` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MaHD`,`MaThuoc`),
  ADD KEY `MaThuoc` (`MaThuoc`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Indexes for table `khothuoc`
--
ALTER TABLE `khothuoc`
  ADD PRIMARY KEY (`MaThuoc`),
  ADD KEY `MaNhomThuoc` (`MaNhomThuoc`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Indexes for table `nhomthuoc`
--
ALTER TABLE `nhomthuoc`
  ADD PRIMARY KEY (`MaNhomThuoc`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKH` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khothuoc`
--
ALTER TABLE `khothuoc`
  MODIFY `MaThuoc` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `nhomthuoc`
--
ALTER TABLE `nhomthuoc`
  MODIFY `MaNhomThuoc` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`),
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaThuoc`) REFERENCES `khothuoc` (`MaThuoc`);

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Constraints for table `khothuoc`
--
ALTER TABLE `khothuoc`
  ADD CONSTRAINT `khothuoc_ibfk_1` FOREIGN KEY (`MaNhomThuoc`) REFERENCES `nhomthuoc` (`MaNhomThuoc`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
