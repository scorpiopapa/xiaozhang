package com.qiYang.model.web;

public class TbClassnoticeWeb {
	private Integer classNoticeId;
	private Integer courseId;
	private String courseName;
	private Integer userinfoId;
	private String userinfoName;
	private String classNoticeTitle;
	private String classNoticeAddTime;
	private String classNoticeSige;
	private Integer classNoticeSelect;
	private String classNoticeContent;
	private Integer isValid;
	private String time;
	private String alterTime;

	public TbClassnoticeWeb(Integer classNoticeId, Integer courseId,
			String courseName, Integer userinfoId, String userinfoName,
			String classNoticeTitle, String classNoticeAddTime,
			String classNoticeSige, Integer classNoticeSelect,
			String classNoticeContent, Integer isValid, String time,
			String alterTime) {
		super();
		this.classNoticeId = classNoticeId;
		this.courseId = courseId;
		this.courseName = courseName;
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.classNoticeTitle = classNoticeTitle;
		this.classNoticeAddTime = classNoticeAddTime;
		this.classNoticeSige = classNoticeSige;
		this.classNoticeSelect = classNoticeSelect;
		this.classNoticeContent = classNoticeContent;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbClassnoticeWeb(Integer classNoticeId, Integer userinfoId,
			String userinfoName, String classNoticeTitle,
			String classNoticeAddTime, String classNoticeContent) {
		super();
		this.classNoticeId = classNoticeId;
		this.userinfoId = userinfoId;
		this.userinfoName = userinfoName;
		this.classNoticeTitle = classNoticeTitle;
		this.classNoticeAddTime = classNoticeAddTime;
		this.classNoticeContent = classNoticeContent;
	}

	public TbClassnoticeWeb(Integer classNoticeId, String userinfoName,
			String classNoticeTitle, String classNoticeAddTime) {
		super();
		this.classNoticeId = classNoticeId;
		this.userinfoName = userinfoName;
		this.classNoticeTitle = classNoticeTitle;
		this.classNoticeAddTime = classNoticeAddTime;
	}

	public String getClassNoticeTitle() {
		return classNoticeTitle;
	}

	public void setClassNoticeTitle(String classNoticeTitle) {
		this.classNoticeTitle = classNoticeTitle;
	}

	public String getClassNoticeAddTime() {
		return classNoticeAddTime;
	}

	public void setClassNoticeAddTime(String classNoticeAddTime) {
		this.classNoticeAddTime = classNoticeAddTime;
	}

	public String getClassNoticeSige() {
		return classNoticeSige;
	}

	public void setClassNoticeSige(String classNoticeSige) {
		this.classNoticeSige = classNoticeSige;
	}

	public Integer getClassNoticeSelect() {
		return classNoticeSelect;
	}

	public void setClassNoticeSelect(Integer classNoticeSelect) {
		this.classNoticeSelect = classNoticeSelect;
	}

	public String getClassNoticeContent() {
		return classNoticeContent;
	}

	public void setClassNoticeContent(String classNoticeContent) {
		this.classNoticeContent = classNoticeContent;
	}

	public TbClassnoticeWeb() {
		super();
	}

	public Integer getClassNoticeId() {
		return classNoticeId;
	}

	public void setClassNoticeId(Integer classNoticeId) {
		this.classNoticeId = classNoticeId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getUserinfoId() {
		return userinfoId;
	}

	public void setUserinfoId(Integer userinfoId) {
		this.userinfoId = userinfoId;
	}

	public String getUserinfoName() {
		return userinfoName;
	}

	public void setUserinfoName(String userinfoName) {
		this.userinfoName = userinfoName;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
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
