package com.qiYang.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.qiYang.model.web.TbUserinfoWeb;
import com.qiYang.util.TWObjectUtil;
import com.qiYang.util.TWPictureUtil;

/**
 * TbUserinfo entity. @author MyEclipse Persistence Tools
 */

public class TbUserinfo implements java.io.Serializable {

	// Fields

	private Integer userInfoId;
	private TbSchool tbSchool;
	private TbBranchschool tbBranchschool;
	private String userinfoQq;
	private String studentClass;
	private String studentSchool;
	private String userInfoName;
	private String userInfoSex;
	private Date userInfoBirthday;
	private String userInfoPhone;
	private String userInfoAvatar;
	private String userInfoSign;
	private Date userInfoVip;
	private String graduateSchool;
	private String userInfoEmail;
	private String workYear;
	private String userInfoCourse;
	private Integer userInfoRoot;
	private Integer isValid;
	private Timestamp time;
	private Timestamp alterTime;
	private Set tbComplainsForUserInfoId = new HashSet(0);
	private Set tbComplainsForTbUserInfoId = new HashSet(0);
	private Set tbUserphotos = new HashSet(0);
	private Set tbPushs = new HashSet(0);
	private Set tbRelationsForUserInfoId = new HashSet(0);
	private Set tbRelationsForTbUserInfoId = new HashSet(0);
	private Set tbBillses = new HashSet(0);
	private Set tbChatsForUserInfoId = new HashSet(0);
	private Set tbClassnotices = new HashSet(0);
	private Set tbChatsForTbUserInfoId = new HashSet(0);
	private Set tbHistoryquestions = new HashSet(0);
	private Set tbAttendances = new HashSet(0);
	private Set tbNoticesForIssuerId = new HashSet(0);
	private Set tbNoticesForParentId = new HashSet(0);
	private Set tbUserinfoclasses = new HashSet(0);
	private Set tbTests = new HashSet(0);
	private Set tbUsers = new HashSet(0);
	private Set tbComplaindetailses = new HashSet(0);
	private Set tbTestfinishs = new HashSet(0);
	private Set tbViptimes = new HashSet(0);
	private Set tbReviewsForTbUserInfoId = new HashSet(0);
	private Set tbReviewsForUserInfoId = new HashSet(0);
	private Set tbSchoolnotices = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbUserinfo() {
	}

