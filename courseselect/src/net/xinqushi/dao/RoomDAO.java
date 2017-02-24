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
	// ��������
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

	// ɾ��
	public void delete(Room room) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.delete(room);
		closeSession(session);
	}

	/** ��ѯroom by id */
	public Room getRoomById(Room room) {
		String hql = "from Room where id =:id";
		return (Room) QueryUtil.getInfo(hql, room);
	}

	/** ��ѯroom���� */
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

	/** ��ҳ����ѯ */
	public void initPageCount(Pager pager){
		String hql = "from Room";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Room> list=query.list();
		//��ҳ������
		//1���������ܼ�¼��/ÿҳ��¼��
		pager.setPageCount(list.size()/pager.getPageSize());
		//���ܼ�¼����/ÿҳ��¼��������ʱ��Ӧ+1ҳ
		if (list.size() % pager.getPageSize() > 0) {
			pager.setPageCount(list.size()/pager.getPageSize() + 1);
		}
		//�ر�session
		closeSession(session);		
	}
	/** ��ҳ��Ϣ��ѯ */
	public List<Room> getRooms(Pager pager){
		String hql = "from Room";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		//��ҳ����
		//1������һҳ�ӵڼ������ݿ�ʼ
		query.setFirstResult((pager.getPageNo()-1)*pager.getPageSize());
		//2������һҳ��ʾ��������¼
		query.setMaxResults(pager.getPageSize());
		//ִ�в�ѯ
		@SuppressWarnings("unchecked")
		List<Room> list = query.list();
		closeSession(session);
		return list;
	}
}
