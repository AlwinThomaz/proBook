package com.project.probook.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.probook.exceptions.BookmarkNotFoundException;
import com.project.probook.persistence.domain.Bookmark;
import com.project.probook.service.BookmarkService;


@RunWith(SpringRunner.class)
public class BookmarkControllerUnitTest {
	

	@InjectMocks
	private BookmarkController controller;

	@Mock
	private BookmarkService service;

	private List<Bookmark> bookmarkList;

	private Bookmark testBookmark;

	private Bookmark testBookmarkWithBookmarkId;

	final long bookmarkId = 1L;
	
	@Before
	public void init() {
		this.bookmarkList = new ArrayList<>();
		this.bookmarkList.add(testBookmark);
		this.testBookmark = new Bookmark("Freecodecamp", "Place to discuss and learn coding", "https://www.freecodecamp.org");
		this.testBookmarkWithBookmarkId = new Bookmark(testBookmark.getName(), testBookmark.getDescription(), testBookmark.getUrl());
		this.testBookmarkWithBookmarkId.setBookmarkId(bookmarkId);		
	}

	@Test
	public void createBookmarkTest() {
		when(this.service.createBookmark(testBookmark)).thenReturn(testBookmarkWithBookmarkId);

		assertEquals(this.testBookmarkWithBookmarkId, this.controller.createBookmark(testBookmark));

		verify(this.service, times(1)).createBookmark(this.testBookmark);
	}

	@Test
	public void deleteBookmarkTest() throws BookmarkNotFoundException {
		this.controller.deleteBookmark(bookmarkId);

		verify(this.service, times(1)).deleteBookmark(bookmarkId);
	}
	
	@Test
	public void updateBookmarkTest() throws BookmarkNotFoundException {
		
		Bookmark newBookmark = new Bookmark("Udemy", "Java online course", "https://www.udemy.com/topic/java/");
		Bookmark updatedBookmark = new Bookmark(newBookmark.getName(), newBookmark.getDescription(), newBookmark.getUrl());
		updatedBookmark.setBookmarkId(this.bookmarkId);

		when(this.service.updateBookmark(newBookmark, this.bookmarkId)).thenReturn(updatedBookmark);

		assertEquals(updatedBookmark, this.controller.updateBookmark(this.bookmarkId, newBookmark));

		verify(this.service, times(1)).updateBookmark(newBookmark, this.bookmarkId);
	}

	@Test
	public void findBookmarksByIDTest() throws BookmarkNotFoundException {
		when(this.service.findBookmarkById(this.bookmarkId)).thenReturn(this.testBookmarkWithBookmarkId);

		assertEquals(this.testBookmarkWithBookmarkId, this.controller.getBookmark(this.bookmarkId));

		verify(this.service, times(1)).findBookmarkById(this.bookmarkId);
	}

	@Test
	public void getAllBookmarksTest() {

		when(service.readBookmarks()).thenReturn(this.bookmarkList);

		assertFalse("Controller has found no bookmarks", this.controller.getAllBookmarks().isEmpty());

		verify(service, times(1)).readBookmarks();
	}

	
}

