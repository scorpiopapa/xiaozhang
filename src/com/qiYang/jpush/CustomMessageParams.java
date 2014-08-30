package com.qiYang.jpush;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

/*
 * 自定义类型的消息内容
 */
public class CustomMessageParams extends MessageParams {
	public class CustomMsgContent extends MessageParams.MsgContent {
		// message 里的内容类型
		private String contentType = "";
		// 更多的附属信息
		private Map<String, Object> extra = new HashMap<String, Object>();

		public String getContentType() {
			return contentType;
		}

		public void setContentType(String contentType) {
			this.contentType = contentType;
		}

		public Map<String, Object> getExtra() {
			return extra;
		}

		public void setExtra(Map<String, Object> extra) {
			this.extra = extra;
		}

		@Override
		public String toString() {
			Gson gson = new Gson();
			Map<String, String> params = new HashMap<String, String>();
			params.put("title", this.getTitle());
			params.put("message", this.getMessage());
			params.put("content_type", this.getContentType());
			params.put("extras", gson.toJson(this.getExtra()));

			return gson.toJson(params);
		}
	}

	private CustomMsgContent msgContent = new CustomMsgContent();

	@Override
	public CustomMsgContent getMsgContent() {
		return msgContent;
	}
}
