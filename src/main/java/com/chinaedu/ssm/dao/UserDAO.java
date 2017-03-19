package com.chinaedu.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chinaedu.ssm.model.Condition;
import com.chinaedu.ssm.model.User;

public interface UserDAO extends BaseDAO<User>{
	
	public List<User> findUsersByCondition(@Param("condition")Condition condition);
	
}
