package classicPayModel.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.Product;
import entity.ProductLine;

public class ProductDao extends HibernateDaoSupport{
	
	public List<Product> getAllProducts(){
		return getHibernateTemplate().loadAll(Product.class);
	}
	
	public Product getProductByProductCode(int productCode) {
		return (Product) getHibernateTemplate().get(Product.class, productCode);
	}
	
	public void addProduct(Product product) {
		getHibernateTemplate().save(product);
	}
	
	public void updateProduct(Product product) {
		getHibernateTemplate().update(product);
	}
	public void deleteProduct(Product product) {
		getHibernateTemplate().delete(product);
	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Product> findProductsByAll(java.util.Hashtable<String,String> searchTable){
//		
//		List<Product> productList;
//		
//		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
//		java.util.Enumeration<String> e = searchTable.keys();
//		while(e.hasMoreElements()) {
//			String fieldName = e.nextElement();
//			String fieldValue = searchTable.get(fieldName);
//			if( fieldName.equalsIgnoreCase("quantityInStock")) {
//				criteria.add(Restrictions.sqlRestriction(fieldName + " like ?", "%"+fieldValue+"%", Hibernate.STRING));
//			}else {
//				criteria.add(Restrictions.like(fieldName, "%"+fieldValue+"%"));
//			}
//		}
//		criteria.addOrder(Order.asc("productLine.productLine"));
//		criteria.addOrder(Order.asc("productName"));
//		productList = getHibernateTemplate().findByCriteria(criteria);
//		return productList;
//		
//	}
//	
//	public List<Product> findProductsByAny(java.util.Hashtable<String,String> searchTable){
//		
//		List<Product> productList;
//		Disjunction disjunction = Restrictions.disjunction();
//		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
//		java.util.Enumeration<String> e = searchTable.keys();
//		while(e.hasMoreElements()) {
//			String fieldName = e.nextElement();
//			String fieldValue = searchTable.get(fieldName);
//			disjunction.add(Restrictions.like(fieldName, fieldValue, MatchMode.ANYWHERE));
//		}
//		
//		criteria.add(disjunction);
//		criteria.addOrder(Order.asc("productLine.productLine"));
//		criteria.addOrder(Order.asc("productName"));
//		productList = getHibernateTemplate().findByCriteria(criteria);
//		return productList;
//		
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Product> findProductsByAll(String productCode, 
//		String productName, String productLine, String productScale,
//		String productVendor, String productDescription){
//	
//	List<Product> productList;
//	
//	DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
//	
//	criteria.add(Restrictions.like("productCode", productCode, MatchMode.ANYWHERE));
//	criteria.add(Restrictions.like("productName", productName, MatchMode.ANYWHERE));
//	criteria.add(Restrictions.like("productLine.productLine", productLine, MatchMode.ANYWHERE));
//	criteria.add(Restrictions.like("productScale", productScale, MatchMode.ANYWHERE));
//	criteria.add(Restrictions.like("productVendor", productVendor, MatchMode.ANYWHERE));
//	criteria.add(Restrictions.like("productDescription", productDescription, MatchMode.ANYWHERE));
//	
//	criteria.addOrder(Order.asc("productLine.productLine"));
//	criteria.addOrder(Order.asc("productName"));
//	productList = getHibernateTemplate().findByCriteria(criteria);
//	return productList;
//	
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Product> findProductsByAny(String searchValue){
//		List<Product> productList;
//		
//		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
//		criteria.add(  Restrictions.disjunction()
//				.add(Restrictions.like("productCode", searchValue, MatchMode.ANYWHERE))
//				.add(Restrictions.like("productName", searchValue, MatchMode.ANYWHERE))
//				.add(Restrictions.like("productLine.productLine", searchValue, MatchMode.ANYWHERE))
//				.add(Restrictions.like("productScale", searchValue, MatchMode.ANYWHERE))
//				.add(Restrictions.like("productVendor", searchValue, MatchMode.ANYWHERE))
//				.add(Restrictions.like("productDescription", searchValue, MatchMode.ANYWHERE))
//		);
//		
//		criteria.addOrder(Order.asc("productLine.productLine"));
//		criteria.addOrder(Order.asc("productName"));
//		productList = getHibernateTemplate().findByCriteria(criteria);
//		
//		
//		return productList;
//		
//	}
//	
	
}
