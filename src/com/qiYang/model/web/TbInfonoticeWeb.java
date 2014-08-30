package com.qiYang.model.web;



public class TbInfonoticeWeb {

		private Integer id;
		private String starttime;
		private String headline;
		private String content;
		private Integer adminId;
		private String adminName;
		private String pictrueUrl;
		private Integer isValid;
		private String time;
		private String alterTime;
		// Constructors
		
		/** default constructor */
		public TbInfonoticeWeb() {
		}

		/** full constructor */
		public TbInfonoticeWeb(String starttime, String headline,Integer adminId,
				String adminName,String content, Integer isValid, String time, String alterTime) {
			this.starttime = starttime;
			this.headline = headline;
			this.content = content;
			this.isValid = isValid;
			this.adminId = adminId;
			this.adminName = adminName;
			this.time = time;
			this.alterTime = alterTime;
		}
		
		public String getPictrueUrl() {
			return pictrueUrl;
		}

		public void setPictrueUrl(String pictrueUrl) {
			this.pictrueUrl = pictrueUrl;
		}

		public TbInfonoticeWeb(Integer id, String starttime, String headline,
				String content, Integer adminId, String adminName,
				String pictrueUrl, Integer isValid, String time,
				String alterTime) {
			super();
			this.id = id;
			this.starttime = starttime;
			this.headline = headline;
			this.content = content;
			this.adminId = adminId;
			this.adminName = adminName;
			this.pictrueUrl = pictrueUrl;
			this.isValid = isValid;
			this.time = time;
			this.alterTime = alterTime;
		}

		public TbInfonoticeWeb(Integer id, String starttime, String headline,
				String adminName) {
			super();
			this.id = id;
			this.starttime = starttime;
			this.headline = headline;
			this.adminName = adminName;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getStarttime() {
			return starttime;
		}

		public void setStarttime(String starttime) {
			this.starttime = starttime;
		}

		public String getHeadline() {
			return headline;
		}

		public void setHeadline(String headline) {
			this.headline = headline;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Integer getAdminId() {
			return adminId;
		}

		public void setAdminId(Integer adminId) {
			this.adminId = adminId;
		}

		public String getAdminName() {
			return adminName;
		}

		public void setAdminName(String adminName) {
			this.adminName = adminName;
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