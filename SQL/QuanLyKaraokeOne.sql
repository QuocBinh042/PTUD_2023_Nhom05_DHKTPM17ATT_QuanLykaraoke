--Tạo cơ sở dữ liệu
create database QuanLyKaraokeOne
go
use QuanLyKaraokeOne
go
drop database QuanLyKaraokeOne
go
--------------Create Table
go
create table DichVu(
	MaDV nvarchar(20) not null primary key,
	TenDV nvarchar(50) not null,
	DonGia float not null,
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
	NamSinh date,
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
create table PhieuDatPhong(
	MaPDP nvarchar(20) not null primary key,
	-----FOREIGN KEY MaNhanVien
	MaNV nvarchar(20) not null,
	-----FOREIGN KEY MaKhachHang
	MaKH nvarchar(20) not null,
	ThoiGianDatPhong date,
	ThoiGianNhanPhong date,
	SoLuongKhach int,
	--TinhTrangPDP(0: chua thanh toan, 1: da thanh toan)
	TinhTrangPDP bit, 
	GhiChu nvarchar(50),
	CONSTRAINT fk_kh FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
	CONSTRAINT fk_nv FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
)
go
create table KhuyenMai(
	MaKM nvarchar(20) not null primary key,
	PhanTramGiam float,
	NgayBatDau date,
	NgayKetThuc date,
	MoTa nvarchar(50),
	--TrangThaiKM(0: ket thuc, 1: dang hoat dong)
	TrangThaiKM bit
)
go
create table HoaDon(
	MaHD nvarchar(20) not null primary key,
	-------FOREIGN KEY PhieuDatPhong
	MaPDP nvarchar(20) not null,
	GioThanhToan time,
	NgayThanhToan date,
	MaNV nvarchar(20) not null,
	MaKH nvarchar(20) not null,
	MaKM nvarchar(20) not null,
	TongHoaDon float
	CONSTRAINT fk_maPDP FOREIGN KEY (MaPDP) REFERENCES PhieuDatPhong(MaPDP),
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
	SoLuong int not null,
	CONSTRAINT fk_hd FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
	CONSTRAINT fk_dv FOREIGN KEY (MaDV) REFERENCES DichVu(MaDV),
	PRIMARY KEY (MaHD,MaDV)
)
go
create table LoaiPhong(
	MaLP nvarchar(20) not null primary key,
	TenLP nvarchar(50) not null,
	SucChua int,
	GiaPhong float
)
go
create table Phong(
	MaPhong nvarchar(20) not null primary key,
	TenPhong nvarchar(50) not null,
	MaLP nvarchar(20) not null,
	--TinhTrangPhong(con trong,dang thue,da dat truoc,da xoa)
	TinhTrangPhong nvarchar(20) not null,
	MoTa nvarchar(100)
	CONSTRAINT fk_lp FOREIGN KEY (MaLP) REFERENCES LoaiPhong(MaLP),
)
go
create table ChiTietHoaDon(
	-------FOREIGN KEY Phong
	MaPhong nvarchar(20) not null,
	-------FOREIGN KEY HoaDon
	MaHD nvarchar(20) not null,
	ThoiGianNhanPhong date,
	ThoiGianTraPhong date,
	CONSTRAINT fk_maP FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong),
	CONSTRAINT fk_mahd FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
	PRIMARY KEY (MaPhong,MaHD)
)

go

-----------Insert data



----------------------------LOAI PHONG---------------------------------------------------
--INSERT INTO LoaiPhong(MaLP, TenLP, SucChua, GiaPhong)
INSERT INTO LoaiPhong values(N'LP001', N'VIP', 10, 400000)
INSERT INTO LoaiPhong values(N'LP002', N'Thường', 10, 125000)
INSERT INTO LoaiPhong values(N'LP003', N'VIP', 15, 500000)
INSERT INTO LoaiPhong values(N'LP004', N'Thường', 15, 175000)
INSERT INTO LoaiPhong values(N'LP005', N'VIP', 20, 650000)
INSERT INTO LoaiPhong values(N'LP006', N'Thường', 20, 250000)
--Xem toan bo LoaiPhong
select *from LoaiPhong


