package ptithcm.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.SanPham;

@Controller
@RequestMapping("Home/")
@Transactional
public class HomeController {
	@Autowired
	SessionFactory factory;
	public List<SanPham> getSanPhams(){
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
	
	@RequestMapping("index")
	public String hello(ModelMap model)
	{
		List<SanPham> listSP = this.getSanPhams();
		model.addAttribute("listSP", listSP);
		return "index";
	}
	
	@RequestMapping("XemChiTiet/{id}.htm")
	public String XemChiTietSP(ModelMap model,  @PathVariable("id") Integer id) {
		model.addAttribute("sanPham", this.getSanPham(id));
		return "KhachHang/XemChiTiet";
	}
	
}
