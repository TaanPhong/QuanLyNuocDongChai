package ptithcm.controller;

import java.util.List;

import javax.naming.Context;
import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.MultipartFile;

import ptithcm.entity.Hang;
import ptithcm.entity.Loai;
import ptithcm.entity.NhanVien;
import ptithcm.entity.SanPham;
import ptithcm.entity.TaiKhoan;

@Controller
@RequestMapping("QuanLy/")
@Transactional
public class AdminSanPhamController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	Integer test = 0;
	@RequestMapping("SanPham")
	public String SanPham(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Object[]> listSP = getSanPhams();
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
		test = 0;
		return "QuanLy/SanPham";
	}

	// ====================================================== Sản Phẩm
	// =======================================//
	public List<Object[]> getSanPhams() {
		Session session = factory.getCurrentSession();
		String hql = "select SP.MASP, SP.TENSP, L.TENLOAI, H.TENHANG, SP.SLTON, SP.GIA\r\n"
				+ "from SanPham SP, Hang H, Loai L\r\n" + "where SP.loai.MALOAI  = L.MALOAI\r\n"
				+ "and SP.hang.MAHANG = H.MAHANG";

		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public SanPham getSanPham(Integer id) {
		//System.out.println(id);
		Session session = factory.getCurrentSession();
		return (SanPham) session.get(SanPham.class, id); 
	}

	public Integer ThemSanPham(SanPham sp) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(sp);
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

	public Integer XoaSanPham(Integer id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			SanPham tempSP = (SanPham) session.get(SanPham.class, id);
			session.delete(tempSP);
			t.commit();
		} catch (Exception e) {
			System.err.println(e.toString());
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}


	// ================ Thêm =====================//
	@RequestMapping(value = "ThemSanPham", method = RequestMethod.GET)
	public String ThemSanPham(ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		model.addAttribute("sanPham", new SanPham());
		model.addAttribute("hangsSel", this.getListHang());
		model.addAttribute("loaisSel", this.getLoai());
		model.addAttribute("btnStatus", "btnAdd");
		return "QuanLy/NhapSanPham";
	}

	@RequestMapping(value = "NhapSanPham", method = RequestMethod.POST)
	public String themSanPham(HttpServletRequest request, ModelMap model, @ModelAttribute("sanPham") SanPham sanPham, HttpSession ss,
			@RequestParam("hinhAnh") MultipartFile hinhAnh) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		// sanPham.setGIA(0);
		sanPham.setSLTON(0);
		if(hinhAnh.isEmpty())
			sanPham.setHINHANH(null);
		else {
			//String photoPath = context.getRealPath("/files/" + hinhAnh.getOriginalFilename());
			try {
				String photoPath = hinhAnh.getOriginalFilename();
				sanPham.setHINHANH(photoPath);
			} catch (Exception e) {
				model.addAttribute("message", "Lỗi lưu file !");
			}
			
		}
		Integer temp = this.ThemSanPham(sanPham);
		if (temp != 0) {
			test = 1;
			model.addAttribute("message", "Thêm thành công");
		} else {
			test = 2;
			model.addAttribute("message", "Thêm thất bại!");
		}

		List<Object[]> listSP = getSanPhams();
		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);

		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		System.out.println(test);
		model.addAttribute("test", test);
		test = 0;
		return "QuanLy/SanPham";
	}

	// ============================ Sửa ==============================//
	public Integer SuaSanPham(SanPham sp) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(sp);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "SanPham/{id}.htm", params = "linkEdit", method = RequestMethod.POST)
	public String SuaSanPham(ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		System.out.println("Vô rồi");
		SanPham s = this.getSanPham(id);
		model.addAttribute("sanPham", this.getSanPham(id));
		System.out.println(s);
		model.addAttribute("hangsSel", this.getListHang());
		model.addAttribute("loaisSel", this.getLoai());
		model.addAttribute("btnStatus", "linkEdit");
		return "QuanLy/NhapSanPham";
	}

	@RequestMapping(value = "NhapSanPham", params = "linkEdit", method = RequestMethod.POST)
	public String HieuChinhSanPham(HttpServletRequest request, ModelMap model, HttpSession ss,
			@ModelAttribute("sanPham") SanPham sanPham, @RequestParam(value = "id", required = true) Integer id,  @RequestParam("hinhAnh") MultipartFile hinhAnh) {
		//System.err.println(sanPham);
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		SanPham tmp = this.getSanPham(id);
		sanPham.setMASP(tmp.getMASP());
		sanPham.setSLTON(tmp.getSLTON());
		if(hinhAnh.isEmpty())
			sanPham.setHINHANH(null);
		else {
			//String photoPath = context.getRealPath("/files/" + hinhAnh.getOriginalFilename());
			try {
				String photoPath = hinhAnh.getOriginalFilename();
				sanPham.setHINHANH(photoPath);
			} catch (Exception e) {
				model.addAttribute("message", "Lỗi lưu file !");
			}
			
		}
		Integer temp = this.SuaSanPham(sanPham);
		if (temp != 0) {
			test = 1;
			model.addAttribute("message", "Sửa  thành công");
		} else {
			test = 2;
			model.addAttribute("message", "sửa thất bại!");
		}
		System.out.println(test);
		return "redirect:/QuanLy/SanPham.htm";
	}
	
	@RequestMapping(value = "SanPham/{id}.htm", params = "linkDelete", method = RequestMethod.POST)
	public String XoaSanPhams(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
//		System.out.println("Vô rồi xoa");
		SanPham sp = this.getSanPham(id);
		System.out.println(sp);
		if(!sp.getCtddh().isEmpty() || !sp.getCthd().isEmpty() || !sp.getCtpn().isEmpty())
		{
			test = 2;
			model.addAttribute("message", "Xoa thất bại không thể xóa vật phẩm đã được lập hóa đơn hoặc được nhập về!");
			List<Object[]> listSP = getSanPhams();
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
			test = 0;
			return "QuanLy/SanPham";
		}
		Integer tmp = this.XoaSanPham(id);
		System.out.println(id);
		if(tmp != 0)
		{
			test = 1;
			model.addAttribute("message", "Xoa thành công");
		}
		else
		{
			test = 2;
			model.addAttribute("message", "Xoa khong thành công");
		}
		List<Object[]> listSP = getSanPhams();
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
		test = 0;
		return "QuanLy/SanPham";
	}

