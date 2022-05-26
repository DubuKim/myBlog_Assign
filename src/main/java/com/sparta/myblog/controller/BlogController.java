package com.sparta.myblog.controller;

import com.sparta.myblog.domain.Blog;
import com.sparta.myblog.domain.BlogRepository;
import com.sparta.myblog.domain.BlogRequestDto;
import com.sparta.myblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BlogController {
    private final BlogRepository blogRepository;
    private final BlogService blogService;

    @PostMapping("/api/blogs")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        return blogRepository.save(blog);
    }

    @GetMapping("/api/blogs/{id}")
    public Optional<Blog> findBlog(@PathVariable Long id) {
        return blogRepository.findById(id);
    }

    @GetMapping("/api/blogs")
    public List<Blog> getBlogs() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    @DeleteMapping("/api/blogs/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/blogs/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody BlogRequestDto requestDto){
        return blogService.update(id, requestDto);
    }

}