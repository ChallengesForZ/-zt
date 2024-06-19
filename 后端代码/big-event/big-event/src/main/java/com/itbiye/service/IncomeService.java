package com.itbiye.service;

import com.itbiye.pojo.Income;
import com.itbiye.pojo.PageBean;

/**
 * @author youmu
 */
public interface IncomeService {


    void add(Income income);

    PageBean<Income> list(Integer pageNum, Integer pageSize, Integer categoryId);

    Income findById(Integer id);

    void update(Income income);

    void deleteById(Integer id);
}
