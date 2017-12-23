package cn.taowd.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.taowd.oa.base.DaoSupportImpl;
import cn.taowd.oa.domain.User;
import cn.taowd.oa.service.UserService;
import cn.taowd.oa.util.MD5Util;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {

	/**
	 * 验证账户和密码
	 */
	public User findByLoginNameAndPassword(String loginName, String password) {

		String Md5Passwd = MD5Util.encrypt(password);// 密码加密查询
		User user = (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName = ? AND u.password = ?")// 准备查询SQL
				.setParameter(0, loginName)// 设置参数
				.setParameter(1, Md5Passwd)// 设置参数
				.uniqueResult();// 返回唯一结果或者null,返回多个结果报错
		return user;
	}

}
