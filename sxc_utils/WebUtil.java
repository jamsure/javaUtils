package com.xz.base.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.xz.base.model.JsonResult;
import com.xz.ym.core.service.file.FileUpload; 

public class WebUtil {

	public static String getString(HttpServletRequest request, String name, String defaultValue) {
		String value = getRequestValue(request, name);
		if (value == null) {
			value = defaultValue;
		}
		if (value != null) {
			value = value.trim();
		}
		return value;
	}

	public static boolean getBoolean(HttpServletRequest request, String name, boolean defaultValue) {
		String value = getRequestValue(request, name);
		boolean r = false;
		if (value != null) {
			if ("true".equals(value) || "yes".equals(value)) {
				r = true;
			}
		} else {
			r = defaultValue;
		}
		return (r);
	}
	
	public static int getInt(HttpServletRequest request, String name, int defaultValue) {

		String value = getRequestValue(request, name);
		int r = 0;
		if (value != null) {
			try {
				r = Integer.parseInt(value);

			} catch (Exception e) {
				r = defaultValue;
			}
		} else {
			r = defaultValue;
		}
		return r;
	}
	
	public static Integer getInteger(HttpServletRequest request, String name, Integer defaultValue) {

		String value = getRequestValue(request, name);
		Integer r = null;
		if (value != null) {
			try {
				r = Integer.parseInt(value);

			} catch (Exception e) {
				r = defaultValue;
			}
		} else {
			r = defaultValue;
		}
		return r;
	}

	public static long getLong(HttpServletRequest request, String name, long defaultValue) {
		String value = getRequestValue(request, name);
		long r = 0;
		if (value != null) {
			try {
				r = Long.parseLong(value);
			} catch (Exception e) {
				r = defaultValue;
			}
		} else {
			r = defaultValue;
		}
		return r;
	}

	public static double getDouble(HttpServletRequest request, String name, double defaultValue) {
		String value = getRequestValue(request, name);
		double r = 0.0;
		if (value != null) {
			try {
				r = Double.parseDouble(value);
			} catch (Exception e) {
				r = defaultValue;
			}
		} else {
			r = defaultValue;
		}
		return r;
	}

	public static float getFloat(HttpServletRequest request, String name, float defaultValue) {
		String value = getRequestValue(request, name);
		float r = 0;
		if (value != null) {
			try {
				r = Float.parseFloat(value);
			} catch (Exception e) {
				r = defaultValue;
			}
		} else {
			r = defaultValue;
		}
		return r;
	}

	private static String getRequestValue(HttpServletRequest request, String name) {
		String value = null;
		try {
			value = request.getParameter(name);
		} catch (Exception e) {
			value = null;
		}
		return (value);
	}

	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return basePath;
	}
	
	public static String getRootRealPath(HttpServletRequest request) {
		String rootRealPath = request.getServletContext().getRealPath("/");
		//rootRealPath = "\\".equals(rootRealPath.substring(rootRealPath.length()-1, rootRealPath.length()))?rootRealPath.substring(0,rootRealPath.length()-1):rootRealPath;
		//return rootRealPath.substring(0, rootRealPath.lastIndexOf("\\"))+"/";
		String root = new File(rootRealPath).getParentFile().getAbsolutePath() + "/";
		return root;
	}
	
	public static String getRootPath(HttpServletRequest request) {
		String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
		return rootPath;
	}

	/**
	 * 获取IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String getRemortIP(HttpServletRequest request) { 
	    if (request.getHeader("x-forwarded-for") == null) { 
	        return request.getRemoteAddr(); 
	    } 
	    return request.getHeader("x-forwarded-for"); 
	}  

	/**
	 * @Description 把对象转换成json字符串
	 * @param obj
	 * @return String    
	 * @author davidwan 
	 */
	public static String toJson(Object obj) { 
		String jsonText = null;
		try {
			jsonText = JSON.toJSONString(obj);
		} catch (Exception e) { 
			LogHelper.getLogger().error("转json出错", e);
		}
		return jsonText;
	}
	
	public static JsonResult getFileUpLoadRealPath(HttpServletRequest request,String extName){
		FileUpload ful = new FileUpload(request);
		String savePath = ful.getSavePath();
		String saveUrl = ful.getSaveUrl();
		// 检查目录
		File uploadDirPath = new File(savePath);
		if (!uploadDirPath.isDirectory()) {
			boolean flag = uploadDirPath.mkdir();
			if (!flag) {
				return new JsonResult(false, "创建文件夹时出错");
			}
			uploadDirPath.setWritable(true);
			uploadDirPath.setReadable(true);
		}

		// 检查目录写权限
		if (!uploadDirPath.canWrite()) {
			return new JsonResult(false, "上传目录没有写权限");
		}
		
		String dirName = ful.getDirNameByExtName(extName);
		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		sdf = new SimpleDateFormat("HHmmssSSS");
		String newFileName = RandomUtil.generateString(32) + sdf.format(new Date()) + "." + extName;
		Map<String,String> pathMap = new HashMap<String,String>();
		pathMap.put("saveFilePath", savePath);
		pathMap.put("saveFileUrl", saveUrl);
		pathMap.put("fileName", newFileName);
		return new JsonResult(true, "获取上传目录成功",pathMap);
	}
}