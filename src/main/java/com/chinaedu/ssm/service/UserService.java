package com.chinaedu.ssm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinaedu.ssm.dao.UserDAO;
import com.chinaedu.ssm.model.Condition;
import com.chinaedu.ssm.model.User;
@Service("userService")
public class UserService extends BaseService<User> {

	/**
	 * 这里注入特殊的dao
	 * crud使用basedao实现
	 */
	@Resource
	private UserDAO userDAO;
	
	//这里只写特殊的方法，就是除了简单的增删改查，剩下的复杂方法都写在这里
	public List<User> findAllByCondition(Condition c) {
		return userDAO.findUsersByCondition(c);
	}

}	
