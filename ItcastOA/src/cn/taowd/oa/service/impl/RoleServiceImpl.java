package cn.taowd.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.taowd.oa.base.DaoSupportImpl;
import cn.taowd.oa.domain.Role;
import cn.taowd.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements
		RoleService {

//	@Resource
//	private RoleDao roleDao;

//	public List<Role> findAll() {
//		return roleDao.findAll();
//	}
//
//	public void deleteRole(Long id) {
//		roleDao.delete(id);
//
//	}
//
//	public void save(Role role) {
//		roleDao.save(role);
//
//	}
//
//	public Role getRoleById(Long id) {
//
//		return roleDao.getById(id);
//	}
//
//	public void update(Role role) {
//		roleDao.update(role);
//
//	}

}
