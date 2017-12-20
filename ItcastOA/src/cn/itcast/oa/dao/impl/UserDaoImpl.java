package cn.itcast.oa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.dao.UserDao;
import cn.itcast.oa.domain.User;

@Deprecated
@Repository
public class UserDaoImpl extends DaoSupportImpl<User> implements UserDao {

}
