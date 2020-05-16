package entity;

import java.math.BigDecimal;
import javax.persistence.*;
import org.hibernate.annotations.AccessType;

@Entity
@Table(name="products")
@AccessType("field")
public class Product implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )	
	private int productCode;
	@Column(length=50)
    private String productName;
    @ManyToOne
    @JoinColumn(name="productLineFK")
    private ProductLine productLine;
    @Column(length=50)
    private String productScale;
    @Column(length=50)
    private String productVendor;
    @Column(length=50)
    private String productDescription;
    @Column(length=15)
    private int quantityInStock;
    @Column(length=15)
    private BigDecimal buyPrice;
    @Column(length=15)
    private BigDecimal msrp;
   
    
    public Product() {
    	
    }
    public Product(int productCode) {
    	this.productCode = productCode;
    }
    
	public Product(int productCode, String productName, ProductLine productLine, String productScale,
			String productVendor, String productDescription, int quantityInStock, BigDecimal buyPrice,
			BigDecimal msrp) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		this.msrp = msrp;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ProductLine getProductLine() {
		return productLine;
	}
	public void setProductLine(ProductLine productLine) {
		this.productLine = productLine;
	}
	public String getProductScale() {
		return productScale;
	}
	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}
	public String getProductVendor() {
		return productVendor;
	}
	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	public BigDecimal getMsrp() {
		return msrp;
	}
	public void setMsrp(BigDecimal msrp) {
		this.msrp = msrp;
	}
    
    

}
