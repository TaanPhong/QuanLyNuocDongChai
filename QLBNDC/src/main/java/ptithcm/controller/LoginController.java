package ptithcm.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.bean.Mailer;
import ptithcm.entity.*;

@Controller
@RequestMapping("Login/")
@Transactional
public class LoginController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	Integer cap = 0;
	int dem = 0;

	@RequestMapping(value = "Login", method = RequestMethod.GET)
	public String Login(ModelMap model) {
		model.addAttribute("cap", cap);
		return "Login/login";
	}

	@RequestMapping(value = "Sig_in", method = RequestMethod.POST)
	public String SigIn(ModelMap model, HttpSession session, HttpServletRequest request,
			@Validated @ModelAttribute("taiKhoan") TaiKhoan taiKhoan, BindingResult errors) {
		if (cap > 3) {
			String captcha = session.getAttribute("captcha_security").toString();
			String verifyCaptcha = request.getParameter("captcha");
			boolean kt = false;
			if (captcha.equals(verifyCaptcha))
				kt = true;
			else
				kt = false;
			if (errors.hasErrors() || !kt) {
				if (!kt)
					model.addAttribute("reCatra", "Vui lòng nhập reCaptra");
				return "Login/login";
			}
		}

		try {

			String pw = request.getParameter("pwd").trim();
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(pw.getBytes(StandardCharsets.UTF_8));
//				System.out.println(this.bytesToHex(encodedhash));

			TaiKhoan tmp = getTK(request.getParameter("tenDN"), this.bytesToHex(encodedhash).trim());
			if (tmp != null) {
				session.setAttribute("tai_Khoan", tmp);
				if (tmp.getPhanQuyen().getMAPQ() == 1) {
					// session.setAttribute("tk_QL", this.getNV(tmp.getMATK()));
					return "redirect:/QuanLy/TrangChu.htm";
				} else if (tmp.getPhanQuyen().getMAPQ() == 2) {
					NhanVien nv = getNV(tmp.getMATK());
					if(nv.getTRANGTHAI() == false)
					{
						model.addAttribute("message", "Tài khoản khoản của bạn đã bị khóa");
						return "Login/login";
						//return 
					}
					// session.setAttribute("tk_NV", this.getNV(tmp.getMATK()));
					return "redirect:/NhanVien/HoaDon.htm";
				} else if (tmp.getPhanQuyen().getMAPQ() == 3) {

					// session.setAttribute("tk_KH", this.getKH(tmp.getMATK()));
					return "redirect:/KhachHang/TrangChu.htm";
//						session.getAttribute("")
				} else {
					return "redirect:/Login/Login.htm";
				}
			}
			errors.rejectValue("TenDN", "taiKhoan", "Tài khoản hoặc mật khẩu không đúng!");
			errors.rejectValue("MK", "taiKhoan", "Tài khoản hoặc mật khẩu không đúng!");
//			model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác!");
			
			
			return "Login/login";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác!");
			dem++;
			if(dem == 3)
				cap = 4;
			System.out.println("vo !!!!");
			model.addAttribute("cap", cap);
			return "Login/login";
		}
//		return "Login/login";
	}
	
	

	public TaiKhoan getTK(String tenDN, String pwd) {
		Session session = factory.getCurrentSession();
		String hql = "from TaiKhoan where TenDN = '" + tenDN + "'" + " and MK = '" + pwd + "'";
		Query query = session.createQuery(hql);
		TaiKhoan tk = (TaiKhoan) query.list().get(0);
//		System.out.println("1");
		return tk;
	}

	public NhanVien getNV(Integer Id) {
		Session session = factory.getCurrentSession();
		String hql = "from NhanVien where MATK = " + Id;
		Query query = session.createQuery(hql);
		NhanVien nv = (NhanVien) query.list().get(0);
		return nv;
	}

	public KhachHang getKH(Integer Id) {
		Session session = factory.getCurrentSession();
		String hql = "from KhachHang where MATK = " + Id;
		Query query = session.createQuery(hql);
		KhachHang kh = (KhachHang) query.list().get(0);
		return kh;
	}

	public TaiKhoan getTKQ(String tenDN) {
		Session session = factory.getCurrentSession();
		String hql = "from TaiKhoan where TenDN = '" + tenDN + "'";
		Query query = session.createQuery(hql);
		if(query.list().size() == 0)
			return null;
		TaiKhoan list = (TaiKhoan) query.list().get(0);
		return list;
	}

	@RequestMapping("LogOut")
	public String LogOut(HttpSession ss) {
		cap = 0;
		dem = 0;
		ss.removeAttribute("tai_Khoan");
		return "redirect:/Home/index.htm";
	}

	// ========================= Đăng Ký =============================//

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

	public Integer HieuChinhTaiKhoan(TaiKhoan tk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(tk);
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

	public Integer ThemKhachHang(KhachHang kh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(kh);
			t.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public PhanQuyen getPhanQuyen() {
		Session session = factory.getCurrentSession();
		return (PhanQuyen) session.get(PhanQuyen.class, 3);
	}

	@RequestMapping(value = "DangKy", method = RequestMethod.GET)
	public String DangKy(ModelMap model) {
		model.addAttribute("khachHang", new KhachHang());
		model.addAttribute("taiKhoan", new TaiKhoan());
		return "Login/DangKy";
	}

	@RequestMapping(value = "TaoTaiKhoan", method = RequestMethod.POST)
	public String TaoTaiKhoan(HttpServletRequest request, ModelMap model, HttpSession session,
			@ModelAttribute("khachHang") KhachHang khachHang, @ModelAttribute("taiKhoan") TaiKhoan taiKhoan,
			@RequestParam("ngsinh") String ngs, @RequestParam("pwd") String pwd, @RequestParam("pwd1") String pwd1) {
		if (!pwd.equals(pwd1)) {
			// System.out.println();
			return "Login/DangKy";
		}
		PhanQuyen phanQuyen = this.getPhanQuyen();
		// System.out.println(phanQuyen.getMAPQ());
		taiKhoan.setPhanQuyen(phanQuyen);
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
			taiKhoan.setMK(this.bytesToHex(encodedhash));
			taiKhoan.setDOI(0);
		} catch (Exception e) {
			return "Login/DangKy";
			// TODO: handle exception
		}

		khachHang.setTaiKhoan(taiKhoan);
		khachHang.setNGSINH(ngs);
//		khachHang.setDIEMTL(0);
		Integer tmp = this.ThemTaiKhoan(taiKhoan);
		if (tmp != 0) {
			model.addAttribute("message", "Thêm thành công");
		} else {
			model.addAttribute("message", "Thêm thất bại!");
			return "Login/DangKy";
		}
		Integer temp = this.ThemKhachHang(khachHang);
		if (temp != 0) {
			System.out.println("thành công");
			model.addAttribute("message", "Thêm thành công");

		} else {
			System.out.println("Thấy bại");
			model.addAttribute("message", "Thêm thất bại!");
			return "Login/DangKy";
		}
//			String body = tai
		try {
			// Gửi mail
			mailer.send(khachHang.getEMAIL(), "Tạo Tài Khoản Thành công",
					"Tên đăng nhập: " + taiKhoan.getTenDN() + "\n" + "Mật khẩu: " + taiKhoan.getMK());
			model.addAttribute("message", "Gửi email thành công !");
		} catch (Exception ex) {
			model.addAttribute("message", "Gửi email thất bại !");
			return "Login/DangKy";
		}
		session.setAttribute("tai_khoan", taiKhoan);
		return "redirect:/KhachHang/TrangChu.htm";
	}

	// ==================================== Đổi Mật Khẩu
	// =========================================//

	@RequestMapping("DoiMK")
	public String DoiMatKhau() {
		return "Login/DoiMatKhau";
	}

	@RequestMapping(value = "DoiMatKhau", method = RequestMethod.POST)
	public String DoiMatKhau(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam("pwd") String pwd, @RequestParam("pwd1") String pwd1, @RequestParam("pwd2") String pwd2) {
		TaiKhoan t = (TaiKhoan) session.getAttribute("tai_Khoan");
		if (t == null)
			return "redirect:/Login/Login.htm";
		String s = t.getMK().trim();
		Integer ts = 0;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
			System.out.println(bytesToHex(encodedhash) + "p");
			if (!s.equals(this.bytesToHex(encodedhash))) {
				System.out.println("Vô sai mật khẩu");
				ts = 1;
				System.out.println(ts);
				model.addAttribute("ts", ts);
				model.addAttribute("messgase", "Sai mật khẩu.");
				return "Login/DoiMatKhau";
			}
		} catch (Exception e) {
			return "Login/DoiMatKhau";
			// TODO: handle exception
		}
		if (pwd.equals(pwd1) || pwd.equals(pwd2)) {
			// System.out.println();
			ts = 2;
			model.addAttribute("ts", ts);
			model.addAttribute("messgase", "Mật khẩu mới phải khác mật khẩu cũ.");
			return "Login/DoiMatKhau";
		}
		if (!pwd1.equals(pwd2)) {
			ts = 3;
			model.addAttribute("ts", ts);
			model.addAttribute("messgase", "Xác minh mật khẩu không khớp với mật khẩu bạn đã nhập.");
			return "Login/DoiMatKhau";
		}
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(pwd2.getBytes(StandardCharsets.UTF_8));
			t.setMK(this.bytesToHex(encodedhash));
			t.setDOI(0);
			Integer tmp = this.HieuChinhTaiKhoan(t);
			System.out.println(tmp);
			if (tmp != 0) {
				System.out.println("thành công");
				model.addAttribute("message", "Thêm thành công");

			} else {
				System.out.println("Thấy bại");
				model.addAttribute("message", "Thêm thất bại!");
				return "Login/DoiMatKhau";
			}
		} catch (Exception e) {
			return "Login/DoiMatKhau";
			// TODO: handle exception
		}
		if (t.getPhanQuyen().getMAPQ() == 1 || t.getPhanQuyen().getMAPQ() == 2) {
			System.out.println("Vô 5");
			NhanVien nv = getNV(t.getMATK());
			try {
				// Gửi mail
				mailer.send(nv.getEMAIL(), "Đổi mật khẩu thành công", "Mật khẩu mới " + t.getMK());
				model.addAttribute("message", "Gửi email thành công !");
			} catch (Exception ex) {
				model.addAttribute("message", "Gửi email thất bại !");
				return "Login/DoiMatKhau";
			}
			if (t.getPhanQuyen().getMAPQ() == 1)
				return "redirect:/QuanLy/TrangChu.htm";
			else
				return "redirect:/NhanVien/TrangChu.htm";
		} else {
			KhachHang kh = getKH(t.getMATK());
			try {
				// Gửi mail
				mailer.send(kh.getEMAIL(), "Đổi mật khẩu thành công", "Mật khẩu mới " + t.getMK());
				model.addAttribute("message", "Gửi email thành công !");
			} catch (Exception ex) {
				model.addAttribute("message", "Gửi email thất bại !");
				return "Login/DoiMatKhau";
			}
			return "redirect:/KhachHang/TrangChu.htm";
		}
