//package com.chinaedu.modules.repository.mybatis.basedao;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.concurrent.ConcurrentHashMap;
//
//import com.chinaedu.modules.repository.mybatis.annotation.Column;
//import com.chinaedu.modules.repository.mybatis.annotation.Id;
//import com.chinaedu.modules.repository.mybatis.annotation.Table;
//import com.chinaedu.modules.utils.StringUtils;
//
//public class MybatisSQLProvideHandlerHolder {
//	/** 缓存实体类信息 */
//	private static ConcurrentHashMap<Class<?>, EntityInfo> entityMap = new ConcurrentHashMap<Class<?>, EntityInfo>();
//	
//	/**
//	 * 实体类字段信息
//	 */
//	public static class Entity {
//		private Id id;
//		private Column column;
//		private Field field;
//		private Method method;
//		
//		public boolean isId() {
//			return id != null;
//		}
//		
//		public String getColumnName() {
//			return column.value().isEmpty() ? getFieldName() : column.value();
//		}
//		
//		public Column getColumn() {
//			return column;
//		}
//		
//		public String getFieldName() {
//			return field.getName();
//		}
//		
//		public Field getField() {
//			return field;
//		}
//		
//		public Method getMethod() {
//			return method;
//		}
//	}
//	
//	/**
//	 * 实体类信息
//	 */
//	public static class EntityInfo {
//		private String tableName;
//		private Entity idEntity;
//		private ArrayList<Entity> entities = new ArrayList<Entity>();
//		
//		public String getTableName() {
//			return tableName;
//		}
//		
//		public ArrayList<Entity> getEntities() {
//			return entities;
//		}
//		
//		public Entity getIdEntity() {
//			return idEntity;
//		}
//	}
//	
//	/**
//	 * 获取实体类信息
//	 * 
//	 * @param o
//	 * @return
//	 */
//	public static EntityInfo getEntityInfo(Object o) {
//		if (o == null) {
//			return null;
//		}
//		Class<?> cls = o.getClass();
//		EntityInfo entityInfo = entityMap.get(cls);
//		if (entityInfo == null) {
//			entityInfo = addEntityMap(cls);
//		}
//		return entityInfo;
//	}
//	
//	/**
//	 * 获取此类的表信息
//	 * 
//	 * @param cls
//	 * @return
//	 */
//	public static EntityInfo getEntityInfo(Class<?> cls) {
//		EntityInfo entityInfo = entityMap.get(cls);
//		if (entityInfo == null) {
//			entityInfo = addEntityMap(cls);
//		}
//		return entityInfo;
//	}
//	
//	/**
//	 * 根据class解析获取实体类相关信息，并加入到缓存中去
//	 * 
//	 * @param cls
//	 * @return
//	 */
//	private static EntityInfo addEntityMap(Class<?> cls) {
//		EntityInfo entityInfo = new EntityInfo();
//		
//		String tableName = cls.getAnnotation(Table.class).value();
//		if (tableName.isEmpty()) {
//			tableName = cls.getSimpleName();
//		}
//		entityInfo.tableName = tableName;
//		
//		Entity idEntity = null;
//		Field[] fields = cls.getDeclaredFields();
//		for (Field f : fields) {
//			Entity entity = new Entity();
//			Id id = f.getAnnotation(Id.class);
//			if (id != null) {
//				entity.id = id;
//			}
//			Column column = f.getAnnotation(Column.class);
//			if (column != null) {
//				entity.column = column;
//			}
//			
//			try {
//				Method m = cls.getMethod("get" + StringUtils.capitalize(f.getName()));
//				if (m != null) {
//					entity.method = m;
//					id = m.getAnnotation(Id.class);
//					if (id != null) {
//						entity.id = id;
//					}
//					column = m.getAnnotation(Column.class);
//					if (column != null) {
//						entity.column = column;
//					}
//				}
//			} catch (NoSuchMethodException e) {
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			}
//			
//			if (entity.id != null) {
//				idEntity = new Entity();
//				idEntity.id = entity.id;
//				idEntity.column = entity.column;
//				idEntity.field = f;
//				idEntity.method = entity.method;
//				entityInfo.idEntity = idEntity;
//			}
//			
//			if (entity.id != null || entity.column != null) {
//				entity.field = f;
//				entity.field.setAccessible(true);
//				entityInfo.entities.add(entity);
//			}
//			
//		}
//		if (idEntity == null) {
//			throw new IllegalArgumentException("Dao使用内部提供方法必须配置@Id");
//		}
//		entityMap.put(cls, entityInfo);
//		return entityInfo;
//	}
//	
//}
