package cn.taowd.oa.util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.taowd.oa.domain.User;

/**
 * 检查权限的拦截器
 * 
 * @author Taowd AbstractInterceptor 继承抽象类 或者 实现 Interceptor
 */
public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	// private static final Logger logger =
	// LoggerFactory.getLogger(CheckPrivilegeInterceptor.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 3123353315475003316L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// // logger.debug("拦截之前-------->");
		// System.out.println("拦截之前-------->");
		// String result = invocation.invoke(); // 方行
		//
		// // logger.debug("拦截之后-------->");
		// System.out.println("拦截之后-------->");
		// return result;

		// 获取当前登录用户的信息
		User user = (User) ActionContext.getContext().getSession().get("user");

		String nameSpace = invocation.getProxy().getNamespace();// 获取当前访问Action中的名称空间
		String actionName = invocation.getProxy().getActionName();// 获取当前访问Action的名称

		String priviliUrl = nameSpace + actionName;

		// 如果未登录，就转到登录页面
		if (user == null) {
			// 如果是准备登录，就放行
			if (priviliUrl.startsWith("/user_login")) {
				return invocation.invoke();
			} else {
				return "loginUI";
			}
		} else {
			// 如果已登录 就判断权限
			if (user.hasPrivilegeByUrl(priviliUrl)) {
				// 如果有权限 放行
				return invocation.invoke();
			} else {
				// 如果无权限 就转到提示页面
				return "noPrivilegeError";
			}

		}

	}

}
