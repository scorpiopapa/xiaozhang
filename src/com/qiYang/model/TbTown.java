package com.qiYang.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TbTown entity. @author MyEclipse Persistence Tools
 */

public class TbTown implements java.io.Serializable {

	// Fields

	private Integer townId;
	private TbCity tbCity;
	private String townName;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbCurriculums = new HashSet(0);
	private Set tbSchools = new HashSet(0);
	private Set tbBranchschools = new HashSet(0);
	private Set tbRegschools = new HashSet(0);

	public TbTown(Integer townId, TbCity tbCity, String townName,
			Integer isValid, Timestamp time, Timestamp alterTime,
			Set tbCurriculums, Set tbSchools, Set tbBranchschools,
			Set tbRegschools) {
		super();
		this.townId = townId;
		this.tbCity = tbCity;
		this.townName = townName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.tbCurriculums = tbCurriculums;
		this.tbSchools = tbSchools;
		this.tbBranchschools = tbBranchschools;
		this.tbRegschools = tbRegschools;

	}

	// Constructors

	/** default constructor */
	public TbTown() {
	}

	// /** full constructor */
	public TbTown(TbCity tbCity, String townName, Integer isValid,
			Timestamp time, Timestamp alterTime, Set tbCurriculums,
			Set tbSchools, Set tbRegschools, Set tbBranchschools) {
		this.tbCity = tbCity;
		this.townName = townName;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.tbCurriculums = tbCurriculums;
		this.tbSchools = tbSchools;
		this.tbRegschools = tbRegschools;
		this.tbBranchschools = tbBranchschools;
	}

	// Property accessors

	public Integer getTownId() {
		return this.townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	public TbCity getTbCity() {
		return this.tbCity;
	}

	public void setTbCity(TbCity tbCity) {
		this.tbCity = tbCity;
	}

	public String getTownName() {
		return this.townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
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

	public Set getTbCurriculums() {
		return this.tbCurriculums;
	}

	public void setTbCurriculums(Set tbCurriculums) {
		this.tbCurriculums = tbCurriculums;
	}

	public Set getTbSchools() {
		return this.tbSchools;
	}

	public void setTbSchools(Set tbSchools) {
		this.tbSchools = tbSchools;
	}

	public Set getTbBranchschools() {
		return this.tbBranchschools;
	}

	public void setTbBranchschools(Set tbBranchschools) {
		this.tbBranchschools = tbBranchschools;
	}

	public Set getTbRegschools() {
		return this.tbRegschools;
	}

	public void setTbRegschools(Set tbRegschools) {
		this.tbRegschools = tbRegschools;
	}
}