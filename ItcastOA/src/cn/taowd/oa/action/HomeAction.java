package cn.taowd.oa.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2074442349400189006L;

	public String index() throws Exception {
		// TODO Auto-generated method stub
		return "index";
	}

	public String top() throws Exception {
		// TODO Auto-generated method stub
		return "top";
	}

	public String bottom() throws Exception {
		// TODO Auto-generated method stub
		return "bottom";
	}

	public String left() throws Exception {
		// TODO Auto-generated method stub
		return "left";
	}

	public String right() throws Exception {
		// TODO Auto-generated method stub
		return "right";
	}

}
