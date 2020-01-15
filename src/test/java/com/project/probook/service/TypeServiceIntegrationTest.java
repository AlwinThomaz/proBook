package com.project.probook.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeServiceIntegrationTest {

	@Autowired
	private BookmarkService service;
	
	@Autowired
	private BookmarkRepo repo;

	private Bookmark testBookmark;

	private Bookmark testBookmarkWithId;

	@Before
	public void init() {
		this.testBookmark = new Bookmark("AlgoExpert", "Interview Preparation resource", "www.algoexpert.com");
		
		this.repo.deleteAll();
		
		this.testBookmarkWithId = this.repo.save(this.testBookmark);
	}
	
	@Test
	public void testCreateBookmark() throws BookmarkInvalidEntryException, BookmarkDuplicateException {
		this.repo.deleteAll();
		assertEquals(this.testBookmarkWithId, this.repo.save(this.testBookmark));
	}

	@Test
	public void testDeleteBookmark() throws BookmarkNotFoundException {
		assertThat(this.service.deleteBookmark(this.testBookmarkWithId.getId())).isFalse();
	}

	@Test
	public void testFindBookmarkById() throws BookmarkNotFoundException {
		assertThat(this.service.findBookmarkById(this.testBookmarkWithId.getId())).isEqualTo(this.testBookmarkWithId);
	}

	@Test
	public void testReadBookmarks() {
		assertThat(this.service.readBookmarks()).isEqualTo(Arrays.asList(new Bookmark[] { this.testBookmarkWithId }));
	}

	@Test
	public void testUpdateBookmark() throws BookmarkNotFoundException {
		Bookmark newBookmark = new Bookmark("Oracle", "Resource to find all information on Java", "www.oracle.com");
		Bookmark updatedBookmark = new Bookmark(newBookmark.getName(), newBookmark.getDescription(), newBookmark.getUrl());
		updatedBookmark.setId(this.testBookmarkWithId.getId());

		assertThat(this.service.updateBookmark(newBookmark, this.testBookmarkWithId.getId())).isEqualTo(updatedBookmark);
	}

}

