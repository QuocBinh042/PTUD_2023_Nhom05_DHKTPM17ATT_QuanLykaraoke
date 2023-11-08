go
create database QuanLyKaraokeOne
go
use QuanLyKaraokeOne
go
--drop database QuanLyKaraokeOne
go
--------------Create Table
go
create table DichVu(
	MaDV nvarchar(20) not null primary key,
	TenDV nvarchar(50) not null,
	DonGiaNhap float not null,
	DonGiaBan float not null,
	DonViTinh nvarchar(20),
	SoLuongTonKho int,
	--TinhTrangDV (0: het, 1: sap het; 2: con; 3 da xoa)
	TinhTrangDV int 
)
go
create table KhachHang(
	MaKH nvarchar(20) not null primary key,
	TenKH nvarchar(50) not null,
	--LoaiKhachHang(0: thuong, 1: vip)
	LoaiKH bit,
	--GioiTinh(0: nam, 1: nu)
	GioiTinh bit,
	SoDienThoai varchar(10) not null,
	Email nvarchar(50),
	SoGioDaThue int,
	GhiChu nvarchar(50) 
)
go
create table NhanVien(
	MaNV nvarchar(20) not null primary key,
	TenNV  nvarchar(50) not null,
	NamSinh date,
	--GioiTinh (0: nam, 1: nu)
	GioiTinh bit,
	SoDienThoai nvarchar(10) not null,
	CCCD nvarchar(12) not null,
	--ChucVu (0: nhan vien quan ly, 1: le tan, 2: phuc vu)
	ChucVu int not null,
	MatKhau nvarchar(30) not null,
	--TinhTrangNV(0: nghi viec, 1: dang lam)
	TinhTrangNV bit
)
go
create table PhieuDatPhong(
	MaPDP nvarchar(20) not null primary key,
	-----FOREIGN KEY MaKhachHang
	MaKH nvarchar(20) not null,
	-----FOREIGN KEY MaNhanVien
	MaNV nvarchar(20) not null,
	NgayDatPhong date,
	GioDatPhong time,
	SoLuongKhach int,
	--TinhTrangPDP(0: chua thanh toan, 1: da thanh toan)
	TinhTrangPDP bit,
	MoTa nvarchar(50),
	CONSTRAINT fk_kh FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
	CONSTRAINT fk_nv FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
)
go
create table CTDVPhong(
	-------FOREIGN KEY PhieuDatPhong
	MaPDP nvarchar(20) not null,
	-------FOREIGN KEY DichVu
	MaDV nvarchar(20) not null,
	SoLuong int not null,
	CONSTRAINT fk_pdp FOREIGN KEY (MaPDP) REFERENCES PhieuDatPhong(MaPDP),
	CONSTRAINT fk_dv FOREIGN KEY (MaDV) REFERENCES DichVu(MaDV),
	PRIMARY KEY (MaPDP,MaDV)
)
go
create table HoaDon(
	MaHD nvarchar(20) not null primary key,
	-------FOREIGN KEY PhieuDatPhong
	MaPDP nvarchar(20) not null,
	GioThanhToan time,
	NgayThanhToan date,
	TienPhong float,
	TienDichVu float,
	TienGiam float,
	TongTien float,
	CONSTRAINT fk_maPDP FOREIGN KEY (MaPDP) REFERENCES PhieuDatPhong(MaPDP)
)
go
create table LoaiPhong(
	MaLP nvarchar(20) not null primary key,
	TenLP nvarchar(50) not null,
	SucChua int,
	GiaPhong float
)
go
create table KhuyenMai(
	MaKhuyenMai nvarchar(20) not null primary key,
	PhanTramGiam float,
	NgayBatDau date,
	NgayKetThuc date,
	MoTa nvarchar(50),
	--TrangThaiKM(0: ket thuc, 1: dang hoat dong)
	TrangThaiKM bit
)
go
create table Phong(
	MaPhong nvarchar(20) not null primary key,
	TenPhong nvarchar(50) not null,
	-------FOREIGN KEY LoaiPhong
	MaKM nvarchar(20) not null,
	MaLP nvarchar(20) not null,
	--TinhTrangPhong(0: con trong, 1: dang thue, 2: da dat truoc, 3: da xoa)
	TinhTrangPhong int,
	CONSTRAINT fk_lp FOREIGN KEY (MaLP) REFERENCES LoaiPhong(MaLP),
	CONSTRAINT fk_km FOREIGN KEY (MaKM) REFERENCES KhuyenMai(MaKhuyenMai)
)
go
create table CTPhong(
	-------FOREIGN KEY Phong
	MaPhong nvarchar(20) not null,
	-------FOREIGN KEY PhieuDatPhong
	MaPDP nvarchar(20) not null,
	NgayNhanPhong date,
	GioNhanPhong time,
	GioTraPhong time ,
	CONSTRAINT fk_maP FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong),
	CONSTRAINT fk_maPDphong FOREIGN KEY (MaPDP) REFERENCES PhieuDatPhong(MaPDP),
	PRIMARY KEY (MaPhong,MaPDP)
)

