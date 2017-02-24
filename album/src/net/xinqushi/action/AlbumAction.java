package net.xinqushi.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;

import net.xinqushi.pojo.Album;
import net.xinqushi.pojo.Photo;
import net.xinqushi.pojo.User;
import net.xinqushi.service.AlbumService;
import net.xinqushi.service.UserService;

public class AlbumAction {
	// 注入service
	@Autowired
	private AlbumService albumService;
	@Autowired
	private UserService userService;
	// 接收请求信息
	private Album album;
	private Photo photo;
	private String albumId;

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	// 引入图片上传所需要的三个属性并设置g/setter
	private File newfile;
	private String newfileFileName; // 文件名称
	private String newfileContentType; // 文件类型

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public File getNewfile() {
		return newfile;
	}

	public void setNewfile(File newfile) {
		this.newfile = newfile;
	}

	public String getNewfileFileName() {
		return newfileFileName;
	}

	public void setNewfileFileName(String newfileFileName) {
		this.newfileFileName = newfileFileName;
	}

	public String getNewfileContentType() {
		return newfileContentType;
	}

	public void setNewfileContentType(String newfileContentType) {
		this.newfileContentType = newfileContentType;
	}

	// 相册封面上传
	public String upload() {
		String realPath = ServletActionContext.getServletContext().getRealPath("/images");
		// System.out.println("realPath: " + realPath);
		// 判断并 将图片存入磁盘
		if (null != newfile) {
			File saveFile = new File(new File(realPath), newfileFileName);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			// 复制图片
			try {
				FileUtils.copyFile(newfile, saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 更新数据库
			// 设置album的cover字段
			String cover = ServletActionContext.getRequest().getContextPath() + "/images/" + newfileFileName;
			album.setCover(cover);
			// 从session获取user信息并从数据库获取完整信息
			User user = (User) ActionContext.getContext().getSession().get("LOGINUSER");
			user = userService.get(user);
			// 设album的uid字段【多对一关系】
			album.setUser(user);
			// 更新数据库信息
			albumService.add(album);
		}
		// 返回页面
		return "album";
	}

	// 相册预览展示
	public String total() {
		// 从session获取user信息
		User user = (User) ActionContext.getContext().getSession().get("LOGINUSER");
		//
		user = userService.get(user);
		// 数据准备
		ArrayList<Album> albumList = albumService.getAlbumList(user);
		// 将list存入session
		ActionContext.getContext().put("ALBUMLIST", albumList);
		// 返回页面
		return "total";
	}

	// 相册照片上传
	public String uploadphoto() {
		String realPath = ServletActionContext.getServletContext().getRealPath("/photos");
		// 判断并 将图片存入磁盘
		if (null != newfile) {
			File saveFile = new File(new File(realPath), newfileFileName);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			// 复制图片
			try {
				FileUtils.copyFile(newfile, saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 更新数据库
			// 设置photo的url字段
			String url = ServletActionContext.getRequest().getContextPath() + "/photos/" + newfileFileName;
			photo.setUrl(url);			
			//从session获取id
			int id = (int) ActionContext.getContext().getSession().get("ALBUMID");
//			int id = Integer.parseInt(ServletActionContext.getRequest().getParameter("albumId"));
			Album album = new Album();
			album.setId(id);
			album = albumService.getAlbum(album);
			photo.setAlbum(album);
			// 更新数据库信息
			albumService.add(photo);
		}
		return detail();
	}

	// 相册详细展示
	public String detail() {
		//获取album的id
		Integer id = null;
		if (null == ServletActionContext.getRequest().getParameter("albumId")) {
			id = (int) ActionContext.getContext().getSession().get("ALBUMID");
		}else {
			id = Integer.parseInt(ServletActionContext.getRequest().getParameter("albumId"));
		}
		Album album = new Album();
		album.setId(id);
		//从数据库获取
		album = albumService.getAlbum(album);
		ActionContext.getContext().getSession().put("ALBUMID", id);
		// 数据准备
//		Set<Photo> photoList = album.getPhotos();
		ArrayList<Photo> photoList = albumService.getPhotoList(album);
		// 将list存入session
		ActionContext.getContext().put("PHOTOSLIST", photoList);
		// 返回页面
		return "photos";
	}
}
