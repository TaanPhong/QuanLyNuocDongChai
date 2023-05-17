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
public class NhanVienPhieuNhapController {
	@Autowired
	SessionFactory factory;
	List<SanPham> listSP = new ArrayList<>();
	List<Integer> listSL = new ArrayList<>();
	List<Integer> listGia = new ArrayList<>();
	List<CTPN> listCTPN = new  ArrayList<>();
	Integer Id = 0;
	Integer IdPN = 0;
	boolean dangThem = false;
	boolean dangSua = false;
	// ======================== Sản Phẩm ============================//
	public SanPham getSanPham(Integer id) {
		//System.out.println(id);
		Session session = factory.getCurrentSession();
		return (SanPham) session.get(SanPham.class, id); 
	}
	
	public List<Integer> LayMaSP(Integer id){
		Session session = factory.getCurrentSession();
		String hql = "select CT.sanPham.MASP from CTPN CT where CT.phieuNhap.MAPN = " + id;
		Query query = session.createQuery(hql);
		List<Integer> list = query.list();
		return list;
	}
	
	// ======================== Nhân Viên =======================//
	public NhanVien getNV(Integer id) {
		//System.out.println(id);
		Session session = factory.getCurrentSession();
		String hql = "from NhanVien where MATK = " + id;
		Query query = session.createQuery(hql);
		NhanVien nv = (NhanVien)query.list().get(0);
		return nv;
	}
	
	// ================== Phiếu Nhập =============================//
	public PhieuNhap getPhieuNhap(Integer id) {
		Session session = factory.getCurrentSession();
		return (PhieuNhap) session.get(PhieuNhap.class, id);
	}
	
	public List<PhieuNhap> getPhieuNhaps(Integer id){
		String ngay = java.time.LocalDate.now().toString();
		Session session = factory.getCurrentSession();
		String hql = "from PhieuNhap where NGAYNHAP = '" + ngay + "' and MANV = " + id;
		Query query = session.createQuery(hql);
		List<PhieuNhap> list = query.list();
		return list;
	}
	
	public List<PhieuNhap> getTimPhieuNhaps(Integer id, Integer idPN){
		String ngay = java.time.LocalDate.now().toString();
		Session session = factory.getCurrentSession();
		String hql = "from PhieuNhap where NGAYNHAP = '" + ngay + "' and MANV = " + id + " and MAPN = " + idPN;
		Query query = session.createQuery(hql);
		List<PhieuNhap> list = query.list();
		return list;
	}
	
	public Integer ThemPN(PhieuNhap pn) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(pn);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	public Integer XoaPN(PhieuNhap pn) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.delete(pn);
			t.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	
	
	// ======================= CTPN ==============================//
	
	public void getCTPNs(Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "from CTPN CT where CT.phieuNhap.MAPN = " + id;
		Query query = session.createQuery(hql);
		listCTPN = query.list();
		//session.close();
	}
	
	public CTPN getCTPN(Integer id, Integer idsp) {
		//System.out.println(id);
		Session session = factory.getCurrentSession();
		String hql = "from CTPN CT where CT.phieuNhap.MAPN = " + id
					+ " and CT.sanPham.MASP = " + idsp;
		Query query = session.createQuery(hql);
		CTPN list = (CTPN)query.list().get(0);
		return list;
	}
	
	public Integer ThemCTPN(CTPN ctpn) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(ctpn);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	
	public Integer HieuChinhCTPN(CTPN ctpn) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(ctpn);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	public Integer XoaCTPN(CTPN ctpn) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.delete(ctpn);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	
	@RequestMapping("PhieuNhap")
	public String TrangChu(ModelMap model, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		if(dangSua == true)
		{
			listSP.clear();
			listGia.clear();
			listSL.clear();
			dangSua = false;
		}
		model.addAttribute("id", Id);
		model.addAttribute("sp", this.getSanPham(Id));
		model.addAttribute("listSP", listSP);
		model.addAttribute("listSL", listSL);
		model.addAttribute("listGia", listGia);
		return "NhanVien/PhieuNhap"; 
	}
	
	
	
	
	//===================================== Thêm Phiếu Nhập ===================================//
	
