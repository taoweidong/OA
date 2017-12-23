package cn.taowd.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.taowd.oa.base.DaoSupportImpl;
import cn.taowd.oa.domain.Forum;
import cn.taowd.oa.domain.PageBean;
import cn.taowd.oa.domain.Topic;
import cn.taowd.oa.service.TopicService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements TopicService {

	@Override
	@Deprecated
	public List<Topic> findByForum(Forum forum) {
		// 排序：所有的置顶帖在最上面，并按照最后更新时间排序，让新状态的在最上面
		// TODO 怎么排序
		return getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.list();
	}

	@Override
	public void update(Topic topic) {

		getSession().update(topic);

		// 2. 维护相关的特殊属性
		Forum forum = topic.getForum();
		forum.setLastTopic(topic);// 最后主题
		forum.setArticleCount(forum.getArticleCount() + 1);// 文章数量
		forum.setTopicCount(forum.getTopicCount() + 1);// 主题数量
		getSession().update(forum);
	}

	@Override
	public void save(Topic topic) {

		// 1. 设置属性并保存
		// --->应放到业务方法中
		topic.setType(Topic.TYPE_NORMAL);
		topic.setLastReply(null);
		topic.setLastUpdateTime(topic.getPostTime());
		topic.setReplyCount(0);

		getSession().save(topic);

		// 2. 维护相关的特殊属性
		Forum forum = topic.getForum();
		forum.setLastTopic(topic);// 最后主题
		forum.setArticleCount(forum.getArticleCount() + 1);// 文章数量
		forum.setTopicCount(forum.getTopicCount() + 1);// 主题数量
		getSession().update(forum);
	}

	@Override
	public PageBean getPageBeanByForum(int pageNum, int pageSize, Forum forum) {
		// 查询满足条件的列表
		List list = getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();

		// 查询总数
		Long count = (Long) getSession().createQuery(//
				"SELECT count(*) FROM Topic t WHERE t.forum=? ")//
				.setParameter(0, forum)//
				.uniqueResult();

		return new PageBean(pageNum, pageSize, count.intValue(), list);

	}

}
