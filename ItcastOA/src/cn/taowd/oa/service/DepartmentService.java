package cn.taowd.oa.service;

import java.util.List;

import cn.taowd.oa.base.DaoSupport;
import cn.taowd.oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department> {

	/**
	 * 功能：查询所有的顶级菜单
	 * 
	 * @return
	 */
	List<Department> findTopList();

	/**
	 * 功能：查询所有的子菜单
	 * 
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);

}
