<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
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
	href="<c:url value='/resource/QuanLy/css/QuanLyDoanhSo.css'/>" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" />
</head>

<body>
	<div class="container-fluid">
		<input type="checkbox" id="nav-toggle">
		<!-- thanh menu -->
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
				</a> <a href="QuanLy/DoanhSo.htm" class="active"> <span
					class="material-icons-sharp">inventory</span>
					<h3>Doanh số</h3>
				</a> <a href="QuanLy/Loai.htm" > <span
					class="material-icons-sharp">card_travel</span>
					<h3>Loại Sản Phẩm</h3>
				</a> </a> <a href="QuanLy/Hang.htm"> <span
					class="material-icons-sharp">fastfood</span>
					<h3>Hãng Sản Phẩm</h3>
				</a><a href="#"> <span class="material-icons-sharp">key</span>
					<h3>Đổi mật khuẩu</h3>
				</a>
			</div>
		</aside>
		<!-- end aside  -->
		<!-- -------------------------------------------------------------------------- -->
		<!-- start main  -->
		<div class="main">
			<!-- start header -->
			<%@include file="/WEB-INF/views/include/header.jsp"%>
			
			<div class="content">
				<div class="navbar">
					<h3>Thông kê doanh thu</h3>
				</div>
				<!-- Các bảng thống kê -->
				<div class="cardBox">
					<div class="card"
						style="background-image: linear-gradient(45deg, rgb(2, 136, 209), rgb(38, 198, 218)) !important;">
						<div>
							<div class="number">${soHoDon}</div>
							<div class="cardName">Số lượng hóa đơn</div>
						</div>
						<div class="iconBox">
							<span class="material-icons-sharp"> add_card </span>
						</div>
					</div>

					<div class="card"
						style="background-image: -webkit-linear-gradient(45deg, #ff5252, #f48fb1) !important;">
						<div>
							<div class="number">${tongDonDH}</div>
							<div class="cardName">Số lượng đơn đặt hàng</div>
						</div>
						<div class="iconBox">
							<span class="material-icons-sharp"> shopping_cart </span>
						</div>
					</div>

					<div class="card"
						style="background-image: linear-gradient(45deg, #ff6f00, #ffca28) !important;">
						<div>
							<div class="number">${tongTien}</div>
							<div class="cardName">Tổng giá trị đã bán ra</div>
						</div>
						<div class="iconBox">
							<span class="material-icons-sharp"> local_drink </span>
						</div>
					</div>

					<!-- <div class="card">
                    <div>
                        <div class="number">0</div>
                        <div class="cardName"></div>
                    </div>
                    <div class="iconBox">
                        <span class="material-icons-sharp">
                            remove_shopping_cart
                        </span>
                    </div>
                </div> -->

				</div>

				<div class="search">
					<c:if test="${kiemtra == 0}">
						<form class="d-inline-flex" action="QuanLy/TimDoanhSoHoaDon.htm">
							<div class="search-sp">
								<input name="searchInput" type="number"
									placeholder="Nhập vào mã hóa đơn" />
							</div>
							<button name="btnsearch" id="searchProduct"
								class="btn btn-outline-success" type="submit">Tìm Kiếm</button>
						</form>
					</c:if>
					<c:if test="${kiemtra == 1}">
						<form class="d-inline-flex" action="QuanLy/TimDoanhSoDonDatHang.htm">
							<div class="search-sp">
								<input name="searchInput" type="number"
									placeholder="Nhập vào mã đơn đặt hàng" />
							</div>
							<button name="btnsearch" id="searchProduct"
								class="btn btn-outline-success" type="submit">Tìm Kiếm</button>
						</form>
					</c:if>
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-expanded="false">Thống kê</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="QuanLy/DoanhSoHoaDon.htm">Theo
								Hóa Đơn</a> <a class="dropdown-item"
								href="QuanLy/DoanhSoDonDatHang.htm">Theo Đơn Đặt Hàng</a>
						</div>
					</div>
				</div>
				<div class="DoanhSo">
					<jsp:useBean id="pagedListHolder" scope="request"
						type="org.springframework.beans.support.PagedListHolder" />

					<c:if test="${kiemtra == 0}">
						<c:url value="QuanLy/DoanhSoHoaDon.htm" var="pagedLink">
							<c:param name="p" value="~" />
						</c:url>
						<table class="table">
							<thead>
								<tr>
									<th>Mã Hóa Đơn</th>
									<th>Ngày Lập Hóa Đơn</th>
									<th>Họ Tên Nhân Viên</th>
									<th>Tổng Tiền</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="pd" items="${pagedListHolder.pageList}">
									<tr>
										<th>${pd[0]}</th>
										<td>${pd[1]}</td>
										<td>${pd[2]}</td>
										<td>${pd[3]}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
						<div>
							<tg:paging pagedListHolder="${pagedListHolder}"
								pagedLink="${pagedLink}" />
						</div>
					</c:if>
					<c:if test="${kiemtra == 1}">
						<c:url value="QuanLy/DoanhSoDonDatHang.htm" var="pagedLink">
							<c:param name="p" value="~" />
						</c:url>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Mã Hóa Đơn</th>
									<th>Ngày Đặt</th>
									<th>Họ Tên Nhân Viên</th>
									<th>Tổng Tiền</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="pd" items="${pagedListHolder.pageList}">
									<tr>
										<th>${pd[0]}</th>
										<td>${pd[1]}</td>
										<td>${pd[3]}</td>
										<td>${pd[2]}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
						<div>
							<tg:paging pagedListHolder="${pagedListHolder}"
								pagedLink="${pagedLink}" />
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<!-- end main -->
		<!-- <script>
            function toggleMenu(){
                let toggle = document.querySelector('.toggle');
                toggle.classList.toggle
            }
        </script> -->
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>

</html>