package com.qiYang.dao.alipay;

import com.qiYang.model.TbBills;

/**
 * 
 * @author coolgo
 * 
 *         winerdaxian@163.com
 */
public interface OrderDao {

	// 创建订单
	boolean createOrderInfo(TbBills bills);

	// 删除订单
	boolean delOrderInfo(TbBills bills);

	// 根据订单号获取订单信息
	TbBills getOrderInfoByOrderNo(String orderNo);

	// 修改订单信息
	void updateOrderInfo(TbBills bills);

}
