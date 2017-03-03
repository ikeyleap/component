package com.ikeyleap.ctrl;

/**
 * @author lobas_av
 * 
 */
public class Person {
	private String name;
	private String email;
	private String phone;
	private String mobilePhone1;

	public Person() {
		super();
	}

	private String mobilePhone2;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the mobilePhone1
	 */
	public String getMobilePhone1() {
		return mobilePhone1;
	}

	/**
	 * @param mobilePhone1
	 *            the mobilePhone1 to set
	 */
	public void setMobilePhone1(String mobilePhone1) {
		this.mobilePhone1 = mobilePhone1;
	}

	/**
	 * @return the mobilePhone2
	 */
	public String getMobilePhone2() {
		return mobilePhone2;
	}

	/**
	 * @param mobilePhone2
	 *            the mobilePhone2 to set
	 */
	public void setMobilePhone2(String mobilePhone2) {
		this.mobilePhone2 = mobilePhone2;
	}

	public Person(String name, String email, String phone, String mobilePhone1, String mobilePhone2) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.mobilePhone1 = mobilePhone1;
		this.mobilePhone2 = mobilePhone2;
	}

}