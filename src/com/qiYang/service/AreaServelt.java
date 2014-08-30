package com.qiYang.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.qiYang.model.TbTown;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class AreaServelt extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String cityid = request.getParameter("cityId");
		if(null==cityid||"".equals(cityid))
			cityid = "0";
		
		JSONObject jsonObj = new JSONObject();
		JsonConfig JC = new JsonConfig();
		/*JC.setJsonPropertyFilter(new PropertyFilter() {
			
			public boolean apply(Object source, String name, Object value) {
				if(name.equals("tbCity"))
					return true;
				return false;
			}
		});*/
//		DataBaseDao dbd = new DataBaseDaoImpl();
//		TbCity city=new TbCity();
//		city.setCityId(Integer.parseInt(cityid));
//		
//		Map map = new HashMap();
//		map.put("tbCity", city);
//		
//		List<TbTown> list = dbd.getObjects(TbTown.class, map);
//		List<TbTownWeb> townlist = new ArrayList<TbTownWeb>();
//		for (TbTown t1 : list) {
//			townlist.add(t1.getTown());
//		}
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		try {
			Criteria criteria1 = session.createCriteria(TbTown.class);
			criteria1.createAlias("tbCity", "city");
			criteria1.add(Restrictions.eq("city.cityId", Integer.parseInt(cityid)));
			
			List<TbTown> townlist = criteria1.list();
			//过滤外表
		    JC.setExcludes(new String[]{"tbCity","tbSchools","tbCourses","tbBranchschools"}) ;
		    JSONArray jsa = JSONArray.fromObject(townlist,JC);
		    
			jsonObj.element("town", jsa);
		} catch (Exception e) {
			//session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
		  out.write(jsonObj.toString());	
		  out.flush();
		  out.close();
		  session.close();
		}
	}

}
