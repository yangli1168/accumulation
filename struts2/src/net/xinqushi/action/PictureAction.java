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
	// ����response
	HttpServletResponse response = ServletActionContext.getResponse();
	// ����id����
	String idType;

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	// ���������ϴ���Ƭ����Ϣ
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

	// ��
	public String add() {
		// TODO Auto-generated method stub
		ServletContext application = ServletActionContext.getServletContext();
		String path = application.getRealPath("") + "/uploadimages";
		// ����ͼƬ��Ϣ
		for (int i = 0; i < image.length; i++) {
			// �����ļ�����ͼƬ
			// 1������Ŀ¼
			File newFile = new File(path, imageFileName[i]);
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdir();
			}
			// 2�������ϴ��ļ���Ŀ¼
			try {
				// 3�������ļ�ʵ��
				FileUtils.copyFile(image[i], newFile);
				// 4������ͼƬurl
				pictures.get(i).setUrl("uploadimages/" + imageFileName[i]);
				// 5������������url��Ϣ��ͼƬ��Ϣ�������ݿ�pictures��
				new PictureDAO().addPicture(pictures.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "list";
	}

	// ɾ
	public String delete() {
		// TODO Auto-generated method stub
		// ɾ�������ļ�
		String url = new PictureDAO().getUrl(picture.getId());
		String path = ServletActionContext.getServletContext().getRealPath("") + "/" + url;
		File dFile = new File(path);
		FileUtils.deleteQuietly(dFile);
		// ɾ�����ݿ��¼
		new PictureDAO().deletePicture(picture.getId());
		return null;
	}

	// ������
	public String getPics() {
		int id = 0; // ��ʼ��-16.9.25
		if (idType.equals("user")) {
			id = picture.getUid();
		} else {
			id = picture.getId();
		}
		try {
			ArrayList<Picture> list = new PictureDAO().getPictures(id, idType);
			String path = ServletActionContext.getRequest().getContextPath();
			String json = PictureService.getJSON(list, path);
			// ��������-16.9.25
			/* ���ø�ʽΪtext/json */
			response.setContentType("text/json");
			/* �����ַ���Ϊ'UTF-8' */
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			out.println(json);
			// ����̨��ӡjson��Ϣ[����ã��޴���ɾ��]
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ������
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
