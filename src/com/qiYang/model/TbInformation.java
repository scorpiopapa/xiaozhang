package com.qiYang.model;

import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;

/**
 * TbInformation entity. @author MyEclipse Persistence Tools
 */

public class TbInformation implements java.io.Serializable {

	// Fields

	private Integer infoid;
	private String title;
	private String content;
	private String image;
	private Integer infotype;
	private Timestamp addtime;

	// Constructors

	/** default constructor */
	public TbInformation() {
	}

	/** full constructor */
	public TbInformation(String title, String content, String image,
			Integer infotype, Timestamp addtime) {
		this.title = title;
		this.content = content;
		this.image = image;
		this.infotype = infotype;
		this.addtime = addtime;
	}

	// Property accessors

	public Integer getInfoid() {
		return this.infoid;
	}

	public void setInfoid(Integer infoid) {
		this.infoid = infoid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getInfotype() {
		return this.infotype;
	}

	public void setInfotype(Integer infotype) {
		this.infotype = infotype;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}