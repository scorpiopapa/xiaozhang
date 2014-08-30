package com.qiYang.util;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.jpush.api.ErrorCodeEnum;
import cn.jpush.api.MessageResult;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.ApnsServiceBuilder;
import com.notnoop.exceptions.NetworkIOException;
import com.qiYang.dao.DataBaseDaoImpl;
import com.qiYang.model.TbAttendance;
import com.qiYang.model.TbNotice;
import com.qiYang.model.TbPush;
import com.qiYang.model.TbUserinfo;
import com.qiYang.service.GogoServiceImpl;

public class JpushThread extends Thread{
	
	
	private static final String KEY_NOTICE_TYPE="notice_type";
	private boolean ISDEVELOPMENT=true;
	private String rootUrl;
	private String content;
	private List<String> machines=new ArrayList<String>();
	private List<String> userIds=new ArrayList<String>();
	private TbAttendance[] attendances;
	private String title;
	private Boolean outschool=false;//outschool为true时，离校；
	private Boolean boo;//boo为true时，推送班级通知，学校公告。boo为false推送到离校；
	private Integer createrId;
	private Integer type=0; //1为二版推送，其他为默认
	private List<Integer> receiverIds;
	private GogoServiceImpl gogoService=new GogoServiceImpl();
	private DataBaseDaoImpl daoImpl=new DataBaseDaoImpl();
	
	public JpushThread(Integer createrId,TbAttendance[] attendances, String title,String rootUrl) {
		super();
		this.createrId=createrId;
		this.attendances = attendances;
		this.title = title;
		this.rootUrl = rootUrl;
		this.boo=false;
	}
	public JpushThread(Integer createrId,TbAttendance[] attendances, String title,String rootUrl,Boolean outSchool) {
		super();
		this.createrId=createrId;
		this.attendances = attendances;
		this.title = title;
		this.rootUrl = rootUrl;
		this.boo=false;
		this.outschool=true;
	}
	public JpushThread(Integer createrId,List<Integer> receiverIds,  String content,String title,String rootUrl) {
		super();
		this.createrId=createrId;
		this.receiverIds=receiverIds;
		this.content=content;
		this.title = title;
		this.rootUrl = rootUrl;
		this.boo=true;
	}
	public JpushThread(List<Integer> receiverIds,  String content,String title,String rootUrl) {
		super();
		this.type=1;
		this.receiverIds=receiverIds;
		this.content=content;
		this.title = title;
		this.rootUrl = rootUrl;
		this.boo=true;
	}
	public static void main(String[] args) {
		new JpushThread().start();
	}
	
	public JpushThread() {
		/*this.rootUrl=new TWDataUtil().getImagesLocalhost();
		machines.add("f3d3a832c25604bfa923637187ffd8e9f280c781753144e392664b4531e0de8a");
		this.content="随便来";
		this.title = "班级通知";*/
	}
	@Override
	public void run() {
		if(type==1){
			saveTbPushRecords();
		}else{
		String c = StringUtils.abbreviate(content, 50);
		getReceiversAndMachines(boo);
		if(boo){
		sendAndroid(userIds, c,title);
		sendIOS(machines, c, rootUrl,title);
			}
		}
	}
	public void saveTbPushRecords(){
		if(receiverIds==null||receiverIds.isEmpty())
			return;
		for (Integer reciverId : receiverIds) {
			 TbUserinfo tbUserinfo = gogoService.getObjectByClazz(TbUserinfo.class, reciverId);
			 List<TbPush> tbPushs = gogoService.getTbPush(tbUserinfo);
			 if(tbUserinfo.getUserInfoRoot()==3){
				 TbUserinfo parentUser = TWObjectUtil.getParentByChild(tbUserinfo);
				 Integer parentNum=0;
				 if(parentUser!=null&&parentUser.getUserInfoId()!=null)
				 parentNum = gogoService.checkTbPush(parentUser);
				 if(parentNum>=1){
					 List<TbPush> parentPush = gogoService.getTbPush(parentUser);
					 tbPushs.addAll(parentPush);
					 for (TbPush tbPush : parentPush) {
						 if(tbPush.getPushType().equals("Android")){
								if(userIds.isEmpty())
									userIds.add(tbPush.getTbUserinfo().getUserInfoId().toString());
								else{
									if(!userIds.contains(tbPush.getTbUserinfo().getUserInfoId().toString()))
										userIds.add(tbPush.getTbUserinfo().getUserInfoId().toString());
					}
				 }
			 }
		   }
		}
			 if(tbPushs==null||tbPushs.isEmpty())
				 continue;
			getData(tbPushs, reciverId);
				 }
		sendAndroid(userIds, StringUtils.abbreviate(content, 50),title);
		sendIOS(machines, StringUtils.abbreviate(content, 50), rootUrl,title);
	}
	
