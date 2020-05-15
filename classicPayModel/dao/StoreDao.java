package classicPayModel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.Store;

public class StoreDao extends HibernateDaoSupport{

	public List<Store> getAllStores(){
		return getHibernateTemplate().loadAll(Store.class);
	}
	
	public Store getStoreByStoreCode(int storeCode) {
		
		return (Store) getHibernateTemplate().get(Store.class, storeCode);
		
	}
	
	public void createStore(Store store) { getHibernateTemplate().save(store);}
	public void updateStore(Store store) { getHibernateTemplate().update(store);}
	public void deleteStore(Store store) { getHibernateTemplate().delete(store);}
	
	
	public Long getRowCount() {
		Session dbSession = getSession();
		Query dbQuery= dbSession.createQuery(
			"select count(storecode) from entity.Store");
		Long count = (Long) dbQuery.uniqueResult();
		dbSession.close();
		return count;
	}
}
