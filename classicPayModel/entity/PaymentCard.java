package entity;

import javax.persistence.*;

@Entity						// This is a persistent class
@Table(name="paymentcards")	// This class maps to the table named "paymentcards" in mssql.
//@AccessType("field")		// This class uses field-based annotations.
public class PaymentCard implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	//@Column(name = "cardIndex", unique = true, nullable = false)
	private int cardIndex;
	@Column(length=75)
	private String ccNameOnCard;
	@Column(length=16)
	private String ccNumber;
	@Column(length=50)
	private String ccType;
	@Column(length=3)
	private String ccSecurity;
	@Column(length=4)
	private String ccExpireDate;
	 
	@ManyToOne
	@JoinColumn(name = "customerNumber", nullable = false, insertable = false, updatable = false)
	private Customer customer;

	
	
	
	
	public PaymentCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentCard(int card_index, String ccNameOnCard, String ccNumber, String ccType,
			 String ccSecurity, String ccExpireDate,
			Customer customer) {
		super();
		this.cardIndex = card_index;
		this.ccNameOnCard = ccNameOnCard;
		this.ccNumber = ccNumber;
		this.ccType = ccType;
		this.ccSecurity = ccSecurity;
		this.ccExpireDate = ccExpireDate;
		this.customer = customer;
	}

	public int getCard_index() {
		return cardIndex;
	}

	public void setCard_index(int card_index) {
		this.cardIndex = card_index;
	}

	public String getCcNameOnCard() {
		return ccNameOnCard;
	}

	public void setCcNameOnCard(String ccNameOnCard) {
		this.ccNameOnCard = ccNameOnCard;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	public String getCcSecurity() {
		return ccSecurity;
	}

	public void setCcSecurity(String ccSecurity) {
		this.ccSecurity = ccSecurity;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCcExpireDate() {
		return ccExpireDate;
	}

	public void setCcExpireDate(String ccExpireDate) {
		this.ccExpireDate = ccExpireDate;
	}
	
	
	
	
}
