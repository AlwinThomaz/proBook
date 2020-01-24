//package com.project.probook.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.assertj.core.util.Arrays;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.project.probook.exceptions.BookmarkDuplicateException;
//import com.project.probook.exceptions.BookmarkInvalidEntryException;
//import com.project.probook.exceptions.BookmarkNotFoundException;
//import com.project.probook.persistence.domain.Bookmark;
//import com.project.probook.persistence.repo.BookmarkRepo;
//
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class BookmarkServiceIntegrationTest {
//	
//	@Autowired
//	private BookmarkService service;
//	
//	@Autowired
//	private BookmarkRepo repo;
//
//	private Bookmark testBookmark;
//
//	private Bookmark testBookmarkWithId;
//	
//	private final Long id = 2L;
//	
//	private Bookmark testBookmark2;
//
//	private Bookmark testBookmark2WithId;
//	
//	private String name51 = "udsx6umwunzjz6s00fvc0jmlv26e8rj8k7cj5t2grg83h1nnblj";
//
//	private String description251 = "rzl94za2dh7mocr03cnawrfl0cnabvs873f70jh5o8dd0vkp2ekkp0g4fkknx0h3lzeiwfl6edvyytfu9o7wyd7cr5cn6fxlqgiajo6pt7bn6siydyk6x5yl7y1divkpq4rzj3iqjf3nkhz9xbyd0zl3lox66j1p5ikr5gistj45uwn1my5aphj0h50r9w6f6pu1t3mrgf2kg9xjzvacro7hiuqbc6hwe7bon8f399jd4tw2pckdsv4lkby";
//
//	private String urlNoWebAdress = "test123.com";
//	
//
//	@Before
//	public void init() {
//		this.repo.deleteAll();
//		
//		this.testBookmark = new Bookmark("AlgoExpert", "Interview Preparation resource", "http://www.algoexpert.com");
//		this.testBookmarkWithId = this.repo.save(this.testBookmark);
//		
//		this.testBookmark2 = new Bookmark("Java", "Learn Java", "http://www.java.com");
//		this.testBookmark2WithId = new Bookmark(this.testBookmark2.getName(), this.testBookmark2.getDescription(), this.testBookmark2.getUrl());
//		this.testBookmark2WithId.setId(this.id);
//		
//	}
//	
//	@Test
//	public void testCreateBookmark() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
//		this.repo.deleteAll();
//		assertEquals(this.testBookmarkWithId, this.repo.save(this.testBookmark));
//	}
//
//	@Test
//	public void testDeleteBookmark() throws BookmarkNotFoundException {
//		assertThat(this.service.deleteBookmark(this.testBookmarkWithId.getId())).isFalse();
//	}
//
//	@Test
//	public void testFindBookmarkById() throws BookmarkNotFoundException {
//		assertThat(this.service.findBookmarkById(this.testBookmarkWithId.getId())).isEqualTo(this.testBookmarkWithId);
//	}
//
//	@Test
//	public void testReadBookmarks() {
//		assertThat(this.service.readBookmarks()).isEqualTo(Arrays.asList(new Bookmark[] { this.testBookmarkWithId }));
//	}
//
//	@Test
//	public void testUpdateBookmark() throws BookmarkNotFoundException {
//		Bookmark updatedBookmark = new Bookmark(this.testBookmark2.getName(), this.testBookmark2.getDescription(), this.testBookmark2.getUrl());
//		updatedBookmark.setId(testBookmarkWithId.getId());
//		assertThat(updatedBookmark).isEqualToComparingFieldByField(this.service.updateBookmark(testBookmark2, this.testBookmarkWithId.getId()));
//	}
//	
//	
//	@Test
//	public void findBookmarkByIdTest() throws BookmarkNotFoundException {
//		assertThat(this.testBookmarkWithId).isEqualToComparingFieldByField(this.service.findBookmarkById(this.testBookmarkWithId.getId()));
//	}
//	
//	@Test
//	public void getBookmarksTest() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
//		this.testBookmark2WithId = this.service.createBookmark(this.testBookmark2);
//		assertThat(Arrays.asList(new Bookmark[] {this.testBookmarkWithId, this.testBookmark2WithId})).isEqualTo(this.service.readBookmarks());
//	}
//	
//	@Test
//	public void findRepeatedBookmarkExistsTest() {
//		assertTrue(this.service.findRepeatedBookmark(this.testBookmark));
//	}
//	
//	@Test
//	public void findRepeatedBookmarkDoesNotExistTest() {
//		assertFalse(this.service.findRepeatedBookmark(this.testBookmark2));
//	}
//	
//	
//	@Test
//	public void validateNewBookmarkTest() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
//		assertTrue(this.service.validateBookmark(testBookmark2WithId, true));
//	}
//
//	
//	@Test(expected = BookmarkDuplicateException.class)
//	public void validateNewBookmarkAlreadyExistsTest() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
//		this.service.validateBookmark(testBookmarkWithId, true);
//		fail();
//	}
//	
//	@Test(expected = BookmarkInvalidEntryException.class)
//	public void validateNewBookmarkNameTooLongTest() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
//		this.testBookmark2WithId.setName(this.name51);
//		this.service.validateBookmark(testBookmark2WithId, true);
//		fail();
//	}
//	
//	@Test(expected = BookmarkInvalidEntryException.class)
//	public void validateExistingBookmarkNameTooLongTest() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
//		this.testBookmarkWithId.setName(this.name51);
//		this.service.validateBookmark(testBookmarkWithId, false);
//		fail();
//	}
//	
//	@Test(expected = BookmarkInvalidEntryException.class)
//	public void validateNewBookmarkDescritpionTooLongTest() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
//		this.testBookmark2WithId.setDescription(this.description251);
//		this.service.validateBookmark(testBookmark2WithId, true);
//		fail();
//	}
//	
//	@Test(expected = BookmarkInvalidEntryException.class)
//	public void validateExistingBookmarkDescriptionTooLongTest() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
//		this.testBookmarkWithId.setDescription(this.description251);
//		this.service.validateBookmark(testBookmarkWithId, false);
//		fail();
//	}
//	
//	@Test(expected = BookmarkInvalidEntryException.class)
//	public void validateNewBookmarkUrlIncorrectTest() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
//		this.testBookmark2WithId.setUrl(this.urlNoWebAdress);
//		this.service.validateBookmark(testBookmark2WithId, true);
//		fail();
//	}
//	
//	@Test(expected = BookmarkInvalidEntryException.class)
//	public void validateExistingBookmarkUrlIncorrectTest() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
//		this.testBookmarkWithId.setUrl(this.urlNoWebAdress);
//		this.service.validateBookmark(testBookmarkWithId, false);
//		fail();
//	}
//
//	
//}
