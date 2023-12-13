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
--Xem toan bo DichVu
select *from DichVu

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


--------------------------------NHAN VIEN-----------------------------------------------
--INSERT INTO NhanVien(MaNV, TenNV, NamSinh, GioiTinh, SoDienThoai, CCCD, ChucVu, MatKhau, TinhTrangNV)
update NhanVien set SoDienThoai = N'0386076296' where MaNV = N'NV0001'
--ChucVu (0: nhan vien quan ly, 1: le tan, 2: phuc vu)
INSERT INTO NhanVien values(N'NV0001', N'Nguyễn Thị Yến Nhi', '2003/10/05', 1, N'0386076296', N'082594657912', N'Lễ tân', N'123456789', 1)
INSERT INTO NhanVien values(N'NV0002', N'Nguyễn Trung Kiên', '1999/07/21', 0, N'0205578931', N'064769123700', N'Nhân viên quản lý', N'123456789', 1)
INSERT INTO NhanVien values(N'NV0003', N'Vũ Đức Thắng', '2004/02/15', 0, N'0702447601', N'076591236064', N'Nhân viên quản lý', N'thang54t', 1)
INSERT INTO NhanVien values(N'NV0004', N'Lâm Thúy Hiền', '2002/05/18', 1, N'0452603567', N'084769431052', N'Phục vụ', N'hienthuy59r', 0)
INSERT INTO NhanVien values(N'NV0005', N'Nguyễn Văn Thanh', '2001/11/05', 0, N'0160567832', N'073976012433', N'Phục vụ', N'vanthanh10', 1)
INSERT INTO NhanVien values(N'NV0006', N'Nguyễn Hữu Nhật', '2000/04/23', 0, N'0870553419', N'087923408723', N'Lễ tân', N'nhathuu@07', 1)
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
INSERT INTO KhuyenMai values(N'KM0009', 5, '2023/10/21', '2023/10/29', '', 0)
INSERT INTO KhuyenMai values(N'KM0010', 5, '2023/11/30', '2023/12/09', '', 1)
--Xem toan bo KhuyenMai
select *from KhuyenMai


----------------------------------PHIEU DAT PHONG-----------------------------------------------
----INSERT INTO PhieuDatPhong(MaPDP, MaNV, MaKH, MaPhong,ThoiGianDatPhong, ThoiGianNhanPhong, SoLuongKhach, TinhTrangPDP, MoTa)
--TinhTrangPDP(0: chua thanh toan, 1: da thanh toan)
INSERT INTO PhieuDatPhong VALUES (N'PDP0001', N'NV0001', N'KH0010', N'P0003', '2020-09-01 10:00:00', '2020-09-01 12:00:00', 11, 1, N'Business meeting')
INSERT INTO PhieuDatPhong VALUES (N'PDP0002', N'NV0001', N'KH0003', N'P0005', '2020-09-03 14:00:00', '2020-09-04 12:00:00', 10, 1, N'Family vacation')
INSERT INTO PhieuDatPhong VALUES (N'PDP0003', N'NV0001', N'KH0017', N'P0010', '2020-09-08 16:30:00', '2020-09-08 20:30:00', 8, 1, N'Wedding party')

INSERT INTO PhieuDatPhong VALUES (N'PDP0004', N'NV0008', N'KH0004', N'P0005', '2021-06-04 18:00:00', '2021-06-05 12:00:00', 12, 1, N'Weekend getaway')
INSERT INTO PhieuDatPhong VALUES (N'PDP0005', N'NV0008', N'KH0015', N'P0016', '2021-06-06 09:00:00', '2021-06-06 12:00:00', 10, 1, N'')
INSERT INTO PhieuDatPhong VALUES (N'PDP0006', N'NV0008', N'KH0008', N'P0001', '2021-06-11 20:00:00', '2021-06-12 10:00:00', 4, 1, N'Birthday party')
INSERT INTO PhieuDatPhong VALUES (N'PDP0007', N'NV0008', N'KH0019', N'P0007', '2021-06-20 11:30:00', '2021-06-20 13:00:00', 8, 1, N'Business meeting')

