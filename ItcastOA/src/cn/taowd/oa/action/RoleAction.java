package cn.taowd.oa.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.taowd.oa.base.BaseAction;
import cn.taowd.oa.domain.Privilege;
import cn.taowd.oa.domain.Role;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long[] privilegeIds;

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	/**
	 * 
	 * Taowd TODO 列表 2017-11-13-下午8:03:10
	 */
	public String list() {

		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);// 放在值栈中

		return "list";
	}

	/**
	 * 
	 * Taowd TODO 删除功能 2017-11-14-上午8:41:30
	 */
	public String delete() {
		roleService.delete(model.getId());
		return "toList";
	}

	/**
	 * 
	 * Taowd TODO 添加 2017-11-14-下午12:59:48
	 */
	public String add() {

		roleService.save(model);

		return "toList";
	}

	/**
	 * 
	 * Taowd TODO 添加页面 2017-11-14-下午2:04:40
	 */
	public String addUI() {

		return "SaveUI";
	}

	/**
	 * 
	 * Taowd TODO 修改页面 2017-11-14-下午12:59:33
	 */
	public String editUI() {

		Role role = roleService.getById(model.getId());

		ActionContext.getContext().getValueStack().push(role);

		return "SaveUI";
	}

	/**
	 * 
	 * Taowd TODO 修改 2017-11-14-下午12:59:20
	 */
	public String edit() {
		// 1、从数据库中获取
		Role role = roleService.getById(model.getId());
		// 2、设置新值
		role.setName(model.getName());
		role.setDescription(model.getDescription());

		System.out.println(role.toString());
		// 3、更新数据库
		roleService.update(role);
		return "toList";

	}

	/**
	 * 设置权限页面
	 * 
	 * @return
	 */
	public String setPrivilegeUI() {
		// 1、准备回显数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);

		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for (Privilege var : role.getPrivileges()) {
				privilegeIds[index++] = var.getId();
			}
		}
		// 设置回显数据 所有的权限数据
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);

		return "setPrivilegeUI";

	}

	/**
	 * 设置权限
	 * 
	 * @return
	 */
	public String setPrivilege() {
		// 1、从数据库中获取
		Role role = roleService.getById(model.getId());
		// 2、设置新值
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);

		role.setPrivileges(new HashSet<Privilege>(privilegeList));

		// 3、更新数据库
		roleService.update(role);
		return "toList";

	}

}
