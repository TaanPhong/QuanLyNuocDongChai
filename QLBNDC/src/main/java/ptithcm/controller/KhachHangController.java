package ptithcm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ptithcm.entity.CTDDH;
import ptithcm.entity.CTHD;
import ptithcm.entity.DonDH;
import ptithcm.entity.HoaDon;
import ptithcm.entity.KhachHang;
import ptithcm.entity.Loai;
import ptithcm.entity.NhanVien;
import ptithcm.entity.SanPham;
import ptithcm.entity.TaiKhoan;

@Controller
@RequestMapping("KhachHang/")
@Transactional
public class KhachHangController {
	@Autowired
	SessionFactory factory;
	List<SanPham> listSanPham = new ArrayList<>();
	List<Integer> listSL = new ArrayList<>();
	String ho = "";
	String ten = "";
	String sdt = "";
	String dc = "";
	String ngay = java.time.LocalDate.now().toString();
	DonDH dh = new DonDH();
	KhachHang kh = new KhachHang();
	Integer test = 0;

	public List<SanPham> getSanPhams() {
		Session session = factory.getCurrentSession();
		String hql = "from SanPham";
		Query query = session.createQuery(hql);
		List<SanPham> list = query.list();
		return list;
	}

	public SanPham getSanPham(Integer id) {
		Session session = factory.getCurrentSession();
		return (SanPham) session.get(SanPham.class, id);
	}

	public KhachHang getKhachHang(Integer id) {
		Session session = factory.getCurrentSession();
		return (KhachHang) session.get(KhachHang.class, id);
	}

	public Integer ThemDonDH(DonDH dh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(dh);
			t.commit();
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public Integer HieuChinhKhachHang(KhachHang kh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(kh);
			t.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public Integer ThemCTDDH(CTDDH ctddh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ctddh);
			t.commit();
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public KhachHang getKH(Integer id) {
		// System.out.println(id);
		Session session = factory.getCurrentSession();
		String hql = "from KhachHang where MATK = " + id;
		Query query = session.createQuery(hql);
		KhachHang kh = (KhachHang) query.list().get(0);
		return kh;
	}

	@RequestMapping("TrangChu")
	public String TrangChu(ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		String messgase = "";
		if (test == 1)
			messgase = "Sản phẩm đã hết hàng, quý khách xin quay lại sau!";
		if (test == 2)
			messgase = "Đã thêm vào giỏ hàng";
		List<SanPham> listSP = this.getSanPhams();
		model.addAttribute("listSP", listSP);
		model.addAttribute("listSL", listSL);
		model.addAttribute("test", test);
		model.addAttribute("messgase", messgase);
		test = 0;
		return "KhachHang/TrangChu";
	}

	// ===================================== Thêm Vào Giỏ
	// ==========================//

	@RequestMapping("ThemVaoGio/{id}.htm")
	public String ThemVaoGioHang(ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		SanPham sp = getSanPham(id);
		if (sp.getSLTON() == 0) {
			test = 1;
			return "redirect:/KhachHang/TrangChu.htm";
		}
		if (listSanPham.size() == 0) {
			test = 2;
			listSanPham.add(sp);
			listSL.add(1);
			return "redirect:/KhachHang/TrangChu.htm";
		}
		int i = 0;
		System.out.println(id);
		for (SanPham sp1 : listSanPham) {
			if (sp1.getMASP() == id) {
				listSL.set(i, listSL.get(i) + 1);
				if (listSL.get(i) > sp1.getSLTON()) {
					test = 1;
					listSL.set(i, sp1.getSLTON());
					return "redirect:/KhachHang/TrangChu.htm";
				}
				test = 2;
				return "redirect:/KhachHang/TrangChu.htm";
			}
			i++;
		}
		listSL.add(1);
		listSanPham.add(sp);
		test = 2;
		return "redirect:/KhachHang/TrangChu.htm";
	}

	// ==================================== Giỏ Hàng
	// =====================================//
	@RequestMapping("GioHang")
	public String GioHang(ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		model.addAttribute("listSanPham", listSanPham);
		model.addAttribute("listSL", listSL);
		return "KhachHang/GioHang";
	}

	@RequestMapping("GioHang/{id}.htm")
	public String XoaSanPhamHD(ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		int i = 0;
		int vtxoa = 0;
		for (SanPham sp : listSanPham) {
			if (sp.getMASP() == id)
				vtxoa = i;
			i++;
		}
		listSanPham.remove(vtxoa);
		listSL.remove(vtxoa);
		return "redirect:/KhachHang/GioHang.htm";
	}

	@RequestMapping(value = "GioHang", params = "btnTC", method = RequestMethod.POST)
	public String VeTrangChu(ModelMap model, HttpServletRequest request, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		// listSP.add(getSanPham(id));
		String s = "";
		int i = 0;
		for (SanPham sp : listSanPham) {
			s = sp.getMASP().toString();
			Integer x = Integer.valueOf(request.getParameter(s));
			listSL.set(i, x);
			if (sp.getMASP() < x)
				listSL.set(i, x);
//				return "redirect:/KhachHang/TrangChu.htm";
			i++;
		}
		return "redirect:/KhachHang/TrangChu.htm";
	}

	@RequestMapping(value = "GioHang", params = "btnDH", method = RequestMethod.POST)
	public String DatHang(ModelMap model, HttpSession session, HttpServletRequest request, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		// listSP.add(getSanPham(id));
		if (listSanPham.size() == 0) {
			return "redirect:/KhachHang/TrangChu.htm";
		}
		String s = "";
		int i = 0;
		for (SanPham sp : listSanPham) {
			s = sp.getMASP().toString();
			Integer x = Integer.valueOf(request.getParameter(s));
			listSL.set(i, x);
			if (sp.getSLTON() < x)
				listSL.set(i, sp.getSLTON());
			i++;
		}
		return "redirect:/KhachHang/XacNhanHoaDon.htm";
	}

	@RequestMapping(value = "XacNhanThongTin", method = RequestMethod.POST)
	public String HoanThanh(HttpServletRequest request, @ModelAttribute("donDH") DonDH donDH, HttpSession ss,
			ModelMap model) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		ho = donDH.getHONGUOINHAN();
		ten = donDH.getTENNGUOINHAN();
		sdt = donDH.getSDTNGUOINHAN();
		dc = donDH.getDIACHINHAN();
		Integer tong = 0;
		int i = 0;
		for (SanPham sp1 : listSanPham) {
			tong = tong + sp1.getGIA() * listSL.get(i);
			i++;
		}
		model.addAttribute("tong", tong);
		model.addAttribute("listSanPham", listSanPham);
		model.addAttribute("listSL", listSL);
		return "KhachHang/XacNhanDonHang";
	}

	@RequestMapping("XacNhanHoaDon")
	public String XacNhanThongTin(ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		DonDH donDH = new DonDH();
		model.addAttribute("donDH", donDH);
		return "KhachHang/XacNhanMuaHang";
	}

	@RequestMapping(value = "ThanhToan", method = RequestMethod.POST)
	public String ThanhToan(HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		KhachHang kh = getKH(tk.getMATK());
//		DonDH dh = new DonDH();
		dh.setKhachHang(kh);
		dh.setNGAYDAT(ngay);
		dh.setDIACHINHAN(dc);
		dh.setHONGUOINHAN(ho);
		dh.setTENNGUOINHAN(ten);
		dh.setSDTNGUOINHAN(sdt);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(dh);
			t.commit();
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
		} finally {
			session.close();
		}
		return "redirect:/KhachHang/ThanhTien.htm";

	}

