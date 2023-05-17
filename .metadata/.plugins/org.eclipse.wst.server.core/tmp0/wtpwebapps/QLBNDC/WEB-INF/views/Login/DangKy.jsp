<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/tags/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đăng ký</title>
<base href="${pageContext.servletContext.contextPath }/" />
<!-- Latest compiled and minified CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!--Bootstrap-->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- Latest compiled JavaScript -->
<link rel="stylesheet"
	href="<c:url value ='/resource/Login/css/loginstyle.css'/>" />
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
					<li class="nav-item active"><a href="#" class="nav-link">Trang
							chủ</a></li>
					<li class="nav-item active"><a href="Login/Login.htm"
						class="nav-link">Tài khoản</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card border-0 shadow rounded-3 my-5">
					<div class="card-body p-4 p-sm-5">
						<h5 class="card-title text-center mb-5 fw-light fs-5">Đăng ký</h5>
						<form action="Login/TaoTaiKhoan.htm?${message}" method="POST">
							<div class="form-floating mb-3">
								<spring:bind path="khachHang.HO">
									<input name ="HO" type="text" class="form-control" id="floatingInput"
										placeholder="name@example.com">
								</spring:bind>
								<label for="floatingInput">Họ</label>
							</div>
							<div class="form-floating mb-3">
								<spring:bind path="khachHang.TEN">
									<input name ="TEN" type="text" class="form-control" id="floatingInput"
										placeholder="name@example.com">
								</spring:bind>
								<label for="floatingInput">Tên</label>
							</div>
							<div class="form-floating mb-3">
									<input type="date" name ="ngsinh" class="form-control" id="floatingInput"
										placeholder="name@example.com">
								<label for="floatingInput">Ngày Sinh</label>
							</div>
							<div class="form-floating mb-3">
								<spring:bind path="khachHang.DIACHI">
									<input name ="DIACHI" type="text" class="form-control" id="floatingInput"
										placeholder="name@example.com">
								</spring:bind>
								<label for="floatingInput">Địa Chỉ</label>
							</div>
							<div class="form-floating mb-3">
								<spring:bind path="khachHang.SDT">
									<input name="SDT" type="text" class="form-control" id="floatingInput"
										placeholder="name@example.com"/>
								</spring:bind>
								<label for="floatingInput">Số Điện Thoại</label>
							</div>
							<div class="form-floating mb-3">
								<spring:bind path="khachHang.EMAIL">
									<input name ="EMAIL" type="text" class="form-control" id="floatingInput"
										placeholder="name@example.com">
								</spring:bind>
								<label for="floatingInput">EMAIL</label>
							</div>
							<div class="form-floating mb-3">
								<spring:bind path="taiKhoan.TenDN">
									<input name="TenDN" type="text" class="form-control" id="floatingInput"
										placeholder="name@example.com">
								</spring:bind>
								<label for="floatingInput">Tên Đăng Nhập</label>
							</div>
							<div class="form-floating mb-3">
									<input type="password" name="pwd" class="form-control" id="floatingInput"
										placeholder="name@example.com">
								<label for="floatingInput">Mật Khẩu</label>
							</div>
							<div class="form-floating mb-3">
									<input type="password" name="pwd1" class="form-control" id="floatingInput"
										placeholder="name@example.com">
								<label for="floatingInput">Nhập Lại Mật Khẩu</label>
							</div>
							<div class="d-grid btn-login">
								<button
									class="btn btn-outline-primary btn-login text-uppercase fw-bold"
									type="submit">Tạo tài khoản</button>
							</div>
						</form>
					</div>
				</div>
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