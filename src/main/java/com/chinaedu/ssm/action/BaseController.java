package com.chinaedu.ssm.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 设计只是用来跳转页面以及一些公共的处理
 * @author nbn
 * @date 2017年2月20日
 */
@Controller
public class BaseController {
	

	@RequestMapping("/gotoUpload")
	public ModelAndView gotoUpload() {
		return new ModelAndView("user/json");
	}

}