INSERT INTO PhieuDatPhong VALUES (N'PDP0008', N'NV0002', N'KH0008', N'P0003', '2022-10-02 14:00:00', '2022-10-03 12:00:00', 13, 1, N'Family vacation')
INSERT INTO PhieuDatPhong VALUES (N'PDP0009', N'NV0002', N'KH0010', N'P0021', '2022-10-05 08:00:00', '2022-10-06 10:00:00', 7, 1, N'Học sinh')
INSERT INTO PhieuDatPhong VALUES (N'PDP0010', N'NV0002', N'KH0005', N'P0014', '2022-10-11 15:00:00', '2022-10-11 18:00:00', 12, 1, N'');
INSERT INTO PhieuDatPhong VALUES (N'PDP0011', N'NV0002', N'KH0020', N'P0018', '2022-10-16 11:00:00', '2022-10-16 14:00:00', 5, 1, N'Nghỉ ngơi và thư giãn')

INSERT INTO PhieuDatPhong VALUES (N'PDP0012', N'NV0011', N'KH0016', N'P0004', '2023-08-10 08:00:00', '2023-08-10 10:00:00', 9, 1, N'Họp công ty')
INSERT INTO PhieuDatPhong VALUES (N'PDP0013', N'NV0011', N'KH0005', N'P0016', '2023-08-13 14:30:00', '2023-08-14 14:30:00', 11, 1, N'')
INSERT INTO PhieuDatPhong VALUES (N'PDP0014', N'NV0011', N'KH0014', N'P0011', '2023-08-20 10:00:00', '2023-08-22 11:30:00', 5, 1, N'Sinh viên')
INSERT INTO PhieuDatPhong VALUES (N'PDP0015', N'NV0011', N'KH0013', N'P0022', '2023-08-25 13:30:00', '2023-08-25 15:00:00', 10, 1, N'Tiệc gia đình')

INSERT INTO PhieuDatPhong VALUES (N'PDP0016', N'NV0003', N'KH0005', N'P0020', '2023-10-06 08:00:00', '2023-10-08 09:00:00', 7, 1, N'Họp gia đình')
INSERT INTO PhieuDatPhong VALUES (N'PDP0017', N'NV0003', N'KH0011', N'P0015', '2023-10-17 16:00:00', '2023-10-17 18:00:00', 5, 1, N'Tiệc bạn bè')
INSERT INTO PhieuDatPhong VALUES (N'PDP0018', N'NV0006', N'KH0018', N'P0002', '2023-10-19 09:00:00', '2023-10-20 10:00:00', 10, 1, N'Tiệc mừng ngày Nhà giáo Việt Nam')
INSERT INTO PhieuDatPhong VALUES (N'PDP0019', N'NV0006', N'KH0003', N'P0024', '2023-10-28 12:00:00', '2023-10-29 13:30:00', 12, 1, N'')

INSERT INTO PhieuDatPhong VALUES (N'PDP0020', N'NV0006', N'KH0001', N'P0001', '2023-11-29 09:00:00', '2023-11-30 10:00:00', 7, 0, N'Tiệc gặp mặt hội viên')
INSERT INTO PhieuDatPhong VALUES (N'PDP0021', N'NV0006', N'KH0007', N'P0002', '2023-11-29 10:00:00', '2023-11-30 11:30:00', 9, 0, N'Tiệc gia đình');
INSERT INTO PhieuDatPhong VALUES (N'PDP0022', N'NV0006', N'KH0015', N'P0011', '2023-11-29 12:00:00', '2023-11-30 14:00:00', 6, 0, N'Tiệc chia tay học viên')
INSERT INTO PhieuDatPhong VALUES (N'PDP0023', N'NV0003', N'KH0014', N'P0012', '2023-11-30 14:00:00', '2023-11-30 15:30:00', 16, 0, N'Tiệc chào đón thành viên mới')
INSERT INTO PhieuDatPhong VALUES (N'PDP0024', N'NV0003', N'KH0008', N'P0017', '2023-11-30 16:30:00', '2023-11-30 17:00:00', 8, 0, N'Buổi găp mặt sinh viên')
INSERT INTO PhieuDatPhong VALUES (N'PDP0025', N'NV0003', N'KH0013', N'P0021', '2023-11-30 18:00:00', '2023-11-30 19:45:00', 5, 0, N'Tiệc mừng sinh nhật')
--Xem toan bo PhieuDatPhong
select *from PhieuDatPhong

