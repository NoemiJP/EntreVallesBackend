package com.ibq.entrevalles.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibq.entrevalles.model.Blog;
import com.ibq.entrevalles.repository.BlogRepository;

@RestController
public class BlogController {
	
	@Autowired
	private BlogRepository blogRepository;
	
    @GetMapping("/blogs")
    public @ResponseBody List<Blog> blog() {
        List<Blog> blog = this.blogRepository.findAll();
        return blog;
    }
    
    @PostMapping("/blog/save")
    public @ResponseBody Blog save(@RequestBody Blog blog) {
        return this.blogRepository.save(blog);
    }

}
