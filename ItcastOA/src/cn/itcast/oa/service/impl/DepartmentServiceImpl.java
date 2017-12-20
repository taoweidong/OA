package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements
		DepartmentService {

	// @Resource
	// private DepartmentDaoImpl departmentDao;

	@Resource
	private SessionFactory sessionFactory;

//	public List<Department> findAll() {
//		return departmentDao.findAll();
//	}
//
//	public void deleteDepartment(Long id) {
//		departmentDao.delete(id);
//	}
//
//	public void save(Department department) {
//		departmentDao.save(department);
//	}
//
//	public void update(Department department) {
//		departmentDao.update(department);
//
//	}
//
//	public Department getDepartmentById(Long id) {
//
//		return departmentDao.getById(id);
//	}

	/**
	 * 查询所有顶级菜单
	 */
	public List<Department> findTopList() {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d WHERE d.parent IS NULL").list();
	}

	/**
	 * 查询所有子菜单
	 */
	public List<Department> findChildren(Long parentId) {

		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d WHERE d.parent.id=?")
				.setParameter(0, parentId).list();
	}

}
