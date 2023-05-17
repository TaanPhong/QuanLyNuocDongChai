<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	href="<c:url value='/resource/QuanLy/css/QuanlyTrangChu.css'/>" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</head>

<body>
	<p>Phong</p>
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
				<a href="QuanLy/TrangChu.htm" class="active"> <span
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
			<!-- header  -->
			<%@include file="/WEB-INF/views/include/header.jsp"%>
			>
			<!-- Các bảng thống kê -->
			<div class="cardBox">
				<div class="card" style="background-image: linear-gradient(45deg, rgb(2, 136, 209), rgb(38, 198, 218)) !important;">
					<div>
						<div class="number">${tongTien}</div>
						<div class="cardName">Tiền bán hàng</div>
					</div>
					<div class="iconBox">
						<span class="material-icons-sharp"> add_card </span>
					</div>
				</div>

				<div class="card" style="background-image: -webkit-linear-gradient(45deg,#ff5252,#f48fb1)!important;">
					<div>
						<div class="number">${soDonHang}</div>
						<div class="cardName">Số đơn hàng</div>
					</div>
					<div class="iconBox">
						<span class="material-icons-sharp"> shopping_cart </span>
					</div>
				</div>

				<div class="card" style="background-image: linear-gradient(45deg,#ff6f00,#ffca28)!important;">
					<div>
						<div class="number">${soSanPham}</div>
						<div class="cardName">Số sản phẩm</div>
					</div>
					<div class="iconBox">
						<span class="material-icons-sharp"> local_drink </span>
					</div>
				</div>

				<div class="card" style="background-image:linear-gradient(45deg,#43a047,#1de9b6)!important;">
					<div>
						<div class="number">${soKhachHang}</div>
						<div class="cardName">Khách hàng</div>
					</div>
					<div class="iconBox">
						<span class="material-icons-sharp"> remove_shopping_cart </span>
					</div>
				</div>

			</div>
			<!-- end các bảng thống kê -->

			<!-- đơn đặt hàng  -->
			<div class="details">
				<div class="recentOrders">
					<div class="cardHeader">
						<h2>Đơn đặt hàng gần đây</h2>
					</div>
					<table>
						<thead>
							<tr>
								<td>Mã đơn đặt hàng</td>
								<td>Họ Tên Khách Hàng</td>
								<td>Số Điện Thoại</td>
								<td>Ngày đặt</td>
								<td></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items= "${donDHs}" var = "i"  begin="0" end="7">
								<tr>
								<td>${i[0]}</td>
								<td>${i[1]}</td>
								<td>${i[2]}</td>
								<td>${i[3]}</td>
								<td><a href="#"><span class="material-icons-sharp" style="color: black;"> visibility </span> </a></td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
				</div>
				<!-- end đơn đặt hàng -->

				<!-- khách hàng  -->
				<div class="recentCustomers">
					<div class="cardHeader">
						<h2>Khách hàng gần đây</h2>
					</div>
					<table>
						<tbody>
							
							<c:forEach items= "${khanhHangDats}" var = "i"  begin="0" end="7">
							<tr>
								<td width="60px"><div class="imgBx">
										<img src="<c:url value='/resource/QuanLy/image/avatar.jpg' />">
									</div></td>
								<td><h4>
										${i[0]}<br> <span>${i[2]}</span>
									</h4></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end khách hàng -->
			</div>
		</div>
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