----------------------------PHONG---------------------------------------------------
--INSERT INTO Phong(MaPhong, TenPhong, MaKM, MaLP, TinhTrangPhong, MoTa)
--TinhTrangPhong(0: con trong, 1: dang thue, 2: da dat truoc, 3: da xoa)
INSERT INTO Phong values(N'P001', N'101', N'LP002', N'Đã đặt trước',N'')
INSERT INTO Phong values(N'P002', N'102', N'LP004', N'Đã đặt trước',N'')
INSERT INTO Phong values(N'P003', N'103', N'LP006', N'Còn trống',N'')
INSERT INTO Phong values(N'P004', N'104', N'LP004', N'Đã xóa',N'')
INSERT INTO Phong values(N'P005', N'105', N'LP006', N'Còn trống',N'')
INSERT INTO Phong values(N'P006', N'106', N'LP006', N'Còn trống',N'')
INSERT INTO Phong values(N'P007', N'107', N'LP001', N'Đang thuê',N'')
INSERT INTO Phong values(N'P008', N'108', N'LP003', N'Đang thuê',N'')
INSERT INTO Phong values(N'P009', N'201', N'LP002', N'Đã xóa',N'')
INSERT INTO Phong values(N'P010', N'202', N'LP004', N'Đang thuê',N'')
INSERT INTO Phong values(N'P011', N'203', N'LP002', N'Đã đặt trước',N'')
INSERT INTO Phong values(N'P012', N'204', N'LP006', N'Đã đặt trước',N'')
INSERT INTO Phong values(N'P013', N'205',  N'LP002', N'Đã xóa',N'')
INSERT INTO Phong values(N'P014', N'206',  N'LP002', N'Đang thuê',N'')
INSERT INTO Phong values(N'P015', N'207',  N'LP003', N'Đang thuê',N'')
INSERT INTO Phong values(N'P016', N'208',  N'LP005', N'Đã xóa',N'')
INSERT INTO Phong values(N'P017', N'301',  N'LP004', N'Đã đặt trước',N'')
INSERT INTO Phong values(N'P018', N'302',  N'LP002', N'Còn trống',N'')
INSERT INTO Phong values(N'P019', N'303',  N'LP003', N'Đang thuê',N'')
INSERT INTO Phong values(N'P020', N'304',  N'LP001', N'Còn trống',N'')
INSERT INTO Phong values(N'P021', N'305',  N'LP001', N'Đã đặt trước',N'')
INSERT INTO Phong values(N'P022', N'306',  N'LP005', N'Còn trống',N'')
INSERT INTO Phong values(N'P023', N'307',  N'LP001', N'Đang thuê',N'')
INSERT INTO Phong values(N'P024', N'308',  N'LP005', N'Còn trống',N'')
--Xem toan bo Phong
select *from Phong
---
select p.MaPhong, TenPhong, lp.MaLP, lp.TenLP, lp.SucChua, lp.GiaPhong, p.TinhTrangPhong,p.MoTa from Phong p
inner join LoaiPhong lp on p.MaLP = lp.MaLP



