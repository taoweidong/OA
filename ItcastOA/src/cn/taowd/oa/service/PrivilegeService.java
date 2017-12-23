package cn.taowd.oa.service;

import java.util.Collection;
import java.util.List;

import cn.taowd.oa.base.DaoSupport;
import cn.taowd.oa.domain.Privilege;

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
