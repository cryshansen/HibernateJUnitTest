package classicPayModel.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import classicPayModel.entity.Customer;
import classicPayModel.entity.Order;
public class OrderDao extends HibernateDaoSupport {
	
	public List<Order> getAllOrders(){
		return getHibernateTemplate().loadAll(Order.class);
	}

	public Order getOrderByOrderNumber(int orderNumber) {
		return (Order) getHibernateTemplate().get(Order.class, orderNumber);
		
	}
	
	//find orders by customer name
	public List<Order> findOrdersByCustomerName(Customer cust) {
		//TODO:
		DetachedCriteria criteria = DetachedCriteria.forClass(Order.class);
		criteria.add(Restrictions.like("clientName", cust.getClientName(), MatchMode.ANYWHERE));
		criteria.createCriteria("customer").add(Restrictions.sqlRestriction("customerNumber like ? ","%" + cust.getCustomerNumber() + "%", Hibernate.STRING ) );
		
		
		return getHibernateTemplate().findByCriteria(criteria);
	}
	//find orders by customer number
	
	
	
	
	
	
	public void createOrder(Order order) {
		getHibernateTemplate().save(order);
		
	}
	public void updateOrder(Order order) {
		getHibernateTemplate().update(order);
	}
	public void deleteOrder(Order order) {
		getHibernateTemplate().delete(order);
	}
	
	
	//rowcount
	public Long getRowCount() {
		Session dbSession = getSession();
		Query dbQuery= dbSession.createQuery(
			"select count(*) from classicPayModel.entity.Order");
		Long count = (Long) dbQuery.uniqueResult();
		dbSession.close();
		return count;
	}
	
}
