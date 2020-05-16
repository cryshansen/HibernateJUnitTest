package classicPayModel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.sql.*;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import classicPayModel.entity.Customer;
import classicPayModel.entity.Payment;


public class PaymentDaoTest extends HibernateDaoSupport{
	
	private ApplicationContext ctx;
	private PaymentDao paymentDao;
	private CustomerDao customerDao;
	
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	
	@Before
	public void setUp() throws Exception{
		ctx = new ClassPathXmlApplicationContext("pm-spring-config.xml");
		paymentDao = (PaymentDao) ctx.getBean("paymentDao");
		customerDao = (CustomerDao) ctx.getBean("customerDao");
		transactionManager = (PlatformTransactionManager) ctx.getBean("transactionManager");
		transactionTemplate = new TransactionTemplate(transactionManager);
		
	}
	
	
	@Test
	public void testGetAllPayments() {
		List<Payment> paylist = paymentDao.getAllPayments();
		
		assertTrue(paylist.size()>=0);
		assertEquals(1,paylist.size());
		for(Payment p : paylist) {
			System.out.println("Payment "+p.getPaymentId()+" amt: "+p.getAmount()+" date "+p.getPaymentDate());
		}
		
	}
	@Test
	public void testGetPaymentByPaymentId() throws ParseException {
		final Payment pay = paymentDao.getPaymentByPaymentId(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date payDate = sdf.parse("2020-12-30");
		assertEquals(1,pay.getPaymentId());
		assertEquals(25.00,pay.getAmount());
		assertEquals(payDate,pay.getPaymentDate());
		assertEquals("6543 5433 6510",pay.getCheckNumber());
		assertEquals("windy river soap",pay.getCustomer().getClientName());
		
		System.out.println("Payment "+pay.getPaymentId()+" amt: "+pay.getAmount()+" date "+pay.getPaymentDate());
		
		
	}
//	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreatePayment() {
		final Payment p = new Payment();
		
		p.setAmount( new BigDecimal("12.000"));
		p.setCheckNumber("2345453");
		
		p.setPaymentDate(new java.util.Date());
		final Customer c = customerDao.getCustomerByCustomerNumber(10001);
		p.setCustomer(c);
		

    	transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	
		    	paymentDao.createPayment(p);
		    	final Payment p2 =paymentDao.getPaymentByPaymentId(p.getPaymentId());
		    	
		    	assertEquals(p.getAmount(),p2.getAmount());
		    	assertEquals(p.getCheckNumber(),p2.getCheckNumber());
		    	assertEquals(p.getCustomer().getCustomerNumber(),p2.getCustomer().getCustomerNumber());
		    	assertEquals(p.getPaymentDate(),p2.getPaymentDate());
		    	
		    	status.setRollbackOnly();
		    	
		    }});
		
		
		
	}
	
	
	@Test
	public void testUpdatePayment() throws ParseException {
		final Payment p = paymentDao.getPaymentByPaymentId(1);
		p.setAmount(new BigDecimal("35.00"));
		p.setCheckNumber("6765 4323 2231");
		final Customer c = customerDao.getCustomerByCustomerNumber(10002);
		p.setCustomer(c);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date paymentDate = sdf.parse("2020-04-30");
		p.setPaymentDate(paymentDate);
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	
		    	paymentDao.updatePayment(p);
		    	final Payment p2 = paymentDao.getPaymentByPaymentId(p.getPaymentId());
		    	assertEquals(p.getAmount(),p2.getAmount());
		    	assertEquals(p.getCheckNumber(),p2.getCheckNumber());
		    	assertEquals(p.getCustomer().getCustomerNumber(),p2.getCustomer().getCustomerNumber());
		    	assertEquals(p.getPaymentDate(),p2.getPaymentDate());
		    	
		    	status.setRollbackOnly();
		    	
		    	
		    	
		    }});
	}
	
	@Test
	public void testDeletePayment() {
		final Payment p = paymentDao.getPaymentByPaymentId(1);
		
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	paymentDao.deletePayment(p);
		    	 assertNull(paymentDao.getPaymentByPaymentId(1));
		    	
		    	status.setRollbackOnly();
		    	
		    }});
		
	}
	
	@Test
	public void testGetPaymentByCustomer() {
		final Customer customer = customerDao.getCustomerByCustomerNumber(10001);
		assertNotNull(customer);
		final List<Payment> p = paymentDao.findPaymentsByCustomer(customer);
		assertNotNull(p);
		assertTrue(p.size()>0);
		
		
	}
	@Test
	public void testGetPaymentByCustomerName() {
		final Customer customer = customerDao.getCustomerByCustomerNumber(10001);
		assertNotNull(customer);
		final List<Payment> p = paymentDao.findPaymentsByCustomer(customer);
		assertNotNull(p);
		assertTrue(p.size()>0);
	}
	
	@Test
	public void testGetPaymentByCustomerNumber() {
		
		final Customer customer = customerDao.getCustomerByCustomerNumber(10001);
		assertNotNull(customer);
		
		final List<Payment> p = paymentDao.findPaymentsByCustomer(customer);
		assertNotNull(p);
		assertTrue(p.size()>0);
		
		
		
	}
	
	@Test
	public void TestGetRowCount() {
		long count = paymentDao.getRowCount();

		assertEquals(1,count);
		
	}
	
	
	
}
