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

import com.project.probook.exceptions.TypeDuplicateException;
import com.project.probook.exceptions.TypeInvalidEntryException;
import com.project.probook.exceptions.TypeNotFoundException;
import com.project.probook.persistence.domain.Type;
import com.project.probook.persistence.repo.TypeRepo;


@RunWith (SpringRunner.class)
public class TypeServiceUnitTest {
	
	@InjectMocks
	private TypeService service;
	
	@Mock
	private TypeRepo repo;
	
	private List<Type> typeList;
	
	private Type testType;
	
	private Type testTypeWithId;
	
	final long id = 1L;
	
	@Before
	public void init() {
		this.typeList = new ArrayList<>();
		this.typeList.add(testType);
		this.testType = new Type("Software tools");
		this.testTypeWithId = new Type(testType.getName());
		this.testTypeWithId.setId(id);
	}
	
	@Test
	public void createTypeTest() throws TypeInvalidEntryException, TypeDuplicateException {
		when(this.repo.save(testType)).thenReturn(testTypeWithId);
		
		assertEquals(this.testTypeWithId, this.service.createType(testType));
		
		verify(this.repo, times(1)).save(this.testType);
	}
		
	@Test
	public void deleteTypeTest() throws TypeNotFoundException {
		when(this.repo.existsById(id)).thenReturn(true, false);
		
		this.service.deleteType(id);
		
		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(2)).existsById(id);
	}
	
	@Test
	public void findTypesByIDTest() throws TypeNotFoundException {
		when(this.repo.findById(id)).thenReturn(Optional.of(this.testTypeWithId));
		
		assertEquals(this.testTypeWithId, this.service.findTypeById(this.id));
		
		verify(this.repo, times(1)).findById(this.id);
	}
	
	@Test
	public void findAllTypesTest() {
		when(repo.findAll()).thenReturn(this.typeList);
		
		assertFalse("Controller has found no types", this.service.readTypes().isEmpty());
		
		verify(repo, times(1)).findAll();
	}
	
	@Test
	public void updateTypeTest() throws TypeNotFoundException {
		Type newType = new Type("Community Forums");
		Type updatedType = new Type(newType.getName());
		updatedType.setId(this.id);
		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testTypeWithId));
		when(this.repo.save(updatedType)).thenReturn(updatedType);
		
		assertEquals(updatedType, this.service.updateType(updatedType, this.id));
		
		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedType);
	}
	
}

