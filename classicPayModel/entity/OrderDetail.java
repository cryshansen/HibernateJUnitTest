package entity;

import javax.persistence.*;
import org.hibernate.annotations.AccessType;

import java.math.BigDecimal;


@Embeddable
@AccessType("field")
public class OrderDetail implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//“many” side of the association owns the relationship (OrderDetails)
	//and is responsible for updating the database
	//the @Id is not used because it is a composite key of all fields.
	@ManyToOne
	@JoinColumn(name="productCode")
	private Product productCode	;
	private int quantityOrdered	;
	private BigDecimal pricePerItem;
	
	

	public Product getProductCode() {
		return productCode;
	}
	public void setProductCode(Product productCode) {
		this.productCode = productCode;
	}
	public int getQuantityOrdered() {
		return quantityOrdered;
	}
	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}
	public BigDecimal getPricePerItem() {
		return pricePerItem;
	}
	public void setPricePerItem(BigDecimal pricePerItem) {
		this.pricePerItem = pricePerItem;
	}
	public OrderDetail(
			//int orderLineNumber,int orderNumber, 
			Product productCode, int quantityOrdered, BigDecimal pricePerItem) {
		super();
		//this.orderLineNumber = orderLineNumber;
		//this.orderNumber = orderNumber;
		this.productCode = productCode;
		this.quantityOrdered = quantityOrdered;
		this.pricePerItem = pricePerItem;
	}
	
	public OrderDetail() {
		super();
	}
	
	
	
	
}
