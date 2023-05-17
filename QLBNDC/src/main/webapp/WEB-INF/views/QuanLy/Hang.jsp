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
	 <link rel="stylesheet" type="text/css" href="<c:url value='/resource/QuanLy/css/Loai.css'/>" />
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
				</a> <a href="QuanLy/DoanhSo.htm"> <span
					class="material-icons-sharp">inventory</span>
					<h3>Doanh số</h3>
				</a> <a href="QuanLy/Loai.htm" > <span
					class="material-icons-sharp">card_travel</span>
					<h3>Loại Sản Phẩm</h3>
				</a> </a> <a href="QuanLy/Hang.htm" class="active"> <span
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
			
			<!-- end header -->

			<!-- start content -->
			<div class="content">
				<c:if test="${test == 1}">
					<div id="toast1">
						<div class="toast1 toast--ThanhCong">
							<div class="toast__icon">
								<i class="fa-solid fa-circle-check"></i>
							</div>
							<div class="toast__body">
								<h3 class="toast__title">Thành Công</h3>
								<p class="toast__msg">${message}</p>
							</div>
							<div class="toast__close">
								<i class="fa-solid fa-circle-xmark"></i>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${test == 2}">
					<div id="toast1">
						<div class="toast1 toast--ThanhCong">
							<div class="toast__icon">
								<i class="fa-solid fa-circle-exclamation"></i>
							</div>
							<div class="toast__body">
								<h3 class="toast__title">Thất Bại</h3>
								<p class="toast__msg">${message}</p>
							</div>
							<div class="toast__close">
								<i class="fa-solid fa-do-not-enter"></i>
							</div>
						</div>
					</div>
				</c:if>
				<div class="navbar"
					style="background-image: linear-gradient(45deg, #43a047, #1de9b6) !important;">
					<h3>Danh sách sản phẩm</h3>
					<button type="button" class="btn btn-primary">
						<a href="QuanLy/ThemHang.htm" style="color: red;"> <span
							class="material-icons-sharp"> local_drink </span> <span
							style="float: right; margin-top: 5px;">Thêm Hãng</span>
						</a>

					</button>


				</div>
				<div class="search">
                	<form class="d-inline-flex" action="QuanLy/Hang.htm">
                    <div class="search-kh">
                        <input name="searchInput" type="search" placeholder="Nhập vào tên hãng" />
                    </div>
                    <button name="btnsearch" id="searchProduct"
							class="btn btn-outline-success" type="submit">Tìm Kiếm</button>
					</form>
                </div>
				<div class="customer">

					<jsp:useBean id="pagedListHolder" scope="request"
						type="org.springframework.beans.support.PagedListHolder" />
					<c:url value="QuanLy/Hang.htm" var="pagedLink">
						<c:param name="p" value="~" />
					</c:url>
					<table class="customer-list">
						<thead>
							<tr>
								<th>Mã Hãng</th>
								<th>Tên Hãng</th>
								<th>Hiệu Chỉnh</th>
								<th>Xóa</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="pd" items="${pagedListHolder.pageList}">
								<tr>
									<th>${pd.MAHANG}</th>
									<td>${pd.TENHANG}</td>
										<td><form:form
											action="QuanLy/Hang/${pd.MAHANG}.htm?linkEdit" method="POST">
											<button type="submit"class="btn btn-warning">
												<span class="material-icons-sharp" style="color: black;">edit_note</span>
											</button>
										</form:form></td>
									<td><form:form
											action="QuanLy/Hang/${pd.MAHANG}.htm?linkDelete" method="POST">
<%-- 											<input type="hidden" name="username" value="${u.username }" /> --%>
											<button type="submit"class="btn btn-danger">
												<span class="material-icons-sharp" style="color: black;">delete</span>
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
			<!-- end content -->

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