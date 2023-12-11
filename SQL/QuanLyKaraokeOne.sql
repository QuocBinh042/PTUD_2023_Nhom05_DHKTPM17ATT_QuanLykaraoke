--Tạo cơ sở dữ liệu
go
drop database QuanLyKaraokeOne
go
create database QuanLyKaraokeOne
go
use QuanLyKaraokeOne

--------------Create Table
go
CREATE TABLE LoaiPhong (
    MaLP nvarchar(20) not null primary key,
    TenLP nvarchar(50) not null,
    SucChua int CHECK (SucChua > 0),
    GiaPhong float CHECK (GiaPhong > 0)
)
go
create table Phong(
	MaPhong nvarchar(20) not null primary key,
	TenPhong nvarchar(50) not null,
	MaLP nvarchar(20) not null,
	--TinhTrangPhong(con trong,dang thue,da dat truoc,da xoa)
	TinhTrangPhong nvarchar(20) not null,
	MoTa nvarchar(100),
	CONSTRAINT fk_lp FOREIGN KEY (MaLP) REFERENCES LoaiPhong(MaLP)
)
create table DichVu(
	MaDV nvarchar(20) not null primary key,
	TenDV nvarchar(50) not null,
	DonGia float not null CHECK (DonGia>0),
	DonVi nvarchar(20),
	SoLuongTonKho int,
	--TinhTrangDV (Co 4 loai: Con hang(SL>10), sap het hang(10>SL>0), het hang(SL=0), da xoa(SL=0))
	TinhTrangDV nvarchar(20) not null
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
	NamSinh date CHECK (NamSinh < GETDATE()),
	--GioiTinh (0: nam, 1: nu)
	GioiTinh bit,
	SoDienThoai nvarchar(10) not null,
	CCCD nvarchar(12) not null,
	--ChucVu (nhan vien quan ly, le tan,phuc vu)
	ChucVu nvarchar(20)not null,
	MatKhau nvarchar(30) not null,
	--TinhTrangNV(0: nghi viec, 1: dang lam)
	TinhTrangNV bit
)
go
create table KhuyenMai(
	MaKM nvarchar(20) not null primary key,
	PhanTramGiam float CHECK (PhanTramGiam >= 0),
	NgayBatDau date,
	NgayKetThuc date,
	MoTa nvarchar(50),
	--TrangThaiKM(0: ket thuc, 1: dang hoat dong)
	TrangThaiKM bit,
	CONSTRAINT CK_NgayKetThuc CHECK (NgayKetThuc > NgayBatDau)
)
go
create table PhieuDatPhong(
	MaPDP nvarchar(20) not null primary key,	
	MaNV nvarchar(20) not null,
	-----FOREIGN KEY MaNhanVien	
	MaKH nvarchar(20) not null,
	-----FOREIGN KEY MaKhachHang
	MaPhong nvarchar(20) not null,
	-----FOREIGN KEY MaPhong
	ThoiGianDatPhong datetime,
	ThoiGianNhanPhong datetime,
	SoLuongKhach int CHECK (SoLuongKhach>0),
	--TinhTrangPDP(0: chua thanh toan, 1: da thanh toan)
	TinhTrangPDP bit, 
	GhiChu nvarchar(50),
	CONSTRAINT fk_phong FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong),
	CONSTRAINT fk_kh FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
	CONSTRAINT fk_nv FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
)
go

create table HoaDon(
	MaHD nvarchar(20) not null primary key,
	-------FOREIGN KEY PhieuDatPhong
	GioThanhToan time,
	NgayThanhToan date,
	MaNV nvarchar(20) not null,
	MaKH nvarchar(20) not null,
	MaKM nvarchar(20) not null,
	TongHoaDon float
	CONSTRAINT fk_n_vien FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
	CONSTRAINT fk_k_hang FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
	CONSTRAINT fk_maKM FOREIGN KEY (MaKM) REFERENCES KhuyenMai(MaKM)
)
go
create table CTDVPhong(
	-------FOREIGN KEY PhieuDatPhong
	MaHD nvarchar(20) not null,
	-------FOREIGN KEY DichVu
	MaDV nvarchar(20) not null,
	SoLuong int CHECK (SoLuong>0),
	CONSTRAINT fk_hd FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
	CONSTRAINT fk_dv FOREIGN KEY (MaDV) REFERENCES DichVu(MaDV),
	PRIMARY KEY (MaHD,MaDV)
)
go
create table ChiTietHoaDon(
	-------FOREIGN KEY Phong
	MaPhong nvarchar(20) not null,
	-------FOREIGN KEY HoaDon
	MaHD nvarchar(20) not null,
	ThoiGianNhanPhong datetime,
	ThoiGianTraPhong datetime,
	CONSTRAINT fk_maP FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong),
	CONSTRAINT fk_mahd FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
	--CONSTRAINT CK_ThoiGianTraPhong CHECK (ThoiGianTraPhong > ThoiGianNhanPhong),
	PRIMARY KEY (MaPhong,MaHD)
)
go

-----------Insert data

----------------------------LOAI PHONG---------------------------------------------------
--INSERT INTO LoaiPhong(MaLP, TenLP, SucChua, GiaPhong)
INSERT INTO LoaiPhong values(N'LP0001', N'VIP', 10, 400000)
INSERT INTO LoaiPhong values(N'LP0002', N'Thường', 10, 125000)
INSERT INTO LoaiPhong values(N'LP0003', N'VIP', 15, 500000)
INSERT INTO LoaiPhong values(N'LP0004', N'Thường', 15, 175000)
INSERT INTO LoaiPhong values(N'LP0005', N'VIP', 20, 650000)
INSERT INTO LoaiPhong values(N'LP0006', N'Thường', 20, 250000)
--Xem toan bo LoaiPhong
select *from LoaiPhong


