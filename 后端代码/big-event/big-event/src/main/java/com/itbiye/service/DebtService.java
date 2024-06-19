package com.itbiye.service;

import com.itbiye.pojo.Debt;

import java.util.List;

public interface DebtService {
    void add(Debt debt);


    Debt findById(Integer id);

    void update(Debt debt);

    void deleteById(Integer id);

    List<Debt> list();
}
