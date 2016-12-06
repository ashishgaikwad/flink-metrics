package com.citi.reghub.model;

import java.io.Serializable;

public class Student implements Serializable{
	
	private String name;
	private int phoneNumber;
	
	public Student() {
		
	}
	
	public Student(String nm, int number) {
		this.name = nm;
		this.phoneNumber = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	
	
}
