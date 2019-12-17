package com.project.probook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.probook.exceptions.BookmarkNotFoundException;
import com.project.probook.persistence.domain.Bookmark;
import com.project.probook.persistence.repo.BookmarkRepo;



@Service
public class BookmarkService {

@Autowired
private BookmarkRepo repo;
	
	
	public BookmarkService(BookmarkRepo repo) {
		this.repo = repo;
	}
	
	
	public Bookmark createBookmark(Bookmark bookmark) {
		return this.repo.save(bookmark);
	}
	
	public boolean deleteBookmark(Long id) throws BookmarkNotFoundException {
		if (!this.repo.existsById(id)) {
			throw new BookmarkNotFoundException();
		}
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}
	
	public Bookmark findBookmarkById(Long id) throws BookmarkNotFoundException {
		return this.repo.findById(id).orElseThrow(
				() -> new BookmarkNotFoundException());
	}
	
	
	public List<Bookmark> readBookmarks() {
		return this.repo.findAll();
	}
	

	public Bookmark updateBookmark(Bookmark bookmark, Long id) throws BookmarkNotFoundException {
		Bookmark toUpdate = findBookmarkById(id);
		toUpdate.setName(bookmark.getName());
		toUpdate.setDescription(bookmark.getDescription());
		toUpdate.setUrl(bookmark.getUrl());
		return this.repo.save(toUpdate);
	}

}
