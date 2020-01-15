package com.project.probook.persistence.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.probook.persistence.domain.Type;

@Repository
public interface TypeRepo extends JpaRepository<Type, Long> {
	
	List<Type> findByName(String name);
	
	
	

} 


