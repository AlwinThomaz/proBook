//package com.project.probook.rest;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.project.probook.persistence.domain.Type;
//import com.project.probook.persistence.repo.TypeRepo;
//
//
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TypeControllerIntegrationTest {
//
//	@Autowired
//	private MockMvc mock;
//	
//	@Autowired
//	private TypeRepo repo;
//
//	
//	private ObjectMapper mapper = new ObjectMapper();
//	
//	private Type testType;
//	
//	private Type testTypeWithId;
//	
//	private Type testType2;
//
//	private Type testType2WithId;
//	
//	private long id;	
//	
//	@Before
//	public void init() {
//		this.repo.deleteAll();
//		
//		this.testType = new Type("Programming");
//		this.testTypeWithId = this.repo.save(this.testType);
//		this.id = this.testTypeWithId.getId();
//		
//		this.testType2 = new Type("Articles");	
//		this.testType2WithId = new Type(this.testType2.getName());
//		this.testType2WithId.setId(this.testTypeWithId.getId() + 1);
//
//	}
//	
//	@After
//	public void deleteAll() {
//		this.repo.deleteAll();
//	
//	}
//	
//	@Test
//	public void createTypeTest() throws Exception {
//		String result = this.mock
//				.perform(request(HttpMethod.POST, "/type/createType").contentType(MediaType.APPLICATION_JSON)
//				.content(this.mapper.writeValueAsString(this.testType2)).accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		assertEquals(this.mapper.writeValueAsString(this.testType2WithId), result);
//	}
//	
//	@Test
//	public void getAllTypesTest() throws Exception {
//		List<Type> typeList = new ArrayList<>();
//		typeList.add(this.testTypeWithId);
//		
//		String content = this.mock.perform(request(HttpMethod.GET, "/type/getAllTypes").accept(MediaType.APPLICATION_JSON))
//							.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		
//		assertEquals(this.mapper.writeValueAsString(typeList), content);
//	}
//	
//	@Test
//	public void getTypeTest() throws Exception {
//		String url = "/type/getType/" + this.id;
//		String content = this.mock.perform(request(HttpMethod.GET, url).accept(MediaType.APPLICATION_JSON))
//							.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		
//		assertEquals(this.mapper.writeValueAsString(testTypeWithId), content);
//	}
//	
//	@Test
//	public void deleteTypeTest() throws Exception {
//		String url = "/type/deleteType/" + this.id;
//		this.mock.perform(request(HttpMethod.DELETE, url)).andExpect(status().isOk());
//	}
//	
//	@Test
//	public void updateTypeTest() throws Exception {	
//		Type newType = new Type("Test");
//		Type updatedType = new Type(newType.getName());
//		updatedType.setId(this.id);
//		String url = "/type/updateType?id=" + this.id;
//		
//		String result = this.mock
//						.perform(request(HttpMethod.PUT, url).accept(MediaType.APPLICATION_JSON)
//						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newType)))
//						.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		assertEquals(this.mapper.writeValueAsString(updatedType), result);
//	}
//	
//}
