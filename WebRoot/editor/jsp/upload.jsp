<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page  
    import="java.util.*,java.io.*,  
    org.apache.commons.fileupload.FileItem,  
    org.apache.commons.fileupload.FileUploadException,  
    org.apache.commons.fileupload.disk.DiskFileItemFactory,  
    org.apache.commons.fileupload.servlet.ServletFileUpload,  
    java.util.concurrent.locks.*"%>  
<%  
    String id = "";  
    String url = "";  
    String imgTitle = "";  
    String imgWidth = "";  
    String imgHeight = "";  
    String imgBorder = "";  
    String filePath = "";  
    String align = "";  
    // **************************************  
    // 初始化上传工厂对象  
    DiskFileItemFactory factory = new DiskFileItemFactory();  
    // 设置上传工厂对象限制  
    factory.setSizeThreshold(1024 * 1024 * 20);  
    factory.setRepository(new File(request.getSession(true)  
            .getServletContext().getRealPath("/")));  
    // 创建上传对象  
    ServletFileUpload upload = new ServletFileUpload(factory);  
    upload.setFileSizeMax(1024 * 1024 * 20);  
    List<FileItem> items = null;  
    try {  
        items = upload.parseRequest(request);  
    } catch (FileUploadException e) {  
        e.printStackTrace(System.out);  
    }  
    for (Iterator<FileItem> i = items.iterator(); i.hasNext();) {  
        FileItem item = i.next();  
        if (item.isFormField()) {  
            String name = item.getFieldName();  
            String value = item.getString("gbk");  
            if (name.equals("id")) {  
                id = value;  
            } else if (name.equals("imgTitle")) {  
                imgTitle = value;  
            } else if (name.equals("imgWidth")) {  
                imgWidth = value;  
            } else if (name.equals("imgHeight")) {  
                imgHeight = value;  
            } else if (name.equals("imgBorder")) {  
                imgBorder = value;  
            } else if (name.equals("align")) {  
                align = value;  
            } else if (name.equals("url")) {  
                filePath = value;  
            }  
        } else {  
            // 取得表单域名  
            String fieldName = item.getFieldName();  
            // 取得文件名  
            String fileName = item.getName();  
            // 取得文件类型  
            String contentType = item.getContentType();  
              
            final Lock lock = new ReentrantLock();  
            String newName = null;  
            lock.lock();  
            try {  
                //加锁为防止同一时间文件名冲突   
                newName = System.currentTimeMillis()  
                        + fileName.substring(fileName.lastIndexOf("."),  
                                fileName.length());  
            } catch (Exception e) {  
                e.printStackTrace(System.err);  
            } finally {  
                lock.unlock();  
            }  
            filePath = "./ke_upload/" + newName;  
            FileOutputStream fos = new FileOutputStream(request  
                    .getSession().getServletContext().getRealPath("/")  
                    + "ke_upload\\" + newName);  
            if (item.isInMemory()) {  
                fos.write(item.get());  
                fos.close();  
            } else {  
                InputStream in = item.getInputStream();  
                byte[] buffer = new byte[1024];  
                int len;  
                while ((len = in.read(buffer)) > 0) {  
                    fos.write(buffer, 0, len);  
                }  
                in.close();  
                fos.close();  
            }  
        }  
    }  
    out.println("<html><head><title>Insert Image</title><meta http-equiv='content-type' content='text/html; charset=gbk'/></head><body>");  
    out.println("<script type='text/javascript'>");  
    out.println("parent.parent.KE.plugin['image'].insert('" + id  
            + "','" + filePath + "','" + imgTitle + "','" + imgWidth  
            + "','" + imgHeight + "','" + imgBorder + "','" + align  
            + "');</script>");  
    out.println("</body></html>");  
%>  