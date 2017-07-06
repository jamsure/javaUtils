package com.fh.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fh.entity.JsonResult;

public class NewFileUpload {
	public static final String UPLOAD_DIR = "uploadDir";
	public static final String UPLOAD_IMAGE_EXT = "uploadImageExt";
	public static final String UPLOAD_FLASH_EXT = "uploadFlashExt";
	public static final String UPLOAD_MEDIA_EXT = "uploadMediaExt";
	public static final String UPLOAD_FILE_EXT = "uploadFileExt";
	public static final String UPLOAD_DOC_EXT = "uploadDocExt";
	public static final String UPLAOD_MAX_SIZE = "uplaodMaxSize";
	public static final String BATCH_UPLOAD_LIMIT = "batchUploadLimit";

	/**
	 * Servlet请求
	 */
	private HttpServletRequest request;

	/**
	 * 实际路径
	 */
	private String savePath;

	/**
	 * 保存的相当路径
	 */
	private String saveUrl;

	/**
	 * 单个文件最大
	 */
	private long maxSize;

	/**
	 * 扩展名Map
	 */
	private Map<String, String> extMap;

	public NewFileUpload() {

	}

	public NewFileUpload(HttpServletRequest request) {
		this.request = request;
		String uploadDir = "/upload/";
		uploadDir = "/".equals(uploadDir.substring(0, 1))?uploadDir.substring(1):uploadDir;
		this.savePath = PathUtil.getRootRealPath(request) + uploadDir;
		this.saveUrl = uploadDir;

		maxSize = 20 * 1024 * 1024;

		// 定义允许上传的文件扩展名
		extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,PNG,JPG,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "gif,jpg,jpeg,png,PNG,JPG,bmp,swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
	}

