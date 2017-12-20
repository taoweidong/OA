package cn.itcast.oa.service;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;

/**
 * 板块的服务类
 * 
 * @author Taowd
 *
 */
public interface ForumManageService extends DaoSupport<Forum> {

	/**
	 * 上移 最上面的不能上移
	 * 
	 * @param id
	 */
	void moveUp(Long id);

	/**
	 * 下移 最下面的不能下移
	 * 
	 * @param id
	 */
	void moveDown(Long id);

}
