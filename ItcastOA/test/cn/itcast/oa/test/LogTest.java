package cn.itcast.oa.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class LogTest {

	// private ApplicationContext ac = new ClassPathXmlApplicationContext(
	// "applicationContext.xml");

	private Log log = LogFactory.getLog(this.getClass());

	@Test
	public void test() throws Exception {

		// log.debug(new Date());

		log.debug("这是debug信息");
		log.info("这是info信息");
		log.warn("这是warn信息");
		log.error("这是error信息，测试一下看这个信息能不能发送邮件到指定的邮箱！");
		log.fatal("这是fatal信息,测试一下看这个信息能不能发送邮件到指定的邮箱！");
	}
}