	@RequestMapping("ThanhTien")
	public String ThanhTien(HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		int i = 0;
		for (SanPham sp : listSanPham) {
			CTDDH ct = new CTDDH();
			ct.setDonDH(dh);
			ct.setSanPham(sp);
			ct.setDONGIA(sp.getGIA());
			ct.setSL(listSL.get(i));
			System.out.println(listSL.get(i));
			Integer tmp = ThemCTDDH(ct);
			i++;
		}
		listSanPham.clear();
		listSL.clear();
		return "KhachHang/ThanhToan";
	}

	// ============================= Xem chi tiết ===============================//
	@RequestMapping("XemChiTiet/{id}.htm")
	public String XemChiTietSP(ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		model.addAttribute("sanPham", this.getSanPham(id));
		return "KhachHang/XemChiTiet";
	}
	// ============================= Xem Lịch sử mua hàng
	// =======================================//

	public List<DonDH> getDHs(Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "from DonDH where MAKH = " + id;
		Query query = session.createQuery(hql);
		List<DonDH> list = query.list();
//		System.out.println("1");
		return list;
	}

	public List<Object[]> getCTDDHs(Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "select SP.TENSP, CT.SL, CT.DONGIA from CTDDH CT, SanPham SP "
				+ "where CT.sanPham.MASP = SP.MASP and MADDH = " + id;
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
//		System.out.println("1");
		return list;
	}

	@RequestMapping("LichSuMua")
	public String LichSuMuaHang(ModelMap model, HttpServletRequest request, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		KhachHang kh = getKH(tk.getMATK());
		List<DonDH> listDH = this.getDHs(kh.getMAKH());
		model.addAttribute("listDH", listDH);
		return "KhachHang/LichSuDatHang";
	}

	@RequestMapping("XemChiTietDonHang/{id}.htm")
	public String XemChiTietDonHang(ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Object[]> listCTDDH = this.getCTDDHs(id);
		model.addAttribute("listCTDDH", listCTDDH);
		return "KhachHang/ChiTietDonDatHang";
	}

	// ========================================= Xem Thông tin cá Nhân
	// =================================//
	@RequestMapping("XemThongTinCaNhan")
	public String XemThongTinCaNhan(ModelMap model, HttpServletRequest request, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		kh = getKH(tk.getMATK());
		// List<DonDH> listDH = this.getDHs(kh.getMAKH());
		model.addAttribute("kh", kh);
		return "KhachHang/XemThongTinCaNhan";
	}

	@RequestMapping("SuaThongTinCaNhan")
	public String SuaThongTinCaNhan(ModelMap model, HttpServletRequest request, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
//		kh = getKH(tk.getMATK());
		// List<DonDH> listDH = this.getDHs(kh.getMAKH());
		model.addAttribute("khachHang", kh);
		return "KhachHang/SuaThongTinCaNhan";
	}

	@RequestMapping(value = "XacNhanSuaThongTinCaNhan", method = RequestMethod.POST)
	public String XacNhanSuaThongTinCaNhan(ModelMap model, HttpServletRequest request, HttpSession ss,
			@ModelAttribute("khahHang") KhachHang khachHang) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null || tk.getPhanQuyen().getMAPQ() != 3)
			return "redirect:/Login/Login.htm";
		if (tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		kh.setHO(request.getParameter("HO"));
		kh.setTEN(request.getParameter("TEN"));
		kh.setDIACHI(request.getParameter("DIACHI"));
		kh.setSDT(request.getParameter("SDT"));
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(kh);
			t.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			t.rollback();
//			return 0;
		} finally {
			session.close();
		}
//		System.out.println(tmp);
//		model.addAttribute("khacHang", khachHang);
		return "redirect:/KhachHang/XemThongTinCaNhan.htm";
	}
}
