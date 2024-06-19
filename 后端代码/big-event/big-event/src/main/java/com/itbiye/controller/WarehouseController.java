package com.itbiye.controller;

import com.itbiye.pojo.Result;
import com.itbiye.pojo.Warehouse;
import com.itbiye.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public Result add(@RequestBody @Validated(Warehouse.Add.class) Warehouse warehouse){
        warehouseService.add(warehouse);
        return Result.success();
    }

    @GetMapping
    public Result<List<Warehouse>> list(){
        List<Warehouse> cs = warehouseService.list();
        return Result.success(cs);
    }

    @GetMapping("/detail")
    public Result<Warehouse> detail(Integer id){
        Warehouse c = warehouseService.findById(id);
        return Result.success(c);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Warehouse.Update.class) Warehouse warehouse){
        warehouseService.update(warehouse);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        warehouseService.deleteById(id);
        return Result.success();
    }



}
