package com.project.probook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.probook.exceptions.BookmarkDuplicateException;
import com.project.probook.exceptions.BookmarkInvalidEntryException;
import com.project.probook.exceptions.TypeDuplicateException;
import com.project.probook.exceptions.TypeInvalidEntryException;
import com.project.probook.exceptions.TypeNotFoundException;
import com.project.probook.persistence.domain.Bookmark;
import com.project.probook.persistence.domain.Type;
import com.project.probook.persistence.repo.TypeRepo;


@Service
public class TypeService {

	private TypeRepo repo;

	private BookmarkService bookmarkService;

	@Autowired
	public TypeService(TypeRepo repo, BookmarkService bookmarkService) {
		this.repo = repo;
		this.bookmarkService = bookmarkService;
	}

	public Type createType(Type type) throws TypeInvalidEntryException, TypeDuplicateException {
		validateType(type, true);
		return this.repo.save(type);
	}

	public boolean deleteType(Long id) throws TypeNotFoundException {
		if (!this.repo.existsById(id)) {
			throw new TypeNotFoundException();
		}
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

	public Type findTypeById(Long id) throws TypeNotFoundException {
		return this.repo.findById(id).orElseThrow(() -> new TypeNotFoundException());
	}
	

	public boolean findRepeatedType(Type type) {
		return this.readTypes().contains(type);
	}
	
	public List<Type> readTypes() {
		return this.repo.findAll();
	}

	public Type updateType(Type type, Long id) throws TypeNotFoundException {
		Type toUpdate = findTypeById(id);
		toUpdate.setName(type.getName());
		return this.repo.save(toUpdate);
	}
	
	public boolean validateType(Type type, boolean newType) throws TypeInvalidEntryException, TypeDuplicateException {
		if (type.getName().length() > 50) {
			throw new TypeInvalidEntryException();
		} else if (findRepeatedType(type)) {
			throw new TypeDuplicateException();
		}
		return true;
		
	}
	
	public Type addBookmarkToType(Long id, Bookmark bookmark) throws TypeNotFoundException, BookmarkInvalidEntryException, BookmarkDuplicateException {
		Type toUpdate = this.findTypeById(id);
		if (!this.bookmarkService.findRepeatedBookmark(bookmark)) {
			this.bookmarkService.createBookmark(bookmark);
		}
		
		if (toUpdate.getBookmarks().contains(bookmark)) {
			throw new BookmarkDuplicateException();
		}
		toUpdate.getBookmarks().add(bookmark);

		return this.repo.saveAndFlush(toUpdate);
	}
	
	public List<Type> findOneByName(String name) {
	return this.repo.findByName(name);
	}

}
