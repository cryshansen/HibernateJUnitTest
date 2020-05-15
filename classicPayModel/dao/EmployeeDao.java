package classicPayModel.dao;




import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.Employee;
public class EmployeeDao extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees(){
		return  getHibernateTemplate().loadAll(Employee.class);
	}
	public Employee getEmployeeById(int employeeNumber) {
		return (Employee) getHibernateTemplate().get(Employee.class, employeeNumber);
	}
	
	public void createEmployee( Employee employee) {
		getHibernateTemplate().save(employee);
	}
	public void updateEmployee( Employee employee) {
		getHibernateTemplate().update(employee);
	}
	public void deleteEmployee( Employee employee) {
		getHibernateTemplate().delete(employee);
	}
	public Long getRowCount() {
		Session dbSession = getSession();
		Query dbQuery= dbSession.createQuery(
			"select count(*) from entity.Employee");
		Long count = (Long) dbQuery.uniqueResult();
		dbSession.close();
		return count;
	}
	
	
	
}
