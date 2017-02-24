package net.xinqushi.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Album {
	private int id;
	private String name;
	private String cover;
	// 多对一关系引入user
	private User user;
	//一对多关系引入photo
	private Set<Photo> photos = new HashSet<Photo>();
	
	
	@OneToMany(mappedBy = "album")
	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@ManyToOne
	@JoinColumn(name = "uid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
