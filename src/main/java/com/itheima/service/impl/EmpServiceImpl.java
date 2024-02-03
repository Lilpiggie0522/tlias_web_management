package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {
    @Autowired private EmpMapper empMapper;

    //  不用pageHelper的传统查询方式
    /*@Override
    public PageBean page(Integer page, Integer pageSize) {
        Integer start = (page - 1) * pageSize;
        List<Emp> list = empMapper.page(start, pageSize);
        PageBean pageBean = new PageBean();
        pageBean.setRows(list);
        Long total = empMapper.count();
        pageBean.setTotal(total);
        return pageBean;
    }*/
    @Override
    public PageBean page(Integer start, Integer pageSize, String name,
                         Short gender,
                         LocalDate begin,
                         LocalDate end) {
        PageHelper.startPage(start, pageSize);
        List<Emp> list = empMapper.list(name, gender, begin, end);
        log.info("list is : {} !!!!!!!!!!!!!!!!!!!!!", list);
        Page<Emp> pageHelper = (Page<Emp>) list;
        PageBean pageBean = new PageBean();
        pageBean.setTotal(pageHelper.getTotal());
        pageBean.setRows(pageHelper.getResult());
        return pageBean;
    }

    @Override
    public void delete(Integer[] ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public Emp get(Integer id) {
        return empMapper.get(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getUserByUsernameAndPassword(emp);
    }
}
