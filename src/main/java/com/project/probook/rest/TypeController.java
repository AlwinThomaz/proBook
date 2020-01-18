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

import com.project.probook.exceptions.BookmarkDuplicateException;
import com.project.probook.exceptions.BookmarkInvalidEntryException;
import com.project.probook.exceptions.TypeDuplicateException;
import com.project.probook.exceptions.TypeInvalidEntryException;
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
	public Type createType(@RequestBody Type type) throws TypeInvalidEntryException, TypeDuplicateException {
		return this.service.createType(type);
	}

	@DeleteMapping("/deleteType/{id}")
	public void deleteType(@PathVariable Long id) throws TypeNotFoundException {
		this.service.deleteType(id);
	}

	@GetMapping("/getType/{id}")
	public Type getType(@PathVariable Long id) throws TypeNotFoundException {
		return this.service.findTypeById(id);
	}
	
	@GetMapping("/getAllTypes")
	public List<Type> getAllTypes() {
		return this.service.readTypes();
	}

	@PutMapping("/updateType")
	public Type updateType(@PathParam("id") Long id, @RequestBody Type type) throws TypeNotFoundException {
		return this.service.updateType(type, id);
	}
	
	@PatchMapping("/update/{id}")
	public Type addBookmarkToType(@PathVariable Long id, @RequestBody Bookmark bookmark) throws TypeNotFoundException, BookmarkInvalidEntryException, BookmarkDuplicateException {
		return this.service.addBookmarkToType(id, bookmark);
	}
	

}