	@RequestMapping(value = "PhieuNhap", params = "btnsearch", method = RequestMethod.POST)
	public String TimSanPhamHD(ModelMap model, @RequestParam("masp") Integer id, HttpServletRequest request, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		String s = "";
		int i = 0;
		//listSP.add(getSanPham(id));
		if(id == null) {
			Id = 0;
			for(SanPham sp: listSP)
			{
				s = "SL_" + sp.getMASP().toString();
				Integer x = Integer.valueOf(request.getParameter(s)); 
				listSL.set(i, x);
				s = "Gia_" + sp.getMASP().toString();
				x = Integer.valueOf(request.getParameter(s));
				listGia.set(i, x);
				i++;
			}
			return "redirect:/NhanVien/PhieuNhap.htm";
		}
		s = "";
		i = 0;
		for(SanPham sp: listSP)
		{
			s = "SL_" + sp.getMASP().toString();
			Integer x = Integer.valueOf(request.getParameter(s)); 
			listSL.set(i, x);
			s = "Gia_" + sp.getMASP().toString();
			x = Integer.valueOf(request.getParameter(s));
			listGia.set(i, x);
			i++;
		}
		Id = id;
		return "redirect:/NhanVien/PhieuNhap.htm"; 
	}
	
	@RequestMapping(value = "PhieuNhap", params = "btnthemsp", method = RequestMethod.POST)
	public String ThemSanPhamPN(ModelMap model, HttpServletRequest request, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		String s = "";
		int i = 0;
		System.out.println(Id);
		if(Id == 0)
		{
			for(SanPham sp: listSP)
			{
				s = "SL_" + sp.getMASP().toString();
				Integer x = Integer.valueOf(request.getParameter(s)); 
				listSL.set(i, x);
				s = "Gia_" + sp.getMASP().toString();
				x = Integer.valueOf(request.getParameter(s));
				listGia.set(i, x);
				i++;
			}
			return "redirect:/NhanVien/PhieuNhap.htm";
		}
			
		s = "";
		i = 0;
		for(SanPham sp: listSP)
		{
			s = "SL_" + sp.getMASP().toString();
			Integer x = Integer.valueOf(request.getParameter(s)); 
			listSL.set(i, x);
			s = "Gia_" + sp.getMASP().toString();
			x = Integer.valueOf(request.getParameter(s));
			listGia.set(i, x);
			i++;
		}
		i = 0;
		for(SanPham sp: listSP)
		{
			if(sp.getMASP() == Id)
			{
				Id = 0;
				listSL.set(i, listSL.get(i) + 1);
//				model.addAttribute("listSP", listSP);
				return "redirect:/NhanVien/PhieuNhap.htm";
			}
			i++;
		}
		SanPham sp = getSanPham(Id);
		Id = 0;
		listSL.add(1);
		listGia.add(sp.getGIA());
		listSP.add(sp);
//		model.addAttribute("listSP", listSP);
		dangThem = true;
		return "redirect:/NhanVien/PhieuNhap.htm"; 
	}
	
	@RequestMapping("PhieuNhap/{id}.htm")
	public String XoaSanPhamPN(ModelMap model,  @PathVariable("id") Integer id, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		System.out.println("Vô");
		int i = 0;
		int vtxoa = 0;
		for(SanPham sp: listSP)
		{
			if(sp.getMASP() == id)
				vtxoa = i;
			i++;
		}
		listSP.remove(vtxoa);
		listSL.remove(vtxoa);
		listGia.remove(vtxoa);
		return "redirect:/NhanVien/PhieuNhap.htm"; 
	}
	