go

-----------Insert data

--------------------------------DICH VU-----------------------------------------------
--INSERT INTO DichVu(MaDV, TenDV, DonGiaNhap, DonGiaBan, DonViTinh, SoLuongTonKho, TinhTrangDV) 
INSERT INTO DichVu values(N'DV001', N'Khăn giấy', 3000, 5000, N'Bịch', 200, 2)
INSERT INTO DichVu values(N'DV002', N'Bia Heineken 330ml', 21000, 26000, N'Lon', 50, 1)
INSERT INTO DichVu values(N'DV003', N'Coca cola', 10000, 15000, N'Lon', 70, 1)
INSERT INTO DichVu values(N'DV004', N'Trái cây dĩa', 60000, 70000, N'Dĩa', 1, 0)
INSERT INTO DichVu values(N'DV005', N'Khăn giấy ướt', 5000, 8000, N'Bịch', 100, 2)
INSERT INTO DichVu values(N'DV006', N'Cocktail', 40000, 50000, N'Lon', 2, 0)
INSERT INTO DichVu values(N'DV007', N'Cà phê sữa đá', 15000, 18000, N'Ly', 100, 2)
INSERT INTO DichVu values(N'DV008', N'Đậu phộng', 16000, 20000, N'Bịch', 45, 1)
INSERT INTO DichVu values(N'DV009', N'Bia Tiger 500ml ', 27000, 35000, N'Lon', 60, 1)
INSERT INTO DichVu values(N'DV010', N'Cơm chiên hải sản', 30000, 40000, N'Dĩa', 1, 0)
INSERT INTO DichVu values(N'DV011', N'Sụn gà rang muối', 35000, 40000, N'Dĩa', 0, 3)
INSERT INTO DichVu values(N'DV012', N'Nước suối Aquafina 500ml', 4000, 7000, N'Chai', 150, 2)
INSERT INTO DichVu values(N'DV013', N'Mì xào hải sản', 30000, 40000, N'Dĩa', 20, 1)
INSERT INTO DichVu values(N'DV014', N'Thùng 24 lon bia Hà Nội 330ml', 282000, 315000, N'Thùng', 30, 1)
INSERT INTO DichVu values(N'DV015', N'Đậu hũ xóc tỏi ớt', 25000, 35000, N'Dĩa', 30, 1)
INSERT INTO DichVu values(N'DV016', N'Bia Sài Gòn Chill 330ml dạng lốc 6 lon', 102000, 130000, N'Lốc', 1, 0)
INSERT INTO DichVu values(N'DV017', N'Xúc xích nướng tiêu', 35000, 45000, N'Dĩa', 100, 2)
INSERT INTO DichVu values(N'DV018', N'Bia Huda 330ml', 11000, 15000, N'Lon', 0, 3)
INSERT INTO DichVu values(N'DV019', N'Chả giò', 30000, 40000, N'Dĩa', 70, 2)
INSERT INTO DichVu values(N'DV020', N'6 lon bia Heineken Silver 330ml', 120000, 150000, N'Lốc', 40, 1)
--Xem toan bo DichVu
select *from DichVu
--Insert DichVu
insert into DichVu (MaDV,TenDV,DonGiaNhap,DonGiaBan,DonViTinh,SoLuongTonKho,TinhTrangDV) values(N'DV021',N'Trái cây dĩa',50000,70000,N'Dĩa',100,1)
insert into DichVu values(N'DV021',N'Trái cây dĩa',50000,70000,N'Dĩa',100,1)
--Update DichVu
update DichVu 
set TenDV = N'Khăn giấy', DonGiaNhap = 2000, DonGiaBan = 5000, DonViTinh = N'Cái', SoLuongTonKho = 100
where MaDV = N'DV001'
--Delete DichVu
delete from DichVu where MaDV = N'DV021'
--Loc theo tinh trang (0: het, 1: sap het; 2: con; 3 da xoa)
select *from DichVu
where TinhTrangDV != 3
-- Tim dich vu theo ten
select *from DichVu
where TenDV like N'Bia'
--Xoa dich vu
delete from DichVu where MaDV = N'DV022'
--Lay toan bo ten dich vu
select TenDV from DichVu


