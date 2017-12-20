package cn.itcast.oa.service;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.User;

/**
 * 用户管理的服务类
 * 
 * @author Taowd
 *
 */
public interface UserService extends DaoSupport<User> {

	/**
	 * 验证账户和密码
	 * 
	 * @param loginName
	 *            账户
	 * @param password
	 *            密码
	 * @return 查询到的用户信息
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
