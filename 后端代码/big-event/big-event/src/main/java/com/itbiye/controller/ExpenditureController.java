package com.itbiye.controller;


import com.itbiye.pojo.*;
import com.itbiye.service.ExpenditureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author youmu
 */
@RestController
@RequestMapping("/expenditure")
public class ExpenditureController {

    @Autowired
    private ExpenditureService expenditureService;

    @PostMapping
    public Result add(@RequestBody @Validated(Expenditure.Add.class) Expenditure expenditure){
        expenditureService.add(expenditure);
        return Result.success();
    }

    @GetMapping
    public Result<List<Expenditure>> list(){
        List<Expenditure> cs = expenditureService.list();
        return Result.success(cs);
    }

    @GetMapping("/detail")
    public Result<Expenditure> detail(Integer id){
        Expenditure c = expenditureService.findById(id);
        return Result.success(c);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Expenditure.Update.class) Expenditure expenditure){
        expenditureService.update(expenditure);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        expenditureService.deleteById(id);
        return Result.success();
    }

}