//		@ModelAttribute("hangsSel")
	public List<Hang> getListHang() {
		Session session = factory.getCurrentSession();
		String hql = "from Hang";
		Query query = session.createQuery(hql);
		List<Hang> list = query.list();
		return list;
	}

	public List<Loai> getLoai() {
		Session session = factory.getCurrentSession();
		String hql = "from Loai";
		Query query = session.createQuery(hql);
		List<Loai> list = query.list();
		return list;
	}
	
	public List<Object[]> timSanPhams(String ten_sanPham) {
		Session session = factory.getCurrentSession();
		String hql = "select SP.MASP, SP.TENSP, L.TENLOAI, H.TENHANG, SP.SLTON, SP.GIA\r\n"
				+ "from SanPham SP, Hang H, Loai L\r\n" + "where SP.loai.MALOAI  = L.MALOAI\r\n"
				+ "and SP.hang.MAHANG = H.MAHANG and SP.TENSP LIKE :ten_sanPham";
		Query query = session.createQuery(hql);
		query.setParameter("ten_sanPham", "%" + ten_sanPham + "%");
		List<Object[]> list = query.list();
		
		return list;
		
	}
	
	@RequestMapping(value = "SanPham", params = "btnsearch")
	public String TimSanPham(HttpServletRequest request, ModelMap model, HttpSession ss) 
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		System.out.println("vô");
		List<Object[]> listSP = timSanPhams(request.getParameter("searchInput"));
		test = 0;
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
		return "QuanLy/SanPham";
	}
}
