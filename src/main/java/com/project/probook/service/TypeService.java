package com.project.probook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.probook.exceptions.TypeNotFoundException;
import com.project.probook.persistence.domain.Type;
import com.project.probook.persistence.repo.TypeRepo;

@Service
public class TypeService {
	private TypeRepo repo;

 	@Autowired
 	public TypeService(TypeRepo repo) {
 		this.repo = repo;
 	}

 	public Type createType(Type type) {
 		return this.repo.save(type);
 	}

 	public boolean deleteType(Long typeId) throws TypeNotFoundException {
 		if (!this.repo.existsById(typeId)) {
 			throw new TypeNotFoundException();
 		}
 		this.repo.deleteById(typeId);
 		return this.repo.existsById(typeId);
 	}	

 	public Type findTypeById(Long typeId) throws TypeNotFoundException {
 		return this.repo.findById(typeId).orElseThrow(
 				() -> new TypeNotFoundException());
 	}

 	public List<Type> readTypes() {
 		return this.repo.findAll();
 	}

 	public Type updateType(Type type, Long typeId) throws TypeNotFoundException {
 		Type toUpdate = findTypeById(typeId);
 		toUpdate.setName(type.getName());
 		return this.repo.save(toUpdate);
 	}

 	public List<Type> getAllTypes() {

 			return this.repo.findAll();

 	}

 }

