package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.service.ForumManageService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ForumManageServiceImpl extends DaoSupportImpl<Forum> implements ForumManageService {

	@Override
	public void save(Forum entity) {
		super.save(entity);
		// 这里使用了Hibernate框架的特性 在保存之前该实体是没有id值的 保存之后框架会自动把id值设置到实体中返回，让使用者可以使用
		entity.setPosition(entity.getId().intValue());
	}

	@Override
	public List<Forum> findAll() {
		return getSession().createQuery("FROM Forum f ORDER BY f.position").list();
	}

	/**
	 * 上移
	 */
	public void moveUp(Long id) {
		Forum forum = getById(id);// 获取当前对象
		Forum other = (Forum) getSession().createQuery("FROM Forum f WHERE f.position < ? ORDER BY f.position DESC")
				.setParameter(0, forum.getPosition()).setFirstResult(0).setMaxResults(1).uniqueResult();// 获取上一个对象

		if (other == null) {
			return;
		}

		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);

		// 更新对象
		getSession().update(forum);
		getSession().update(other);

	}

	/**
	 * 下移
	 */
	public void moveDown(Long id) {
		Forum forum = getById(id);// 获取当前对象
		Forum other = (Forum) getSession().createQuery("FROM Forum f WHERE f.position > ? ORDER BY f.position ASC")
				.setParameter(0, forum.getPosition()).setFirstResult(0).setMaxResults(1).uniqueResult();// 获取上一个对象

		if (other == null) {
			return;
		}

		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);

		// 更新对象
		getSession().update(forum);
		getSession().update(other);

	}

}
