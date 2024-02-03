package com.itheima.mapper;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import jakarta.annotation.Resources;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
public interface DeptMapper {
    //  查询全部的部门数据
    @Select("select * from dept")
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
    List<Dept> list();

    // 根据ID删除部门

    @Delete("delete from dept where id=#{id}")
    Integer deleteById(Integer id);

    // 插入部门

    @Insert("insert into dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select * from dept where id = #{id}")
    public Dept getById(Integer id);

    // 根据ID修改部门

    @Update("update dept set name=#{name}, update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
