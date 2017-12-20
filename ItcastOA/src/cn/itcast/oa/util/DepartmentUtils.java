package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.itcast.oa.domain.Department;

public class DepartmentUtils {

	/**
	 * 功能：处理所有的菜单，对它们的名称进行处理
	 * @param findTopList
	 * @return
	 */
	public static List<Department> getAllDepartments(
			List<Department> findTopList) {

		List<Department> list = new ArrayList<Department>();

		walkDepartmentTreeList(findTopList, "┣", list);

		return list;
	}

	private static void walkDepartmentTreeList(Collection<Department> topList,
			String profix, List<Department> list) {
		for (Department top : topList) {
			// 顶点
			Department copy = new Department();// 使用副本,原对象在Session中
			// 用什么就给副本设置什么值
			copy.setId(top.getId());
			copy.setName(profix + top.getName());// 设置新名称
			list.add(copy);// 把副本添加的List中
			// 子树
			walkDepartmentTreeList(top.getChildren(), "　" + profix, list);

		}

	}
}
