package com.itbiye.controller;

import com.itbiye.pojo.PageBean;
import com.itbiye.pojo.Result;
import com.itbiye.pojo.Income;
import com.itbiye.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author youmu
 */
@RestController
@RequestMapping("/Income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public Result add(@RequestBody @Validated Income income){
        incomeService.add(income);
        return Result.success();

    }

    @GetMapping
    public Result<PageBean<Income>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId
    ){
        PageBean<Income> pageBean =  incomeService.list(pageNum,pageSize,categoryId);
        return Result.success(pageBean);


    }

    @GetMapping("/detail")
    public Result<Income> detail(Integer id){
        Income i = incomeService.findById(id);
        return Result.success(i);

    }


    @PutMapping
    public Result update(@RequestBody @Validated Income income){
        incomeService.update(income);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        incomeService.deleteById(id);
        return Result.success();
    }



}
