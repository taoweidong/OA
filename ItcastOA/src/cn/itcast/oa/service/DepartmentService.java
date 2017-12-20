package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department> {

	// /**
	// *
	// * Taowd
	// * TODO 查询所有部门信息
	// * 2017-11-23-上午8:47:49
	// */
	// List<Department> findAll();
	//
	// /**
	// *
	// * Taowd
	// * TODO 删除部门
	// * 2017-11-23-上午8:48:05
	// */
	// void deleteDepartment(Long id);
	//
	// /**
	// *
	// * Taowd
	// * TODO 保存信息
	// * 2017-11-23-上午8:48:31
	// */
	// void save(Department department);
	//
	// /**
	// *
	// * Taowd
	// * TODO 查询部门信息
	// * 2017-11-23-上午8:49:03
	// */
	// Department getDepartmentById(Long id);
	//
	// /**
	// *
	// * Taowd
	// * TODO 更新部门信息
	// * 2017-11-23-上午8:49:48
	// */
	// void update(Department department);
	
	 /**
	 * 功能：查询所有的顶级菜单
	 * @return
	 */
	 List<Department> findTopList();
	
	 /**
	 * 功能：查询所有的子菜单
	 * @param parentId
	 * @return
	 */
	 List<Department> findChildren(Long parentId);

}
