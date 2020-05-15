package classicPayModel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import entity.ProductLine;



public class ProductLineDaoTest {
	
	private ApplicationContext ctx;
	private ProductLineDao productLineDao;
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("pm-spring-config.xml");
		productLineDao = (ProductLineDao) ctx.getBean("productLineDao");
		transactionManager = (PlatformTransactionManager) ctx.getBean("transactionManager");
		transactionTemplate= new TransactionTemplate(transactionManager);
		
		
	}
	@Test
	public void testGetAllProductLines() {
		List<ProductLine> prodlineList = productLineDao.getAllProductLines();
		
		assertTrue(prodlineList.size()>0);
		assertEquals(4,prodlineList.size());
		
		for(ProductLine p : prodlineList) {
			System.out.println(" "+p.getProductLine() +" "+p.getTextDescription()+" "+p.getHtmlDescription());
		}
		
	}
	

}
