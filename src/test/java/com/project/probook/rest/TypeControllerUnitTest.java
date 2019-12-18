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

		verify(this.service, times(1)).updatetype(newType, this.typeId);
	}

	@Test
	public void findProjectsByIDTest() throws ProjectNotFoundException {
		when(this.service.findProjectById(this.id)).thenReturn(this.testProjectWithID);

		assertEquals(this.testProjectWithID, this.controller.getProject(this.id));

		verify(this.service, times(1)).findProjectById(this.id);
	}

	@Test
	public void getAllProjectsTest() {

		when(service.getAllProjects()).thenReturn(this.projectList);

		assertFalse("Controller has found no projects", this.controller.getAllProjects().isEmpty());

		verify(service, times(1)).getAllProjects();
	}

}

