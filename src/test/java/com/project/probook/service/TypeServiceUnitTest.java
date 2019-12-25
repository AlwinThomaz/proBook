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
	
	private Type testTypeWithTypeId;
	
	final long typeId = 1L;
	
	@Before
	public void init() {
		this.typeList = new ArrayList<>();
		this.typeList.add(testType);
		this.testType = new Type("Software tools");
		this.testTypeWithTypeId = new Type(testType.getName());
		this.testTypeWithTypeId.setTypeId(typeId);
	}
	
	@Test
	public void createTypeTest() {
		when(this.repo.save(testType)).thenReturn(testTypeWithTypeId);
		
		assertEquals(this.testTypeWithTypeId, this.service.createType(testType));
		
		verify(this.repo, times(1)).save(this.testType);
	}
		
	@Test
	public void deleteTypeTest() throws TypeNotFoundException {
		when(this.repo.existsById(typeId)).thenReturn(true, false);
		
		this.service.deleteType(typeId);
		
		verify(this.repo, times(1)).deleteById(typeId);
		verify(this.repo, times(2)).existsById(typeId);
	}
	
	@Test
	public void findTypesByIDTest() throws TypeNotFoundException {
		when(this.repo.findById(typeId)).thenReturn(Optional.of(this.testTypeWithTypeId));
		
		assertEquals(this.testTypeWithTypeId, this.service.findTypeById(this.typeId));
		
		verify(this.repo, times(1)).findById(this.typeId);
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
		updatedType.setTypeId(this.typeId);
		
		when(this.repo.findById(this.typeId)).thenReturn(Optional.of(this.testTypeWithTypeId));
		when(this.repo.save(updatedType)).thenReturn(updatedType);
		
		assertEquals(updatedType, this.service.updateType(updatedType, this.typeId));
		
		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedType);
	}
	
}

