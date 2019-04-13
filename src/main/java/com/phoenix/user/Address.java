package com.phoenix.user;

public class Address {
	private String streetnumber;
	private String street;
	private String city;
	private String country;
	private String zip;
	
	public Address(String streetnumber, String street, String city, String country, String zip) {
		super();
		this.streetnumber = streetnumber;
		this.street = street;
		this.city = city;
		this.country = country;
		this.zip = zip;
	}

	public String getStreetnumber() {
		return streetnumber;
	}

	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	
}
