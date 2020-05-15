package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="addresses")	// This class maps to the table named "customers".
@AccessType("field")	
public class Address implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int addressId;
	
	//must use key joincolumn to make add work for addresses.
	 @ManyToOne
	 @JoinColumn(name="customerNumber")
	 private Customer customer;
	
	 
	@Column(length=50)
	private String addressline1;
	//allows testing inserts to succeed without the variable
	@Column(length=50, nullable=true)
	private String addressline2;
	@Column(length=50, nullable=true)
	private String addressline3;
	@Column(length=50, nullable=true)
	private String addressline4;
	
	@Column(length=50)
	private String city;
	@Column(length=50)
	private String province; /* can be state as well*/
	@Column(length=50)
	private String Country;
	@Column(length=15)
	private String postalCode;
	
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Address(int addressId, Customer customer, String addressline1, String addressline2, String addressline3,
			String addressline4, String city, String province, String country, String postalCode) {
		super();
		this.addressId = addressId;
		this.customer = customer;
		this.addressline1 = addressline1;
		this.addressline2 = addressline2;
		this.addressline3 = addressline3;
		this.addressline4 = addressline4;
		this.city = city;
		this.province = province;
		Country = country;
		this.postalCode = postalCode;
	}


	public int getAddressId() {
		return addressId;
	}


	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public String getAddressline1() {
		return addressline1;
	}


	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}


	public String getAddressline2() {
		return addressline2;
	}


	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}


	public String getAddressline3() {
		return addressline3;
	}


	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}


	public String getAddressline4() {
		return addressline4;
	}


	public void setAddressline4(String addressline4) {
		this.addressline4 = addressline4;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCountry() {
		return Country;
	}


	public void setCountry(String country) {
		Country = country;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	
	

}
