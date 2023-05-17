<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header>
				<h1>
					<label for="nav-toggle"> <span class="material-icons-sharp">reorder</span>
					</label>
				</h1>

				<div class="user-wrapper">
					<img
						src="<c:url value='/resource/QuanLy/image/logo-vingroup-inkythuatso-13-13-41-00.jpg' />"
						alt="">
					<!-- Example split danger button -->
					<!-- Example single danger button -->
					<div class="btn-group">
						<button type="button" class="btn btn-warning dropdown-toggle"
							data-bs-toggle="dropdown" aria-expanded="false"
							style="width: 6.8rem; background: white; height: 2.8rem; border: none;">
							Quản lý</button>
						<ul class="dropdown-menu" style="width: 170px;">
							<li><a class="dropdown-item" href="QuanLy/ThongTin.htm"> <i><span
										class="material-icons-sharp"> account_circle </span> <h6 style="float: right;  margin-top: 4px;"> Thông tin cá nhân</h6></i> 
										
							</a></li>
							<li><a class="dropdown-item" href="Login/LogOut.htm"
								style="text-align: center;"> <i><span
										class="material-icons-sharp"> logout </span>
										<h6 style="float: right; margin-top: 4px; margin-right: 23px;">Đăng
											xuất</h6> </i>
							</a></li>
						</ul>
					</div>
				</div>
			</header>