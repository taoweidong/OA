package cn.itcast.oa.view.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long parentId;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 
	 * Taowd TODO 列表信息 2017-11-21-下午8:29:25
	 */
	public String list() throws Exception {
		List<Department> departmentList = null;
		if (parentId == null) {
			departmentList = departmentService.findTopList();
		} else {
			departmentList = departmentService.findChildren(parentId);

			// 获取上一级列表的id
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);

		}

		// 把数据放在值栈中

		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/**
	 * 
	 * Taowd TODO 删除 2017-11-21-下午8:29:25
	 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/**
	 * 
	 * Taowd TODO 添加 2017-11-21-下午8:29:25
	 */
	public String add() throws Exception {

		Department parent = departmentService.getById(parentId);
		model.setParent(parent);
		departmentService.save(model);
		return "toList";
	}

	/**
	 * 
	 * Taowd TODO 修改 2017-11-21-下午8:29:25
	 */
	public String edit() throws Exception {
		logger.info("进入修改方法-----");

		// 从数据中获取原数据
		Department department = departmentService.getById(model.getId());
		// 赋新值
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));

		// 更新数据库
		departmentService.update(department);

		return "toList";
	}

	/**
	 * 
	 * Taowd TODO 修改页面 2017-11-21-下午8:29:25
	 */
	public String editUI() throws Exception {

		List<Department> departmentList = new ArrayList<Department>();
		departmentList = DepartmentUtils.getAllDepartments(departmentService.findTopList());
		// 把数据放在值栈中的Map中
		ActionContext.getContext().put("departmentList", departmentList);

		// 获取原数据
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);

		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}

		return "SaveUI";
	}

	/**
	 * 
	 * Taowd TODO 添加页面 2017-11-21-下午8:29:25
	 */
	public String addUI() throws Exception {
		List<Department> departmentList = new ArrayList<Department>();
		departmentList = DepartmentUtils.getAllDepartments(departmentService.findTopList());

		// 把数据放在值栈中的Map中
		ActionContext.getContext().put("departmentList", departmentList);

		return "SaveUI";
	}
}
