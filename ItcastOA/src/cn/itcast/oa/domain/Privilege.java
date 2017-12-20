package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限表
 * 
 * @author Taowd
 *
 */
public class Privilege implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6469502344433581587L;
	/**
	 * 唯一标识
	 */
	private Long id;
	/**
	 * 角色信息
	 */
	private Set<Role> roles = new HashSet<Role>();
	/**
	 * URL地址
	 */
	private String url;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父权限
	 */
	private Privilege parent;
	/**
	 * 子权限
	 */
	private Set<Privilege> children = new HashSet<Privilege>();

	public Privilege() {
		super();
	}

	public Privilege(String name, String url, Privilege parent) {
		this.name = name;
		this.url = url;
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Set<Privilege> getChildren() {
		return children;
	}

	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}

}