--------------------------------KHACH HANG-----------------------------------------------
--INSERT INTO KhachHang(MaKH, TenKH, LoaiKH, GioiTinh, SoDienThoai, Email, SoGioDaThue, GhiChu)
INSERT INTO KhachHang values(N'KH001', N'Nguyễn Bùi Phát Đạt', 0, 0, '0649248221', N'phatdat123@gmail.com', 3, '')
INSERT INTO KhachHang values(N'KH002', N'Lê Thị Thảo Vy', 0, 1, '0492568801', N'vyvy444@gmail.com', 1, '')
INSERT INTO KhachHang values(N'KH003', N'Trần Mạnh Công', 1, 0, '0581943912', N'congmanh45@gmail.com', 4, '')
INSERT INTO KhachHang values(N'KH004', N'Dương Đình Toàn', 1, 0, '0194369932', N'dinhtoan17@gmail.com', 1, '')
INSERT INTO KhachHang values(N'KH005', N'Nguyễn Thu Sương', 0, 1, '0395354914', N'suongthu00@gmail.com', 2, '')
INSERT INTO KhachHang values(N'KH006', N'Nguyễn Thị Anh Thư', 0, 1, '0825164023', N'thuthu032@gmail.com', 5, '')
INSERT INTO KhachHang values(N'KH007', N'Nguyễn Toàn Vương', 1, 0, '0292165032', N'manhvuong@gmail.com', 2, '')
INSERT INTO KhachHang values(N'KH008', N'Tống Nguyên Kiệt', 0, 0, '0938654812', N'kiettongoo@gmail.com', 4, '')
INSERT INTO KhachHang values(N'KH009', N'Vũ Anh Thư', 1, 1, '0159264902', N'anhthu201@gmail.com', 1, '')
INSERT INTO KhachHang values(N'KH010', N'Nguyễn Thanh Thanh', 1, 0, '0730118701', N'thanhthanh@gmail.com', 6, '')
INSERT INTO KhachHang values(N'KH011', N'Nguyễn Thị Bích Loan', 1, 1, '0193276520', N'bichloan3@gmail.com', 5, '')
INSERT INTO KhachHang values(N'KH012', N'Vũ Minh Huy', 0, 0, '0569370341', N'huyhuy3498@gmail.com', 3, '')
INSERT INTO KhachHang values(N'KH013', N'Huỳnh Thanh Lộc', 1, 0, '0340554068', N'locthanh37@gmail.com', 1, '')
INSERT INTO KhachHang values(N'KH014', N'Lê Thị Yến Nhi', 0, 1, '0297450377', N'yennhi12@gmail.com', 4, '')
INSERT INTO KhachHang values(N'KH015', N'Nguyễn Thành Luân', 1, 0, '0409339601', N'luann65@gmail.com', 6, '')
INSERT INTO KhachHang values(N'KH016', N'Nguyễn Phương Khánh', 0, 0, '0692270465', N'phuongk333@gmail.com', 2, '')
INSERT INTO KhachHang values(N'KH017', N'Lê Phạm Công Khanh', 1, 0, '0592669130', N'khanhktt@gmail.com', 3, '')
INSERT INTO KhachHang values(N'KH018', N'Nguyễn Minh Nguyệt', 0, 1, '0856976509', N'minhn583@gmail.com', 5, '')
INSERT INTO KhachHang values(N'KH019', N'Nguyễn Ngọc Kim Liên', 1, 1, '0927550871', N'kimlien91@gmail.com', 4, '')
INSERT INTO KhachHang values(N'KH020', N'Đặng Hoàng Phúc', 0, 0, '0749205481', N'phucphuc32@gmail.com', 1, '')
--Xem toan bo KhachHang
select *from KhachHang


