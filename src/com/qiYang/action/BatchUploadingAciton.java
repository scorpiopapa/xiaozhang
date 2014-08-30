package com.qiYang.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.qiYang.service.SecondaryDevelopmen;
import com.qiYang.util.JsonTools;
import com.qiYang.util.TWPictureUtil;

public class BatchUploadingAciton extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5615143510753252705L;
	private static Logger logger = Logger.getLogger(BatchUploadingAciton.class);
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String encoding = request.getCharacterEncoding();
		response.setContentType("text/json;charset=" + encoding);
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		String action_flag = request.getParameter("action_flag");
		String schoolId=null;
		String pusherName=null;
		List<FileItem> items = new ArrayList<FileItem>();
		String[] fileNames = null;
		String rootPath =null;
		@SuppressWarnings("deprecation")
		boolean isMultipart = FileUploadBase.isMultipartContent(request);
		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				logger.error(e);
			}
			Iterator<FileItem> iter = items.iterator();
			rootPath = request.getSession().getServletContext()
						.getRealPath("images/").replace("\\", "/");
			String[] fileNamesTemp = new String[items.size()];
			int fileNum = 0;
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if (item.isFormField()) {
					if ("action_flag".equals(item.getFieldName()))
						action_flag = item.getString("utf-8");
					if ("schoolId".equals(item.getFieldName()))
						schoolId = item.getString("utf-8");
					if ("pusherName".equals(item.getFieldName()))
						pusherName = item.getString("utf-8");
				} else {
					String fileName = null;
					if(!"file".equals(item.getName())){
					StringBuffer name = new StringBuffer(item.getName());
					name.delete(0, name.lastIndexOf("."));
					name.insert(0, String.valueOf(System.currentTimeMillis()));
					fileName=name.toString();
					}else{
						StringBuffer name = new StringBuffer();
						name.append(System.currentTimeMillis());
						name.append(".");
						name.append("jpg");
						fileName=name.toString();
					}
					String result = TWPictureUtil.getUploadedFile01(rootPath, item,
							fileName);
					if (result != null) {
						fileNamesTemp[fileNum] = result;
						fileNum++;
					}
				}
			}
			if(fileNum>0){
			fileNames=new String[fileNum];
			int j=0;
			for (int i = 0; i < fileNamesTemp.length; i++) {
				if(fileNamesTemp[i]!=null){
					fileNames[j]=fileNamesTemp[i];
					j++;
				}
			}
			}
		}
		
		PrintWriter pw = response.getWriter();
		String jsonSting = "";
		if ("uploadingSchoolPhotoCopy".equals(action_flag)){
			jsonSting = JsonTools.rendJson(new SecondaryDevelopmen().uploadingSchoolPhotoCopy(schoolId,pusherName,fileNames));
		}
		pw.write(jsonSting);
		pw.flush();
		pw.close();
	}

}
