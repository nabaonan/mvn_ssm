package com.chinaedu.ssm.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chinaedu.ssm.model.Condition;
import com.chinaedu.ssm.model.PageInfo;
import com.chinaedu.ssm.model.User;
import com.chinaedu.ssm.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	private PageInfo p;

	private User u;

	@RequestMapping("/gotoAdd")
	public ModelAndView gotoAdd() {
		return new ModelAndView("user/add");
	}

	@RequestMapping("/gotoJson")
	public ModelAndView gotoJson() {
		return new ModelAndView("user/json");
	}

	@RequestMapping("/getList")
	public ModelAndView getUserList(User u) throws IOException {
		
		List<User> userList = userService.findAll(u, p);
		return new ModelAndView("user/list","userList",userList);
	}

	@RequestMapping("/getListByCondition")
	public ModelAndView getUserListByCondition(Condition c)
			throws IOException {
		List<User> userList = userService.findAllByCondition(c);
		return new ModelAndView("user/list","userList",userList);
	}

	/**
	 * 返回json需要在spring-appContext中配置RequestMappingHandlerAdapter,必须添加注解@responseBody要不然不生效
	 * 
	 * @return 只要把查询对象返回，自动转换成json，需要引入两个包 jackson-annotations jackson-core
	 */

	@ResponseBody
	@RequestMapping("/getListJson")
	public List<User> list() {
		System.out.println("返回userList-json");
		List<User> userList = userService.findAll(u, p);
		return userList;
	}

	/**
	 * get查
	 * 
	 * @return
	 */

	@ResponseBody
	@GetMapping("/getListJsonForOne")
	public Map getUserListJsonForOne() {
		HashMap<String, List<User>> result = new HashMap<String, List<User>>();
		System.out.println("返回userList-json");
		List<User> userList = userService.findAll(u, p);
		result.put("result", userList);
		return result;
	}

	/**
	 * 如果想同一个form传到不同的对象中，需要下面这样绑定一个前缀，注解对应的是@modelAttribute中括号的名字
	 * setFieldDefaultPrefix括号中对应的是页面中input的name值，比如<input name='user.name'/>
	 * @param binder
	 */
	@InitBinder("myUser")
	public void bindUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("user.");
	}
	@InitBinder("myOtherUser")
	public void bindOtherUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("otherUser.");
	}
	/**
	 * post增加
	 * @requestBody 是用来接受json数据的,这个和form提交的可以公用
	 * 
	 * @return
	 */
	
	@RequestMapping("/add")
	public ModelAndView addUser(@RequestBody @ModelAttribute("myUser") User user,@RequestBody @ModelAttribute("myOtherUser") User o) {

		System.out.println("这是other" + o.toString());
		System.out.println("这是user对象" + user.toString());

		userService.save(user);
		return new ModelAndView("redirect:/user/getList");
	}
	
	
	@RequestMapping("/addAll")
	public ModelAndView addAllUser(@RequestBody List<User> users) {
	
		System.out.println(users.toString());
		return new ModelAndView("redirect:/user/getList");
	}
	

	/**
	 * put更新
	 * 
	 * 这个是传统风格，/update/{id}这个是rest风格
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView updateUser(@ModelAttribute User u) {
		System.out.println(u.toString());
		userService.update(u);
		return new ModelAndView("redirect:/user/getList");

	}

	/**
	 * delete删除
	 * 
	 * @return
	 */

	@RequestMapping(value = "/delete/{id}",method=RequestMethod.DELETE)
	public ModelAndView deleteUser(@ModelAttribute User u) {
		System.out.println("删除用户");
		userService.deleteById(u);
		return new ModelAndView("redirect:/user/getList");
	}

	/**
	 * 测试modelattribute modelAttribute如果定义在方法上边，则把方法的参数看作一个model
	 * 
	 * @param id
	 * @param u
	 */
	
	@GetMapping("/get/{id}")
	@ModelAttribute
	public ModelAndView getUser(User u) {
		// System.out.println("userId"+id+"user="+u.toString());

		User uu = userService.findById(u);
		return new ModelAndView("user/detail").addObject(uu);
	}

	/**
	 * 通过mapping配置的{id}必须与User对象中的属性名一致才可以
	 * 
	 * @param u
	 * @return
	 */
	@RequestMapping("/willEdit/{id}")
	public ModelAndView willEdit(@ModelAttribute User u) {
		User uu = userService.findById(u);
		return new ModelAndView("user/edit", "userForEdit", uu);
	}
	

	

}
