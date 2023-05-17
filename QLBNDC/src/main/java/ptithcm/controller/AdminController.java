package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.entity.*;

@Controller
@RequestMapping("QuanLy/")
@Transactional
public class AdminController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("TrangChu")
	public String TrangChu(ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		Session session = factory.getCurrentSession();
		String hql = "select DDH.MADDH, HO + ' ' + TEN as HOTEN, DDH.SDTNGUOINHAN, DDH.NGAYDAT from KhachHang KH, DonDH DDH\r\n"
				+ "where KH.MAKH = DDH.khachHang.MAKH ORDER BY DDH.NGAYDAT DESC";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		model.addAttribute("donDHs", list);

		hql = "select KH.HO + ' ' + KH.TEN as HOTEN, KH.SDT from KhachHang KH, DonDH DDH "
				+ "where KH.MAKH = DDH.khachHang.MAKH order by DDH.NGAYDAT DESC";
		query = session.createQuery(hql);
		List<Object[]> list1 = query.list();
		model.addAttribute("khanhHangDats", list1);

		hql = "select sum(SP.GIA * CT.SL) from HoaDon HD, SanPham SP, CTHD CT\r\n"
				+ "where HD.MAHD = CT.hoaDon.MAHD\r\n" + "and SP.MASP = CT.sanPham.MASP\r\n";
		query = session.createQuery(hql);
		long tongTien = (long) query.uniqueResult();
		model.addAttribute("tongTien", tongTien);

		hql = "select count(*) from HoaDon";
		query = session.createQuery(hql);
		long soDonHang = (long) query.uniqueResult();
		model.addAttribute("soDonHang", soDonHang);

		hql = "select count(*) from SanPham";
		query = session.createQuery(hql);
		long soSanPham = (long) query.uniqueResult();
		model.addAttribute("soSanPham", soSanPham);

		hql = "select count(*) from KhachHang";
		query = session.createQuery(hql);
		long soKhachHang = (long) query.uniqueResult();
		model.addAttribute("soKhachHang", soKhachHang);
		return "QuanLy/TrangChu";
	}

	@RequestMapping("NhapKho")
	public String NhapKho(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Object[]> listNH = getNhapKho();
		PagedListHolder pagedListHolder = new PagedListHolder(listNH);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "QuanLy/NhapKho";
	}

	@RequestMapping("LoiNhuan")
	public String LoiNhuan(HttpServletRequest request, ModelMap model) {

		return "QuanLy/LoiNhuan";
	}

	@RequestMapping("KhachHang")
	public String KhachHang(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Object[]> listKH = getKhacHang();
		PagedListHolder pagedListHolder = new PagedListHolder(listKH);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "QuanLy/KhachHang";
	}

	@RequestMapping("DoanhSoHoaDon")
	public String DoanhSoHoaDon(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Object[]> listHD = getHoaDon();
		PagedListHolder pagedListHolder = new PagedListHolder(listHD);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("kiemtra", 0);
		Session session = factory.getCurrentSession();
		String hql = "select count(*) from HoaDon";
		Query query = session.createQuery(hql);
		long soHoDon = (long) query.uniqueResult();
		model.addAttribute("soHoDon", soHoDon);
		hql = "select count(*) from DonDH";
		query = session.createQuery(hql);
		long tongDonDH = (long) query.uniqueResult();
		model.addAttribute("tongDonDH", tongDonDH);
		hql = "select sum(CTHD.SL * CTHD.DONGIA) from CTHD CTHD";
		query = session.createQuery(hql);
		long sum1 = (long) query.uniqueResult();
		hql = "select sum(CTDDH.SL * CTDDH.DONGIA) from CTDDH CTDDH";
		query = session.createQuery(hql);
		long sum2 = (long) query.uniqueResult();
		long tongTien = sum1 + sum2;
		model.addAttribute("tongTien", tongTien);
		return "QuanLy/DoanhSo";
	}

	@RequestMapping("DoanhSo")
	public String DoanhSo(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Object[]> listHD = getHoaDon();
		PagedListHolder pagedListHolder = new PagedListHolder(listHD);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("kiemtra", "0");
		Session session = factory.getCurrentSession();
		String hql = "select count(*) from HoaDon";
		Query query = session.createQuery(hql);
		long soHoDon = (long) query.uniqueResult();
		model.addAttribute("soHoDon", soHoDon);
		hql = "select count(*) from DonDH";
		query = session.createQuery(hql);
		long tongDonDH = (long) query.uniqueResult();
		model.addAttribute("tongDonDH", tongDonDH);
		hql = "select sum(CTHD.SL * CTHD.DONGIA) from CTHD CTHD";
		query = session.createQuery(hql);
		long sum1 = (long) query.uniqueResult();
		hql = "select sum(CTDDH.SL * CTDDH.DONGIA) from CTDDH CTDDH";
		query = session.createQuery(hql);
		long sum2 = (long) query.uniqueResult();
		long tongTien = sum1 + sum2;
		model.addAttribute("tongTien", tongTien);
		return "QuanLy/DoanhSo";
	}

	@RequestMapping("DoanhSoDonDatHang")
	public String DoanhSoDonDatHang(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Object[]> listDDH = getDonDH();
		PagedListHolder pagedListHolder = new PagedListHolder(listDDH);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("kiemtra", 1);
		Session session = factory.getCurrentSession();
		String hql = "select count(*) from HoaDon";
		Query query = session.createQuery(hql);
		long soHoDon = (long) query.uniqueResult();
		model.addAttribute("soHoDon", soHoDon);
		hql = "select count(*) from DonDH";
		query = session.createQuery(hql);
		long tongDonDH = (long) query.uniqueResult();
		model.addAttribute("tongDonDH", tongDonDH);
		hql = "select sum(CTHD.SL * CTHD.DONGIA) from CTHD CTHD";
		query = session.createQuery(hql);
		long sum1 = (long) query.uniqueResult();
		hql = "select sum(CTDDH.SL * CTDDH.DONGIA) from CTDDH CTDDH";
		query = session.createQuery(hql);
		long sum2 = (long) query.uniqueResult();
		long tongTien = sum1 + sum2;
		model.addAttribute("tongTien", tongTien);
		return "QuanLy/DoanhSo";
	}

	@RequestMapping("TonKho")
	public String TonKho(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Object[]> listTK = getTonKho();
		PagedListHolder pagedListHolder = new PagedListHolder(listTK);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		Session session = factory.getCurrentSession();
		String hql = "select sum(SP.SLTON) from SanPham SP";
		Query query = session.createQuery(hql);
		long soSLTON = (long) query.uniqueResult();
		model.addAttribute("soSLTON", soSLTON);
		hql = "select sum(SP.SLTON * CTPN.DONGIA) from SanPham SP, CTPN CTPN" + " where CTPN.sanPham.MASP = SP.MASP";
		query = session.createQuery(hql);
		long tongVonTonKho = (long) query.uniqueResult();
		model.addAttribute("tongVonTonKho", tongVonTonKho);
		hql = "select sum(SP.SLTON * SP.GIA) from SanPham SP";
		query = session.createQuery(hql);
		long tongGiaTriTonKho = (long) query.uniqueResult();
		model.addAttribute("tongGiaTriTonKho", tongGiaTriTonKho);
		return "QuanLy/TonKho";
	}

