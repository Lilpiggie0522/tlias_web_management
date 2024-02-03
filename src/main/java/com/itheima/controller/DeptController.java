package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // -> RestController把返回的Result对象转换为JSON再响应回去
@RequestMapping("/depts")
public class DeptController {
    @Autowired(required = true)
    private DeptService deptService;
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        log.info("search all departments");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    @DeleteMapping("/{id}")
    @Log
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("deleting department according to id {}", id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping
    @Log
    public Result add(@RequestBody Dept dept) {
        log.info("new department created: {} !!!!!", dept);
        deptService.insert(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("searching id {} !!!!!", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping()
    @Log
    public Result update(@RequestBody Dept dept) {
        log.info("updating department name: {} !!!!!!", dept.getName());
        log.info("updating department id: {} !!!!!!", dept.getId());
        deptService.update(dept);
        return Result.success();
    }

}



