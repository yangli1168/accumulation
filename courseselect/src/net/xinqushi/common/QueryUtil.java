package net.xinqushi.common;

import org.hibernate.Query;
import org.hibernate.Session;

public class QueryUtil {
	public static Object getInfo(String hql, Object object){
		Session session = HibernateUtil.openSession();
		Query query = session.createQuery(hql).setProperties(object);
		session.beginTransaction();
		object = null;
		if (query.list().size() > 0) {
			object = query.list().get(0);
		}
		session.getTransaction().commit();
		session.close();
		return object;	
	}
}
