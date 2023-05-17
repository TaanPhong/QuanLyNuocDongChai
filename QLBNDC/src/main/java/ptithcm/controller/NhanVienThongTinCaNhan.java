package ptithcm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.bean.LapPhieuNhap;
import ptithcm.entity.CTHD;
import ptithcm.entity.CTPN;
import ptithcm.entity.Hang;
import ptithcm.entity.HoaDon;
import ptithcm.entity.NhanVien;
import ptithcm.entity.PhieuNhap;
import ptithcm.entity.SanPham;
import ptithcm.entity.TaiKhoan;

@Controller
@RequestMapping("NhanVien/")
@Transactional
public class NhanVienThongTinCaNhan {
	@Autowired
	SessionFactory factory;
	// ======================== Nhân Viên =======================//
	public NhanVien getNV(Integer id) {
		// System.out.println(id);
		Session session = factory.getCurrentSession();
		String hql = "from NhanVien where MATK = " + id;
		Query query = session.createQuery(hql);
		NhanVien nv = (NhanVien) query.list().get(0);
		return nv;
	}

	@RequestMapping("XemThongTinCaNhan")
	public String XemThongTin(ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if (tk == null)
			return "redirect:/Login/Login.htm";
		NhanVien nv = getNV(tk.getMATK());
		String hoTen = nv.getHO() + nv.getTEN();
		model.addAttribute("nhanVien", nv);
		model.addAttribute("hoTen", hoTen);
		return "NhanVien/XemThongTin";
	}
}
