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
	href="<c:url value='/resource/KhachHang/GioHang.css'/>">
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
		<div class="step-wizard">
				<ul class="step-wizard-list">
					<li class="step-wizard-item current-item"><span
						class="progress-count">1</span> <span class="progress-label">CHỌN
							HÀNG</span></li>
					<li class="step-wizard-item"><span class="progress-count">2</span>
						<span class="progress-label">XÁC NHẬN THÔNG TIN</span></li>
					<li class="step-wizard-item"><span class="progress-count">3</span>
						<span class="progress-label">XÁC NHẬN ĐƠN HÀNG</span></li>
					<li class="step-wizard-item "><span class="progress-count">4</span>
						<span class="progress-label">THANH TOÁN</span></li>
				</ul>

			</div>
		<div class="card">
			<div class="card-header">
				<h2>Giỏ hàng</h2>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered m-0">
						<thead>
							<tr>
								<!-- Set columns width -->
								<th class="text-center py-3 px-4" style="min-width: 400px;">Sản
									phẩm</th>
								<th class="text-right py-3 px-4" style="width: 100px;">Giá</th>
								<th class="text-center py-3 px-4" style="width: 120px;">Số
									lượng</th>
								<th class="text-center align-middle py-3 px-0"
									style="width: 40px;"></th>
							</tr>
						</thead>
						<tbody>
							<form action="KhachHang/GioHang.htm" , method="POST" id="form1">
								<c:forEach var="lsp" items="${listSanPham}">
									<tr>
										<td class="p-4">
											<div class="media align-items-center">

												<div class="media-body">${lsp.TENSP}</div>
											</div>
										</td>
										<td class="text-right font-weight-semibold align-middle p-4">${lsp.GIA}</td>
										<td class="align-middle p-4"><input type="number"
											name="${lsp.MASP}" class="form-control text-center"
											value="${listSL.get(listSanPham.indexOf(lsp))}"></td>
										<td class="text-center align-middle px-0"><a
											href="KhachHang/GioHang/${lsp.getMASP()}.htm"
											class="shop-tooltip close float-none text-danger" title=""
											data-original-title="Remove">×</a></td>
									</tr>
								</c:forEach>
							</form>
						</tbody>
					</table>
				</div>
				<!-- / Shopping cart table -->

				<div class="float-right" style="margin-top: 20px;">
					<button type="submit"
						class="btn btn-lg btn-default md-btn-flat mt-2 mr-3" form="form1"
						name="btnTC">Về trang chủ</button>
					<button type="submit" class="btn btn-lg btn-primary mt-2"
						form="form1" name="btnDH">Bước Kế</button>
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