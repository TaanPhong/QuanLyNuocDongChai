<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<html lang="en">
<head>
<meta charset="utf-8" />
<title>Quản lý</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<base href="${pageContext.servletContext.contextPath }/" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resource/QuanLy/css/ThongTinCaNhan.css'/>" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" />
</head>

<body>
	<div class="container-fluid">
		<aside>
			<!--  logo  -->
			<div class="top">
				<div class="logo">
					<img
						src="<c:url value='/resource/QuanLy/image/Vinmart_logo_sieu_thi (1).png' />" />
				</div>

				<div class="close" id="close-btn">
					<span class="material-icons-sharp">close</span>
				</div>
			</div>

			<div class="sidebar">
				<a href="QuanLy/TrangChu.htm"> <span
					class="material-icons-sharp">home</span>
					<h3>Trang chủ</h3>
				</a> <a href="QuanLy/SanPham.htm"> <span
					class="material-icons-sharp">local_drink</span>
					<h3>Sản phẩm</h3>
				</a> <a href="QuanLy/KhachHang.htm"> <span
					class="material-icons-sharp">people_alt</span>
					<h3>Khách hàng</h3>
				</a> <a href="QuanLy/NhanVien.htm"> <span
					class="material-icons-sharp">folder_shared</span>
					<h3>Nhân viên</h3>
				</a> <a href="QuanLy/NhapKho.htm"> <span
					class="material-icons-sharp">add_business</span>
					<h3>Nhập kho</h3>
				</a> <a href="QuanLy/TonKho.htm"> <span class="material-icons-sharp">store</span>
					<h3>Tồn kho</h3>
				</a> <a href="QuanLy/DoanhSo.htm"> <span
					class="material-icons-sharp">inventory</span>
					<h3>Doanh số</h3>
				</a> <a href="QuanLy/Loai.htm"> <span class="material-icons-sharp">card_travel</span>
					<h3>Loại Sản Phẩm</h3>
				</a> </a> <a href="QuanLy/Hang.htm"> <span class="material-icons-sharp">fastfood</span>
					<h3>Hãng Sản Phẩm</h3>
				</a><a href="#"> <span class="material-icons-sharp">key</span>
					<h3>Đổi mật khuẩu</h3>
				</a>
			</div>
		</aside>

		<div class="main">
			<%@include file="/WEB-INF/views/include/header.jsp"%>
			<div class="content">
				<div class="table-title">
					<h3>Thông Tin Cá Nhân</h3>
				</div>
				<table class="table-fill">
					<thead>
						<tr>
							<th class="text-left">Quản Lý</th>
						</tr>
					</thead>
					<tbody class="table-hover">
						<tr>
							<td class="text-left">Họ: ${nv.HO}</td>
						</tr>
						<tr>
							<td class="text-left">Tên: ${nv.TEN}</td>
						</tr>
						<tr>
							<td class="text-left">Ngày Sinh: ${nv.NGSINH}</td>
						</tr>
						<tr>
							<td class="text-left">Địa Chỉ: ${nv.DIACHI}</td>
						</tr>
						<tr>
							<td class="text-left">Số Điện Thoại: ${nv.SDT}</td>
						</tr>
					</tbody>
				</table>

			</div>
			<button type="button" class="btn btn-success"
				style="margin-left: 800px; margin-top: 50px;">
				<a href="Login/DoiMK.htm">Đổi Mật Khẩu</a>
			</button>
		</div>
	</div>

</body>