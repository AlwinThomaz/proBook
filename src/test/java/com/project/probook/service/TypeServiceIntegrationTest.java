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

	@Before
	public void init() {
		this.testType = new Type("Test");
		
		this.repo.deleteAll();
		
		this.testTypeWithId = this.repo.save(this.testType);
	}
	
	@Test
	public void testCreateType() throws TypeInvalidEntryException, TypeDuplicateException {
		this.repo.deleteAll();
		assertEquals(this.testTypeWithId, this.repo.save(this.testType));
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

}

