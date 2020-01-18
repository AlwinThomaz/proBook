package com.project.probook.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.project.probook.service.TypeService;


@RunWith(SpringRunner.class)
public class TypeControllerUnitTest {

	@InjectMocks
	private TypeController controller;

	@Mock
	private TypeService service;

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
		when(this.service.createType(testType)).thenReturn(testTypeWithId);

		assertEquals(this.testTypeWithId, this.controller.createType(testType));

		verify(this.service, times(1)).createType(this.testType);
	}

	@Test
	public void deleteTypeTest() throws TypeNotFoundException {
		this.controller.deleteType(id);

		verify(this.service, times(1)).deleteType(id);
	}

	@Test
	public void updateTypeTest() throws TypeNotFoundException {

		Type newType = new Type("Community Forums");
		Type updatedType = new Type(newType.getName());
		updatedType.setId(this.id);

		when(this.service.updateType(newType, this.id)).thenReturn(updatedType);

		assertEquals(updatedType, this.controller.updateType(this.id, newType));

		verify(this.service, times(1)).updateType(newType, this.id);
	}

	@Test
	public void findTypesByIdTest() throws TypeNotFoundException {
		when(this.service.findTypeById(this.id)).thenReturn(this.testTypeWithId);

		assertEquals(this.testTypeWithId, this.controller.getType(this.id));

		verify(this.service, times(1)).findTypeById(this.id);
	}

	@Test
	public void getAllTypesTest() {

		when(service.readTypes()).thenReturn(this.typeList);

		assertFalse("Controller has found no types", this.controller.getAllTypes().isEmpty());

		verify(service, times(1)).readTypes();
	}

}