//		return "";
	}

	// =============================== Quên Mật khẩu
	// =====================================//
	@RequestMapping(value = "QuenMK")
	public String QuenMatKhau() {
		return "Login/QuenMatKhau";
	}

	@RequestMapping(value = "QuenMatKhau", method = RequestMethod.POST)
	public String GuiMatKhau(HttpServletRequest request, ModelMap model, @RequestParam("mail") String mail,
			@RequestParam("sdt") String sdt, @RequestParam("tenDN") String tenDN) {
		System.out.println(tenDN + "p");
		TaiKhoan tk = this.getTKQ(tenDN);
		Integer ts = 0;
		if (tk == null) {
			ts = 3;
			model.addAttribute("ts", ts);
			model.addAttribute("messgase", "Sai tên đăng nhập");
			return "Login/QuenMatKhau";
		}
//		System.out.println(tk);
		
		if (tk != null) {
			if (tk.getPhanQuyen().getMAPQ() != 3) {
				NhanVien nv = getNV(tk.getMATK());
				if(!nv.getEMAIL().trim().equals(mail))
				{
					ts = 1;
					model.addAttribute("ts", ts);
					model.addAttribute("messgase", "email không chính xác!");
					return "Login/QuenMatKhau";
				}
				if(!nv.getSDT().trim().equals(sdt))
				{
					ts = 2;
					model.addAttribute("ts", ts);
					model.addAttribute("messgase", "số điện thoại không chính xác!");
					return "Login/QuenMatKhau";
				}
				try {
					// Gửi mail
					mailer.send(nv.getEMAIL(), "Mật khẩu của bạn là", "Mật khẩu mới: " + tenDN);
					model.addAttribute("messgase", "Gửi email thành công !");
				} catch (Exception ex) {
					model.addAttribute("messgase", "Gửi email thất bại !");
					return "Login/QuenMatKhau";
				}
			} else {
				KhachHang kh = getKH(tk.getMATK());
				if(!kh.getEMAIL().trim().equals(mail))
				{
					ts = 1;
					model.addAttribute("ts", ts);
					model.addAttribute("messgase", "email không chính xác!");
					return "Login/QuenMatKhau";
				}
				if(!kh.getSDT().trim().equals(sdt))
				{
					ts = 2;
					model.addAttribute("ts", ts);
					model.addAttribute("messgase", "số điện thoại không chính xác!");
					return "Login/QuenMatKhau";
				}
				try {
					// Gửi mail
					mailer.send(kh.getEMAIL(), "Mật khẩu của bạn là", "Mật khẩu mới: " + tenDN);
					model.addAttribute("messgase", "Gửi email thành công !");
				} catch (Exception ex) {
					model.addAttribute("messgase", "Gửi email thất bại !");
					return "Login/QuenMatKhau";
				}
			}
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] encodedhash = digest.digest(tenDN.getBytes(StandardCharsets.UTF_8));
				tk.setMK(this.bytesToHex(encodedhash));
				tk.setDOI(1);
//			System.out.println("Vô 4");
				Integer tmp = this.HieuChinhTaiKhoan(tk);
				System.out.println(tmp);
				if (tmp != 0) {
//				System.out.println("thành công");
					model.addAttribute("messgase", "Thêm thành công");
					return "redirect:/Login/Login.htm";

				} else {
//				System.out.println("Thấy bại");
					ts = 0;
					model.addAttribute("ts", ts);
					model.addAttribute("messgase", "Thêm thất bại!");
					return "Login/QuenMatKhau";
				}
			} catch (Exception e) {
				ts = 0;
				model.addAttribute("ts", ts);
				model.addAttribute("messgase", "Thêm thất bại!");
				return "Login/QuenMatKhau";
				// TODO: handle exception
			}
		}
		ts = 0;
		model.addAttribute("ts", ts);
		model.addAttribute("message", "Thêm thất bại!");
		return "Login/QuenMatKhau";
	}

	// ============================================================================================//
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
