package classicPayModel.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.*;

import entity.Customer;

import java.util.List;


public class CustomerDao extends HibernateDaoSupport{

	public List<Customer> getAllCustomers(){
		return getHibernateTemplate().loadAll(Customer.class);
	}
	public Customer getCustomerByCustomerNumber(int customerNumber) {
		return (Customer) getHibernateTemplate().get(Customer.class, customerNumber);
	}
	
	public void createCustomer(Customer cust) {
		getHibernateTemplate().save(cust);
	}
	public void updateCustomer(Customer cust) {
		getHibernateTemplate().update(cust);
		
	}
	public void deleteCustomer(Customer cust) {
		getHibernateTemplate().delete(cust);
	}
	
	public Long getRowCount() {
		Session dbSession = getSession();
		Query dbQuery= dbSession.createQuery(
			"select count(*) from entity.Customer");
		Long count = (Long) dbQuery.uniqueResult();
		dbSession.close();
		return count;
	}
	
	
	
}
