USE [master]
GO
/****** Object:  Database [QLNDC]    Script Date: 7/9/2022 11:31:21 AM ******/
CREATE DATABASE [QLNDC]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QLNDC', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\QLNDC.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QLNDC_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\QLNDC_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [QLNDC] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLNDC].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLNDC] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QLNDC] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QLNDC] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QLNDC] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QLNDC] SET ARITHABORT OFF 
GO
ALTER DATABASE [QLNDC] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QLNDC] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QLNDC] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QLNDC] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QLNDC] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QLNDC] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QLNDC] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QLNDC] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QLNDC] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QLNDC] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QLNDC] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QLNDC] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QLNDC] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QLNDC] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QLNDC] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QLNDC] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QLNDC] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QLNDC] SET RECOVERY FULL 
GO
ALTER DATABASE [QLNDC] SET  MULTI_USER 
GO
ALTER DATABASE [QLNDC] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QLNDC] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QLNDC] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QLNDC] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QLNDC] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'QLNDC', N'ON'
GO
USE [QLNDC]
GO
/****** Object:  Table [dbo].[CTDDH]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTDDH](
	[MADDH] [int] NOT NULL,
	[MASP] [int] NOT NULL,
	[SL] [int] NOT NULL,
	[DONGIA] [int] NOT NULL,
 CONSTRAINT [PK_CTPHIEUDAT] PRIMARY KEY CLUSTERED 
(
	[MADDH] ASC,
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CTHD]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHD](
	[MAHD] [int] NOT NULL,
	[MASP] [int] NOT NULL,
	[SL] [int] NOT NULL,
	[DONGIA] [int] NOT NULL,
 CONSTRAINT [PK_CTHD] PRIMARY KEY CLUSTERED 
(
	[MAHD] ASC,
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CTPN]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTPN](
	[MAPN] [int] NOT NULL,
	[MASP] [int] NOT NULL,
	[SL] [int] NOT NULL,
	[DONGIA] [int] NOT NULL,
 CONSTRAINT [PK_CTPN] PRIMARY KEY CLUSTERED 
(
	[MAPN] ASC,
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DonDH]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DonDH](
	[MADDH] [int] IDENTITY(1,1) NOT NULL,
	[NGAYDAT] [date] NOT NULL,
	[HONGUOINHAN] [nvarchar](50) NULL,
	[TENNGUOINHAN] [nvarchar](20) NULL,
	[DIACHINHAN] [nvarchar](100) NOT NULL,
	[SDTNGUOINHAN] [varchar](10) NOT NULL,
	[MAKH] [int] NOT NULL,
 CONSTRAINT [PK_PHIEUDAT] PRIMARY KEY CLUSTERED 
(
	[MADDH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HANG]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HANG](
	[MAHANG] [int] IDENTITY(1,1) NOT NULL,
	[TENHANG] [nvarchar](50) NULL,
 CONSTRAINT [PK_HANG] PRIMARY KEY CLUSTERED 
(
	[MAHANG] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HOADON]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOADON](
	[MAHD] [int] IDENTITY(1,1) NOT NULL,
	[MANV] [int] NOT NULL,
	[NGAYLAPHD] [date] NOT NULL,
 CONSTRAINT [PK_HOADON] PRIMARY KEY CLUSTERED 
(
	[MAHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[KHACHHANG](
	[MAKH] [int] IDENTITY(1,1) NOT NULL,
	[HO] [nvarchar](50) NULL,
	[TEN] [nvarchar](20) NULL,
	[DIACHI] [nvarchar](100) NOT NULL,
	[SDT] [char](10) NOT NULL,
	[NGSINH] [date] NOT NULL,
	[EMAIL] [varchar](50) NOT NULL,
	[MATK] [int] NOT NULL,
 CONSTRAINT [PK_KHACHHANG] PRIMARY KEY CLUSTERED 
(
	[MAKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LOAI]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAI](
	[MALOAI] [int] IDENTITY(1,1) NOT NULL,
	[TENLOAI] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_LOAI] PRIMARY KEY CLUSTERED 
(
	[MALOAI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MANV] [int] IDENTITY(1,1) NOT NULL,
	[HO] [nvarchar](50) NOT NULL,
	[TEN] [nvarchar](20) NOT NULL,
	[SDT] [varchar](10) NOT NULL,
	[DIACHI] [nvarchar](100) NOT NULL,
	[GIOITINH] [bit] NOT NULL,
	[EMAIL] [varchar](50) NOT NULL,
	[CMND] [varchar](50) NOT NULL,
	[NGSINH] [date] NOT NULL,
	[LUONG] [int] NOT NULL,
	[TRANGTHAI] [bit] NOT NULL,
	[MATK] [int] NOT NULL,
	[MAQL] [int] NOT NULL,
 CONSTRAINT [PK_NHANVIEN] PRIMARY KEY CLUSTERED 
(
	[MANV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PhanQuyen]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhanQuyen](
	[MAPQ] [int] IDENTITY(1,1) NOT NULL,
	[Quyen] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_PhanQuyen] PRIMARY KEY CLUSTERED 
(
	[MAPQ] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PHIEUNHAP]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHIEUNHAP](
	[MAPN] [int] IDENTITY(1,1) NOT NULL,
	[NGAYNHAP] [date] NOT NULL,
	[MANV] [int] NOT NULL,
 CONSTRAINT [PK_PHIEUNHAP] PRIMARY KEY CLUSTERED 
(
	[MAPN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SANPHAM]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SANPHAM](
	[MASP] [int] IDENTITY(1,1) NOT NULL,
	[TENSP] [nvarchar](50) NOT NULL,
	[MOTA] [ntext] NULL,
	[SLTON] [int] NOT NULL CONSTRAINT [DF__SANPHAM__SLTON__0E8E2250]  DEFAULT ((0)),
	[MALOAI] [int] NOT NULL,
	[MAHANG] [int] NOT NULL,
	[HINHANH] [ntext] NULL,
	[GIA] [int] NOT NULL,
 CONSTRAINT [PK_SANPHAM] PRIMARY KEY CLUSTERED 
(
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 7/9/2022 11:31:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[MATK] [int] IDENTITY(1,1) NOT NULL,
	[TenDN] [varchar](50) NOT NULL,
	[MK] [nvarchar](500) NOT NULL,
	[MAPQ] [int] NOT NULL,
	[DOI] [bit] NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[MATK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (1, 1, 1, 10000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (1, 2, 1, 10000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (2, 3, 1, 9000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (3, 3, 1, 11000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (5, 4, 1, 18000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (6, 4, 1, 15000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (7, 5, 1, 7000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (9, 2, 1, 10000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (11, 1, 1, 10000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (11, 2, 1, 9000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (11, 10, 1, 10000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (19, 2, 1, 9000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (19, 3, 1, 11000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (19, 4, 1, 20000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (20, 1, 1, 10000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (20, 5, 5, 18000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (20, 6, 1, 15000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (21, 2, 2, 9000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (21, 4, 2, 20000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (22, 3, 1, 11000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (23, 1, 2, 10000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (23, 2, 2, 9000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (23, 3, 1, 11000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (24, 2, 3, 9000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (25, 1, 2, 10000)
INSERT [dbo].[CTDDH] ([MADDH], [MASP], [SL], [DONGIA]) VALUES (25, 4, 2, 20000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (2, 2, 1, 9000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (3, 4, 1, 11000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (4, 5, 1, 20000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (5, 6, 1, 18000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (6, 7, 1, 15000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (7, 2, 1, 7000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (8, 9, 1, 5000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (9, 7, 1, 10000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (10, 5, 1, 10000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (11, 9, 1, 10000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (14, 5, 1, 18000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (31, 5, 1, 18000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (31, 7, 2, 18000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (35, 5, 1, 18000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (35, 6, 1, 20000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (36, 5, 1, 18000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (37, 5, 1, 18000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (38, 7, 1, 7000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (43, 6, 1, 15000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (44, 6, 1, 15000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (44, 7, 4, 7000)
INSERT [dbo].[CTHD] ([MAHD], [MASP], [SL], [DONGIA]) VALUES (46, 5, 1, 18000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (1, 13, 50, 25000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (1, 14, 47, 50000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (2, 1, 20, 5000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (2, 2, 30, 7000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (2, 5, 120, 12300)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (2, 11, 100, 10000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (3, 3, 50, 11000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (3, 8, 150, 19000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (3, 9, 87, 20000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (4, 4, 50, 12000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (4, 5, 10, 9000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (5, 6, 100, 10000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (5, 7, 120, 15000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (5, 10, 100, 25000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (11, 5, 20, 18000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (11, 6, 50, 15000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (12, 7, 1, 7000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (13, 5, 4, 18000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (15, 5, 50, 18000)
INSERT [dbo].[CTPN] ([MAPN], [MASP], [SL], [DONGIA]) VALUES (15, 6, 10, 15000)
SET IDENTITY_INSERT [dbo].[DonDH] ON 

INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (1, CAST(N'2022-05-17' AS Date), N'Trần Tấn ', N'Phong', N'Quận 9', N'0825040559', 1)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (2, CAST(N'2022-05-17' AS Date), N'Trần Văn', N'Biển', N'Quận 9', N'0804656520', 6)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (3, CAST(N'2022-05-17' AS Date), N'Nguyễn ', N'Nhung', N'Quận 9', N'0154989054', 11)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (5, CAST(N'2022-05-17' AS Date), N'Nguyễn Thị', N'Nhung', N'Quận 9', N'0825040559', 1)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (6, CAST(N'2022-05-17' AS Date), N'Trần Tấn', N'Phong', N'Quận 9', N'0967533854', 8)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (7, CAST(N'2022-05-17' AS Date), N'Nguyễn Mạnh', N'Hùng', N'Quận 9', N'0155987812', 8)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (9, CAST(N'2022-05-17' AS Date), N'Nguyễn Văn', N'Danh', N'Quận 9', N'0148289328', 16)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (10, CAST(N'2022-05-29' AS Date), N'Trần Tấn', N'Phong', N'Quận 9', N'0165892015', 8)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (11, CAST(N'2022-05-30' AS Date), N'Trần Tấn ', N'Phong', N'Quận 9', N'0825040559', 31)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (12, CAST(N'2022-05-30' AS Date), N'Trần Tấn ', N'Phong', N'Quận 9', N'0825040559', 31)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (19, CAST(N'2022-06-06' AS Date), N'Trần Tấn ', N'Phong', N'Quận 9', N'0825040559', 31)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (20, CAST(N'2022-06-06' AS Date), N'Trần Tấn ', N'Phong', N'Quận 9', N'0825040559', 31)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (21, CAST(N'2022-06-06' AS Date), N'Trần Tấn ', N'Phong', N'Quận 9', N'0825404050', 31)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (22, CAST(N'2022-06-06' AS Date), N'Trần Tấn ', N'Phong', N'Quận 9', N'0825040559', 31)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (23, CAST(N'2022-06-06' AS Date), N'Trần Tấn ', N'Phong', N'Quận 9', N'0825040559', 31)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (24, CAST(N'2022-06-07' AS Date), N'Trần Tấn ', N'Phong', N'Quận 9', N'054050405', 31)
INSERT [dbo].[DonDH] ([MADDH], [NGAYDAT], [HONGUOINHAN], [TENNGUOINHAN], [DIACHINHAN], [SDTNGUOINHAN], [MAKH]) VALUES (25, CAST(N'2022-06-07' AS Date), N'Trần Tấn ', N'Phong', N'Quận 9', N'052505240', 31)
SET IDENTITY_INSERT [dbo].[DonDH] OFF
SET IDENTITY_INSERT [dbo].[HANG] ON 

INSERT [dbo].[HANG] ([MAHANG], [TENHANG]) VALUES (1, N'Vinamilk')
INSERT [dbo].[HANG] ([MAHANG], [TENHANG]) VALUES (2, N'CoCaCoLa')
INSERT [dbo].[HANG] ([MAHANG], [TENHANG]) VALUES (3, N'Pepsi')
INSERT [dbo].[HANG] ([MAHANG], [TENHANG]) VALUES (4, N'Nutrifood')
INSERT [dbo].[HANG] ([MAHANG], [TENHANG]) VALUES (5, N'Aquavila')
INSERT [dbo].[HANG] ([MAHANG], [TENHANG]) VALUES (6, N'Bia')
INSERT [dbo].[HANG] ([MAHANG], [TENHANG]) VALUES (11, N'Phong đẹp trai')
INSERT [dbo].[HANG] ([MAHANG], [TENHANG]) VALUES (12, N'Phong đẹp trai')
SET IDENTITY_INSERT [dbo].[HANG] OFF
SET IDENTITY_INSERT [dbo].[HOADON] ON 

INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (1, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (2, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (3, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (4, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (5, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (6, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (7, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (8, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (9, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (10, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (11, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (12, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (13, 2, CAST(N'2022-05-27' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (14, 2, CAST(N'2001-05-05' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (31, 2, CAST(N'2022-05-28' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (35, 2, CAST(N'2022-06-03' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (36, 2, CAST(N'2022-06-03' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (37, 2, CAST(N'2022-06-03' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (38, 2, CAST(N'2022-06-03' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (43, 2, CAST(N'2022-06-06' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (44, 2, CAST(N'2022-06-06' AS Date))
INSERT [dbo].[HOADON] ([MAHD], [MANV], [NGAYLAPHD]) VALUES (46, 2, CAST(N'2022-06-07' AS Date))
SET IDENTITY_INSERT [dbo].[HOADON] OFF
SET IDENTITY_INSERT [dbo].[KHACHHANG] ON 

INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (1, N'Trần Tấn', N'Phong', N'Quận 9', N'0825040559', CAST(N'2000-12-12' AS Date), N'trantanphong@gmail.com', 2)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (6, N'Thái Hồng', N'Vân', N'Quận Thủ Đức', N'0123456789', CAST(N'2000-06-25' AS Date), N'vanhong@gmail.com', 6)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (8, N'Phạm Mỹ', N'Hạnh', N'Quận 9', N'7899014556', CAST(N'1999-02-06' AS Date), N'myhanh@gmail.com', 7)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (11, N'Phạm Như', N'Thắng', N'Quận 10', N'0126504599', CAST(N'1998-04-02' AS Date), N'phamthang@gmail.com', 8)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (14, N'Nguyễn Thị', N'Nhung', N'Quận 10', N'0147992500', CAST(N'2001-06-09' AS Date), N'nhungbeo@gmail.com', 9)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (15, N'Nguyễn Mạnh', N'Hùng', N'Quận 2', N'7895416620', CAST(N'2001-08-08' AS Date), N'manhhung@gmail.com', 10)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (16, N'Trần Thanh', N'Hùng', N'Quận 11', N'0990504569', CAST(N'2001-05-16' AS Date), N'thanhhung@gmail.com', 11)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (17, N'Trần Ái', N'Thủy', N'Quận Thủ Đức', N'0967533854', CAST(N'2003-06-19' AS Date), N'thuytac@gmail.com', 12)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (18, N'Ngô Thảo', N'Như', N'Quận 10', N'0284202086', CAST(N'2001-03-30' AS Date), N'nhumap@gmail.com', 13)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (19, N'Ngu Như', N'Minh', N'Quận 9', N'0284005144', CAST(N'2001-01-20' AS Date), N'minhbeo@gmail.com', 14)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (20, N'Nguyễn Thanh', N'Danh', N'Quận 10', N'0248420898', CAST(N'2001-04-05' AS Date), N'danh@gmail.com', 15)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (31, N'Lại Đức ', N'Mạnh', N'Quận Thủ Đức', N'0825040559', CAST(N'2022-05-10' AS Date), N'n19dccn140@student.ptithcm.edu.vn', 44)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (35, N'Trần Tấn', N'Phong', N'Quận 10', N'0825040559', CAST(N'2022-05-04' AS Date), N'n19dccn140@student.ptithcm.edu.vn', 57)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (36, N'Trần Tấn', N'Phong', N'Quận 9', N'0825040559', CAST(N'2022-05-22' AS Date), N'n19dccn140@student.ptithcm.edu.vn', 58)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (37, N'Trần Tấn', N'Phong', N'Quận 9', N'0825040559', CAST(N'2022-05-03' AS Date), N'n19dccn140@student.ptithcm.edu.vn', 62)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (1037, N'Trần Tấn', N'Phong', N'Quận 9', N'0825040559', CAST(N'2022-05-03' AS Date), N'ducmanhlai22@gmail.com', 1060)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (1038, N'Trần Tấn', N'Phong', N'Quận 9', N'0825040559', CAST(N'2004-02-17' AS Date), N'n19dccn140@student.ptithcm.edu.vn', 1063)
INSERT [dbo].[KHACHHANG] ([MAKH], [HO], [TEN], [DIACHI], [SDT], [NGSINH], [EMAIL], [MATK]) VALUES (1039, N'Nguyễn Nghiên', N'Trinh', N'Quận Thủ Đức', N'0825040559', CAST(N'2022-01-03' AS Date), N'n19dccn140@student.ptithcm.edu.vn', 1068)
SET IDENTITY_INSERT [dbo].[KHACHHANG] OFF
SET IDENTITY_INSERT [dbo].[LOAI] ON 

INSERT [dbo].[LOAI] ([MALOAI], [TENLOAI]) VALUES (1, N'Sữa tươi')
INSERT [dbo].[LOAI] ([MALOAI], [TENLOAI]) VALUES (2, N'Nước Khoáng')
INSERT [dbo].[LOAI] ([MALOAI], [TENLOAI]) VALUES (3, N'Đồ uống có cồn')
INSERT [dbo].[LOAI] ([MALOAI], [TENLOAI]) VALUES (4, N'Nước ngọt có ga')
INSERT [dbo].[LOAI] ([MALOAI], [TENLOAI]) VALUES (5, N'Nước ngọt không có ga')
INSERT [dbo].[LOAI] ([MALOAI], [TENLOAI]) VALUES (8, N'Phong đẹp trai vl')
SET IDENTITY_INSERT [dbo].[LOAI] OFF
SET IDENTITY_INSERT [dbo].[NHANVIEN] ON 

INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [SDT], [DIACHI], [GIOITINH], [EMAIL], [CMND], [NGSINH], [LUONG], [TRANGTHAI], [MATK], [MAQL]) VALUES (2, N'Trần Tấn', N'Phong', N'0825040559', N'Quận 10', 1, N'trantanphong2000@gmail.com', N'123456789', CAST(N'1900-01-01' AS Date), 150000000, 1, 4, 4)
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [SDT], [DIACHI], [GIOITINH], [EMAIL], [CMND], [NGSINH], [LUONG], [TRANGTHAI], [MATK], [MAQL]) VALUES (3, N'Nguyễn Nghiêm', N'Trinh', N'0123456789', N'Quận 9', 1, N'n19dccn140@gmail.com', N'123547868', CAST(N'2001-06-15' AS Date), 40000000, 1, 5, 4)
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [SDT], [DIACHI], [GIOITINH], [EMAIL], [CMND], [NGSINH], [LUONG], [TRANGTHAI], [MATK], [MAQL]) VALUES (4, N'Admin', N'Admin', N'0967533854', N'Quận 9', 1, N'mrphong2000@gmail.com', N'233314756', CAST(N'2000-06-14' AS Date), 50000000, 1, 1, 4)
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [SDT], [DIACHI], [GIOITINH], [EMAIL], [CMND], [NGSINH], [LUONG], [TRANGTHAI], [MATK], [MAQL]) VALUES (5, N'Phạm Quang Phước', N'Hiếu', N'0154812054', N'Quận Thủ Đức', 1, N'adasdas@gmail.com', N'212458412', CAST(N'2001-06-14' AS Date), 50000000, 1, 3, 4)
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [SDT], [DIACHI], [GIOITINH], [EMAIL], [CMND], [NGSINH], [LUONG], [TRANGTHAI], [MATK], [MAQL]) VALUES (1030, N'Trần Tấn', N'Đẹp', N'0825040559', N'Quận 9', 1, N'trantanphong@gmail.com', N'2333333333', CAST(N'1900-01-01' AS Date), 5000000, 0, 1066, 4)
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [SDT], [DIACHI], [GIOITINH], [EMAIL], [CMND], [NGSINH], [LUONG], [TRANGTHAI], [MATK], [MAQL]) VALUES (1032, N'Trần Tấn', N'Mạnh', N'0825040559', N'Quận 10', 1, N'n19dccn140@student.ptithcm.edu.vn', N'2333333333', CAST(N'1900-01-01' AS Date), 500000, 0, 1069, 4)
SET IDENTITY_INSERT [dbo].[NHANVIEN] OFF
SET IDENTITY_INSERT [dbo].[PhanQuyen] ON 

INSERT [dbo].[PhanQuyen] ([MAPQ], [Quyen]) VALUES (1, N'Quản lý')
INSERT [dbo].[PhanQuyen] ([MAPQ], [Quyen]) VALUES (2, N'Nhân Viên')
INSERT [dbo].[PhanQuyen] ([MAPQ], [Quyen]) VALUES (3, N'Khách Hàng')
SET IDENTITY_INSERT [dbo].[PhanQuyen] OFF
SET IDENTITY_INSERT [dbo].[PHIEUNHAP] ON 

INSERT [dbo].[PHIEUNHAP] ([MAPN], [NGAYNHAP], [MANV]) VALUES (1, CAST(N'2022-05-28' AS Date), 2)
INSERT [dbo].[PHIEUNHAP] ([MAPN], [NGAYNHAP], [MANV]) VALUES (2, CAST(N'2022-05-28' AS Date), 2)
INSERT [dbo].[PHIEUNHAP] ([MAPN], [NGAYNHAP], [MANV]) VALUES (3, CAST(N'2022-05-28' AS Date), 2)
INSERT [dbo].[PHIEUNHAP] ([MAPN], [NGAYNHAP], [MANV]) VALUES (4, CAST(N'2022-05-28' AS Date), 2)
INSERT [dbo].[PHIEUNHAP] ([MAPN], [NGAYNHAP], [MANV]) VALUES (5, CAST(N'2022-05-28' AS Date), 2)
INSERT [dbo].[PHIEUNHAP] ([MAPN], [NGAYNHAP], [MANV]) VALUES (6, CAST(N'2022-05-28' AS Date), 2)
INSERT [dbo].[PHIEUNHAP] ([MAPN], [NGAYNHAP], [MANV]) VALUES (11, CAST(N'2022-06-05' AS Date), 2)
INSERT [dbo].[PHIEUNHAP] ([MAPN], [NGAYNHAP], [MANV]) VALUES (12, CAST(N'2022-06-06' AS Date), 2)
INSERT [dbo].[PHIEUNHAP] ([MAPN], [NGAYNHAP], [MANV]) VALUES (13, CAST(N'2022-06-06' AS Date), 2)
INSERT [dbo].[PHIEUNHAP] ([MAPN], [NGAYNHAP], [MANV]) VALUES (15, CAST(N'2022-06-07' AS Date), 2)
SET IDENTITY_INSERT [dbo].[PHIEUNHAP] OFF
SET IDENTITY_INSERT [dbo].[SANPHAM] ON 

INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (1, N'Sữa Vilamilk hương dâu 122', N'sữa tươi nguyên chất hương vị dâu', 13, 1, 1, N'6-lon-nuoc-ngot-7-up-vi-chanh-330ml-202006131621577309.jpg', 10000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (2, N'Nước khoáng lavi', N'nước khoáng nguyên chất cung cấp đầu đủ các ion khoáng', 18, 2, 5, N'lavie-1500ml.png', 10000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (3, N'CoCaCoLa 300ml', N'Nước ngọt có ga', 46, 4, 2, N'162427567193110150521-LON-Loc-6-lon-nuoc-khung-calo-chanh-330ml.jpg', 11000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (4, N'CoCaCoLa 1,5L', N'Nước ngọt có gas loại lớn', 42, 4, 2, N'nuoc-ngot-co-gas-7up-pet-15l-2-org.jpg', 20000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (5, N'Bia Tiger', N'Bia đồ uống có cồn', 191, 3, 6, N'8934822101336.jpg', 18000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (6, N'Bia HaLiKen', N'Có cồn', 156, 3, 6, N'bia-tiger-330ml-202012151339189979.jpg', 15000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (7, N'Sữa tươi vị dâu 123', N'sữa', 112, 1, 1, N'FinoPackshot_Coduong-1_540x440_acf_cropped.png', 7000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (8, N'Sữa đậu nành vi na soi', N'sữa đậu nàn', 150, 1, 2, N'sua-bich-vinamilk-co-duong-b_eeaab361818e4c66ac34ee6c4e03193f.png', 5000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (9, N'Bia tươi', N'bia', 85, 3, 5, N'bia-tiger-330ml-202012151339189979.jpg', 10000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (10, N'Nươc Khoáng Vec', N'nước khoáng', 99, 2, 2, N'162427567193110150521-LON-Loc-6-lon-nuoc-khung-calo-chanh-330ml.jpg', 10000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (11, N'Sữa tươi vilamilk không đường', N'sữa không đường', 100, 1, 3, N'FinoPackshot_Coduong-1_540x440_acf_cropped.png', 10000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (13, N'Nước ép nitri hương táo', N'nước ep', 50, 5, 4, N'806850103620_nuoc-viva.png', 10000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (14, N'Sinh Tố Bơ', N'sinh tố', 47, 5, 4, N'sua-bich-vinamilk-co-duong-b_eeaab361818e4c66ac34ee6c4e03193f.png', 10000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (1031, N'Sữa Chua vinamilk1', N'Sưa chua Vinamilk', 0, 1, 1, N'sua-bich-vinamilk-co-duong-b_eeaab361818e4c66ac34ee6c4e03193f.png', 50000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (1032, N'Sữa Chua vinamilk2', N'Sưa chua Vinamilk', 0, 1, 1, N'nuoc-ngot-co-gas-7up-pet-15l-2-org.jpg', 50000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (1044, N'Sữa Chua vinamilk3', N'Sưa chua Vinamilk', 0, 1, 1, N'nuoc-khoang-lavie-chai-750ml.png', 50000)
INSERT [dbo].[SANPHAM] ([MASP], [TENSP], [MOTA], [SLTON], [MALOAI], [MAHANG], [HINHANH], [GIA]) VALUES (1046, N'Sữa Chua vinamilk45', N'Sưa chua Vinamilk', 0, 1, 1, N'6-lon-nuoc-ngot-7-up-vi-chanh-330ml-202006131621577309.jpg', 50000)
SET IDENTITY_INSERT [dbo].[SANPHAM] OFF
SET IDENTITY_INSERT [dbo].[TaiKhoan] ON 

INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (1, N'TaansPhong', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 1, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (2, N'phong', N'5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (3, N'phuochieu', N'5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 2, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (4, N'TanPhong', N'5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 2, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (5, N'NghiemTrinh', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 2, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (6, N'vanhong123', N'4654d793972c3b6a1d48fb0ab58d9cb0de46c3d33d605f9222c283dfaa12d420                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (7, N'myhanh14', N'03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (8, N'nhuthang12', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (9, N'nguyennhung', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (10, N'manhhung', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (11, N'thanhhung', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (12, N'aithuy1', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (13, N'nhubeo', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (14, N'minhbeo', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (15, N'thanhdanh', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (44, N'aithuy', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (57, N'phongdeptrai', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (58, N'phongdeptrai1', N'03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (60, N'phongdeptrai5', N'5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 2, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (62, N'phongdeptrai9', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (1060, N'manh', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (1062, N'phongdeptrai12', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 2, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (1063, N'phongdeptrai32', N'5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 3, 0)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (1065, N'phongdhkt146', N'54084b071a5e7533530094d0c950272e20af915bc06811ff8e61ae0f6ef64c79                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 2, 1)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (1066, N'phongdhkt142', N'd6bf0dee1ebc3497d8bd0464adab6d1d665f55a0092429ce7a092883f11fb848                                                                                                                                                                                                                                                                                                                                                                                                                                                    ', 2, 1)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (1068, N'phongdeptrai33', N'06e3bc3645d226ab669ff28dae7a343746ef288d37e0b7178e177f052f7e6e87', 3, 1)
INSERT [dbo].[TaiKhoan] ([MATK], [TenDN], [MK], [MAPQ], [DOI]) VALUES (1069, N'phongdhkt141', N'b08a8d6a28b1fd4142c025b5303b15d0a90399d726deb6823899311328287092', 2, 1)
SET IDENTITY_INSERT [dbo].[TaiKhoan] OFF
/****** Object:  Index [IX_KHACHHANG]    Script Date: 7/9/2022 11:31:21 AM ******/
ALTER TABLE [dbo].[KHACHHANG] ADD  CONSTRAINT [IX_KHACHHANG] UNIQUE NONCLUSTERED 
(
	[MATK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [IX_NHANVIEN]    Script Date: 7/9/2022 11:31:21 AM ******/
ALTER TABLE [dbo].[NHANVIEN] ADD  CONSTRAINT [IX_NHANVIEN] UNIQUE NONCLUSTERED 
(
	[MATK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [US_TENSP]    Script Date: 7/9/2022 11:31:21 AM ******/
ALTER TABLE [dbo].[SANPHAM] ADD  CONSTRAINT [US_TENSP] UNIQUE NONCLUSTERED 
(
	[TENSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UK_TenDN]    Script Date: 7/9/2022 11:31:21 AM ******/
ALTER TABLE [dbo].[TaiKhoan] ADD  CONSTRAINT [UK_TenDN] UNIQUE NONCLUSTERED 
(
	[TenDN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CTDDH]  WITH CHECK ADD  CONSTRAINT [FK_CTDDH_SANPHAM] FOREIGN KEY([MASP])
REFERENCES [dbo].[SANPHAM] ([MASP])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CTDDH] CHECK CONSTRAINT [FK_CTDDH_SANPHAM]
GO
ALTER TABLE [dbo].[CTDDH]  WITH CHECK ADD  CONSTRAINT [FK_CTPHIEUDAT_PHIEUDAT] FOREIGN KEY([MADDH])
REFERENCES [dbo].[DonDH] ([MADDH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTDDH] CHECK CONSTRAINT [FK_CTPHIEUDAT_PHIEUDAT]
GO
ALTER TABLE [dbo].[CTHD]  WITH NOCHECK ADD  CONSTRAINT [FK_CTHD_HOADON] FOREIGN KEY([MAHD])
REFERENCES [dbo].[HOADON] ([MAHD])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CTHD] NOCHECK CONSTRAINT [FK_CTHD_HOADON]
GO
ALTER TABLE [dbo].[CTHD]  WITH CHECK ADD  CONSTRAINT [FK_CTHD_SANPHAM1] FOREIGN KEY([MASP])
REFERENCES [dbo].[SANPHAM] ([MASP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTHD] CHECK CONSTRAINT [FK_CTHD_SANPHAM1]
GO
ALTER TABLE [dbo].[CTPN]  WITH CHECK ADD  CONSTRAINT [FK_CTPN_PHIEUNHAP] FOREIGN KEY([MAPN])
REFERENCES [dbo].[PHIEUNHAP] ([MAPN])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CTPN] CHECK CONSTRAINT [FK_CTPN_PHIEUNHAP]
GO
ALTER TABLE [dbo].[CTPN]  WITH CHECK ADD  CONSTRAINT [FK_CTPN_SANPHAM] FOREIGN KEY([MASP])
REFERENCES [dbo].[SANPHAM] ([MASP])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CTPN] CHECK CONSTRAINT [FK_CTPN_SANPHAM]
GO
ALTER TABLE [dbo].[DonDH]  WITH CHECK ADD  CONSTRAINT [FK_DonDH_KHACHHANG] FOREIGN KEY([MAKH])
REFERENCES [dbo].[KHACHHANG] ([MAKH])
GO
ALTER TABLE [dbo].[DonDH] CHECK CONSTRAINT [FK_DonDH_KHACHHANG]
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD  CONSTRAINT [FK_HOADON_NHANVIEN] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[HOADON] CHECK CONSTRAINT [FK_HOADON_NHANVIEN]
GO
ALTER TABLE [dbo].[KHACHHANG]  WITH CHECK ADD  CONSTRAINT [FK_KHACHHANG_TaiKhoan] FOREIGN KEY([MATK])
REFERENCES [dbo].[TaiKhoan] ([MATK])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[KHACHHANG] CHECK CONSTRAINT [FK_KHACHHANG_TaiKhoan]
GO
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD  CONSTRAINT [FK_NHANVIEN_NHANVIEN] FOREIGN KEY([MAQL])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[NHANVIEN] CHECK CONSTRAINT [FK_NHANVIEN_NHANVIEN]
GO
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD  CONSTRAINT [FK_NHANVIEN_TaiKhoan] FOREIGN KEY([MATK])
REFERENCES [dbo].[TaiKhoan] ([MATK])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[NHANVIEN] CHECK CONSTRAINT [FK_NHANVIEN_TaiKhoan]
GO
ALTER TABLE [dbo].[PHIEUNHAP]  WITH CHECK ADD  CONSTRAINT [FK_PHIEUNHAP_NHANVIEN] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[PHIEUNHAP] CHECK CONSTRAINT [FK_PHIEUNHAP_NHANVIEN]
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD  CONSTRAINT [FK_SANPHAM_HANG] FOREIGN KEY([MAHANG])
REFERENCES [dbo].[HANG] ([MAHANG])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[SANPHAM] CHECK CONSTRAINT [FK_SANPHAM_HANG]
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD  CONSTRAINT [FK_SANPHAM_LOAI] FOREIGN KEY([MALOAI])
REFERENCES [dbo].[LOAI] ([MALOAI])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[SANPHAM] CHECK CONSTRAINT [FK_SANPHAM_LOAI]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_PhanQuyen] FOREIGN KEY([MAPQ])
REFERENCES [dbo].[PhanQuyen] ([MAPQ])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_PhanQuyen]
GO
ALTER TABLE [dbo].[CTDDH]  WITH CHECK ADD  CONSTRAINT [CK_CTDDH_SL] CHECK  (([SL]>(0)))
GO
ALTER TABLE [dbo].[CTDDH] CHECK CONSTRAINT [CK_CTDDH_SL]
GO
ALTER TABLE [dbo].[CTHD]  WITH CHECK ADD  CONSTRAINT [CK_CTHD_SL] CHECK  (([SL]>(0)))
GO
ALTER TABLE [dbo].[CTHD] CHECK CONSTRAINT [CK_CTHD_SL]
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD  CONSTRAINT [CK_SL] CHECK  (([SLTON]>=(0)))
GO
ALTER TABLE [dbo].[SANPHAM] CHECK CONSTRAINT [CK_SL]
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD  CONSTRAINT [CK_SLTON] CHECK  (([SLTON]>=(0)))
GO
ALTER TABLE [dbo].[SANPHAM] CHECK CONSTRAINT [CK_SLTON]
GO
USE [master]
GO
ALTER DATABASE [QLNDC] SET  READ_WRITE 
GO
