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
		when(this.service.createType(testType)).thenReturn(testTypeWithTypeId);

		assertEquals(this.testTypeWithTypeId, this.controller.createType(testType));

		verify(this.service, times(1)).createType(this.testType);
	}

	@Test
	public void deleteTypeTest() throws TypeNotFoundException {
		this.controller.deleteType(typeId);

		verify(this.service, times(1)).deleteType(typeId);
	}

	@Test
	public void updateTypeTest() throws TypeNotFoundException {

		Type newType = new Type("Community Forums");
		Type updatedType = new Type(newType.getName());
		updatedType.setTypeId(this.typeId);

		when(this.service.updateType(newType, this.typeId)).thenReturn(updatedType);

		assertEquals(updatedType, this.controller.updateType(this.typeId, newType));

		verify(this.service, times(1)).updateType(newType, this.typeId);
	}

	@Test
	public void findTypesByIDTest() throws TypeNotFoundException {
		when(this.service.findTypeById(this.typeId)).thenReturn(this.testTypeWithTypeId);

		assertEquals(this.testTypeWithTypeId, this.controller.getType(this.typeId));

		verify(this.service, times(1)).findTypeById(this.typeId);
	}

	@Test
	public void getAllTypesTest() {

		when(service.readTypes()).thenReturn(this.typeList);

		assertFalse("Controller has found no types", this.controller.getAllTypes().isEmpty());

		verify(service, times(1)).readTypes();
	}

}

