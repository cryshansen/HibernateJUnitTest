package classicPayModel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


import classicPayModel.dao.CustomerDao;
import entity.Address;
import entity.Customer;
import entity.PaymentCard;

public class CustomerDaoTest {

	
	private ApplicationContext ctx;
	private CustomerDao customerDao;
	
    private TransactionTemplate transactionTemplate;
    private PlatformTransactionManager transactionManager;
	
    @Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("pm-spring-config.xml");
		customerDao = (CustomerDao) ctx.getBean("customerDao");
		transactionManager = (PlatformTransactionManager) ctx.getBean("transactionManager");
		transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
    
    @Test
    public void testgetAllCustomers() {
    	List<Customer> customerList = customerDao.getAllCustomers();
		assertTrue( customerList.size() > 0 );
		assertEquals(3, customerList.size() );
		for(Customer customer : customerList) {
			System.out.println( customer.getClientName() + "\t" + customer.getContactFirstName());
		}
    	
    }
    
    
    
	@Test
	public void testGetCustomerByCustomerNumbers() {
		final Customer cust = customerDao.getCustomerByCustomerNumber(10002);
		
		assertEquals(10002, cust.getCustomerNumber() );
		assertEquals("JustNutritives", cust.getClientName() );
		
	}
	
	@Test
	public void testCreateCustomer() {
		final Customer cust = new Customer();
		cust.setCustomerNumber(10003);
		cust.setClientName("Banaba Boat");
		cust.setContactFirstName("Sarah");
		cust.setContactLastName("George");
		
    	cust.setEmail("sgeorge@bb.ca");
    	cust.setPhone("780-995-3814");
    	 
    	Address homeAddress = new Address();
         homeAddress.setAddressline1("10 Park Avenue");
         homeAddress.setCity("London");
         homeAddress.setPostalCode("SW12RT");
         homeAddress.setProvince("Alberta");
         homeAddress.setCountry("Canada");
         homeAddress.setCustomer(cust);
        
         Address workAddress = new Address();
         workAddress.setAddressline1("15 Maple Road");
         workAddress.setCity("Sheffield");
         workAddress.setPostalCode("SH142YU");
         workAddress.setProvince("Alberta");
         workAddress.setCountry("Canada");
         workAddress.setCustomer(cust);
         List<Address> addressList = new  ArrayList<Address>();
         addressList.add(workAddress);
         addressList.add(homeAddress);
         cust.setAddressList(addressList);
    	
   	
    	
    	transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	customerDao.createCustomer(cust);
		    	Customer cust2 = customerDao.getCustomerByCustomerNumber(cust.getCustomerNumber());
		    	assertEquals(cust.getCustomerNumber(),cust2.getCustomerNumber());
		    	assertEquals(cust.getClientName(),cust2.getClientName());
		    	
		    	assertEquals(cust.getContactFirstName(),cust2.getContactFirstName());
		    	assertEquals(cust.getContactLastName(),cust2.getContactLastName());
		    	assertEquals(cust.getEmail(),cust2.getEmail());
		    	assertEquals(cust.getPhone(),cust2.getPhone());
		    	//cust.getAddressList();
		    	assertTrue(cust.getAddressList().size()>0);
	        
		        status.setRollbackOnly();
		    }
		    
		});
		
	}
	@Test
	public void testAddCustomerPaymentCard() {
		final Customer cust = new Customer();
		//cust.setCustomerNumber(10003);
		cust.setClientName("Trader Joes");
		cust.setContactFirstName("Joe");
		cust.setContactLastName("Bloggs");
		
    	cust.setEmail("jbloggs@bb.ca");
    	cust.setPhone("780-965-3814");
      

        PaymentCard visa = new PaymentCard();
        visa.setCcType("VISA");
        visa.setCcNumber("1234567812345678");
        visa.setCcNameOnCard("Mr J Bloggs");
        visa.setCcExpireDate("0614");
        visa.setCcSecurity("098");
        PaymentCard masterCard = new PaymentCard();
        masterCard.setCcType("MASTERCARD");
        masterCard.setCcNumber("8765432187654321");
        masterCard.setCcNameOnCard("Mr Joe Bloggs");
        masterCard.setCcExpireDate("0714");
        masterCard.setCcSecurity("098");
        
        PaymentCard amex = new PaymentCard();
        amex.setCcType("AMEX");
        amex.setCcNumber("1122334455667788");
        amex.setCcNameOnCard("Joe Bloggs");
        amex.setCcExpireDate("0814");
        amex.setCcSecurity("098");
        

        cust.setPaymentCard(amex);
        cust.setPaymentCard(masterCard);
        cust.setPaymentCard(visa,1);
       
        
    	transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	 customerDao.createCustomer(cust);
		    	Customer cust2 = customerDao.getCustomerByCustomerNumber(cust.getCustomerNumber());
		    	assertEquals(cust.getCustomerNumber(),cust2.getCustomerNumber());
		    	assertEquals(cust.getClientName(),cust2.getClientName());
		    	
		    	assertEquals(cust.getContactFirstName(),cust2.getContactFirstName());
		    	assertEquals(cust.getContactLastName(),cust2.getContactLastName());
		    	assertEquals(cust.getEmail(),cust2.getEmail());
		    	assertEquals(cust.getPhone(),cust2.getPhone());
		    	//cust.getAddressList();
		    	//assertTrue(cust.getAddressList().size()>0);
		    	assertTrue(cust.getPaymentCardList().size()>0);
		    	assertEquals(3,cust.getPaymentCardList().size());
    
		    	status.setRollbackOnly();
		    }});
		
		
	}
	
	@Test
	public void testUpdateCustomer() {
		final Customer cust = customerDao.getCustomerByCustomerNumber(10002);
		
		cust.setClientName("Banana Boat");
		cust.setContactFirstName("Sara");
		cust.setContactLastName("George-Michale");
		
    	cust.setEmail("sgeorge-michale@bb.ca");
    	cust.setPhone("780-995-3818");
    	 
    	Address workAddress = new Address();
         workAddress.setAddressline1("15 Maple Road");
         workAddress.setCity("Sheffield");
         workAddress.setPostalCode("SH142YU");
         workAddress.setProvince("Alberta");
         workAddress.setCountry("Canada");
         workAddress.setCustomer(cust);
         
         
         Address homeAddress = new Address();
         homeAddress.setAddressline1("10 Park Avenue");
         homeAddress.setCity("London");
         homeAddress.setPostalCode("SW12RT");
         homeAddress.setProvince("Alberta");
         homeAddress.setCountry("Canada");
         homeAddress.setCustomer(cust);
         
    	//add address list to customer by adding to addresslist directly
         List<Address> addressList = new ArrayList<Address>();
         addressList.add(workAddress);
         addressList.add(homeAddress);
         cust.setAddressList(addressList);
       
    	transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	customerDao.updateCustomer(cust);

		    	Customer cust2 = customerDao.getCustomerByCustomerNumber(10002);
		    	assertEquals(cust.getCustomerNumber(),cust2.getCustomerNumber());
		    	assertEquals(cust.getClientName(),cust2.getClientName());
		    	
		    	assertEquals(cust.getContactFirstName(),cust2.getContactFirstName());
		    	assertEquals(cust.getContactLastName(),cust2.getContactLastName());
		    	assertEquals(cust.getEmail(),cust2.getEmail());
		    	assertEquals(cust.getPhone(),cust2.getPhone());
		        
		    	assertTrue(cust2.getAddressList().size()>0);
		    	assertEquals(cust.getAddressList().size(),cust2.getAddressList().size());
		    	
		    	//assertTrue(cust2.getPaymentCardList().size()>0);
		    	//assertEquals(3,cust.getAddressList().size());
		        status.setRollbackOnly();
		    }
		    
		});
		
	}
	
	
	@Test
	public void testDeleteCustomer() {
		final Customer cust = customerDao.getCustomerByCustomerNumber(10002);
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			 protected void doInTransactionWithoutResult(TransactionStatus status) {
				 customerDao.deleteCustomer(cust);
				 assertNull(customerDao.getCustomerByCustomerNumber(cust.getCustomerNumber()));
				 //assertNull(customerDao.get);
				 status.setRollbackOnly();
			 }
		});
		
		
	}
	
	@Test
	public void testGetRowCount() {
		Long count = customerDao.getRowCount();
		assertEquals( 3, count );
	}
	
}