	/** full constructor */
	public TbUserinfo(TbSchool tbSchool, TbBranchschool tbBranchschool,
			String userinfoQq, String studentClass, String studentSchool,
			String userInfoName, String userInfoSex, Date userInfoBirthday,
			String userInfoPhone, String userInfoAvatar, String userInfoSign,
			Date userInfoVip, String graduateSchool, String userInfoEmail,
			String workYear, String userInfoCourse, Integer userInfoRoot,
			Integer isValid, Timestamp time, Timestamp alterTime,
			Set tbComplainsForUserInfoId, Set tbComplainsForTbUserInfoId,
			Set tbUserphotos, Set tbPushs, Set tbRelationsForUserInfoId,
			Set tbRelationsForTbUserInfoId, Set tbBillses,
			Set tbChatsForUserInfoId, Set tbClassnotices,
			Set tbChatsForTbUserInfoId, Set tbHistoryquestions,
			Set tbAttendances, Set tbNoticesForIssuerId,
			Set tbNoticesForParentId, Set tbUserinfoclasses, Set tbTests,
			Set tbUsers, Set tbComplaindetailses, Set tbTestfinishs,
			Set tbViptimes, Set tbReviewsForTbUserInfoId,
			Set tbReviewsForUserInfoId, Set tbSchoolnotices) {
		this.tbSchool = tbSchool;
		this.tbBranchschool = tbBranchschool;
		this.userinfoQq = userinfoQq;
		this.studentClass = studentClass;
		this.studentSchool = studentSchool;
		this.userInfoName = userInfoName;
		this.userInfoSex = userInfoSex;
		this.userInfoBirthday = userInfoBirthday;
		this.userInfoPhone = userInfoPhone;
		this.userInfoAvatar = userInfoAvatar;
		this.userInfoSign = userInfoSign;
		this.userInfoVip = userInfoVip;
		this.graduateSchool = graduateSchool;
		this.userInfoEmail = userInfoEmail;
		this.workYear = workYear;
		this.userInfoCourse = userInfoCourse;
		this.userInfoRoot = userInfoRoot;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
		this.tbComplainsForUserInfoId = tbComplainsForUserInfoId;
		this.tbComplainsForTbUserInfoId = tbComplainsForTbUserInfoId;
		this.tbUserphotos = tbUserphotos;
		this.tbPushs = tbPushs;
		this.tbRelationsForUserInfoId = tbRelationsForUserInfoId;
		this.tbRelationsForTbUserInfoId = tbRelationsForTbUserInfoId;
		this.tbBillses = tbBillses;
		this.tbChatsForUserInfoId = tbChatsForUserInfoId;
		this.tbClassnotices = tbClassnotices;
		this.tbChatsForTbUserInfoId = tbChatsForTbUserInfoId;
		this.tbHistoryquestions = tbHistoryquestions;
		this.tbAttendances = tbAttendances;
		this.tbNoticesForIssuerId = tbNoticesForIssuerId;
		this.tbNoticesForParentId = tbNoticesForParentId;
		this.tbUserinfoclasses = tbUserinfoclasses;
		this.tbTests = tbTests;
		this.tbUsers = tbUsers;
		this.tbComplaindetailses = tbComplaindetailses;
		this.tbTestfinishs = tbTestfinishs;
		this.tbViptimes = tbViptimes;
		this.tbReviewsForTbUserInfoId = tbReviewsForTbUserInfoId;
		this.tbReviewsForUserInfoId = tbReviewsForUserInfoId;
		this.tbSchoolnotices = tbSchoolnotices;
	}
	public TbUserinfo(TbSchool tbSchool,
			TbBranchschool tbBranchschool, String studentClass,
			String studentSchool, String userInfoName, String userInfoSex,
			Date userInfoBirthday, String userInfoPhone, String userInfoAvatar,
			String userInfoSign, Date userInfoVip, String graduateSchool,
			String userInfoEmail, String workYear, String userInfoCourse,
			Integer userInfoRoot, Integer isValid, Timestamp time,
			Timestamp alterTime) {
		super();
		this.tbSchool = tbSchool;
		this.tbBranchschool = tbBranchschool;
		this.studentClass = studentClass;
		this.studentSchool = studentSchool;
		this.userInfoName = userInfoName;
		this.userInfoSex = userInfoSex;
		this.userInfoBirthday = userInfoBirthday;
		this.userInfoPhone = userInfoPhone;
		this.userInfoAvatar = userInfoAvatar;
		this.userInfoSign = userInfoSign;
		this.userInfoVip = userInfoVip;
		this.graduateSchool = graduateSchool;
		this.userInfoEmail = userInfoEmail;
		this.workYear = workYear;
		this.userInfoCourse = userInfoCourse;
		this.userInfoRoot = userInfoRoot;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbUserinfo(TbSchool tbSchool,
			TbBranchschool tbBranchschool,String userinfoQq, String studentClass,
			String studentSchool, String userInfoName, String userInfoSex,
			Date userInfoBirthday, String userInfoPhone, String userInfoAvatar,
			String userInfoSign, Date userInfoVip, String graduateSchool,
			String userInfoEmail, String workYear, String userInfoCourse,
			Integer userInfoRoot, Integer isValid, Timestamp time,
			Timestamp alterTime) {
		super();
		this.tbSchool = tbSchool;
		this.tbBranchschool = tbBranchschool;
		this.userinfoQq = userinfoQq;
		this.studentClass = studentClass;
		this.studentSchool = studentSchool;
		this.userInfoName = userInfoName;
		this.userInfoSex = userInfoSex;
		this.userInfoBirthday = userInfoBirthday;
		this.userInfoPhone = userInfoPhone;
		this.userInfoAvatar = userInfoAvatar;
		this.userInfoSign = userInfoSign;
		this.userInfoVip = userInfoVip;
		this.graduateSchool = graduateSchool;
		this.userInfoEmail = userInfoEmail;
		this.workYear = workYear;
		this.userInfoCourse = userInfoCourse;
		this.userInfoRoot = userInfoRoot;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbUserinfoWeb toPageVo() {
		String avatar = this.userInfoAvatar == null ? "" : TWPictureUtil
				.getNomalPicPath(this.userInfoAvatar);
		String sign = this.userInfoSign == null ? "" : StringUtils.abbreviate(
				this.userInfoSign, 23);
		TbUserinfo userinfo = TWObjectUtil.getUserinfoSetId(this.userInfoId);
		TbUserinfo parent = TWObjectUtil.getParentByChild(userinfo);
		TbUserinfoWeb tbUserinfoWeb = new TbUserinfoWeb(this.userInfoId, this.userInfoName, avatar,
				sign,TWObjectUtil.getUserName(this.userInfoId),TWObjectUtil.getString(parent.getUserInfoName()));
		tbUserinfoWeb.setUserinfoAvatar(TWPictureUtil.getNomalPicPath(this.userInfoAvatar));
		return tbUserinfoWeb;
	}
	// Property accessors

	public Integer getUserInfoId() {
		return this.userInfoId;
	}

	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}

	public TbSchool getTbSchool() {
		return this.tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
	}

	public String getUserinfoQq() {
		return this.userinfoQq;
	}

	public void setUserinfoQq(String userinfoQq) {
		this.userinfoQq = userinfoQq;
	}

	public String getStudentClass() {
		return this.studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getStudentSchool() {
		return this.studentSchool;
	}

	public void setStudentSchool(String studentSchool) {
		this.studentSchool = studentSchool;
	}

	public String getUserInfoName() {
		return this.userInfoName;
	}

	public void setUserInfoName(String userInfoName) {
		this.userInfoName = userInfoName;
	}

	public String getUserInfoSex() {
		return this.userInfoSex;
	}

	public void setUserInfoSex(String userInfoSex) {
		this.userInfoSex = userInfoSex;
	}

	public Date getUserInfoBirthday() {
		return this.userInfoBirthday;
	}

	public void setUserInfoBirthday(Date userInfoBirthday) {
		this.userInfoBirthday = userInfoBirthday;
	}

	public String getUserInfoPhone() {
		return this.userInfoPhone;
	}

	public void setUserInfoPhone(String userInfoPhone) {
		this.userInfoPhone = userInfoPhone;
	}

	public String getUserInfoAvatar() {
		return this.userInfoAvatar;
	}

	public void setUserInfoAvatar(String userInfoAvatar) {
		this.userInfoAvatar = userInfoAvatar;
	}

	public String getUserInfoSign() {
		return this.userInfoSign;
	}

	public void setUserInfoSign(String userInfoSign) {
		this.userInfoSign = userInfoSign;
	}

	public Date getUserInfoVip() {
		return this.userInfoVip;
	}

	public void setUserInfoVip(Date userInfoVip) {
		this.userInfoVip = userInfoVip;
	}

	public String getGraduateSchool() {
		return this.graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getUserInfoEmail() {
		return this.userInfoEmail;
	}

	public void setUserInfoEmail(String userInfoEmail) {
		this.userInfoEmail = userInfoEmail;
	}

	public String getWorkYear() {
		return this.workYear;
	}

	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}

	public String getUserInfoCourse() {
		return this.userInfoCourse;
	}

	public void setUserInfoCourse(String userInfoCourse) {
		this.userInfoCourse = userInfoCourse;
	}

	public Integer getUserInfoRoot() {
		return this.userInfoRoot;
	}

	public void setUserInfoRoot(Integer userInfoRoot) {
		this.userInfoRoot = userInfoRoot;
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

	public Set getTbComplainsForUserInfoId() {
		return this.tbComplainsForUserInfoId;
	}

	public void setTbComplainsForUserInfoId(Set tbComplainsForUserInfoId) {
		this.tbComplainsForUserInfoId = tbComplainsForUserInfoId;
	}

	public Set getTbComplainsForTbUserInfoId() {
		return this.tbComplainsForTbUserInfoId;
	}

	public void setTbComplainsForTbUserInfoId(Set tbComplainsForTbUserInfoId) {
		this.tbComplainsForTbUserInfoId = tbComplainsForTbUserInfoId;
	}

	public Set getTbUserphotos() {
		return this.tbUserphotos;
	}

	public void setTbUserphotos(Set tbUserphotos) {
		this.tbUserphotos = tbUserphotos;
	}

	public Set getTbPushs() {
		return this.tbPushs;
	}

	public void setTbPushs(Set tbPushs) {
		this.tbPushs = tbPushs;
	}

	public Set getTbRelationsForUserInfoId() {
		return this.tbRelationsForUserInfoId;
	}

	public void setTbRelationsForUserInfoId(Set tbRelationsForUserInfoId) {
		this.tbRelationsForUserInfoId = tbRelationsForUserInfoId;
	}

	public Set getTbRelationsForTbUserInfoId() {
		return this.tbRelationsForTbUserInfoId;
	}

	public void setTbRelationsForTbUserInfoId(Set tbRelationsForTbUserInfoId) {
		this.tbRelationsForTbUserInfoId = tbRelationsForTbUserInfoId;
	}

	public Set getTbBillses() {
		return this.tbBillses;
	}

	public void setTbBillses(Set tbBillses) {
		this.tbBillses = tbBillses;
	}

	public Set getTbChatsForUserInfoId() {
		return this.tbChatsForUserInfoId;
	}

	public void setTbChatsForUserInfoId(Set tbChatsForUserInfoId) {
		this.tbChatsForUserInfoId = tbChatsForUserInfoId;
	}

	public Set getTbClassnotices() {
		return this.tbClassnotices;
	}

	public void setTbClassnotices(Set tbClassnotices) {
		this.tbClassnotices = tbClassnotices;
	}

	public Set getTbChatsForTbUserInfoId() {
		return this.tbChatsForTbUserInfoId;
	}

	public void setTbChatsForTbUserInfoId(Set tbChatsForTbUserInfoId) {
		this.tbChatsForTbUserInfoId = tbChatsForTbUserInfoId;
	}

	public Set getTbHistoryquestions() {
		return this.tbHistoryquestions;
	}

	public void setTbHistoryquestions(Set tbHistoryquestions) {
		this.tbHistoryquestions = tbHistoryquestions;
	}

	public Set getTbAttendances() {
		return this.tbAttendances;
	}

	public void setTbAttendances(Set tbAttendances) {
		this.tbAttendances = tbAttendances;
	}

	public Set getTbNoticesForIssuerId() {
		return this.tbNoticesForIssuerId;
	}

	public void setTbNoticesForIssuerId(Set tbNoticesForIssuerId) {
		this.tbNoticesForIssuerId = tbNoticesForIssuerId;
	}

	public Set getTbNoticesForParentId() {
		return this.tbNoticesForParentId;
	}

	public void setTbNoticesForParentId(Set tbNoticesForParentId) {
		this.tbNoticesForParentId = tbNoticesForParentId;
	}

	public Set getTbUserinfoclasses() {
		return this.tbUserinfoclasses;
	}

	public void setTbUserinfoclasses(Set tbUserinfoclasses) {
		this.tbUserinfoclasses = tbUserinfoclasses;
	}

	public Set getTbTests() {
		return this.tbTests;
	}

	public void setTbTests(Set tbTests) {
		this.tbTests = tbTests;
	}

	public Set getTbUsers() {
		return this.tbUsers;
	}

	public void setTbUsers(Set tbUsers) {
		this.tbUsers = tbUsers;
	}

	public Set getTbComplaindetailses() {
		return this.tbComplaindetailses;
	}

	public void setTbComplaindetailses(Set tbComplaindetailses) {
		this.tbComplaindetailses = tbComplaindetailses;
	}

	public Set getTbTestfinishs() {
		return this.tbTestfinishs;
	}

	public void setTbTestfinishs(Set tbTestfinishs) {
		this.tbTestfinishs = tbTestfinishs;
	}

	public Set getTbViptimes() {
		return this.tbViptimes;
	}

	public void setTbViptimes(Set tbViptimes) {
		this.tbViptimes = tbViptimes;
	}

	public Set getTbReviewsForTbUserInfoId() {
		return this.tbReviewsForTbUserInfoId;
	}

	public void setTbReviewsForTbUserInfoId(Set tbReviewsForTbUserInfoId) {
		this.tbReviewsForTbUserInfoId = tbReviewsForTbUserInfoId;
	}

	public Set getTbReviewsForUserInfoId() {
		return this.tbReviewsForUserInfoId;
	}

	public void setTbReviewsForUserInfoId(Set tbReviewsForUserInfoId) {
		this.tbReviewsForUserInfoId = tbReviewsForUserInfoId;
	}

	public Set getTbSchoolnotices() {
		return this.tbSchoolnotices;
	}

	public void setTbSchoolnotices(Set tbSchoolnotices) {
		this.tbSchoolnotices = tbSchoolnotices;
	}

}