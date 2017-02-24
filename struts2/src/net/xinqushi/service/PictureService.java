package net.xinqushi.service;

import java.util.ArrayList;

import net.xinqushi.pojo.Picture;

public class PictureService {

	public static String getJSON(ArrayList<Picture> list,String contextPath)
	{
		StringBuilder str=new StringBuilder();
		str.append("{");
		str.append("\"status\": 1,");
		str.append("\"msg\": \"\",");
		str.append("\"title\": \"用户相册\",");
		str.append("\"id\": 0,");
		str.append("\"start\": 0,");
		str.append("\"data\": [");	
		//处理照片
		for(int i=0;i<list.size();i++)
		{
			str.append("{");
			str.append("\"name\": \""+list.get(i).getName()+"\",");
			str.append("\"pid\": 0,");
			str.append("\"src\": \""+contextPath+"/"+list.get(i).getUrl()+"\",");
			str.append("\"thumb\": \"\",");
			str.append("\"area\": [638, 851]");
			str.append("}");
			if(i<list.size()-1)
			{
				str.append(",");
			}
		}
		str.append("]");
		str.append("}");
		return str.toString();
	}	
}