-----------------------------------HOA DON-----------------------------------
----INSERT INTO HoaDon(MaHD, GioThanhToan, NgayThanhToan, MaNV, MaKH, MaKM, TongHoaDon, TinhTrang)
INSERT INTO HoaDon VALUES (N'HD0001', '14:00:00', '2020-09-01', N'NV0001', N'KH0001', N'KM0002', 332.200)
INSERT INTO HoaDon VALUES (N'HD0002', '15:30:00', '2020-09-02', N'NV0002', N'KH0002', N'KM0002', 950.400)
INSERT INTO HoaDon VALUES (N'HD0003', '21:30:00', '2020-10-03', N'NV0003', N'KH0003', N'KM0004', 165.000)
INSERT INTO HoaDon VALUES (N'HD0004', '16:30:00', '2020-10-04', N'NV0006', N'KH0004', N'KM0004', 841.500)
INSERT INTO HoaDon VALUES (N'HD0005', '19:00:00', '2020-10-05', N'NV0001', N'KH0005', N'KM0004', 473.000)
INSERT INTO HoaDon VALUES (N'HD0006', '21:45:00', '2020-10-06', N'NV0006', N'KH0006', N'KM0004', 875.875)
INSERT INTO HoaDon VALUES (N'HD0007', '15:00:00', '2020-10-07', N'NV0002', N'KH0007', N'KM0004', 1210.000)
INSERT INTO HoaDon VALUES (N'HD0008', '20:30:00', '2020-11-01', N'NV0003', N'KH0008', N'KM0006', 1179.200)
INSERT INTO HoaDon VALUES (N'HD0009', '17:30:00', '2020-11-02', N'NV0006', N'KH0009', N'KM0006', 363.000)
INSERT INTO HoaDon VALUES (N'HD0010', '21:30:00', '2020-11-04', N'NV0002', N'KH0010', N'KM0006', 0)

INSERT INTO HoaDon VALUES (N'HD0011', '14:00:00', '2021-05-04', N'NV0006', N'KH0012', N'KM0003', 434.500)
INSERT INTO HoaDon VALUES (N'HD0012', '15:30:00', '2021-05-10', N'NV0006', N'KH0005', N'KM0003', 833.800)
INSERT INTO HoaDon VALUES (N'HD0013', '21:30:00', '2021-05-12', N'NV0006', N'KH0001', N'KM0003', 627.000)
INSERT INTO HoaDon VALUES (N'HD0014', '16:30:00', '2021-05-16', N'NV0006', N'KH0003', N'KM0003', 1150.600)
INSERT INTO HoaDon VALUES (N'HD0015', '19:00:00', '2021-05-21', N'NV0006', N'KH0020', N'KM0003', 1265.000)
INSERT INTO HoaDon VALUES (N'HD0016', '21:45:00', '2021-06-02', N'NV0008', N'KH0015', N'KM0007', 549.175)
INSERT INTO HoaDon VALUES (N'HD0017', '15:00:00', '2021-06-03', N'NV0008', N'KH0018', N'KM0007', 2409.000)
INSERT INTO HoaDon VALUES (N'HD0018', '20:30:00', '2021-06-05', N'NV0008', N'KH0004', N'KM0007', 303.600)
INSERT INTO HoaDon VALUES (N'HD0019', '17:30:00', '2021-06-15', N'NV0008', N'KH0007', N'KM0007', 1199.000)
INSERT INTO HoaDon VALUES (N'HD0020', '21:30:00', '2021-06-27', N'NV0008', N'KH0016', N'KM0007', 1265.000)

INSERT INTO HoaDon VALUES (N'HD0021', '15:00:00', '2022-07-02', N'NV0011', N'KH0004', N'KM0001', 1243.000)
INSERT INTO HoaDon VALUES (N'HD0022', '15:30:00', '2022-07-04', N'NV0011', N'KH0008', N'KM0001', 693.000)
INSERT INTO HoaDon VALUES (N'HD0023', '21:30:00', '2022-07-11', N'NV0011', N'KH0009', N'KM0001', 1222.100)
INSERT INTO HoaDon VALUES (N'HD0024', '16:00:00', '2022-07-13', N'NV0011', N'KH0011', N'KM0002', 561.000)
INSERT INTO HoaDon VALUES (N'HD0025', '21:00:00', '2022-07-17', N'NV0011', N'KH0015', N'KM0002', 0)
INSERT INTO HoaDon VALUES (N'HD0026', '21:45:00', '2022-07-22', N'NV0011', N'KH0002', N'KM0005', 663.025)
INSERT INTO HoaDon VALUES (N'HD0027', '15:00:00', '2022-07-28', N'NV0011', N'KH0013', N'KM0005', 1430.000)
INSERT INTO HoaDon VALUES (N'HD0028', '20:45:00', '2022-08-04', N'NV0003', N'KH0019', N'KM0005', 1005.950)
INSERT INTO HoaDon VALUES (N'HD0029', '18:00:00', '2022-08-18', N'NV0003', N'KH0005', N'KM0005', 2186.800)
INSERT INTO HoaDon VALUES (N'HD0030', '22:00:00', '2022-08-28', N'NV0003', N'KH0017', N'KM0005', 0)

