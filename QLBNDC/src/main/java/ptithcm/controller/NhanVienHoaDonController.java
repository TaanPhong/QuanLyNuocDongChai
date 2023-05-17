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

import ptithcm.entity.CTHD;
import ptithcm.entity.HoaDon;
import ptithcm.entity.NhanVien;
import ptithcm.entity.SanPham;
import ptithcm.entity.TaiKhoan;

@Controller
@RequestMapping("NhanVien/")
@Transactional
public class NhanVienHoaDonController {
	@Autowired
	SessionFactory factory;
	List<SanPham> listSP = new ArrayList<>();
	List<CTHD> ListCT = new ArrayList<>();
	List<Integer> listSL = new ArrayList<>();
	Integer Id = 0;
	Integer IdHD = 0;
	boolean dangThem = false;
	boolean dangSua = false;
	public Integer ThemHoaDon(HoaDon hd) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(hd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	public Integer XoaHoaDon(HoaDon hd) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.delete(hd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	public Integer ThemCTHD(CTHD cthd) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(cthd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	public Integer XoaCTHD(CTHD cthd) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.delete(cthd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	public Integer HieuChinhCTHD(CTHD cthd) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(cthd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	public void getCTs(Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "from CTHD CT where CT.hoaDon.MAHD = " + id;
		Query query = session.createQuery(hql);
		ListCT = query.list();
//		session.close();
	}
	
	public Integer TongTien() {
		Integer i = 0;
		int j = 0;
		for(SanPham sp: listSP)
		{
			i = i + sp.getGIA()*listSL.get(j);
			j++;
		}
		return i;
	}
	
	@RequestMapping("HoaDon")
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
			listSL.clear();
			dangSua = false;
		}
		model.addAttribute("id", Id);
		model.addAttribute("sp", this.getSanPham(Id));
		model.addAttribute("listSP", listSP);
		model.addAttribute("listSL", listSL);
		model.addAttribute("tongTien", this.TongTien());
		return "NhanVien/HoaDon"; 
	}
	
	public SanPham getSanPham(Integer id) {
		//System.out.println(id);
		Session session = factory.getCurrentSession();
		return (SanPham) session.get(SanPham.class, id); 
	}
	
	public HoaDon getHoaDon(Integer id) {
		//System.out.println(id);
		Session session = factory.getCurrentSession();
		return (HoaDon) session.get(HoaDon.class, id); 
	}
	
	public NhanVien getNV(Integer id) {
		//System.out.println(id);
		Session session = factory.getCurrentSession();
		String hql = "from NhanVien where MATK = " + id;
		Query query = session.createQuery(hql);
		NhanVien nv = (NhanVien)query.list().get(0);
		return nv;
	}
	
	public CTHD getCTHD(Integer id, Integer idsp) {
		//System.out.println(id);
		Session session = factory.getCurrentSession();
		String hql = "from CTHD CT where CT.hoaDon.MAHD = " + id
					+ " and CT.sanPham.MASP = " + idsp;
		Query query = session.createQuery(hql);
		CTHD list = (CTHD)query.list().get(0);
		return list;
	}
	
	public int Index(SanPham s, List<SanPham> lsp)
	{
		int i = 0; 
		for(SanPham sp: lsp)
		{
			if(sp.getMASP() == s.getMASP())
				return i;
			i++;
		}
		return -1;
	}
	
//===================================== Thêm Hóa đơn ===================================//
	@RequestMapping(value = "HoaDon", params = "btnthemsp", method = RequestMethod.POST)
	public String ThemSanPhamHD(ModelMap model, HttpServletRequest request, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		if(Id == 0)
			return "redirect:/NhanVien/HoaDon.htm";
		String s = "";
		int i = 0;
		for(SanPham sp: listSP)
		{
			if(sp.getMASP() == Id)
			{
				if(listSL.get(i) >= sp.getSLTON())
				{
					Id = 0;
					model.addAttribute("id", Id);
					model.addAttribute("sp", this.getSanPham(Id));
					model.addAttribute("listSP", listSP);
					model.addAttribute("listSL", listSL);
					model.addAttribute("tongTien", this.TongTien());
					return "NhanVien/HoaDon"; 
				}
				Id = 0;
				listSL.set(i, listSL.get(i) + 1);
				model.addAttribute("id", Id);
				model.addAttribute("sp", this.getSanPham(Id));
				model.addAttribute("listSP", listSP);
				model.addAttribute("listSL", listSL);
				model.addAttribute("tongTien", this.TongTien());
				return "NhanVien/HoaDon"; 
			}
			i++;
		}
		SanPham sp = getSanPham(Id);
//		SanPham sp1 = getSanPham(Id);
		Id = 0;
		if(sp.getSLTON() < 1)
		{
			model.addAttribute("id", Id);
			model.addAttribute("sp", this.getSanPham(Id));
			model.addAttribute("listSP", listSP);
			model.addAttribute("listSL", listSL);
			model.addAttribute("tongTien", this.TongTien());
			return "NhanVien/HoaDon"; 
		}
//			return "NhanVien/HoaDon";
//		ListSPG.add(sp1);
		listSL.add(1);
		listSP.add(sp);
//		model.addAttribute("listSP", listSP);
		dangThem = true;
		return "redirect:/NhanVien/HoaDon.htm"; 
	}
	
	@RequestMapping("HoaDon/{id}.htm")
	public String XoaSanPhamHD(ModelMap model,  @PathVariable("id") Integer id, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
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
		Id = 0;
		return "redirect:/NhanVien/HoaDon.htm"; 
	}
	
	@RequestMapping(value = "HoaDon", params = "btnsearch", method = RequestMethod.POST)
	public String TimSanPhamHD(ModelMap model, @RequestParam("masp") Integer id, HttpServletRequest request, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		//listSP.add(getSanPham(id));
		String s = "";
		
		if(id == null) {
			return "redirect:/NhanVien/HoaDon.htm";
		}
		Id = id;
		if(Id == 0)
			return "redirect:/NhanVien/HoaDon.htm";
		int i = 0;
		
//		Id = 0;
		return "redirect:/NhanVien/HoaDon.htm"; 
	}
	
	@RequestMapping(value = "HoaDon", params = "btntaoHD", method = RequestMethod.POST)
	public String XuatHD(ModelMap model, HttpSession session, HttpServletRequest request, HttpSession ss)
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
			model.addAttribute("id", Id);
			model.addAttribute("sp", this.getSanPham(Id));
			model.addAttribute("listSP", listSP);
			model.addAttribute("listSL", listSL);
			model.addAttribute("tongTien", this.TongTien());
			return "NhanVien/HoaDon";
		}
		//System.out.println(Id);
		HoaDon hd = new HoaDon();
		NhanVien nv = getNV(tk.getMATK());
		hd.setNhanVien(nv);
		String ngay = java.time.LocalDate.now().toString();
		hd.setNGAYLAPHD(ngay);
		Integer temp = ThemHoaDon(hd);
		if (temp != 0) {
			model.addAttribute("message", "Thêm thành công");
		} else {
			model.addAttribute("message", "Thêm thất bại!");
		}
		int i = 0;
		for(SanPham sp: listSP)
		{
			CTHD ctdh = new CTHD();
			ctdh.setHoaDon(hd);
			ctdh.setSanPham(sp);
			ctdh.setDONGIA(sp.getGIA());
			ctdh.setSL(listSL.get(i));
			temp = this.ThemCTHD(ctdh);
			if (temp != 0) {
				System.out.println("Thêm Thành Công");
			}
			i++;
		}
		listSP.clear();
		listSL.clear();
		dangThem = false;
		return "redirect:/NhanVien/HoaDon.htm"; 
	}
	
	
	
	//==================================== Sửa Hóa đơn ========================================//
	
	public List<HoaDon> getHoaDons(Integer id){
		String ngay = java.time.LocalDate.now().toString();
		Session session = factory.getCurrentSession();
		String hql = "from HoaDon where NGAYLAPHD = '" + ngay + "' and MANV = " + id;
		Query query = session.createQuery(hql);
		List<HoaDon> list = query.list();
		return list;
	}
	
	public List<HoaDon> getTimHoaDons(Integer id, Integer idhd){
		String ngay = java.time.LocalDate.now().toString();
		Session session = factory.getCurrentSession();
		String hql = "from HoaDon where NGAYLAPHD = '" + ngay + "' and MANV = " + id + " and MAHD = " + idhd;
		Query query = session.createQuery(hql);
		List<HoaDon> list = query.list();
		return list;
	}
	
	public List<Integer> LayMaSP(Integer id){
		Session session = factory.getCurrentSession();
		String hql = "select CT.sanPham.MASP from CTHD CT where CT.hoaDon.MAHD = " + id;
		Query query = session.createQuery(hql);
		List<Integer> list = query.list();
		return list;
	}
	
	
	@RequestMapping("HieuChinhHoaDon")
	public String SuaHoaDon(HttpServletRequest request, ModelMap model, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		if(dangThem)
		{
			return "redirect:/NhanVien/HoaDon.htm";
		}
		NhanVien nv = getNV(tk.getMATK());
		List<HoaDon> listHD = getHoaDons(nv.getMANV());
		PagedListHolder pagedListHolder = new PagedListHolder(listHD);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "NhanVien/SuaHoaDon";
	}
	
	@RequestMapping(value = "HieuChinhHoaDon", params = "btnsearch", method = RequestMethod.POST)
	public String TimSuaHoaDon(HttpServletRequest request, ModelMap model, HttpSession ss, @RequestParam("masp") Integer id) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		if(dangThem)
		{
			return "redirect:/NhanVien/HoaDon.htm";
		}
		NhanVien nv = getNV(tk.getMATK());
		System.out.println(id);
//		Integer idHD = Integer.valueOf(request.getParameter("masp"));
		List<HoaDon> listHD = getTimHoaDons(nv.getMANV(), id);
		PagedListHolder pagedListHolder = new PagedListHolder(listHD);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		// SỐ page
		pagedListHolder.setMaxLinkedPages(5);
		// Số dòng
		pagedListHolder.setPageSize(10);
		// model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "NhanVien/SuaHoaDon";
	}
	
