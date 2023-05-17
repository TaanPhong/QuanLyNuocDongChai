package ptithcm.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.bean.Mailer;
import ptithcm.entity.*;

@Controller
@RequestMapping("QuanLy/")
@Transactional

public class AdminNhanVienController {
	@Autowired
	Mailer mailer;
	@Autowired
	SessionFactory factory;
	Integer test = 0;

	@RequestMapping("NhanVien")
	public String NhanVien(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Object[]> listKH = getNhanViens();
		PagedListHolder pagedListHolder = new PagedListHolder(listKH);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("test", test);
		test = 0;
		return "QuanLy/NhanVien";
	}

	public List<Object[]> getNhanViens() {
		Session session = factory.getCurrentSession();
		String hql = "select NV.MANV, NV.HO + ' ' + NV.TEN, NV.LUONG, NV.SDT, NV.EMAIL "
				+ "from NhanVien NV, TaiKhoan TK\r\n" + "where NV.taiKhoan.MATK = TK.MATK and TK.phanQuyen.MAPQ = 2";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public NhanVien getNhanVien(Integer id) {
		Session session = factory.getCurrentSession();
		return (NhanVien) session.get(NhanVien.class, id);
	}

	public TaiKhoan getTaiKhoan(Integer id) {
		Session session = factory.getCurrentSession();
		return (TaiKhoan) session.get(TaiKhoan.class, id);
	}

	public Integer ThemNhanVien(NhanVien nv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(nv);
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

	public Integer ThemTaiKhoan(TaiKhoan tk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(tk);
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

	public Integer XoaNhanVien(NhanVien nv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.delete(nv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public Integer SuaNhanVien(NhanVien nv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(nv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public Integer XoaTaiKhoan(TaiKhoan tk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.delete(tk);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public PhanQuyen getPhanQuyen() {
		Session session = factory.getCurrentSession();
		return (PhanQuyen) session.get(PhanQuyen.class, 2);
	}
	
	public NhanVien getQuanLy() {
		Session session = factory.getCurrentSession();
		return (NhanVien) session.get(NhanVien.class, 4);
	}
	
	// ================ Thêm =====================//
	@RequestMapping(value = "ThemNhanVien", method = RequestMethod.GET)
	public String ThemSanPham(ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		model.addAttribute("nhanVien", new NhanVien());
		model.addAttribute("taiKhoan", new TaiKhoan());
		model.addAttribute("btnStatus", "btnAdd");
		return "QuanLy/NhapNhanVien";
	}

	@RequestMapping(value = "NhapNhanVien", method = RequestMethod.POST)
	public String ThemNhanVien(HttpServletRequest request, ModelMap model, HttpSession ss,
			@ModelAttribute("nhanVien") NhanVien nhanVien, @ModelAttribute("taiKhoan") TaiKhoan taiKhoan,
			@RequestParam("ngsinh") String ngs) {
		// sanPham.setGIA(0);
		// sanPham.setSLTON(0);
//		System.out.println("Vô rồi Port");
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		System.out.println(ngs);
		PhanQuyen phanQuyen = this.getPhanQuyen();
		taiKhoan.setPhanQuyen(phanQuyen);
		taiKhoan.setDOI(1);
		String pwd = taiKhoan.getTenDN();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
			taiKhoan.setMK(this.bytesToHex(encodedhash));
		} catch (Exception e) {
			return "QuanLy/NhapNhanVien";
			// TODO: handle exception
		}
//		taiKhoan.setMK(pwd);
		nhanVien.setNhanVien(getQuanLy());
		nhanVien.setTaiKhoan(taiKhoan);
		nhanVien.setNGSINH(ngs);
		System.out.println(nhanVien.getGIOITINH());
		// System.err.print(ngs);
		// System.out.println();
		Integer tmp = this.ThemTaiKhoan(taiKhoan);
		if (tmp != 0) {
			test = 1;
			model.addAttribute("message", "Thêm thành công");
		} else {
			test = 2;
			model.addAttribute("message", "Thêm thất bại!");
			List<Object[]> listKH = getNhanViens();
			PagedListHolder pagedListHolder = new PagedListHolder(listKH);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			// SỐ page
			pagedListHolder.setMaxLinkedPages(5);
			// Số dòng
			pagedListHolder.setPageSize(10);
			// model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("test", test);
			test = 0;
			return "QuanLy/NhanVien";
		}
		Integer temp = this.ThemNhanVien(nhanVien);
		if (temp != 0) {
			test = 1;
			model.addAttribute("message", "Thêm thành công");
		} else {
			test = 2;
			model.addAttribute("message", "Thêm thất bại!");
			List<Object[]> listKH = getNhanViens();
			PagedListHolder pagedListHolder = new PagedListHolder(listKH);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			// SỐ page
			pagedListHolder.setMaxLinkedPages(5);
			// Số dòng
			pagedListHolder.setPageSize(10);
			// model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("test", test);
			test = 0;
			return "QuanLy/NhanVien";
		}
//			String body = tai
		try {
			// Gửi mail
			mailer.send(nhanVien.getEMAIL(), "Tạo Tài Khoản Thành công",
					"Bạn đã tạo tài khoản thành công");
			model.addAttribute("message", "Gửi email thành công !");
		} catch (Exception ex) {
			model.addAttribute("message", "Gửi email thất bại !");
			List<Object[]> listKH = getNhanViens();
			PagedListHolder pagedListHolder = new PagedListHolder(listKH);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			// SỐ page
			pagedListHolder.setMaxLinkedPages(5);
			// Số dòng
			pagedListHolder.setPageSize(10);
			// model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("test", test);
			test = 0;
			return "QuanLy/NhanVien";
		}
		List<Object[]> listKH = getNhanViens();
		PagedListHolder pagedListHolder = new PagedListHolder(listKH);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("test", test);
		test = 0;
		return "QuanLy/NhanVien";
	}
	// ================ Sửa =====================//

	@RequestMapping(value = "NhanVien/{id}.htm", params = "linkEdit", method = RequestMethod.GET)
	public String SuaNhanVien(ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		System.out.println("Vô rồi");
		NhanVien s = this.getNhanVien(id);
		model.addAttribute("nhanVien", this.getNhanVien(id));
		Date t = SimpleDateFormat(this.getNhanVien(id).getNGSINH());
		model.addAttribute("ngaySinh", t);
//		System.out.println(s);
		// model.addAttribute("btnStatus", "linkEdit");
		return "QuanLy/HieuChinhNhanVien";
	}

	@RequestMapping(value = "HieuChinhNhanVien", method = RequestMethod.POST)
	public String HieuChinhNhanVien(HttpServletRequest request, ModelMap model, HttpSession ss,
			@ModelAttribute("nhanVien") NhanVien nhanVien, @RequestParam("ngsinh") String ngs) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		System.out.println("Vô rồi Hiệu Chỉnh Port");
		nhanVien.setNGSINH(ngs);
		NhanVien nv = getNhanVien(nhanVien.getMANV());
		TaiKhoan t = nv.getTaiKhoan();
		nhanVien.setTaiKhoan(t);
		nhanVien.setNhanVien(nv.getNhanVien());
		Integer temp = this.SuaNhanVien(nhanVien);
		if (temp != 0) {
			test = 1;
			model.addAttribute("message", "Sửa thành công");
		} else {
			test = 2;
			model.addAttribute("message", "Sửa thất bại!");
			List<Object[]> listKH = getNhanViens();
			PagedListHolder pagedListHolder = new PagedListHolder(listKH);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			// SỐ page
			pagedListHolder.setMaxLinkedPages(5);
			// Số dòng
			pagedListHolder.setPageSize(10);
			// model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("test", test);
			test = 0;
			return "QuanLy/NhanVien";
		}
		try {
			test = 1;
			mailer.send(nhanVien.getEMAIL(), "Hiệu chỉnh Tài Khoản Thành công", "Thông tin của bạn đã được thay đổi!");
			model.addAttribute("message", "Gửi email thành công !");
		} catch (Exception ex) {
			test = 2;
			model.addAttribute("message", "Gửi email thất bại !");
			List<Object[]> listKH = getNhanViens();
			PagedListHolder pagedListHolder = new PagedListHolder(listKH);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			// SỐ page
			pagedListHolder.setMaxLinkedPages(5);
			// Số dòng
			pagedListHolder.setPageSize(10);
			// model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("test", test);
			test = 0;
			return "QuanLy/NhanVien";
		}
		List<Object[]> listKH = getNhanViens();
		PagedListHolder pagedListHolder = new PagedListHolder(listKH);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("test", test);
		test = 0;
		return "QuanLy/NhanVien";
	}

	private Date SimpleDateFormat(String ngsinh) {
		// TODO Auto-generated method stub
		return null;
	}

	// ================================== Xóa
	// ======================================//

	@RequestMapping(value = "NhanVien/{id}.htm", params = "linkDelete", method = RequestMethod.GET)
	public String XoaNhanVien(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		System.out.println("Vô Xoa");
//		NhanVien nv = this.getNhanVien(id);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		NhanVien nv = (NhanVien) session.get(NhanVien.class, id);
		if(!nv.getHoaDon().isEmpty() || !nv.getPhieuNhap().isEmpty())
		{
			test = 2;
			model.addAttribute("message", "Nhân viên này đã lập hóa đơn hoặc phiếu nhập không thể xóa!");
			List<Object[]> listKH = getNhanViens();
			PagedListHolder pagedListHolder = new PagedListHolder(listKH);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			// SỐ page
			pagedListHolder.setMaxLinkedPages(5);
			// Số dòng
			pagedListHolder.setPageSize(10);
			// model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("test", test);
			test = 0;
			return "QuanLy/NhanVien";
//			return "redirect:/QuanLy/NhanVien.htm";
		}
		
		Integer temp = 0;
		try {
			temp = 1;
			session.delete(nv);
			t.commit();
		}
		catch (Exception e){
			t.rollback();
		}
		finally {
			session.close();
		}
		System.out.println(temp);
		if (temp != 0) {
			test = 1;
			model.addAttribute("message", "Xóa thành công");
		} else {
			test = 2;
			model.addAttribute("message", "Xóa thất bại!");
			List<Object[]> listKH = getNhanViens();
			PagedListHolder pagedListHolder = new PagedListHolder(listKH);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			// SỐ page
			pagedListHolder.setMaxLinkedPages(5);
			// Số dòng
			pagedListHolder.setPageSize(10);
			// model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("test", test);
			test = 0;
			return "QuanLy/NhanVien";
		}
		session = factory.openSession();
		t = session.beginTransaction();
		Integer tmp = 0;
		try {
			TaiKhoan tK = (TaiKhoan) session.get(TaiKhoan.class, nv.getTaiKhoan().getMATK());
			tmp = 1;
			session.delete(tK);
			t.commit();
		}
		catch (Exception e){
			t.rollback();
		}
		finally {
			session.close();
		}
		if (tmp != 0) {
			test = 1;
			model.addAttribute("message", "Xóa thành công");
		} else {
			test = 2;
			model.addAttribute("message", "Xóa thất bại!");
			List<Object[]> listKH = getNhanViens();
			PagedListHolder pagedListHolder = new PagedListHolder(listKH);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			// SỐ page
			pagedListHolder.setMaxLinkedPages(5);
			// Số dòng
			pagedListHolder.setPageSize(10);
			// model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("test", test);
			test = 0;
			return "QuanLy/NhanVien";
		}
		List<Object[]> listKH = getNhanViens();
		PagedListHolder pagedListHolder = new PagedListHolder(listKH);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("test", test);
		test = 0;
		return "QuanLy/NhanVien";
//		return "QuanLy/NhanVien";
	}

	// ================================== Tìm Kiếm
	// =================================//

	public List<Object[]> timNhanViens(String ten_nhanVien) {
		Session session = factory.getCurrentSession();
		String hql = "select NV.MANV, NV.HO + ' ' + NV.TEN as HOTEN, NV.LUONG, NV.SDT, NV.EMAIL "
				+ "from NhanVien NV, TaiKhoan TK\r\n"
				+ "where NV.taiKhoan.MATK = TK.MATK and TK.phanQuyen.MAPQ = 2 and NV.TEN LIKE :ten_nhanVien";
		Query query = session.createQuery(hql);
		query.setParameter("ten_nhanVien", "%" + ten_nhanVien + "%");
		List<Object[]> list = query.list();
		return list;

	}

	@RequestMapping(value = "NhanVien", params = "btnsearch")
	public String TimSanPham(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		System.out.println("vô");
		test = 0;
		List<Object[]> listSP = timNhanViens(request.getParameter("searchInput"));

		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("test", test);
		return "QuanLy/NhanVien";
	}

	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
