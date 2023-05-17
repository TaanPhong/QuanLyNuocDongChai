<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/tags/taglib.jsp"%>
<!doctype html>
<html lang="en">

<head>
<title>Nhân Vien</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<base href="${pageContext.servletContext.contextPath }/" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js">
	
</script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<c:url value ='/resource/NhanVien/css/Thong_Tin.css'/>" />
</head>


<body>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
		<div class="container-fluid">
			<a class="navbar-branch" href="#"> <img
				src="<c:url value='/resource/NhanVien/img/logo-vinmart.png' />"
				height="100">
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link "
						href="NhanVien/HoaDon.htm">Hóa Đơn</a></li>
					<li class="nav-item"><a class="nav-link"
						href="NhanVien/PhieuNhap.htm">Phiếu Nhập</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="NhanVien/XemThongTinCaNhan.htm">Thông tin cá nhân</a></li>
					<li class="nav-item"><a class="nav-link"
						href="Login/LogOut.htm">Đăng Xuất</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Optional JavaScript -->
	<div class="main">
		<h1>
			<span class="yellow">${hoTen}</pan>
		</h1>

		<table class="container">
			<thead>
				<tr>
					<th><h1>${hoTen}</h1></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Họ: ${nhanVien.HO}</td>
				</tr>
				<tr>
					<td>Tên: ${nhanVien.TEN}</td>
				</tr>
				<tr>
					<td>Email: ${nhanVien.EMAIL}</td>
				</tr>
				<tr>
					<td>Lương: ${nhanVien.LUONG}</td>
				</tr>
				<tr>
					<td>Ngày sinh: ${nhanVien.NGSINH}</td>
				</tr>
				<tr>
					<td>Số điện thoại: ${nhanVien.SDT}</td>
				</tr>
			</tbody>
		</table>
		<button type="button" class="btn btn-success"
			style="margin-left: 1170px; margin-top: 50px; margin-bottom: 50px; padding: 15px; font-size: 20px; background: yellow;">
			<a href="Login/DoiMK.htm">Đổi Mật Khẩu</a>
		</button>
	</div>
</body>
</html>