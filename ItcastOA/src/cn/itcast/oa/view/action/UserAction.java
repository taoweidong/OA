package cn.itcast.oa.view.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.DepartmentUtils;
import cn.itcast.oa.util.MD5Util;
import cn.itcast.oa.util.QueryHelper;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 部门ID
	 */
	private Long departmentId;
	/**
	 * 岗位ID
	 */
	private Long[] roleIds;

	/** 列表 */
	public String list() throws Exception {
		// List<User> userList = userService.findAll();
		// ActionContext.getContext().put("userList", userList);// 把数据放在值栈中，便于页面展示
		new QueryHelper(User.class, "u")//
				.preparePageBean(userService, pageNum, pageSize);

		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		userService.delete(model.getId());

		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备树状部门列表
		List<Department> departmentList = new ArrayList<Department>();
		departmentList = DepartmentUtils.getAllDepartments(departmentService.findTopList());
		// 把数据放在值栈中的Map中
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备岗位数据
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 封装到对象中（当model为实体类型时，也可以直接使用model，但是要封装未设值的属性）
		// 设置部门
		model.setDepartment(departmentService.getById(departmentId));
		// 设置岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));// 注意此处类型转换

		// 设置密码
		model.setPassword(MD5Util.encrypt("123456"));

		userService.save(model);

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备树状部门列表
		List<Department> departmentList = new ArrayList<Department>();
		departmentList = DepartmentUtils.getAllDepartments(departmentService.findTopList());
		// 把数据放在值栈中的Map中
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备岗位数据
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);// 放置在值栈的栈顶

		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();// 回显部门
		}

		if (user.getRoles() != null) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}

		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库取原值
		User user = userService.getById(model.getId());

		// 2、赋新数据
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());

		// 设置部门
		user.setDepartment(departmentService.getById(departmentId));
		// 设置岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));// 注意此处类型转换

		// 3、保存到数据库
		userService.update(user);

		return "toList";
	}

	/**
	 * 初始化密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initPassword() throws Exception {

		// 1、从数据库取原值
		User user = userService.getById(model.getId());

		// 2、赋新数据
		user.setPassword(MD5Util.encrypt("123456"));

		// 3、保存到数据库
		userService.update(user);

		return "toList";
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loginUI() throws Exception {

		return "loginUI";
	}

	/**
	 * 登录功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {

		User user = userService.findByLoginNameAndPassword(model.getLoginName(), model.getPassword());
		if (user == null) {

			addFieldError("login", "用户名或密码错误！");
			return "loginUI";// 转到登录页面

		} else {
			// 登录用户
			ActionContext.getContext().getSession().put("user", user);// 把用户信息存储到Session中去
			return "toIndex";

		}

	}

	/**
	 * 注销页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");// 把用户信息从Session中删除掉
		return "logout";
	}

	// -------------封装属性
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}
