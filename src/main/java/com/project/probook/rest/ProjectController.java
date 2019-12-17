package com.project.probook.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.probook.exceptions.ProjectNotFoundException;
import com.project.probook.persistence.domain.Project;
import com.project.probook.service.ProjectService;


@RestController
public class ProjectController {
	private ProjectService service;
	
	@Autowired
	public ProjectController(ProjectService service) {
		super();
		this.service = service;
	}
	@PostMapping("/createProject")
	public Project createProject(@RequestBody Project projectToAdd) {
		return this.service.createProject(projectToAdd);
	}

	@DeleteMapping("/deleteProject/{id}")
	public void deleteProject(@PathVariable Long id) throws ProjectNotFoundException {
		this.service.deleteProject(id);
	}
	
	@GetMapping("/getProject/{id}")
	public Project getProject(@PathVariable Long id) throws ProjectNotFoundException {
		return this.service.findProjectById(id);
	}

	@GetMapping("/getAllProjects")
	public List<Project> getAllProjects() {
		return this.service.getAllProjects();
	}

	@PutMapping("/updateProject")
	public Project updateProject(@PathParam("id") Long id, @RequestBody Project project) throws ProjectNotFoundException {
		return this.service.updateProject(project, id);
	}

}