INSERT INTO HoaDon VALUES (N'HD0031', '18:00:00', '2023-09-13', N'NV0001', N'KH0007', N'KM0008', 1666.500)
INSERT INTO HoaDon VALUES (N'HD0032', '17:30:00', '2023-09-17', N'NV0001', N'KH0013', N'KM0008', 0)
INSERT INTO HoaDon VALUES (N'HD0033', '16:30:00', '2023-09-21', N'NV0001', N'KH0018', N'KM0008', 811.250)
INSERT INTO HoaDon VALUES (N'HD0034', '19:45:00', '2023-09-23', N'NV0001', N'KH0006', N'KM0008', 1116.500)
INSERT INTO HoaDon VALUES (N'HD0035', '20:00:00', '2023-09-25', N'NV0001', N'KH0004', N'KM0008', 880.000)
INSERT INTO HoaDon VALUES (N'HD0036', '17:00:00', '2023-09-29', N'NV0001', N'KH0020', N'KM0008', 451.000)
INSERT INTO HoaDon VALUES (N'HD0037', '15:30:00', '2023-10-21', N'NV0002', N'KH0010', N'KM0009', 710.850)
INSERT INTO HoaDon VALUES (N'HD0038', '19:30:00', '2023-10-23', N'NV0002', N'KH0013', N'KM0009', 929.250)
INSERT INTO HoaDon VALUES (N'HD0039', '13:45:00', '2023-10-25', N'NV0002', N'KH0005', N'KM0009', 1568.175)
INSERT INTO HoaDon VALUES (N'HD0040', '20:00:00', '2023-10-28', N'NV0002', N'KH0014', N'KM0009', 493.500)

INSERT INTO HoaDon VALUES (N'HD0041', '19:00:00', '2023-11-15', N'NV0006', N'KH0003', N'KM0009', 0)
INSERT INTO HoaDon VALUES (N'HD0042', '15:30:00', '2023-11-20', N'NV0006', N'KH0015', N'KM0009', 789.250)
INSERT INTO HoaDon VALUES (N'HD0043', '22:00:00', '2023-11-27', N'NV0006', N'KH0019', N'KM0009', 1567.500)
INSERT INTO HoaDon VALUES (N'HD0044', '11:30:00', '2023-11-30', N'NV0002', N'KH0012', N'KM0010', 645.750)
INSERT INTO HoaDon VALUES (N'HD0045', '14:00:00', '2023-11-30', N'NV0002', N'KH0015', N'KM0010', 1333.500)
INSERT INTO HoaDon VALUES (N'HD0046', '15:30:00', '2023-11-30', N'NV0002', N'KH0007', N'KM0010', 481.425)
INSERT INTO HoaDon VALUES (N'HD0047', '17:00:00', '2023-11-30', N'NV0008', N'KH0014', N'KM0010', 354.375)
INSERT INTO HoaDon VALUES (N'HD0048', '18:30:00', '2023-11-30', N'NV0008', N'KH0008', N'KM0010', 825.300)
INSERT INTO HoaDon VALUES (N'HD0049', '19:30:00', '2023-11-30', N'NV0008', N'KH0016', N'KM0010', 960.750)
INSERT INTO HoaDon VALUES (N'HD0050', '21:00:00', '2023-11-30', N'NV0008', N'KH0003', N'KM0010', 945.000)
--Xem toan bo HoaDon
select *from HoaDon

