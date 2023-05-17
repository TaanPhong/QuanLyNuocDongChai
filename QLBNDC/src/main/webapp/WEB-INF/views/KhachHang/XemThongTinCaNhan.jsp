<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/tags/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<!--  This file has been downloaded from bootdey.com @bootdey on twitter -->
<!--  All snippets are MIT license http://bootdey.com/license -->
<title>Giỏ hàng</title>
<base href="${pageContext.servletContext.contextPath }/" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="<c:url value='/resource/KhachHang/XemThongTinCaNhan.css'/>">
</head>
<body>
	<!--navigation-->
	<nav class="navbar navbar-expand-md navbar-dark bg-danger sticky-top"
		margin="auto">
		<div class="container-fluid">
			<a href="#" class="navbar-brand"> <img src="asset/logo.png"
				alt="">
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarReponsive">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarReponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a href="KhachHang/TrangChu.htm"
						class="nav-link">Trang chủ</a></li>
					<li class="nav-item active"><a href="KhachHang/GioHang.htm"
						class="nav-link">Giỏ hàng</a></li>
					<li class="nav-item active"><a href="KhachHang/XemThongTinCaNhan.htm"
						class="nav-link">Thông tin cá nhân</a></li>
					<li class="nav-item active"><a href="KhachHang/LichSuMua.htm"
						class="nav-link">Lịch sử mua hàng</a></li>
					<li class="nav-item active"><a href="Login/LogOut.htm"
						class="nav-link">Đăng Xuất</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container px-3 my-5 clearfix">
		<!-- Shopping cart table -->
		<div class="card">
			<div class="content">
				<div class="table-title">
					<h3>Thông Tin Cá Nhân</h3>
				</div>
				<table class="table-fill">
					<thead>
						<tr>
							<th class="text-left">Khách Hàng</th>
						</tr>
					</thead>
					<tbody class="table-hover">
						<tr>
							<td class="text-left">Họ: ${kh.HO}</td>
						</tr>
						<tr>
							<td class="text-left">Tên: ${kh.TEN}</td>
						</tr>
						<tr>
							<td class="text-left">Ngày Sinh: ${kh.NGSINH}</td>
						</tr>
						<tr>
							<td class="text-left">Địa Chỉ: ${kh.DIACHI}</td>
						</tr>
						<tr>
							<td class="text-left">Số Điện Thoại: ${kh.SDT}</td>
						</tr>
					</tbody>
				</table>
				<button type="button" class="btn btn-success"
					style="margin-left: 525px; margin-top: 20px; margin-bottom: 20px;">
					<a href="Login/DoiMK.htm">Đổi Mật Khẩu</a>
				</button>
				<button type="button" class="btn btn-success"
					style="margin-left: 20px; margin-top: 20px; margin-bottom: 20px;">
					<a href="KhachHang/SuaThongTinCaNhan.htm">Sửa thông tin cá nhân</a>
				</button>
			</div>

			<!-- / Shopping cart table -->


		</div>
	</div>
	</div>
	<!--footer-->
	<footer>
		<div class="container-fluid padding"
			style="background-color: #3f3f3f; color: #d5d5d5; padding-top: 2rem;">
			<div class="row text-center">
				<div class="col-md-4">

					<p>123-456-789</p>
					<p>Email : winmart@gmail.com</p>
					<p>512 Quốc lộ 13</p>
					<p>Bình Thạnh,TP.Hồ Chí Minh</p>
				</div>
				<div class="col-md-4">

					<p>Hỗ trợ khách hàng</p>
					<p>Thông tin về công ty</p>
					<p>Tuyển dụng việc làm</p>
					<p>Khiếu nại</p>
				</div>
				<div class="col-md-4">

					<p>Facebook</p>
					<p>Về trang chủ</p>

				</div>
			</div>
		</div>
	</footer>
</body>
</html>