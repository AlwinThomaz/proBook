package com.project.probook.rest;


import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.probook.exceptions.TypeNotFoundException;
import com.project.probook.persistence.domain.Bookmark;
import com.project.probook.persistence.domain.Type;
import com.project.probook.service.TypeService;



@RestController
@RequestMapping("/type")
public class TypeController {
	
	private TypeService service;

	@Autowired
	public TypeController(TypeService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createType")
	public Type createType(@RequestBody Type bookmark) {
		return this.service.createType(bookmark);
	}

	@DeleteMapping("/deleteType/{typeId}")
	public void deleteType(@PathVariable Long typeId) throws TypeNotFoundException {
		this.service.deleteType(typeId);
	}

	@GetMapping("/getType/{typeId}")
	public Type getType(@PathVariable Long typeId) throws TypeNotFoundException {
		return this.service.findTypeById(typeId);
	}
	
	@GetMapping("/getAllTypes")
	public List<Type> getAllTypes() {
		return this.service.readTypes();
	}

	@PutMapping("/updateType")
	public Type updateType(@PathParam("typeId") Long typeId, @RequestBody Type type) throws TypeNotFoundException {
		return this.service.updateType(type, typeId);
	}
	
	@PatchMapping("/update/{typeId}")
	public Type addBookmarkToType(@PathVariable Long typeId, @RequestBody Bookmark bookmark) throws TypeNotFoundException {
		return this.service.addBookmarkToType(typeId, bookmark);
	}
	

}
