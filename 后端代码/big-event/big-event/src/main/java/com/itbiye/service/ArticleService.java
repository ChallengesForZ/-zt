package com.itbiye.service;

import com.itbiye.pojo.Article;
import com.itbiye.pojo.PageBean;

public interface ArticleService {
    //新增文章
    void add(Article article);

    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Article findById(Integer id);

    void update(Article article);

    void deleteById(Integer id);

    void adminadd(Article article);

    PageBean<Article> adminslist(Integer pageNum, Integer pageSize, String state);

    void adminsupdate(Article article);
}
