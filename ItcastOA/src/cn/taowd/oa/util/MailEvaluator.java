package cn.taowd.oa.util;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 自定义日期日志邮件格式，主要处理发送邮件时的乱码 问题
 * 格式化输出日志信息
 * @author Taowd
 *
 */
public class MailEvaluator extends Layout {

	StringBuffer sbuf;

	@Override
	public String getContentType() {

		return "text/html;charset=GBK";
	}

	public MailEvaluator() {
		sbuf = new StringBuffer(128);
	}

	/**
	 * 注意在这个方法里面可以获取Log4j所有的日志信息，组装格式
	 */
	@Override
	public String format(LoggingEvent event) {
		sbuf.setLength(0);
		sbuf.append("错误等级：" + event.getLevel().toString() + "===\n");
		sbuf.append("错误原因：" + event.getMessage().toString() + "===\n");
		sbuf.append("错误所在类" + event.getLocationInformation().getClassName()
				+ "===\n");
		sbuf.append("错误方法所在：" + event.getLocationInformation().getMethodName()
				+ "===\n");
		sbuf.append("错误行:" + event.getLocationInformation().getLineNumber()
				+ "\n");
		return sbuf.toString();
	}

	@Override
	public boolean ignoresThrowable() {
		// TODO Auto-generated method stub
		return false;
	}

	public void activateOptions() {
		// TODO Auto-generated method stub

	}

}
