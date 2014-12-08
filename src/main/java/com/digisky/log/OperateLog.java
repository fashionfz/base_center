/**   
 * @Title: OperateLog.java 
 * @Package com.digisky.log 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午11:17:11 
 * @version V1.0   
 */
package com.digisky.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.digisky.common.FunEnum;
import com.digisky.common.MenuEnum;

/** 
 * @ClassName: OperateLog 
 * @Description: 操作日志处理注解
 * @author dengbin
 * @date 2014年12月3日 上午11:17:11  
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface OperateLog {
	/**菜单对象名称*/
	MenuEnum menuName();
	/**操作功能名称*/
	FunEnum funName();

}
