package ptithcm.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.SanPham;

@Controller
@Transactional
public class IndexController {
	@Autowired
	SessionFactory factory;
	public List<SanPham> getSanPhams(){
		Session session = factory.getCurrentSession();
		String hql = "from SanPham";
		Query query = session.createQuery(hql);
		List<SanPham> list = query.list();
		return list;
	}
	
	@RequestMapping("index")
	public String hello(ModelMap model)
	{
		List<SanPham> listSP = this.getSanPhams();
		model.addAttribute("listSP", listSP);
		return "index";
	}
}
