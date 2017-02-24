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
	// 获取请求信息
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

	// 新增或更新
	public String update() {
		return updateIt();
	}

	// 为插件提供修改方法
	public String modify() {
		return updateIt();
	}

	// 删除
	public String delete() {
		rDao.delete(room);
		return null;
	}

	private String updateIt() {
		// 判断添加?修改【添加时room.id为0；修改时>0】
		if (room.getId() == 0) {
			ActionContext.getContext().put("ADD", true);
		}
		// 向数据库存入数据
		rDao.update(room);
		return dataForRooms();
	}

	// 为页面准备分页数据
	public String dataForRooms() {
		// 引入request
		HttpServletRequest request = ServletActionContext.getRequest();
		// 设置初始页码【当网页未传递时】
		if (page == 0) {
			page = 1;
		}
		// 定义pager并初始化总页数
		Pager pager = new Pager();
		rDao.initPageCount(pager);
		// 对页面传递的页码参数进行判断
		if (page > pager.getPageCount()) {
			page = pager.getPageCount();
		}
		// 判断对room的操作是修改还是新增【新增时数据库记录数增加，page应该定位到新增页面】
		if (null != ActionContext.getContext().get("ADD")) {
			page = pager.getPageCount();
		}
		// 设置需要显示的页数
		pager.setPageNo(page);
		List<Room> list = rDao.getRooms(pager);
		// 判断
		if (list.size() > 0) {
			// 将所有教室信息存入session
			ActionContext.getContext().getSession().put("ROOMS", list);
		}
		//设置请求路径
		String url = "/room/dataForRooms";
		// 将导航条存入session
		ActionContext.getContext().getSession().put("BTNS", BtnBuilder.buildBtn(request, pager, page, url));
		// 控制台打印str[查错用]
//		System.out.println(str.toString());
		return "roommain";
	}

	// 为修改插件提供数据
	public String getRoomById() {
		room = rDao.getRoomById(room);
		// 解决向jsp页面传送数据乱码
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		// 向页面发送信息
		try {
			ServletActionContext.getResponse().getWriter()
					.print(room.getId() + "!" + room.getName() + "!" + room.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