	public void getReceiversAndMachines(Boolean boo2){
		//boo2为true，推送班级通知
		if(boo2){
		if(receiverIds==null||receiverIds.isEmpty())
			return;
		for (Integer reciverId : receiverIds) {
			if(reciverId.equals(createrId))
				continue;
			 TbUserinfo tbUserinfo = gogoService.getObjectByClazz(TbUserinfo.class, reciverId);
			 List<TbPush> tbPushs = gogoService.getTbPush(tbUserinfo);
			 if(tbUserinfo.getUserInfoRoot()==3){
				 TbUserinfo parentUser = TWObjectUtil.getParentByChild(tbUserinfo);
				 Integer parentNum=0;
				 if(parentUser!=null&&parentUser.getUserInfoId()!=null)
				 parentNum = gogoService.checkTbPush(parentUser);
				 if(parentNum>=1){
					 List<TbPush> parentPush = gogoService.getTbPush(parentUser);
					 tbPushs.addAll(parentPush);
					 for (TbPush tbPush : parentPush) {
						 if(tbPush.getPushType().equals("Android")){
								if(userIds.isEmpty())
									userIds.add(tbPush.getTbUserinfo().getUserInfoId().toString());
								else{
									if(!userIds.contains(tbPush.getTbUserinfo().getUserInfoId().toString()))
										userIds.add(tbPush.getTbUserinfo().getUserInfoId().toString());
					}
				 }
			 }
		   }
		}
			 if(tbPushs==null||tbPushs.isEmpty())
				 continue;
				 if("学校通知".equals(this.title)){
				 if(tbUserinfo.getUserInfoRoot()<=1)
					 getData(tbPushs, reciverId);
				 }else
				     getData(tbPushs, reciverId);
				 }
			 }
		//boo为false推送到离校；
		else{
			Map<String, String> contents=new HashMap<String, String>();
			Map<String, String> contentsIOS=new HashMap<String, String>();
			for (TbAttendance tbAttendance : attendances) {
				String content="";
				TbUserinfo userinfo = tbAttendance.getTbUserinfo();
				Integer userInfoId = userinfo.getUserInfoId();
				TbUserinfo tbUserinfo = gogoService.getObjectByClazz(TbUserinfo.class, userInfoId);
				Integer state = tbAttendance.getIsLate();
				Timestamp nowTime = tbAttendance.getAlterTime();
				String time = TWDataUtil.timestampToHHmm(nowTime);
				if(this.outschool){
						if(state==0)
						content="尊敬的家长，您的孩子"+tbUserinfo.getUserInfoName()+"已在今天"+time+"离校";
				}else{
				if(state==0)
					content="尊敬的家长，您的孩子"+tbUserinfo.getUserInfoName()+"未到校或迟于到校时间";
				else
					content="尊敬的家长，您的孩子"+tbUserinfo.getUserInfoName()+"已在今天"+time+"平安到校";
				}
				TbUserinfo tbUserinfoByParentId = TWObjectUtil.getParentByChild(userinfo);
				TbUserinfo issuer = TWObjectUtil.getUserinfoSetId(this.createrId);
				TbNotice tbNotice=new TbNotice(tbUserinfoByParentId, issuer, null,content, 1, nowTime, nowTime);
				String result="";
				if(StringUtils.isNotEmpty(content))
				result = daoImpl.addObject(tbNotice);
				if("fail".equals(result))
					continue;
				 List<TbPush> tbPushs1 = gogoService.getTbPush(tbUserinfoByParentId);
				 if(this.outschool){
					 if(state==1)
						 tbPushs1=null;
				 }
				 if(tbPushs1==null||tbPushs1.isEmpty())
					 continue;
				 else   {
					 if(2==tbUserinfoByParentId.getUserInfoRoot()){
						 Integer id = tbUserinfoByParentId.getUserInfoId();
					 contentsIOS = getData001(tbPushs1, id,content,contentsIOS);
					 contents.put(id.toString(), content);
					 }
				 }
			}
			sendAndroid001(userIds, contents,title);
			for (String machine : machines) {
				List<String> machinesIos=new ArrayList<String>();
				machinesIos.add(machine);
				sendIOS001(machinesIos, contentsIOS.get(machine), rootUrl,title);
			}
		}
	}
	public void getData(List<TbPush> tbPushs,Integer id){
		for (TbPush tbPush2 : tbPushs) {
			if(tbPush2.getPushType().equals("Android")){
				if(userIds.isEmpty())
					userIds.add(id.toString());
				else{
					if(!userIds.contains(id.toString()))
						userIds.add(id.toString());
			}
		}
		if(tbPush2.getPushType().equals("IOS"))
			machines.add(tbPush2.getPushToken());
		}
	}
	public Map<String, String> getData001(List<TbPush> tbPushs,Integer id,String content, Map<String, String> contentsIOS){
		for (TbPush tbPush2 : tbPushs) {
			if(tbPush2.getPushType().equals("Android")){
				if(userIds.isEmpty())
					userIds.add(id.toString());
				else{
					if(!userIds.contains(id.toString()))
						userIds.add(id.toString());
				}
			}
			if(tbPush2.getPushType().equals("IOS"))
				machines.add(tbPush2.getPushToken());
			contentsIOS.put(tbPush2.getPushToken(), content);
		}
		return contentsIOS;
	}
	
