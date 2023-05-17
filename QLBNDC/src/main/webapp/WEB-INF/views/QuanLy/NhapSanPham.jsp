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
	href="<c:url value='/resource/QuanLy/css/QuanLy-NhapSanPham.css'/>" />
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
				<a href="QuanLy/TrangChu.htm"> <span
					class="material-icons-sharp">home</span>
					<h3>Trang chủ</h3>
				</a> <a href="QuanLy/SanPham.htm" class="active"> <span
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


			<div class="content"
				style="background-image: linear-gradient(45deg, rgb(2, 136, 209), rgb(38, 198, 218)) !important;">

				<h2 style="color: red;">SẢN PHẨM</h2>
				<div class="bg-light p-5 rounded"
					style="background-image: -webkit-linear-gradient(45deg, #ff5252, #f48fb1) !important;">
					<form:form class="row g-3" modelAttribute="sanPham"
						action="QuanLy/NhapSanPham.htm?id=${sanPham.MASP}&${message}"
						method="POST" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-4">
								<label for="exampleFormControlInput1" class="form-label">Tên
									sản phầm</label>

								<form:input path="TENSP" type="text" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập mô tả sản phẩm" />
							</div>
							<div class="col-md-4">
								<label for="exampleFormControlInput1" class="form-label">Mô
									tả sản phầm</label>
								<form:input path="MOTA" type="text" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập mô tả sản phẩm" />
							</div>
						</div>

						<div class="row" style="margin-top: 20px;">
							<div class="col-md-4">
								<label for="exampleFormControlInput1" class="form-label">Hình
									ảnh</label> <input name="hinhAnh" type="file" class="form-control"
									id="exampleFormControlInput1"
									placeholder="chọn hình ảnh của sản phẩm" />
							</div>
							<div class="col-md-4">
								<label for="exampleFormControlInput1" class="form-label">Giá
									sản phầm</label>
								<form:input path="GIA" type="text" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập mô tả sản phẩm" />
							</div>
						</div>
						<div class="row" style="margin-top: 20px;">
							<div class="col-md-6">
								<label for="exampleFormControlInput1" class="form-label">Nhãn
									hàng</label>
								<form:select path="hang.MAHANG" items="${hangsSel}"
									itemValue="MAHANG" itemLabel="TENHANG"
									class="form-select form-select-lg mb-6"
									aria-label=".form-select-lg example">

								</form:select>
							</div>
						</div>
						<div class="row" style="margin-top: 20px;">
							<div class="col-md-6">
								<label for="exampleFormControlInput1" class="form-label">Loại
									Sản Phẩm</label>
								<form:select path="loai.MALOAI" items="${loaisSel}"
									itemValue="MALOAI" itemLabel="TENLOAI"
									class="form-select form-select-lg mb-6"
									aria-label=".form-select-lg example">

								</form:select>
							</div>
						</div>
						<div class="col-1">
							<button name="${btnStatus}" class="btn btn-primary">Lưu</button>
						</div>
					</form:form>
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