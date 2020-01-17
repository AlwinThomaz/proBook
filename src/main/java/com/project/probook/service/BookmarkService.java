package com.project.probook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.probook.exceptions.BookmarkNotFoundException;
import com.project.probook.exceptions.BookmarkDuplicateException;
import com.project.probook.exceptions.BookmarkInvalidEntryException;
import com.project.probook.persistence.domain.Bookmark;
import com.project.probook.persistence.repo.BookmarkRepo;

@Service
public class BookmarkService {

	private BookmarkRepo repo;

	@Autowired
	public BookmarkService(BookmarkRepo repo) {
		this.repo = repo;
	}

	public Bookmark createBookmark(Bookmark bookmark) throws BookmarkInvalidEntryException, BookmarkDuplicateException {
		validateBookmark(bookmark, true);
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
		return this.repo.findById(id).orElseThrow(() -> new BookmarkNotFoundException());
	}
	
	public boolean findRepeatedBookmark(Bookmark bookmark) {
		return this.readBookmarks().contains(bookmark);
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
	
	public Boolean validateBookmark(Bookmark bookmark, boolean newBookmark) throws BookmarkInvalidEntryException, BookmarkDuplicateException {
		if (bookmark.getName().length() > 50) {
			throw new BookmarkInvalidEntryException();
		}
		else if (findRepeatedBookmark(bookmark)) {
			throw new BookmarkDuplicateException();
		}
		else if (bookmark.getDescription().length() > 250) {
			throw new BookmarkInvalidEntryException();
		}
		else if (!(bookmark.getUrl().contains("http://") || bookmark.getUrl().contains("https://"))) {
			throw new BookmarkInvalidEntryException();
		}

		return true;
	}
	

}
