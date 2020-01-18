package com.project.probook.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.project.probook.exceptions.BookmarkDuplicateException;
import com.project.probook.exceptions.BookmarkInvalidEntryException;
import com.project.probook.exceptions.BookmarkNotFoundException;
import com.project.probook.persistence.domain.Bookmark;
import com.project.probook.persistence.repo.BookmarkRepo;

@RunWith(SpringRunner.class)
public class BookmarkServiceUnitTest {

	@InjectMocks
	private BookmarkService service;

	@Mock
	private BookmarkRepo repo;

	private List<Bookmark> bookmarkList;

	private Bookmark testBookmark;

	private Bookmark testBookmarkWithId;
	
	private Bookmark testBookmarkFail;
	
	private Bookmark testBookmarkFailWithId;

	final long id = 1L;

	private String name51 = "udsx6umwunzjz6s00fvc0jmlv26e8rj8k7cj5t2grg83h1nnblj";

	private String description251 = "rzl94za2dh7mocr03cnawrfl0cnabvs873f70jh5o8dd0vkp2ekkp0g4fkknx0h3lzeiwfl6edvyytfu9o7wyd7cr5cn6fxlqgiajo6pt7bn6siydyk6x5yl7y1divkpq4rzj3iqjf3nkhz9xbyd0zl3lox66j1p5ikr5gistj45uwn1my5aphj0h50r9w6f6pu1t3mrgf2kg9xjzvacro7hiuqbc6hwe7bon8f399jd4tw2pckdsv4lkby";

	private String urlNoWebAdress = "test123.com";
	
	@Before
	public void init() {
		this.bookmarkList = new ArrayList<>();
		this.testBookmark = new Bookmark("Freecodecamp", "Place to discuss and learn coding",
				"http://www.freecodecamp.org");
		this.testBookmarkWithId = new Bookmark(testBookmark.getName(), testBookmark.getDescription(),
				testBookmark.getUrl());
		this.testBookmarkWithId.setId(id);
		this.bookmarkList.add(testBookmark);
		this.bookmarkList.add(testBookmark);
		this.testBookmarkFail = new Bookmark(testBookmark.getName(), testBookmark.getDescription(),
				testBookmark.getUrl());
		this.testBookmarkFail.setId(id);
		this.testBookmarkFailWithId = new Bookmark(testBookmarkFail.getName(), testBookmarkFail.getDescription(),
				testBookmarkFail.getUrl());
		this.testBookmarkFailWithId.setId(id);
		
	}

	@Test
	public void createBookmarkTest() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
		when(this.repo.save(testBookmark)).thenReturn(testBookmarkWithId);

		assertEquals(this.testBookmarkWithId, this.service.createBookmark(testBookmark));

		verify(this.repo, times(1)).save(this.testBookmark);
	}

	@Test
	public void deleteBookmarkTest() throws BookmarkNotFoundException {
		when(this.repo.existsById(id)).thenReturn(true, false);

		this.service.deleteBookmark(id);

		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(2)).existsById(id);
	}

	@Test
	public void findBookmarksByIdTest() throws BookmarkNotFoundException {
		when(this.repo.findById(id)).thenReturn(Optional.of(this.testBookmarkWithId));

		assertEquals(this.testBookmarkWithId, this.service.findBookmarkById(this.id));

		verify(this.repo, times(1)).findById(this.id);
	}

	@Test
	public void findAllBookmarksTest() {
		when(repo.findAll()).thenReturn(this.bookmarkList);

		assertFalse("Controller has found no bookmarks", this.service.readBookmarks().isEmpty());

		verify(repo, times(1)).findAll();
	}

	@Test
	public void updateBookmarkTest() throws BookmarkNotFoundException {
		Bookmark newBookmark = new Bookmark("Udemy", "Java online course", "http://www.udemy.com/topic/java/");
		Bookmark updatedBookmark = new Bookmark(newBookmark.getName(), newBookmark.getDescription(),
				newBookmark.getUrl());
		updatedBookmark.setId(this.id);

		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testBookmarkWithId));
		when(this.repo.save(updatedBookmark)).thenReturn(updatedBookmark);

		assertEquals(updatedBookmark, this.service.updateBookmark(updatedBookmark, this.id));

		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedBookmark);
	}
	
	@Test
	public void bookmarkDuplicateTest() {
		testBookmarkFail.setName("Freecodecamp");
		when(this.repo.findAll()).thenReturn(this.bookmarkList);
		assertTrue(this.service.findRepeatedBookmark(this.testBookmark));
		}
	
	@Test
	public void createBookmarkDuplicateTest() {
		when(this.repo.findAll()).thenReturn(this.bookmarkList);
		assertThrows(BookmarkDuplicateException.class, () -> {
			this.service.createBookmark(this.testBookmark);
			verify(this.repo, times(1)).findAll();
		});
	}
	
	@Test
	public void invalidNameTest() {
		this.testBookmarkFail.setName(name51);
		assertThrows(BookmarkInvalidEntryException.class, () -> {
			this.service.createBookmark(this.testBookmarkFail);
		});
	}

	@Test
	public void invalidDescriptionTest() {
		this.testBookmarkFail.setDescription(description251);
		assertThrows(BookmarkInvalidEntryException.class, () -> {
			this.service.createBookmark(this.testBookmarkFail);
		});
	}
	
	@Test
	public void urlVerifyTest() {
		this.testBookmarkFail.setUrl(urlNoWebAdress);
		assertThrows(BookmarkInvalidEntryException.class, () -> {
			this.service.createBookmark(this.testBookmarkFail);
		});
	}
	
	

}