--------------------------------DICH VU-----------------------------------------------
--INSERT INTO DichVu(MaDV, TenDV, DonGiaNhap, DonGiaBan, DonVi, SoLuongTonKho, TinhTrangDV) 
--TinhTrangDV (Co 4 loai: Con hang(SL>10), sap het hang(10>SL>0), het hang(SL=0), da xoa(SL=0))
INSERT INTO DichVu values(N'DV001', N'Khăn giấy', 5000, N'Bịch', 200, N'Còn hàng')
INSERT INTO DichVu values(N'DV002', N'Bia Heineken 330ml', 26000, N'Lon', 100, N'Còn hàng')
INSERT INTO DichVu values(N'DV003', N'Coca cola', 15000, N'Lon', 7, N'Sắp hết hàng')
INSERT INTO DichVu values(N'DV004', N'Trái cây dĩa',  70000, N'Dĩa', 0, N'Hết hàng')
INSERT INTO DichVu values(N'DV005', N'Khăn giấy ướt', 8000, N'Bịch', 100, N'Còn hàng')
INSERT INTO DichVu values(N'DV006', N'Cocktail',  50000, N'Lon', 50, N'Còn hàng')
INSERT INTO DichVu values(N'DV007', N'Cà phê sữa đá',  18000, N'Ly', 70, N'Còn hàng')
INSERT INTO DichVu values(N'DV008', N'Đậu phộng',  20000, N'Bịch', 5, N'Sắp hết hàng')
INSERT INTO DichVu values(N'DV009', N'Bia Tiger 500ml ', 35000, N'Lon', 100, N'Còn hàng')
INSERT INTO DichVu values(N'DV010', N'Cơm chiên hải sản',  40000, N'Dĩa', 3, N'Sắp hết hàng')
INSERT INTO DichVu values(N'DV011', N'Sụn gà rang muối',  40000, N'Dĩa', 0, N'Đã xóa')
INSERT INTO DichVu values(N'DV012', N'Nước suối Aquafina 500ml',  7000, N'Chai', 150, N'Còn hàng')
INSERT INTO DichVu values(N'DV013', N'Mì xào hải sản',  40000, N'Dĩa', 8, N'Sắp hết hàng')
INSERT INTO DichVu values(N'DV014', N'Thùng 24 lon bia Hà Nội 330ml',  315000, N'Thùng', 30, N'Còn hàng')
INSERT INTO DichVu values(N'DV015', N'Đậu hũ xóc tỏi ớt',  35000, N'Dĩa', 3, N'Sắp hết hàng')
INSERT INTO DichVu values(N'DV016', N'Bia Sài Gòn Chill 330ml dạng lốc 6 lon',  130000, N'Lốc', 0, N'Hết hàng')
INSERT INTO DichVu values(N'DV017', N'Xúc xích nướng tiêu',  45000, N'Dĩa', 100, N'Còn hàng')
INSERT INTO DichVu values(N'DV018', N'Bia Huda 330ml',  15000, N'Lon', 0, N'Đã xóa')
INSERT INTO DichVu values(N'DV019', N'Chả giò',  40000, N'Dĩa', 20, N'Còn hàng')
INSERT INTO DichVu values(N'DV020', N'6 lon bia Heineken Silver 330ml', 150000, N'Lốc', 40, N'Còn hàng')
--Xem toan bo DichVu
select *from DichVu
--Insert DichVu
--insert into DichVu (MaDV,TenDV,DonGiaNhap,DonGiaBan,DonViTinh,SoLuongTonKho,TinhTrangDV) values(N'DV021',N'Trái cây dĩa',50000,70000,N'Dĩa',100,1)
--insert into DichVu values(N'DV021',N'Trái cây dĩa',50000,70000,N'Dĩa',100,1)
--Update DichVu
--update DichVu 
--set TenDV = N'Khăn giấy', DonGiaNhap = 2000, DonGiaBan = 5000, DonViTinh = N'Cái', SoLuongTonKho = 100
--where MaDV = N'DV001'
--Delete DichVu
delete from DichVu where MaDV = N'DV021'
--Loc theo tinh trang (0: het, 1: sap het; 2: con; 3 da xoa)
select *from DichVu
where TinhTrangDV not like N'Đã xóa'
-- Tim dich vu theo ten
select *from DichVu
where TenDV like N'Bia'
--Xoa dich vu
delete from DichVu where MaDV = N'DV022'
--Lay toan bo ten dich vu
select TenDV from DichVu




