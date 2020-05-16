package entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.AccessType;

@Entity
@Table(name="payments")
@AccessType("field")
public class Payment implements java.io.Serializable{

	private static final long serialVersionUID=1L;
	
//used for composite primary key generation	
//	@EmbeddedId
//	private PaymentPk id;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
	
	private BigDecimal amount;
	private Date paymentDate;
	
	@ManyToOne
	@JoinColumn(name="customerNumber")
	private Customer customer;
	private String checkNumber;
	
	public Payment() {
		super();
	}
	public Payment(int paymentId) {
		super();
		this.id =paymentId;
	}

	public Payment(int id,		
			Date paymentDate, 
			BigDecimal amount,
			String checkNumber,
			Customer cust ) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.checkNumber = checkNumber;
		this.customer = cust;
	}

//	public PaymentPk getId() {
//		return id;
//	}
//
//	public void setId(PaymentPk id) {
//		this.id = id;
//	}
//
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getPaymentId() {
		return id;
	}

	public void setPaymentId(int paymentId) {
		this.id = paymentId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	
	
	
}
