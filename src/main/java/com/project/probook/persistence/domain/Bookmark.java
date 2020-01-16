package com.project.probook.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bookmark {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "Bookmark_Name", unique = true)
	private String name;
	
	private String description;

	private String url;
	
	public Bookmark(String name, String description, String url) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
	}
	
	public Bookmark() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

//	@Override
//	public String toString() {
//		return "Bookmark [id=" + id + ", name=" + name + ", description=" + description + ", url=" + url
//				+ "]";
//	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (int) (id ^ (id >>> 32));
//		result = prime * result + ((description == null) ? 0 : description.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + ((url == null) ? 0 : url.hashCode());
//		return result;
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bookmark other = (Bookmark) obj;
//		if (id != other.id)
//			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} 
		else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	

}
