package com.sunbeaminfo.internship.library.entities;

public abstract class User {
	public static final String MEMBER = "member";
	public static final String LIBRARIAN = "librarian";
	public static final String OWNER = "owner";
	
	private int id;
	private String name;
	private String email;
	private String phone;
	private String passwd;
	private String role;
	
	public User() {
		this(0, "", "", "", "", MEMBER);
	}
	public User(String role) {
		this(0, "", "", "", "", role);
	}
	public User(int id, String name, String email, String phone, String passwd) {
		this(id, name, email, phone, passwd, MEMBER);
	}
	public User(int id, String name, String email, String phone, String passwd, String role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.passwd = passwd;
		this.role = role;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + " [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", passwd=" + passwd + "]";
	}
	
	public static User newInstance(String role) {
		if(role.equals(MEMBER))
			return new Member();
		else if(role.equals(LIBRARIAN))
			return new Librarian();
		else
			return new Owner();
	}
}
