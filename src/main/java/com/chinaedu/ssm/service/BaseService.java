package com.chinaedu.ssm.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinaedu.ssm.dao.BaseDAO;
import com.chinaedu.ssm.model.PageInfo;

/**
 * 这个是service，个人感觉应该与dao层解除耦合，虽然看似都是增删改查，但是service应该会掺杂一些公共逻辑，所以没有和dao实现相同接口
 * @author nbn
 * @date 2017年2月20日
 */

public abstract class BaseService<M extends Serializable> {

	/**
	 * 这是spring的新写法（泛型限定式依赖注入）这里注入dao只能使用autowired，如果使用resource查不出完整的对象，只能查出一个字段的值
	 */
	@Autowired
	protected BaseDAO<M> baseDAO;
		
	
	public List<M> findAll(M m,PageInfo p ) {
		//这里可能还需要做一些业务处理
		return baseDAO.findAll(m,p);
	}

	public M findById(M m) {
		//这里可能还需要做一些业务处理
		return baseDAO.findById(m);
	}
	
	public void deleteById(M m) {
		//这里可能还需要做一些业务处理
		baseDAO.deleteById(m);
	}
	
	public void update(M m) {
		//这里可能还需要做一些业务处理
		baseDAO.update(m);
	}
	
	public void save(M m) {
		//这里可能还需要做一些业务处理
		baseDAO.save(m);
	}
	

}
