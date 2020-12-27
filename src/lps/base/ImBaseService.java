package lps.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * Service
 * 
 * @author guild
 * 
 */
public abstract class ImBaseService<T> implements BaseService<T> {

	protected BaseDao<T> baseDao;

	@Resource
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	public int count() {
		return baseDao.count();
	}

	public int count(String hql, Object... objects) {
		return baseDao.count(hql, objects);
	}

	public void delete(int id) {
		baseDao.delete(id);
	}

	public void delete(T entity) {
		baseDao.delete(entity);
	}

	public void delete(List<T> entities) {
		baseDao.delete(entities);
	}

	public void delete(String hql, Object... objects) {
		baseDao.delete(hql, objects);
	}

	public T get(int id) {
		return baseDao.get(id);
	}

	public List<T> get(int[] ids) {
		return baseDao.get(ids);
	}

	public T load(int id) {
		return baseDao.load(id);
	}

	public List<Object[]> iterate(String hql, Object... objects) {
		return baseDao.iterate(hql, objects);
	}

	public List<T> query(String hql, Object... objects) {
		return baseDao.query(hql, objects);
	}

	public List<T> query(int page, int pageSize, String hql, Object... objects) {
		return baseDao.query(page, pageSize, hql, objects);
	}

	public T queryUnique(String hql, Object... objects) {
		return baseDao.queryUnique(hql, objects);
	}

	public void save(T entity) {
		baseDao.save(entity);
	}

	public void save(List<T> entities) {
		baseDao.save(entities);
	}

	public void saveOrUpdate(T entity) {
		baseDao.saveOrUpdate(entity);
	}

	
	public void saveOrUpdate(List<T> entities) {
		baseDao.saveOrUpdate(entities);
	}
	
	
	public void update(T entity) {
		baseDao.update(entity);
	}

	public void update(List<T> entities) {
		baseDao.update(entities);
	}

	public void executeUpdate(String hql, Map<String, Object> params) {
		baseDao.executeUpdate(hql, params);
	}

	public void executeUpdate(String hql, Object... objects) {
		baseDao.executeUpdate(hql, objects);
	}

}