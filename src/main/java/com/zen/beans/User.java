package com.zen.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class User {
	private int id;
	private String password;
	private String lastName;
	private String firstName;
	private String adr1;
	private String adr2;
	private String pc;
	private String town;
	private String phone;
	private String mail;
	private ArrayList<AbstractRole> roles;
	private String token;
	private Timestamp timetamps;
	
	public User() {		
	}

	public User(String password, String mail) {
		super();
		this.password = password;
		this.mail = mail;
	}

	public User(String password, String lastName, String firstName, String mail) {
		super();
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.mail = mail;
	}

	public User(String password, String lastName, String firstName, String adr1, String adr2, String pc, String town,
			String phone, String mail, ArrayList<AbstractRole> roles) {
		super();
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.adr1 = adr1;
		this.adr2 = adr2;
		this.pc = pc;
		this.town = town;
		this.phone = phone;
		this.mail = mail;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAdr1() {
		return adr1;
	}

	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}

	public String getAdr2() {
		return adr2;
	}

	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public ArrayList<AbstractRole> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<AbstractRole> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getTimetamps() {
		return timetamps;
	}

	public void setTimetamps(Timestamp timestamp) {
		this.timetamps = timestamp;
	}
	
	
	

}
