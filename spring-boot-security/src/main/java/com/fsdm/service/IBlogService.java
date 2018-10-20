package com.fsdm.service;

import com.fsdm.pojo.Blog;

import java.util.List;


public interface IBlogService {
    List<Blog> getBlogs();
    void deleteBlog(long id);
}
