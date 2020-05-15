package classicPayModel.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.ProductLine;

public class ProductLineDao extends HibernateDaoSupport{

	//getProductLines
	public List<ProductLine> getAllProductLines(){
		return getHibernateTemplate().loadAll(ProductLine.class);
	}
	
	
	
	//getProductLineByProductLine
	public ProductLine getProductLineByProductLine(int productLinePK) {
		
		return (ProductLine) getHibernateTemplate().get(ProductLine.class, productLinePK);
	}
	
	
	
}
