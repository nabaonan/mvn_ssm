//package com.chinaedu.ssm.dao.sql;
//
//
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Objects;
//
//import org.apache.ibatis.jdbc.SQL;
//
//import com.chinaedu.ssm.dao.sql.MybatisSQLProvideHandlerHolder.EntityInfo;
//
//
//public class MybatisSQLProvideHandler {
//	/**
//	 * 插入有值的记录
//	 * 
//	 * @param o
//	 * @return
//	 */
//	public String insert(final Object o) {
//		final EntityInfo entityInfo = MybatisSQLProvideHandlerHolder.getEntityInfo(o);
//		return new SQL() {
//			{
//				INSERT_INTO(entityInfo.getTableName());
//				for (Entity entity : entityInfo.getEntities()) {
//					if (entity.getColumn().insertable() && !isNullVal(o, entity.getField(), entity.getMethod())) {
//						VALUES(entity.getColumnName(), "#{" + entity.getFieldName() + "}");
//					}
//				}
//			}
//		}.toString();
//	}
//	
//	/**
//	 * 根据有值的数据删除记录
//	 * 
//	 * @param o
//	 * @return
//	 */
//	public String delete(final Object o) {
//		final EntityInfo entityInfo = MybatisSQLProvideHandlerHolder.getEntityInfo(o);
//		return new SQL() {
//			{
//				DELETE_FROM(entityInfo.getTableName());
//				for (Entity entity : entityInfo.getEntities()) {
//					if (!isNullVal(o, entity.getField(), entity.getMethod())) {
//						WHERE(toKVPair(entity));
//					}
//				}
//			}
//		}.toString();
//	}
//	
//	/**
//	 * 根据ID更新其它有值的记录
//	 * 
//	 * @param o
//	 * @return
//	 */
//	public String updateById(final Object o) {
//		final EntityInfo entityInfo = MybatisSQLProvideHandlerHolder.getEntityInfo(o);
//		Entity idEntity = entityInfo.getIdEntity();
//		if (idEntity == null || isNullVal(o, idEntity.getField(), idEntity.getMethod())) {
//			throw new IllegalArgumentException(o.getClass().getName() + " " + idEntity.getFieldName() + " must be not null");
//		}
//		return new SQL() {
//			{
//				UPDATE(entityInfo.getTableName());
//				for (Entity entity : entityInfo.getEntities()) {
//					if (!isNullVal(o, entity.getField(), entity.getMethod())) {
//						SET(toKVPair(entity));
//					}
//				}
//				WHERE(toKVPair(entityInfo.getIdEntity()));
//			}
//		}.toString();
//	}
//	
//	/**
//	 * 根据ID更新其它所有记录（更新NULL值）
//	 * 
//	 * @param o
//	 * @return
//	 */
//	public String updateByIdWithNull(final Object o) {
//		final EntityInfo entityInfo = MybatisSQLProvideHandlerHolder.getEntityInfo(o);
//		Entity idEntity = entityInfo.getIdEntity();
//		if (isNullVal(o, idEntity.getField(), idEntity.getMethod())) {
//			throw new IllegalArgumentException(o.getClass().getName() + " " + idEntity.getFieldName() + " must be not null");
//		}
//		return new SQL() {
//			{
//				UPDATE(entityInfo.getTableName());
//				for (Entity entity : entityInfo.getEntities()) {
//					SET(toKVPair(entity));
//				}
//				WHERE(toKVPair(entityInfo.getIdEntity()));
//			}
//		}.toString();
//	}
//	
//	/**
//	 * 根据已有的值查询个数（查询条件使用AND）
//	 * 
//	 * @param o
//	 * @return
//	 */
//	public String count(final Object o) {
//		final EntityInfo entityInfo = MybatisSQLProvideHandlerHolder.getEntityInfo(o);
//		return new SQL() {
//			{
//				SELECT("count(" + entityInfo.getIdEntity().getColumnName() + ")").FROM(entityInfo.getTableName());
//				for (Entity entity : entityInfo.getEntities()) {
//					if (!isNullVal(o, entity.getField(), entity.getMethod())) {
//						WHERE(toKVPair(entity));
//					}
//				}
//			}
//		}.toString();
//	}
//	
//	/**
//	 * 根据已有的值查询个数（查询条件使用OR）
//	 * 
//	 * @param o
//	 * @return
//	 */
//	public String countOr(final Object o) {
//		final EntityInfo entityInfo = MybatisSQLProvideHandlerHolder.getEntityInfo(o);
//		return new SQL() {
//			{
//				SELECT("count(" + entityInfo.getIdEntity().getColumnName() + ")").FROM(entityInfo.getTableName());
//				ArrayList<String> list = new ArrayList<String>();
//				for (Entity entity : entityInfo.getEntities()) {
//					if (!isNullVal(o, entity.getField(), entity.getMethod())) {
//						list.add(toKVPair(entity));
//					}
//				}
//				WHERE(StringUtils.join(list.iterator(), " OR "));
//			}
//		}.toString();
//	}
//	
//	/**
//	 * 判断字段和对应的get方法是否有值
//	 * 
//	 * @param o
//	 * @param field
//	 * @param method
//	 * @return
//	 */
//	public static boolean isNullVal(Object o, Field field, Method getMethod) {
//		try {
//			String value = Objects.toString(field.get(o), "");
//			String getValue = Objects.toString(getMethod.invoke(o), "");
//			return StringUtils.isBlank(value) && StringUtils.isBlank(getValue);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
//	
//	/**
//	 * 生成键值sql
//	 * 
//	 * @param entity
//	 * @return
//	 */
//	public static String toKVPair(Entity entity) {
//		return entity.getColumnName() + " = #{" + entity.getFieldName() + "}";
//	}
//	
//	/**
//	 * 生成键值sql
//	 * 
//	 * @param entityPrefix
//	 * @param entity
//	 * @return
//	 */
//	public static String toKVPair(String entityPrefix, Entity entity) {
//		return String.format("%s = #{%s.%s}", entity.getColumnName(), entityPrefix, entity.getFieldName());
//	}
//	
//	/**
//	 * 生成键值sql
//	 * 
//	 * @param table
//	 * @param entityPrefix
//	 * @param entity
//	 * @return
//	 */
//	public static String toKVPair(String table, String entityPrefix, Entity entity) {
//		return String.format("%s.%s = #{%s.%s}", table, entity.getColumnName(), entityPrefix, entity.getFieldName());
//	}
//}
