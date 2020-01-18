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

import com.project.probook.exceptions.BookmarkDuplicateException;
import com.project.probook.exceptions.BookmarkInvalidEntryException;
import com.project.probook.exceptions.BookmarkNotFoundException;
import com.project.probook.persistence.domain.Bookmark;
import com.project.probook.service.BookmarkService;
import com.project.probook.service.TypeService;


@RunWith(SpringRunner.class)
public class BookmarkControllerUnitTest {
	

	@InjectMocks
	private BookmarkController controller;

	@Mock
	private BookmarkService service;
	
	@Mock
	private TypeService typeService;

	private List<Bookmark> bookmarkList;

	private Bookmark testBookmark;

	private Bookmark testBookmarkWithId;

	final long id = 1L;
	
	@Before
	public void init() {
		this.bookmarkList = new ArrayList<>();
		this.bookmarkList.add(testBookmark);
		this.testBookmark = new Bookmark("Freecodecamp", "Place to discuss and learn coding", "http://www.freecodecamp.org");
		this.testBookmarkWithId = new Bookmark(testBookmark.getName(), testBookmark.getDescription(), testBookmark.getUrl());
		this.testBookmarkWithId.setId(id);		
	}

	@Test
	public void createBookmarkTest() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
		when(this.service.createBookmark(testBookmark)).thenReturn(testBookmarkWithId);

		assertEquals(this.testBookmarkWithId, this.controller.createBookmark(testBookmark));

		verify(this.service, times(1)).createBookmark(this.testBookmark);
	}

	@Test
	public void deleteBookmarkTest() throws BookmarkNotFoundException {
		this.controller.deleteBookmark(id);

		verify(this.service, times(1)).deleteBookmark(id);
	}
	
	@Test
	public void updateBookmarkTest() throws BookmarkNotFoundException {
		
		Bookmark newBookmark = new Bookmark("Udemy", "Java online course", "http://www.udemy.com/topic/java/");
		Bookmark updatedBookmark = new Bookmark(newBookmark.getName(), newBookmark.getDescription(), newBookmark.getUrl());
		updatedBookmark.setId(this.id);

		when(this.service.updateBookmark(newBookmark, this.id)).thenReturn(updatedBookmark);

		assertEquals(updatedBookmark, this.controller.updateBookmark(this.id, newBookmark));

		verify(this.service, times(1)).updateBookmark(newBookmark, this.id);
	}

	@Test
	public void findBookmarksByIDTest() throws BookmarkNotFoundException {
		when(this.service.findBookmarkById(this.id)).thenReturn(this.testBookmarkWithId);

		assertEquals(this.testBookmarkWithId, this.controller.getBookmark(this.id));

		verify(this.service, times(1)).findBookmarkById(this.id);
	}

	@Test
	public void getAllBookmarksTest() {

		when(service.readBookmarks()).thenReturn(this.bookmarkList);

		assertFalse("Controller has found no bookmarks", this.controller.getAllBookmarks().isEmpty());

		verify(service, times(1)).readBookmarks();
	}
	
}

