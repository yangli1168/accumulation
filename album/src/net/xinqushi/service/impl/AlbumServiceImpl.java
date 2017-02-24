package net.xinqushi.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xinqushi.dao.AlbumDAO;
import net.xinqushi.pojo.Album;
import net.xinqushi.pojo.Photo;
import net.xinqushi.pojo.User;
import net.xinqushi.service.AlbumService;
@Service
public class AlbumServiceImpl implements AlbumService {
	//注入dao
	@Autowired
	private AlbumDAO albumDAO;
	/*@Resource   //等同于@resource(name="albumDAO")
	public void setAlbumDAO(AlbumDAO albumDAO) {
		this.albumDAO = albumDAO;
	}*/
	
	@Override
	public void add(Album album) {
		albumDAO.save(album);
	}

	@Override
	public ArrayList<Album> getAlbumList(User user) {
		// TODO Auto-generated method stub
		return albumDAO.getAlbumList(user);
	}

	@Override
	public void add(Photo photo) {
		// TODO Auto-generated method stub
		albumDAO.save(photo);
	}

	@Override
	public ArrayList<Photo> getPhotoList(Album album) {
		// TODO Auto-generated method stub
		return albumDAO.getPhotoList(album);
	}
	
	public Album getAlbum(Album album){
		return albumDAO.getAlbum(album);
	}
}
