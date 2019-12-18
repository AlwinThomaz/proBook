package com.project.probook.persistence.domain;

import java.util.Arrays;
import java.util.List;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;

@Entity
public class Type {

	
	@Id
	@GeneratedValue
	private long typeId;

	
	private String name;

	@OneToMany
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
}