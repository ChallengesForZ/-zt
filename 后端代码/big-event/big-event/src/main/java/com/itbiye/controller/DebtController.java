package com.itbiye.controller;

import com.itbiye.pojo.*;
import com.itbiye.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/debt")
public class DebtController {
    @Autowired
    private DebtService debtService;

    @PostMapping
    public Result add(@RequestBody @Validated Debt debt){
        debtService.add(debt);
        return Result.success();

    }

    @GetMapping
    public Result<List<Debt>> list(){
        List<Debt> cs = debtService.list();
        return Result.success(cs);
    }

    @GetMapping("/detail")
    public Result<Debt> detail(Integer id){
        Debt i = debtService.findById(id);
        return Result.success(i);

    }


    @PutMapping
    public Result update(@RequestBody @Validated Debt debt){
        debtService.update(debt);
        return Result.success();
    }


    @DeleteMapping
    public Result delete(Integer id){
        debtService.deleteById(id);
        return Result.success();
    }

}
