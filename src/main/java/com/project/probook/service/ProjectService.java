package com.project.probook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.probook.exceptions.ProjectNotFoundException;
import com.project.probook.persistence.domain.Project;
import com.project.probook.persistence.repo.ProjectRepo;


@Service
public class ProjectService {
	
private ProjectRepo repo;
	
	@Autowired
	public ProjectService(ProjectRepo repo) {
		this.repo = repo;
	}
	
	public Project createProject(Project project) {
		return this.repo.save(project);
	}
	
	public boolean deleteProject(Long projectId) throws ProjectNotFoundException {
		if (!this.repo.existsById(projectId)) {
			throw new ProjectNotFoundException();
		}
		this.repo.deleteById(projectId);
		return this.repo.existsById(projectId);
	}
	
	public Project findProjectById(Long id) throws ProjectNotFoundException {
		return this.repo.findById(id).orElseThrow(
				() -> new ProjectNotFoundException());
	}
	
	public List<Project> readProjects() {
		return this.repo.findAll();
	}

	public Project updateProject(Project project, Long id) throws ProjectNotFoundException {
		Project toUpdate = findProjectById(id);
		toUpdate.setName(project.getName());
		toUpdate.setDescription(project.getDescription());
		return this.repo.save(toUpdate);
	}

	public List<Project> getAllProjects() {
		
			return this.repo.findAll();
		
	}

}


