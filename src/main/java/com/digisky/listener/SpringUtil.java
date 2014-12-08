package com.digisky.listener;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 
 * @ClassName: SpringUtil 
 * @Description: spring处理工具类
 * @author dengbin
 * @date 2014年12月4日 上午10:20:09
 */
public class SpringUtil {

	private static ApplicationContext context;

	public static ApplicationContext getContext()
	{
		ServletContext servletContext = MyListener.getContext();
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		return context;
	}

	public static void setContext(ApplicationContext context)
	{
		SpringUtil.context = context;
	}
	/**
	 * 
	 * @Title: getBean 
	 * @Description: 根据bean名称获取对象
	 * @author dengbin
	 * @date 2014年12月4日 上午10:20:23 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName){
		return SpringUtil.getContext().getBean(beanName);		
	}
}
