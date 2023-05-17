<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/tags/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
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
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js">
	
</script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<c:url value ='/resource/NhanVien/css/Hoa_Don.css'/>" />
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
					<li class="nav-item"><a class="nav-link active"
						href="NhanVien/HoaDon.htm">Hóa Đơn</a></li>
					<li class="nav-item"><a class="nav-link"
						href="NhanVien/PhieuNhap.htm">Phiếu Nhập</a></li>
					<li class="nav-item"><a class="nav-link"
						href="NhanVien/XemThongTinCaNhan.htm">Thông Tin Cá Nhân</a></li>
					<li class="nav-item"><a class="nav-link"
						href="Login/LogOut.htm">Đăng Xuất</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Optional JavaScript -->

	<!-- Giao diện  -->
	<div class="main">
		<!-- Shopping cart table -->
		<div class="card">
			<div class="card-header">
				<h3>Hóa Đơn</h3>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<jsp:useBean id="pagedListHolder" scope="request"
						type="org.springframework.beans.support.PagedListHolder" />
					<c:url value="NhanVien/HieuChinhHoaDon.htm" var="pagedLink">
						<c:param name="p" value="~" />
					</c:url>
					<table class="table table-bordered m-0">
						<thead>
							<tr>
								<!-- Set columns width -->
								<th class="text-center py-3 px-4" style="min-width: 100px;">Mã
									Hóa Đơn</th>
								<th class="text-right py-3 px-4" style="width: 400px;">Ngày
									Lập</th>
								<th class="text-right py-3 px-4" style="width: 200px;">Hiệu
									Chỉnh</th>
								<th class="text-right py-3 px-4" style="width: 100px;">Xóa</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="pd" items="${pagedListHolder.pageList}">
								<tr>
									<td class="p-4">${pd.getMAHD()}</td>
									<td class="text-right font-weight-semibold align-middle p-4">${pd.getNGAYLAPHD()}</td>

									<td class="text-right font-weight-semibold align-middle p-4"><form:form
											action="NhanVien/HieuChinhHoaDon/${pd.getMAHD()}.htm?linkEdit"
											method="POST">
											<button type="submit" class="btn btn-warning">
												<span class="material-icons-sharp">edit_note</span>
											</button>
										</form:form></td>
									<td class="text-right font-weight-semibold align-middle p-4"><form:form
											action="NhanVien/HieuChinhHoaDon/${pd.getMAHD()}.htm?linkDelete"
											method="POST">
											<%-- 											<input type="hidden" name="username" value="${u.username }" /> --%>
											<button type="submit" class="btn btn-danger">
												<span class="material-icons-sharp">delete</span>
											</button>
										</form:form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div>
						<tg:paging pagedListHolder="${pagedListHolder}"
							pagedLink="${pagedLink}" />
					</div>
				</div>
			</div>
		</div>
		<!-- 	</div> -->
		<!--footer-->
		<div class="recentCustomers">
			<div class="row" style="height: 50px;">
				<button id="searchProduct" class="btn btn-secondary" type="button">
					<a href="NhanVien/HoaDon.htm">Thêm Mới</a>
				</button>
				<button id="searchProduct" class="btn btn-secondary active"
					style="margin-left: 20px;" type="button">
					<a href="NhanVien/HieuChinhHoaDon.htm">Hiệu Chỉnh</a>
				</button>
			</div>
			<div class="row" style="margin-top: 20px; margin-bottom: 20px;">
				<form action="NhanVien/HieuChinhHoaDon.htm" method="POST">
					<input name="masp" type="number" class="form-control"
						id="floatingInput" placeholder="Nhập mã hóa đơn"
						style="font-size: 18px !important; width: 200px; height: 50px; margin-right: 0px;">
					<button name="btnsearch" id="searchProduct"
						class="btn btn-outline-success" type="submit"
						style="height: 50px;">Tìm Kiếm</button>
				</form>
			</div>
		</div>
	</div>

	<!-- end khách hàng -->
</body>

</html>