----------------------------PHONG---------------------------------------------------
--INSERT INTO Phong(MaPhong, TenPhong, MaLP, TinhTrangPhong, MoTa)
--TinhTrangPhong(0: con trong, 1: dang thue, 2: da dat truoc, 3: da xoa)
INSERT INTO Phong values(N'P0001', N'101', N'LP0002', N'Đã đặt trước',N'')
INSERT INTO Phong values(N'P0002', N'102', N'LP0004', N'Đã đặt trước',N'')
INSERT INTO Phong values(N'P0003', N'103', N'LP0006', N'Còn trống',N'')
INSERT INTO Phong values(N'P0004', N'104', N'LP0004', N'Đã xóa',N'')
INSERT INTO Phong values(N'P0005', N'105', N'LP0006', N'Còn trống',N'')
INSERT INTO Phong values(N'P0006', N'106', N'LP0006', N'Còn trống',N'')
INSERT INTO Phong values(N'P0007', N'107', N'LP0001', N'Đang thuê',N'')
INSERT INTO Phong values(N'P0008', N'108', N'LP0003', N'Đang thuê',N'')
INSERT INTO Phong values(N'P0009', N'201', N'LP0002', N'Đã xóa',N'')
INSERT INTO Phong values(N'P0010', N'202', N'LP0004', N'Đang thuê',N'')
INSERT INTO Phong values(N'P0011', N'203', N'LP0002', N'Đã đặt trước',N'')
INSERT INTO Phong values(N'P0012', N'204', N'LP0006', N'Đã đặt trước',N'')
INSERT INTO Phong values(N'P0013', N'205', N'LP0002', N'Đã xóa',N'')
INSERT INTO Phong values(N'P0014', N'206', N'LP0002', N'Đang thuê',N'')
INSERT INTO Phong values(N'P0015', N'207', N'LP0003', N'Đang thuê',N'')
INSERT INTO Phong values(N'P0016', N'208', N'LP0005', N'Đã xóa',N'')
INSERT INTO Phong values(N'P0017', N'301', N'LP0004', N'Đã đặt trước',N'')
INSERT INTO Phong values(N'P0018', N'302', N'LP0002', N'Còn trống',N'')
INSERT INTO Phong values(N'P0019', N'303', N'LP0003', N'Đang thuê',N'')
INSERT INTO Phong values(N'P0020', N'304', N'LP0001', N'Còn trống',N'')
INSERT INTO Phong values(N'P0021', N'305', N'LP0001', N'Đã đặt trước',N'')
INSERT INTO Phong values(N'P0022', N'306', N'LP0005', N'Còn trống',N'')
INSERT INTO Phong values(N'P0023', N'307', N'LP0001', N'Đang thuê',N'')
INSERT INTO Phong values(N'P0024', N'308', N'LP0005', N'Còn trống',N'')
--Xem toan bo Phong
select *from Phong
---
select p.MaPhong, TenPhong, lp.MaLP, lp.TenLP, lp.SucChua, lp.GiaPhong, p.TinhTrangPhong,p.MoTa from Phong p
inner join LoaiPhong lp on p.MaLP = lp.MaLP

--------------------------------DICH VU-----------------------------------------------
--INSERT INTO DichVu(MaDV, TenDV, DonGia, DonVi, SoLuongTonKho, TinhTrangDV) 
--TinhTrangDV (Co 4 loai: Con hang(SL>10), sap het hang(10>SL>0), het hang(SL=0), da xoa(SL=0))
INSERT INTO DichVu values(N'DV0001', N'Khăn giấy', 5000, N'Bịch', 200, N'Còn hàng')
INSERT INTO DichVu values(N'DV0002', N'Bia Heineken 330ml', 26000, N'Lon', 100, N'Còn hàng')
INSERT INTO DichVu values(N'DV0003', N'Coca cola', 15000, N'Lon', 7, N'Sắp hết hàng')
INSERT INTO DichVu values(N'DV0004', N'Trái cây dĩa',  70000, N'Dĩa', 0, N'Hết hàng')
INSERT INTO DichVu values(N'DV0005', N'Khăn giấy ướt', 8000, N'Bịch', 100, N'Còn hàng')
INSERT INTO DichVu values(N'DV0006', N'Cocktail',  50000, N'Lon', 50, N'Còn hàng')
INSERT INTO DichVu values(N'DV0007', N'Cà phê sữa đá',  18000, N'Ly', 70, N'Còn hàng')
INSERT INTO DichVu values(N'DV0008', N'Đậu phộng',  20000, N'Bịch', 5, N'Sắp hết hàng')
INSERT INTO DichVu values(N'DV0009', N'Bia Tiger 500ml ', 35000, N'Lon', 100, N'Còn hàng')
INSERT INTO DichVu values(N'DV0010', N'Cơm chiên hải sản',  40000, N'Dĩa', 3, N'Sắp hết hàng')
INSERT INTO DichVu values(N'DV0011', N'Sụn gà rang muối',  40000, N'Dĩa', 0, N'Đã xóa')
INSERT INTO DichVu values(N'DV0012', N'Nước suối Aquafina 500ml',  7000, N'Chai', 150, N'Còn hàng')
INSERT INTO DichVu values(N'DV0013', N'Mì xào hải sản',  40000, N'Dĩa', 8, N'Sắp hết hàng')
INSERT INTO DichVu values(N'DV0014', N'Thùng 24 lon bia Hà Nội 330ml',  315000, N'Thùng', 30, N'Còn hàng')
INSERT INTO DichVu values(N'DV0015', N'Đậu hũ xóc tỏi ớt',  35000, N'Dĩa', 3, N'Sắp hết hàng')
INSERT INTO DichVu values(N'DV0016', N'Bia Sài Gòn Chill 330ml dạng lốc 6 lon',  130000, N'Lốc', 0, N'Hết hàng')
INSERT INTO DichVu values(N'DV0017', N'Xúc xích nướng tiêu',  45000, N'Dĩa', 100, N'Còn hàng')
INSERT INTO DichVu values(N'DV0018', N'Bia Huda 330ml',  15000, N'Lon', 0, N'Đã xóa')
INSERT INTO DichVu values(N'DV0019', N'Chả giò',  40000, N'Dĩa', 20, N'Còn hàng')
INSERT INTO DichVu values(N'DV0020', N'6 lon bia Heineken Silver 330ml', 150000, N'Lốc', 40, N'Còn hàng')

