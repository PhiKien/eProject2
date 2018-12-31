-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 31, 2018 lúc 03:42 PM
-- Phiên bản máy phục vụ: 10.1.34-MariaDB
-- Phiên bản PHP: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlycuahangthuoc`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHD` int(11) NOT NULL,
  `MaThuoc` int(11) NOT NULL,
  `SoLuong` smallint(6) DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
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
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` int(11) NOT NULL,
  `HoTenKH` varchar(100) COLLATE utf8_bin NOT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `DiaChi` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `TrieuChung` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ChuanDoan` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `TrangThai` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `HoTenKH`, `NgaySinh`, `GioiTinh`, `DiaChi`, `TrieuChung`, `ChuanDoan`, `TrangThai`) VALUES
(1, 'Nguyen Quang Toan', '2014-01-12', 'Nam', 'Ha Noi', '', '', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khothuoc`
--

CREATE TABLE `khothuoc` (
  `MaThuoc` int(11) NOT NULL,
  `TenThuoc` varchar(100) COLLATE utf8_bin NOT NULL,
  `DonVi` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `DonGia` int(11) DEFAULT NULL,
  `NSX` date DEFAULT NULL,
  `HSD` date DEFAULT NULL,
  `MaNhomThuoc` int(11) DEFAULT NULL,
  `TrangThai` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
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
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `HoTenNV`, `NgaySinh`, `GioiTinh`, `DiaChi`, `Usernane`, `Password`, `TrangThai`) VALUES
(4, 'admin', '2018-12-31', 'Nam', 'Ha Noi', 'admin', 'admin', 1),
(32, 'nquangtoan', '1998-01-12', 'Nam', 'Ha Tay', 'nquangtoan11', '123456', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhomthuoc`
--

CREATE TABLE `nhomthuoc` (
  `MaNhomThuoc` int(11) NOT NULL,
  `TenNhomThuoc` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `GhiChu` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Đang đổ dữ liệu cho bảng `nhomthuoc`
--

INSERT INTO `nhomthuoc` (`MaNhomThuoc`, `TenNhomThuoc`, `GhiChu`, `TrangThai`) VALUES
(1, 'Nhom 1', 'Thuoc Khang Sinh', 1),
(2, 'Nhóm 2', 'Thuoc giam dau', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MaHD`,`MaThuoc`),
  ADD KEY `MaThuoc` (`MaThuoc`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Chỉ mục cho bảng `khothuoc`
--
ALTER TABLE `khothuoc`
  ADD PRIMARY KEY (`MaThuoc`),
  ADD KEY `MaNhomThuoc` (`MaNhomThuoc`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Chỉ mục cho bảng `nhomthuoc`
--
ALTER TABLE `nhomthuoc`
  ADD PRIMARY KEY (`MaNhomThuoc`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `khothuoc`
--
ALTER TABLE `khothuoc`
  MODIFY `MaThuoc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT cho bảng `nhomthuoc`
--
ALTER TABLE `nhomthuoc`
  MODIFY `MaNhomThuoc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`),
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaThuoc`) REFERENCES `khothuoc` (`MaThuoc`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Các ràng buộc cho bảng `khothuoc`
--
ALTER TABLE `khothuoc`
  ADD CONSTRAINT `khothuoc_ibfk_1` FOREIGN KEY (`MaNhomThuoc`) REFERENCES `nhomthuoc` (`MaNhomThuoc`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
