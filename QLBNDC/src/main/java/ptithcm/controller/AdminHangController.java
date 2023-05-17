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
public class AdminHangController {
	@Autowired
	SessionFactory factory;
	Integer test = 0;
	public List<Hang> TimHangs(String ten_hang) {
		Session session = factory.getCurrentSession();
		String hql = "from Hang where TENHANG LIKE :ten_hang";
		Query query = session.createQuery(hql);
		query.setParameter("ten_hang", "%" + ten_hang + "%");
		List<Hang> list = query.list();
		return list;
	}
	
	public List<Hang> getHangs() {
		Session session = factory.getCurrentSession();
		String hql = "from Hang";
		Query query = session.createQuery(hql);
		List<Hang> list = query.list();
		return list;
	}
	
	public Hang getHang(Integer id) {
		Session session = factory.getCurrentSession();
		return (Hang) session.get(Hang.class, id);
	}

	public Integer ThemHang(Hang h) {
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

	public Integer HieuChinhHang(Hang h) {
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

	public Integer XoaHang(Integer id) {
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

	@RequestMapping("Hang")
	public String Hang(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Hang> listHang = getHangs();
		PagedListHolder pagedListHolder = new PagedListHolder(listHang);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		if(test == 1)
			model.addAttribute("message", "Hiệu Chỉnh thành công");
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("test", test);
		test = 0;
		return "QuanLy/Hang";
	}
	
	@RequestMapping(value = "Hang", params = "btnsearch")
	public String TimHang(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Hang> listHang = TimHangs(request.getParameter("searchInput"));
		PagedListHolder pagedListHolder = new PagedListHolder(listHang);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		if(test == 1)
			model.addAttribute("message", "Hiệu Chỉnh thành công");
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("test", test);
		test = 0;
		return "QuanLy/Hang";
	}

	// ================ Thêm =====================//
	@RequestMapping(value = "ThemHang", method = RequestMethod.GET)
	public String ThemHang(ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		model.addAttribute("hang", new Hang());
		model.addAttribute("btnStatus", "btnAdd");
		return "QuanLy/NhapHang";
	}

	@RequestMapping(value = "NhapHang", method = RequestMethod.POST)
	public String themSanPham(HttpServletRequest request, ModelMap model, @ModelAttribute("hang") Hang hang,
			HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		test = 0;
		Integer temp = this.ThemHang(hang);
		if (temp != 0) {
			test = 1;
			model.addAttribute("message", "Thêm thành công");
		} else {
			test = 2;
			model.addAttribute("message", "Thêm thất bại!");
			List<Hang> listHang = getHangs();
			PagedListHolder pagedListHolder = new PagedListHolder(listHang);
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
			return "QuanLy/Hang";
		}
		List<Hang> listHang = getHangs();
		PagedListHolder pagedListHolder = new PagedListHolder(listHang);
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
		return "QuanLy/Hang";
	}

	// ============================= Sửa ===================================//
	@RequestMapping(value = "Hang/{id}.htm", params = "linkEdit", method = RequestMethod.POST)
	public String SuaHang(ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		Hang h = this.getHang(id);
		model.addAttribute("hang", h);
		model.addAttribute("btnStatus", "linkEdit");
		return "QuanLy/NhapHang";
	}

	@RequestMapping(value = "NhapHang", params = "linkEdit", method = RequestMethod.POST)
	public String HieuChinhSanPham(HttpServletRequest request, ModelMap model, HttpSession ss,
			@ModelAttribute("hang") Hang hang, @RequestParam(value = "id", required = true) Integer id) {
		// System.err.println(sanPham);
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		Hang tmp = this.getHang(id);
		hang.setMAHANG(tmp.getMAHANG());
		Integer temp = this.HieuChinhHang(hang);
		System.out.println("Sửa: " + 1);
		test = 0;
		if (temp != 0) {
			test = 1;
			model.addAttribute("message", "Sửa  thành công");
		} else {
			test = 2;
			model.addAttribute("message", "sửa thất bại!");
			List<Hang> listHang = getHangs();
			PagedListHolder pagedListHolder = new PagedListHolder(listHang);
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
			return "QuanLy/Hang";	
		}
		
		return "redirect:/QuanLy/Hang.htm";
	}

	// ========================== Xóa =====================================//
	@RequestMapping(value = "Hang/{id}.htm", params = "linkDelete", method = RequestMethod.POST)
	public String XoaSanPhams(ModelMap model, @PathVariable("id") Integer id, HttpSession ss, HttpServletRequest request) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 1)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		test = 0;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Hang temp = (Hang) session.get(Hang.class, id);
			if (!temp.getSanPham().isEmpty())
			{
				test = 2;
				model.addAttribute("message", "Nhãn hàng này đã tồn tại sản phẩm không thể xóa!");
				List<Hang> listHang = getHangs();
				PagedListHolder pagedListHolder = new PagedListHolder(listHang);
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
				return "QuanLy/Hang";
			}
//				return "redirect:/QuanLy/Hang.htm";
			test = 1;
			session.delete(temp);
			t.commit();
			model.addAttribute("message", "Xóa thành công");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			test = 2;
			t.rollback();
			model.addAttribute("message", "Xóa thất bại!");
			List<Hang> listHang = getHangs();
			PagedListHolder pagedListHolder = new PagedListHolder(listHang);
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
			return "QuanLy/Hang";
		} finally {
			session.close();
		}
		List<Hang> listHang = getHangs();
		PagedListHolder pagedListHolder = new PagedListHolder(listHang);
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
		return "QuanLy/Hang";
	}
}
