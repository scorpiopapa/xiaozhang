package com.qiYang.model;

import java.sql.Timestamp;

public class TbShouyeguanggao implements java.io.Serializable {
	private Integer id;
	private String url;
	private String hyperlink;
	private Integer type;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;

	public TbShouyeguanggao() {
	}

	public TbShouyeguanggao(Integer id, String url, String hyperlink,
			Integer type, Integer isValid, Timestamp time, Timestamp alterTime) {
		super();
		this.id = id;
		this.url = url;
		this.hyperlink = hyperlink;
		this.type = type;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Timestamp getAlterTime() {
		return alterTime;
	}

	public void setAlterTime(Timestamp alterTime) {
		this.alterTime = alterTime;
	}

}
