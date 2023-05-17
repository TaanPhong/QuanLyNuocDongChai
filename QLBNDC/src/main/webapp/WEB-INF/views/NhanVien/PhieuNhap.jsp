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
					<li class="nav-item"><a class="nav-link"
						href="NhanVien/HoaDon.htm">Hóa Đơn</a></li>
					<li class="nav-item"><a class="nav-link active"
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
				<h3>Phiếu Nhập</h3>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered m-0">
						<thead>
							<tr>
								<!-- Set columns width -->
								<th class="text-center py-3 px-4" style="min-width: 100px;">Sản
									phẩm</th>
								<th class="text-right py-3 px-4" style="width: 200px;">Số
									lượng</th>
								<th class="text-center py-3 px-4" style="width: 250px;">Giá</th>
								<th class="text-center align-middle py-3 px-0"
									style="width: 40px;"></th>
							</tr>
						</thead>
						<tbody>
							<form action="NhanVien/PhieuNhap.htm" , method="POST" id="form1">
								<c:forEach var="lsp" items="${listSP}">
									<tr>
										<td class="p-4">${lsp.getTENSP()}</td>
										<td class="align-middle p-4"><input type="number" name="SL_${lsp.getMASP()}"
											class="form-control text-center" value="${listSL.get(listSP.indexOf(lsp))}"></td>
										<td class="text-right font-weight-semibold align-middle p-4">
											<input type="number" class="form-control text-center" name="Gia_${lsp.getMASP()}"
											value="${listGia.get(listSP.indexOf(lsp))}">
										</td>
										<td class="text-center align-middle px-0">
										<a href="NhanVien/PhieuNhap/${lsp.getMASP()}.htm"
											class="shop-tooltip close float-none text-danger" title=""
											data-original-title="Remove">X</a></td>
									</tr>
								</c:forEach>
							</form>
						</tbody>
					</table>
				</div>

			</div>
		</div>
		<!-- 	</div> -->
		<!--footer-->
		<div class="recentCustomers">
			<div class="row" style="height: 50px;">
				<button id="searchProduct" class="btn btn-danger active"
					type="button">
					<a href="NhanVien/PhieuNhap.htm">Thêm Mới</a>
				</button>
				<button id="searchProduct" class="btn btn-danger" style="margin-left: 20px;"
					type="button">
					<a href="NhanVien/HieuChinhPhieuNhap.htm">Hiệu Chỉnh</a>
				</button>
				<!-- 			<button name="btnsearch" id="searchProduct" -->
				<!-- 				class="btn btn-outline-success" type="submit">Xóa</button> -->
			</div>
			<div class="row" style="margin-top: 30px;">
<!-- 				<form action="NhanVien/PhieuNhap.htm" method="POST"> -->
					<input name="masp" type="number" class="form-control" form="form1"
						id="floatingInput" placeholder="Nhập mã sản phẩm"
						style="font-size: 18px !important; width: 200px; height: 50px; margin-right: 10px;">
					<button name="btnsearch" id="searchProduct" form="form1"
						class="btn btn-warning" type="submit"
						style="height: 50px;">Tìm Kiếm</button>
<!-- 				</form> -->
			</div>
			<div class="row" style="margin-top: 20px;">
<!-- 				<form action="NhanVien/HoaDon.htm" method="POST"> -->
					<button name="btnthemsp" id="searchProduct" form="form1"
						class="btn btn-info" type="submit"
						style="height: 50px;">Thêm Vào Phiếu Nhập</button>
<!-- 				</form> -->
			</div>
			<div class="row" style="margin-top: 20px;">
<!-- 				<form action="NhanVien/HoaDon.htm" method="POST"> -->
					<button name="btntaoPN" id="searchProduct" form="form1"
						class="btn btn-dark" type="submit"
						style="height: 50px;">Xuất Phiếu Nhập</button>
<!-- 				</form> -->
			</div>
			<c:if test="${id > 0}">
				<ul>
					<li>${sp.getMASP()}</li>
					<li>${sp.getTENSP()}</li>
				</ul>
			</c:if>
		</div>
		<ul>

		</ul>
	</div>
	</div>

	<!-- end khách hàng -->
</body>

</html>