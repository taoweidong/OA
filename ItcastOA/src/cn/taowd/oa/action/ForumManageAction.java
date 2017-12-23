package cn.taowd.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.taowd.oa.base.BaseAction;
import cn.taowd.oa.domain.Forum;
/**
 * 板块管理的Action
 * @author Taowd
 *
 */
@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5519080617926673475L;

	/** 列表 */
	public String list() throws Exception {

		List<Forum> roumList = forumManageService.findAll();
		ActionContext.getContext().put("forumList", roumList);

		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		forumManageService.delete(model.getId());
		
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		forumManageService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		//准备数据
		Forum forum = forumManageService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		
		
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		//取出元数据
		Forum forum = forumManageService.getById(model.getId());
		//设置新数据
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		//更新数据库
		forumManageService.update(forum);
		
		return "toList";
	}

	/** 上移 */
	public String moveUp() throws Exception {
		
		forumManageService.moveUp(model.getId());
		
		return "toList";
	}

	/** 下移 */
	public String moveDown() throws Exception {
		forumManageService.moveDown(model.getId());
		return "toList";
	}

}