	@RequestMapping(value = "PhieuNhap", params = "btntaoPN", method = RequestMethod.POST)
	public String XuatPN(ModelMap model, HttpSession session, HttpServletRequest request, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		//listSP.add(getSanPham(id));
		Id = 0;
		if(listSP.size() == 0)
		{
			return "redirect:/NhanVien/PhieuNhap.htm";
		}
		//System.out.println(Id);
		PhieuNhap pn = new PhieuNhap();
		NhanVien nv = getNV(tk.getMATK());
		pn.setNhanVien(nv);
		String ngay = java.time.LocalDate.now().toString();
		pn.setNGAYNHAP(ngay);
		Integer temp = ThemPN(pn);
		String s = "";
		for(SanPham sp: listSP)
		{
			
			CTPN ctpn = new CTPN();
			ctpn.setPhieuNhap(pn);
			ctpn.setSanPham(sp);
			s = "SL_" + sp.getMASP().toString();
			Integer x = Integer.valueOf(request.getParameter(s));
			ctpn.setSL(x);
			s = "Gia_" + sp.getMASP().toString();
			x = Integer.valueOf(request.getParameter(s));
			ctpn.setDONGIA(x);
//			ctdh.setSL(0);
			temp = this.ThemCTPN(ctpn);
			if (temp != 0) {
				System.out.println("Thêm Thành Công");
			}
		}
		listSP.clear();
		listSL.clear();
		listGia.clear();
		dangThem = false;
		return "redirect:/NhanVien/PhieuNhap.htm"; 
	}
	
	@RequestMapping("HieuChinhPhieuNhap")
	public String SuaPhieuNhap(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		if(dangThem)
		{
			return "redirect:/NhanVien/PhieuNhap.htm";
		}
		NhanVien nv = getNV(tk.getMATK());
		List<PhieuNhap> listPN = getPhieuNhaps(nv.getMANV());
		PagedListHolder pagedListHolder = new PagedListHolder(listPN);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "NhanVien/SuaPhieuNhap";
	}
	