-----------------------------------CHI TIET HOA DON-----------------------------------
----INSERT INTO ChiTietHoaDon(MaPhong, MaHD, ThoiGianNhanPhong, ThoiGianTraPhong)
INSERT INTO ChiTietHoaDon VALUES (N'P0001', N'HD0001', '2020-09-01 12:00:00', '2020-09-01 14:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0002', N'HD0002', '2020-09-02 12:30:00', '2020-09-02 15:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0011', N'HD0003', '2020-10-03 20:30:00', '2020-10-03 21:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0012', N'HD0004', '2020-10-04 14:00:00', '2020-10-04 16:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0001', N'HD0005', '2020-10-05 17:00:00', '2020-10-05 19:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0017', N'HD0006', '2020-10-06 19:00:00', '2020-10-06 21:45:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0008', N'HD0007', '2020-10-07 13:00:00', '2020-10-07 15:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0015', N'HD0008', '2020-11-01 18:30:00', '2020-11-01 20:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0011', N'HD0009', '2020-11-02 15:30:00', '2020-11-02 17:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0021', N'HD0010', '2020-11-04 20:00:00', '2020-11-04 21:30:00')

INSERT INTO ChiTietHoaDon VALUES (N'P0004', N'HD0011', '2021-05-04 12:00:00', '2021-05-04 14:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0006', N'HD0012', '2021-05-10 12:30:00', '2021-05-10 15:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0015', N'HD0013', '2021-05-12 20:30:00', '2021-05-12 21:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0012', N'HD0014', '2021-05-16 14:00:00', '2021-05-16 16:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0007', N'HD0015', '2021-05-21 17:00:00', '2021-05-21 19:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0017', N'HD0016', '2021-06-02 19:00:00', '2021-06-02 21:45:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0019', N'HD0017', '2021-06-03 13:00:00', '2021-06-03 15:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0013', N'HD0018', '2021-06-05 18:30:00', '2021-06-05 20:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0008', N'HD0019', '2021-06-15 15:30:00', '2021-06-15 17:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0022', N'HD0020', '2021-06-27 20:00:00', '2021-06-27 21:30:00')

INSERT INTO ChiTietHoaDon VALUES (N'P0003', N'HD0021', '2022-07-02 12:00:00', '2022-07-02 15:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0005', N'HD0022', '2022-07-04 13:30:00', '2022-07-04 15:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0016', N'HD0023', '2022-07-11 20:00:00', '2022-07-11 21:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0014', N'HD0024', '2022-07-13 14:00:00', '2022-07-13 16:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0009', N'HD0025', '2022-07-17 18:00:00', '2022-07-17 21:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0011', N'HD0026', '2022-07-22 19:00:00', '2022-07-22 21:45:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0015', N'HD0027', '2022-07-28 13:00:00', '2022-07-28 15:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0012', N'HD0028', '2022-08-04 18:00:00', '2022-08-04 20:45:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0007', N'HD0029', '2022-08-18 15:00:00', '2022-08-18 18:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0024', N'HD0030', '2022-08-28 20:00:00', '2022-08-28 22:00:00')

INSERT INTO ChiTietHoaDon VALUES (N'P0007', N'HD0031', '2023-09-13 15:00:00', '2023-09-13 18:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0012', N'HD0032', '2023-09-17 15:30:00', '2023-09-17 17:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0018', N'HD0033', '2023-09-21 13:00:00', '2023-09-21 16:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0023', N'HD0034', '2023-09-23 18:00:00', '2023-09-23 19:45:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0004', N'HD0035', '2023-09-25 19:00:00', '2023-09-25 20:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0005', N'HD0036', '2023-09-29 16:00:00', '2023-09-29 17:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0014', N'HD0037', '2023-10-21 13:30:00', '2023-10-21 15:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0003', N'HD0038', '2023-10-23 17:00:00', '2023-10-23 19:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0016', N'HD0039', '2023-10-25 12:00:00', '2023-10-25 13:45:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0009', N'HD0040', '2023-10-28 18:00:00', '2023-10-28 20:00:00')

