/**   
 * @Title: OptLogAop.java 
 * @Package com.digisky.log 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午10:03:48 
 * @version V1.0   
 */
package com.digisky.log;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.digisky.po.OperateLog;

/** 
 * @ClassName: OptLogAop 
 * @Description: 操作日志AOP
 * @author dengbin
 * @date 2014年12月3日 上午10:03:48  
 */
@Component
public class OptLogAop {
	
	@Autowired
	private IOptLogService logService;
	
	public static final ThreadLocal<OperateLog> optLog = new ThreadLocal<OperateLog>();
	/**
	 * 
	 * @Title: exceptionLog 
	 * @Description: aop异常拦截处理
	 * @author dengbin
	 * @date 2014年12月4日 上午10:12:14 
	 * @param point
	 * @param ex
	 */
	public void exceptionLog(JoinPoint point,Throwable ex){
		OperateLog log = optLog.get();
		if(log!=null){
			log.setSuccess(false);
			log.setResultMssg(ex.toString());
			logService.save(log);
			optLog.set(null);
		}
		

	}
	/**
	 * 
	 * @Title: optLogBefore 
	 * @Description: aop befor拦截处理
	 * @author dengbin
	 * @date 2014年12月4日 上午10:12:17 
	 * @param point
	 */
	public void optLogBefore(JoinPoint point){
		Class clazz = point.getTarget().getClass();
		String name = point.getSignature().getName();
		Object[] args = point.getArgs();
		try {
			Method m = getMethod(clazz,name,args);
			com.digisky.log.OperateLog annotation = m.getAnnotation(com.digisky.log.OperateLog.class);
			if(annotation!=null){
				OperateLog log = new OperateLog();
				Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if("anonymousUser".equals(auth)){
					log.setUserName("system");
				}else{
					UserDetails userDetails = (UserDetails)auth;
			        String username = userDetails.getUsername();
			        log.setUserName(username);
				}
		        log.setOptTime(new Date());
		        log.setSuccess(true);
		        log.setArgs(Arrays.toString(args));
				log.setMenuName(annotation.menuName().getContext());
				log.setFunName(annotation.funName().getContext());
				optLog.set(log);
			}
			System.out.println(m.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @Title: optLogAfter 
	 * @Description: aop after拦截处理
	 * @author dengbin
	 * @date 2014年12月4日 上午10:12:20 
	 * @param point
	 */
	public void optLogAfter(JoinPoint point){
		OperateLog log = optLog.get();
		if(log!=null){
			logService.save(log);
			optLog.set(null);
		}
		
	}
	/**
	 * 
	 * @Title: getMethod 
	 * @Description: 根据类和方法名称查询方法，如果使用参数类型查询，如果参数为null，查询失败
	 * 如果类中重载了方法，方法查询返回可能不正确
	 * @author dengbin
	 * @date 2014年12月4日 上午10:12:24 
	 * @param clazz
	 * @param name
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public Method getMethod(Class clazz,String name,Object[] args) throws Exception{
		Method[] methods = clazz.getMethods();
		for(Method m : methods){
			if(name.equals(m.getName())){
				return m;
			}
		}
		return null;
	}
}
