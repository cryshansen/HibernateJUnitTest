package classicPayModel.dao;


import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import classicPayModel.entity.Customer;
import classicPayModel.entity.Payment;
import classicPayModel.entity.PaymentPk;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Hibernate;
import org.hibernate.Query;



public class PaymentDao extends HibernateDaoSupport{

	@SuppressWarnings("unchecked")
	public List<Payment> getAllPayments(){
		return getHibernateTemplate().loadAll(Payment.class);
	}
//	public Payment getPaymentByPaymentPk(PaymentPk paymt) {
//		return (Payment) getHibernateTemplate().get(Payment.class, paymt);//primarykey
//	}
	
	//find payment by customer currently returns a full listing not a single payment
	@SuppressWarnings("unchecked")
	public List<Payment> findPaymentsByCustomer(Customer customer) {
		return getHibernateTemplate().find("from classicPayModel.entity.Payment where id.customer.customerNumber = ?", customer.getCustomerNumber());
	}
	
	public Payment getPaymentByPaymentId(int pid) {
		return (Payment) getHibernateTemplate().get(Payment.class, pid);
	}
	
	//find payment by customerName CheckNo
	@SuppressWarnings("unchecked")
	public List<Payment> findPaymentByCustomerNameCheque(String customerName,String checkNumber) {
		
		return getHibernateTemplate().find(
				"from classicPayModel.entity.Payment where id.customer.customerName like ? and id.checkNumber like ?" , 
				new Object[] {"%" + customerName + "%" , "%"+ checkNumber+"%"}
				);
	}
	//find payment by customerNumber CheckNo
		@SuppressWarnings("unchecked")
		public List<Payment> findPaymentsByCustomerNumberCheque(String customerNumber,String checkNumber) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Payment.class);
			criteria.add(Restrictions.like("checkNumber", checkNumber, MatchMode.ANYWHERE) );
			criteria.createCriteria("customer").add(Restrictions.sqlRestriction("customerNumber like ? ","%" + customerNumber + "%", Hibernate.STRING));
			
			return getHibernateTemplate().findByCriteria(criteria);
					
		}
	
	
	public void createPayment(Payment pay) {
		getHibernateTemplate().save(pay);
	}
	public void updatePayment(Payment pay) {
		getHibernateTemplate().update(pay);
	}
	public void deletePayment(Payment pay) {
		getHibernateTemplate().delete(pay);
	}
	
	
	public Long getRowCount() {
		Session dbSession = getSession();
		Query dbQuery= dbSession.createQuery(
			"select count(paymentDate) from classicPayModel.entity.Payment");
		Long count = (Long) dbQuery.uniqueResult();
		dbSession.close();
		return count;
	}
	
	
	
}
