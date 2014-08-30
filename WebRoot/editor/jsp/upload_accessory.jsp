<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.FileRenamePolicy"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page
	import="org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper"%>
<%@ page import="org.json.simple.*"%>
<%
//Struts2  请求 包装过滤器  
//MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;  
 request.setCharacterEncoding("UTF-8");
 response.setContentType("text/html; charset=UTF-8");
 //配置:文件存储路径,对应web路径,文件限制大小
 String realPath = application.getRealPath("");
 String att_lj = realPath + "/attached";
 String att_ctx = request.getContextPath() + "/attached";
 String att_size = "2";
 //kindeditor对应路径
 String ke_url = request.getContextPath() + "/attached/";
 String msg1 = "上传文件大小超过2M的限制!";
 String msg2 = "文件上传失败!";
 String msg3 = "文件上传成功!";
 try {
  int maxPostSize = Integer.parseInt(att_size) * 1024 * 1024;
  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
  String dateString = sdf.format(new Date());
  String saveDirectory = att_lj + "/" + dateString + "/";
  String wjmc = "", wjlj = "", file_ext = "";
  //建立文件夹
  File dir = new File(saveDirectory);
  dir.mkdirs();
  //重命名文件策略
  FileRenamePolicy rfrp = new FileRenamePolicy() {
   public File rename(File file) {
    StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS")
      .format(new Date()));
    int n = (int) (Math.random() * 90000.0D + 10000.0D);
    String guid = now.toString() + n;
    String ext = "";
    String fileName = file.getName();
    int pot = fileName.lastIndexOf(".");
    if (pot > 0) {
     ext = fileName.substring(pot);
    }
    String fileAllName = guid + ext;
    return new File(file.getParent(), fileAllName);
   }
  };
  MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", rfrp);
  //上传文件
  Enumeration<?> files = multi.getFileNames();
  while (files.hasMoreElements()) {
   String name = (String) files.nextElement();
   //附件实际文件名
   wjmc = multi.getOriginalFileName(name);
   int p_dot = wjmc.lastIndexOf('.');
   if (p_dot > 0) {
    file_ext = wjmc.substring(p_dot + 1).toLowerCase();
   }
   File f = multi.getFile(name);
   if (f != null) {
    //拼写上传后文件路径
    String fileName = multi.getFilesystemName(name);
    wjlj = "/" + dateString + "/" + fileName;
   }
  }
  //构造返回参数
  String file_url = att_ctx + wjlj;//获得上传后文件对应web路径
  String id = multi.getParameter("id");//kindeditor的content对应textarea的ID
  String fileTitle = multi.getParameter("imgTitle");//附件说明文字
  if (fileTitle.trim().equals("")) {
   fileTitle = wjmc;
  }
  out.println("<html>");
  out.println("<head>");
  out.println("<title>" + msg3 + "</title>");
  out.println("<meta http-equiv='content-type' content='text/html; charset=utf-8'>");
  out.println("</head>");
  out.println("<body>");
  out.println("<script type='text/javascript'>parent.KE.plugin.accessory.insert('" + id + "', '"
    + file_url + "','" + fileTitle + "','" + file_ext + "','" + ke_url + "');</script>");
  out.println("</body>");
  out.println("</html>");
 } catch (Exception e) {
  String msg = e.getMessage();
  System.out.println(msg);
  if (msg.contains("exceeds limit")) {
   msg = msg1;
  } else {
   msg = msg2;
  }
  out.println("<html>");
  out.println("<head>");
  out.println("<title>error message</title>");
  out.println("<meta http-equiv='content-type' content='text/html; charset=utf-8'>");
  out.println("</head>");
  out.println("<body>");
  out.println("<script type='text/javascript'>alert('" + msg + "');history.back();</script>");
  out.println("</body>");
  out.println("</html>");
 }
%>