package com.xz.base.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {

   private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml"); 
	
   @SuppressWarnings("unchecked")
   public static <T> T getBeanById(String beanId){
	   return (T)ac.getBean(beanId);
   }
   
   
   /**
    * 当前IOC
    */
   private static ApplicationContext applicationContext;

   /**
    * 设置当前上下文环境，此方法由spring自动装配
    */
   public void setApplicationContext(ApplicationContext arg0)
           throws BeansException {
       applicationContext = arg0;
   }

   /**
    * 从当前IOC获取bean
    * 
    * @param id
    *            bean的id
    * @return
    */
   public static Object getObject(String id) {
       Object object = null;
       object = applicationContext.getBean(id);
       return object;
   }
   
   /**
	 * 获取项目部署的绝对路径
	 * 
	 * @return
	 */
	public static String getRealPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}
}