	@RequestMapping(value ="HieuChinhHoaDon/{id}.htm", params = "linkEdit")
	public String HieuChinhHD(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		List<Integer> list = new ArrayList<>();
		IdHD = id;
		Id = 0;
		dangSua = true;
		list = this.LayMaSP(id);
		for(Integer i:list)
			System.out.println(i);
		Integer i = 0;
		if(list.size() == listSP.size())
		{
			model.addAttribute("id", Id);
			model.addAttribute("sp", this.getSanPham(Id));
			model.addAttribute("listSP", listSP);
			model.addAttribute("listSL", listSL);
			model.addAttribute("tongTien", this.TongTien());
			return "NhanVien/SuaChiTietHoaDon";
		}
		while(list.size() != 0)
		{
			SanPham sp1 = new SanPham();
			i = list.get(0);
			sp1 = getSanPham(i);
			CTHD ct = getCTHD(id, i);
//			sp1.setSLTON(ct.getSL());
			i = ct.getSL();
			listSL.add(i);
			listSP.add(sp1);
			list.remove(0);
		}
		model.addAttribute("id", Id);
		model.addAttribute("sp", this.getSanPham(Id));
		model.addAttribute("listSP", listSP);
		model.addAttribute("listSL", listSL);
		model.addAttribute("tongTien", this.TongTien());
		return "NhanVien/SuaChiTietHoaDon";
	}
	
