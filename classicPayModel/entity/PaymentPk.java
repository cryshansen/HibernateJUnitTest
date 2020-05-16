package entity;

import java.util.Date;

import javax.persistence.*;

@Embeddable
public class PaymentPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	//this can be the credit card number in hypothetical terms. 
	//Assumption, card name is same as customer name
    private String checkNumber;
    private Date paymentDate;
   //private int id;
    //credit card has expire date, fullname security number and card number
    //private String expire_date;
    //private String securityNumber; 
    //customerName could store all in customer as well depend on requirement
    //private String FullName;
    
    
    
    @ManyToOne
    @JoinTable(name="customers")
    private Customer customer;

    public PaymentPk() {
    	
    	customer =  new Customer();
    }

	public PaymentPk(String checkNumber, Customer customer) {
		super();
		this.checkNumber = checkNumber;
		this.customer = customer;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    
}
