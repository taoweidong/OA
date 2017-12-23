package cn.taowd.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import cn.taowd.oa.domain.PageBean;
import cn.taowd.oa.util.QueryHelper;

/**
 * 
 * @author Taowd 功能：Dao层的基础功能实现，基本的增删改查功能
 * @param <T>
 */
@SuppressWarnings("unchecked")
// 注意如果此处不进行事务管理，会出现增和查询成功，删除和修改无任何作用的问题，此处需注意
// 子类可以继承事务管理
@Transactional
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	public DaoSupportImpl() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		// System.out.println("获取到BaseDao层的反射类型：" + clazz);

	}

	/**
	 * 获取当前可用的Session 方便子类获取session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 保存实体
	 */
	public void save(T entity) {
		getSession().save(entity);
	}

	/**
	 * 更新实体
	 */
	public void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * 删除实体
	 */
	public void delete(Long id) {
		Object obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	/**
	 * 根据Id查询实体
	 */
	public T getById(Long id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}

	}

	/**
	 * 根据Id(多个)查询对应的实体(多个)
	 */
	public List<T> getByIds(Long[] ids) {

		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;// 返回一个空的数组，放置外部调用时 出现空指针异常
		} else {
			return getSession().createQuery(//
					"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
					.setParameterList("ids", ids)//
					.list();
		}
	}

	/**
	 * 查询所有数据
	 */
	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName())//
				.list();
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean getPageBean(int pageNum, int pageSize, String hql, List<Object> parameters) {
		// 查询满足条件的列表
		Query query = getSession().createQuery(hql);//
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);
		List list = query.list();

		// 查询总数
		Query countQuery = getSession().createQuery("SELECT count(*) " + hql);
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) countQuery.uniqueResult();

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

	@Override
	public PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper) {
		System.out.println("-------> DaoSupportImpl.getPageBean( int pageNum, int pageSize, QueryHelper queryHelper )");

		// 参数列表
		List<Object> parameters = queryHelper.getParameters();

		// 查询本页的数据列表
		Query listQuery = getSession().createQuery(queryHelper.getListQueryHql()); // 创建查询对象
		if (parameters != null) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list(); // 执行查询

		// 查询总记录数量
		Query countQuery = getSession().createQuery(queryHelper.getCountQueryHql());
		if (parameters != null) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) countQuery.uniqueResult(); // 执行查询

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

}
