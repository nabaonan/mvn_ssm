package com.chinaedu.ssm.model;

import static org.hamcrest.CoreMatchers.notNullValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import com.chinaedu.ssm.annotation.Column;
import com.chinaedu.ssm.annotation.Condition;
import com.chinaedu.ssm.annotation.NotParam;
import com.chinaedu.ssm.annotation.PK;
import com.chinaedu.ssm.annotation.Table;

/**
 * 这个只用来解析对象
 * 
 * @author nbn
 *
 */
public class Persist {

	public Persist(Object o) {
		baseParse(o);
		genTableName();
		genColumns(o);
	}

	private Class<?> clazz;
	private Annotation[] annotations;
	private Field[] fields;

	private String tableName;

	private String[] columnNames;// COLUMN

	private String[] valueFieldNames;// 这个应该是组织成#{field}这种形式

	private String[] columnEqualField;// 组织成COLUMN=#{field}的形式

	private String[] conditionCF;// 组织成COLUMN=#{field}的形式,作为条件
	
	private String[] pkConditionCF;//这里只放主键条件形式和conditionCF一样

	public void baseParse(Object o) {
		clazz = o.getClass();
		annotations = clazz.getAnnotations();
		fields = clazz.getDeclaredFields();
	}

	private void genColumns(Object o) {

		ArrayList<String> columnList = new ArrayList<String>();
		ArrayList<String> valueFieldNameList = new ArrayList<String>();
		ArrayList<String> columnEqualFieldList = new ArrayList<String>();
		ArrayList<String> conditionList = new ArrayList<String>();
		ArrayList<String> pkList = new ArrayList<String>();

		for (Field f : this.fields) {
			String columnName = getColumnName(f);
			String fieldName = f.getName();
			String paramExp = "#{" + fieldName + "}";
			if (!"".equals(columnName)) {
				columnList.add(columnName);
				columnEqualFieldList.add(columnName + "=" + paramExp);
				
				// 防止静态方法也掺杂进来，比如serialVersionUID
				// 指定column就必须与传参个数对应，要不sql报错
				//fieldList只用于插入
				if (!Modifier.isStatic(f.getModifiers())&&!f.isAnnotationPresent(NotParam.class)) {
					valueFieldNameList.add(paramExp);
				}
			}
			
			if(f.isAnnotationPresent(PK.class)) {//如果该列是主键列则放在主键list中
				pkList.add(columnName + "=" + paramExp);
			}
			
			if (!"".equals(isCondition(f, o))) {//如果条件字段不是空则作为条件
				conditionList.add(columnName + "=" + paramExp);
			}
			

		}

		this.valueFieldNames = valueFieldNameList.toArray(new String[valueFieldNameList.size()]);
		this.columnEqualField = columnEqualFieldList.toArray(new String[columnEqualFieldList.size()]);
		this.columnNames = columnList.toArray(new String[columnList.size()]);
		this.conditionCF = conditionList.toArray(new String[conditionList.size()]);
		this.pkConditionCF=pkList.toArray(new String[pkList.size()]);
	}

	private String isCondition(Field f, Object o) {
		Condition condition = f.getAnnotation(Condition.class);

		Object value = null;
		try {
			f.setAccessible(true);
			value = f.get(o);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		// 如果有条件注解，则返回这个条件的表列的名字
		if (condition != null && value != null) {
			Column column = f.getAnnotation(Column.class);
			return column.value();
		} else {
			return "";
		}
	}

	private String getColumnName(Field f) {
		Column column = f.getAnnotation(Column.class);
		if(column==null){
			return "";
		}else{
			String cValue = column.value();
			if("fieldName".equals(cValue)){
				return f.getName();
			}else{
				return cValue;
			}
		}
	}

	private void genTableName() {
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = (Table) clazz.getAnnotation(Table.class);
			String tName=table.value();
			tableName = "className".equals(tName)?clazz.getSimpleName().toLowerCase():tName;
		}
	}

	public Annotation[] getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Annotation[] annotations) {
		this.annotations = annotations;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	

	public String[] getColumnEqualField() {
		return columnEqualField;
	}

	public void setColumnEqualField(String[] columnEqualField) {
		this.columnEqualField = columnEqualField;
	}

	public String[] getConditionCF() {
		return conditionCF;
	}

	public void setConditionCF(String[] conditionCF) {
		this.conditionCF = conditionCF;
	}

	public String[] getPkConditionCF() {
		return pkConditionCF;
	}

	public void setPkConditionCF(String[] pkConditionCF) {
		this.pkConditionCF = pkConditionCF;
	}

	public String[] getValueFieldNames() {
		return valueFieldNames;
	}

	public void setValueFieldNames(String[] valueFieldNames) {
		this.valueFieldNames = valueFieldNames;
	}

}
