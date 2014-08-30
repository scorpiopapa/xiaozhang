package com.qiYang.model.web;



public class TbNoticeWeb {
	private Integer id;
	private Integer parentId;
	private String parentName;
	private Integer issuerId;
	private String issuerName;
	private String noticeContent;
	private String time;
	private String alterTime;
	public TbNoticeWeb() {
		super();
	}
	public TbNoticeWeb(Integer id, Integer parentId, String parentName,
			Integer issuerId, String issuerName, String noticeContent,
			String time, String alterTime) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.parentName = parentName;
		this.issuerId = issuerId;
		this.issuerName = issuerName;
		this.noticeContent = noticeContent;
		this.time = time;
		this.alterTime = alterTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(Integer issuerId) {
		this.issuerId = issuerId;
	}
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAlterTime() {
		return alterTime;
	}
	public void setAlterTime(String alterTime) {
		this.alterTime = alterTime;
	}
}
