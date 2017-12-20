package cn.itcast.oa.service;

import java.util.Collection;
import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege> {

	/**
	 * 获取顶级菜单
	 * 
	 * @return
	 */
	List<Privilege> findTopMenu();

	/**
	 * 获取数据库中所有Url地址
	 * 
	 * @return
	 */
	Collection<String> finAllPrivilegeUrls();

}
