package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.ForumManageService;
import cn.itcast.oa.service.PrivilegeService;
import cn.itcast.oa.service.ReplyService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.TopicService;
import cn.itcast.oa.service.UserService;

@SuppressWarnings("unchecked")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	protected static final Logger logger = LoggerFactory.getLogger(BaseAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ========ModelDriven的封装支持===========
	protected T model;

	public BaseAction() {
		try {
			// 通过反射获得Model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 通过反射创建Model的实例
	}

	public T getModel() {

		return model;
	}

	/**
	 * 从Session中获取当前登录用户;
	 * 
	 * @return 当前登录用户
	 */
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

	// ==========分页数据准备
	protected int pageNum = 1;
	protected int pageSize = 10;

	// =========Service实例的声明===============

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected RoleService roleService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected ForumManageService forumManageService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;
}
