package com.qiYang.model;

import java.sql.Timestamp;

import com.qiYang.model.web.TbChatWeb;
import com.qiYang.util.TWObject;
import com.qiYang.util.TWObjectUtil;

/**
 * TbChat entity. @author MyEclipse Persistence Tools
 */

public class TbChat implements java.io.Serializable {

	// Fields

	private Integer chatId;
	private TbUserinfo tbUserinfoByUserInfoId;
	private TbBranchschool tbBranchschool;
	private TbUserinfo tbUserinfoByTbUserInfoId;
	private String chatPicture;
	private String chatContent;
	private String chatVoice;
	private Integer isValid;
	private Integer chatCommetNum;
	private Timestamp time;
	private Timestamp alterTime;

	// Constructors

	/** default constructor */
	public TbChat() {
	}

	/** full constructor */
	public TbChat(TbUserinfo tbUserinfoByUserInfoId,
			TbBranchschool tbBranchschool, TbUserinfo tbUserinfoByTbUserInfoId,
			String chatPicture, String chatContent, String chatVoice,
			Integer isValid, Integer chatCommetNum, Timestamp time,
			Timestamp alterTime) {
		this.tbUserinfoByUserInfoId = tbUserinfoByUserInfoId;
		this.tbBranchschool = tbBranchschool;
		this.tbUserinfoByTbUserInfoId = tbUserinfoByTbUserInfoId;
		this.chatPicture = chatPicture;
		this.chatContent = chatContent;
		this.chatVoice = chatVoice;
		this.isValid = isValid;
		this.chatCommetNum = chatCommetNum;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbChat(TbUserinfo tbUserinfoByUserInfoId,
			TbUserinfo tbUserinfoByTbUserInfoId, String chatContent,Integer isValid,
			Timestamp time, Timestamp alterTime) {
		super();
		this.tbUserinfoByUserInfoId = tbUserinfoByUserInfoId;
		this.tbUserinfoByTbUserInfoId = tbUserinfoByTbUserInfoId;
		this.chatContent = chatContent;
		this.isValid = isValid;
		this.time = time;
		this.alterTime = alterTime;
	}
	public TbChatWeb toPageWeb(){
		TWObject twObject=new TWObject();
		TbUserinfo userFrom = twObject.isNullTbUserinfo(tbUserinfoByUserInfoId);
		TbUserinfo userTo = twObject.isNullTbUserinfo(tbUserinfoByTbUserInfoId);
		return new TbChatWeb(this.chatId, userFrom.getUserInfoName(), userTo.getUserInfoName(), this.chatContent, this.isValid, TWObjectUtil.getTimestamp(this.time), TWObjectUtil.getTimestamp(this.alterTime));
	}
	// Property accessors

	public Integer getChatId() {
		return this.chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	public TbUserinfo getTbUserinfoByUserInfoId() {
		return this.tbUserinfoByUserInfoId;
	}

	public void setTbUserinfoByUserInfoId(TbUserinfo tbUserinfoByUserInfoId) {
		this.tbUserinfoByUserInfoId = tbUserinfoByUserInfoId;
	}

	public TbBranchschool getTbBranchschool() {
		return this.tbBranchschool;
	}

	public void setTbBranchschool(TbBranchschool tbBranchschool) {
		this.tbBranchschool = tbBranchschool;
	}

	public TbUserinfo getTbUserinfoByTbUserInfoId() {
		return this.tbUserinfoByTbUserInfoId;
	}

	public void setTbUserinfoByTbUserInfoId(TbUserinfo tbUserinfoByTbUserInfoId) {
		this.tbUserinfoByTbUserInfoId = tbUserinfoByTbUserInfoId;
	}

	public String getChatPicture() {
		return this.chatPicture;
	}

	public void setChatPicture(String chatPicture) {
		this.chatPicture = chatPicture;
	}

	public String getChatContent() {
		return this.chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public String getChatVoice() {
		return this.chatVoice;
	}

	public void setChatVoice(String chatVoice) {
		this.chatVoice = chatVoice;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getChatCommetNum() {
		return this.chatCommetNum;
	}

	public void setChatCommetNum(Integer chatCommetNum) {
		this.chatCommetNum = chatCommetNum;
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

}