package lps.base;

import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dao
 * 
 * @author guild
 * 
 */
@SuppressWarnings("unchecked")
public abstract class ImBaseDao<T> implements BaseDao<T> {

	private Class<T> clazz;

	@Resource
	protected SessionFactory sessionFactory;

	public ImBaseDao() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	public int count() {
		String hql = "SELECT COUNT(*) FROM " + clazz.getName();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Object obj = query.uniqueResult();
		if (obj == null)
			return 0;
		return (int) ((Long) obj).intValue();
	}

	public int count(String hql, Object... objects) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				if (objects[i] == null)
					continue;
				query.setParameter(i, objects[i]);
			}
		}
		Object obj = query.uniqueResult();
		if (obj == null)
			return 0;
		return (int) ((Long) obj).intValue();
	}

	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		T entity = (T) session.get(clazz, id);
		session.delete(entity);
	}

	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	public void delete(List<T> entities) {
		Session session = sessionFactory.getCurrentSession();
		for (T entity : entities) {
			session.delete(entity);
		}
	}

	public void delete(String hql, Object... objects) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				if (objects[i] == null)
					continue;
				query.setParameter(i, objects[i]);
			}
		}
		List<T> entities = query.list();
		for (T entity : entities) {
			session.delete(entity);
		}
	}

	public T get(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.get(clazz, id);
	}

	public List<T> get(int[] ids) {
		Session session = sessionFactory.getCurrentSession();
		List<T> entities = new ArrayList<T>();
		for (int id : ids) {
			T t = (T) session.get(clazz, id);
			if (t != null)
				entities.add(t);
		}
		return entities;
	}

	public T load(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.load(clazz, id);
	}

	public List<Object[]> iterate(String hql, Object... objects) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				if (objects[i] == null)
					continue;
				query.setParameter(i, objects[i]);
			}
		}
		List<Object[]> list = query.list();
		// Iterator<Object> it = list.iterator();
		return list;
	}

	public List<T> query(String hql, Object... objects) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				if (objects[i] == null)
					continue;
				try {
					Date date = sdf.parse((String) objects[i]);
					query.setDate(i, date);
				} catch (Exception e) {

					query.setParameter(i, objects[i]);
				}
			}
		}

		List<T> entities = query.list();

		return entities;
	}

	public List<T> query(int page, int pageSize, String hql, Object... objects) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				if (objects[i] == null)
					continue;
				try {
					Date date = sdf.parse((String) objects[i]);
					query.setDate(i, date);
				} catch (Exception e) {
					query.setParameter(i, objects[i]);
				}
			}
		}
		int firstResult = (page - 1) * pageSize;
		firstResult = firstResult < 0 ? 0 : firstResult;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<T> entities = query.list();
		return entities;
	}

	public T queryUnique(String hql, Object... objects) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				if (objects[i] == null)
					continue;
				query.setParameter(i, objects[i]);
			}
		}
		List<T> entities = query.list();
		if (entities == null || entities.size() == 0)
			return null;
		return entities.get(0);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)  
	public void save(T entity) {
		Session session = sessionFactory.getCurrentSession();
        //Transaction tx = session.beginTransaction();  
try {
	session.save(entity);
	// tx.commit(); 
} catch (Exception e) {
	e.printStackTrace();
	//session.flush();
	///session.close();
	// tx.rollback();  
	// TODO: handle exception
}
        

		
		
		
	}

	
	public void save(List<T> entities) {
		Session session = sessionFactory.getCurrentSession();

		for (T entity : entities) {
	      //  Transaction tx = session.beginTransaction();  

			try {

				session.save(entity);
				// tx.commit(); 

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("aaaaaaaaaaaaaaaaaa");
				// tx.rollback();  

				// TODO: handle exception
			}

			
		}
	}

	
	
	
    public void savetx(List<T> entities) {  
        Session session = sessionFactory.getCurrentSession();  
        //执行事务  
        Transaction tx = session.beginTransaction();  
        for (T entity : entities) {
			try {
			
				session.save(entity);
			} catch (Exception e) {
				System.out.println("---");
				
				// TODO: handle exception
			}
			
		}
	
            //sql作用:忽略相同数据  
           // session.createSQLQuery(  
                  //  "insert ignore into test values(" + i + ",'a')")  
                  //  .executeUpdate();//注意,插入要加上executeUpdate,否则插入不成功  
              //提交事务  
        tx.commit();  
  
    } 
	
	
	
	
	public void saveOrUpdate(List<T> entities) {
		Session session = sessionFactory.getCurrentSession();
		for (T entity : entities) {
			session.saveOrUpdate(entity);
		}
	}

	
	
	public void saveOrUpdate(T entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	public void update(T entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	public void update(List<T> entities) {
		Session session = sessionFactory.getCurrentSession();
		for (T entity : entities) {
			session.update(entity);
		}
	}

	public void executeUpdate(String hql, Map<String, Object> params) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (Entry<String, Object> entry : params.entrySet()) {
				if (entry.getValue() instanceof Collection) {
					query.setParameterList(entry.getKey(), (List<?>) entry.getValue());
				} else {
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
		query.executeUpdate();
	}

	public void executeUpdate(String hql, Object... objects) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				if (objects[i] == null)
					continue;
				query.setParameter(i, objects[i]);
			}
		}
		query.executeUpdate();
	}

}