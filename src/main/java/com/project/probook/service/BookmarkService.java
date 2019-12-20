package com.project.probook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.probook.exceptions.BookmarkNotFoundException;
import com.project.probook.persistence.domain.Bookmark;
import com.project.probook.persistence.repo.BookmarkRepo;

@Service
public class BookmarkService {

	private BookmarkRepo repo;

	@Autowired
	public BookmarkService(BookmarkRepo repo) {
		this.repo = repo;
	}

	public Bookmark createBookmark(Bookmark bookmark) {
		return this.repo.save(bookmark);
	}

	public boolean deleteBookmark(Long bookmarkId) throws BookmarkNotFoundException {
		if (!this.repo.existsById(bookmarkId)) {
			throw new BookmarkNotFoundException();
		}
		this.repo.deleteById(bookmarkId);
		return this.repo.existsById(bookmarkId);
	}

	public Bookmark findBookmarkById(Long bookmarkId) throws BookmarkNotFoundException {
		return this.repo.findById(bookmarkId).orElseThrow(() -> new BookmarkNotFoundException());
	}

	public List<Bookmark> readBookmarks() {
		return this.repo.findAll();
	}

	public Bookmark updateBookmark(Bookmark bookmark, Long bookmarkId) throws BookmarkNotFoundException {
		Bookmark toUpdate = findBookmarkById(bookmarkId);
		toUpdate.setName(bookmark.getName());
		toUpdate.setDescription(bookmark.getDescription());
		toUpdate.setUrl(bookmark.getUrl());
		return this.repo.save(toUpdate);
	}

}

