package cn.itcast.oa.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	private static final Logger logger = LoggerFactory.getLogger(SpringTest.class);

	private ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

	@Test
	public void testBean() throws Exception {
		TestAction testAction = (TestAction) ac.getBean("testAction");
		logger.info(testAction.toString());
	}

	/**
	 * 
	 * Taowd TODO 测试SessionFactory 2017-11-13-下午7:18:05
	 */
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		logger.info(sessionFactory.toString());
	}

	// 测试事务
	@Test
	public void testTransaction() {
		try {
			TestService testService = (TestService) ac.getBean("testService");
			testService.saveTwoUsers();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("异常报错", e);// 打印完整堆栈信息
		}

	}

	// @Test
	// public void testAddRole() {
	// try {
	// Role role = new Role();
	// role.setName("营运部");
	// role.setDescription("营运部546546");
	// RoleDao testService = (RoleDao) ac.getBean("roleDao");
	// testService.save(role);
	// } catch (Exception e) {
	// // TODO: handle exception
	// logger.error("异常报错", e);// 打印完整堆栈信息
	// }
	//
	// }
}