--------------------------------NHAN VIEN-----------------------------------------------
--INSERT INTO NhanVien(MaNV, TenNV, NamSinh, GioiTinh, SoDienThoai, CCCD, ChucVu, MatKhau, TinhTrangNV)
INSERT INTO NhanVien values(N'NV001', N'Nguyễn Thị Yến Nhi', '2003/10/05', 1, N'0395259603', N'082594657912', 1, N'nhinhi23@', 1)
INSERT INTO NhanVien values(N'NV002', N'Nguyễn Trung Kiên', '1999/07/21', 0, N'0205578931', N'064769123700', 1, N'kienn65i', 1)
INSERT INTO NhanVien values(N'NV003', N'Vũ Đức Thắng', '2004/02/15', 0, N'0702447601', N'076591236064', 0, N'thang54t', 0)
INSERT INTO NhanVien values(N'NV004', N'Lâm Thúy Hiền', '2002/05/18', 1, N'0452603567', N'084769431052', 2, N'hienthuy59r', 0)
INSERT INTO NhanVien values(N'NV005', N'Nguyễn Văn Thanh', '2001/11/05', 0, N'0160567832', N'073976012433', 2, N'vanthanh10', 1)
INSERT INTO NhanVien values(N'NV006', N'Nguyễn Hữu Nhật', '2000/04/23', 0, N'0870553419', N'087923408723', 0, N'nhathuu@07', 0)
INSERT INTO NhanVien values(N'NV007', N'Nguyễn Đỗ Gia Hân', '1998/06/01', 1, N'0573960875', N'062769236591', 0, N'giahan1314', 1)
INSERT INTO NhanVien values(N'NV008', N'Nguyễn Ngọc Anh Thư', '2001/04/29', 1, N'0952566034', N'073658012563', 1, N'anhthu542', 1)
INSERT INTO NhanVien values(N'NV009', N'Trần Anh Tuấn', '2003/09/25', 0, N'0651273390', N'088425070237', 2, N'tuananh76@a', 0)
INSERT INTO NhanVien values(N'NV010', N'Nguyễn Văn Phương', '2000/12/07', 0, N'0305671489', N'084072359038', 0, N'baoky3415', 0)
INSERT INTO NhanVien values(N'NV011', N'Trần Hoàng Nghĩa', '2001/05/22', 0, N'0582632176', N'076548160533', 1, N'hoangnghia25#', 1)
INSERT INTO NhanVien values(N'NV012', N'Nguyễn Ngọc Phương Anh', '2000/01/15', 1, N'0154787932', N'072976002137', 1, N'panhhh5932', 0)
INSERT INTO NhanVien values(N'NV013', N'Trần Thị Thảo Trang', '2002/10/28', 1, N'0748500751', N'086942660432', 2, N'trangtr298', 0)
INSERT INTO NhanVien values(N'NV014', N'Nguyễn Cao Minh Khôi', '2002/03/09', 0, N'0952633601', N'069432866015', 2, N'minhkhoi3@#', 1)
INSERT INTO NhanVien values(N'NV015', N'Hà Bảo Ngọc', '2003/09/21', 1, N'0367927602', N'083602658934', 2, N'ngocbao639', 1)
INSERT INTO NhanVien values(N'NV016', N'Nguyễn Minh Nhựt', '2000/11/06', 0, N'0638477036', N'073965234671', 0, N'nhutminh532', 1)
INSERT INTO NhanVien values(N'NV017', N'Phạm Quế Trân', '2004/03/25', 1, N'0838614401', N'063634789932', 0, N'quetran406#', 1)
INSERT INTO NhanVien values(N'NV018', N'Châu Văn Tấn', '1999/07/18', 0, N'0269743064', N'083852704766', 1, N'tantan396@', 0)
INSERT INTO NhanVien values(N'NV019', N'Huỳnh Chí Phong', '2002/06/10', 0, N'0478280031', N'071603449254', 1, N'chiphongeu4', 0)
INSERT INTO NhanVien values(N'NV020', N'Trần Thị Quỳnh Như', '1999/04/17', 1, N'0386370342', N'084598701225', 2, N'nhuquynhh45', 1)
--Xem toan bo NhanVien
select *from NhanVien

