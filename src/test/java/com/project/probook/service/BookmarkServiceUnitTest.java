package com.project.probook.service;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.probook.exceptions.BookmarkNotFoundException;
import com.project.probook.persistence.domain.Bookmark;
import com.project.probook.persistence.repo.BookmarkRepo;


@RunWith (SpringRunner.class)
public class BookmarkServiceUnitTest {
	
	@InjectMocks
	private BookmarkService service;
	
	@Mock
	private BookmarkRepo repo;
	
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
		when(this.repo.save(testBookmark)).thenReturn(testBookmarkWithBookmarkId);
		
		assertEquals(this.testBookmarkWithBookmarkId, this.service.createBookmark(testBookmark));
		
		verify(this.repo, times(1)).save(this.testBookmark);
	}
		
	@Test
	public void deleteBookmarkTest() throws BookmarkNotFoundException {
		when(this.repo.existsById(bookmarkId)).thenReturn(true, false);
		
		this.service.deleteBookmark(bookmarkId);
		
		verify(this.repo, times(1)).deleteById(bookmarkId);
		verify(this.repo, times(2)).existsById(bookmarkId);
	}
	
	@Test
	public void findBookmarksByIDTest() throws BookmarkNotFoundException {
		when(this.repo.findById(bookmarkId)).thenReturn(Optional.of(this.testBookmarkWithBookmarkId));
		
		assertEquals(this.testBookmarkWithBookmarkId, this.service.findBookmarkById(this.bookmarkId));
		
		verify(this.repo, times(1)).findById(this.bookmarkId);
	}
	
	@Test
	public void findAllBookmarksTest() {
		when(repo.findAll()).thenReturn(this.bookmarkList);
		
		assertFalse("Controller has found no bookmarks", this.service.readBookmarks().isEmpty());
		
		verify(repo, times(1)).findAll();
	}
	
	@Test
	public void updateBookmarkTest() throws BookmarkNotFoundException {
		Bookmark newBookmark = new Bookmark("Udemy", "Java online course", "https://www.udemy.com/topic/java/");
		Bookmark updatedBookmark = new Bookmark(newBookmark.getName(), newBookmark.getDescription(), newBookmark.getUrl());
		updatedBookmark.setBookmarkId(this.bookmarkId);
		
		when(this.repo.findById(this.bookmarkId)).thenReturn(Optional.of(this.testBookmarkWithBookmarkId));
		when(this.repo.save(updatedBookmark)).thenReturn(updatedBookmark);
		
		assertEquals(updatedBookmark, this.service.updateBookmark(updatedBookmark, this.bookmarkId));
		
		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedBookmark);
	}
	
	
	
}

