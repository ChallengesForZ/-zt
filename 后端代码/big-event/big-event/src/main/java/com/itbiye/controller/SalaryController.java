package com.itbiye.controller;


import com.itbiye.pojo.Result;
import com.itbiye.pojo.Salary;
import com.itbiye.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author youmu
 */
@RestController
@RequestMapping("/Salary")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @PostMapping
    public Result add(@RequestBody @Validated Salary salary){
        salaryService.add(salary);
        return Result.success();

    }

    @GetMapping
    public Result<List<Salary>> list(){
        List<Salary> cs = salaryService.list();
        return Result.success(cs);
    }

    @GetMapping("/detail")
    public Result<Salary> detail(Integer id){
        Salary i = salaryService.findById(id);
        return Result.success(i);

    }


    @PutMapping
    public Result update(@RequestBody @Validated Salary salary){
        salaryService.update(salary);
        return Result.success();
    }


    @DeleteMapping
    public Result delete(Integer id){
        salaryService.deleteById(id);
        return Result.success();
    }

}
