package com.chinaedu.ssm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据表名称注解，默认值为类名称
 * 
 * @return
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	
	//这里定义的value()是默认赋值，在使用的时候可以直接在括号中传值
	public String value() default "className";

}
