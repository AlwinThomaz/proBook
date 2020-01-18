package com.project.probook.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.probook.persistence.domain.Bookmark;


@Repository
public interface BookmarkRepo extends JpaRepository<Bookmark, Long> {
	
	List<Bookmark> findByName(String name);
	

}
