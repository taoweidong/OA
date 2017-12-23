package cn.taowd.oa.util;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.taowd.oa.domain.Privilege;
import cn.taowd.oa.service.PrivilegeService;

/**
 * 程序启动时的监听器 获取程序不变的数据</br>
 * 
 * <p>
 * 加载程序中默认数据，每次启动时只加载一次
 * </p>
 * 
 * @author Taowd
 *
 */
public class InitListener implements ServletContextListener {

	/**
	 * 销毁
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 初始化
	 */
	public void contextInitialized(ServletContextEvent sce) {

		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");

		List<Privilege> topPrivilegeList = privilegeService.findTopMenu();
		sce.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("------->数据准备完毕topPrivilegeList<---------！");

		Collection<String> allPrivilegeUrls = privilegeService.finAllPrivilegeUrls();
		sce.getServletContext().setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		System.out.println("------->数据准备完毕allPrivilegeUrls<---------！");

	}

}