--------------------------------NHAN VIEN-----------------------------------------------
--INSERT INTO NhanVien(MaNV, TenNV, NamSinh, GioiTinh, SoDienThoai, CCCD, ChucVu, MatKhau, TinhTrangNV)
update NhanVien set SoDienThoai = N'0386076296' where MaNV = N'NV0001'
--ChucVu (0: nhan vien quan ly, 1: le tan, 2: phuc vu)
INSERT INTO NhanVien values(N'NV0001', N'Nguyễn Thị Yến Nhi', '2003/10/05', 1, N'0386076296', N'082594657912', N'Lễ tân', N'123456789', 1)
INSERT INTO NhanVien values(N'NV0002', N'Nguyễn Trung Kiên', '1999/07/21', 0, N'0205578931', N'064769123700', N'Nhân viên quản lý', N'123456789', 1)
INSERT INTO NhanVien values(N'NV0003', N'Vũ Đức Thắng', '2004/02/15', 0, N'0702447601', N'076591236064', N'Nhân viên quản lý', N'thang54t', 1)
INSERT INTO NhanVien values(N'NV0004', N'Lâm Thúy Hiền', '2002/05/18', 1, N'0452603567', N'084769431052', N'Phục vụ', N'hienthuy59r', 0)
INSERT INTO NhanVien values(N'NV0005', N'Nguyễn Văn Thanh', '2001/11/05', 0, N'0160567832', N'073976012433', N'Phục vụ', N'vanthanh10', 1)
INSERT INTO NhanVien values(N'NV0006', N'Nguyễn Hữu Nhật', '2000/04/23', 0, N'0870553419', N'087923408723', N'Lễ tân', N'nhathuu@07', 0)
INSERT INTO NhanVien values(N'NV0007', N'Nguyễn Đỗ Gia Hân', '1998/06/01', 1, N'0573960875', N'062769236591', N'Phục vụ', N'giahan1314', 1)
INSERT INTO NhanVien values(N'NV0008', N'Nguyễn Ngọc Anh Thư', '2001/04/29', 1, N'0952566034', N'073658012563', N'Nhân viên quản lý', N'anhthu542', 1)
INSERT INTO NhanVien values(N'NV0009', N'Trần Anh Tuấn', '2003/09/25', 0, N'0651273390', N'088425070237', N'Phục vụ', N'tuananh76@a', 0)
INSERT INTO NhanVien values(N'NV0010', N'Nguyễn Văn Phương', '2000/12/07', 0, N'0305671489', N'084072359038', N'Phục vụ', N'baoky3415', 0)
INSERT INTO NhanVien values(N'NV0011', N'Trần Hoàng Nghĩa', '2001/05/22', 0, N'0582632176', N'076548160533', N'Lễ tân', N'hoangnghia25#', 1)
INSERT INTO NhanVien values(N'NV0012', N'Nguyễn Ngọc Phương Anh', '2000/01/15', 1, N'0154787932', N'072976002137', N'Phục vụ', N'panhhh5932', 0)
INSERT INTO NhanVien values(N'NV0013', N'Trần Thị Thảo Trang', '2002/10/28', 1, N'0748500751', N'086942660432', N'Phục vụ', N'trangtr298', 0)
INSERT INTO NhanVien values(N'NV0014', N'Nguyễn Cao Minh Khôi', '2002/03/09', 0, N'0952633601', N'069432866015', N'Phục vụ', N'minhkhoi3@#', 1)
INSERT INTO NhanVien values(N'NV0015', N'Hà Bảo Ngọc', '2003/09/21', 1, N'0367927602', N'083602658934', N'Phục vụ', N'ngocbao639', 1)
INSERT INTO NhanVien values(N'NV0016', N'Nguyễn Minh Nhựt', '2000/11/06', 0, N'0638477036', N'073965234671', N'Phục vụ', N'nhutminh532', 0)
INSERT INTO NhanVien values(N'NV0017', N'Phạm Quế Trân', '2004/03/25', 1, N'0838614401', N'063634789932', N'Phục vụ', N'quetran406#', 1)
INSERT INTO NhanVien values(N'NV0018', N'Châu Văn Tấn', '1999/07/18', 0, N'0269743064', N'083852704766', N'Phục vụ', N'tantan396@', 0)
INSERT INTO NhanVien values(N'NV0019', N'Huỳnh Chí Phong', '2002/06/10', 0, N'0478280031', N'071603449254', N'Phục vụ', N'chiphongeu4', 0)
INSERT INTO NhanVien values(N'NV0020', N'Trần Thị Quỳnh Như', '1999/04/17', 1, N'0386370342', N'084598701225', N'Phục vụ', N'nhuquynhh45', 1)
--Xem toan bo NhanVien
select *from NhanVien

