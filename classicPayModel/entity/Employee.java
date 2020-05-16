package  entity;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.AccessType;

/**
 * This is the persistence POJO (Plain Ordinary Java Object) mapping to the database table "employees"
 * using Hibernate 3 Annotations.
 * 
 * @author Crystal Hansen
 * @version 2020
 */
@Entity						// This is a persistent class
@Table(name="employees")	// This class maps to the table named "employees".
@AccessType("field")		// This class uses field-based annotations.
public class Employee implements java.io.Serializable {

	@Transient 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )	
	private int employeeNumber;
	
	@Column(length=50)
	private String lastName;
	
	@Column(length=50)
	private String firstName;
	
	@Column(length=15)
	private String phone;
	@Column(length=100, unique=true)
	private String email;
	
	
	
	@Column(length=50)
	private String employeeRole;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="storeCode")
	private Store store;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional=true )
	@JoinColumn(name="reportsTo")
	private Employee supervisor;
	
	

	public Employee() {
	}

	public Employee(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public Employee(int employeeNumber, String lastName, String firstName,
		 String email, Store store, Employee supervisor,
			String employeeRole) {
		super();
		this.employeeNumber = employeeNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		
		this.email = email;
		this.store = store;
		this.supervisor = supervisor;
		this.employeeRole = employeeRole;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	
	
    public String getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}



	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean equals(Object other) {
    	boolean equal = false;
    	if( other instanceof Store ) {
    		Employee castOther = (Employee) other;
    		equal = new EqualsBuilder()
    			.append(this.getEmployeeNumber(), castOther.getEmployeeNumber())
    			.isEquals();
    	}
    	return equal;
    }
				
    public int hashCode() {
    	return new HashCodeBuilder()
    			.append(this.getEmployeeNumber())
    			.toHashCode();
    }
		
}
