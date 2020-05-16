package entity;


import java.sql.Blob;
import javax.persistence.*;
import org.hibernate.annotations.AccessType;

@Entity
@Table(name="productlines")
@AccessType("field")
public class ProductLine  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int productLinePK;
	private String productLine;
    private String textDescription;
    private String htmlDescription;
//    @Lob
//    private Blob image;
    
    

    
	public ProductLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	public ProductLine(int productLinePK) {
			super();
			this.productLinePK = productLinePK;
		}

	public ProductLine(int productLinePK, String productLine, String textDescription, String htmlDescription, Blob image) {
		super();
		this.productLinePK = productLinePK;
		this.productLine = productLine;
		this.textDescription = textDescription;
		this.htmlDescription = htmlDescription;
		//this.image = image;
	}
	
	
	public int getProductLinePK() {
		return productLinePK;
	}

	public void setProductLinePK(int productLinePK) {
		this.productLinePK = productLinePK;
	}

	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public String getTextDescription() {
		return textDescription;
	}
	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}
	public String getHtmlDescription() {
		return htmlDescription;
	}
	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}
//	public Blob getImage() {
//		return image;
//	}
//	public void setImage(Blob image) {
//		this.image = image;
//	}
//    
    
    

}
