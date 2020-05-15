package classicPayModel.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import entity.Product;
import entity.ProductLine;

public class ProductDaoTest {

	private ApplicationContext ctx;
	private ProductDao productDao;
	private ProductLineDao productLineDao;
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	@Before
	public void setUp() throws Exception{
		ctx = new ClassPathXmlApplicationContext("pm-spring-config.xml");
		productDao = (ProductDao) ctx.getBean("productDao");
		productLineDao = (ProductLineDao) ctx.getBean("productLineDao");
		transactionManager= (PlatformTransactionManager)ctx.getBean("transactionManager");
		transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
	
	@Test
	public void testGetAllProducts() {
		List<Product> prodList = productDao.getAllProducts();
		assertEquals(3,prodList.size());
		assertTrue(prodList.size()>0);
		for(Product p : prodList) {
			
			System.out.println(p.getProductDescription() + " " + p.getProductName() + " Qty" + p.getQuantityInStock());
		}
		
		
	}
	@Test
	public void testGetProductByProductCode() {
		
		final Product prod = productDao.getProductByProductCode(1);
		assertEquals(1,prod.getProductCode());
		assertEquals("luxury silky coconut milk coconut oil soap",prod.getProductDescription());
		assertEquals("Coconut Milk Soap",prod.getProductName());
		assertEquals(15,prod.getQuantityInStock());
		assertEquals(4,prod.getMsrp());
		assertEquals(2,prod.getBuyPrice());
		
	}
	
	@Test
	public void testAddProduct() {
		final Product p = new Product();
		final ProductLine pl = productLineDao.getProductLineByProductLine(1);
		p.setProductName("Cherry Seed Soap");
		p.setProductDescription("Cherry Seed is good for...");
		p.setProductScale("jig");
		p.setProductVendor("Bob");
		p.setQuantityInStock(15);
		p.setBuyPrice(new BigDecimal("2.00"));
		p.setMsrp(new BigDecimal("4.00000"));
		p.setProductLine(pl);
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	productDao.addProduct(p);
		    	final Product p2 = productDao.getProductByProductCode(p.getProductCode());
		    	
		    	assertEquals(p.getProductCode(),p2.getProductCode());
		    	assertEquals(p.getProductName(),p2.getProductName());
		    	assertEquals(p.getProductDescription(),p2.getProductDescription());
		    	assertEquals(p.getProductScale(),p2.getProductScale());
		    	assertEquals(p.getProductVendor(),p2.getProductVendor());
		    	assertEquals(p.getBuyPrice(),p2.getBuyPrice());
		    	assertEquals(p.getMsrp(),p2.getMsrp());
		    	
		    	
		    	assertEquals(p.getProductLine(),p2.getProductLine());
		    	status.setRollbackOnly();
		    	
		    }});
		
		
	}
	@Test
	public void testUpdateProduct() {
		final Product p = productDao.getProductByProductCode(4);
		final ProductLine pl = productLineDao.getProductLineByProductLine(4);
		p.setProductName("Cherry Seed Serum");
		p.setProductDescription("Cherry Seed treats the skin with a little extra");
		p.setProductScale("unknown");
		p.setProductVendor("Bob Mills");
		p.setQuantityInStock(15);
		p.setBuyPrice(new BigDecimal("6.00"));
		p.setMsrp(new BigDecimal("15.00000"));
		p.setProductLine(pl);
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	productDao.updateProduct(p);
		    	final Product p2 = productDao.getProductByProductCode(p.getProductCode());
		    	
		    	assertEquals(p.getProductCode(),p2.getProductCode());
		    	assertEquals(p.getProductName(),p2.getProductName());
		    	assertEquals(p.getProductDescription(),p2.getProductDescription());
		    	assertEquals(p.getProductScale(),p2.getProductScale());
		    	assertEquals(p.getProductVendor(),p2.getProductVendor());
		    	assertEquals(p.getBuyPrice(),p2.getBuyPrice());
		    	assertEquals(p.getMsrp(),p2.getMsrp());
		    	
		    	
		    	assertEquals(p.getProductLine(),p2.getProductLine());
		    	
		    }});
		
		
	}
	
	
	@Test
	public void testDeleteProduct() {
		final Product p = productDao.getProductByProductCode(4);
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	
		    	productDao.deleteProduct(p);
		    	assertNull(productDao.getProductByProductCode(p.getProductCode()));
		    	status.setRollbackOnly();
		    }});
		
	}
	
}
