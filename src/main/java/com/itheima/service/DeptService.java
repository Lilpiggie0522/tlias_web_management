package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    /*
    查询全部部门数据
    */
    List<Dept> list();

    void delete(Integer id) throws Exception;

    void insert(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