--------------------------------KHACH HANG-----------------------------------------------
--INSERT INTO KhachHang(MaKH, TenKH, LoaiKH, GioiTinh, SoDienThoai, Email, SoGioDaThue, GhiChu)
INSERT INTO KhachHang values(N'KH0001', N'Nguyễn Bùi Phát Đạt', 1, 0, '0649248221', N'phatdat123@gmail.com', 50, '')
INSERT INTO KhachHang values(N'KH0002', N'Lê Thị Thảo Vy', 1, 1, '0492568801', N'vyvy444@gmail.com', 50, '')
INSERT INTO KhachHang values(N'KH0003', N'Trần Mạnh Công', 0, 0, '0581943912', N'congmanh45@gmail.com', 19, '')
INSERT INTO KhachHang values(N'KH0004', N'Dương Đình Toàn', 0, 0, '0194369932', N'dinhtoan17@gmail.com', 12, '')
INSERT INTO KhachHang values(N'KH0005', N'Nguyễn Thu Sương', 0, 1, '0395354914', N'suongthu00@gmail.com', 23, '')
INSERT INTO KhachHang values(N'KH0006', N'Nguyễn Thị Anh Thư', 1, 1, '0825164023', N'thuthu032@gmail.com', 54, '')
INSERT INTO KhachHang values(N'KH0007', N'Nguyễn Toàn Vương', 0, 0, '0292165032', N'manhvuong@gmail.com', 25, '')
INSERT INTO KhachHang values(N'KH0008', N'Tống Nguyên Kiệt', 0, 0, '0938654812', N'kiettongoo@gmail.com', 41, '')
INSERT INTO KhachHang values(N'KH0009', N'Vũ Anh Thư', 0, 1, '0159264902', N'anhthu201@gmail.com', 10, '')
INSERT INTO KhachHang values(N'KH0010', N'Nguyễn Thanh Thanh', 1, 0, '0730118701', N'thanhthanh@gmail.com', 60, '')
INSERT INTO KhachHang values(N'KH0011', N'Nguyễn Thị Bích Loan', 1, 1, '0193276520', N'bichloan3@gmail.com', 50, '')
INSERT INTO KhachHang values(N'KH0012', N'Vũ Minh Huy', 0, 0, '0569370341', N'huyhuy3498@gmail.com', 3, '')
INSERT INTO KhachHang values(N'KH0013', N'Huỳnh Thanh Lộc', 0, 0, '0340554068', N'locthanh37@gmail.com', 11, '')
INSERT INTO KhachHang values(N'KH0014', N'Lê Thị Yến Nhi', 0, 1, '0297450377', N'yennhi12@gmail.com', 41, '')
INSERT INTO KhachHang values(N'KH0015', N'Nguyễn Thành Luân', 0, 0, '0409339601', N'luann65@gmail.com', 16, '')
INSERT INTO KhachHang values(N'KH0016', N'Nguyễn Phương Khánh', 0, 0, '0692270465', N'phuongk333@gmail.com', 12, '')
INSERT INTO KhachHang values(N'KH0017', N'Lê Phạm Công Khanh', 0, 0, '0592669130', N'khanhktt@gmail.com', 13, '')
INSERT INTO KhachHang values(N'KH0018', N'Nguyễn Minh Nguyệt', 0, 1, '0856976509', N'minhn583@gmail.com', 15, '')
INSERT INTO KhachHang values(N'KH0019', N'Nguyễn Ngọc Kim Liên', 0, 1, '0927550871', N'kimlien91@gmail.com', 14, '')
INSERT INTO KhachHang values(N'KH0020', N'Đặng Hoàng Phúc', 0, 0, '0749205481', N'phucphuc32@gmail.com', 12, '')
--Xem toan bo KhachHang
select *from KhachHang

----------------------------KHUYEN MAI---------------------------------------------------
--INSERT INTO KhuyenMai(MaKhuyenMai, PhanTramGiam, NgayBatDau, NgayKetThuc, MoTa, TrangThaiKM)
INSERT INTO KhuyenMai values(N'KM0001', 20, '2023/02/20', '2023/02/25', '', 0)
INSERT INTO KhuyenMai values(N'KM0002', 10, '2023/03/15', '2023/03/19', '', 0)
INSERT INTO KhuyenMai values(N'KM0003', 15, '2023/04/15', '2023/04/20', '', 0)
INSERT INTO KhuyenMai values(N'KM0004', 10, '2023/05/04', '2023/05/07', '', 0)
INSERT INTO KhuyenMai values(N'KM0005', 20, '2023/06/18', '2023/06/22', '', 0)
INSERT INTO KhuyenMai values(N'KM0006', 5, '2023/07/13', '2023/07/17', '', 0)
INSERT INTO KhuyenMai values(N'KM0007', 15, '2023/08/22', '2023/08/26', '', 0)
INSERT INTO KhuyenMai values(N'KM0008', 20, '2023/09/07', '2023/09/12', '', 0)
INSERT INTO KhuyenMai values(N'KM0009', 10, '2023/10/06', '2023/10/10', '', 0)
INSERT INTO KhuyenMai values(N'KM0010', 5, '2023/11/21', '2023/11/30', '', 1)
--Xem toan bo KhuyenMai
select *from KhuyenMai


----------------------------------PHIEU DAT PHONG-----------------------------------------------
----INSERT INTO PhieuDatPhong(MaPDP, MaNV, MaKH, MaPhong,ThoiGianDatPhong, ThoiGianNhanPhong, SoLuongKhach, TinhTrangPDP, MoTa)
--INSERT INTO PhieuDatPhong values(N'PDP0001', N'NV0001', N'KH0001', N'P0001' ,'2022/11/21 9:25:43', '2022/11/21 9:30:43', 4, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0002', N'NV0001', N'KH0002', N'P0023' ,'2022/03/10 8:45:12', '2022/03/10 8:50:15', 3, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0003', N'NV0003', N'KH0003', N'P0011' ,'2022/09/24 13:10:06', '2022/09/24 14:05:25', 2, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0004', N'NV0001', N'KH0004', N'P0013' ,'2022/06/05 17:05:25', '2022/06/05 17:10:30', 7, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0005', N'NV0003', N'KH0005', N'P0014' ,'2022/10/13 15:27:18', '2022/10/13 15:35:20', 6, 0, '')

--INSERT INTO PhieuDatPhong values(N'PDP0006', N'NV0003', N'KH0006', N'P0003' ,'2022/05/29 11:30:15', '2022/05/29 11:35:20', 10, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0007', N'NV0006', N'KH0007', N'P0004' ,'2022/07/17 19:14:27', '2022/07/17 19:20:36', 11, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0008', N'NV0008', N'KH0008', N'P0003' ,'2022/10/30 10:40:10', '2022/10/30 10:45:20', 15, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0009', N'NV0008', N'KH0009', N'P0004' ,'2022/03/15 12:05:50', '2022/03/15 12:10:15', 15, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0010', N'NV0001', N'KH0010', N'P0004' ,'2022/08/25 20:35:38', '2022/08/25 20:40:03', 13, 0, '')

