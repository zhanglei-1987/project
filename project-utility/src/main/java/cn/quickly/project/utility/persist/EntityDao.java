package cn.quickly.project.utility.persist;

import java.util.List;

public interface EntityDao {

	/**
	 * 
	 * 查找主键为id的Entity对象，如果不存在返回null，如果有多条返回第一条
	 * 
	 * (可选实现支持延迟加载，需要LazyParam)
	 * 
	 * (可选实现支持锁机制，需要LockParam)
	 * 
	 * @param entityClass
	 * @param argument
	 * @return
	 * @throws DaoException
	 */
	public <T> T query(Class<T> entityClass, Object argument) throws DaoException;

	/**
	 * 
	 * 查找主键为id的Entity对象，如果不存在或者有多条抛出异常
	 * 
	 * (可选实现支持延迟加载，需要LazyParam)
	 * 
	 * (可选实现支持锁机制，需要LockParam)
	 * 
	 * @param entityClass
	 * @param argument
	 * @return
	 * @throws DaoException
	 */
	public <T> T fetch(Class<T> entityClass, Object argument) throws DaoException;

	/**
	 * 
	 * 获得Entity的集合，出于性能考虑，实现时最好给定一个最大值
	 * 
	 * (可选实现支持延迟加载，需要LazyParam)
	 * 
	 * (可选实现支持锁机制，需要LockParam)
	 * 
	 * @param entityClass
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> list(Class<T> entityClass, Object argument) throws DaoException;

	/**
	 * 
	 * 获得Entity开始偏移start到结束偏移end的集合
	 * 
	 * (可选实现支持延迟加载，需要LazyParam)
	 * 
	 * (可选实现支持锁机制，需要LockParam)
	 * 
	 * @param entityClass
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> list(Class<T> entityClass, Object argument, int start, int end) throws DaoException;

	/**
	 * 获得Entity的个数
	 * 
	 * @param entityClass
	 * @return
	 * @throws DaoException
	 */
	public <T> long count(Class<T> entityClass, Object argument) throws DaoException;

	/**
	 * 
	 * 删除实体对象
	 * 
	 * @param entity
	 * @throws DaoException
	 */
	public <T> T delete(Object entity) throws DaoException;

	/**
	 * 插入实体对象
	 * 
	 * (可选实现支持merage功能，需要MerageParam)
	 * 
	 * @param entity
	 * @throws DaoException
	 */
	public <T> T insert(Object entity) throws DaoException;

	/**
	 * 删除实体对象
	 * 
	 * @param entity
	 * @throws DaoException
	 */
	public <T> T update(Object entity) throws DaoException;

}
