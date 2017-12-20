package cn.itcast.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long topicId;

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	/**
	 * 发表新回复页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		// 准备相应的数据
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);

		return "addUI";
	}

	/**
	 * 发表新回复
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {

		// model.setTitle(title);
		// model.setContent(content);
		model.setTopic(topicService.getById(topicId));

		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());

		replyService.save(model);

		return "toTopicShow";// 转到新回复所在主题的显示页面
	}

}