--------------------------------NHAN VIEN-----------------------------------------------
--INSERT INTO NhanVien(MaNV, TenNV, NamSinh, GioiTinh, SoDienThoai, CCCD, ChucVu, MatKhau, TinhTrangNV)
--ChucVu (0: nhan vien quan ly, 1: le tan, 2: phuc vu)
INSERT INTO NhanVien values(N'NV001', N'Nguyễn Thị Yến Nhi', '2003/10/05', 1, N'0395259603', N'082594657912', N'Lễ tân', N'nhinhi23@', 1)
INSERT INTO NhanVien values(N'NV002', N'Nguyễn Trung Kiên', '1999/07/21', 0, N'0205578931', N'064769123700', N'Phục vụ', N'kienn65i', 0)
INSERT INTO NhanVien values(N'NV003', N'Vũ Đức Thắng', '2004/02/15', 0, N'0702447601', N'076591236064', N'Nhân viên quản lý', N'thang54t', 1)
INSERT INTO NhanVien values(N'NV004', N'Lâm Thúy Hiền', '2002/05/18', 1, N'0452603567', N'084769431052', N'Phục vụ', N'hienthuy59r', 0)
INSERT INTO NhanVien values(N'NV005', N'Nguyễn Văn Thanh', '2001/11/05', 0, N'0160567832', N'073976012433', N'Phục vụ', N'vanthanh10', 1)
INSERT INTO NhanVien values(N'NV006', N'Nguyễn Hữu Nhật', '2000/04/23', 0, N'0870553419', N'087923408723', N'Lễ tân', N'nhathuu@07', 0)
INSERT INTO NhanVien values(N'NV007', N'Nguyễn Đỗ Gia Hân', '1998/06/01', 1, N'0573960875', N'062769236591', N'Phục vụ', N'giahan1314', 1)
INSERT INTO NhanVien values(N'NV008', N'Nguyễn Ngọc Anh Thư', '2001/04/29', 1, N'0952566034', N'073658012563', N'Nhân viên quản lý', N'anhthu542', 1)
INSERT INTO NhanVien values(N'NV009', N'Trần Anh Tuấn', '2003/09/25', 0, N'0651273390', N'088425070237', N'Phục vụ', N'tuananh76@a', 0)
INSERT INTO NhanVien values(N'NV010', N'Nguyễn Văn Phương', '2000/12/07', 0, N'0305671489', N'084072359038', N'Phục vụ', N'baoky3415', 0)
INSERT INTO NhanVien values(N'NV011', N'Trần Hoàng Nghĩa', '2001/05/22', 0, N'0582632176', N'076548160533', N'Lễ tân', N'hoangnghia25#', 1)
INSERT INTO NhanVien values(N'NV012', N'Nguyễn Ngọc Phương Anh', '2000/01/15', 1, N'0154787932', N'072976002137', N'Phục vụ', N'panhhh5932', 0)
INSERT INTO NhanVien values(N'NV013', N'Trần Thị Thảo Trang', '2002/10/28', 1, N'0748500751', N'086942660432', N'Phục vụ', N'trangtr298', 0)
INSERT INTO NhanVien values(N'NV014', N'Nguyễn Cao Minh Khôi', '2002/03/09', 0, N'0952633601', N'069432866015', N'Phục vụ', N'minhkhoi3@#', 1)
INSERT INTO NhanVien values(N'NV015', N'Hà Bảo Ngọc', '2003/09/21', 1, N'0367927602', N'083602658934', N'Phục vụ', N'ngocbao639', 1)
INSERT INTO NhanVien values(N'NV016', N'Nguyễn Minh Nhựt', '2000/11/06', 0, N'0638477036', N'073965234671', N'Phục vụ', N'nhutminh532', 0)
INSERT INTO NhanVien values(N'NV017', N'Phạm Quế Trân', '2004/03/25', 1, N'0838614401', N'063634789932', N'Phục vụ', N'quetran406#', 1)
INSERT INTO NhanVien values(N'NV018', N'Châu Văn Tấn', '1999/07/18', 0, N'0269743064', N'083852704766', N'Phục vụ', N'tantan396@', 0)
INSERT INTO NhanVien values(N'NV019', N'Huỳnh Chí Phong', '2002/06/10', 0, N'0478280031', N'071603449254', N'Phục vụ', N'chiphongeu4', 0)
INSERT INTO NhanVien values(N'NV020', N'Trần Thị Quỳnh Như', '1999/04/17', 1, N'0386370342', N'084598701225', N'Phục vụ', N'nhuquynhh45', 1)
--Xem toan bo NhanVien
select *from NhanVien



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



