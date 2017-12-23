package cn.taowd.oa.service;

import java.util.List;

import cn.taowd.oa.base.DaoSupport;
import cn.taowd.oa.domain.PageBean;
import cn.taowd.oa.domain.Reply;
import cn.taowd.oa.domain.Topic;

public interface ReplyService extends DaoSupport<Reply> {

	List<Reply> findByTopic(Topic topic);

	@Deprecated
	PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic);

}
