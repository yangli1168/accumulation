package net.xinqushi.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import net.xinqushi.common.HibernateUtil;
import net.xinqushi.common.Pager;
import net.xinqushi.common.QueryUtil;
import net.xinqushi.pojo.Room;

public class RoomDAO {
	// 保存或更新
	public void update(Room room) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.saveOrUpdate(room);
		closeSession(session);
	}

	private void closeSession(Session session) {
		session.getTransaction().commit();
		session.close();
	}

	// 删除
	public void delete(Room room) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.delete(room);
		closeSession(session);
	}

	/** 查询room by id */
	public Room getRoomById(Room room) {
		String hql = "from Room where id =:id";
		return (Room) QueryUtil.getInfo(hql, room);
	}

	/** 查询room集合 */
	public ArrayList<Room> getRooms() {
		String hql = "from Room";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<Room> list = (ArrayList<Room>) query.list();
		closeSession(session);
		return list;
	}

	/** 分页数查询 */
	public void initPageCount(Pager pager){
		String hql = "from Room";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Room> list=query.list();
		//分页数设置
		//1、先设置总记录数/每页记录数
		pager.setPageCount(list.size()/pager.getPageSize());
		//当总记录条数/每页记录数有余数时，应+1页
		if (list.size() % pager.getPageSize() > 0) {
			pager.setPageCount(list.size()/pager.getPageSize() + 1);
		}
		//关闭session
		closeSession(session);		
	}
	/** 分页信息查询 */
	public List<Room> getRooms(Pager pager){
		String hql = "from Room";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		//分页设置
		//1、设置一页从第几条数据开始
		query.setFirstResult((pager.getPageNo()-1)*pager.getPageSize());
		//2、设置一页显示多少条记录
		query.setMaxResults(pager.getPageSize());
		//执行查询
		@SuppressWarnings("unchecked")
		List<Room> list = query.list();
		closeSession(session);
		return list;
	}
}