INSERT INTO ChiTietHoaDon VALUES (N'P0005', N'HD0041', '2023-11-15 16:00:00', '2023-11-15 19:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0010', N'HD0042', '2023-11-20 13:00:00', '2023-11-20 15:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0019', N'HD0043', '2023-11-27 20:00:00', '2023-11-27 22:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0007', N'HD0044', '2023-11-30 10:00:00', '2023-11-30 11:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0008', N'HD0045', '2023-11-30 11:30:00', '2023-11-30 14:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0010', N'HD0046', '2023-11-30 14:00:00', '2023-11-30 15:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0014', N'HD0047', '2023-11-30 15:30:00', '2023-11-30 17:00:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0015', N'HD0048', '2023-11-30 17:00:00', '2023-11-30 18:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0019', N'HD0049', '2023-11-30 18:30:00', '2023-11-30 19:30:00')
INSERT INTO ChiTietHoaDon VALUES (N'P0023', N'HD0050', '2023-11-30 19:30:00', '2023-11-30 21:00:00')
--Xem toan bo ChiTietHoaDon
select *from ChiTietHoaDon

-----------------------------------CTDV PHONG-----------------------------------
----INSERT INTO CTDVPhong(MaHD, MaDV, SoLuong)
INSERT INTO CTDVPhong VALUES (N'HD0001', N'DV0002', 2)
INSERT INTO CTDVPhong VALUES (N'HD0002', N'DV0005', 3)
INSERT INTO CTDVPhong VALUES (N'HD0002', N'DV0004', 3)
INSERT INTO CTDVPhong VALUES (N'HD0002', N'DV0009', 3)
INSERT INTO CTDVPhong VALUES (N'HD0003', N'DV0001', 5)
INSERT INTO CTDVPhong VALUES (N'HD0004', N'DV0009', 4)
INSERT INTO CTDVPhong VALUES (N'HD0005', N'DV0018', 2)
INSERT INTO CTDVPhong VALUES (N'HD0005', N'DV0020', 1)
INSERT INTO CTDVPhong VALUES (N'HD0006', N'DV0014', 1)
INSERT INTO CTDVPhong VALUES (N'HD0007', N'DV0006', 2)
INSERT INTO CTDVPhong VALUES (N'HD0008', N'DV0007', 4)
INSERT INTO CTDVPhong VALUES (N'HD0009', N'DV0013', 2)
INSERT INTO CTDVPhong VALUES (N'HD0010', N'DV0020', 1)
INSERT INTO CTDVPhong VALUES (N'HD0010', N'DV0011', 3)

INSERT INTO CTDVPhong VALUES (N'HD0011', N'DV0003', 3)
INSERT INTO CTDVPhong VALUES (N'HD0012', N'DV0005', 1)
INSERT INTO CTDVPhong VALUES (N'HD0013', N'DV0015', 2)
INSERT INTO CTDVPhong VALUES (N'HD0014', N'DV0002', 3)
INSERT INTO CTDVPhong VALUES (N'HD0014', N'DV0005', 6)
INSERT INTO CTDVPhong VALUES (N'HD0014', N'DV0011', 1)
INSERT INTO CTDVPhong VALUES (N'HD0014', N'DV0015', 3)
INSERT INTO CTDVPhong VALUES (N'HD0014', N'DV0020', 1)
INSERT INTO CTDVPhong VALUES (N'HD0015', N'DV0004', 5)
INSERT INTO CTDVPhong VALUES (N'HD0016', N'DV0007', 1)
INSERT INTO CTDVPhong VALUES (N'HD0017', N'DV0014', 2)
INSERT INTO CTDVPhong VALUES (N'HD0017', N'DV0016', 4)
INSERT INTO CTDVPhong VALUES (N'HD0017', N'DV0008', 2)
INSERT INTO CTDVPhong VALUES (N'HD0018', N'DV0002', 1)
INSERT INTO CTDVPhong VALUES (N'HD0019', N'DV0017', 2)
INSERT INTO CTDVPhong VALUES (N'HD0020', N'DV0009', 5)

