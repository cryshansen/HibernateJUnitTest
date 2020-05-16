package entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.IndexColumn;

/**
 * This is the persistence POJO (Plain Ordinary Java Object) mapping to the database table "customers"
 * using Hibernate 3 Annotations.
 */
@Entity						// This is a persistent class
@Table(name="customers")	// This class maps to the table named "customers".
@AccessType("field")		// This class uses field-based annotations.
public class Customer implements java.io.Serializable{

	@Transient 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int customerNumber;
	/*customer could be business contact name is person*/
	@Column(length=50)
	private String clientName;
	
	
	@Column(length=50)
	private String contactFirstName;
	
	@Column(length=50)
	private String contactLastName;
	
	@Column(length=50)
	private String phone;
	
	@Column(length=50)
	private String email;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Address> addressList;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="customerNumber" , nullable = false) 
	//removed the index column and it worked
	 private List<PaymentCard> paymentCardList = new ArrayList<PaymentCard>();
		
	
	public Customer() {
		
	}
	public Customer(int customerNumber) {
		
		this.customerNumber =customerNumber;
	}
	
	public Customer(int customerNumber, String clientName, String contactFirstName, String contactLastName,
			String phone, String email, String addressline1, String addressline2, String city, String province,
			String country, String postalCode) {
		super();
		this.customerNumber = customerNumber;
		this.clientName = clientName;
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
		this.phone = phone;
		this.email = email;

	}
	public int getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getContactFirstName() {
		return contactFirstName;
	}
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}
	public String getContactLastName() {
		return contactLastName;
	}
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	public List<PaymentCard> getPaymentCardList() {
		return paymentCardList;
	}
	public void setPaymentCardList(List<PaymentCard> paymentCardList) {
		this.paymentCardList = paymentCardList;
	}
	
	public void setPaymentCard(PaymentCard card) {
		getPaymentCardList().add(card);
		card.setCustomer(this);
		
	}
	public void setPaymentCard(PaymentCard card,int cardindex) {
		getPaymentCardList().add(cardindex, card);
		card.setCustomer(this);
	}
	
	
}
