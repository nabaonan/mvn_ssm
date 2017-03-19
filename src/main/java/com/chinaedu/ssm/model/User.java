package com.chinaedu.ssm.model;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.web.servlet.View;

import com.chinaedu.ssm.annotation.Column;
import com.chinaedu.ssm.annotation.Condition;
import com.chinaedu.ssm.annotation.NotParam;
import com.chinaedu.ssm.annotation.PK;
import com.chinaedu.ssm.annotation.Table;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonView;

@Table
@JsonAutoDetect
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	@PK
	@Column
	@Condition
	private Long id;
	
	@Column
	@Condition
	private String name;
	
	@Column
	private Integer age;
	
	@Column("nice_name")
	private String niceName;

	@NotParam
	private String[] hobbies;
	
	@NotParam
	private String sex;
	
	@NotParam
	private User father;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNiceName() {
		return niceName;
	}

	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}

	

	public String[] getHobbies() {
		return hobbies;
	}

	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", niceName=" + niceName + ", hobbies="
				+ Arrays.toString(hobbies) + ", sex=" + sex + "]";
	}

	public User getFather() {
		return father;
	}

	public void setFather(User father) {
		this.father = father;
	}

}
