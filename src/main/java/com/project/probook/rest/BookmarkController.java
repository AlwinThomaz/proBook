package com.project.probook.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.probook.exceptions.BookmarkDuplicateException;
import com.project.probook.exceptions.BookmarkInvalidEntryException;
import com.project.probook.exceptions.BookmarkNotFoundException;
import com.project.probook.persistence.domain.Bookmark;
import com.project.probook.service.BookmarkService;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

	private BookmarkService service;

	@Autowired
	public BookmarkController(BookmarkService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createBookmark")
	public Bookmark createBookmark(@RequestBody Bookmark bookmark) throws BookmarkInvalidEntryException, BookmarkDuplicateException {
		return this.service.createBookmark(bookmark);
	}

	@DeleteMapping("/deleteBookmark/{id}")
	public void deleteBookmark(@PathVariable Long id) throws BookmarkNotFoundException {
		this.service.deleteBookmark(id);
	}

	@GetMapping("/getBookmark/{id}")
	public Bookmark getBookmark(@PathVariable Long id) throws BookmarkNotFoundException {
		return this.service.findBookmarkById(id);
	}

	@GetMapping("/getAllBookmarks")
	public List<Bookmark> getAllBookmarks() {
		return this.service.readBookmarks();
	}

	@PutMapping("/updateBookmark")
	public Bookmark updateBookmark(@PathParam("id") Long id, @RequestBody Bookmark bookmark)
			throws BookmarkNotFoundException {
		return this.service.updateBookmark(bookmark, id);
	}

}