--INSERT INTO PhieuDatPhong values(N'PDP0011', N'NV0001', N'KH0011', N'P0005' ,'2023/06/24 14:23:39', '2023/06/24 15:00:23', 16, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0012', N'NV0001', N'KH0012', N'P0005' ,'2023/05/02 16:36:55', '2023/05/02 16:40:10', 17, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0013', N'NV0003', N'KH0013', N'P0022','2023/01/15 15:40:05', '2023/01/15 15:50:10', 17, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0014', N'NV0003', N'KH0014', N'P0024' ,'2023/08/29 10:01:20', '2023/08/29 10:06:35', 19, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0015', N'NV0003', N'KH0015', N'P0024' ,'2023/10/02 18:20:35', '2023/10/02 18:25:40', 20, 0, '')

--INSERT INTO PhieuDatPhong values(N'PDP0016', N'NV0005', N'KH0016', N'P0014' ,'2023/04/13 10:14:20', '2023/04/13 10:20:34', 2, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0017', N'NV0011', N'KH0017', N'P0004' ,'2023/07/18 19:50:10', '2023/07/18 20:00:32', 11, 0, '')
--INSERT INTO PhieuDatPhong values(N'PDP0018', N'NV0001', N'KH0018', N'P0011' ,'2023/11/12 14:21:37', '2023/11/13 10:25:45', 5, 1, '')
--INSERT INTO PhieuDatPhong values(N'PDP0019', N'NV0011', N'KH0019', N'P0005' ,'2023/11/12 9:50:45', '2023/11/13 14:35:50', 20, 1, '')
--INSERT INTO PhieuDatPhong values(N'PDP0020', N'NV0011', N'KH0020', N'P0024' ,'2023/11/13 9:30:03', '2023/11/13 12:30:07', 15, 1, '')
----Xem toan bo PhieuDatPhong
--select *from PhieuDatPhong

----------------------------------HOA DON-----------------------------------------------
----INSERT INTO HoaDon(MaHD, GioThanhToan, NgayThanhToan, MaNV, MaKH, MaKM, TongHoaDon, TinhTrang)
--INSERT INTO HoaDon values(N'HD0001', '13:34:26', '2022/11/21', N'NV0001', N'KH0001', N'KM0001', 475000)
--INSERT INTO HoaDon values(N'HD0002', '16:30:50', '2022/03/10', N'NV0002', N'KH0002', N'KM0002', 775000)
--INSERT INTO HoaDon values(N'HD0003', '10:15:30', '2022/09/24', N'NV0003', N'KH0003', N'KM0002', 1355000)
--INSERT INTO HoaDon values(N'HD0004', '11:35:45', '2022/06/05', N'NV0004', N'KH0004', N'KM0003', 1525000)
--INSERT INTO HoaDon values(N'HD0005', '12:01:59', '2022/10/13', N'NV0005', N'KH0005', N'KM0004', 1130000)
--INSERT INTO HoaDon values(N'HD0006', '09:23:31', '2022/05/29', N'NV0006', N'KH0006', N'KM0005', 975000)
--INSERT INTO HoaDon values(N'HD0007', '15:43:27', '2022/07/17', N'NV0007', N'KH0007', N'KM0005', 840000)
--INSERT INTO HoaDon values(N'HD0008', '20:27:46', '2022/10/30', N'NV0008', N'KH0008', N'KM0006', 925000)
--INSERT INTO HoaDon values(N'HD0009', '22:05:48', '2022/03/15', N'NV0009', N'KH0009', N'KM0007', 650000)
--INSERT INTO HoaDon values(N'HD0010', '17:38:46', '2022/08/25', N'NV0010', N'KH0010', N'KM0008', 725000)
--INSERT INTO HoaDon values(N'HD0011', '14:55:21', '2023/06/24', N'NV0011', N'KH0011', N'KM0008', 1275000)
--INSERT INTO HoaDon values(N'HD0012', '18:25:47', '2023/05/02', N'NV0012', N'KH0012', N'KM0009', 1000000)
--INSERT INTO HoaDon values(N'HD0013', '21:42:58', '2023/01/15', N'NV0013', N'KH0013', N'KM0010', 905000)
--INSERT INTO HoaDon values(N'HD0014', '11:50:11', '2023/08/29', N'NV0014', N'KH0014', N'KM0005', 605000)
--INSERT INTO HoaDon values(N'HD0015', '12:35:47', '2023/10/02', N'NV0015', N'KH0015', N'KM0007', 685000)
--INSERT INTO HoaDon values(N'HD0016', '19:27:06', '2023/04/13', N'NV0016', N'KH0016', N'KM0010', 1775000)
--INSERT INTO HoaDon values(N'HD0017', '13:45:29', '2023/07/18', N'NV0017', N'KH0017', N'KM0003', 990000)
--INSERT INTO HoaDon values(N'HD0018', '23:02:33', '2023/03/17', N'NV0018', N'KH0018', N'KM0009', 1050000)
--INSERT INTO HoaDon values(N'HD0019', '15:46:13', '2023/05/25', N'NV0019', N'KH0019', N'KM0009', 1805000)
--INSERT INTO HoaDon values(N'HD0020', '10:55:04', '2023/09/07', N'NV0020', N'KH0020', N'KM0002', 525000)
----Xem toan bo HoaDon
--select *from HoaDon

------------------------------CT HOA DON---------------------------------------------------
----INSERT INTO ChiTietHoaDon(MaPhong, MaHD, ThoiGianNhanPhong, ThoiGianTraPhong)
--INSERT INTO ChiTietHoaDon values(N'P0001', N'HD0001', '2022/11/21 8:30:15', '2022/11/21 11:31:24')
--INSERT INTO ChiTietHoaDon values(N'P0002', N'HD0001', '2022/03/10 11:32:15', '2022/03/10 16:31:24') 
--INSERT INTO ChiTietHoaDon values(N'P0003', N'HD0002', '2022/09/24 14:25:32', '2022/09/24 19:30:31')
--INSERT INTO ChiTietHoaDon values(N'P0004', N'HD0003', '2022/06/05 10:15:40', '2022/06/05 13:20:15')
--INSERT INTO ChiTietHoaDon values(N'P0005', N'HD0004', '2022/10/13 9:10:37', '2022/10/13 12:15:38')
--INSERT INTO ChiTietHoaDon values(N'P0006', N'HD0004', '2022/05/29 12:18:23', '2022/05/29 14:15:35')
--INSERT INTO ChiTietHoaDon values(N'P0007', N'HD0005', '2022/07/17 18:45:28', '2022/07/17 22:50:37')
--INSERT INTO ChiTietHoaDon values(N'P0008', N'HD0006', '2022/10/30 11:05:20', '2022/10/30 17:10:55')
--INSERT INTO ChiTietHoaDon values(N'P0009', N'HD0007', '2022/03/15 20:36:34', '2022/03/15 23:45:10')
--INSERT INTO ChiTietHoaDon values(N'P0010', N'HD0008', '2022/08/25 13:31:12', '2022/08/25 15:35:21')
--INSERT INTO ChiTietHoaDon values(N'P0011', N'HD0008', '2023/06/24 15:40:34', '2023/06/24 19:55:40')
--INSERT INTO ChiTietHoaDon values(N'P0012', N'HD0009', '2023/05/02 9:20:30', '2023/05/02 13:28:48')
--INSERT INTO ChiTietHoaDon values(N'P0013', N'HD0010', '2023/01/15 19:23:45', '2023/01/15 22:30:50')
--INSERT INTO ChiTietHoaDon values(N'P0014', N'HD0011', '2023/08/29 10:15:21', '2023/08/29 15:21:23')
--INSERT INTO ChiTietHoaDon values(N'P0015', N'HD0012', '2023/10/02 13:40:38', '2023/10/02 17:35:14')
--INSERT INTO ChiTietHoaDon values(N'P0016', N'HD0012', '2023/04/13 17:38:27', '2023/04/13 21:40:35')
--INSERT INTO ChiTietHoaDon values(N'P0017', N'HD0013', '2023/07/18 15:14:45', '2023/07/18 19:23:57')
--INSERT INTO ChiTietHoaDon values(N'P0018', N'HD0014', '2023/03/17 18:40:17', '2023/03/17 22:35:41')
--INSERT INTO ChiTietHoaDon values(N'P0019', N'HD0015', '2023/05/25 11:28:29', '2023/05/25 14:36:10')
--INSERT INTO ChiTietHoaDon values(N'P0020', N'HD0015', '2023/09/07 14:40:16', '2023/09/07 18:22:26')
----Xem toan bo CTPhong
--select *from ChiTietHoaDon

----------------------------------CTDV PHONG-----------------------------------------------
----INSERT INTO CTDVPhong(MaHD, MaDV, SoLuong)
--INSERT INTO CTDVPhong values(N'HD0001', N'DV0001', 10)
--INSERT INTO CTDVPhong values(N'HD0001', N'DV0002', 15)
--INSERT INTO CTDVPhong values(N'HD0002', N'DV0003', 8)
--INSERT INTO CTDVPhong values(N'HD0003', N'DV0004', 5)
--INSERT INTO CTDVPhong values(N'HD0004', N'DV0005', 13)
--INSERT INTO CTDVPhong values(N'HD0005', N'DV0006', 6)
--INSERT INTO CTDVPhong values(N'HD0005', N'DV0007', 10)
--INSERT INTO CTDVPhong values(N'HD0005', N'DV0008', 15)
--INSERT INTO CTDVPhong values(N'HD0006', N'DV0009', 6)
--INSERT INTO CTDVPhong values(N'HD0007', N'DV0010', 9)
--INSERT INTO CTDVPhong values(N'HD0008', N'DV0011', 15)
--INSERT INTO CTDVPhong values(N'HD0009', N'DV0012', 8)
--INSERT INTO CTDVPhong values(N'HD0009', N'DV0013', 16)
--INSERT INTO CTDVPhong values(N'HD0010', N'DV0014', 20)
--INSERT INTO CTDVPhong values(N'HD0011', N'DV0015', 9)
--INSERT INTO CTDVPhong values(N'HD0012', N'DV0016', 14)
--INSERT INTO CTDVPhong values(N'HD0013', N'DV0017', 5)
--INSERT INTO CTDVPhong values(N'HD0014', N'DV0018', 10)
--INSERT INTO CTDVPhong values(N'HD0014', N'DV0019', 15)
--INSERT INTO CTDVPhong values(N'HD0014', N'DV0020', 20)
----Xem toan bo CTDVPhong
--select *from CTDVPhong


----Xem toan bo DichVu
--select *from DichVu
----Insert DichVu
----insert into DichVu (MaDV,TenDV,DonGiaNhap,DonGiaBan,DonViTinh,SoLuongTonKho,TinhTrangDV) values(N'DV021',N'Trái cây dĩa',50000,70000,N'Dĩa',100,1)
----insert into DichVu values(N'DV021',N'Trái cây dĩa',50000,70000,N'Dĩa',100,1)
----Update DichVu
----update DichVu 
----set TenDV = N'Khăn giấy', DonGiaNhap = 2000, DonGiaBan = 5000, DonViTinh = N'Cái', SoLuongTonKho = 100
----where MaDV = N'DV001'
----Delete DichVu
--delete from DichVu where MaDV = N'DV0021'
----Loc theo tinh trang (0: het, 1: sap het; 2: con; 3 da xoa)
--select *from DichVu
--where TinhTrangDV not like N'Đã xóa'
---- Tim dich vu theo ten
--select *from DichVu
--where TenDV like N'Bia'
----Xoa dich vu
--delete from DichVu where MaDV = N'DV0022'
----Lay toan bo ten dich vu
--select TenDV from DichVu


----------------------------------PHIEU DAT PHONG-----------------------------------------------
----INSERT INTO PhieuDatPhong(MaPDP, MaNV, MaKH, MaPhong,ThoiGianDatPhong, ThoiGianNhanPhong, SoLuongKhach, TinhTrangPDP, MoTa)
INSERT INTO PhieuDatPhong VALUES (N'PDP0001', N'NV0001', N'KH0001', N'P0001', '2023-09-01 10:00:00', '2023-09-01 12:00:00', 3, 0, N'Business meeting')
INSERT INTO PhieuDatPhong VALUES (N'PDP0002', N'NV0002', N'KH0002', N'P0002', '2023-09-02 14:00:00', '2023-09-03 12:00:00', 2, 0, N'Family vacation')
INSERT INTO PhieuDatPhong VALUES (N'PDP0003', N'NV0003', N'KH0003', N'P0003', '2023-10-03 16:30:00', '2023-10-03 20:30:00', 5, 0, N'Wedding party')

INSERT INTO PhieuDatPhong VALUES (N'PDP0004', N'NV0004', N'KH0004', N'P0004', '2023-10-04 18:00:00', '2023-10-05 12:00:00', 2, 0, N'Weekend getaway')
INSERT INTO PhieuDatPhong VALUES (N'PDP0005', N'NV0005', N'KH0005', N'P0005', '2023-10-05 09:00:00', '2023-10-05 17:00:00', 10, 0, N'')
INSERT INTO PhieuDatPhong VALUES (N'PDP0006', N'NV0006', N'KH0006', N'P0006', '2023-10-06 20:00:00', '2023-10-07 10:00:00', 15, 0, N'Birthday party')
INSERT INTO PhieuDatPhong VALUES (N'PDP0007', N'NV0007', N'KH0007', N'P0007', '2023-10-07 10:00:00', '2023-10-07 12:00:00', 5, 0, N'Business meeting')

INSERT INTO PhieuDatPhong VALUES (N'PDP0008', N'NV0008', N'KH0008', N'P0008', '2023-11-01 14:00:00', '2023-11-02 12:00:00', 4, 0, N'Family vacation')
INSERT INTO PhieuDatPhong VALUES (N'PDP0009', N'NV0009', N'KH0009', N'P0009', '2023-11-02 08:00:00', '2023-11-02 18:00:00', 15, 0, N'Học sinh')
INSERT INTO PhieuDatPhong VALUES (N'PDP0010', N'NV0002', N'KH0001', N'P0001', '2023-11-02 15:00', '2023-11-03 12:00', 2, 1, N'');
INSERT INTO PhieuDatPhong VALUES (N'PDP0011', N'NV0011', N'KH0012', N'P0002', '2023-11-04 11:00', '2023-11-04 20:00', 1, 1, N'Nghỉ ngơi và thư giãn');

INSERT INTO PhieuDatPhong VALUES (N'PDP0012', N'NV0002', N'KH0017', N'P0011', '2023-11-10 08:00', '2023-11-10 18:00', 5, 1, N'Họp công ty');
INSERT INTO PhieuDatPhong VALUES (N'PDP0013', N'NV0005', N'KH0012', N'P0012', '2023-11-10 14:00', '2023-11-12 10:00', 3, 1, N'Tiệc gia đình');
INSERT INTO PhieuDatPhong VALUES (N'PDP0014', N'NV0010', N'KH0015', N'P0017', '2023-11-15 09:00', '2023-11-16 16:00', 4, 1, N'Sinh viên');
INSERT INTO PhieuDatPhong VALUES (N'PDP0015', N'NV0008', N'KH0010', N'P0021', '2023-11-20 12:00', '2023-11-21 10:00', 2, 1, N'Tiệc gia đình');

-----------------------------------HOA DON-----------------------------------
----INSERT INTO HoaDon(MaHD, GioThanhToan, NgayThanhToan, MaNV, MaKH, MaKM, TongHoaDon, TinhTrang)
INSERT INTO HoaDon VALUES (N'HD0001', '15:30:00', '2023-09-01', N'NV0001', N'KH0001', N'KM0001', 500000)
INSERT INTO HoaDon VALUES (N'HD0002', '15:45:00', '2023-09-02', N'NV0002', N'KH0002', N'KM0002', 1500000)
INSERT INTO HoaDon VALUES (N'HD0003', '21:30:00', '2023-10-03', N'NV0003', N'KH0003', N'KM0003', 3000000)

INSERT INTO HoaDon VALUES (N'HD0004', '13:30:00', '2023-10-04', N'NV0006', N'KH0004', N'KM0004', 800000)
INSERT INTO HoaDon VALUES (N'HD0005', '13:30:00', '2023-10-05', N'NV0001', N'KH0005', N'KM0005', 1200000)
INSERT INTO HoaDon VALUES (N'HD0006', '15:30:00', '2023-10-06', N'NV0006', N'KH0006', N'KM0006', 5000000)
INSERT INTO HoaDon VALUES (N'HD0007', '13:30:00', '2023-10-07', N'NV0002', N'KH0007', N'KM0007', 500000)

INSERT INTO HoaDon VALUES (N'HD0008', '13:30:00', '2023-11-01', N'NV0003', N'KH0008', N'KM0008', 3000000)
INSERT INTO HoaDon VALUES (N'HD0009', '19:30:00', '2023-11-02', N'NV0006', N'KH0009', N'KM0009', 1500000)
INSERT INTO HoaDon VALUES (N'HD0010', '16:00', '2023-11-02', N'NV0002', N'KH0001', N'KM0004', 0);
INSERT INTO HoaDon VALUES (N'HD0011', '21:00', '2023-11-04', N'NV0003', N'KH0012', N'KM0007', 0);

INSERT INTO HoaDon VALUES (N'HD0012', '19:30', '2023-11-10', N'NV0006', N'KH0017', N'KM0002', 0);
INSERT INTO HoaDon VALUES (N'HD0013', '15:00', '2023-11-12', N'NV0001', N'KH0012', N'KM0006', 0);
INSERT INTO HoaDon VALUES (N'HD0014', '18:30', '2023-11-16', N'NV0002', N'KH0015', N'KM0001', 0);
INSERT INTO HoaDon VALUES (N'HD0015', '12:30', '2023-11-21', N'NV0003', N'KH0010', N'KM0003', 0);

-----------------------------------CHI TIET HOA DON-----------------------------------
----INSERT INTO ChiTietHoaDon(MaPhong, MaHD, ThoiGianNhanPhong, ThoiGianTraPhong)
INSERT INTO ChiTietHoaDon VALUES (N'P0001', N'HD0001', '2022-09-01 12:00:00', '2022-09-01 15:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0002', N'HD0002', '2023-09-02 12:00:00', '2023-09-02 15:45:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0003', N'HD0003', '2023-10-03 20:30:00', '2023-10-03 21:30:00')

INSERT INTO ChiTietHoaDon VALUES (N'P0004', N'HD0004', '2023-10-05 12:00:00', '2023-10-04 13:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0005', N'HD0005', '2023-10-05 17:00:00', '2023-10-05 13:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0006', N'HD0006', '2023-10-07 10:00:00', '2023-10-06 15:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0007', N'HD0007', '2023-10-07 12:00:00', '2023-10-07 13:30:00')

INSERT INTO ChiTietHoaDon VALUES (N'P0008', N'HD0008', '2023-11-02 12:00:00', '2023-11-01 13:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0009', N'HD0009', '2023-11-02 18:00:00', '2023-11-02 19:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0001', N'HD0010', '2023-11-03 12:00', '2023-11-02 16:00');
INSERT INTO ChiTietHoaDon VALUES (N'P0002', N'HD0011', '2023-11-04 20:00', '2023-11-04 21:00');

INSERT INTO ChiTietHoaDon VALUES (N'P0011', N'HD0012', '2023-11-10 18:00', '2023-11-10 19:30');
INSERT INTO ChiTietHoaDon VALUES (N'P0012', N'HD0013', '2023-11-12 10:00', '2023-11-12 15:00');
INSERT INTO ChiTietHoaDon VALUES (N'P0017', N'HD0014', '2023-11-16 16:00', '2023-11-16 18:30');
INSERT INTO ChiTietHoaDon VALUES (N'P0021', N'HD0015', '2023-11-21 10:00', '2023-11-21 12:30');

-----------------------------------CTDV PHONG-----------------------------------
----INSERT INTO CTDVPhong(MaHD, MaDV, SoLuong)
INSERT INTO CTDVPhong VALUES (N'HD0001', N'DV0001', 2)
INSERT INTO CTDVPhong VALUES (N'HD0001', N'DV0002', 1)

INSERT INTO CTDVPhong VALUES (N'HD0002', N'DV0003', 1)
INSERT INTO CTDVPhong VALUES (N'HD0002', N'DV0004', 1)

INSERT INTO CTDVPhong VALUES (N'HD0003', N'DV0005', 5)
INSERT INTO CTDVPhong VALUES (N'HD0003', N'DV0006', 2)

INSERT INTO CTDVPhong VALUES (N'HD0004', N'DV0001', 1)
INSERT INTO CTDVPhong VALUES (N'HD0004', N'DV0003', 2)

INSERT INTO CTDVPhong VALUES (N'HD0005', N'DV0002', 10)
INSERT INTO CTDVPhong VALUES (N'HD0005', N'DV0004', 1)

INSERT INTO CTDVPhong VALUES (N'HD0006', N'DV0005', 15)
INSERT INTO CTDVPhong VALUES (N'HD0006', N'DV0006', 5)

INSERT INTO CTDVPhong VALUES (N'HD0007', N'DV0002', 5)
INSERT INTO CTDVPhong VALUES (N'HD0007', N'DV0004', 1)

INSERT INTO CTDVPhong VALUES (N'HD0008', N'DV0001', 4)
INSERT INTO CTDVPhong VALUES (N'HD0008', N'DV0003', 1)

INSERT INTO CTDVPhong VALUES (N'HD0009', N'DV0002', 15)
INSERT INTO CTDVPhong VALUES (N'HD0009', N'DV0005', 1)

--INSERT INTO CTDVPhong VALUES (N'HD0010', N'DV0002', 1);

--INSERT INTO CTDVPhong VALUES (N'HD0011', N'DV0005', 2);

--INSERT INTO CTDVPhong VALUES (N'HD0012', N'DV0001', 1);

--INSERT INTO CTDVPhong VALUES (N'HD0013', N'DV0003', 3);

--INSERT INTO CTDVPhong VALUES (N'HD0014', N'DV0004', 1);

--INSERT INTO CTDVPhong VALUES (N'HD0015', N'DV0006', 2);

SELECT SUM(DATEDIFF(day, ThoiGianTraPhong, ThoiGianNhanPhong) * lp.GiaPhong) AS TotalCost
FROM ChiTietHoaDon cthd INNER JOIN Phong p ON cthd.MaPhong = p.MaPhong
INNER JOIN LoaiPhong lp ON p.MaLP = lp.MaLP WHERE MONTH(ThoiGianTraPhong) = MONTH('2023-10-03 21:30:00')

SELECT 
  SUM(
    DATEDIFF(HOUR, cthd.ThoiGianNhanPhong, cthd.ThoiGianTraPhong)*lp.GiaPhong 
  ) AS GrandTotalInHours
FROM ChiTietHoaDon cthd 
INNER JOIN Phong p ON cthd.MaPhong = p.MaPhong
INNER JOIN LoaiPhong lp ON p.MaLP = lp.MaLP
WHERE MONTH(ThoiGianTraPhong) = MONTH('2023-10-03 21:30:00')



SELECT SUM( dv.DonGia * ctdvp.SoLuong) AS TotalCount 
FROM CTDVPhong ctdvp 
INNER JOIN DichVu dv ON dv.MaDV = ctdvp.MaDV
INNER JOIN HoaDon hd ON hd.MaHD = ctdvp.MaHD
WHERE CONVERT(DATE, hd.NgayThanhToan) = ?

