package cn.itcast.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.QueryHelper;

@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5988876514465199171L;

	private Long forumId;

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	/**
	 * 显示单个主题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		// 准备数据显示 Topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);

		// 准备主题的回复数据
		// List<Reply> replyList = replyService.findByTopic(topic);
		// ActionContext.getContext().put("replyList", replyList);

		// PageBean pageBean = replyService.getPageBeanByTopic(pageNum, pageSize,
		// topic);
		// ActionContext.getContext().getValueStack().push(pageBean);

		// 准备分页信息 v2
		// String hql = "FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC";
		// List<Object> parameters = new ArrayList<Object>();
		// parameters.add(topic);

		new QueryHelper(Reply.class, "r")//
				.addCondition("r.topic=?", topic)//
				.addOrderProperty("r.postTime", true)//
				.preparePageBean(replyService, pageNum, pageSize);
		;
		// PageBean pageBean = replyService.getPageBean(pageNum, pageSize, queryHelper);
		// ActionContext.getContext().getValueStack().push(pageBean);

		return "show";
	}

	/**
	 * 发表新主题页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		// 准备数据
		Forum forum = forumManageService.getById(forumId);
		ActionContext.getContext().put("forum", forum);

		return "addUI";
	}

	/**
	 * 发表新主题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 封装对象保存数据
		// -->表单参数 已经封装
		// topic.setTitle(title);
		// topic.setContent(content);
		model.setForum(forumManageService.getById(forumId));

		// --->当前直接获取

		model.setAuthor(getCurrentUser());// 当前登录用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());// 当前请求IP地址
		model.setPostTime(new Date());// 当前请求时间

		topicService.save(model);

		return "toShow";
	}

}
