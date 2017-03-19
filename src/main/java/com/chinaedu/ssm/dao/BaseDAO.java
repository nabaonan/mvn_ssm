package com.chinaedu.ssm.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.chinaedu.ssm.dao.sql.CRUDProvider;
import com.chinaedu.ssm.model.PageInfo;

/**
 * 这里只有crud操作
 * @author nbn
 *
 * @param <M>
 */
public interface BaseDAO<M extends Serializable> {
	
	@SelectProvider(type=CRUDProvider.class,method="save")
	public void save(M m);
	
	@SelectProvider(type=CRUDProvider.class,method="update")
	public void update(M m);
	
	@SelectProvider(type=CRUDProvider.class,method="findAll")
	public List<M> findAll(M t,PageInfo p);
	
	@SelectProvider(type=CRUDProvider.class,method="findById")
	public M findById(M m);
	
	@SelectProvider(type=CRUDProvider.class,method="deleteById")
	public void deleteById(M m);
	
}
