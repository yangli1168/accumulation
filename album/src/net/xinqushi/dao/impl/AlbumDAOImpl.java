package net.xinqushi.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import net.xinqushi.dao.AlbumDAO;
import net.xinqushi.pojo.Album;
import net.xinqushi.pojo.Photo;
import net.xinqushi.pojo.User;

@Component
public class AlbumDAOImpl implements AlbumDAO {
	// 引入HibernateTemplate并设置setter【因为spring只用到setter】
	HibernateTemplate hibernateTemplate;

	@Resource(name = "hibernateTemplate") // 等同于@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void save(Album album) {
		hibernateTemplate.merge(album);
	}

	@Override
	public ArrayList<Album> getAlbumList(User user) {
//		Album album = new Album();
//		album.setUser(user);
//		List<Album> list = (List<Album>) hibernateTemplate.findByExample(album);
		
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Album where uid=:uid";
		Query query = session.createQuery(hql);
		query.setInteger("uid", user.getId());
		List<Album> list = query.list();
		session.getTransaction().commit();
		session.close();
		if (list.size() > 0) {
			return (ArrayList<Album>) list;
		}
		return null;
	}
	
	public void save(Photo photo){
		hibernateTemplate.merge(photo);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Photo> getPhotoList(Album album){
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Photo where aid=:aid";
		Query query = session.createQuery(hql);
		query.setInteger("aid", album.getId());
		List<Photo> list = query.list();		
		
//		Photo photo = new Photo();
//		photo.setAlbum(album);
//		List<Photo> list = hibernateTemplate.findByExample(photo);
		
		session.getTransaction().commit();
		session.close();
		if (list.size() > 0) {
			return (ArrayList<Photo>) list;
		}
		
		return null;
	}
	
	public Album getAlbum(Album album) {
		// TODO Auto-generated method stub
//		List<Album> list = hibernateTemplate.findByExample(album);
		
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Album where id=:id";
		Query query = session.createQuery(hql).setProperties(album);
		@SuppressWarnings("unchecked")
		List<Album> list = query.list();
		
		// 判断：list为空时返回null
		if (list.size() > 0) {
			album = list.get(0);
//			Hibernate.initialize(album.getPhotos());
			return album;
		}
		return null;
	}

}
