package com.qiYang.model;

/**
 * Ofproperty entity. @author MyEclipse Persistence Tools
 */

public class Ofproperty implements java.io.Serializable {

	// Fields

	private String name;
	private String propValue;

	// Constructors

	/** default constructor */
	public Ofproperty() {
	}

	/** full constructor */
	public Ofproperty(String propValue) {
		this.propValue = propValue;
	}

	// Property accessors

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPropValue() {
		return this.propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

}