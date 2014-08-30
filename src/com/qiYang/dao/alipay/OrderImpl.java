package com.qiYang.dao.alipay;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.qiYang.dao.DataBaseDao;
import com.qiYang.dao.DataBaseDaoImpl;
import com.qiYang.model.TbBills;
import com.qiYang.util.TWObject;

/**
 * 订单实现类
 * 
 * @author coolgo
 * 
 *         winerdaxian@163.com
 */
public class OrderImpl implements OrderDao {

	private final DataBaseDao dataBaseDao = new DataBaseDaoImpl();

	/*
	 * 
	 */
	public boolean createOrderInfo(TbBills bills) {
		String flag = dataBaseDao.addObject(bills);
		if (StringUtils.equalsIgnoreCase(flag, "success"))
			return true;
		else {
			return false;
		}
	}

	/*
	 */
	public boolean delOrderInfo(TbBills bills) {
		String result = dataBaseDao
				.deleteObjects(TbBills.class, bills.getBillsId());
		return "success".equals(result) ? true : false;
	}

	/*
	 * 
	 */
	public TbBills getOrderInfoByOrderNo(String orderNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("outTradeNo", orderNo);
		Object bills = dataBaseDao.getObject(TbBills.class,
				params);
		return new TWObject().getT(TbBills.class,bills);
	}

	/*
	 * 
	 */
	public void updateOrderInfo(TbBills bills) {
		String result = dataBaseDao.updateObject(bills);

	}
}
