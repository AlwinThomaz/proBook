package com.project.probook.persistence.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Type {

	@Column
	@Id
	@GeneratedValue
	private long typeId;

	@Column(name = "Name", length = 50, nullable = false, unique = true)
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "type_id")
	private List<Bookmark> bookmarks;

	public Type(String name, Bookmark... bookmarks) {
		super();
		this.name = name;
		this.bookmarks = Arrays.asList(bookmarks);

	}

	public Type() {

	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	@Override
	public String toString() {
		return "Type [typeId=" + typeId + ", name=" + name + ", bookmarks=" + bookmarks + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookmarks == null) ? 0 : bookmarks.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (typeId ^ (typeId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		if (bookmarks == null) {
			if (other.bookmarks != null)
				return false;
		} else if (!bookmarks.equals(other.bookmarks))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}

}
