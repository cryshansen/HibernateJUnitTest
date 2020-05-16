package classicPayModel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import classicPayModel.entity.Customer;
import classicPayModel.entity.Order;
import classicPayModel.entity.OrderDetail;
import classicPayModel.entity.Product;

public class OrderDaoTest {
	
	private ApplicationContext ctx;
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	private OrderDao orderDao;
	private CustomerDao customerDao;
	private ProductDao productDao;
	
	
	
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("pm-spring-config.xml");
		orderDao = (OrderDao) ctx.getBean("orderDao");
		customerDao = (CustomerDao) ctx.getBean("customerDao");
		productDao = (ProductDao) ctx.getBean("productDao");
		transactionManager = (PlatformTransactionManager) ctx.getBean("transactionManager");
		transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
	
	@Test
	public void testGetAllOrders() {
		List<Order> orderList = orderDao.getAllOrders();
		//List<OrderDetail> orderDetails = orderDao.get
		assertTrue(orderList.size()>0);
		assertEquals(3,orderList.size());
		
		for(Order order: orderList) {
			System.out.println(" Order: " + order.getOrderDate() +" : " + order.getRequiredDate()+ " " + order.getCustomer().getClientName() );
			List<OrderDetail> orderDetails = order.getOrderDetails();
			for(OrderDetail ordDet : orderDetails) {
				System.out.println(" Order Detail: " + ordDet.getPricePerItem());
			}
		}
		
	}
	@Test
	public void testGetOrderByOrderNumber() {
		final Order order = orderDao.getOrderByOrderNumber(1);
		
		
		assertEquals(1,order.getOrderNumber());
		assertEquals(10001,order.getCustomer().getCustomerNumber());
		
		
		System.out.println(" Order: " + order.getOrderDate() +" : " + order.getRequiredDate()+ " " + order.getCustomer().getClientName() );
			
		
		
	}
	
	@Test
	public void testCreateOrder() throws ParseException {
		java.util.Date today = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 java.util.Date requiredDate = sdf.parse("2020-05-30");
		 java.util.Date shipDate = sdf.parse("2020-06-10");
		final Order order = new Order();
		order.setOrderDate(today);
		order.setRequiredDate(requiredDate);
		order.setShippedDate(shipDate);
		order.setStatus("ordered");
		order.setComments(" thanks ");
		final Customer cust = customerDao.getCustomerByCustomerNumber(10002);
		order.setCustomer(cust);
		final OrderDetail ordDets = new OrderDetail();
		
		ordDets.setPricePerItem(new BigDecimal("3.50"));
		ordDets.setQuantityOrdered(4);
		final Product prod = productDao.getProductByProductCode(2);
		ordDets.setProductCode(prod);
		
		
		final OrderDetail ordDets2 = new OrderDetail();
		
		ordDets2.setPricePerItem(new BigDecimal("3.00"));
		ordDets2.setQuantityOrdered(4);
		final Product prod2 = productDao.getProductByProductCode(1);
		ordDets2.setProductCode(prod2);
		
		
		List<OrderDetail> ordDetList = new ArrayList<OrderDetail>();
		ordDetList.add(ordDets);
		ordDetList.add(ordDets2);
		order.setOrderDetails(ordDetList);
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	orderDao.createOrder(order);
		    	
		    	assertNotNull(order.getOrderNumber());
		    	final Order order2 = orderDao.getOrderByOrderNumber(order.getOrderNumber());
		    	assertEquals(order.getCustomer().getCustomerNumber(),order2.getCustomer().getCustomerNumber());
		    	assertEquals(2,order2.getOrderDetails().size());
		    	assertTrue(order2.getOrderDetails().size()>0);
		    	
		    	status.setRollbackOnly();
		    	
		    }});
		
	}
	
	
	@Test
	public void testUpdateOrder() throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date today = sdf.parse("2020-05-15");
		 java.util.Date requiredDate = sdf.parse("2020-05-31");
		 java.util.Date shipDate = sdf.parse("2020-06-08");
		final Order order = orderDao.getOrderByOrderNumber(1);
		order.setOrderDate(today);
		order.setRequiredDate(requiredDate);
		order.setShippedDate(shipDate);
		order.setStatus("ordered");
		order.setComments(" ordered ");
		final Customer cust = customerDao.getCustomerByCustomerNumber(10002);
		order.setCustomer(cust);
		

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	orderDao.updateOrder(order);
		    	
		    	assertNotNull(order.getOrderNumber());
		    	final Order order2 = orderDao.getOrderByOrderNumber(order.getOrderNumber());
		    	assertEquals(order.getCustomer().getCustomerNumber(),order2.getCustomer().getCustomerNumber());
		    	
		    	status.setRollbackOnly();
		    
		    }});
	}
	@Test
	public void testDeleteOrder() {
		
		
		final Order order = orderDao.getOrderByOrderNumber(1);
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	orderDao.deleteOrder(order);
		    	final Order ord = orderDao.getOrderByOrderNumber(order.getOrderNumber());
		    	assertNull(ord);
		    
		    	status.setRollbackOnly();
		    }
		});
		
	}
	

	
	@Test
	public void testGetRowCount() {
		
		long count = orderDao.getRowCount();
		assertEquals(2,count);
		
	}
	

}
