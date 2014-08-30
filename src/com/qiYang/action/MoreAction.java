package com.qiYang.action;

import java.util.List;

import com.qiYang.dao.UpdateMoreDao;
import com.qiYang.model.TbMore;

public class MoreAction {

	private UpdateMoreDao moredao;
	private List listmore;
	private TbMore tbmore;
	private Integer moreId;
	
	public String selectMore(){
		listmore = moredao.selectMore();
		return "selectmore";
	}
	
	public String toupdateMore(){
		tbmore = moredao.toupdateMore(moreId);
		return "toupdateMore";
	}
	public String updateMore(){
		tbmore = moredao.updateMore(tbmore);
		return "updateMore";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public List getListmore() {
		return listmore;
	}

	public void setListmore(List listmore) {
		this.listmore = listmore;
	}

	public UpdateMoreDao getMoredao() {
		return moredao;
	}
	public void setMoredao(UpdateMoreDao moredao) {
		this.moredao = moredao;
	}
	public TbMore getTbmore() {
		return tbmore;
	}
	public void setTbmore(TbMore tbmore) {
		this.tbmore = tbmore;
	}
	public Integer getMoreId() {
		return moreId;
	}
	public void setMoreId(Integer moreId) {
		this.moreId = moreId;
	}
}
