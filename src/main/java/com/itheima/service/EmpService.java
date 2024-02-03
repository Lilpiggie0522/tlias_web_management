package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;

public interface EmpService {
    PageBean page(Integer pageNo, Integer pageSize, String name,
                  Short gender,
                  LocalDate begin,
                  LocalDate end);

    void delete(Integer[] ids);

    void save(Emp emp);

    Emp get(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
