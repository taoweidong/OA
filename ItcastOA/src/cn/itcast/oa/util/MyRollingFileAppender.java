
package cn.itcast.oa.util;

import org.apache.log4j.Priority;
import org.apache.log4j.RollingFileAppender;

/**
 * 功能：重新日志输出级别
 * 
 * @author Taowd
 *
 */
public class MyRollingFileAppender extends RollingFileAppender {
	/**
	 * 功能：重新定义日志输出的级别，只有等于当前指定级别时才进行输出
	 */
	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		return this.getThreshold().equals(priority);
	}
}
