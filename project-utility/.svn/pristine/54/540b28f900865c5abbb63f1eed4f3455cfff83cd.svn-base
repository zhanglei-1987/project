package cn.quickly.project.utility.persist;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface RelationDao {
	/**
	 * 查询
	 * 
	 * 
	 * (可选实现支持延迟加载，需要LazyParam)
	 * 
	 * (可选实现支持锁机制，需要LockParam)
	 * 
	 * @param key
	 *            该操作关键字
	 * @param argument
	 *            查询参数
	 * @return 没有记录返回null，多条记录抛出异常
	 * 
	 * @throws DaoException
	 */
	public <T> T query(String key, Object argument) throws DaoException;

	/**
	 * 查询
	 * 
	 * (可选实现支持延迟加载，需要LazyParam)
	 * 
	 * (可选实现支持锁机制，需要LockParam)
	 * 
	 * @param key
	 *            该操作关键字
	 * @param argument
	 *            查询参数
	 * @return
	 * 
	 * @throws DaoException
	 *             多条记录或者没有记录抛出异常
	 */
	public <T> T fetch(String key, Object argument) throws DaoException;

	/**
	 * 统计结果集大小
	 * 
	 * @param key
	 * @param argument
	 * @return
	 * @throws DaoException
	 */
	public long count(String key, Object argument) throws DaoException;

	/**
	 * 返回一个查询集合，为了防止记录太多，Dao对象最好设置一个默认结果集条数
	 * 
	 * (可选实现支持延迟加载，需要LazyParam)
	 * 
	 * (可选实现支持锁机制，需要LockParam)
	 * 
	 * @param key
	 * @param argument
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> list(String key, Object argument) throws DaoException;

	/**
	 * 返回指定的结果集
	 * 
	 * (可选实现支持延迟加载，需要LazyParam)
	 * 
	 * (可选实现支持锁机制，需要LockParam)
	 * 
	 * @param key
	 * @param argument
	 * @param start
	 * @param end
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> list(String key, Object argument, int start, int end) throws DaoException;

	/**
	 * 更新
	 * 
	 * @param key
	 *            该操作关键字
	 * @param argument
	 *            更新参数
	 * @throws DaoException
	 */
	public <T> T update(String key, Object argument) throws DaoException;

	/**
	 * 更新
	 * 
	 * @param key
	 *            该操作关键字
	 * @param argument
	 *            更新参数
	 * @throws DaoException
	 */
	public <T> T delete(String key, Object argument) throws DaoException;

	/**
	 * 插入,如果已存在报错
	 * 
	 * @param key
	 *            该操作关键字
	 * @param argument
	 *            插入参数
	 * @throws DaoException
	 */
	public <T> T insert(String key, Object argument) throws DaoException;

	/**
	 * 批处理
	 * 
	 * @param key
	 * @param collection
	 * @return
	 * @throws DaoException
	 */
	public <T> T batch(String key, Collection<?> collection) throws DaoException;

	/**
	 * 调用过程, 该方法不返回结果，如果需要返回值，可放入到参数map中
	 * 
	 * 该方法所调用的可以是存储过程，也可以是native实现的数据操作等等
	 * 
	 * @param key
	 * @param map
	 * @throws DaoException
	 */
	public void call(String key, Map<String, Object> map) throws DaoException;

}
