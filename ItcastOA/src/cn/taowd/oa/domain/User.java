package cn.taowd.oa.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

/**
 * 功能：用户信息表
 * 
 * @author Taowd
 *
 */
@SuppressWarnings("unchecked")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3027505287108642412L;
	private Long id;
	private String loginName;
	private String password;
	private String name;
	private String gender;
	private String phoneNumber;
	private String email;
	private String description;

	private Department department;

	/**
	 * 岗位集合(角色权限)
	 */
	private Set<Role> roles = new HashSet<Role>();

	/**
	 * 判断给定用户角色用户是否有权限
	 * 
	 * @return
	 */
	public boolean hasPrivilegeByName(String name) {

		if (isAdmin()) {
			return true;
		}

		for (Role role : roles) {
			for (Privilege priv : role.getPrivileges()) {
				if (priv.getName().equals(name)) {
					return true;
				}
			}
		}

		return false;

	}

	/**
	 * 判断用户是否拥有指定Url地址的权限
	 * 
	 * @param priviUrl
	 *            权限Url地址
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String priviUrl) {

		if (isAdmin()) {
			return true;
		}

		// --->去掉后面的参数
		int pos = priviUrl.indexOf("?");
		if (pos > -1) {
			priviUrl = priviUrl.substring(0, pos);
		}
		// --->去掉UI后缀
		if (priviUrl.endsWith("UI")) {
			priviUrl = priviUrl.substring(0, priviUrl.length() - 2);
		}

		// 获取数据库中存储的需要控制的所有URL地址
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication()
				.get("allPrivilegeUrls");
		// 如果当前访问的URL地址不在控制范围之内，则直接让其访问
		if (!allPrivilegeUrls.contains(priviUrl)) {
			return true;
		} else {
			for (Role role : roles) {
				for (Privilege priv : role.getPrivileges()) {
					if ((priviUrl).equals(priv.getUrl())) {
						return true;
					}
				}
			}
		}

		return false;

	}

	/**
	 * 判断用户是否为管理员
	 * 
	 * @param name
	 * @return
	 */
	private boolean isAdmin() {
		if ("admin".equals(loginName)) {
			return true;
		}
		return false;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User() {
		super();
	}

	public User(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