	public JsonResult process(String userId) {
		try {
			if (!ServletFileUpload.isMultipartContent(request)) {
				return new JsonResult(false, "请选择上传文件");
			}
			// 检查目录
			File uploadDir = new File(savePath);
			if (!uploadDir.isDirectory()) {
				boolean flag = uploadDir.mkdir();
				if (!flag) {
					return new JsonResult(false, "创建文件夹时出错");
				}
				uploadDir.setWritable(true);
				uploadDir.setReadable(true);
			}

			// 检查目录写权限
			if (!uploadDir.canWrite()) {
				return new JsonResult(false, "上传目录没有写权限");
			}

			String fileName = "", fileExt = "",dirName = "",
					formName = "",tempSavePath = "",tempSaveUrl = "",newSaveUrl = "";
			
					
			Map<String,Object> urlMap = new HashMap<String,Object>();
			//进行转换  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);  
            //获取所有文件名称  
            Iterator<String> iterator = multiRequest.getFileNames();
			String path = "";
			while (iterator.hasNext()) {
				tempSavePath = savePath;
				tempSaveUrl = saveUrl;
				formName = iterator.next();
				//根据文件名称取文件  
                MultipartFile file = multiRequest.getFile(formName);  
				// 检查文件大小
				if (file.getSize() > maxSize) {
					return new JsonResult(false, "上传文件大小超过限制");
				}
                fileName = file.getOriginalFilename(); 
               
				fileExt = fileName.substring(fileName.lastIndexOf('.') + 1);
				
				dirName = getDirNameByExtName(fileExt,userId);
				// 创建文件夹
				tempSavePath += dirName + "/";
				tempSaveUrl += dirName + "/";
				File saveDirFile = new File(tempSavePath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String ymd = sdf.format(new Date());
				tempSavePath += ymd + "/";
				tempSaveUrl += ymd + "/";
				File dirFile = new File(tempSavePath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				
				sdf = new SimpleDateFormat("HHmmssSSS");
				String newFileName = RandomUtil.generateString(32) + sdf.format(new Date()) + "." + fileExt;
				File uploadedFile = new File(tempSavePath, newFileName);
				file.transferTo(uploadedFile); 
				newSaveUrl = tempSaveUrl;
				tempSaveUrl = tempSaveUrl + newFileName;
				path = uploadedFile.getPath();
				urlMap.put("formName", tempSaveUrl);  
				urlMap.put("videoPath", path);  
				
			}
			return new JsonResult(true, "上传文件成功",urlMap);
		} catch (Exception ex) {
			LogHelper.getLogger().error("上传文件时出错", ex);
			return new JsonResult(false, "上传文件过程中出错");
		}
	}
	
	
	
	
	public JsonResult processAll(String userId) {
		try {
			if (!ServletFileUpload.isMultipartContent(request)) {
				return new JsonResult(false, "请选择上传文件");
			}
			// 检查目录
			File uploadDir = new File(savePath);
			if (!uploadDir.isDirectory()) {
				boolean flag = uploadDir.mkdir();
				if (!flag) {
					return new JsonResult(false, "创建文件夹时出错");
				}
				uploadDir.setWritable(true);
				uploadDir.setReadable(true);
			}

			// 检查目录写权限
			if (!uploadDir.canWrite()) {
				return new JsonResult(false, "上传目录没有写权限");
			}

			
			
			Map<String,Object> urlMap = new HashMap<String,Object>();
			List<String> imgArr = new ArrayList<String>();
			//进行转换  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request); 
            Iterator<String> iterator = multiRequest.getFileNames();
            while (iterator.hasNext()) {      	
				String formName = iterator.next();
				//根据文件名称取文件  
				List<MultipartFile> mfiles = multiRequest.getFiles(formName);
				for (MultipartFile multipartFile : mfiles) {
					String fileName = "", fileExt = "",dirName = "",
							tempSavePath = "",tempSaveUrl = "",newSaveUrl = "";
					tempSavePath = savePath;
					tempSaveUrl = saveUrl;
					// 检查文件大小
					if (multipartFile.getSize() > maxSize) {
						return new JsonResult(false, "上传文件大小超过限制");
					}
	                fileName = multipartFile.getOriginalFilename(); 
	                System.out.println(fileName);
					fileExt = fileName.substring(fileName.lastIndexOf('.') + 1);
					
					dirName = getDirNameByExtName(fileExt,userId);
					// 创建文件夹
					tempSavePath += dirName + "/";
					tempSaveUrl += dirName + "/";
					File saveDirFile = new File(tempSavePath);
					if (!saveDirFile.exists()) {
						saveDirFile.mkdirs();
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String ymd = sdf.format(new Date());
					tempSavePath += ymd + "/";
					tempSaveUrl += ymd + "/";
					File dirFile = new File(tempSavePath);
					if (!dirFile.exists()) {
						dirFile.mkdirs();
					}

					sdf = new SimpleDateFormat("HHmmssSSS");
					String newFileName = RandomUtil.generateString(32) + sdf.format(new Date()) + "." + fileExt;
					File uploadedFile = new File(tempSavePath, newFileName);
					multipartFile.transferTo(uploadedFile); 
					newSaveUrl = tempSaveUrl;
					String tempformName = tempSaveUrl + newFileName;
					imgArr.add(tempformName);  
				}
				urlMap.put("formName",StringUtils.join(imgArr, ","));
				}
			
			return new JsonResult(true, "上传文件成功",urlMap);
		} catch (Exception ex) {
			LogHelper.getLogger().error("上传文件时出错", ex);
			return new JsonResult(false, "上传文件过程中出错");
		}
	}
	
	public JsonResult upLoadImageByInputStream(InputStream is,String fileName,String userId) {
		FileOutputStream os = null;
		try {
			// 检查目录
			File uploadDir = new File(savePath);
			if (!uploadDir.isDirectory()) {
				boolean flag = uploadDir.mkdir();
				if (!flag) {
					return new JsonResult(false, "创建文件夹时出错");
				}
				uploadDir.setWritable(true);
				uploadDir.setReadable(true);
			}

			// 检查目录写权限
			if (!uploadDir.canWrite()) {
				return new JsonResult(false, "上传目录没有写权限");
			}

			String fileExt = "",dirName = "",tempSavePath = "",tempSaveUrl = "";
			
			Map<String,String> urlMap = new HashMap<String,String>();
			tempSavePath = savePath;
			tempSaveUrl = saveUrl;

			fileExt = fileName.substring(fileName.lastIndexOf('.') + 1);
			
			dirName = getDirNameByExtName(fileExt,userId);
			// 创建文件夹
			tempSavePath += dirName + "/";
			tempSaveUrl += dirName + "/";
			File saveDirFile = new File(tempSavePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			tempSavePath += ymd + "/";
			tempSaveUrl += ymd + "/";
			File dirFile = new File(tempSavePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			sdf = new SimpleDateFormat("HHmmssSSS");
			String newFileName = RandomUtil.generateString(32) + sdf.format(new Date()) + "." + fileExt;
			File uploadedFile = new File(tempSavePath, newFileName);
    		os=new FileOutputStream(uploadedFile); 
    		byte buffer[] = new byte[1024];
    		int len = 0;
    		while((len = is.read(buffer)) != -1)
    		{
    		os.write(buffer,0,len);
    		}
    		os.flush();
			tempSaveUrl = tempSaveUrl + newFileName;
			urlMap.put("newImageUrl", tempSaveUrl);
			return new JsonResult(true, "上传文件成功",urlMap);
		} catch (Exception ex) {
			LogHelper.getLogger().error("上传文件时出错", ex);
			return new JsonResult(false, "上传文件过程中出错");
		}finally {
			try {
			if(is != null){
				is.close();
			}
			if(os != null){
				os.close();
			}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getDirNameByExtName(String extName,String userId){
		if(StringUtil.isNullOrEmpty(extName)){
			return null;
		}
		if(Arrays.asList(((String)extMap.get("image")).split(",")).contains(extName)|| "PNG".equals(extName) || "JPG".equals(extName)){
			return "image";
		}
		if(Arrays.asList(((String)extMap.get("flash")).split(",")).contains(extName)){
			return "flash";
		}
		if(Arrays.asList(((String)extMap.get("media")).split(",")).contains(extName)){
			return "media";
		}
		if(Arrays.asList(((String)extMap.get("file")).split(",")).contains(extName)){
			return "file";
		}
		if(!StringUtil.isEmpty(userId)){
			return "media";
		}
		return null;
	}

	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public Map<String, String> getExtMap() {
		return extMap;
	}

	public void setExtMap(Map<String, String> extMap) {
		this.extMap = extMap;
	}
	
	
	
	
	
	
	
}
