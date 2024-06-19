package com.itbiye.controller;


import com.itbiye.pojo.Goods;
import com.itbiye.pojo.PageBean;
import com.itbiye.pojo.Result;
import com.itbiye.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping
    public Result add(@RequestBody @Validated Goods goods) {
        goodsService.add(goods);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Goods>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer warehouseId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Goods> pb =  goodsService.list(pageNum,pageSize,warehouseId,state);
        return Result.success(pb);
    }

    @GetMapping("/detail")
    public Result<Goods> detail(Integer id){
        Goods c = goodsService.findById(id);
        return Result.success(c);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Goods.Update.class) Goods goods){
        goodsService.update(goods);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        goodsService.deleteById(id);
        return Result.success();
    }
}