--------------------------------PHIEU DAT PHONG-----------------------------------------------
--INSERT INTO PhieuDatPhong(MaPDP, MaKH, MaNV, NgayDatPhong, GioDatPhong, SoLuongKhach, TinhTrangPDP, MoTa)
INSERT INTO PhieuDatPhong values(N'PDP001', N'KH001', N'NV001', '2022/07/23', '9:25:43', 4, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP002', N'KH002', N'NV002', '2022/03/11', '8:45:12', 3, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP003', N'KH003', N'NV003', '2022/10/05', '13:10:06', 7, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP004', N'KH004', N'NV004', '2022/04/28', '17:05:25', 2, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP005', N'KH005', N'NV005', '2022/09/15', '20:27:18', 5, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP006', N'KH006', N'NV006', '2022/01/13', '11:30:15', 5, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP007', N'KH007', N'NV007', '2022/11/04', '22:14:27', 6, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP008', N'KH008', N'NV008', '2022/05/27', '10:40:10', 8, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP009', N'KH009', N'NV009', '2022/08/16', '12:05:50', 3, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP010', N'KH010', N'NV010', '2022/12/01', '19:35:38', 4, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP011', N'KH011', N'NV011', '2022/07/19', '14:23:39', 2, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP012', N'KH012', N'NV012', '2022/06/25', '21:36:55', 7, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP013', N'KH013', N'NV013', '2023/10/01', '15:40:05', 5, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP014', N'KH014', N'NV014', '2023/05/21', '23:01:20', 3, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP015', N'KH015', N'NV015', '2023/09/18', '18:20:35', 4, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP016', N'KH016', N'NV016', '2023/01/27', '10:14:20', 2, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP017', N'KH017', N'NV017', '2023/08/06', '19:50:10', 8, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP018', N'KH018', N'NV018', '2023/06/29', '14:21:37', 5, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP019', N'KH019', N'NV019', '2023/07/16', '8:30:45', 6, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP020', N'KH020', N'NV020', '2023/04/24', '17:44:03', 3, 1, '')
--Xem toan bo PhieuDatPhong
select *from PhieuDatPhong


--------------------------------CTDV PHONG-----------------------------------------------
--INSERT INTO CTDVPhong(MaPDP, MaDV, SoLuong)
INSERT INTO CTDVPhong values(N'PDP001', N'DV001', 10)
INSERT INTO CTDVPhong values(N'PDP002', N'DV002', 8)
INSERT INTO CTDVPhong values(N'PDP003', N'DV003', 5)
INSERT INTO CTDVPhong values(N'PDP004', N'DV004', 13)
INSERT INTO CTDVPhong values(N'PDP005', N'DV005', 6)
INSERT INTO CTDVPhong values(N'PDP006', N'DV006', 20)
INSERT INTO CTDVPhong values(N'PDP007', N'DV007', 7)
INSERT INTO CTDVPhong values(N'PDP008', N'DV008', 6)
INSERT INTO CTDVPhong values(N'PDP009', N'DV009', 9)
INSERT INTO CTDVPhong values(N'PDP010', N'DV010', 15)
INSERT INTO CTDVPhong values(N'PDP011', N'DV011', 8)
INSERT INTO CTDVPhong values(N'PDP012', N'DV012', 12)
INSERT INTO CTDVPhong values(N'PDP013', N'DV013', 20)
INSERT INTO CTDVPhong values(N'PDP014', N'DV014', 9)
INSERT INTO CTDVPhong values(N'PDP015', N'DV015', 14)
INSERT INTO CTDVPhong values(N'PDP016', N'DV016', 5)
INSERT INTO CTDVPhong values(N'PDP017', N'DV017', 19)
INSERT INTO CTDVPhong values(N'PDP018', N'DV018', 7)
INSERT INTO CTDVPhong values(N'PDP019', N'DV019', 16)
INSERT INTO CTDVPhong values(N'PDP020', N'DV020', 20)
--Xem toan bo CTDVPhong
select *from CTDVPhong


--------------------------------HOA DON-----------------------------------------------
--INSERT INTO HoaDon(MaHD, MaPDP, GioThanhToan, NgayThanhToan, TienPhong, TienDichVu, TienGiam, TongTien)
INSERT INTO HoaDon values(N'HDOO1', N'PDP001', '13:34:26', '2022/11/21', 125000, 400000, 50000, 475000)
INSERT INTO HoaDon values(N'HDOO2', N'PDP002', '16:30:50', '2022/03/10', 225000, 650000, 100000, 775000)
INSERT INTO HoaDon values(N'HDOO3', N'PDP003', '10:15:30', '2022/09/24', 455000, 1050000, 150000, 1355000)
INSERT INTO HoaDon values(N'HDOO4', N'PDP004', '11:35:45', '2022/06/05', 175000, 1500000, 150000, 1525000)
INSERT INTO HoaDon values(N'HDOO5', N'PDP005', '12:01:59', '2022/10/13', 400000, 830000, 100000, 1130000)
INSERT INTO HoaDon values(N'HDOO6', N'PDP006', '09:23:31', '2022/05/29', 525000, 500000, 50000, 975000)
INSERT INTO HoaDon values(N'HDOO7', N'PDP007', '15:43:27', '2022/07/17', 175000, 765000, 100000, 840000)
INSERT INTO HoaDon values(N'HDOO8', N'PDP008', '20:27:46', '2022/10/30', 525000, 450000, 50000, 925000)
INSERT INTO HoaDon values(N'HDOO9', N'PDP009', '22:05:48', '2022/03/15', 125000, 600000, 75000, 650000)
INSERT INTO HoaDon values(N'HDO10', N'PDP010', '17:38:46', '2022/08/25', 225000, 575000, 75000, 725000)
INSERT INTO HoaDon values(N'HDO11', N'PDP011', '14:55:21', '2023/06/24', 225000, 1200000, 150000, 1275000)
INSERT INTO HoaDon values(N'HDO12', N'PDP012', '18:25:47', '2023/05/02', 400000, 675000, 75000, 1000000)
INSERT INTO HoaDon values(N'HDO13', N'PDP013', '21:42:58', '2023/01/15', 455000, 500000, 50000, 905000)
INSERT INTO HoaDon values(N'HDO14', N'PDP014', '11:50:11', '2023/08/29', 175000, 480000, 50000, 605000)
INSERT INTO HoaDon values(N'HDO15', N'PDP015', '12:35:47', '2023/10/02', 125000, 635000, 75000, 685000)
INSERT INTO HoaDon values(N'HDO16', N'PDP016', '19:27:06', '2023/04/13', 525000, 1400000, 150000, 1775000)
INSERT INTO HoaDon values(N'HDO17', N'PDP017', '13:45:29', '2023/07/18', 225000, 865000, 100000, 990000)
INSERT INTO HoaDon values(N'HDO18', N'PDP018', '23:02:33', '2023/03/17', 400000, 750000, 100000, 1050000)
INSERT INTO HoaDon values(N'HDO19', N'PDP019', '15:46:13', '2023/05/25', 455000, 1500000, 150000, 1805000)
INSERT INTO HoaDon values(N'HDO20', N'PDP020', '10:55:04', '2023/09/07', 125000, 450000, 50000, 525000)
--Xem toan bo HoaDon
select *from HoaDon



----------------------------LOAI PHONG---------------------------------------------------
--INSERT INTO LoaiPhong(MaLP, TenLP, SucChua, GiaPhong)
INSERT INTO LoaiPhong values(N'LP001', N'Phòng VIP', 15, 425000)
INSERT INTO LoaiPhong values(N'LP002', N'Phòng thường', 10, 125000)
INSERT INTO LoaiPhong values(N'LP003', N'Phòng VIP', 20, 455000)
INSERT INTO LoaiPhong values(N'LP004', N'Phòng thường', 15, 155000)
INSERT INTO LoaiPhong values(N'LP005', N'Phòng VIP', 15, 425000)
INSERT INTO LoaiPhong values(N'LP006', N'Phòng thường', 15, 155000)
INSERT INTO LoaiPhong values(N'LP007', N'Phòng thường', 10, 125000)
INSERT INTO LoaiPhong values(N'LP008', N'Phòng VIP', 10, 400000)
INSERT INTO LoaiPhong values(N'LP009', N'Phòng VIP', 10, 400000)
INSERT INTO LoaiPhong values(N'LP010', N'Phòng thường', 20, 175000)
INSERT INTO LoaiPhong values(N'LP011', N'Phòng VIP', 15, 425000)
INSERT INTO LoaiPhong values(N'LP012', N'Phòng thường', 10, 125000)
INSERT INTO LoaiPhong values(N'LP013', N'Phòng VIP', 10, 400000)
INSERT INTO LoaiPhong values(N'LP014', N'Phòng VIP', 20, 455000)
INSERT INTO LoaiPhong values(N'LP015', N'Phòng thường', 15, 155000)
INSERT INTO LoaiPhong values(N'LP016', N'Phòng thường', 10, 155000)
INSERT INTO LoaiPhong values(N'LP017', N'Phòng VIP', 10, 400000)
INSERT INTO LoaiPhong values(N'LP018', N'Phòng VIP', 20, 455000)
INSERT INTO LoaiPhong values(N'LP019', N'Phòng VIP', 15, 425000)
INSERT INTO LoaiPhong values(N'LP020', N'Phòng thường', 20, 175000)
--Xem toan bo LoaiPhong
select *from LoaiPhong


----------------------------KHUYEN MAI---------------------------------------------------
--INSERT INTO KhuyenMai(MaKhuyenMai, PhanTramGiam, NgayBatDau, NgayKetThuc, MoTa, TrangThaiKM)
INSERT INTO KhuyenMai values(N'KM001', 20, '2022/06/20', '2022/06/26', '', 1)
INSERT INTO KhuyenMai values(N'KM002', 10, '2022/03/15', '2022/03/18', '', 1)
INSERT INTO KhuyenMai values(N'KM003', 15, '2022/10/15', '2022/10/20', '', 1)
INSERT INTO KhuyenMai values(N'KM004', 10, '2022/07/04', '2022/07/06', '', 0)
INSERT INTO KhuyenMai values(N'KM005', 20, '2022/09/18', '2022/09/22', '', 1)
INSERT INTO KhuyenMai values(N'KM006', 5, '2022/11/13', '2022/11/17', '', 0)
INSERT INTO KhuyenMai values(N'KM007', 15, '2022/05/22', '2022/05/25', '', 1)
INSERT INTO KhuyenMai values(N'KM008', 20, '2022/12/07', '2022/12/12', '', 1)
INSERT INTO KhuyenMai values(N'KM009', 10, '2022/04/06', '2022/04/10', '', 0)
INSERT INTO KhuyenMai values(N'KM010', 5, '2022/08/24', '2022/08/28', '', 0)
INSERT INTO KhuyenMai values(N'KM011', 15, '2023/04/05', '2023/04/09', '', 1)
INSERT INTO KhuyenMai values(N'KM012', 10, '2023/07/16', '2023/07/20', '', 1)
INSERT INTO KhuyenMai values(N'KM013', 20, '2023/10/01', '2023/10/04', '', 0)
INSERT INTO KhuyenMai values(N'KM014', 20, '2023/09/23', '2023/09/27', '', 1)
INSERT INTO KhuyenMai values(N'KM015', 15, '2023/05/12', '2023/05/26', '', 1)
INSERT INTO KhuyenMai values(N'KM016', 5, '2023/06/24', '2023/06/27', '', 1)
INSERT INTO KhuyenMai values(N'KM017', 10, '2023/03/07', '2023/03/11', '', 0)
INSERT INTO KhuyenMai values(N'KM018', 15, '2023/08/18', '2023/08/25', '', 1)
INSERT INTO KhuyenMai values(N'KM019', 5, '2023/01/15', '2023/01/19', '', 1)
INSERT INTO KhuyenMai values(N'KM020', 20, '2023/02/09', '2023/02/13', '', 0)
--Xem toan bo KhuyenMai
select *from KhuyenMai



----------------------------PHONG---------------------------------------------------
--INSERT INTO Phong(MaPhong, TenPhong, MaKM, MaLP, TinhTrang)
INSERT INTO Phong values(N'P001', N'VIP301', N'KM001', N'LP001', 1)
INSERT INTO Phong values(N'P002', N'302', N'KM002', N'LP002', 0)
INSERT INTO Phong values(N'P003', N'VIP101', N'KM003', N'LP003', 0)
INSERT INTO Phong values(N'P004', N'VIP102', N'KM004', N'LP004', 1)
INSERT INTO Phong values(N'P005', N'201', N'KM005', N'LP005', 2)
INSERT INTO Phong values(N'P006', N'103', N'KM006', N'LP006', 2)
INSERT INTO Phong values(N'P007', N'VIP103', N'KM007', N'LP007', 3)
INSERT INTO Phong values(N'P008', N'404', N'KM008', N'LP008', 0)
INSERT INTO Phong values(N'P009', N'VIP104', N'KM009', N'LP009', 1)
INSERT INTO Phong values(N'P010', N'106', N'KM010', N'LP010', 0)
INSERT INTO Phong values(N'P011', N'202', N'KM011', N'LP011', 2)
INSERT INTO Phong values(N'P012', N'VIP303', N'KM012', N'LP012', 2)
INSERT INTO Phong values(N'P013', N'VIP107', N'KM013', N'LP013', 3)
INSERT INTO Phong values(N'P014', N'305', N'KM014', N'LP014', 1)
INSERT INTO Phong values(N'P015', N'206', N'KM015', N'LP015', 1)
INSERT INTO Phong values(N'P016', N'VIP204', N'KM016', N'LP016', 3)
INSERT INTO Phong values(N'P017', N'VIP302', N'KM017', N'LP017', 2)
INSERT INTO Phong values(N'P018', N'207', N'KM018', N'LP018', 0)
INSERT INTO Phong values(N'P019', N'304', N'KM019', N'LP019', 1)
INSERT INTO Phong values(N'P020', N'VIP207', N'KM020', N'LP020', 0)
--Xem toan bo Phong
select *from Phong


----------------------------CT PHONG---------------------------------------------------
--INSERT INTO CTPhong(MaPhong, MaPDP, NgayNhanPhong, GioNhanPhong, GioTraPhong)
INSERT INTO CTPhong values(N'P001', N'PDP001', '2022/06/23', '8:30:15', '11:31:24')
INSERT INTO CTPhong values(N'P002', N'PDP002', '2022/01/15', '14:25:32', '19:30:31')
INSERT INTO CTPhong values(N'P003', N'PDP003', '2022/09/04', '10:15:40', '13:20:15')
INSERT INTO CTPhong values(N'P004', N'PDP004', '2022/04/18', '17:20:23', '21:15:35')
INSERT INTO CTPhong values(N'P005', N'PDP005', '2022/05/25', '9:10:37', '12:15:38')
INSERT INTO CTPhong values(N'P006', N'PDP006', '2022/10/30', '18:45:28', '22:50:37')
INSERT INTO CTPhong values(N'P007', N'PDP007', '2022/07/11', '11:05:20', '17:10:55')
INSERT INTO CTPhong values(N'P008', N'PDP008', '2022/12/07', '20:36:34', '23:45:10')
INSERT INTO CTPhong values(N'P009', N'PDP009', '2022/08/13', '13:31:12', '16:35:21')
INSERT INTO CTPhong values(N'P010', N'PDP010', '2022/11/05', '15:48:34', '19:55:40')
INSERT INTO CTPhong values(N'P011', N'PDP011', '2023/05/24', '9:20:30', '13:28:48')
INSERT INTO CTPhong values(N'P012', N'PDP012', '2023/04/12', '19:23:45', '22:30:50')
INSERT INTO CTPhong values(N'P013', N'PDP013', '2023/10/01', '10:15:21', '15:21:23')
INSERT INTO CTPhong values(N'P014', N'PDP014', '2023/09/17', '13:40:38', '17:50:14')
INSERT INTO CTPhong values(N'P015', N'PDP015', '2023/06/28', '17:35:27', '21:40:35')
INSERT INTO CTPhong values(N'P016', N'PDP016', '2023/03/05', '15:14:45', '19:23:57')
INSERT INTO CTPhong values(N'P017', N'PDP017', '2023/05/09', '18:40:17', '22:35:41')
INSERT INTO CTPhong values(N'P018', N'PDP018', '2023/07/16', '11:28:29', '15:36:10')
INSERT INTO CTPhong values(N'P019', N'PDP019', '2023/08/10', '14:10:16', '18:22:26')
INSERT INTO CTPhong values(N'P020', N'PDP020', '2023/06/26', '19:28:55', '23:05:09')
--Xem toan bo CTPhong
select *from CTPhong

