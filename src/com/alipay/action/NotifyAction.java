package com.alipay.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.alipay.util.AlipayNotify;
import com.qiYang.dao.BaseDao;
import com.qiYang.dao.TransactionDao;
import com.qiYang.model.TbCoupon;
import com.qiYang.model.TbCurriculumCopy;
import com.qiYang.model.TbOrder;
import com.qiYang.model.TbTeam;
import com.qiYang.model.TbTeamCopy;
import com.qiYang.util.TWDataUtil;

/* *
功能：支付宝服务器异步通知页面
版本：3.3
日期：2012-08-17
说明：
以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

//***********页面功能说明***********
创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
该页面调试工具请使用写文本函数logResult，该函数在com.alipay.util文件夹的AlipayNotify.java类文件中
如果没有收到该页面返回的 success 信息，支付宝会在24小时内按一定的时间策略重发通知
//********************************
* */
public class NotifyAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1641516475169988524L;
	private static String SUCCESS="success";
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String encoding = request.getCharacterEncoding();
		response.setContentType("text/json;charset=" + encoding);
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		PrintWriter pw = response.getWriter();
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if("TRADE_FINISHED".equals(trade_status)||"TRADE_SUCCESS".equals(trade_status)){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//该种交易状态只在两种情况下出现
				//1、开通了普通即时到账，买家付款成功后。
				//2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
				
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				BaseDao baseDao=new BaseDao();
				DetachedCriteria ordDc=DetachedCriteria.forClass(TbOrder.class);
				ordDc.add(Restrictions.eq("outTradeNo", out_trade_no));
				ordDc.add(Restrictions.eq("status", "unpay"));
				ArrayList<TbOrder> orders = baseDao.dCList(TbOrder.class, ordDc);
				if(orders!=null&&!orders.isEmpty()){
					TbOrder tbOrder = orders.get(0);
					Integer num = 0 ;
					String couponId="";
					Boolean couponIsNull=false;
					TbCoupon tbCoupon=null;
					TbTeam team =null;
					while(!couponIsNull){
						couponId = RandomStringUtils.randomAlphanumeric(12);
						DetachedCriteria coudc=DetachedCriteria.forClass(TbCoupon.class);
						coudc.add(Restrictions.eq("id", couponId));
						num = baseDao.dCRowCount(coudc);
						if(num==0){
							couponIsNull=true;
							Timestamp time=new Timestamp(System.currentTimeMillis());
							Timestamp expireTime=null;
							if(tbOrder.getType()==0){
							TbTeamCopy tbTeam = baseDao.get(TbTeamCopy.class, tbOrder.getTeamId());
							DetachedCriteria teamdc=DetachedCriteria.forClass(TbTeam.class);
							teamdc.add(Restrictions.eq("time", tbTeam.getTime()));
							teamdc.add(Restrictions.eq("title", tbTeam.getTitle()));
							ArrayList<TbTeam> teams = baseDao.dCList(TbTeam.class, teamdc);
							if(teams!=null&&!teams.isEmpty()){
								team=teams.get(0);
								team.setQuantity(tbTeam.getNowNumber()==null?1:tbTeam.getNowNumber()+1);
								team.setInventory(tbTeam.getInventory()==null?0:tbTeam.getInventory()-1);
								team.setAlterTime(time);
							}
							expireTime=tbTeam.getExpireTime();
							}else{
							TbCurriculumCopy tbCurriculum = baseDao.get(TbCurriculumCopy.class, tbOrder.getCurriculumId());
							expireTime=TWDataUtil.getEndTimestamp(tbCurriculum.getEndTerm());
							}
							tbCoupon=new TbCoupon(tbOrder.getUserinfoId(), tbOrder.getSchoolId(),null, tbOrder.getTeamId(),tbOrder.getCurriculumId(), tbOrder.getId(), tbOrder.getType(), RandomStringUtils.randomAlphanumeric(6), 0, expireTime, null, 1, time, time);
							tbCoupon.setId(couponId);
							tbOrder.setAlterTime(time);
							tbOrder.setStatus("pay");
							tbOrder.setTradeNo(trade_no);
							tbOrder.setPayTime(time);
						}
					}
					 new TransactionDao().addTbCoupon(tbCoupon,tbOrder,team);
				}
				}
			pw.write(SUCCESS);
			pw.flush();
			pw.close();
			return;	
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			pw.write("fail");
			pw.flush();
			pw.close();
			return;
		}
	}

}
