package cn.itcast.oa.base;

import java.util.List;

import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.util.QueryHelper;

/**
 * 功能：Dao层的基类，拥有基本的增删改查功能
 * 
 * @author Taowd
 *
 * @param <T>
 */
public interface DaoSupport<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 按id查询
	 * 
	 * @param id
	 * @return
	 */
	T getById(Long id);

	/**
	 * 按id查询
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 公共的查询分页信息的方法
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param hql
	 *            查询数据列表的HQL
	 * @param parameters
	 *            参数列表，与HQL中问号一一对应
	 * @return
	 */
	PageBean getPageBean(int pageNum, int pageSize, String hql, List<Object> parameters);

	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);

}
