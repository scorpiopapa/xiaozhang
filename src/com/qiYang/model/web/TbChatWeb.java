package com.qiYang.model.web;

public class TbChatWeb {
	private Integer chatId;
	private Integer userFromId;
	private String userFromName;
	private Integer userToId;
	private String userToName;
	private String chatPicture;
	private String chatContent;
	private String chatVoice;
	private Integer isValid;
	private Integer chatCommetNum;
	private String time;
	private String alterTime;
	
	public TbChatWeb(Integer chatId, String userFromName, String userToName,
			String chatContent, Integer isValid, String time, String alterTime) {
		super();
		this.chatId = chatId;
		this.userFromName = userFromName;
		this.userToName = userToName;
		this.chatContent = chatContent;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public Integer getChatId() {
		return chatId;
	}
	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}
	public Integer getUserFromId() {
		return userFromId;
	}
	public void setUserFromId(Integer userFromId) {
		this.userFromId = userFromId;
	}
	public String getUserFromName() {
		return userFromName;
	}
	public void setUserFromName(String userFromName) {
		this.userFromName = userFromName;
	}
	public Integer getUserToId() {
		return userToId;
	}
	public void setUserToId(Integer userToId) {
		this.userToId = userToId;
	}
	public String getUserToName() {
		return userToName;
	}
	public void setUserToName(String userToName) {
		this.userToName = userToName;
	}
	public String getChatPicture() {
		return chatPicture;
	}
	public void setChatPicture(String chatPicture) {
		this.chatPicture = chatPicture;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public String getChatVoice() {
		return chatVoice;
	}
	public void setChatVoice(String chatVoice) {
		this.chatVoice = chatVoice;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public Integer getChatCommetNum() {
		return chatCommetNum;
	}
	public void setChatCommetNum(Integer chatCommetNum) {
		this.chatCommetNum = chatCommetNum;
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
