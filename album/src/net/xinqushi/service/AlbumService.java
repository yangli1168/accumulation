package net.xinqushi.service;

import java.util.ArrayList;

import net.xinqushi.pojo.Album;
import net.xinqushi.pojo.Photo;
import net.xinqushi.pojo.User;

public interface AlbumService {
	//添加
	public void add(Album album);
	//查询集合
	public ArrayList<Album> getAlbumList(User user);
	
	public void add(Photo photo);
	//查询集合
	public ArrayList<Photo> getPhotoList(Album album);
	
	public Album getAlbum(Album album);
}
