/**   
 * @Title: FileUpload.java 
 * @Package: com.xz.base.utils 
 * @Description: 文件上传
 * @author: davidwan
 * @date: 2014-7-22 下午4:41:01 
 * @version: V1.0   
 */
package com.fh.util.mail;

import java.awt.image.BufferedImage;
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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fh.entity.JsonResult;
import com.fh.util.ConfigValue;
import com.fh.util.LogHelper;
import com.fh.util.RandomUtil;
import com.fh.util.StringUtil;
import com.fh.util.WebUtil;

import net.coobird.thumbnailator.Thumbnails;



public class FileUpload {
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

	public FileUpload() {

	}

	public FileUpload(HttpServletRequest request) {
		this.request = request;
		String uploadDir = ConfigValue.readValue(UPLOAD_DIR, "upload/");
		uploadDir = "/".equals(uploadDir.substring(0, 1))?uploadDir.substring(1):uploadDir;
		this.savePath = WebUtil.getRootRealPath(request) + uploadDir;
		this.saveUrl = uploadDir;

		maxSize = ConfigValue.readLongValue(UPLAOD_MAX_SIZE, 20) * 1024 * 1024;

		// 定义允许上传的文件扩展名
		extMap = new HashMap<String, String>();
		extMap.put("image", ConfigValue.readValue(UPLOAD_IMAGE_EXT, "gif,jpg,jpeg,png,PNG,JPG,bmp"));
		extMap.put("flash", ConfigValue.readValue(UPLOAD_FLASH_EXT, "swf,flv"));
		extMap.put("media", ConfigValue.readValue(UPLOAD_MEDIA_EXT, "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb"));
		extMap.put("file", ConfigValue.readValue(UPLOAD_FILE_EXT, "gif,jpg,jpeg,png,PNG,JPG,bmp,swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2"));
	}

	public JsonResult process() {
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
				
				dirName = getDirNameByExtName(fileExt);
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
				//压缩图片
				if(Arrays.asList(((String)extMap.get("image")).split(",")).contains(fileExt) || "PNG".equals(fileExt) || "JPG".equals(fileExt)){
					String thumbnailatorName = thumbnailatorImage(tempSavePath, newFileName, newSaveUrl,fileExt, Integer.parseInt("600"), Integer.parseInt("600"));
					urlMap.put("formName", thumbnailatorName);
				}
				else
				{
					urlMap.put("formName", tempSaveUrl);
				}    
			}
			return new JsonResult(true, "上传文件成功",urlMap);
		} catch (Exception ex) {
			LogHelper.getLogger().error("上传文件时出错", ex);
			return new JsonResult(false, "上传文件过程中出错");
		}
	}
	
	public JsonResult processAll() {
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
					
					dirName = getDirNameByExtName(fileExt);
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
					//压缩图片
					if(Arrays.asList(((String)extMap.get("image")).split(",")).contains(fileExt)|| "PNG".equals(fileExt) || "JPG".equals(fileExt)){
						String thumbnailatorName = thumbnailatorImage(tempSavePath, newFileName, newSaveUrl,fileExt, Integer.parseInt("600"), Integer.parseInt("600"));
						imgArr.add(thumbnailatorName);
					}
					else
					{
						imgArr.add(tempformName);
					}  
					
				}
				urlMap.put("formName",StringUtils.join(imgArr, ","));
				}
			
			return new JsonResult(true, "上传文件成功",urlMap);
		} catch (Exception ex) {
			LogHelper.getLogger().error("上传文件时出错", ex);
			return new JsonResult(false, "上传文件过程中出错");
		}
	}
	
	public JsonResult upLoadImageByInputStream(InputStream is,String fileName) {
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
			
			dirName = getDirNameByExtName(fileExt);
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
	
	public String getDirNameByExtName(String extName){
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
		return null;
	}

	/**
     * 上传压缩压缩并保存图片
     * @param oldSavePath 原文件路径
     * @param oldFileName 原文件名称
     * @param fix 文件类型
     * @param x 需要压缩的宽度
     * @param y 需要压缩的长度
     * @return
     * @throws IOException
     */
    public String thumbnailatorImage(String oldSavePath,String oldFileName,String newSaveUrl,String fix,int x,int y) throws IOException
    {
         //Thumbnail读取并压缩图片
        BufferedImage waterMarkBufferedImage = Thumbnails.of(oldSavePath+oldFileName).scale(0.7f).outputQuality(0.5f)
                //Thumbnail的方法,压缩图片
                //.size(x, y)
                //读取成BufferedImage对象  
                .asBufferedImage();  
        //把内存中的图片写入到指定的文件中  
        String savePath = oldSavePath+"thumbnails/";
        File saveFile = new File(savePath);
        if (!saveFile.isDirectory())
            saveFile.mkdirs();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 4);
        factory.setRepository(saveFile);
        ServletFileUpload uploader = new ServletFileUpload(factory);
        uploader.setSizeMax(20 * 1024 * 1024);
        String saveFileName = savePath+oldFileName;
        File fileOutPut = new File(saveFileName);  
        ImageIO.write(waterMarkBufferedImage, fix, fileOutPut);
        return newSaveUrl + "thumbnails/" + oldFileName;
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
