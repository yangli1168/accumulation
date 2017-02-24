package net.xinqushi.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.xinqushi.dao.PictureDAO;
import net.xinqushi.pojo.Picture;
import net.xinqushi.service.PictureService;

public class PictureAction extends ActionSupport {
	private Picture picture;
	private File[] image;
	private String[] imageFileName;
	String[] imageContentType;
	// 引入response
	HttpServletResponse response = ServletActionContext.getResponse();
	// 定义id分类
	String idType;

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	// 接受批量上传照片的信息
	ArrayList<Picture> pictures = new ArrayList<Picture>();

	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<Picture> pictures) {
		this.pictures = pictures;
	}

	public File[] getImage() {
		return image;
	}

	public void setImage(File[] image) {
		this.image = image;
	}

	public String[] getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String[] imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String[] getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String[] imageContentType) {
		this.imageContentType = imageContentType;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	// 增
	public String add() {
		// TODO Auto-generated method stub
		ServletContext application = ServletActionContext.getServletContext();
		String path = application.getRealPath("") + "/uploadimages";
		// 保存图片信息
		for (int i = 0; i < image.length; i++) {
			// 创建文件保存图片
			// 1、创建目录
			File newFile = new File(path, imageFileName[i]);
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdir();
			}
			// 2、复制上传文件到目录
			try {
				// 3、复制文件实体
				FileUtils.copyFile(image[i], newFile);
				// 4、设置图片url
				pictures.get(i).setUrl("uploadimages/" + imageFileName[i]);
				// 5、将包含完整url信息的图片信息存入数据库pictures表
				new PictureDAO().addPicture(pictures.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "list";
	}

	// 删
	public String delete() {
		// TODO Auto-generated method stub
		// 删除磁盘文件
		String url = new PictureDAO().getUrl(picture.getId());
		String path = ServletActionContext.getServletContext().getRealPath("") + "/" + url;
		File dFile = new File(path);
		FileUtils.deleteQuietly(dFile);
		// 删除数据库记录
		new PictureDAO().deletePicture(picture.getId());
		return null;
	}

	// 查所有
	public String getPics() {
		int id = 0; // 初始化-16.9.25
		if (idType.equals("user")) {
			id = picture.getUid();
		} else {
			id = picture.getId();
		}
		try {
			ArrayList<Picture> list = new PictureDAO().getPictures(id, idType);
			String path = ServletActionContext.getRequest().getContextPath();
			String json = PictureService.getJSON(list, path);
			// 乱码设置-16.9.25
			/* 设置格式为text/json */
			response.setContentType("text/json");
			/* 设置字符集为'UTF-8' */
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			out.println(json);
			// 控制台打印json信息[检查用，无错后可删除]
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 查数量
	public String getNum() {
		try {
			int num = new PictureDAO().getPicturesCount(picture.getUid());
			PrintWriter out = response.getWriter();
			out.println(num);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
