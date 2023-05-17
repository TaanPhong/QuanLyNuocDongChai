package ptithcm.controller;

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
import org.springframework.web.multipart.MultipartFile;

import ptithcm.bean.Mailer;
import ptithcm.entity.*;

@Controller
@RequestMapping("QuanLy/")
@Transactional
public class AdminLoaiController {
	@Autowired
	SessionFactory factory;
	Integer test = 0;

	public List<Loai> getLoais() {
		Session session = factory.getCurrentSession();
		String hql = "from Loai";
		Query query = session.createQuery(hql);
		List<Loai> list = query.list();
		return list;
	}

	public List<Loai> timLoais(String ten_loai) {
		Session session = factory.getCurrentSession();
		String hql = "from Loai where TENLOAI LIKE :ten_loai";
		Query query = session.createQuery(hql);
		query.setParameter("ten_loai", "%" + ten_loai + "%");
		List<Loai> list = query.list();
		return list;

	}
	public Loai getLoai(Integer id) {
		Session session = factory.getCurrentSession();
		return (Loai) session.get(Loai.class, id);
	}

	public Integer ThemLoai(Loai h) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(h);
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

	public Integer HieuChinhLoai(Loai h) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(h);
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

	public Integer XoaLoai(Integer id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Hang temp = (Hang) session.get(Hang.class, id);
			session.delete(temp);
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

	@RequestMapping("Loai")
	public String Loai(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Loai> listLoai = getLoais();
		PagedListHolder pagedListHolder = new PagedListHolder(listLoai);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		if(test == 1)
			model.addAttribute("message", "Hiệu chỉnh thành công");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("test", test);
		test = 0;
		return "QuanLy/Loai";
	}
	
	@RequestMapping(value =  "Loai", params = "btnsearch")
	public String TimKiemLoai(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Loai> listLoai = timLoais(request.getParameter("searchInput"));
		PagedListHolder pagedListHolder = new PagedListHolder(listLoai);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		if(test == 1)
			model.addAttribute("message", "Hiệu chỉnh thành công");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("test", test);
		test = 0;
		return "QuanLy/Loai";
	}

	// ================ Thêm =====================//
	@RequestMapping(value = "ThemLoai", method = RequestMethod.GET)
	public String ThemHang(ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		model.addAttribute("loai", new Loai());
		model.addAttribute("btnStatus", "btnAdd");
		return "QuanLy/NhapLoai";
	}

	@RequestMapping(value = "NhapLoai", method = RequestMethod.POST)
	public String themSanPham(HttpServletRequest request, ModelMap model, @ModelAttribute("loai") Loai loai,
			HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		test = 0;
		Integer temp = this.ThemLoai(loai);
		if (temp != 0) {
			test = 1;
			model.addAttribute("message", "Thêm thành công");
		} else {
			test = 2;
			model.addAttribute("message", "Thêm thất bại!");
			List<Loai> listLoai = getLoais();
			PagedListHolder pagedListHolder = new PagedListHolder(listLoai);
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
			return "QuanLy/Loai";
		}
		List<Loai> listLoai = getLoais();
		PagedListHolder pagedListHolder = new PagedListHolder(listLoai);
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
		return "QuanLy/Loai";
	}

	// ============================= Sửa ===================================//
	@RequestMapping(value = "Loai/{id}.htm", params = "linkEdit", method = RequestMethod.POST)
	public String SuaHang(ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		Loai h = this.getLoai(id);
		model.addAttribute("loai", h);
		model.addAttribute("btnStatus", "linkEdit");
		return "QuanLy/NhapLoai";
	}

	@RequestMapping(value = "NhapLoai", params = "linkEdit", method = RequestMethod.POST)
	public String HieuChinhSanPham(HttpServletRequest request, ModelMap model, HttpSession ss,
			@ModelAttribute("loai") Loai loai, @RequestParam(value = "id", required = true) Integer id) {
		// System.err.println(sanPham);
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		test = 0;
		Loai tmp = this.getLoai(id);
		loai.setMALOAI(tmp.getMALOAI());
		Integer temp = this.HieuChinhLoai(loai);
		if (temp != 0) {
			test = 1;
			model.addAttribute("message", "Sửa  thành công");
		} else {
			test = 2;
			model.addAttribute("message", "sửa thất bại!");
			List<Loai> listLoai = getLoais();
			PagedListHolder pagedListHolder = new PagedListHolder(listLoai);
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
			return "QuanLy/Loai";
		}
		return "redirect:/QuanLy/Loai.htm";
	}

	// ========================== Xóa =====================================//
	@RequestMapping(value = "Loai/{id}.htm", params = "linkDelete", method = RequestMethod.POST)
	public String XoaSanPhams(ModelMap model, @PathVariable("id") Integer id, HttpSession ss, HttpServletRequest request) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Loai temp = (Loai) session.get(Loai.class, id);
			if (!temp.getSanpham().isEmpty()) {
				test = 2;
				model.addAttribute("message", "Loại sản phẩm này đã tồn tại sản phẩm!");
				List<Loai> listLoai = getLoais();
				PagedListHolder pagedListHolder = new PagedListHolder(listLoai);
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
				return "QuanLy/Loai";
			}
			session.delete(temp);
			t.commit();
			test = 1;
			model.addAttribute("message", "Xóa thành công!");
		} catch (Exception e) {
			// TODO: handle exception
			test = 2;
			System.out.println(e.toString());
			t.rollback();
			model.addAttribute("message", "Xóa thất bại!");
			List<Loai> listLoai = getLoais();
			PagedListHolder pagedListHolder = new PagedListHolder(listLoai);
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
			return "QuanLy/Loai";
		} finally {
			session.close();
		}
//		test = 2;
//		model.addAttribute("message", "Loại sản phẩm này đã tồn tại sản phẩm!");
		List<Loai> listLoai = getLoais();
		PagedListHolder pagedListHolder = new PagedListHolder(listLoai);
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
		return "QuanLy/Loai";
	}
}
