package cn.taowd.oa.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.taowd.oa.base.BaseAction;
import cn.taowd.oa.domain.Forum;
import cn.taowd.oa.domain.Reply;
import cn.taowd.oa.domain.Topic;
import cn.taowd.oa.util.QueryHelper;

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

	/**
	 * 修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveUI() throws Exception {
		// 准备数据
		Forum forum = forumManageService.getById(forumId);
		ActionContext.getContext().put("forum", forum);

		// 准备数据显示 Topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);

		return "SaveUI";
	}

	/**
	 * 更新主题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		Topic topic = topicService.getById(model.getId());
		// 封装对象保存数据
		// -->表单参数 已经封装
		topic.setTitle(model.getTitle());
		topic.setContent(model.getContent());
		// --->当前直接获取

		topic.setAuthor(getCurrentUser());// 当前登录用户
		topic.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());// 当前请求IP地址
		topic.setPostTime(new Date());// 当前请求时间

		topicService.update(topic);

		return "toShow";
	}

	/**
	 * 删除主题--主题不允许删除
	 * 
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public String delete() throws Exception {

		topicService.delete(model.getId());

		return "toForum";

	}

	/**
	 * 主题移动页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String moveTopicToForumUI() throws Exception {
		// 准备数据
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);

		// Forum forum = forumManageService.getById(forumId);
		// ActionContext.getContext().put("forum", forum);

		List<Forum> forumList = forumManageService.findAll();
		ActionContext.getContext().put("forumList", forumList);

		return "moveTopicToForumUI";

	}

	/**
	 * 修改主题板块
	 * 
	 * @return
	 * @throws Exception
	 */
	public String moveTopicToForum() throws Exception {

		System.out.println("移动至=====》" + destForumId);

		Forum forum = forumManageService.getById(destForumId);

		System.out.println("当前主题ID------>" + model.getId());
		Topic topic = topicService.getById(model.getId());

		topic.setForum(forum);

		topicService.update(topic);

		return "moveTopicToForum";

	}

	/**
	 * 设置精华帖
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setBest() throws Exception {

		Topic topic = topicService.getById(model.getId());

		topic.setType(Topic.TYPE_BEST);

		topicService.update(topic);

		ActionContext.getContext().put("forumId", forumId);

		return "toForum";
	}

	/**
	 * 设置置顶帖
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setTop() throws Exception {

		Topic topic = topicService.getById(model.getId());

		topic.setType(Topic.TYPE_TOP);

		topicService.update(topic);
		ActionContext.getContext().put("forumId", forumId);
		return "toForum";
	}

	/**
	 * 设置普通帖
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setNormal() throws Exception {

		Topic topic = topicService.getById(model.getId());
		topic.setType(Topic.TYPE_NORMAL);
		topicService.update(topic);

		ActionContext.getContext().put("forumId", forumId);
		return "toForum";
	}

	private Long destForumId;

	public Long getDestForumId() {
		return destForumId;
	}

	public void setDestForumId(Long destForumId) {
		this.destForumId = destForumId;
	}

}
