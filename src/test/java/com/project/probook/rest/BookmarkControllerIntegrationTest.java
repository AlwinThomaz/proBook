package com.project.probook.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.probook.persistence.domain.Bookmark;
import com.project.probook.persistence.repo.BookmarkRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookmarkControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;

	@Autowired
	private BookmarkRepo repo;

	private ObjectMapper mapper = new ObjectMapper();

	private long id;

	private Bookmark testBookmark;

	private Bookmark testBookmarkWithId;
	

	@Before
	public void init() {
		this.repo.deleteAll();

		this.testBookmark = new Bookmark("AlgoExpert", "Interview Preparation resource", "http://www.algoexpert.com");
		this.testBookmarkWithId = this.repo.save(this.testBookmark);
		this.id = this.testBookmarkWithId.getId();
	}

	@Test
	public void testCreateBookmark() throws Exception {
		this.repo.deleteAll();
		String result = this.mock
				.perform(request(HttpMethod.POST, "/bookmark/createBookmark").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(testBookmark)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		testBookmarkWithId = repo.th();//TODO change id to that repo
		testBookmarkWithId.setId(testBookmarkWithId.getId()+1);
		assertEquals(this.mapper.writeValueAsString(testBookmarkWithId), result);
	}

	@Test
	public void testDeleteBookmark() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/bookmark/deleteBookmark/" + this.id)).andExpect(status().isOk());
	}

	@Test
	public void testGetAllBookmarks() throws Exception {
		List<Bookmark> bookmarkList = new ArrayList<>();
		bookmarkList.add(this.testBookmarkWithId);

		String content = this.mock.perform(request(HttpMethod.GET, "/bookmark/getAllBookmarks").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(bookmarkList), content);
	}

	@Test
	public void testUpdateBookmark() throws Exception {
		Bookmark newBookmark = new Bookmark("Oracle", "Resource to find all information on Java", "http://www.oracle.com");
		Bookmark updatedBookmark = new Bookmark(newBookmark.getName(), newBookmark.getDescription(), newBookmark.getUrl());
		updatedBookmark.setId(this.id);

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/bookmark/updateBookmark?id=" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newBookmark)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertEquals(this.mapper.writeValueAsString(updatedBookmark), result);
	}

}

