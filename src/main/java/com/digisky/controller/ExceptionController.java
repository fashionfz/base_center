package com.digisky.controller;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.digisky.common.Constant;
import com.digisky.dto.ResultDTO;
import com.digisky.exception.DirException;


/**
 * 
 * @ClassName: ExceptionController 
 * @Description: 异常处理controller
 * @author dengbin
 * @date 2014年11月25日 下午4:14:08
 */
@Controller
public class ExceptionController {

	/**
	 * 
	 * @Title: exception 
	 * @Description: 异常处理
	 * @author dengbin
	 * @date 2014年11月25日 下午4:14:46 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/exception",method = {RequestMethod.GET}) 
	@ResponseBody 
	public ResultDTO exception(HttpServletRequest request){
		String flag = request.getParameter("flag");
        ResourceBundle bundle = ResourceBundle.getBundle("exception");
        Exception ex = (Exception) request.getAttribute("exception");
        
        ex.printStackTrace();
        
        ResultDTO result = new ResultDTO();
        result.setCode(Constant.REQUEST_FAIL);
        if("MyException".equals(flag)){
        	result.setData(bundle.getString(((DirException) ex).getErrorKey()));
        }else if("AccessDeniedException".equals(flag)){
        	result.setCode(Constant.SECURITY_FAIL);
        	result.setData(ex.toString());
        	
        }else{
        	result.setData(ex.toString());
        }
        return result;
	}
}
