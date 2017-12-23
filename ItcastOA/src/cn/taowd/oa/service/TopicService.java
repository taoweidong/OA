package cn.taowd.oa.service;

import java.util.List;

import cn.taowd.oa.base.DaoSupport;
import cn.taowd.oa.domain.Forum;
import cn.taowd.oa.domain.PageBean;
import cn.taowd.oa.domain.Topic;

public interface TopicService extends DaoSupport<Topic> {

	/**
	 * 获取所有主题数据
	 * 
	 * @param forum
	 * @return
	 */
	@Deprecated
	List<Topic> findByForum(Forum forum);

	@Deprecated
	PageBean getPageBeanByForum(int pageNum, int pageSize, Forum forum);

}