	@RequestMapping(value = "SuaChiTietHoaDon", params = "btnsearch", method = RequestMethod.POST)
	public String TimSanPhamSuaHD(ModelMap model, @RequestParam("masp") Integer id, HttpServletRequest request, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		//listSP.add(getSanPham(id));
		int i = 0;
		if(id == null) {
			Id = 0;
//			System.out.println("Vô seach");
			model.addAttribute("id", Id);
			model.addAttribute("sp", this.getSanPham(Id));
			model.addAttribute("listSP", listSP);
			model.addAttribute("listSL", listSL);
			model.addAttribute("tongTien", this.TongTien());
			return "NhanVien/SuaChiTietHoaDon";
		}
		Id = id;
		model.addAttribute("id", Id);
		model.addAttribute("sp", this.getSanPham(Id));
		model.addAttribute("listSP", listSP);
		model.addAttribute("listSL", listSL);
		model.addAttribute("tongTien", this.TongTien());
		return "NhanVien/SuaChiTietHoaDon"; 
	}
	
	@RequestMapping(value = "SuaChiTietHoaDon", params = "btnthemsp", method = RequestMethod.POST)
	public String ThemSanPhamSuaHD(ModelMap model, HttpServletRequest request, HttpSession ss)
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
			model.addAttribute("tongTien", this.TongTien());
			return "NhanVien/SuaChiTietHoaDon";
		}
			
		String s = "";
		int i = 0;
		for(SanPham sp: listSP)
		{
			if(sp.getMASP() == Id)
			{
				if(listSL.get(i) >= sp.getSLTON())
				{
					Id = 0;
					model.addAttribute("id", Id);
					model.addAttribute("sp", this.getSanPham(Id));
					model.addAttribute("listSP", listSP);
					model.addAttribute("listSL", listSL);
					model.addAttribute("tongTien", this.TongTien());
					return "NhanVien/SuaChiTietHoaDon"; 
				}
				Id = 0;
				listSL.set(i, listSL.get(i) + 1);
				model.addAttribute("id", Id);
				model.addAttribute("sp", this.getSanPham(Id));
				model.addAttribute("listSP", listSP);
				model.addAttribute("listSL", listSL);
				model.addAttribute("tongTien", this.TongTien());
				return "NhanVien/SuaChiTietHoaDon"; 
			}
			i++;
		}
		SanPham sp = getSanPham(Id);
		if(sp.getSLTON() == 0)
			return "redirect:/NhanVien/SuaChiTietHoaDon.htm";
		Integer j = 1;
		HoaDon hd = getHoaDon(IdHD);
		CTHD ct = new CTHD();
		ct.setSanPham(sp);
		ct.setHoaDon(hd);
		ct.setSL(j);
		ct.setDONGIA(sp.getGIA());
		Integer tmp = this.ThemCTHD(ct);
		listSP.add(sp);
		listSL.add(j);
		Id = 0;
		model.addAttribute("id", Id);
		model.addAttribute("sp", this.getSanPham(Id));
		model.addAttribute("listSP", listSP);
		model.addAttribute("listSL", listSL);
		model.addAttribute("tongTien", this.TongTien());
		return "NhanVien/SuaChiTietHoaDon"; 
	}
	
	@RequestMapping("SuaChiTietHoaDon/{id}.htm")
	public String XoaSuaSanPhamHD(ModelMap model,  @PathVariable("id") Integer id, HttpSession ss)
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
			model.addAttribute("tongTien", this.TongTien());
			return "NhanVien/SuaChiTietHoaDon";
		}
			
		listSP.remove(vtxoa);
		listSL.remove(vtxoa);
		CTHD ct = this.getCTHD(IdHD, id);
		Integer tmp = this.XoaCTHD(ct);
		model.addAttribute("id", Id);
		model.addAttribute("sp", this.getSanPham(Id));
		model.addAttribute("listSP", listSP);
		model.addAttribute("listSL", listSL);
		model.addAttribute("tongTien", this.TongTien());
		dangSua = false;
		return "NhanVien/SuaChiTietHoaDon"; 
	}
	
	@RequestMapping(value = "SuaChiTietHoaDon", params = "btntaoHD", method = RequestMethod.POST)
	public String LuuHD(ModelMap model, HttpSession session, HttpServletRequest request, HttpSession ss)
	{
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
		//listSP.add(getSanPham(id));
		Id = 0;
		int i = 0;
		for(SanPham sp: listSP)
		{
			CTHD ctdh = getCTHD(IdHD, sp.getMASP());
			ctdh.setSL(listSL.get(i));
			Integer temp = this.HieuChinhCTHD(ctdh);
			i++;
		}
		listSP.clear();
		listSL.clear();
		return "redirect:/NhanVien/HoaDon.htm"; 
	}
	
	
	// ========================================= Xóa =======================================//
	
	@RequestMapping(value = "HieuChinhHoaDon/{id}.htm", params = "linkDelete", method = RequestMethod.POST)
	public String XoaSanPhams(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, HttpSession ss) {
		TaiKhoan tk = (TaiKhoan) ss.getAttribute("tai_Khoan");
		if(tk == null || tk.getPhanQuyen().getMAPQ() != 2)
			return "redirect:/Login/Login.htm";
		if(tk.getDOI() == 1)
			return "redirect:/Login/DoiMK.htm";
//		System.out.println("Vô rồi xoa");
		Session session = factory.openSession();
		Transaction t = session.beginTransaction(); 
		HoaDon hd = (HoaDon) session.get(HoaDon.class, id);
		session.close();
		this.getCTs(id);
		while(ListCT.size() != 0) {
			session = factory.openSession();
			t = session.beginTransaction();
			try {
				session.delete(ListCT.get(0));
				t.commit();
			} catch (Exception e) {
				t.rollback();
			} finally {
				session.close();
			}
			ListCT.remove(0);
//			System.out.println(tmp);
		}
		session = factory.openSession();
		t = session.beginTransaction();
		try {
			session.delete(hd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
//		System.out.println(id);
		return "redirect:/NhanVien/HieuChinhHoaDon.htm";
	}
}
