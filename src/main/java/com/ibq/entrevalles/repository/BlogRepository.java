package com.ibq.entrevalles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibq.entrevalles.model.Blog;

public interface BlogRepository  extends JpaRepository<Blog, Long>{
	

}