	@RequestMapping(value = "HieuChinhPhieuNhap", params = "btnsearch", method = RequestMethod.POST)
	public String TimSuaPhieuNhap(HttpServletRequest request, ModelMap model, HttpSession ss, @RequestParam("masp") Integer id) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		if(dangThem)
		{
			return "redirect:/NhanVien/PhieuNhap.htm";
		}
		NhanVien nv = getNV(tk.getMATK());
		List<PhieuNhap> listPN = getTimPhieuNhaps(nv.getMANV(), id);
		PagedListHolder pagedListHolder = new PagedListHolder(listPN);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "NhanVien/SuaPhieuNhap";
	}
	
	// ======================================== Xóa ===========================================//
	
	@RequestMapping("XuLyXoa")
	public String XoaPhieuNhaps(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		PhieuNhap temp = (PhieuNhap) session.get(PhieuNhap.class, IdPN);
		session.close();
		while(listCTPN.size() != 0) {
			session = factory.openSession();
			t = session.beginTransaction();
			try {
				session.delete(listCTPN.get(0));
				t.commit();
			} catch (Exception e) {
				t.rollback();
			} finally {
				session.close();
			}
			listCTPN.remove(0);
		}
		session = factory.openSession();
		t = session.beginTransaction();
		try {
			session.delete(temp);
			t.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			t.rollback();
		} finally {
			session.close();
		}
//		System.out.println(id);
		return "redirect:/NhanVien/HieuChinhPhieuNhap.htm";
	}
	
	@RequestMapping(value = "HieuChinhPhieuNhap/{id}.htm", params = "linkDelete", method = RequestMethod.POST)
	public String XoaPhieuNhaps(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		IdPN = id;
		getCTPNs(id);
		return "redirect:/NhanVien/XuLyXoa.htm";
	}
	
	// ================================== Hiệu Chỉnh =====================================//
	@RequestMapping(value ="HieuChinhPhieuNhap/{id}.htm", params = "linkEdit")
	public String HieuChinhHD(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Integer> list = new ArrayList<>();
		IdPN = id;
		list = this.LayMaSP(id);
		dangSua = true;
		Id = 0;
		if(list.size() == listSP.size())
		{
			model.addAttribute("id", Id);
			model.addAttribute("sp", this.getSanPham(Id));
			model.addAttribute("listSL", listSL);
			model.addAttribute("listGia", listGia);
			model.addAttribute("listSP", listSP);
			// model.addAttribute("btnStatus", "btnAdd");
			return "NhanVien/SuaChiTietPhieuNhap";
		}
		for(Integer i:list)
			System.out.println(i);
		while(list.size() != 0)
		{
			SanPham sp = new SanPham();
			Integer i = list.get(0);
			sp = getSanPham(i);
			CTPN ct = getCTPN(id, i);
			Integer j = ct.getSL();
			listSL.add(j);
			Integer k = ct.getDONGIA();
			listGia.add(k);
			listSP.add(sp);
			list.remove(0);
		}
		model.addAttribute("id", Id);
		model.addAttribute("sp", this.getSanPham(Id));
		model.addAttribute("listSL", listSL);
		model.addAttribute("listGia", listGia);
		model.addAttribute("listSP", listSP);
		// model.addAttribute("btnStatus", "btnAdd");
		return "NhanVien/SuaChiTietPhieuNhap";
	}
	
	@RequestMapping(value = "SuaChiTietPhieuNhap", params = "btnsearch", method = RequestMethod.POST)
	public String TimSanPhamSuaHD(ModelMap model, @RequestParam("masp") Integer id, HttpServletRequest request, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		//listSP.add(getSanPham(id));
//		String s = "";
		String s = "";
		int i = 0;
		if(id == null) {
			Id = 0;
			for(SanPham sp: listSP)
			{
				s = "SL_" + sp.getMASP().toString();
				Integer x = Integer.valueOf(request.getParameter(s)); 
				listSL.set(i, x);
				s = "Gia_" + sp.getMASP().toString();
				x = Integer.valueOf(request.getParameter(s));
				listGia.set(i, x);
				i++;
			}
			model.addAttribute("id", Id);
			model.addAttribute("sp", this.getSanPham(Id));
			model.addAttribute("listSP", listSP);
			model.addAttribute("listSL", listSL);
			model.addAttribute("listGia", listGia);
//			Id = 0;
			return "NhanVien/SuaChiTietPhieuNhap";
		}
		s = "";
		i = 0;
		for(SanPham sp: listSP)
		{
			s = "SL_" + sp.getMASP().toString();
			Integer x = Integer.valueOf(request.getParameter(s)); 
			listSL.set(i, x);
			s = "Gia_" + sp.getMASP().toString();
			x = Integer.valueOf(request.getParameter(s));
			listGia.set(i, x);
			i++;
		}
		Id = id;
		model.addAttribute("id", Id);
		model.addAttribute("sp", this.getSanPham(Id));
		model.addAttribute("listSP", listSP);
		model.addAttribute("listSL", listSL);
		model.addAttribute("listGia", listGia);
//		Id = 0;
		return "NhanVien/SuaChiTietPhieuNhap"; 
	}
	
	@RequestMapping(value = "SuaChiTietPhieuNhap", params = "btnthemsp", method = RequestMethod.POST)
	public String ThemSanPhamSuaPN(ModelMap model, HttpServletRequest request, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		if(Id == 0)
		{
			model.addAttribute("id", Id);
			model.addAttribute("sp", this.getSanPham(Id));
			model.addAttribute("listSP", listSP);
			model.addAttribute("listSL", listSL);
			model.addAttribute("listGia", listGia);
			return "NhanVien/SuaChiTietPhieuNhap";
		}
			
		String s = "";
		int i = 0;
		for(SanPham sp: listSP)
		{
			s = "SL_" + sp.getMASP().toString();
			Integer x = Integer.valueOf(request.getParameter(s)); 
			listSL.set(i, x);
			s = "Gia_" + sp.getMASP().toString();
			x = Integer.valueOf(request.getParameter(s));
			listGia.set(i, x);
			i++;
		}
		s = "";
		i = 0;
		for(SanPham sp: listSP)
		{
			if(sp.getMASP() == Id)
			{
				listSL.set(i, listSL.get(i) + 1);
				model.addAttribute("id", Id);
				model.addAttribute("sp", this.getSanPham(Id));
				model.addAttribute("listSP", listSP);
				model.addAttribute("listSL", listSL);
				model.addAttribute("listGia", listGia);
				return "NhanVien/SuaChiTietPhieuNhap";
			}
			i++;
		}
		SanPham sp = getSanPham(Id);
		PhieuNhap pn = getPhieuNhap(IdPN);
		CTPN ct = new CTPN();
		ct.setSanPham(sp);
		ct.setPhieuNhap(pn);
		ct.setSL(1);
		ct.setDONGIA(sp.getGIA());
		Integer tmp = this.ThemCTPN(ct);
		listSP.add(sp);
		listSL.add(1);
		listGia.add(sp.getGIA());
		model.addAttribute("id", Id);
		model.addAttribute("sp", this.getSanPham(Id));
		model.addAttribute("listSP", listSP);
		model.addAttribute("listSL", listSL);
		model.addAttribute("listGia", listGia);
		Id = 0;
		return "NhanVien/SuaChiTietPhieuNhap"; 
	}
	
	@RequestMapping("SuaChiTietPhieuNhap/{id}.htm")
	public String XoaSuaSanPhamPN(ModelMap model,  @PathVariable("id") Integer id, HttpSession ss, HttpServletRequest request)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		int i = 0;
		int vtxoa = 0;
		Id = 0;
		for(SanPham sp: listSP)
		{
			if(sp.getMASP() == id)
				vtxoa = i;
			i++;
		}
		if(listSP.size() == 1)
		{
			model.addAttribute("id", Id);
			model.addAttribute("sp", this.getSanPham(Id));
			model.addAttribute("listSP", listSP);
			model.addAttribute("listSL", listSL);
			model.addAttribute("listGia", listGia);
			return "NhanVien/SuaChiTietPhieuNhap";
		}
		listSP.remove(vtxoa);
		listSL.remove(vtxoa);
		listGia.remove(vtxoa);
		CTPN ct = this.getCTPN(IdPN, id);
		Integer tmp = this.XoaCTPN(ct);
		model.addAttribute("id", Id);
		model.addAttribute("sp", this.getSanPham(Id));
		model.addAttribute("listSP", listSP);
		model.addAttribute("listSL", listSL);
		model.addAttribute("listGia", listGia);
		return "NhanVien/SuaChiTietPhieuNhap"; 
	}
	
	@RequestMapping(value = "SuaChiTietPhieuNhap", params = "btntaoPN", method = RequestMethod.POST)
	public String LuuHD(ModelMap model, HttpSession session, HttpServletRequest request, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		//listSP.add(getSanPham(id));
		Id = 0;
		String s = "";
		for(SanPham sp: listSP)
		{
			s = "SL_" +sp.getMASP().toString();
			CTPN ctpn = getCTPN(IdPN, sp.getMASP());
			Integer x = Integer.valueOf(request.getParameter(s));
			ctpn.setSL(x);
			s = "Gia_" + sp.getMASP().toString();
			x =  Integer.valueOf(request.getParameter(s));
			ctpn.setDONGIA(x);
			Integer temp = this.HieuChinhCTPN(ctpn);
			if (temp != 0) {
				System.out.println("Sửa Thành Công");
			}
		}
		listSP.clear();
		listSL.clear();
		listGia.clear();
//		dangThem = false;
		return "redirect:/NhanVien/PhieuNhap.htm"; 
	}
}
