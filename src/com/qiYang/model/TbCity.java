package com.qiYang.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TbCity entity. @author MyEclipse Persistence Tools
 */

public class TbCity implements java.io.Serializable {

	// Fields

	private Integer cityId;
	private String cityName;
	private String spell;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbTowns = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbCity() {
	}

	/** full constructor */
	public TbCity(String cityName, String spell, Integer isValid,
			Timestamp time, Timestamp alterTime, Set tbTowns) {
		this.cityName = cityName;
		this.spell = spell;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.tbTowns = tbTowns;
	}

	// Property accessors

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getSpell() {
		return this.spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Timestamp getAlterTime() {
		return this.alterTime;
	}

	public void setAlterTime(Timestamp alterTime) {
		this.alterTime = alterTime;
	}

	public Set getTbTowns() {
		return this.tbTowns;
	}

	public void setTbTowns(Set tbTowns) {
		this.tbTowns = tbTowns;
	}

}