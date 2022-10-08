package com.example.order.model;

public class Customer {
	
	private int id;
	private String name;
	private String email;
	private long phone;
	private double credits;
	
	public Customer(int id, String name, String email, long phone, long credits) {
		this.id= id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.credits = credits;
	}

	public Customer() {
		super();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}	

}
