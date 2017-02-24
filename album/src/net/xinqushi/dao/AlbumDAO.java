package net.xinqushi.dao;

import java.util.ArrayList;

import net.xinqushi.pojo.Album;
import net.xinqushi.pojo.Photo;
import net.xinqushi.pojo.User;

public interface AlbumDAO {
	public void save(Album album);
	
	public ArrayList<Album> getAlbumList(User user);
	
	public void save(Photo photo);
	
	public ArrayList<Photo> getPhotoList(Album album);
	
	public Album getAlbum(Album album);
}
