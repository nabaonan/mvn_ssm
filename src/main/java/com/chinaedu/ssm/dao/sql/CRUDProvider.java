package com.chinaedu.ssm.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.chinaedu.ssm.model.PageInfo;
import com.chinaedu.ssm.model.Persist;

public class CRUDProvider {

	
	public String deleteById(Object o) {
		Persist persist = new Persist(o);
		String sql = new SQL() {
			{
				DELETE_FROM(persist.getTableName());
				WHERE(persist.getPkConditionCF());
			}
		}.toString();
		System.out.println("delete:"+sql);
		return sql;
	}

	public String save(Object o) {
		Persist persist = new Persist(o);
		String sql = new SQL() {
			{
				INSERT_INTO(persist.getTableName());
				INTO_COLUMNS(persist.getColumnNames());
				INTO_VALUES(persist.getValueFieldNames());
			}
		}.toString();
		System.out.println("save:"+sql);
		return sql;
	}
	
	public String update(Object o) {
		Persist persist = new Persist(o);
		String sql = new SQL() {
			{
				UPDATE(persist.getTableName());
				SET(persist.getColumnEqualField());
				WHERE(persist.getPkConditionCF());
			}
		}.toString();
		System.out.println("update:"+sql);
		return sql;
	}

	public String findById(Object o) {
		Persist persist = new Persist(o);
		 String sql = new SQL() {
			{
				SELECT(persist.getColumnNames());
				FROM(persist.getTableName());
				WHERE(persist.getPkConditionCF());
			}
		}.toString();
		System.out.println("findById:"+sql);
		return sql;

	}
	
	
	/**
	 * 带分页的查询所有
	 * @param o
	 * @param page
	 * @return
	 */
	public String findAll(Object o,PageInfo page) {
		System.out.println("调用这里");
		Persist persist = new Persist(o);
		String sql = new SQL() {
			{
				SELECT(persist.getColumnNames());
				FROM(persist.getTableName());
				WHERE(persist.getConditionCF());
			}
		}.toString();
		System.out.println("findAll:"+sql);
		return sql;
	}
	
}
