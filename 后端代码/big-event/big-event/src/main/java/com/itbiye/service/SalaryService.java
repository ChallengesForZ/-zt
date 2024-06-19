package com.itbiye.service;

import com.itbiye.pojo.Salary;

import java.util.List;

public interface SalaryService {
    void add(Salary salary);

    List<Salary> list();

    Salary findById(Integer id);

    void update(Salary salary);

    void deleteById(Integer id);
}
