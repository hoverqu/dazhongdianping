package lps.base;

import java.util.List;
import java.util.Map;

import lps.entities.DazhongCaterDissSummary;
import lps.entities.ElmCaterFirm;

/**
 * Service
 * 
 * @author guild
 * 
 */
public interface BaseService<T> {

	public abstract int count();

	public abstract int count(String hql, Object... objects);

	public abstract void save(T dis);

	public abstract void save(List<T> entities);

	public abstract void delete(int id);

	public abstract void delete(T entity);

	public abstract void delete(String hql, Object... objects);

	public abstract void delete(List<T> entities);

	public abstract void update(T entity);

	public abstract void update(List<T> entities);

	public abstract void saveOrUpdate(List<T> entities);	
	public abstract void saveOrUpdate(T entities);

	

	public abstract T get(int id);

	public abstract List<T> get(int[] ids);

	public abstract T load(int id);

	public abstract List<Object[]> iterate(String hql, Object... objects);

	public abstract T queryUnique(String hql, Object... objects);

	public abstract List<T> query(String hql, Object... objects);

	public abstract List<T> query(int page, int pageSize, String hql, Object... objects);
	
	void executeUpdate(String hql, Map<String, Object> params);
	
	void executeUpdate(String hql, Object... objects);

}
