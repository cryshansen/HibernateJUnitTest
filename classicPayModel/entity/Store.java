package entity;

/* this class represents the same as an office in classic models 
 * it just makes sense to be a store than an office if selling products */

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.AccessType;

/**
 * This is the persistence POJO (Plain Ordinary Java Object) mapping to the database table "offices"
 * using Hibernate 3 Annotations.
 * 
 * @author Sam Wu
 * @version 2007.10.30
 */
@Entity						// This is a persistent class
@Table(name="stores")		// This class maps to the table named "stores".
@AccessType("field")		// This class uses field-based annotations.
public class Store  implements java.io.Serializable {

	
	@Transient	// Do NOT persist this property.
	private static final long serialVersionUID = 1L;

	/** Use the @Id annotation to indicate this field is the unique identifier */
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )	
	private int storeCode;
	
	@Column(name="city",length=50)
	private String city;
	
	@Column(length=50)
	private String phone;
	
	@Column(length=50)
	private String addressLine1;
	
	@Column(length=50, nullable=true)
	private String addressLine2;
	
	@Column(length=50, nullable=true)
	private String province;
	
	@Column(length=50)
	private String country;
	
	@Column(length=15)
	private String postalCode;
	
	@Column(length=10)
	private String territory;

	public Store() {
		super();
	}
	
	public Store(int storeCode) {
		this.storeCode = storeCode;
	}
	
	public Store(int storeCode, String city, String phone, String addressLine1, String addressLine2, String state,
			String country, String postalCode, String territory) {
		super();
		this.storeCode = storeCode;
		this.city = city;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.province = state;
		this.country = country;
		this.postalCode = postalCode;
		this.territory = territory;
	}
	

	
	public int getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(int storeCode) {
		this.storeCode = storeCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public boolean equals(Object other) {
    	boolean equal = false;
    	if( other instanceof Store ) {
    		Store castOther = (Store) other;
    		equal = new EqualsBuilder()
    			.append(this.getStoreCode(), castOther.getStoreCode())
    			.isEquals();
    	}
    	return equal;
    }
				
    public int hashCode() {
    	return new HashCodeBuilder()
    			.append(this.getStoreCode())
    			.toHashCode();
    }

	

}