//	public List<DonDH> getDonDH() {
//		Session session = factory.getCurrentSession();
//		String hql = "From DonDH order by NGAYDAT DESC";
//		Query query = session.createQuery(hql);
//		List<DonDH> list = query.list();
//		return list;
//	}

	public List<Object[]> getKhacHang() {
		Session session = factory.getCurrentSession();
		String hql = "Select KH.MAKH, KH.HO + ' ' + KH.TEN as HOTEN"
				+ ", KH.DIACHI, KH.SDT, KH.EMAIL from KhachHang KH";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> getNhapKho() {
		Session session = factory.getCurrentSession();
		String hql = "select PN.MAPN, PN.NGAYNHAP, sum(CTPN.DONGIA*CTPN.SL) as TONGTIEN,\r\n"
				+ "NV.HO + ' ' + NV.TEN as HOTEN \r\n" + "from NhanVien NV, PhieuNhap PN, CTPN CTPN\r\n"
				+ "where NV.MANV = PN.nhanVien.MANV\r\n" + "and CTPN.phieuNhap.MAPN = PN.MAPN\r\n"
				+ "group by PN.MAPN, PN.NGAYNHAP, \r\n" + "NV.HO, NV.TEN";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> getTonKho() {
		Session session = factory.getCurrentSession();
		String hql = "Select SP.TENSP, L.TENLOAI, CTPN.DONGIA, SP.GIA, SP.SLTON \r\n"
				+ "from SanPham SP, Loai L, CTPN CTPN\r\n" + "where SP.MASP = CTPN.sanPham.MASP\r\n"
				+ "and L.MALOAI = SP.loai.MALOAI";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> getHoaDon() {
		Session session = factory.getCurrentSession();
		String hql = "select HD.MAHD, HD.NGAYLAPHD, NV.HO + ' ' + NV.TEN as HOTEN, sum(CTHD.SL * CTHD.DONGIA) as TONGTIEN\r\n"
				+ "from HoaDon HD, NhanVien NV, CTHD CTHD\r\n"
				+ "where HD.MAHD = CTHD.hoaDon.MAHD and NV.MANV = HD.nhanVien.MANV\r\n"
				+ "group by  HD.MAHD, HD.NGAYLAPHD, NV.HO, NV.TEN";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> getDonDH() {
		Session session = factory.getCurrentSession();
		String hql = "select DDH.MADDH, DDH.NGAYDAT, SUM(CTDDH.SL * CTDDH.DONGIA) as TONG, KH.HO + ' ' + KH.TEN as HOTEN \r\n"
				+ "from DonDH DDH, KhachHang KH, CTDDH CTDDH\r\n"
				+ "where DDH.khachHang.MAKH = KH.MAKH and CTDDH.donDH.MADDH = DDH.MADDH\r\n"
				+ "group by DDH.MADDH, DDH.NGAYDAT, KH.HO, KH.TEN";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	// ===================================== Tim kiem //
	// =======================================//

	// ************* KhachHang *****************//
	public List<Object[]> timKhachHangs(String ten_khachHang) {
		Session session = factory.getCurrentSession();
		String hql = "Select KH.MAKH, KH.HO + ' ' + KH.TEN as HOTEN"
				+ ", KH.DIACHI, KH.SDT, KH.EMAIL from KhachHang KH where KH.TEN LIKE :ten_khachHang";
		Query query = session.createQuery(hql);
		query.setParameter("ten_khachHang", "%" + ten_khachHang + "%");
		List<Object[]> list = query.list();

		return list;

	}

	@RequestMapping(value = "KhachHang", params = "btnsearch")
	public String TimSanPham(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		System.out.println("vô");
		List<Object[]> listSP = timKhachHangs(request.getParameter("searchInput"));

		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "QuanLy/KhachHang";
	}

	// ************* Nhập Kho *****************//
	public List<Object[]> timNhapKhos(String ten_NV) {
		Session session = factory.getCurrentSession();
		String hql = "select PN.MAPN, PN.NGAYNHAP, sum(CTPN.DONGIA*CTPN.SL) as TONGTIEN,\r\n"
				+ "NV.HO + ' ' + NV.TEN as HOTEN	 \r\n" + "from NhanVien NV, PhieuNhap PN, CTPN CTPN\r\n"
				+ "where NV.MANV = PN.nhanVien.MANV and NV.TEN LIKE :ten_NV\r\n"
				+ "and CTPN.phieuNhap.MAPN = PN.MAPN\r\n" + "group by PN.MAPN, PN.NGAYNHAP, \r\n" + "NV.HO, NV.TEN";
		Query query = session.createQuery(hql);
		query.setParameter("ten_NV", "%" + ten_NV + "%");
		List<Object[]> list = query.list();

		return list;

	}

	@RequestMapping(value = "NhapKho", params = "btnsearch")
	public String TimNhapKho(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		System.out.println("vô");
		List<Object[]> listSP = timNhapKhos(request.getParameter("searchInput"));

		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "QuanLy/NhapKho";
	}

	// ************* Tồn Kho *****************//
	public List<Object[]> timTonKhos(String ten_SP) {
		Session session = factory.getCurrentSession();
		String hql = "Select SP.TENSP, L.TENLOAI, CTPN.DONGIA, SP.GIA, SP.SLTON \r\n"
				+ "from SanPham SP, Loai L, CTPN CTPN\r\n" + "where SP.MASP = CTPN.sanPham.MASP\r\n"
				+ "and L.MALOAI = SP.loai.MALOAI and SP.TENSP LIKE :ten_SP";
		Query query = session.createQuery(hql);
		query.setParameter("ten_SP", "%" + ten_SP + "%");
		List<Object[]> list = query.list();
		return list;

	}

	@RequestMapping(value = "TonKho", params = "btnsearch")
	public String TimTonKho(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		System.out.println("vô tồn kho");
		List<Object[]> listSP = timTonKhos(request.getParameter("searchInput"));

		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		Session session = factory.getCurrentSession();
		String hql = "select sum(SP.SLTON) from SanPham SP";
		Query query = session.createQuery(hql);
		long soSLTON = (long) query.uniqueResult();
		model.addAttribute("soSLTON", soSLTON);
		hql = "select sum(SP.SLTON * CTPN.DONGIA) from SanPham SP, CTPN CTPN" + " where CTPN.sanPham.MASP = SP.MASP";
		query = session.createQuery(hql);
		long tongVonTonKho = (long) query.uniqueResult();
		model.addAttribute("tongVonTonKho", tongVonTonKho);
		hql = "select sum(SP.SLTON * SP.GIA) from SanPham SP";
		query = session.createQuery(hql);
		long tongGiaTriTonKho = (long) query.uniqueResult();
		model.addAttribute("tongGiaTriTonKho", tongGiaTriTonKho);
		return "QuanLy/TonKho";
	}

	// ***************** Doanh Số ********************************//

	public List<Object[]> getTimHoaDon(String hd) {
		Session session = factory.getCurrentSession();
		String hql = "select HD.MAHD, HD.NGAYLAPHD, NV.HO + ' ' + NV.TEN as HOTEN, sum(CTHD.SL * CTHD.DONGIA) as TONGTIEN\r\n"
				+ "from HoaDon HD, NhanVien NV, CTHD CTHD\r\n"
				+ "where HD.MAHD = CTHD.hoaDon.MAHD and NV.MANV = HD.nhanVien.MANV and HD.MAHD = " + hd + "\r\n"
				+ "group by  HD.MAHD, HD.NGAYLAPHD, NV.HO, NV.TEN";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> getTimDonDH(String ddh) {
		Session session = factory.getCurrentSession();
		String hql = "select DDH.MADDH, DDH.NGAYDAT, SUM(CTDDH.SL * CTDDH.DONGIA) as TONG, KH.HO + ' ' + KH.TEN as HOTEN \r\n"
				+ "from DonDH DDH, KhachHang KH, CTDDH CTDDH\r\n"
				+ "where DDH.khachHang.MAKH = KH.MAKH and CTDDH.donDH.MADDH = DDH.MADDH and DDH.MADDH = " + ddh + "\r\n"
				+ "group by DDH.MADDH, DDH.NGAYDAT, KH.HO, KH.TEN";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	@RequestMapping(value = "TimDoanhSoHoaDon", params = "btnsearch")
	public String TimDoanhSoHoaDon(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Object[]> listHD = getTimHoaDon(request.getParameter("searchInput"));
		PagedListHolder pagedListHolder = new PagedListHolder(listHD);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("kiemtra", 0);
		Session session = factory.getCurrentSession();
		String hql = "select count(*) from HoaDon";
		Query query = session.createQuery(hql);
		long soHoDon = (long) query.uniqueResult();
		model.addAttribute("soHoDon", soHoDon);
		hql = "select count(*) from DonDH";
		query = session.createQuery(hql);
		long tongDonDH = (long) query.uniqueResult();
		model.addAttribute("tongDonDH", tongDonDH);
		hql = "select sum(CTHD.SL * CTHD.DONGIA) from CTHD CTHD";
		query = session.createQuery(hql);
		long sum1 = (long) query.uniqueResult();
		hql = "select sum(CTDDH.SL * CTDDH.DONGIA) from CTDDH CTDDH";
		query = session.createQuery(hql);
		long sum2 = (long) query.uniqueResult();
		long tongTien = sum1 + sum2;
		model.addAttribute("tongTien", tongTien);
		return "QuanLy/DoanhSo";
	}

//	@RequestMapping("DoanhSo")
//	public String DoanhSo(HttpServletRequest request, ModelMap model) {
//		List<Object[]> listHD = getHoaDon();
//		PagedListHolder pagedListHolder = new PagedListHolder(listHD);
//		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
//		pagedListHolder.setPage(page);
//		// SỐ page
//		pagedListHolder.setMaxLinkedPages(5);
//		// Số dòng
//		pagedListHolder.setPageSize(10);
//		// model.addAttribute("btnStatus", "btnAdd");
//		model.addAttribute("pagedListHolder", pagedListHolder);
//		model.addAttribute("kiemtra", "0");
//		return "QuanLy/DoanhSo";
//	}

	@RequestMapping(value = "TimDoanhSoDonDatHang", params = "btnsearch")
	public String TimDoanhSoDonDatHang(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Object[]> listDDH = getTimDonDH(request.getParameter("searchInput"));
		PagedListHolder pagedListHolder = new PagedListHolder(listDDH);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("kiemtra", 1);
		Session session = factory.getCurrentSession();
		String hql = "select count(*) from HoaDon";
		Query query = session.createQuery(hql);
		long soHoDon = (long) query.uniqueResult();
		model.addAttribute("soHoDon", soHoDon);
		hql = "select count(*) from DonDH";
		query = session.createQuery(hql);
		long tongDonDH = (long) query.uniqueResult();
		model.addAttribute("tongDonDH", tongDonDH);
		hql = "select sum(CTHD.SL * CTHD.DONGIA) from CTHD CTHD";
		query = session.createQuery(hql);
		long sum1 = (long) query.uniqueResult();
		hql = "select sum(CTDDH.SL * CTDDH.DONGIA) from CTDDH CTDDH";
		query = session.createQuery(hql);
		long sum2 = (long) query.uniqueResult();
		long tongTien = sum1 + sum2;
		model.addAttribute("tongTien", tongTien);
		return "QuanLy/DoanhSo";
	}

	public NhanVien getNV(Integer Id) {
		Session session = factory.getCurrentSession();
		String hql = "from NhanVien where MATK = " + Id;
		Query query = session.createQuery(hql);
		NhanVien nv = (NhanVien) query.list().get(0);
		return nv;
	}

	@RequestMapping("ThongTin")
	public String ThongTin(ModelMap model, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		NhanVien nv = getNV(tk.getMATK());
		model.addAttribute("nv", nv);
		return "QuanLy/ThongTinCaNhan";
	}
}
