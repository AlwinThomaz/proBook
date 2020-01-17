package com.project.probook.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.probook.exceptions.TypeDuplicateException;
import com.project.probook.exceptions.TypeInvalidEntryException;
import com.project.probook.exceptions.TypeNotFoundException;
import com.project.probook.persistence.domain.Type;
import com.project.probook.persistence.repo.TypeRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeServiceIntegrationTest {

	@Autowired
	private TypeService service;
	
	@Autowired
	private TypeRepo repo;

	private Type testType;

	private Type testTypeWithId;
	
	private Type testType2;

	private Type testType2WithId;
	
	private final Long id = 2L;
	
	private String name51 = "udsx6umwunzjz6s00fvc0jmlv26e8rj8k7cj5t2grg83h1nnblj";

	@Before
	public void init() {
		this.testType = new Type("Programming");
		
		this.repo.deleteAll();
		
		this.testTypeWithId = this.repo.save(this.testType);
		
		this.testType2 = new Type("Articles");
		this.testType2WithId = new Type(this.testType2.getName());
		this.testType2WithId.setId(this.testTypeWithId.getId() + 1);
		
	}
	
	@Test
	public void testCreateType() throws TypeInvalidEntryException, TypeDuplicateException {
		this.repo.deleteAll();
		assertEquals(this.testType2WithId, this.repo.save(this.testType2));
	}

	@Test
	public void testDeleteType() throws TypeNotFoundException {
		assertThat(this.service.deleteType(this.testTypeWithId.getId())).isFalse();
	}

	@Test
	public void testFindTypeById() throws TypeNotFoundException {
		assertThat(this.service.findTypeById(this.testTypeWithId.getId())).isEqualTo(this.testTypeWithId);
	}

	@Test
	public void testReadTypes() {
		assertThat(this.service.readTypes()).isEqualTo(Arrays.asList(new Type[] { this.testTypeWithId }));
	}

	@Test
	public void testUpdateType() throws TypeNotFoundException {
		Type newType = new Type("Test123");
		Type updatedType = new Type(newType.getName());
		updatedType.setId(this.testTypeWithId.getId());

		assertThat(this.service.updateType(newType, this.testTypeWithId.getId())).isEqualTo(updatedType);
	}
	
	@Test
	public void findTypeByIdTest() throws TypeNotFoundException {
		assertThat(this.testTypeWithId).isEqualTo(this.service.findTypeById(this.testTypeWithId.getId()));
	}
	
	@Test
	public void getTypesTest() throws TypeInvalidEntryException, TypeDuplicateException {
		this.testType2WithId = this.service.createType(this.testType2);
		assertThat(Arrays.asList(new Type[] {this.testTypeWithId, this.testType2WithId})).isEqualTo(this.service.readTypes());
	}
	
	@Test
	public void findRepeatedTypeExistsTest() {
		assertTrue(this.service.findRepeatedType(this.testType));
	}
	
	@Test
	public void findRepeatedTypeDoesNotExistTest() {
		assertFalse(this.service.findRepeatedType(this.testType2));
	}
	
	
	@Test
	public void validateNewTypeTest() throws TypeInvalidEntryException, TypeDuplicateException {
		assertTrue(this.service.validateType(testType2WithId, true));
	}



}

