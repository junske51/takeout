package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Api("套餐")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @PostMapping
    @ApiOperation("新增套餐")
    public Result save(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐");
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("查询套餐")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("查询套餐");
        PageResult pageResult =setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("批量删除套餐")
    public Result delete(@RequestParam List<Long> ids){
        log.info("批量删除套餐");
        setmealService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("修改前保存已有的信息")
    public Result<SetmealVO> getById(@PathVariable Long id){
        log.info("修改时显示数据");
        SetmealVO setmealVO=setmealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    @PutMapping
    @ApiOperation("修改套餐")
    public Result update(@RequestBody SetmealDTO setmealDTO){
        setmealService.update(setmealDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("起售停售")
    public Result startOrStop(@PathVariable Integer status, Long id){
        setmealService.startOrStop(status,id);
        return Result.success();
    }
}
