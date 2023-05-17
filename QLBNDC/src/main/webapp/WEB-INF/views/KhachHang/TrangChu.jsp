<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/tags/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Winmart</title>
<!--Bootstrap-->
<base href="${pageContext.servletContext.contextPath }/" />
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
<link rel="stylesheet"
	href="<c:url value ='/resource/KhachHang/TrangChu.css'/>" />
<!--font roboto-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap"
	rel="stylesheet">
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
	<!--image slider-->
	<div class="slides" class="carousel slide" data-ride="carousel"
		data-interval="2000">
		<ul class="carousel-indicators">
			<li data-target="#slides" data-slide-to="0" class="active"></li>
			<li data-target="#slides" data-slide-to="1"></li>
			<li data-target="#slides" data-slide-to="2"></li>
		</ul>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="<c:url value ='/resource/Home/img/slide1.jpg'/>" alt="">
			</div>
			<div class="carousel-item">
				<img src="<c:url value ='/resource/Home/img/slide2.jpg'/>" alt="">
			</div>
			<div class="carousel-item">
				<img src="<c:url value ='/resource/Home/img/slide3.jpg'/>" alt="">
			</div>
		</div>
	</div>

	<!-- Related items section-->
	<section class="py-5 bg-light">
		<div class="container px-4 px-lg-5 mt-5">
			<h2 class="fw-bolder mb-4">Sản nước giải khát</h2>
			<c:if test="${test == 2}">
				<div id="toast1">
					<div class="toast1 toast--ThanhCong">
						<div class="toast__icon">
							<i class="fa-solid fa-circle-check"></i>
						</div>
						<div class="toast__body">
							<h3 class="toast__title">Thành Công</h3>
							<p class="toast__msg">${messgase}</p>
						</div>
						<div class="toast__close">
							<i class="fa-solid fa-circle-xmark"></i>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${test == 1}">
				<div id="toast1">
					<div class="toast1 toast--ThanhCong">
						<div class="toast__icon">
							<i class="fa-solid fa-circle-exclamation"></i>
						</div>
						<div class="toast__body">
							<h3 class="toast__title">Thất Bại</h3>
							<p class="toast__msg">${messgase}</p>
						</div>
						<div class="toast__close">
							<i class="fa-solid fa-do-not-enter"></i>
						</div>
					</div>
				</div>
			</c:if>
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach var="sp" items="${listSP}">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top" height="180"
								src="<c:url value ='/resource/Home/img/${sp.HINHANH}'/>"
								alt="..." />
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">${sp.TENSP}</h5>
									<div
										class="d-flex justify-content-center small text-warning mb-2">
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
									</div>
									<!-- Product price-->
									${sp.GIA}
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" padding-bottom="10px"
										href="KhachHang/XemChiTiet/${sp.MASP}.htm">Xem chi tiết</a>
								</div>
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto"
										href="KhachHang/ThemVaoGio/${sp.MASP}.htm">Thêm vào giỏ</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>

		</div>
		<!-- 		</div> -->
	</section>
	<!--footer-->
	<footer>
		<div class="container-fluid padding">
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