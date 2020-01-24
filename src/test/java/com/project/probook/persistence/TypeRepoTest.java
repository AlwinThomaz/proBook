//package com.project.probook.persistence;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.project.probook.persistence.domain.Type;
//import com.project.probook.persistence.repo.TypeRepo;
//
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class TypeRepoTest {
//
//	@Autowired
//	private TypeRepo repo;
//	
//	private final String TEST_NAME = "Software Tools";
//	
//	private final Type TEST_TYPE = new Type(TEST_NAME);
//	
//	private Type testSavedType;
//	
//	@Before
//	public void init() {
//		this.repo.deleteAll();
//		this.testSavedType = this.repo.save(this.TEST_TYPE);
//	}
//	
//	@Test
//	public void testFindByName() {
//		assertThat(this.repo.findByName(this.TEST_NAME)).containsExactly(this.testSavedType);
//	}
//}
