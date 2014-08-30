package com.qiYang.action;

import com.opensymphony.xwork2.ActionSupport;
import com.qiYang.service.GogoServiceImpl;
import com.qiYang.util.ResultJson;

public class GogoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1954051029833151750L;
	// 将会被Struts2序列化为JSON字符串的对象
	private ResultJson resultJson;
	private String userName;
	private String userPassword;
	private GogoServiceImpl gogoservice;

	public GogoServiceImpl getGogoservice() {
		return gogoservice;
	}

	public void setGogoservice(GogoServiceImpl gogoservice) {
		this.gogoservice = gogoservice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * 构造方法
	 */
	public GogoAction() {
		// 初始化Map对象

	}

	/**
	 * 测试通过action以视图方式返回JSON数据
	 * 
	 * @return
	 */
	/*
	 * public String testByJSP() { User user = new User(); user.setId("123");
	 * user.setName("JSONActionJSP"); user.setPassword("123");
	 * user.setSay("Hello world !"); JSONObject jsonObject=new JSONObject();
	 * jsonObject.accumulate("user", user); jsonObject.accumulate("success",
	 * true); //这里在request对象中放了一个data，所以struts的result配置中不能有type="redirect"
	 * ServletActionContext.getRequest().setAttribute("data",
	 * jsonObject.toString()); return SUCCESS; };
	 */

	/**
	 * 测试通过action以Struts2默认方式返回JSON数据
	 * 
	 * @return
	 */
	public String login() {
		/*
		 * HttpServletResponse response = ServletActionContext.getResponse();
		 * response.setContentType("text/json"); PrintWriter out = null; try {
		 * out = response.getWriter(); } catch (IOException e) { // 
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据
		/*System.out.println("userName:-->" + this.userName + ";userPassword:-->"
				+ this.userPassword);*/
		if (this.userName == null)
			this.userName = "gogo";
		if (this.userPassword == null)
			this.userPassword = "321";
		setResultJson(gogoservice.phoneLogin(this.userName, this.userPassword));
		resultJson.setCode(0);
		resultJson.setMessage("看得到我吗");
		/*System.out.println(this.resultJson);*/
		/*
		 * out.println(resultJson.toString()); out.flush(); out.close();
		 */

		// 返回结果
		return SUCCESS;
	}

	public ResultJson getResultJson() {
		return resultJson;
	}

	public void setResultJson(ResultJson resultJson) {
		this.resultJson = resultJson;
	}
	/**
	 * 通过action是以传统方式返回JSON数据
	 * 
	 * @throws IOException
	 */
	/*
	 * public void doAction() throws IOException{ HttpServletResponse
	 * response=ServletActionContext.getResponse(); //以下代码从JSON.java中拷过来的
	 * response.setContentType("text/html"); PrintWriter out; out =
	 * response.getWriter(); //将要被返回到客户端的对象 User user=new User();
	 * user.setId("123"); user.setName("JSONActionGeneral");
	 * user.setPassword("JSON");
	 * user.setSay("Hello , i am a action to print a json!"); JSONObject
	 * json=new JSONObject(); json.accumulate("success", true);
	 * json.accumulate("user", user); out.println(json.toString()); //
	 * 因为JSON数据在传递过程中是以普通字符串形式传递的，所以我们也可以手动拼接符合JSON语法规范的字符串输出到客户端 //
	 * 以下这两句的作用与38-46行代码的作用是一样的，将向客户端返回一个User对象，和一个success字段 // String
	 * jsonString=
	 * "{\"user\":{\"id\":\"123\",\"name\":\"JSONActionGeneral\",\"say\":\"Hello , i am a action to print a json!\",\"password\":\"JSON\"},\"success\":true}"
	 * ; // out.println(jsonString); out.flush(); out.close(); }
	 */
	/**
	 * Struts2序列化指定属性时，必须有该属性的getter方法，实际上，如果没有属性，而只有getter方法也是可以的
	 * 
	 * @return
	 */

}
