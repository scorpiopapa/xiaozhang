package com.qiYang.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.qiYang.model.TbAdmin;
import com.qiYang.model.TbCity;
import com.qiYang.model.TbSchool;
import com.qiYang.util.HibernateSessionFactory;
//添加分校
public class AddBranchSchool extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		String type = request.getParameter("type");
		int Type = Integer.parseInt(type);
		String url = "addSchool.jsp";
		
		PrintWriter out = response.getWriter();
		Session session = HibernateSessionFactory.getSession();
		HttpSession sessions = request.getSession();
		if(sessions.getAttribute("loginAdmin")==null){
			request.setAttribute("loginout", 1);
			request.getRequestDispatcher(url).forward(request, response);
		}else{
		try {
			TbAdmin admin1 = (TbAdmin)sessions.getAttribute("loginAdmin");
			Criteria criteria3 = session.createCriteria(TbAdmin.class);
			criteria3.add(Restrictions.eq("adminId", admin1.getAdminId()));
			criteria3.setFetchMode("tbSchool", FetchMode.JOIN);
			List<TbAdmin> adminlist1 = criteria3.list();
			TbAdmin admina = adminlist1.get(0);
			request.setAttribute("adminschool", admina.getTbSchool());
			
			Criteria criteria1 = session.createCriteria(TbCity.class);
			List<TbCity> citylist = criteria1.list();
			
			if(1==Type){
				Criteria criteria2 = session.createCriteria(TbSchool.class);

				List<TbSchool> schoollist = criteria2.list();
				
				url = "addbranchschool.jsp";
				request.setAttribute("schoollist", schoollist);
			}
			request.setAttribute("citylist", citylist);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