	//苹果推送
	public void sendIOS(List<String> tokens, String msgInfo,String url,String title) {
		String payload = null;
		
		if("班级通知".equals(title))
		payload = APNS.newPayload().alertBody(msgInfo).sound("default").badge(1).customField(KEY_NOTICE_TYPE, 100)
				.build();
		if("学校通知".equals(title))
			payload = APNS.newPayload().alertBody(msgInfo).sound("default").badge(1).customField(KEY_NOTICE_TYPE, 101)
			.build();
		if("到离校管理".equals(title))
			payload = APNS.newPayload().alertBody(msgInfo).sound("default").badge(1).customField(KEY_NOTICE_TYPE, 102)
			.build();
		if("聊天".equals(title))
			payload = APNS.newPayload().alertBody(msgInfo).sound("default").badge(1).customField(KEY_NOTICE_TYPE, 103)
			.build();
		if("校掌推送".equals(title))
			payload = APNS.newPayload().alertBody(msgInfo).sound("default").badge(1).customField(KEY_NOTICE_TYPE, 106)
			.build();
		String certPwd = "123456";
		
		
		ApnsServiceBuilder apnsServiceBuilder = APNS.newService();
		ApnsService apnsService;
//		System.out.println("url:"+url+",token:"+tokens);
		if (!ISDEVELOPMENT) {
			apnsService = apnsServiceBuilder
					.withCert(url+File.separator+"xiaozhang_developer.p12", certPwd)
					.withProductionDestination().build();
		} else {
					apnsService = apnsServiceBuilder
					.withCert(url+File.separator+"xiaozhang_developer.p12", certPwd)
					.withSandboxDestination().build();
		}
		try {
			apnsService.push(tokens, payload);
		} catch (NetworkIOException e) {
			e.printStackTrace();
		}
	}
	//苹果推送
	public void sendIOS001(List<String> tokens,String msgInfo,String url,String title) {
		String payload = null;
		
		if("班级通知".equals(title))
			payload = APNS.newPayload().alertBody(msgInfo).sound("default").badge(1).customField(KEY_NOTICE_TYPE, 100)
			.build();
		if("学校通知".equals(title))
			payload = APNS.newPayload().alertBody(msgInfo).sound("default").badge(1).customField(KEY_NOTICE_TYPE, 101)
			.build();
		if("到离校管理".equals(title))
			payload = APNS.newPayload().alertBody(msgInfo).sound("default").badge(1).customField(KEY_NOTICE_TYPE, 102)
			.build();
		if("聊天".equals(title))
			payload = APNS.newPayload().alertBody(msgInfo).sound("default").badge(1).customField(KEY_NOTICE_TYPE, 103)
			.build();
		String certPwd = "123456";
		
		
		ApnsServiceBuilder apnsServiceBuilder = APNS.newService();
		ApnsService apnsService;
//		System.out.println("url:"+url+",token:"+tokens);
		if (!ISDEVELOPMENT) {
			apnsService = apnsServiceBuilder
					.withCert(url+File.separator+"xiaozhang_developer.p12", certPwd)
					.withProductionDestination().build();
		} else {
			apnsService = apnsServiceBuilder
					.withCert(url+File.separator+"xiaozhang_developer.p12", certPwd)
					.withSandboxDestination().build();
		}
		try {
			apnsService.push(tokens, payload);
		} catch (NetworkIOException e) {
			e.printStackTrace();
		}
	}
	//安卓
	public void sendAndroid(List<String> tokens, String content,String title) {
		JPushClientV2 jpushex = new JPushClientV2();
		for (int i = 0; i < tokens.size(); i++) {
//			System.out.println("IP:-------->"+tokens.get(i)+"content--->"+content);
			MessageResult msgResult=null;
			msgResult = JPushClientV2.testSend(title, content,
					tokens.get(i));
			if (null != msgResult) {
				if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR
						.value()) {
//					System.out.println("发送成功， sendNo="
//							+ msgResult.getSendno());
				} else {
					System.out.println("发送失败， 错误代码="
							+ msgResult.getErrcode() + ", 错误消息="
							+ msgResult.getErrmsg());
				}
			} else {
				System.out.println("无法获取数据，检查网络是否超时");
			}
		}
	}
	//安卓
	public void sendAndroid001(List<String> tokens, Map<String, String> contents,String title) {
		JPushClientV2 jpushex = new JPushClientV2();
		for (int i = 0; i < tokens.size(); i++) {
//			System.out.println("IP:-------->"+tokens.get(i)+"content--->"+contents.get(tokens.get(i)));
			MessageResult msgResult=null;
			msgResult = JPushClientV2.testSend(title, contents.get(tokens.get(i)),
					tokens.get(i));
			if (null != msgResult) {
				if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR
						.value()) {
//					System.out.println("发送成功， sendNo="
//							+ msgResult.getSendno());
				} else {
					System.out.println("发送失败， 错误代码="
							+ msgResult.getErrcode() + ", 错误消息="
							+ msgResult.getErrmsg());
				}
			} else {
				System.out.println("无法获取数据，检查网络是否超时");
			}
		}
	}


}
