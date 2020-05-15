package classicPayModel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import entity.Store;

public class StoreDaoTest {
	
	
	//context identifiers
	private ApplicationContext ctx ;
	private StoreDao storeDao;
	private TransactionTemplate transactionTemplate;
	private PlatformTransactionManager transactionManager;
	
	
	
	//set up the context 
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("pm-spring-config.xml");
		
		storeDao = (StoreDao) ctx.getBean("storeDao");
		
		transactionManager = (PlatformTransactionManager)ctx.getBean("transactionManager");
		
		transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
	
	@Test
	public void testGetAllStores() {
		List<Store> storelist = storeDao.getAllStores();
		assertTrue(storelist.size()>0);
		assertEquals(2,storelist.size());
		for(Store store:storelist) {
			System.out.println("Store: number"+store.getStoreCode() +" add: " + store.getAddressLine1() + " city:" +store.getCity());
		}
		
	}
	
	@Test
	public void testGetStoreByStoreCode() {
		final Store store = storeDao.getStoreByStoreCode(1);
		
		assertNotNull(store.getStoreCode());
		assertEquals("T4T5J8",store.getPostalCode());
		
		
	}
	@Test
	public void testRowCount() {
		long count = storeDao.getRowCount();
		assertEquals(2,count);
	}
	
	
	@Test
	public void testAddStore() {
		final Store store = new Store();
		store.setAddressLine1("543 34 Ave");
		store.setPhone("780-993-5487");
		store.setCity("Montreal");
		store.setProvince("Quebec");
		store.setAddressLine2("");
		store.setPostalCode("Q8RV2V");
		store.setTerritory("QC001");
		store.setCountry("Canada");
		
		transactionTemplate.execute( new TransactionCallbackWithoutResult()
		{	
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				storeDao.createStore(store);
				
				final Store store2 = storeDao.getStoreByStoreCode(store.getStoreCode());
				
				assertNotNull(store2.getStoreCode());
				assertEquals(store.getAddressLine1(),store2.getAddressLine1());
				assertEquals(store.getAddressLine2(),store2.getAddressLine2());
				assertEquals(store.getCity(),store2.getCity());
				assertEquals(store.getProvince(),store2.getProvince());				
				assertEquals(store.getCountry(),store2.getCountry());				
				assertEquals(store.getPhone(),store2.getPhone());
				assertEquals(store.getTerritory(),store2.getTerritory());
				assertEquals(store.getPostalCode(),store2.getPostalCode());
				
				status.setRollbackOnly();
						
		}
			
	});
		
	}
	
	@Test
	public void testUpdateStore() {
		final Store store = storeDao.getStoreByStoreCode(2);
		store.setAddressLine1("new add");
		store.setPhone("new phone");
		store.setCity("newCity");
		store.setProvince("Quebec");
		store.setAddressLine2("");
		store.setPostalCode("Q8RV2E");
		store.setTerritory("QC0001");
		store.setCountry("Canada");
		
		transactionTemplate.execute( new TransactionCallbackWithoutResult()
		{	
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				storeDao.updateStore(store);
				
				final Store store2 = storeDao.getStoreByStoreCode(store.getStoreCode());
				
				assertNotNull(store2.getStoreCode());
				assertEquals(store.getAddressLine1(),store2.getAddressLine1());
				assertEquals(store.getAddressLine2(),store2.getAddressLine2());
				assertEquals(store.getCity(),store2.getCity());
				assertEquals(store.getProvince(),store2.getProvince());				
				assertEquals(store.getCountry(),store2.getCountry());				
				assertEquals(store.getPhone(),store2.getPhone());
				assertEquals(store.getTerritory(),store2.getTerritory());
				assertEquals(store.getPostalCode(),store2.getPostalCode());
				
				status.setRollbackOnly();
						
		}
			
	});
		
	}

	
	@Test
	public void testDeleteStore() {
		final Store store = storeDao.getStoreByStoreCode(2);

		transactionTemplate.execute( new TransactionCallbackWithoutResult()
		{	
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				storeDao.deleteStore(store);
				assertNull( storeDao.getStoreByStoreCode(store.getStoreCode()));
				
				
				status.setRollbackOnly();
						
		}
			
	});
		
	}
}
