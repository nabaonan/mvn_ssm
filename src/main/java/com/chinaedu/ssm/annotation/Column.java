package com.chinaedu.ssm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明该字段作为查询结果字段，并且可以指定对应的表的列名
 * @author nbn
 * @date 2017年2月23日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	public String value() default "fieldName";
}