--------------------------------PHIEU DAT PHONG-----------------------------------------------
--INSERT INTO PhieuDatPhong(MaPDP, MaNV, MaKH, ThoiGianDatPhong, ThoiGianNhanPhong, SoLuongKhach, TinhTrangPDP, MoTa)
INSERT INTO PhieuDatPhong values(N'PDP001', N'NV001', N'KH001', '2022/11/21 9:25:43', '2022/11/21 9:30:43', 4, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP002', N'NV002', N'KH002', '2022/03/10 8:45:12', '2022/03/10 8:50:15', 3, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP003', N'NV003', N'KH003', '2022/09/24 13:10:06', '2022/09/24 14:05:25', 7, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP004', N'NV004', N'KH004', '2022/06/05 17:05:25', '2022/06/05 17:10:30', 2, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP005', N'NV005', N'KH005', '2022/10/13 15:27:18', '2022/10/13 15:35:20', 5, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP006', N'NV006', N'KH006', '2022/05/29 11:30:15', '2022/05/29 11:35:20', 5, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP007', N'NV007', N'KH007', '2022/07/17 19:14:27', '2022/07/17 19:20:36', 6, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP008', N'NV008', N'KH008', '2022/10/30 10:40:10', '2022/10/30 10:45:20', 8, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP009', N'NV009', N'KH009', '2022/03/15 12:05:50', '2022/03/15 12:10:15', 3, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP010', N'NV010', N'KH010', '2022/08/25 20:35:38', '2022/08/25 20:40:03', 4, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP011', N'NV011', N'KH011', '2023/06/24 14:23:39', '2023/06/24 15:00:23', 2, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP012', N'NV012', N'KH012', '2023/05/02 16:36:55', '2023/05/02 16:40:10', 7, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP013', N'NV013', N'KH013', '2023/01/15 15:40:05', '2023/01/15 15:50:10', 5, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP014', N'NV014', N'KH014', '2023/08/29 10:01:20', '2023/08/29 10:06:35', 3, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP015', N'NV015', N'KH015', '2023/10/02 18:20:35', '2023/10/02 18:25:40', 4, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP016', N'NV016', N'KH016', '2023/04/13 10:14:20', '2023/04/13 10:20:34', 2, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP017', N'NV017', N'KH017', '2023/07/18 19:50:10', '2023/07/18 20:00:32', 8, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP018', N'NV018', N'KH018', '2023/03/17 14:21:37', '2023/03/17 14:25:45', 5, 1, '')
INSERT INTO PhieuDatPhong values(N'PDP019', N'NV019', N'KH019', '2023/05/25 8:30:45', '2023/05/25 8:35:50', 6, 0, '')
INSERT INTO PhieuDatPhong values(N'PDP020', N'NV020', N'KH020', '2023/09/07 17:44:03', '2023/09/07 17:51:07', 3, 1, '')
--Xem toan bo PhieuDatPhong
select *from PhieuDatPhong


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
--------------------------------HOA DON-----------------------------------------------
--INSERT INTO HoaDon(MaHD, MaPDP, GioThanhToan, NgayThanhToan, MaNV, MaKH, MaKM, TongHoaDon)
INSERT INTO HoaDon values(N'HD001', N'PDP001', '13:34:26', '2022/11/21', N'NV001', N'KH001', N'KM001', 475000)
INSERT INTO HoaDon values(N'HD002', N'PDP002', '16:30:50', '2022/03/10', N'NV002', N'KH002', N'KM002', 775000)
INSERT INTO HoaDon values(N'HD003', N'PDP003', '10:15:30', '2022/09/24', N'NV003', N'KH003', N'KM003', 1355000)
INSERT INTO HoaDon values(N'HD004', N'PDP004', '11:35:45', '2022/06/05', N'NV004', N'KH004', N'KM004', 1525000)
INSERT INTO HoaDon values(N'HD005', N'PDP005', '12:01:59', '2022/10/13', N'NV005', N'KH005', N'KM005', 1130000)
INSERT INTO HoaDon values(N'HD006', N'PDP006', '09:23:31', '2022/05/29', N'NV006', N'KH006', N'KM006', 975000)
INSERT INTO HoaDon values(N'HD007', N'PDP007', '15:43:27', '2022/07/17', N'NV007', N'KH007', N'KM007', 840000)
INSERT INTO HoaDon values(N'HD008', N'PDP008', '20:27:46', '2022/10/30', N'NV008', N'KH008', N'KM008', 925000)
INSERT INTO HoaDon values(N'HD009', N'PDP009', '22:05:48', '2022/03/15', N'NV009', N'KH009', N'KM009', 650000)
INSERT INTO HoaDon values(N'HD010', N'PDP010', '17:38:46', '2022/08/25', N'NV010', N'KH010', N'KM010', 725000)
INSERT INTO HoaDon values(N'HD011', N'PDP011', '14:55:21', '2023/06/24', N'NV011', N'KH011', N'KM011', 1275000)
INSERT INTO HoaDon values(N'HD012', N'PDP012', '18:25:47', '2023/05/02', N'NV012', N'KH012', N'KM012', 1000000)
INSERT INTO HoaDon values(N'HD013', N'PDP013', '21:42:58', '2023/01/15', N'NV013', N'KH013', N'KM013', 905000)
INSERT INTO HoaDon values(N'HD014', N'PDP014', '11:50:11', '2023/08/29', N'NV014', N'KH014', N'KM014', 605000)
INSERT INTO HoaDon values(N'HD015', N'PDP015', '12:35:47', '2023/10/02', N'NV015', N'KH015', N'KM015', 685000)
INSERT INTO HoaDon values(N'HD016', N'PDP016', '19:27:06', '2023/04/13', N'NV016', N'KH016', N'KM016', 1775000)
INSERT INTO HoaDon values(N'HD017', N'PDP017', '13:45:29', '2023/07/18', N'NV017', N'KH017', N'KM017', 990000)
INSERT INTO HoaDon values(N'HD018', N'PDP018', '23:02:33', '2023/03/17', N'NV018', N'KH018', N'KM018', 1050000)
INSERT INTO HoaDon values(N'HD019', N'PDP019', '15:46:13', '2023/05/25', N'NV019', N'KH019', N'KM019', 1805000)
INSERT INTO HoaDon values(N'HD020', N'PDP020', '10:55:04', '2023/09/07', N'NV020', N'KH020', N'KM020', 525000)
--Xem toan bo HoaDon
select *from HoaDon


----------------------------CT HOA DON---------------------------------------------------
--INSERT INTO ChiTietHoaDon(MaPhong, MaHD, ThoiGianNhanPhong, ThoiGianTraPhong)
INSERT INTO ChiTietHoaDon values(N'P001', N'HD001', '8:30:15', '11:31:24')
INSERT INTO ChiTietHoaDon values(N'P002', N'HD001', '11:32:15', '16:31:24') 
INSERT INTO ChiTietHoaDon values(N'P003', N'HD002', '14:25:32', '19:30:31')
INSERT INTO ChiTietHoaDon values(N'P004', N'HD003', '10:15:40', '13:20:15')
INSERT INTO ChiTietHoaDon values(N'P005', N'HD004', '9:10:37', '12:15:38')
INSERT INTO ChiTietHoaDon values(N'P006', N'HD004', '12:18:23', '21:15:35')
INSERT INTO ChiTietHoaDon values(N'P007', N'HD005', '18:45:28', '22:50:37')
INSERT INTO ChiTietHoaDon values(N'P008', N'HD006', '11:05:20', '17:10:55')
INSERT INTO ChiTietHoaDon values(N'P009', N'HD007', '20:36:34', '23:45:10')
INSERT INTO ChiTietHoaDon values(N'P010', N'HD008', '13:31:12', '15:35:21')
INSERT INTO ChiTietHoaDon values(N'P011', N'HD008', '15:40:34', '19:55:40')
INSERT INTO ChiTietHoaDon values(N'P012', N'HD009', '9:20:30', '13:28:48')
INSERT INTO ChiTietHoaDon values(N'P013', N'HD010', '19:23:45', '22:30:50')
INSERT INTO ChiTietHoaDon values(N'P014', N'HD011', '10:15:21', '15:21:23')
INSERT INTO ChiTietHoaDon values(N'P015', N'HD012', '13:40:38', '17:35:14')
INSERT INTO ChiTietHoaDon values(N'P016', N'HD012', '17:38:27', '21:40:35')
INSERT INTO ChiTietHoaDon values(N'P017', N'HD013', '15:14:45', '19:23:57')
INSERT INTO ChiTietHoaDon values(N'P018', N'HD014', '18:40:17', '22:35:41')
INSERT INTO ChiTietHoaDon values(N'P019', N'HD015', '11:28:29', '14:36:10')
INSERT INTO ChiTietHoaDon values(N'P020', N'HD015', '14:40:16', '18:22:26')
--Xem toan bo CTPhong
select *from ChiTietHoaDon


--------------------------------CTDV PHONG-----------------------------------------------
--INSERT INTO CTDVPhong(MaHD, MaDV, SoLuong)
INSERT INTO CTDVPhong values(N'HD001', N'DV001', 10)
INSERT INTO CTDVPhong values(N'HD001', N'DV002', 15)
INSERT INTO CTDVPhong values(N'HD002', N'DV003', 8)
INSERT INTO CTDVPhong values(N'HD003', N'DV004', 5)
INSERT INTO CTDVPhong values(N'HD004', N'DV005', 13)
INSERT INTO CTDVPhong values(N'HD005', N'DV006', 6)
INSERT INTO CTDVPhong values(N'HD005', N'DV007', 10)
INSERT INTO CTDVPhong values(N'HD005', N'DV008', 15)
INSERT INTO CTDVPhong values(N'HD006', N'DV009', 6)
INSERT INTO CTDVPhong values(N'HD007', N'DV010', 9)
INSERT INTO CTDVPhong values(N'HD008', N'DV011', 15)
INSERT INTO CTDVPhong values(N'HD009', N'DV012', 8)
INSERT INTO CTDVPhong values(N'HD009', N'DV013', 16)
INSERT INTO CTDVPhong values(N'HD010', N'DV014', 20)
INSERT INTO CTDVPhong values(N'HD011', N'DV015', 9)
INSERT INTO CTDVPhong values(N'HD012', N'DV016', 14)
INSERT INTO CTDVPhong values(N'HD013', N'DV017', 5)
INSERT INTO CTDVPhong values(N'HD014', N'DV018', 10)
INSERT INTO CTDVPhong values(N'HD014', N'DV019', 15)
INSERT INTO CTDVPhong values(N'HD014', N'DV020', 20)
--Xem toan bo CTDVPhong
select *from CTDVPhong