INSERT INTO CTDVPhong VALUES (N'HD0021', N'DV0004', 4)
INSERT INTO CTDVPhong VALUES (N'HD0021', N'DV0006', 2)
INSERT INTO CTDVPhong VALUES (N'HD0022', N'DV0016', 1)
INSERT INTO CTDVPhong VALUES (N'HD0023', N'DV0003', 4)
INSERT INTO CTDVPhong VALUES (N'HD0023', N'DV0005', 5)
INSERT INTO CTDVPhong VALUES (N'HD0023', N'DV0007', 2)
INSERT INTO CTDVPhong VALUES (N'HD0024', N'DV0016', 2)
INSERT INTO CTDVPhong VALUES (N'HD0025', N'DV0021', 2)
INSERT INTO CTDVPhong VALUES (N'HD0025', N'DV0001', 6)
INSERT INTO CTDVPhong VALUES (N'HD0026', N'DV0005', 3)
INSERT INTO CTDVPhong VALUES (N'HD0026', N'DV0013', 4)
INSERT INTO CTDVPhong VALUES (N'HD0026', N'DV0003', 5)
INSERT INTO CTDVPhong VALUES (N'HD0027', N'DV0020', 2)
INSERT INTO CTDVPhong VALUES (N'HD0028', N'DV0002', 7)
INSERT INTO CTDVPhong VALUES (N'HD0028', N'DV0018', 3)
INSERT INTO CTDVPhong VALUES (N'HD0029', N'DV0010', 1)
INSERT INTO CTDVPhong VALUES (N'HD0029', N'DV0014', 2)
INSERT INTO CTDVPhong VALUES (N'HD0029', N'DV0005', 6)
INSERT INTO CTDVPhong VALUES (N'HD0029', N'DV0015', 2)
INSERT INTO CTDVPhong VALUES (N'HD0030', N'DV0014', 1)

INSERT INTO CTDVPhong VALUES (N'HD0031', N'DV0003', 6)
INSERT INTO CTDVPhong VALUES (N'HD0031', N'DV0017', 5)
INSERT INTO CTDVPhong VALUES (N'HD0032', N'DV0011', 3)
INSERT INTO CTDVPhong VALUES (N'HD0032', N'DV0015', 1)
INSERT INTO CTDVPhong VALUES (N'HD0033', N'DV0016', 2)
INSERT INTO CTDVPhong VALUES (N'HD0033', N'DV0005', 5)
INSERT INTO CTDVPhong VALUES (N'HD0034', N'DV0014', 1)
INSERT INTO CTDVPhong VALUES (N'HD0035', N'DV0004', 4)
INSERT INTO CTDVPhong VALUES (N'HD0035', N'DV0020', 2)
INSERT INTO CTDVPhong VALUES (N'HD0035', N'DV0018', 3)
INSERT INTO CTDVPhong VALUES (N'HD0036', N'DV0013', 4)
INSERT INTO CTDVPhong VALUES (N'HD0037', N'DV0002', 2)
INSERT INTO CTDVPhong VALUES (N'HD0037', N'DV0014', 1)
INSERT INTO CTDVPhong VALUES (N'HD0037', N'DV0008', 3)
INSERT INTO CTDVPhong VALUES (N'HD0038', N'DV0016', 2)
INSERT INTO CTDVPhong VALUES (N'HD0039', N'DV0005', 7)
INSERT INTO CTDVPhong VALUES (N'HD0039', N'DV0020', 2)
INSERT INTO CTDVPhong VALUES (N'HD0040', N'DV0016', 1)
INSERT INTO CTDVPhong VALUES (N'HD0040', N'DV0007', 5)

INSERT INTO CTDVPhong VALUES (N'HD0041', N'DV0006', 5)
INSERT INTO CTDVPhong VALUES (N'HD0041', N'DV0018', 3)
INSERT INTO CTDVPhong VALUES (N'HD0042', N'DV0004', 4)
INSERT INTO CTDVPhong VALUES (N'HD0043', N'DV0014', 1)
INSERT INTO CTDVPhong VALUES (N'HD0043', N'DV0001', 6)
INSERT INTO CTDVPhong VALUES (N'HD0043', N'DV0010', 2)
INSERT INTO CTDVPhong VALUES (N'HD0044', N'DV0001', 3)
INSERT INTO CTDVPhong VALUES (N'HD0045', N'DV0008', 1)
INSERT INTO CTDVPhong VALUES (N'HD0046', N'DV0005', 2)
INSERT INTO CTDVPhong VALUES (N'HD0046', N'DV0013', 1)
INSERT INTO CTDVPhong VALUES (N'HD0046', N'DV0015', 4)
INSERT INTO CTDVPhong VALUES (N'HD0047', N'DV0020', 1)
INSERT INTO CTDVPhong VALUES (N'HD0048', N'DV0007', 2)
INSERT INTO CTDVPhong VALUES (N'HD0049', N'DV0017', 1)
INSERT INTO CTDVPhong VALUES (N'HD0049', N'DV0019', 3)
INSERT INTO CTDVPhong VALUES (N'HD0050', N'DV0006', 5)
--Xem toan bo CTDVPhong
select *from CTDVPhong
