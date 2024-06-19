package com.itbiye.service;

import com.itbiye.pojo.Expenditure;

import java.util.List;

/**
 * @author youmu
 */

public interface ExpenditureService {
    void add(Expenditure expenditure);

    List<Expenditure> list();

    Expenditure findById(Integer id);

    void update(Expenditure expenditure);

    void deleteById(Integer id);
}
