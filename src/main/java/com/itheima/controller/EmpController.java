package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/emps")
@Slf4j
public class EmpController {
    @Autowired private EmpService empService;
    @GetMapping
    public Result getEmps(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          String name,
                          Short gender,
                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        log.info("page: {}, pageSize: {} name: {}, gender: {}, begin: {}, end: {}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    @Log
    public Result deleteByIds(@PathVariable Integer[] ids) {
        log.info("delete by IDs!!!!!!");
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping
    @Log
    public Result save(@RequestBody Emp emp) {
        log.info("emp is {}", emp);
        empService.save(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Emp emp = empService.get(id);
        return Result.success(emp);
    }

    @PutMapping
    @Log
    public Result update(@RequestBody Emp emp) {
        log.info("username is {} !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", emp.getUsername());
        log.info("date is {} !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", emp.getEntrydate());
        empService.update(emp);
        return Result.success();
    }
}
