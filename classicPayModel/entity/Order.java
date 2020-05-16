package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="orders")
@AccessType("field")
public class Order implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
    private int orderNumber;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	@Temporal(TemporalType.DATE)
	private Date requiredDate;
	@Temporal(TemporalType.DATE)
	private Date shippedDate;
	private String status;
	@ManyToOne
    @JoinColumn(name="customerNumber")
	private Customer customer;
	private String comments;
	@CollectionOfElements( fetch = FetchType.EAGER )
    @JoinTable( name="orderdetails", joinColumns = @JoinColumn(name="orderNumber") ) 
    @IndexColumn(name="orderLineNumber", base=1)
    private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>(0);

	public  Order() {
		super();
	}

	public Order(int orderNumber, Date orderDate, Date requiredDate, Date shippedDate, String status, Customer customer,
			String comments, List<OrderDetail> orderDetails) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.status = status;
		this.customer = customer;
		this.comments = comments;
		this.orderDetails = orderDetails;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}
