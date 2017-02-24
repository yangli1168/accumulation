package net.xinqushi.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.xinqushi.common.BtnBuilder;
import net.xinqushi.common.Pager;
import net.xinqushi.dao.RoomDAO;
import net.xinqushi.pojo.Room;

public class RoomAction extends ActionSupport {
	private RoomDAO rDao = new RoomDAO();
	// ��ȡ������Ϣ
	private Room room;
	private int page;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	// ���������
	public String update() {
		return updateIt();
	}

	// Ϊ����ṩ�޸ķ���
	public String modify() {
		return updateIt();
	}

	// ɾ��
	public String delete() {
		rDao.delete(room);
		return null;
	}

	private String updateIt() {
		// �ж����?�޸ġ����ʱroom.idΪ0���޸�ʱ>0��
		if (room.getId() == 0) {
			ActionContext.getContext().put("ADD", true);
		}
		// �����ݿ��������
		rDao.update(room);
		return dataForRooms();
	}

	// Ϊҳ��׼����ҳ����
	public String dataForRooms() {
		// ����request
		HttpServletRequest request = ServletActionContext.getRequest();
		// ���ó�ʼҳ�롾����ҳδ����ʱ��
		if (page == 0) {
			page = 1;
		}
		// ����pager����ʼ����ҳ��
		Pager pager = new Pager();
		rDao.initPageCount(pager);
		// ��ҳ�洫�ݵ�ҳ����������ж�
		if (page > pager.getPageCount()) {
			page = pager.getPageCount();
		}
		// �ж϶�room�Ĳ������޸Ļ�������������ʱ���ݿ��¼�����ӣ�pageӦ�ö�λ������ҳ�桿
		if (null != ActionContext.getContext().get("ADD")) {
			page = pager.getPageCount();
		}
		// ������Ҫ��ʾ��ҳ��
		pager.setPageNo(page);
		List<Room> list = rDao.getRooms(pager);
		// �ж�
		if (list.size() > 0) {
			// �����н�����Ϣ����session
			ActionContext.getContext().getSession().put("ROOMS", list);
		}
		//��������·��
		String url = "/room/dataForRooms";
		// ������������session
		ActionContext.getContext().getSession().put("BTNS", BtnBuilder.buildBtn(request, pager, page, url));
		// ����̨��ӡstr[�����]
//		System.out.println(str.toString());
		return "roommain";
	}

	// Ϊ�޸Ĳ���ṩ����
	public String getRoomById() {
		room = rDao.getRoomById(room);
		// �����jspҳ�洫����������
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		// ��ҳ�淢����Ϣ
		try {
			ServletActionContext.getResponse().getWriter()
					.print(room.getId() + "!" + room.getName() + "!" + room.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
