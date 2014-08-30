package com.qiYang.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class TWPictureUtil {
	private static Logger logger=Logger.getLogger(TWPictureUtil.class);
	public static String getNomalPicPath(String picPath) {
		String picUrl =StringUtils.isNotEmpty(picPath)?new StringBuffer(TWDataUtil.getBaseUrl()).append("images/nomal/").append(picPath).toString():"";
		return picUrl;
	}

	public static String getMinPicPath(String picPath) {
		String picUrl= StringUtils.isNotEmpty(picPath)?new StringBuffer(TWDataUtil.getBaseUrl()).append("images/min/").append(picPath).toString():"";
		return picUrl;
	}

	public static ArrayList<String> getIOSMin(ArrayList<String> strList) {
		ArrayList<String> strListIOS = new ArrayList<String>();
		for (String string : strList) {
			strListIOS.add(getMinPicPath(string));
		}
		return strListIOS;
	}

	public static ArrayList<String> getIOSNormal(ArrayList<String> strList) {
		ArrayList<String> strListIOS = new ArrayList<String>();
		for (String string : strList) {
			strListIOS.add(getNomalPicPath(string));
		}
		return strListIOS;
	}

	public static String pictureAcceptIstrue(String fileName, String picUrl,
			InputStream input, String date1) {
		String strFileNonmal = picUrl + "/nomal";
		String strFileMin = picUrl + "/min";
		String fileNonmalDao = date1 + ".jpg";
		File file = new File(strFileNonmal);
		File fileMin = new File(strFileMin);
		if (!file.exists())
			file.mkdir();
		if (!fileMin.exists())
			fileMin.mkdir();
		strFileNonmal += "/" + date1 + ".jpg";
		strFileMin += "/" + date1 + ".jpg";
		FileOutputStream fos;
		byte[] buffer = new byte[1024];
		int len = 0;
		File fileNonmal = new File(strFileNonmal);
		try {
			fos = new FileOutputStream(fileNonmal, true);
			while ((len = input.read(buffer, 0, 1024)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean boole = PhotoResize.zoomOutImage(strFileNonmal, strFileMin,
				200, 200);
		if (boole && fileNonmal.exists())
			return fileNonmalDao;
		else
			return "fail";
	}

	public static String getUploadedFile(String picUrl, List<FileItem> items,
			String date1) {
		String strFileNonmal = picUrl + "/nomal";
		String strFileMin = picUrl + "/min";
		String fileNonmalDao = date1 + ".jpg";
		File file = new File(strFileNonmal);
		File fileMin = new File(strFileMin);
		if (!file.exists())
			file.mkdir();
		if (!fileMin.exists())
			fileMin.mkdir();
		strFileNonmal += "/" + date1 + ".jpg";
		strFileMin += "/" + date1 + ".jpg";
//		System.out.println("strFileNonmal:" + strFileNonmal);
//		System.out.println("strFileMin" + strFileMin);
		File uploadFile = null;
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.isFormField()) {
			} else {
				long fileSize = item.getSize();
				if (/* "".equals(filename)&& */fileSize == 0) {
					// System.out.println("文件为空 ...");
					continue;
				}
				uploadFile = new File(strFileNonmal);
				try {
					item.write(uploadFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		boolean boole = PhotoResize.zoomOutImage(strFileNonmal, strFileMin,
				200, 200);
		if (uploadFile == null)
			return "fail";
		if (boole && uploadFile.exists())
			return fileNonmalDao;
		else
			return "fail";
	}
	/**池长购
	 * 20140120
	 * 新增通用多文件上传方法
	 * @param path
	 * @param item
	 * @param fileName
	 * @return
	 */
	public static String getUploadedFile01(String path,
			FileItem item,String fileName) {
		Long size = item.getSize();//文件长度  
        if(size==null || size==0){  
//           未选择上传文件  
            return null;  
        }  
        String strFileNonmal =null;
        String strFileMin =null;
        if(fileName.indexOf(".mp3")<0){
		strFileNonmal = path + "/nomal" ;
		strFileMin = path + "/min" ;
		File file = new File(strFileNonmal);
		File fileMin = new File(strFileMin);
		if (!file.exists())
			file.mkdir();
		if (!fileMin.exists())
			fileMin.mkdir();
		strFileNonmal += "/" + fileName;
		strFileMin += "/" + fileName;
        }else{
        	strFileNonmal =path+ "/" + fileName;
        	strFileMin =path+ "/" + fileName;
        }
//        System.out.println(strFileNonmal);
		File uploadFile = new File(strFileNonmal);
				try {
					item.write(uploadFile);
//					System.out.println(strFileNonmal);
				} catch (Exception e) {
					logger.error("getUploadedFile----------------------------->异常！"+e);
					e.printStackTrace();
				}
		if(fileName.indexOf(".mp3")<0){
		boolean boole = new CompressPicDemo().compressPic(strFileNonmal, strFileMin, 400, 400,20, true);
		if (boole && uploadFile.exists())
			return fileName;
		}
		return fileName;
	}
}
