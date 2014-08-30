package com.qiYang.model.web;

public class TbBranchschoolWeb {
	private Integer branchschoolId;
	private Integer townId;
	private Integer schoolId;
	private String townName;
	private String schoolName;
	private Integer headmasterId;
	private String headmasterAccount;
	private String branchschoolName;
	private String branchschoolMinName;
	private String branchschoolAddress;
	private String branchschoolPhone;
	private String branchschoolBusWay;
	private String branchschoolStopLocation;
	private String branchschoolIntroduce;
	private String branchschoolPictureUrl;
	private String branchschoolLongitude;
	private String branchschoolLatitude;
	private Integer imagesNumber; //图片数量
	private Double distance; //距离
	private Integer popularity; //点击率
	private Double longitude;
	private Double latitude;
	private String type;//地图显示颜色类型
	private Integer showType;//0-不显示，1-显示
	private Integer isValid;
	private Integer isNot;//0分校，1总校
	private String time;
	private String alterTime;

	public Integer getHeadmasterId() {
		return headmasterId;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setHeadmasterId(Integer headmasterId) {
		this.headmasterId = headmasterId;
	}

	public String getHeadmasterAccount() {
		return headmasterAccount;
	}
	
	public void setHeadmasterAccount(String headmasterAccount) {
		this.headmasterAccount = headmasterAccount;
	}
	
	public Double getDistance() {
		return distance;
	}
	
	public Integer getImagesNumber() {
		return imagesNumber;
	}

	public void setImagesNumber(Integer imagesNumber) {
		this.imagesNumber = imagesNumber;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public TbBranchschoolWeb(Integer branchschoolId, Integer townId,
			Integer schoolId, String townName, String schoolName,
			String branchschoolName, String branchschoolMinName,
			String branchschoolAddress, String branchschoolPhone,
			String branchschoolBusWay, String branchschoolStopLocation,
			String branchschoolPictureUrl, Integer isValid, String time,
			String alterTime) {
		super();
		this.branchschoolId = branchschoolId;
		this.townId = townId;
		this.schoolId = schoolId;
		this.townName = townName;
		this.schoolName = schoolName;
		this.branchschoolName = branchschoolName;
		this.branchschoolMinName = branchschoolMinName;
		this.branchschoolAddress = branchschoolAddress;
		this.branchschoolPhone = branchschoolPhone;
		this.branchschoolBusWay = branchschoolBusWay;
		this.branchschoolStopLocation = branchschoolStopLocation;
		this.branchschoolPictureUrl = branchschoolPictureUrl;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}

	public TbBranchschoolWeb(Integer branchschoolId, String branchschoolName,
			String branchschoolMinName, String branchschoolPictureUrl,Integer headmasterId,String headmasterAccount) {
		super();
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.branchschoolMinName = branchschoolMinName;
		this.branchschoolPictureUrl = branchschoolPictureUrl;
		this.headmasterId = headmasterId;
		this.headmasterAccount = headmasterAccount;
	}
	

	public TbBranchschoolWeb(Integer branchschoolId, String branchschoolName,
			String branchschoolMinName, String branchschoolLongitude,
			String branchschoolLatitude) {
		super();
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.branchschoolMinName = branchschoolMinName;
		this.branchschoolLongitude = branchschoolLongitude;
		this.branchschoolLatitude = branchschoolLatitude;
	}
	public TbBranchschoolWeb(Integer branchschoolId, String branchschoolName,
			String branchschoolMinName, String branchschoolLongitude,
			String branchschoolLatitude,Integer isNot) {
		super();
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.branchschoolMinName = branchschoolMinName;
		this.branchschoolLongitude = branchschoolLongitude;
		this.branchschoolLatitude = branchschoolLatitude;
		this.isNot=isNot;
	}
	
	
	public TbBranchschoolWeb(Integer branchschoolId, String branchschoolName,
			String branchschoolMinName, String branchschoolAddress,
			String branchschoolPhone, String branchschoolBusWay,
			String branchschoolStopLocation, String branchschoolPictureUrl,String branchschoolIntroduce) {
		super();
		this.branchschoolId = branchschoolId;
		this.branchschoolName = branchschoolName;
		this.branchschoolMinName = branchschoolMinName;
		this.branchschoolAddress = branchschoolAddress;
		this.branchschoolPhone = branchschoolPhone;
		this.branchschoolBusWay = branchschoolBusWay;
		this.branchschoolStopLocation = branchschoolStopLocation;
		this.branchschoolPictureUrl = branchschoolPictureUrl;
		this.branchschoolIntroduce = branchschoolIntroduce;
	}
	
	public TbBranchschoolWeb() {
	}

	public String getBranchschoolIntroduce() {
		return branchschoolIntroduce;
	}

	public void setBranchschoolIntroduce(String branchschoolIntroduce) {
		this.branchschoolIntroduce = branchschoolIntroduce;
	}

	public Integer getBranchschoolId() {
		return branchschoolId;
	}

	public void setBranchschoolId(Integer branchschoolId) {
		this.branchschoolId = branchschoolId;
	}

	public Integer getTownId() {
		return townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public String getTownName() {
		return townName;
	}
	
	public Integer getIsNot() {
		return isNot;
	}

	public void setIsNot(Integer isNot) {
		this.isNot = isNot;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getBranchschoolName() {
		return branchschoolName;
	}

	public void setBranchschoolName(String branchschoolName) {
		this.branchschoolName = branchschoolName;
	}

	public String getBranchschoolMinName() {
		return branchschoolMinName;
	}

	public void setBranchschoolMinName(String branchschoolMinName) {
		this.branchschoolMinName = branchschoolMinName;
	}

	public String getBranchschoolAddress() {
		return branchschoolAddress;
	}

	public void setBranchschoolAddress(String branchschoolAddress) {
		this.branchschoolAddress = branchschoolAddress;
	}

	public String getBranchschoolPhone() {
		return branchschoolPhone;
	}

	public void setBranchschoolPhone(String branchschoolPhone) {
		this.branchschoolPhone = branchschoolPhone;
	}

	public String getBranchschoolBusWay() {
		return branchschoolBusWay;
	}

	public void setBranchschoolBusWay(String branchschoolBusWay) {
		this.branchschoolBusWay = branchschoolBusWay;
	}

	public String getBranchschoolStopLocation() {
		return branchschoolStopLocation;
	}

	public void setBranchschoolStopLocation(String branchschoolStopLocation) {
		this.branchschoolStopLocation = branchschoolStopLocation;
	}

	public String getBranchschoolPictureUrl() {
		return branchschoolPictureUrl;
	}

	public void setBranchschoolPictureUrl(String branchschoolPictureUrl) {
		this.branchschoolPictureUrl = branchschoolPictureUrl;
	}

	
	public String getBranchschoolLongitude() {
		return branchschoolLongitude;
	}

	public void setBranchschoolLongitude(String branchschoolLongitude) {
		this.branchschoolLongitude = branchschoolLongitude;
	}

	public String getBranchschoolLatitude() {
		return branchschoolLatitude;
	}

	public void setBranchschoolLatitude(String branchschoolLatitude) {
		this.branchschoolLatitude = branchschoolLatitude;
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
