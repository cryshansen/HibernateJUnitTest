package classicPayModel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import entity.Employee;
import entity.Store;

public class EmployeeDaoTest {


	
	private ApplicationContext ctx;
	private EmployeeDao employeeDao;
	private StoreDao	storeDao;
    private TransactionTemplate transactionTemplate;
    private PlatformTransactionManager transactionManager;
	
    @Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("pm-spring-config.xml");
		employeeDao = (EmployeeDao) ctx.getBean("employeeDao");
		storeDao = (StoreDao) ctx.getBean("storeDao");
		transactionManager = (PlatformTransactionManager) ctx.getBean("transactionManager");
		transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
    
    @Test 
    public void testGetAllEmployees() {
    	List<Employee> emplist = employeeDao.getAllEmployees();
    	
    	assertTrue(emplist.size() >0);
    	assertEquals(4,emplist.size());
    	for(Employee emp :emplist) {
    		
    		System.out.print("employee: " + emp.getFirstName() + " " + emp.getLastName());
    	}
    	
    }
//	
    @Test 
    public void testEmployeeById() {
    	//fail("Not yet implemented");
    	final Employee emp = employeeDao.getEmployeeById(1);
    	assertEquals(1,emp.getEmployeeNumber());
    
    }
    
    
    @Test 
    public void testgetRowCount() {
    	long count = employeeDao.getRowCount();
    	//fail("Not yet implemented");
    	assertEquals(4,count);
    	
    }
    
    
    @Test 
    public void testCreateEmployees() {
    //fail("Not yet implemented");
    	final Employee emp = new Employee();
    	//you do not set id as it is auto generated in entity class
    	emp.setFirstName("firstName");
    	emp.setLastName("lastName");
    	emp.setEmployeeRole("sales");
    	emp.setEmail("anewemail@bwm.com");
    	emp.setPhone("780-988-7762");
    	emp.setStore(storeDao.getStoreByStoreCode(1));
    	emp.setSupervisor(employeeDao.getEmployeeById(1));
    	
    	
    	transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	employeeDao.createEmployee(emp);
		    	//test the auto increment worked. 
		    	assertTrue(emp.getEmployeeNumber()>0);
		    	//test that each property was added successfully
		    	final Employee emp2 = employeeDao.getEmployeeById(emp.getEmployeeNumber());
		    	assertEquals(emp.getFirstName(),emp2.getFirstName());
		    	assertEquals(emp.getLastName(),emp2.getLastName());
		    	assertEquals(emp.getEmployeeRole(),emp2.getEmployeeRole());
		    	assertEquals(emp.getPhone(),emp2.getPhone());
		    	assertEquals(emp.getEmail(),emp2.getEmail());
		    	assertEquals(emp.getStore(),emp2.getStore());
		    	assertEquals(emp.getSupervisor().getEmployeeNumber(),emp2.getSupervisor().getEmployeeNumber());
		    	
		    	status.setRollbackOnly();
		    	
		    }});
    
    }    

    
    @Test 
    public void testUpdateEmployees() {
        	
        	final Employee emp = employeeDao.getEmployeeById(6);
        	
        	emp.setFirstName("Gerry");
        	emp.setLastName("Smith");
        	emp.setEmployeeRole("sales");
        	emp.setEmail("gsmith@bwm.com");
        	emp.setPhone("780-989-7765");
        	emp.setStore(storeDao.getStoreByStoreCode(1));
        	emp.setSupervisor(employeeDao.getEmployeeById(1));
        	
        	
        	transactionTemplate.execute(new TransactionCallbackWithoutResult() {

    		    protected void doInTransactionWithoutResult(TransactionStatus status) {
    		    	employeeDao.updateEmployee(emp);
    		    	//test the auto increment worked. 
    		    	
    		    	//test that each property was added successfully
    		    	final Employee emp2 = employeeDao.getEmployeeById(emp.getEmployeeNumber());
    		    	assertEquals(emp.getFirstName(),emp2.getFirstName());
    		    	assertEquals(emp.getLastName(),emp2.getLastName());
    		    	assertEquals(emp.getEmployeeRole(),emp2.getEmployeeRole());
    		    	assertEquals(emp.getPhone(),emp2.getPhone());
    		    	assertEquals(emp.getEmail(),emp2.getEmail());
    		    	assertEquals(emp.getStore(),emp2.getStore());
    		    	assertEquals(emp.getSupervisor().getEmployeeNumber(),emp2.getSupervisor().getEmployeeNumber());
    		    	status.setRollbackOnly();
    		    	
        }});
     }
	

    
    @Test 
    public void testDeleteEmployees() {
    	final Employee emp = employeeDao.getEmployeeById(6);
    	
    	
    	transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	employeeDao.deleteEmployee(emp);
		    	
		    	assertNull(employeeDao.getEmployeeById(6));
		    	status.setRollbackOnly();
		    	
		    }});
    	
    }
	
	
	
}
