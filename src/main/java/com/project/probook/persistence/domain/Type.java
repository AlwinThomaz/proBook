package com.project.probook.persistence.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Type {

	
	@Id
	@GeneratedValue
	private long id;

	
	private String name;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="type_id", nullable=true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Bookmark> bookmarks = new ArrayList<>();
	
	
	public Type() {

	}

	public Type(String name) {
		super();
		this.name = name;

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

	public List<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
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

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
}