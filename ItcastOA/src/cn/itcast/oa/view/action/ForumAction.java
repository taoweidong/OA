package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.QueryHelper;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {

	/**
	 * 0 表示查看全部主题<br>
	 * 1表示只看精华帖
	 */
	private int orderBy = 0;

	private int viewType = 0;

	/**
	 * true 表示升序<br>
	 * false 表示降序
	 */
	private boolean asc = false;

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 板块列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {

		List<Forum> forumList = forumManageService.findAll();
		ActionContext.getContext().put("forumList", forumList);

		return "list";
	}

	/**
	 * 显示单个板块(主题列表)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		// 准备数据
		Forum forum = forumManageService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);

		// 准备数据
		// List<Topic> topicList = topicService.findByForum(forum);
		// ActionContext.getContext().put("topicList", topicList);

		// PageBean pageBean = topicService.getPageBeanByForum(pageNum, pageSize,
		// forum);
		// ActionContext.getContext().getValueStack().push(pageBean);

		// 准备分页信息 v2
		// String hql = "FROM Topic t WHERE t.forum=? ";
		// List<Object> parameters = new ArrayList<Object>();
		// parameters.add(forum);
		//
		// if (viewType == 1) {// 1 表示只看精华帖
		// hql += " AND t.type=?";
		// parameters.add(Topic.TYPE_BEST);
		// }
		//
		// if (orderBy == 1) {// 1 表示只按最后更新排除
		// hql += " ORDER BY t.lastUpdateTime " + (asc ? "ASC" : "DESC");
		// } else if (orderBy == 2) {
		// hql += " ORDER BY t.postTime " + (asc ? "ASC" : "DESC");
		// } else if (orderBy == 3) {
		// hql += " ORDER BY t.replyCount " + (asc ? "ASC" : "DESC");
		// } else {
		// hql += " ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END)
		// DESC,t.lastUpdateTime DESC ";
		// }

		new QueryHelper(Topic.class, "t")//
				.addCondition("t.forum=?", forum)//
				.addCondition(viewType == 1, "t.type=?", Topic.TYPE_BEST)//
				.addOrderProperty(orderBy == 1, "t.lastUpdateTime", asc)//
				.addOrderProperty(orderBy == 2, "t.postTime", asc)//
				.addOrderProperty(orderBy == 3, "t.replyCount", asc)//
				.addOrderProperty(orderBy == 0, "(CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime ", asc)//
				.preparePageBean(replyService, pageNum, pageSize);

		// PageBean pageBean = replyService.getPageBean(pageNum, pageSize, queryHelper);
		// ActionContext.getContext().getValueStack().push(pageBean);

		return "show";
	}

}
